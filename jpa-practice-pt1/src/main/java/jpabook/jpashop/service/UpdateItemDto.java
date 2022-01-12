package jpabook.jpashop.service;

import jpabook.jpashop.controller.BookForm;
import lombok.Getter;
import lombok.Setter;

//DTO 에는 GETTER, SETTER 남발 가능
@Getter
@Setter
public class UpdateItemDto {
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    public UpdateItemDto(BookForm bookForm) {
        this.name = bookForm.getName();
        this.price = bookForm.getPrice();
        this.stockQuantity = bookForm.getStockQuantity();
    }
}
