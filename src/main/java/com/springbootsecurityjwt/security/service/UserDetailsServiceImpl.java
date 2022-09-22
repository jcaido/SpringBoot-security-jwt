package com.springbootsecurityjwt.security.service;

import com.springbootsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.springbootsecurityjwt.entity.User user = userRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario con el nombre: " + username));


        return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getPassword(), new ArrayList<>());
    }
}
