package hello.core.order;

public class Order {

    private Long memberId;      // 회원 아이디
    private String itemName;    // 상품명
    private int itemPrice;      // 상품 가격
    private int itemDiscountPrice;  // 상품 할인금액

    public Order(Long memberId, String itemName, int itemPrice, int itemDiscountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDiscountPrice = itemDiscountPrice;
    }

    // 할인된 가격 리턴
    public int calculatePrice() {
        return itemPrice - itemDiscountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemDiscountPrice() {
        return itemDiscountPrice;
    }

    public void setItemDiscountPrice(int itemDiscountPrice) {
        this.itemDiscountPrice = itemDiscountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemDiscountPrice=" + itemDiscountPrice +
                '}';
    }
}

