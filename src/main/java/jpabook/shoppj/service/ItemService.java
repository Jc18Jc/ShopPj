package jpabook.shoppj.service;

import jpabook.shoppj.domain.item.Book;
import jpabook.shoppj.domain.item.Item;
import jpabook.shoppj.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Transactional
    // 파라미터는 이렇게 해도 되고, DTO 하나 만들면 좋음
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {

        Item findItem = itemRepository.findOne(itemId);
        // 실제로는 이렇게 하지 말고, 빌더 패턴으로 change와 같은 메소드 만들어서 변경해주기
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }
}
