package jpabook.shoppj.domain.item;

import jakarta.persistence.*;
import jpabook.shoppj.domain.Category;
import jpabook.shoppj.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // == 비즈니스 로직 = //
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void minusStock(int quantity) {
        int resStock = this.stockQuantity - quantity;

        if (resStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = resStock;
    }
}
