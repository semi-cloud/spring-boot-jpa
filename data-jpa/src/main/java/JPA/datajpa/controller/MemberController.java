package JPA.datajpa.controller;

import JPA.datajpa.dto.MemberDto;
import JPA.datajpa.entity.Member;
import JPA.datajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    //spring data jpa 가 자동으로 조회 후 인젝션
    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member){
        return member.getUsername();
    }

    //http:localhost:8080/members?page=0&size=3..
    //pageRequest 생성해서 인젝션
    @GetMapping("/members")
    public Page<Member> list(@PageableDefault(size = 5, sort = "username") Pageable pageable){
        Page<Member> page = memberRepository.findAll(pageable);

        //엔티티 -> Dto 로 변환 필수
        page.map(MemberDto::new);  //member -> new MemberDto(member)
        return page;
    }

    @PostConstruct
    public void init(){
        for(int i = 0; i < 100 ; i++){
            memberRepository.save(new Member("user" + i, i));
        }
    }

}
