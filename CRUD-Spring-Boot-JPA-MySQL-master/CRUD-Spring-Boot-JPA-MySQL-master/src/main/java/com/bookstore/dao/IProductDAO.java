package com.bookstore.dao;

import com.bookstore.entity.Product;

import java.util.List;

public interface IProductDAO {

    List<Product> getProducts();
    Product getProduct(int productId);
    Product createProduct(Product product);
    Product updateProduct(int ProductId,Product product);
    boolean deleteProduct(int productId);

}
