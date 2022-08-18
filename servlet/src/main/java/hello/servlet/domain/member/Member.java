package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id;    // 회원 Id
    private String username;    // 회원 이름
    private int age;    // 회원 나이

    public Member() {
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
