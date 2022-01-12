package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional   //readOnly = false, 우선권
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);  //영속성 엔티티 꺼내기
        //findItem.change(price,name,stockQuantity); =>추적 용이하도록 설계해야해(setter X)
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
        //itemRepository.save(findItem); => 필요 X, 영속성 상태이기 때문에 자동 commit
        //flush : 영속성 컨텍스트에서 변경 엔티티 감지 후 UPDATE 쿼리 날림
    }

    @Transactional
    public void updateItemV2(UpdateItemDto itemDto){
        Item findItem = itemRepository.findOne(itemDto.getId());

        findItem.setPrice(itemDto.getPrice());
        findItem.setName(itemDto.getName());
        findItem.setStockQuantity(itemDto.getStockQuantity());
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
