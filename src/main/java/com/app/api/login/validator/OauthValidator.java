package com.app.api.login.validator;

import com.app.domain.member.constant.MemberType;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import org.springframework.context.annotation.Configuration;

/**
 * 필수값 체크 (사용자가 인증입력을 안하고 보낼 수 있기 떄문에)
 */
@Configuration
public class OauthValidator {

    // memberType: 네이버 or 카카오 정보 들어가있다. 우리가 지정한 소셜로그인 정보가 아닌 다른 임의의 STRING을 보낼 수 있기 때문에 검증을 해야한다.
    public void validateMemberType(String memberType) {
        if (!MemberType.isMemberType(memberType)) {
            throw new BusinessException(ErrorCode.INVALID_MEMBER_TYPE);
        }
    }
}
