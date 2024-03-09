package org.jog.springsecuritygit.persistence.repository.security;

import org.jog.springsecuritygit.persistence.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String defaultRole);
}
