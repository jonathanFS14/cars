package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> response =
                members.stream().map(((member) -> new MemberResponse(member, includeAll, false))).toList();
        return response;
    }

    public MemberResponse addMember(MemberRequest body) {
        if (memberRepository.existsById(body.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user already exists");
        }

        Member newMember = MemberRequest.getMemberEntity(body);
        newMember.addRole(Role.USER);
        newMember.addRole(Role.ADMIN);
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
        return new MemberResponse(member, true, false);
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

    public List<MemberResponse> findAllUserWithReservations(boolean includeAll) {
        List<Member> members = memberRepository.findByReservationsIsNotEmpty();
        if (members.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no member has made a reservation yet");
        }

        List<MemberResponse> response =
                members.stream().map(((member) -> new MemberResponse(member, includeAll, true))).toList();
        return response;
    }


    private Member getMemberByUsername(String username) {
        return memberRepository.findById(username).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this username does not exist"));
    }

}
