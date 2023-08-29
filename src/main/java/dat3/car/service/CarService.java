package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    CarRepository carRepository;
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(boolean includeAll) {
        List<CarResponse> response = new ArrayList<>();
        List<Car> cars = carRepository.findAll();
        for(Car car: cars){
            CarResponse cr = new CarResponse(car, includeAll);
            response.add(cr);
        }
        return response;
    }

    public CarResponse findById(int id) {
        Car car = carRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this id does not exist"));
        return new CarResponse(car, true);
    }

    public CarResponse addCar(CarRequest body) {
        Car newcar = CarRequest.getCarEntity(body);
        newcar = carRepository.save(newcar);
        return new CarResponse(newcar, true);
    }

    public ResponseEntity<Boolean> editCar(CarRequest body, int id) {
        Car car = carRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"car with this id does not exist"));
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> setBestDiscount(int id, int value) {
        Car car = carRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"car with this credentials does not exist"));
        car.setBestDiscount(value);
        carRepository.save(car);
        return ResponseEntity.ok(true);
    }

    public void deleteCar(int id) {
        Car car = carRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"car with this id does not exist"));
        carRepository.delete(car);
    }

}
