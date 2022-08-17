package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData {    // Json 형식으로 파싱하기 위해 만든 객체
    private String username;
    private String age;
}
