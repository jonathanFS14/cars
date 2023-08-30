package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email1", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(true);
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNotNull(time, "expects date to be set when true is passed for getmembers");
    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(false);
        LocalDateTime time = memberResponses.get(0).getCreated();
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
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        //TODO
    }

    @Test
        /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
         * Internally addMember savex a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {
        //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
        //TODO
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        MemberRequest m3 = new MemberRequest("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1");
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> memberService.addMember(m3));
        assertEquals(/*HttpStatus.BAD_REQUEST*/ HttpStatusCode.valueOf(400), ex.getStatusCode());
        //This should test that a ResponseStatus exception is thrown with status= 400 (BAD_REQUEST)
    }

    // hertil

    @Test
    void testEditMemberWithExistingUsername() {
        //TODO
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        //TODO
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        //TODO
    }

    @Test
    void testSetRankingForUser() {
        //TODO
    }

    @Test
    void testSetRankingForNoExistingUser() {
        //TODO
    }
    @Test
    void testDeleteMemberByUsername() {
        //TODO
    }

    @Test
    void testDeleteMember_ThatDontExist() {
        //TODO
    }
}
