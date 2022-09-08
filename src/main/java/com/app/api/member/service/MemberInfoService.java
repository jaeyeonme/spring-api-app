package com.app.api.member.service;

import com.app.api.member.dto.MemberInfoResponseDto;
import com.app.domain.member.entity.Member;
import com.app.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Transactional을 모아두는 역할
 */
@Service
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberService memberService;

    @Transactional(readOnly = true)
    public MemberInfoResponseDto getMemberInfo(Long memberId) {
        Member member = memberService.findMemberByMemberId(memberId);
        return MemberInfoResponseDto.of(member);
    }
}
