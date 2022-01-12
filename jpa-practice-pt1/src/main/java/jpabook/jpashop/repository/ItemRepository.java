package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){    //item : 파라미터
        if(item.getId() == null){  //신규 등록
            em.persist(item);   //DB에 저장
        }else{
            em.merge(item);  //similar as update
            //Item merge = em.merge(item);  //merge: 영속성 컨텍스트에 의해 관리되는 객체
        }
    }
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    //여러개 찾는거는, JPQL 사용
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
