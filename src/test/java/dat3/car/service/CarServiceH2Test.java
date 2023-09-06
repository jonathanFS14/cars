package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceH2Test {

    @Autowired
    CarRepository carRepository;
    ReservationRepository reservationRepository;
    CarService carService;

    Car c1, c2;

    @BeforeEach
    void setUp() {
        c1 = carRepository.save(new Car("brand1", "model1", 100, 10));
        c2 = carRepository.save(new Car("brand2", "model2", 200, 20));
        carService = new CarService(carRepository, reservationRepository);
    }

    @Test
    void getCarsAllDetails() {
        List<CarResponse> CarResponse = carService.getCars(true);
        Integer id = CarResponse.get(0).getId();
        LocalDate created = CarResponse.get(0).getCreated();
        assertNotNull(id);
        assertNotNull(created);
    }

    @Test
    void getCarsNoDetails() {
        List<CarResponse> CarResponse = carService.getCars(false);
        Integer id = CarResponse.get(0).getId();
        LocalDate created = CarResponse.get(0).getCreated();
        assertNull(id);
        assertNull(created);
    }

    @Test
    //@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
        // ovenstående dropper alle h2 databasens tables og creater dem inden metoden så der vises det rigtige data
        // hvis det ikke bliver gjort vil auto_increment på id gøre at det er afhængigt af hvornår testen bliver udført i forhold til de andre
        // it creates a new application context before the test is invoked, og derfor skal det kun gøres hvis det er nødvendigt
        // test bliver markant langsommere af denne annotation og derfor skal den kan bruges med methodmode og ikke classmode
    void findById() {
        CarResponse response = carService.findById(c1.getId());
        assertEquals(c1.getId(), response.getId());
    }

    @Test
    void findByIdNotFound() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> carService.findById(3));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void addCar() {
        CarRequest c3 = new CarRequest("brand3", "model3", 300.3, 30);
        CarResponse response = carService.addCar(c3);
        assertEquals("brand3", response.getBrand());
        assertEquals("model3", response.getModel());
        assertEquals(300.3, response.getPricePrDay());
        assertEquals(30, response.getBestDiscount());
    }

    @Test
    void editCarThatExist() {
        CarRequest c3 = new CarRequest("brand3", "model3", 300.3, 30);
        ResponseEntity<Boolean> response = carService.editCar(c3, c1.getId());
        assertEquals(true, response.getBody());
        assertEquals("brand3", c1.getBrand());
        assertEquals("model3", c1.getModel());
    }

    @Test
    void setBestDiscount() {
        assertEquals(10, c1.getBestDiscount());
        carService.setBestDiscount(c1.getId(), 100);
        assertEquals(100, c1.getBestDiscount());
    }

    @Test
    void deleteCarThatExist() {
        CarResponse response = carService.findById(c1.getId());
        assertEquals(c1.getId(), response.getId());
        carService.deleteCar(c1.getId());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                carService.findById(c1.getId()));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}