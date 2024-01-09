package com.nicolasortiz.ecommerce.service.impl;

import com.nicolasortiz.ecommerce.exception.MyNotFoundException;
import com.nicolasortiz.ecommerce.model.entity.Product;
import com.nicolasortiz.ecommerce.repository.IProductRepository;
import com.nicolasortiz.ecommerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new MyNotFoundException("Producto no encontrado")));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(int id, Product product) {
        Product productFound = findById(id)
                .orElseThrow(()-> new MyNotFoundException("Producto no encontrado"));
        product.setProductId(id);
        return productRepository.save(product);
    }

    @Override
    public Product deleteById(int id) {
        Product productFound = findById(id)
                .orElseThrow(()-> new MyNotFoundException("Producto no encontrado"));
        productRepository.deleteById(id);
        return productFound;
    }
}
