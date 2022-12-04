package com.waonderboy.chatstompkafka.service;


import com.waonderboy.chatstompkafka.domain.Member;
import com.waonderboy.chatstompkafka.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void register(Member member) {
        memberRepository.save(member);
    }

    public Member getMember(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public boolean checkMember(Member memberForm) {
        Member findMember = memberRepository.findByUsername(memberForm.getUsername())
                .orElseThrow(() -> new EntityNotFoundException());

        return memberForm.getPassword().equals(findMember.getPassword());
    }
}
