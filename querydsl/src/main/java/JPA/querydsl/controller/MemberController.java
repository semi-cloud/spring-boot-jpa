package JPA.querydsl.controller;

import JPA.querydsl.dto.MemberSearchCondition;
import JPA.querydsl.dto.MemberTeamDto;
import JPA.querydsl.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition)
    {
        return memberJpaRepository.search(condition);
    }

}
