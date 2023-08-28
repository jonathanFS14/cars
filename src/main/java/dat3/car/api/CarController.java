package dat3.car.api;

import dat3.car.repository.CarRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    CarRepository carRepository;

    public CarController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

}
