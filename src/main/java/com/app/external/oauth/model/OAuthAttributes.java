package com.app.external.oauth.model;

import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 소셜 플랫폼에서 회원 정보를 가져올건데 반환 정보가 다른데, 하나로 통일을 해서 회원 가입을 할때 OAuthAttributes에 있는 회원 정보들을 이용해서 가입한다.
 */
@ToString
@Getter @Builder
public class OAuthAttributes {

    private String name;
    private String email;
    private String profile;
    private MemberType memberType;

    public Member toMemberEntity(MemberType memberType, Role role) {
        return Member.builder()
                .memberName(name)
                .email(email)
                .memberType(memberType)
                .profile(profile)
                .role(role)
                .build();
    }

}
