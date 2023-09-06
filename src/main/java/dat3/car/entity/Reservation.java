package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class Reservation extends AdminDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDateStart;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDateEnd;

    public Reservation(Member member, Car car, LocalDate reservationDateStart, LocalDate reservationDateEnd) {
        this.member = member;
        this.car = car;
        this.reservationDateStart = reservationDateStart;
        this.reservationDateEnd = reservationDateEnd;
        car.addReservation(this);
        member.addReservation(this);
    }
}