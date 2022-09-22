package com.springbootsecurityjwt.repository;

import com.springbootsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsuario(String usuario);

    Optional<User> findByUsuario(String usuario);
}
