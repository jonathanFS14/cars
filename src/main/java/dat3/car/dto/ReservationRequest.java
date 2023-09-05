package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import lombok.*;

import java.time.LocalDate;

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
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate reservationDateStart;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate reservationDateEnd;

    public static Reservation getReservationEntity (ReservationRequest r){
        return new Reservation(r.getMember(), r.getCar(), r.getReservationDateStart()
                , r.getReservationDateEnd());
    }

    public ReservationRequest(Reservation r){
        this.member = r.getMember();
        this.car = r.getCar();
    }


}
