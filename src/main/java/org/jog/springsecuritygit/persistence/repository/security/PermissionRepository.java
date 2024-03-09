package org.jog.springsecuritygit.persistence.repository.security;

import org.jog.springsecuritygit.persistence.entity.security.GrantedPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<GrantedPermission, Long> {
}
