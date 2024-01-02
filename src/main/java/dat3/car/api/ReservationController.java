package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.service.ReservationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/reservations")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    List<ReservationResponse> getReservations() {
        return reservationService.getReservations();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ReservationResponse addReservationV2(@RequestBody ReservationRequest body, Principal principal) {
        body.setUsername(principal.getName());
        return reservationService.addReservation(body);
    }

    @GetMapping("/{userName}")
    public List<ReservationResponse> getReservationsForUser(@PathVariable String userName){
        List<ReservationResponse> res = reservationService.getReservationsForUser(userName);
        return res;
    }

    @GetMapping("/reservations-for-authenticated")
    public List<ReservationResponse> getReservationsForUser(Principal principal){
        List<ReservationResponse> res = reservationService.getReservationsForUser(principal.getName());
        return res;
    }

    }