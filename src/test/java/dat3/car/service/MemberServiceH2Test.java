package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2;

    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1", "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email11", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(true);
        LocalDate time = memberResponses.get(0).getCreated();
        assertNotNull(time, "expects date to be set when true is passed for getmembers");
    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(false);
        LocalDate time = memberResponses.get(0).getCreated();
        assertNull(time, "expects date to not be set when false is passed for getmembers");
    }

    @Test
    void testFindByIdFound() {
        MemberResponse response = memberService.findById(m1.getUsername());
        assertEquals("user1", response.getUsername());
    }

    @Test
    void testFindByIdNotFound() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> memberService.findById("i dont exist"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void testAddMember_UserDoesNotExist() {
        MemberRequest m3 = new MemberRequest("user100", "pw1", "email1", "fn1", "ln1", "street1", "city1", "zip1");
        MemberResponse response = memberService.addMember(m3);
        assertEquals("user100", response.getUsername());
        assertEquals("email1", response.getEmail());
        assertEquals("city1", response.getCity());
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        MemberRequest m3 = new MemberRequest("user1", "pw1", "email1", "fn1", "ln1", "street1", "city1", "zip1");
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> memberService.addMember(m3));
        assertEquals(/*HttpStatus.BAD_REQUEST*/ HttpStatusCode.valueOf(400), ex.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        assertEquals(ResponseEntity.ok(true), memberService.editMember
                (new MemberRequest("user1", "pw1", "email1", "fn1", "ln1", "street1", "city1", "zip1"), "user1"));
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> memberService.editMember
                (new MemberRequest("user1", "pw1", "email1", "fn1", "ln1", "street1", "city1", "zip1"), "user111"));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        MemberRequest request = new MemberRequest(m1);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                memberService.editMember(request, "user2"));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testSetRankingForUser() {
        assertEquals(0, m1.getRanking());
        memberService.setRankingForUser("user1", 100);
        assertEquals(100, m1.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> memberService.setRankingForUser("does not exist", 5));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testDeleteMemberByUsername() {
        MemberResponse response = memberService.findById("user1");
        assertEquals("user1", response.getUsername());
        memberService.deleteMember("user1");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                memberService.findById("user1"));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testDeleteMember_ThatDontExist() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                memberService.deleteMember("12222"));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
