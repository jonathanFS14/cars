package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarService {

    CarRepository carRepository;
    ReservationRepository reservationRepository;
    public CarService(CarRepository carRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<CarResponse> getCars(boolean includeAll) {
        List<Car> cars = carRepository.findAll();
        List<CarResponse> response =
                cars.stream().map(( (Car) -> new CarResponse(Car, includeAll, true))).toList();
        for (CarResponse carResponse: response) {
            List<Reservation> reservations = reservationRepository.findReservationsByCarId(carResponse.getId());
            List<ReservationResponse> reservationResponses =
                    reservations.stream().map(((reservation) -> new ReservationResponse(reservation, true, false, new Member()))).toList();
            for (ReservationResponse reservationResponse: reservationResponses) {
                carResponse.addReservation(reservationResponse);
            }
        }
        return response;
    }

    public CarResponse findById(int id) {
        Car car = getCarById(id);
        CarResponse response = new CarResponse(car, true, true);
        List<Reservation> reservations = reservationRepository.findReservationsByCarId(response.getId());
        List<ReservationResponse> reservationResponses =
                reservations.stream().map(((reservation) -> new ReservationResponse(reservation, false, false, new Member()))).toList();
        for (ReservationResponse reservationResponse: reservationResponses) {
            response.addReservation(reservationResponse);
        }
        return response;
    }

    public CarResponse addCar(CarRequest body) {
        Car newcar = CarRequest.getCarEntity(body);
        newcar = carRepository.save(newcar);
        return new CarResponse(newcar, true, false);
    }

    public ResponseEntity<Boolean> editCar(CarRequest body, int id) {
        Car car = getCarById(id);
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> setBestDiscount(int id, int value) {
        Car car = getCarById(id);
        car.setBestDiscount(value);
        carRepository.save(car);
        return ResponseEntity.ok(true);
    }

    public void deleteCar(int id) {
        Car car = getCarById(id);
        carRepository.delete(car);
    }

    private Car getCarById(int id){
        return carRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this id does not exist"));
    }

}
