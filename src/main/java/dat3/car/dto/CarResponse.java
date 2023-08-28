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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {

    String brand;
    String model;
    Double pricePrDay;
    Integer besDiscount;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;

    public CarResponse (Car car, boolean includeAll){
        this.brand = car.getBrand();
        this.model = car.getModel();
        if (includeAll){
            this.created = car.getCreated();
            this.edited = car.getEdited();
            this.pricePrDay = car.getPricePrDay();
            this.besDiscount = car.getBestDiscount();
        }
    }
}
