package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceMockitoTest {

    ReservationService reservationService;
    @Mock
    ReservationRepository reservationRepository;
    @Mock
    CarRepository carRepository;
    @Mock
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(carRepository, memberRepository, reservationRepository);
    }

    private Reservation makeReservation(int reservationId, int carId, Car car, Member member, LocalDate startDate, LocalDate endDate) {
        Reservation reservation = new Reservation(member, car, startDate, endDate);
        reservation.setId(reservationId);
        car.setId(carId);
        reservation.setCreated(LocalDate.now());
        reservation.setEdited(LocalDate.now());
        return reservation;
    }

    @Test
    void getReservations() {
        Reservation r1 = makeReservation(1, 1,
                new Car("brand1", "model1", 100, 10)
                , new Member("user1", "pw1", "email1", "fn1", "ln1", "street1", "city1", "zip1")
                , LocalDate.now(), LocalDate.now().plusDays(3));
        Reservation r2 = makeReservation(2, 2,
                new Car("brand2", "model2", 200, 20)
                , new Member("user2", "pw2", "email2", "fn2", "ln2", "street2", "city2", "zip2")
                , LocalDate.now().plusDays(8), LocalDate.now().plusDays(13));
        when(reservationRepository.findAll()).thenReturn(List.of(r1, r2));
        List<ReservationResponse> responses = reservationService.getReservations();
        assertEquals(2, responses.size(), "Expected 2 reservations");
        assertNotNull(responses.get(0).getCreated(), "Dates must be set since true was passed to getReservations");
    }

    @Test
    void findById() {
        when(reservationRepository.findById(1)).thenReturn(Optional.of(makeReservation(
                1,  1, new Car("brand1", "model1", 100, 10)
                , new Member("user1", "pw1", "email1", "fn1", "ln1", "street1", "city1", "zip1")
                , LocalDate.now(), LocalDate.now().plusDays(3))));
        ReservationResponse response = reservationService.findById(1);
        assertEquals(1, response.getId());
        assertNotNull(response.getCreated());
    }

    /*@Test
    void addReservationSucces() {
        Reservation reservation = makeReservation(
                1,  1, new Car("brand1", "model1", 100, 10)
                , new Member("user1", "pw1", "email1", "fn1", "ln1", "street1", "city1", "zip1")
                , LocalDate.now(), LocalDate.now().plusDays(3));
        memberRepository.save(reservation.getMember());
        carRepository.save(reservation.getCar());
        ReservationRequest reservationRequest = new ReservationRequest(reservation.getMember().getUsername(),
                reservation.getCar().getId(), reservation.getReservationDateStart(), reservation.getReservationDateEnd());
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        ReservationResponse response = new ReservationResponse(reservation, true, false, false);
        when(reservationService.addReservation(reservationRequest)).thenReturn(response);

        response = reservationService.addReservation(reservationRequest);

        assertEquals(1, response.getId());
        assertEquals("user1", response.getMember().getUsername());
        assertEquals("brand1", response.getCar().getBrand());
    }*/

    @Test
    void addReservationCarNotAvailable() {

    }

    @Test
    void addReservationStartDateBeforeToday() {

    }

    @Test
    void addReservationEndDateBeforeStartDate() {

    }

    @Test
    void deleteReservation() {

    }

}
