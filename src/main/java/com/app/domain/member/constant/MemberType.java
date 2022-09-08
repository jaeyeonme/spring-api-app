package com.app.domain.member.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MemberType {
    KAKAO;

    // String 으로 들어온 값을 받아서 대문자로 바꾼다음, MemberType에서 가지고 있는 ENUM 값이 있는지 확인
    public static MemberType from(String type) {
        return MemberType.valueOf(type.toUpperCase());
    }

    public static boolean isMemberType(String type) {
        List<MemberType> memberTypes = Arrays.stream(MemberType.values())
                .filter(memberType -> memberType.name().equals(type)) // MemberType의 값을 String에 있는 값과 비교해서 해당하는 값을 가져온다.
                .collect(Collectors.toList());
        // 사이즈가 0이 아니면 True, type에 해당하는 memberType이 없으면 0
        return memberTypes.size() != 0;
    }
}
