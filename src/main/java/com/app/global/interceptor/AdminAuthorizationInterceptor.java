package com.app.global.interceptor;

import com.app.domain.member.constant.Role;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AdminAuthorizationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 회원의 Role이 Admin 이라면 True, 아니라면 예외발생
        String authorizationHeader = request.getHeader("Authorization");
        String accessToken = authorizationHeader.split(" ")[1]; // 인증 인터셉터에서 있는지 검사를 했었다. 그래서 따로 검사를 하지 않는다.

        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String role = (String)tokenClaims.get("role");
        if(!Role.ADMIN.equals(Role.valueOf(role))) {
            throw new AuthenticationException(ErrorCode.FORBIDDEN_ADMIN);
        }

        return true;
    }
}
