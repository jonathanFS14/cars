package dat3.car.repository;

import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findReservationsByMember_Username(String member_username);
    List<Reservation> findReservationsByCarId(int carId);

    boolean existsByCar_IdAndReservationDateStart(int id, LocalDate date);
    boolean existsByCar_IdAndReservationDateEnd(int id, LocalDate date);
}
