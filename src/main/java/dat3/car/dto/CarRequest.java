package dat3.car.dto;

import dat3.car.entity.Car;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //must have for builder annotation
@Builder
@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRequest {
    String brand;
    String model;
    Double pricePrDay;
    Integer bestDiscount;

    public static Car getCarEntity (CarRequest c){
        return new Car(c.getBrand(), c.getModel(), c.getPricePrDay(), c.getBestDiscount());
    }

    public CarRequest(Car c){
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
    }
}
