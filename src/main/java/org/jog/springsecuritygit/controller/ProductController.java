package org.jog.springsecuritygit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jog.springsecuritygit.dto.ProductDTO;
import org.jog.springsecuritygit.persistence.entity.Product;
import org.jog.springsecuritygit.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
        Page<Product> productPage = productService.findAll(pageable);

        return productPage.hasContent()
                ? ResponseEntity.ok(productPage)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findOneById(@PathVariable Long productId) {
        return productService.findOneById(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createOne(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createOne(productDTO));

    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateOneById(@PathVariable Long productId,
                                                 @RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateOneById(productId, productDTO));
    }

    @PutMapping("/{productId}/disabled")
    public ResponseEntity<Product> disableOneById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.disableOneById(productId));
    }
}
