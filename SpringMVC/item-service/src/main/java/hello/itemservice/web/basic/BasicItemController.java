package hello.itemservice.web.basic;

import ch.qos.logback.core.net.SyslogOutputStream;
import hello.itemservice.domain.dto.ItemUpdateParamDto;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
@Slf4j
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam Integer price,
                       @RequestParam Integer quantity,
                       Model model){
        Item item = new Item(itemName,price,quantity);
        itemRepository.save(item);
        model.addAttribute("item",item);

        return "basic/item";
    }
    /*
    @ModelAttribute의 name 속성을 설정하면,
    해당 이름으로 Model에 넣음
     */
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model){

        itemRepository.save(item);
        //model.addAttribute("item",item);              // 자동으로 추가되므로, Model 객체 생략 가능
        return "basic/item";
    }

    /*
    @ModelAttribute의 name 속성을 생략 -> 클래스 이름의 첫 글자를 소문자로 치환해서 Model에 넣음
     */
    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    /*
    @ModelAttribute 생략 -> 클래스 이름의 첫 글자를 소문자로 치환해서 Model에 넣음
     */
    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, ItemUpdateParamDto itemUpdateParamDto){

        log.info("itemId = {}",itemId);
        log.info("itemName = {}",itemUpdateParamDto.getItemName());
        log.info("itemPrice = {}",itemUpdateParamDto.getItemPrice());
        log.info("itemQuantity = {}",itemUpdateParamDto.getItemQuantity());


        itemRepository.update(itemId,itemUpdateParamDto);
        return "redirect:/basic/items/{itemId}";

    }
    /*
    Test 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }
}
