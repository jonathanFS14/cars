package dat3.car.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Reservation extends AdminDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    private LocalDateTime reservationDateStart;
    private LocalDateTime reservationDateEnd;

    public Reservation(Member member, Car car, LocalDateTime reservationDateStart, LocalDateTime reservationDateEnd) {
        this.member = member;
        this.car = car;
        this.reservationDateStart = reservationDateStart;
        this.reservationDateEnd = reservationDateEnd;
    }
}