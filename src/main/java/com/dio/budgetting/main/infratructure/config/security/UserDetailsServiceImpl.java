package com.dio.budgetting.main.infratructure.config.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dio.budgetting.main.infratructure.persistence.entity.UserEntity;
import com.dio.budgetting.main.infratructure.persistence.repository.UserEntityRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityRepository usuarioRepositoryJpa;

    public UserDetailsServiceImpl(UserEntityRepository usuarioRepositoryJpa) {
        this.usuarioRepositoryJpa = usuarioRepositoryJpa;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity usuarioEntidade = usuarioRepositoryJpa.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        Collection<? extends GrantedAuthority> authorities = usuarioEntidade.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());

        return User.builder()
                .username(usuarioEntidade.getUsername())
                .password(usuarioEntidade.getPassword())
                .authorities(authorities)
                .build();
    }
}