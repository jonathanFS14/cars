package dat3.car.service;

import dat3.car.dto.CarResponse;
import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public ReservationResponse addReservation(ReservationRequest body) {
        if (body.getReservationDateStart().isAfter(body.getReservationDateEnd())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date must be before end date");
        }
        if (body.getReservationDateStart().isBefore(java.time.LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date must be after today");
        }
         List<Reservation> reservations = reservationRepository.findReservationsByCar_Id(body.getCar().getId());
        if (!reservations.isEmpty()) {
            for (Reservation r : reservations) {
                if (body.getReservationDateStart().isBefore(r.getReservationDateEnd()) &&
                        body.getReservationDateEnd().isAfter(r.getReservationDateStart()) ||
                        body.getReservationDateStart() == r.getReservationDateStart() ||
                        body.getReservationDateEnd() == r.getReservationDateEnd()
                ) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is already reserved in this period");
                }
            }
        }
        Reservation newReservation = ReservationRequest.getReservationEntity(body);
        reservationRepository.save(newReservation);
        return new ReservationResponse(newReservation, true, true, true);
    }

    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> response =
                reservations.stream().map(((reservation) -> new ReservationResponse(reservation, true, false, false))).toList();
        return response;
    }
}
