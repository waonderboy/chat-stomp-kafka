package com.waonderboy.chatstompkafka.dto.security;

import com.waonderboy.chatstompkafka.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder @AllArgsConstructor
public class ChatPrincipal implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private static ChatPrincipal of(String username, String password) {
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return ChatPrincipal.builder()
                .username(username)
                .password(password)
                .authorities(roleTypes.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()))
                .build();
    }

    public static ChatPrincipal from(Member member) {
        return ChatPrincipal.of(
                member.getUsername(),
                member.getPassword()
        );
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }



    public enum RoleType {
        USER("ROLE_USER");

        @Getter
        private final String name;

        RoleType(String name) {
            this.name = name;
        }
    }
}
