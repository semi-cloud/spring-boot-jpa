package JPA.querydsl.repository;

import JPA.querydsl.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    //정적 쿼리
    List<Member> findByUsername(String username);
}
