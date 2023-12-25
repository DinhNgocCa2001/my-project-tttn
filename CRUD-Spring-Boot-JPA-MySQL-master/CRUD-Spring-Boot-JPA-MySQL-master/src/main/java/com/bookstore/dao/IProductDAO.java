package com.bookstore.dao;

import com.bookstore.entity.Product;

import java.util.List;

public interface IProductDAO {

    List<Product> getProducts();
    Product getProduct(Long productId);
    Product createProduct(Product product);
    Product updateProduct(Long ProductId, Product product);
    boolean deleteProduct(Long productId);

}
