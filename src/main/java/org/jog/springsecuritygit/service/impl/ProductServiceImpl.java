package org.jog.springsecuritygit.service.impl;

import lombok.RequiredArgsConstructor;
import org.jog.springsecuritygit.dto.ProductDTO;
import org.jog.springsecuritygit.exception.ObjectNotFoundException;
import org.jog.springsecuritygit.persistence.entity.Category;
import org.jog.springsecuritygit.persistence.entity.Product;
import org.jog.springsecuritygit.persistence.repository.ProductRepository;
import org.jog.springsecuritygit.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findOneById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product createOne(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        Category category = new Category();
        category.setId(productDTO.getCategoryId());

        product.setCategory(category);
        product.setStatus(Product.ProductStatus.ENABLED);
        return productRepository.save(product);
    }

    @Override
    public Product updateOneById(Long productId, ProductDTO productDTO) {

        Product productFromDb = getProductNotFound(productId);
        productFromDb.setName(productDTO.getName());
        productFromDb.setPrice(productDTO.getPrice());


        Category category = new Category();
        category.setId(productDTO.getCategoryId());
        productFromDb.setCategory(category);
        return productRepository.save(productFromDb);
    }

    @Override
    public Product disableOneById(Long productId) {

        Product productFromDb = getProductNotFound(productId);
        productFromDb.setStatus(Product.ProductStatus.DISABLED);
        return productRepository.save(productFromDb);
    }

    private Product getProductNotFound(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ObjectNotFoundException("Product not found "));
    }
}
