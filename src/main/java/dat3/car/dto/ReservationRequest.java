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

    String username;
    int carId;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate reservationDateStart;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate reservationDateEnd;

    public ReservationRequest(Reservation r){
        this.username = r.getMember().getUsername();
        this.carId = r.getCar().getId();
        this.reservationDateStart = r.getReservationDateStart();
        this.reservationDateEnd = r.getReservationDateEnd();
    }

}
