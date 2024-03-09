package org.jog.springsecuritygit.service;

import org.jog.springsecuritygit.dto.PermissionDTO;
import org.jog.springsecuritygit.dto.ShowPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PermissionService {

    Page<ShowPermission> findAll(Pageable pageable);

    Optional<ShowPermission> findById(Long permissionId);

    ShowPermission createOne(PermissionDTO permissionDTO);

    ShowPermission deleteOneById(Long permissionId);
}
