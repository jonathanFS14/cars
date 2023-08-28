package dat3.car.api;

import dat3.car.repository.MemberRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


}
