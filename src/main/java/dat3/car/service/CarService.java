package dat3.car.service;

import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.stereotype.Service;

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
}
