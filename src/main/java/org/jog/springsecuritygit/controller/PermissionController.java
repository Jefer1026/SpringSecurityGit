package org.jog.springsecuritygit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jog.springsecuritygit.dto.PermissionDTO;
import org.jog.springsecuritygit.dto.ShowPermission;
import org.jog.springsecuritygit.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;


    @GetMapping
    public ResponseEntity<Page<ShowPermission>> findAll(Pageable pageable) {

        Page<ShowPermission> permissionPage = permissionService.findAll(pageable);

        return permissionPage.hasContent()
                ? ResponseEntity.ok(permissionPage)
                : ResponseEntity.notFound().build();

    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<ShowPermission> findOneById(@PathVariable Long permissionId) {

        return permissionService.findById(permissionId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShowPermission> createOne(@RequestBody @Valid PermissionDTO permissionDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.createOne(permissionDTO));
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<ShowPermission> deleteById(@PathVariable Long permissionId) {
        return ResponseEntity.ok(permissionService.deleteOneById(permissionId));
    }

}
