package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    /*
    @PersistenceContext    //JPA 엔티티 매니저 자동 주입
    //@Autowired 로 해도 가능(spring boot + spring data jpa 에서 지원)
    private EntityManager em;

    public MemberRepository(EntityManager em){
        this.em = em;
    }
    */

    public void save(Member member){   //ID 저장 보장
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    //JPQL : 테이블이 아니라, 엔티티 객체를 대상으로 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("Select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
