package com.app.api.token.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * AccessToken을 발급받아서 반환해줘야 하기 때문에 DTO를 생성
 */
@Getter @Builder
public class AccessTokenResponseDto {

    @Schema(description = "grantType", example = "Bearer", required = true)
    private String grantType;

    @Schema(description = "accessToken", example = "access token", required = true)
    private String accessToken;

    @Schema(description = "access token 만료 시간", example = "2022-xx-xx xx:xx:xx", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date accessTokenExpireTime;
}
