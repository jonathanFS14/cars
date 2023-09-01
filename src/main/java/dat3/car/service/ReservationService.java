package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Reservation;
import dat3.car.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    public ReservationService (ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }


    public ReservationResponse addReservation(ReservationRequest body) {
        Reservation newReservation = ReservationRequest.getReservationEntity(body);
        newReservation = reservationRepository.save(newReservation);
        return new ReservationResponse(newReservation, true, true, true);
    }

    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> response =
                reservations.stream().map(( (reservation) -> new ReservationResponse(reservation, true, true, true))).toList();
        return response;
    }
}
