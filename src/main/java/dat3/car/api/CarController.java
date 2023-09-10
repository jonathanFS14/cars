package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    CarService carService;
    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping
    List<CarResponse> getCars(){
        return carService.getCars(/*include all fields*/true);
    }

    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable int id) throws Exception {
        return carService.findById(id);}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    @PutMapping("/{id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
        return carService.editCar(body,id);
    }

    @PatchMapping("/bestdiscount/{id}/{value}")
    ResponseEntity<Boolean> setBestDiscount(@PathVariable int id, @PathVariable int value) {
        return carService.setBestDiscount(id,value);
    }

    // Security admin
    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable int id) {
        carService.deleteCar(id);
    }

    @GetMapping("/noreservations")
    List<CarResponse> getCarsWithNoReservation(){
        return carService.getCarsWithNoReservations(true);
    }

    @GetMapping("/modelandbrandlike/{model}/{brand}")
    List<CarResponse> getCarsWithModelAndBrandLike(@PathVariable String model,@PathVariable String brand){
        return carService.findByModelAndBrand(model, brand, true);
    }

    @GetMapping("/averagepriceallcars")
    Double getAveragePriceForAllCars(){
        return carService.getAveragePriceForAllCars();
    }


}
