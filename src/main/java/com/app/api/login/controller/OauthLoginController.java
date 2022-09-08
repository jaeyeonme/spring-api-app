package com.app.api.login.controller;

import com.app.api.login.dto.OauthLoginDto;
import com.app.api.login.service.OauthLoginService;
import com.app.api.login.validator.OauthValidator;
import com.app.domain.member.constant.MemberType;
import com.app.global.util.AuthorizationHeaderUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "authentication", description = "로그인/로그아웃/토큰재발급 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OauthLoginController {

    private final OauthValidator oauthValidator;
    private final OauthLoginService oauthLoginService;

    @Tag(name = "authentication")
    @Operation(summary = "소셜 로그인 API", description = "소셜 로그인 API")
    @PostMapping("/login")
    public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request oauthLoginRequestDto,
                                                             HttpServletRequest httpServletRequest) {

        // 인증 정보
        // 파라미터에는 헤더에 대한 정보가 있다. 클라이언트가 accesstoken을 담아서 보내줄건데, header에 있는 토큰 정보를 이용해서
        // 카카오에서 회원 정보를 가져온다.
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        // authorizationHeader를 사용자가 입력을 안하고 보낼 수 있기 때문에 필수값 체크를 위한 검증
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);
        oauthValidator.validateMemberType(oauthLoginRequestDto.getMemberType()); // 한번 더 검증을 한다.

        String accessToken = authorizationHeader.split(" ")[1];
        OauthLoginDto.Response jwtTokenResponseDto = oauthLoginService
                .oauthLogin(accessToken, MemberType.from(oauthLoginRequestDto.getMemberType()));
        return ResponseEntity.ok(jwtTokenResponseDto);
    }
}
