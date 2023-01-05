package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

// @Controller -> 반환 값이 String이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링된다.
// @RestController -> 반환 값을 Http 메시지 바디에 바로 입력한다.

@RestController
public class MappingController {

    private Logger log = LoggerFactory .getLogger(getClass());

    /**
     * 기본 요청
     * 둘 다 허용 -> "/hello-basic", "/hello-basic/"
     * HTTP 메서드 모두 허용 -> GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * method 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     * PathVariable(경로변수) 사용
     * @PathVariable("userId") String userId -> @PathVariable userId
     */
//    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * @PathVaruable의 이름과 파라미터의 이름이 같으면 생략 가능
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath2(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId{}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * 조건은 아래와 같음
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * 조건은 아래와 같음
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑 (Content-Type, consume)
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
