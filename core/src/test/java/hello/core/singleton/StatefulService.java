package hello.core.singleton;

/**
 * 싱글톤 패턴의 문제점
 * 객체를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에
 * 싱글톤 객체는 상태를 유지(Stateful)하게 설계하면 안되고 무상태(Stateless)하게 해야 한다.
 */
public class StatefulService {

    private int price;  // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
