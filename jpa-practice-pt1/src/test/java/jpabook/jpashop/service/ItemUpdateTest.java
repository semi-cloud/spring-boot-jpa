package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;
    @Test
    public void updateTest() throws Exception{
        Book book = em.find(Book.class, 11);

        //Tx
        book.setName("asdfasd");

        //변경 감지 == dirty checking
        //JPA는, 이미 식별자가 존재하는 애는 영속 엔티티로 관리 X => 자동 변경 감지 후 저장이 안일어남
        //Tx Commit

    }
}
