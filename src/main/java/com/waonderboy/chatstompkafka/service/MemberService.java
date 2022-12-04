package com.waonderboy.chatstompkafka.service;


import com.waonderboy.chatstompkafka.domain.Member;
import com.waonderboy.chatstompkafka.dto.security.ChatPrincipal;
import com.waonderboy.chatstompkafka.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void register(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        Member savedMember = memberRepository.save(member);

        loginAfterRegister(savedMember);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .map(ChatPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다. - username" + username));
    }

    private void loginAfterRegister(Member savedMember) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                ChatPrincipal.from(savedMember),
                savedMember.getPassword(),
                Set.of(new SimpleGrantedAuthority("ROLE_USER")));

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(token);
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