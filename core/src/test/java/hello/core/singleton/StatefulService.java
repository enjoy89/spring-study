package hello.core.singleton;

/**
 * 객체를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에
 * 싱글톤 객체는 상태를 유지(Stateful)하게 설계하면 안되고 무상태(Stateless)하게 해야 한다.
 * 이때 무상태란 특정 클라이언트에 의존적인 필드가 있으면 안되고, 값을 변경할 수 있는 필드가 있으면 안되는 것을 의미한다.
 * 즉, 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
 */
public class StatefulService {

    private int price;  // 상태를 유지하는 필드 (공유되는 필드)

    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
//        this.price = price; // 여기서 문제 발생
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
