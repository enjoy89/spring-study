package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController // @Controller + @ResponseBody
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestParam @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);

    }

    @PostMapping("api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestParam @Valid CreateMemberRequest request) {

        return null;
    }

    @Data
    public class CreateMemberRequest() {

        private String name;

    }

    @Data
    public class CreateMemberResponse() {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
