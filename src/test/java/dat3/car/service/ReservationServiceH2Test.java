package dat3.car.service;

import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberResponse;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ReservationServiceH2Test {
    @Autowired
    ReservationRepository reservationRepository;
    ReservationService reservationService;
    @Autowired
    CarRepository carRepository;
    @Autowired
    MemberRepository memberRepository;

    Reservation r1, r2;
    Member m1, m2;
    Car c1, c2;

    @BeforeEach
    void setUp() {
        c1 = carRepository.save(new Car("brand1", "model1", 100, 10));
        c2 = carRepository.save(new Car("brand2", "model2", 200, 20));
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1", "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email11", "fn2", "ln2", "street2", "city2", "zip2"));
        r1 = reservationRepository.save(new Reservation(m1, c1, LocalDate.now(), LocalDate.now().plusDays(3)));
        r2 = reservationRepository.save(new Reservation(m2, c2, LocalDate.now().plusDays(8), LocalDate.now().plusDays(13)));
        reservationService = new ReservationService(carRepository, memberRepository, reservationRepository);
    }

    @Test
    void getReservations() {
        List<ReservationResponse> reservationResponse = reservationService.getReservations();
        CarResponse carResponse = reservationResponse.get(0).getCar();
        MemberResponse memberResponse = reservationResponse.get(0).getMember();
        LocalDate startDate = reservationResponse.get(0).getReservationDateStart();
        LocalDate endDate = reservationResponse.get(0).getReservationDateEnd();
        assertNotNull(carResponse);
        assertNotNull(memberResponse);
        assertNotNull(startDate);
        assertNotNull(endDate);
    }

    @Test
    void getReservation(){
        ReservationResponse response = reservationService.findById(r1.getId());
        assertEquals(r1.getId(), response.getId());
    }

    @Test
    void findByIdNotFound() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> reservationService.findById(3));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void addReservationSucces() {
        ReservationRequest c3 = new ReservationRequest(m1.getUsername(), c1.getId(), LocalDate.now().plusDays(4), LocalDate.now().plusDays(5));
        ReservationResponse response = reservationService.addReservation(c3);
        assertEquals(m1.getUsername(), response.getMember().getUsername());
        assertEquals(c1.getBrand(), response.getCar().getBrand());
        assertEquals(c1.getModel(), response.getCar().getModel());
        assertEquals(LocalDate.now().plusDays(4), response.getReservationDateStart());
        assertEquals(LocalDate.now().plusDays(5), response.getReservationDateEnd());
    }

    @Test
    void addReservationCarNotAvailable() {
        ReservationRequest c3 = new ReservationRequest(m1.getUsername(), c1.getId(), LocalDate.now().plusDays(2), LocalDate.now().plusDays(5));
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> reservationService.addReservation(c3));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void addReservationStartDateBeforeToday() {
        ReservationRequest c3 = new ReservationRequest(m1.getUsername(), c1.getId(), LocalDate.now().minusDays(2), LocalDate.now().plusDays(5));
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> reservationService.addReservation(c3));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void addReservationEndDateBeforeStartDate() {
        ReservationRequest c3 = new ReservationRequest(m1.getUsername(), c1.getId(), LocalDate.now().plusDays(5), LocalDate.now().plusDays(2));
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> reservationService.addReservation(c3));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void deleteReservation(){
        ResponseEntity<Boolean> response = reservationService.deleteReservation(r1.getId());
        assertEquals(true, response.getBody());
    }

}
