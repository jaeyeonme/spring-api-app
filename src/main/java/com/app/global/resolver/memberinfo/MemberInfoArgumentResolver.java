package com.app.global.resolver.memberinfo;

import com.app.domain.member.constant.Role;
import com.app.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class MemberInfoArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenManager tokenManager;

    /**
     * return값이 True이면 resolveArgument이 실행되고, False이면 resolveArgument 로직이 실행 안된다.
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // controller 클래스에 memberInfoAnnotation을 붙이고 memberInfoDto 객체를 전달한다.
        // class type도 memberinfodto가 되고 memberinfoannotation도 붙여 있어야 된다
        boolean hasMemberInfoAnnotation = parameter.hasParameterAnnotation(MemberInfo.class);
        boolean hasMemberInfoDto = MemberInfoDto.class.isAssignableFrom(parameter.getParameterType());
        return hasMemberInfoAnnotation && hasMemberInfoDto;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // token에 있던 정보를 memberInfoDto로 만들어서 반환을 해주면 된다.
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.split(" ")[1];

        Claims tokenClaims = tokenManager.getTokenClaims(token);
        Long memberId = Long.valueOf((Integer)tokenClaims.get("memberId"));
        String role = (String) tokenClaims.get("role");

        return MemberInfoDto.builder()
                .memberId(memberId)
                .role(Role.from(role)) // Role 타입이 String 이므로 from으로 넘긴다.
                .build();
    }
}
