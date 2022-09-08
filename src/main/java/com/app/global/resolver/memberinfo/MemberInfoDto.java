package com.app.global.resolver.memberinfo;

import com.app.domain.member.constant.Role;
import lombok.Builder;
import lombok.Getter;

/**
 * Controller 클래스에 메소드의 파라미터로 token에 있던 정보를 MemberInfoDto로 넣어서 객체로 전달
 */
@Getter @Builder
public class MemberInfoDto {

    private Long memberId;
    private Role role;
}
