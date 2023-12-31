package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {

    Integer id;
    String brand;
    String model;
    Double pricePrDay;
    Integer bestDiscount;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate created;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate edited;
    List<ReservationResponse> reservations;

    public CarResponse (Car car, boolean includeAll, boolean includeReservations){
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePrDay = car.getPricePrDay();
        if (includeAll){
            this.created = car.getCreated();
            this.edited = car.getEdited();
            this.bestDiscount = car.getBestDiscount();
        }
        if(includeReservations){
            this.reservations = car.getReservations().stream().map(r -> new ReservationResponse(r, false, false, new Member())).toList();
        }
    }

    public void addReservation(ReservationResponse reservation) {
        reservations.add(reservation);
    }
}
