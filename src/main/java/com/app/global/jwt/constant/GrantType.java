package com.app.global.jwt.constant;

import lombok.Getter;

/**
 * JWT 또는 Oauth토큰을 사용한다는 의미
 */
@Getter
public enum GrantType {

    BEARER("Bearer");

    GrantType(String type) {
        this.type = type;
    }

    private String type;
}
