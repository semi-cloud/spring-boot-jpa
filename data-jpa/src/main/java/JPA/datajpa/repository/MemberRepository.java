package JPA.datajpa.repository;

import JPA.datajpa.dto.MemberDto;
import JPA.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;

//인터페이스끼리 상속
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

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

    //페이징
    //토탈 카운트 구하는 쿼리는 조인할 필요가 없음 => 복잡해지면 쿼리를 분리
    //@Query(value = "select m from Member m left join m.team t", countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    //벌크성 수정 쿼리
    @Modifying           //== executeUpdate()
    @Query("update Member m set m.age = m.age+1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    //페치 조인
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    //select for update
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

    //네이티브 쿼리(권장 X)
    @Query(value = "select * from Member m where username = ?", nativeQuery = true)
    Member findByNativeQuery(String username);

    //프로젝션 + 네이티브 쿼리
    @Query(value = "select m.member_id as id, m.username, t.name as teamName" +
                    "from member m left join team t",
                    countQuery = "select count(*) from member",
                    nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);
}
