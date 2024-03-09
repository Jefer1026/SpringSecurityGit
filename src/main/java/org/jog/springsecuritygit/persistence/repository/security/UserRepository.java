package org.jog.springsecuritygit.persistence.repository.security;

import org.jog.springsecuritygit.persistence.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findByUsername(String username);
}
