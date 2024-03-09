package org.jog.springsecuritygit.service;

import org.jog.springsecuritygit.persistence.entity.security.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByDefaultRole();
}
