package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //A must for @Builder
@Builder  //I will demo it's purpose in the class
@ToString
//It's really IMPORTANT that you understand the purpose of this annotation
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    Integer id;
    MemberResponse member;
    CarResponse car;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate reservationDateStart;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate reservationDateEnd;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate created;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate edited;

    public ReservationResponse(Reservation r, boolean includeAll, boolean includeAllMember, boolean includeAllCar) {
        this.member = new MemberResponse(r.getMember(), includeAllMember, false);
        this.car = new CarResponse(r.getCar(), includeAllCar, false);
        this.reservationDateStart = r.getReservationDateStart();
        this.reservationDateEnd = r.getReservationDateEnd();
        if(includeAll){
            this.id = r.getId();
            this.created = r.getCreated();
            this.edited = r.getEdited();
        }
    }

    // this is used for when I want to print the reservation inside members reservation list, I already have the member
    public ReservationResponse(Reservation r, boolean includeAll, boolean includeAllCar, Car car) {
        this.car = new CarResponse(r.getCar(), includeAllCar, false);
        this.reservationDateStart = r.getReservationDateStart();
        this.reservationDateEnd = r.getReservationDateEnd();
        if(includeAll){
            this.id = r.getId();
            this.created = r.getCreated();
            this.edited = r.getEdited();
        }
    }

    public ReservationResponse(Reservation r, boolean includeAll, boolean includeAllMember, Member member) {
        this.member = new MemberResponse(r.getMember(), includeAllMember, false);
        this.reservationDateStart = r.getReservationDateStart();
        this.reservationDateEnd = r.getReservationDateEnd();
        if(includeAll){
            this.id = r.getId();
            this.created = r.getCreated();
            this.edited = r.getEdited();
        }
    }
}
