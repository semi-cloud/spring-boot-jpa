package JPA.datajpa.repository;

import JPA.datajpa.dto.MemberDto;
import JPA.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

//인터페이스끼리 상속
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    //named Query, @Query 생략 가능
    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    //이름 없는 named Query
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    //값 조회
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //Dto 조회
    @Query("select new JPA.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    //컬렉션 파라미터 바인딩
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);







}
