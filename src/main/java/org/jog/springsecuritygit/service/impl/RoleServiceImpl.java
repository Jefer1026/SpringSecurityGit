package org.jog.springsecuritygit.service.impl;

import lombok.RequiredArgsConstructor;
import org.jog.springsecuritygit.persistence.entity.security.Role;
import org.jog.springsecuritygit.persistence.repository.security.RoleRepository;
import org.jog.springsecuritygit.service.RoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Value("CUSTOMER")
    private String defaultRole;

    @Override
    public Optional<Role> findByDefaultRole() {
        return roleRepository.findByName(defaultRole);
    }
}
