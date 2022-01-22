package JPA.querydsl.repository;

import JPA.querydsl.dto.MemberSearchCondition;
import JPA.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
}
