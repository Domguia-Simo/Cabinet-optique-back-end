package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.models.Product;

public interface ProductInterface {
    public Long createProduct(Product product);
    public void updateProduct(Product product ,long id);
    public void deleteProduct(Long id);
    public List<Product> getProduct();
    public Boolean saveImage(String fileName ,Long id);
}
