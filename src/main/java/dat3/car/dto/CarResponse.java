package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {

    Integer id;
    String brand;
    String model;
    Double pricePrDay;
    Integer bestDiscount;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;

    public CarResponse (Car car, boolean includeAll){
        this.brand = car.getBrand();
        this.model = car.getModel();
        if (includeAll){
            this.id = car.getId();
            this.created = car.getCreated();
            this.edited = car.getEdited();
            this.pricePrDay = car.getPricePrDay();
            this.bestDiscount = car.getBestDiscount();
        }
    }
}
