package dat3.car.dto;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequest {

    Member member;
    Car car;
    LocalDateTime reservationDateStart;
    LocalDateTime reservationDateEnd;

    public static Reservation getReservationEntity (ReservationRequest r){
        return new Reservation(r.getMember(), r.getCar(), r.getReservationDateStart(), r.getReservationDateEnd());
    }

    public ReservationRequest(Reservation r){
        this.member = r.getMember();
        this.car = r.getCar();
        this.reservationDateStart = r.getReservationDateStart();
        this.reservationDateEnd = r.getReservationDateEnd();
    }

}
