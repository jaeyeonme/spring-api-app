package com.app.api.logout.service;

import com.app.domain.member.entity.Member;
import com.app.domain.service.MemberService;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.jwt.constant.TokenType;
import com.app.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class LogoutService {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    public void logout(String accessToken) {

        // 1. 토큰 검증
        tokenManager.validateToken(accessToken);

        // 2. 토큰 타입 검증
        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String tokenType = tokenClaims.getSubject();
        if (!TokenType.isAccessToken(tokenType)) {
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);
        }

        // 3. refresh token 만료 처리
        Long memberId = Long.valueOf((Integer)tokenClaims.get("memberId"));
        Member member = memberService.findMemberByMemberId(memberId); // 해당 회원을 찾아온다.
        // JPA에서는 변경감지 기능이 동작한다. member의 상태를 변경하면 Transaction이 끝나고 나서 회원의 상태를 업데이트는 쿼리문이 실행된다.
        member.expireRefreshToken(LocalDateTime.now());              // 찾아온 회원쪽에 refreshToken의 만료 시간을 업데이트 해준다.
    }
}
