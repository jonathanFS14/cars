package dat3.car.repository;

import dat3.car.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;
    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if (!isInitialized){
            carRepository.save(new Car("ford", "camry", 50, 20));
            carRepository.save(new Car("nissan", "elantra", 65, 15));
            isInitialized = true;
        }
    }

    @Test
    public void deleteAll (){
        carRepository.deleteAll();
        assertEquals(0, carRepository.count());
    }

    @Test
    public void testAll(){
        long count = carRepository.count();
        assertEquals(2, count);
    }

    @Test
    void findByName() {
        Car c1 = carRepository.findByBrand("ford");
        assertEquals("ford", c1.getBrand());
    }

    @Test
    void findByNameLike() {
        Car c2 = carRepository.findByModelLike("%antra%");
        assertEquals("elantra", c2.getModel());
    }

    @Test
    void findByModelAndBrand(){
        Car c1 = carRepository.findByModelAndBrand("camry", "ford");
        assertEquals("ford", c1.getBrand());
        assertEquals("camry", c1.getModel());
    }

    @Test
    public void testFindCarsNotReserved() {
        List<Car> cars = carRepository.findByReservationsIsEmpty();
        assertEquals(2, cars.size());
    }

    @Test
    public void testFindAveragePricePerDay() {
        Double avgPrice = carRepository.findAveragePricePerDay();
        assertEquals(57.5, avgPrice);
    }

}