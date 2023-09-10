package dat3.car.service;

import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberResponse;
import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public ReservationService(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }


    public ReservationResponse addReservation(ReservationRequest body) {
        Member member = memberRepository.findById(body.getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No member with this id found"));
        Car car = carRepository.findById(body.getCarId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Car with this id found"));
        if (body.getReservationDateStart().isAfter(body.getReservationDateEnd())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date must be before end date");
        }
        if (body.getReservationDateStart().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date must be today or after");
        }
        List<Reservation> carReservation = car.getReservations();
        if (!carReservation.isEmpty()) {
            for (Reservation r : carReservation) {
                if (body.getReservationDateStart().isBefore(r.getReservationDateEnd()) &&
                        body.getReservationDateEnd().isAfter(r.getReservationDateStart()) ||
                        body.getReservationDateStart().isEqual(r.getReservationDateStart()) ||
                        body.getReservationDateEnd().isEqual(r.getReservationDateEnd())
                ) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is reserved in this period");
                }
            }
        }
        Reservation res = reservationRepository.save(new Reservation(member, car, body.getReservationDateStart(), body.getReservationDateEnd()));
        return new ReservationResponse(res, true, false, false);
    }

    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> response =
                reservations.stream().map(((reservation) -> new ReservationResponse(reservation, true, false, false))).toList();
        return response;
    }

    public ResponseEntity<Boolean> deleteReservation(int id){
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
        return ResponseEntity.ok(true);
    }

    public ReservationResponse findById(int id){
        Reservation reservation = getReservationById(id);
        ReservationResponse response = new ReservationResponse(reservation, true, false, false);
        return response;
    }

    private Reservation getReservationById(int id){
        return reservationRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with this id does not exist"));
    }
}
