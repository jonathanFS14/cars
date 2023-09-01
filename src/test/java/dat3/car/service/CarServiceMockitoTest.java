package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceMockitoTest {

    private CarService carService;
    @Mock
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carService = new CarService(carRepository);
    }

    private Car makeCar(int id, String brand, String model, double pricePrDay, int bestDiscount) {
        Car car = new Car(brand, model, pricePrDay, bestDiscount);
        car.setId(id);
        car.setCreated(LocalDateTime.now());
        car.setEdited(LocalDateTime.now());
        return car;
    }

    @Test
    void findById() {
        when(carRepository.findById(1)).thenReturn(Optional.of(makeCar(1, "brand1", "model1", 100, 10)));
        CarResponse response = carService.findById(1);
        assertEquals(1, response.getId());
        assertNotNull(response.getCreated());
    }

    @Test
    void findByIdDoesNotExist() {
        when(carRepository.findById(1)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> carService.findById(1));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void addCar() {
        Car car = makeCar(1, "brand1", "model1", 100, 10);
        when(carRepository.save(any(Car.class))).thenReturn(car);
        CarRequest request = new CarRequest(car);
        CarResponse response = carService.addCar(request);
        assertEquals(1, response.getId());
        assertEquals("brand1", response.getBrand());
        assertEquals("model1", response.getModel());
        assertEquals(100, response.getPricePrDay());
        assertEquals(10, response.getBestDiscount());
    }

    @Test
    void getCars() {
        Car c1 = makeCar(1, "brand1", "model1", 100, 10);
        Car c2 = makeCar(2, "brand2", "model2", 200, 20);
        when(carRepository.findAll()).thenReturn(List.of(c1, c2));
        List<CarResponse> responses = carService.getCars(true);
        assertEquals(2, responses.size());
        assertNotNull(responses.get(0).getCreated());
    }

    @Test
    void editCar() {
        Car car = makeCar(1, "brand1", "model1", 100, 10);
        when(carRepository.findById(1)).thenReturn(Optional.of(car));
        CarRequest request = new CarRequest(car);
        request.setBrand("brand2");
        request.setModel("model2");
        request.setPricePrDay(200.3);
        request.setBestDiscount(20);
        assertEquals("brand1", car.getBrand());
        assertEquals("model1", car.getModel());
        assertEquals(100, car.getPricePrDay());
        assertEquals(10, car.getBestDiscount());
        carService.editCar(request, 1);
        assertEquals("brand2", car.getBrand());
        assertEquals("model2", car.getModel());
        assertEquals(200.3, car.getPricePrDay());
        assertEquals(20, car.getBestDiscount());
    }

    @Test
    void deleteCar() {
        Car car = makeCar(1, "brand1", "model1", 100, 10);
        when(carRepository.findById(1)).thenReturn(Optional.of(car));
        carService.deleteCar(1);
        verify(carRepository).delete(car);
    }

}
