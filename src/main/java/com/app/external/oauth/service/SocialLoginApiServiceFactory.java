package com.app.external.oauth.service;

import com.app.domain.member.constant.MemberType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialLoginApiServiceFactory {

    private static Map<String, SocialLoginApiService> socialLoginApiServices;

    // Key: Bean의 이름, Value: 각 소셜 로그인 API Service의 구현체
    public SocialLoginApiServiceFactory(Map<String, SocialLoginApiService> socialLoginApiServices) {
        this.socialLoginApiServices = socialLoginApiServices;
    }

    public static SocialLoginApiService getSocialLoginApiService(MemberType memberType) {
        String socialLoginApiServiceBeanName = "";

        // 어떤 bean을 사용할것인지 이름 지정
        if (MemberType.KAKAO.equals(memberType)) {
            socialLoginApiServiceBeanName = "kakaoLoginApiServiceImpl";
        }
        return socialLoginApiServices.get(socialLoginApiServiceBeanName);
    }
}
