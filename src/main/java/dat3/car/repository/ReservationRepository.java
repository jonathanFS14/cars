package dat3.car.repository;

import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findReservationsByMember_Username(String member_username);
    List<Reservation> findReservationsByCar_Id(int car_id);
}
