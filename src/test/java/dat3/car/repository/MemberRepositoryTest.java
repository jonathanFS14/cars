package dat3.car.repository;

import dat3.car.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if (!isInitialized){
           memberRepository.save(new Member("klaus123", "1234", "klaus@hotmail.dk", "klaus", "klausen",
                   "klausvej12", "københavn", "2350"));
           memberRepository.save(new Member("bente123", "1234", "bente@hotmail.dk", "bente", "bentsen",
                   "bentevej12", "københavn", "2350"));
            isInitialized = true;
        }
    }

    @Test
    public void deleteAll (){
        memberRepository.deleteAll();
        assertEquals(0, memberRepository.count());
    }

    @Test
    public void testAll(){
        long count = memberRepository.count();
        assertEquals(2, count);
    }

}