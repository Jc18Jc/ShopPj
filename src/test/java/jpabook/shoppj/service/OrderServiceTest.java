package jpabook.shoppj.service;

import jakarta.persistence.EntityManager;
import jpabook.shoppj.domain.Address;
import jpabook.shoppj.domain.Member;
import jpabook.shoppj.domain.Order;
import jpabook.shoppj.domain.OrderStatus;
import jpabook.shoppj.domain.item.Book;
import jpabook.shoppj.exception.NotEnoughStockException;
import jpabook.shoppj.repository.MemberRepository;
import jpabook.shoppj.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 상품주문() throws Exception {
        Member member = createMember("멤버1");

        Book book = createBook("북1", 10000, 10);

        int orderCount=2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order order = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, order.getStatus());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(10000*orderCount, order.getTotalPrice());
        assertEquals(8, book.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        Member member = createMember("멤버1");
        Book book = createBook("북1", 10000, 2);

        int orderCount=3;

        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(), orderCount));
    }

    @Test
    public void 주문취소() throws Exception {
        Member member = createMember("멤버1");
        Book book = createBook("북1", 10000, 10);

        int orderCount=3;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order order = orderRepository.findOne(orderId);

        assertEquals(7, book.getStockQuantity());
        assertEquals(OrderStatus.ORDER, order.getStatus());

        orderService.cancelOrder(orderId);

        assertEquals(10, book.getStockQuantity());
        assertEquals(OrderStatus.CANCEL, order.getStatus());
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}