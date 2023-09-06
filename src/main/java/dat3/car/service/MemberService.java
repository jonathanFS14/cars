package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    MemberRepository memberRepository;
    ReservationRepository reservationRepository;

    public MemberService(MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> response =
                members.stream().map(( (member) -> new MemberResponse(member, includeAll, true))).toList();
        for (MemberResponse memberResponse: response) {
            List<Reservation> reservations = reservationRepository.findReservationsByMember_Username(memberResponse.getUsername());
            List<ReservationResponse> reservationResponses =
                    reservations.stream().map(((reservation) -> new ReservationResponse(reservation, false, false, new Car()))).toList();
            for (ReservationResponse reservationResponse: reservationResponses) {
                memberResponse.addReservation(reservationResponse);
            }
        }
        return response;
    }

    public MemberResponse addMember(MemberRequest body) {
        if (memberRepository.existsById(body.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user already exists");
        }
        Member newMember = MemberRequest.getMemberEntity(body);
        newMember = memberRepository.save(newMember);
        return new MemberResponse(newMember, true, false);
    }

    public ResponseEntity<Boolean> editMember(MemberRequest body, String username) {
        Member member = getMemberByUsername(username);
        if (!body.getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change username");
        }
        member.setPassword(body.getPassword());
        member.setEmail(body.getEmail());
        member.setFirstName(body.getFirstName());
        member.setLastName(body.getLastName());
        member.setStreet(body.getStreet());
        member.setCity(body.getCity());
        member.setZip(body.getZip());
        memberRepository.save(member);
        return ResponseEntity.ok(true);
    }

    public MemberResponse findById(String username) {
        Member member = getMemberByUsername(username);
        MemberResponse response = new MemberResponse(member, true, true);
        List<Reservation> reservations = reservationRepository.findReservationsByMember_Username(username);
        List<ReservationResponse> reservationResponses =
                reservations.stream().map(((reservation) -> new ReservationResponse(reservation, false, false, new Car()))).toList();
        for (ReservationResponse reservationResponse: reservationResponses) {
            response.addReservation(reservationResponse);
        }
        return response;
    }

    public void deleteMember(String username) {
        Member member = getMemberByUsername(username);
        memberRepository.delete(member);
    }

    public ResponseEntity<Boolean> setRankingForUser(String username, int value) {
        Member member = getMemberByUsername(username);
        member.setRanking(value);
        memberRepository.save(member);
        return ResponseEntity.ok(true);
    }

    private Member getMemberByUsername(String username) {
        return memberRepository.findById(username).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this username does not exist"));
    }

}
