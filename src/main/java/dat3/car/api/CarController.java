package dat3.car.api;

import dat3.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;
    public CarController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

}
