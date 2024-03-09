package org.jog.springsecuritygit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jog.springsecuritygit.dto.CategoryDTO;
import org.jog.springsecuritygit.persistence.entity.Category;
import org.jog.springsecuritygit.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoryService.findAll(pageable);

        return categoryPage.hasContent()
                ? ResponseEntity.ok(categoryPage)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findOneById(@PathVariable Long categoryId) {
        return categoryService.findOneById(categoryId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> createOne(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createOne(categoryDTO));

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateOneById(@PathVariable Long categoryId,
                                                  @RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateOneById(categoryId, categoryDTO));
    }

    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<Category> disableOneById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.disableOneById(categoryId));
    }


}
