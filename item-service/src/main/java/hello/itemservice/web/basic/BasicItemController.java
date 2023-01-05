package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor    // final로 선언된 변수의 생성자를 자동으로 만들어줌
public class BasicItemController {

    private final ItemRepository itemRepository;

    // 상품 목록 조회 컨트롤러
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    // 상품 상세 조회 컨트롤러
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // 상품 등록 폼을 보여주는 컨트롤러
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /**
     * @RequestParam 어노테이션을 이용하여 요청 파라미터 데이터를 해당 변수에 넘겨 받는다.
     * Item 객체를 생성하고 itemRepository에 저장함
     * 저장된 item을 모델에 담아서 뷰에 전달한다.
     */
    //    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * @ModelAttribute -> 자동으로 Item 객체를 생성해주고, 요청 파라미터의 값을 프로퍼티 접근법(setXxx)으로 입력해준다.
     * 모델에 객체를 자동으로 넣어줌
     */
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동 추가되므로 생략 가능
        return "basic/item";
    }

    /**
     * @ModelAttribute name 생략 가능
     * 생략시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
     */
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * @ModelAttribute 자체 생략 가능
     * model.addAttribute(item) 자동 추가
     */
//    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * addItemV1 ~ addItemV4는 리턴값이 모두 "basic/item" 뷰 템플릿을 재활용하고 있다.
     * 이는 상품이 중복 등록되는 문제가 발생하는 원인이다.
     * 이 부분을 해결하기 위해서는 상품 등록 후, 상품 상세 화면으로 리다이렉트를 호출해주면 된다.
     * 새로고침은 즉 가장 마지막에 서버에 저장한 데이터를 다시 전송하는 행위이다.
     * 그러므로 마지막에 호출한 상품 상세 화면인 GET/items{id}가 되는 것이다.
     *
     * PRG: Post/Redirect/Get
     */
//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

//    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item items = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", items.getId());
        return "redirect:/basic/items/{itemId}";
    }

    /**
     *
     * 상품 등록 완료시 저장 완료 메시지가 뜨게 함
     */
    @PostMapping("/add")
    public String addItemV7(Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status_1", true);
        return "redirect:/basic/items/{itemId}";
    }

    // 상품 등록 폼을 보여주는 컨트롤러
    @GetMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    // 상품 수정
//    @PostMapping("{itemId}/edit")
    public String editV1(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 상품 수정 처리시 수정 완료 메시지가 뜨게 함
     */
    @PostMapping("{itemId}/edit")
    public String editV2(@PathVariable Long itemId, @ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        itemRepository.update(itemId, item);
        redirectAttributes.addAttribute("itemId", itemId);
        redirectAttributes.addAttribute("status_2", true);
        return "redirect:/basic/items/{itemId}";
    }

//    @PostMapping("{itemId}/edit")
    public String editV3(@PathVariable Long itemId, Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
<<<<<<< HEAD
        itemRepository.save(new Item("ItemA", 10000, 10, "작성자A"));
        itemRepository.save(new Item("ItemB", 20000, 20, "작성자A"));
=======
        itemRepository.save(new Item("ItemA", 10000, 10));
        itemRepository.save(new Item("ItemB", 20000, 20));
>>>>>>> 95d936f996c95c4afca81b1fa7f7df871d1433bf
    }
}
