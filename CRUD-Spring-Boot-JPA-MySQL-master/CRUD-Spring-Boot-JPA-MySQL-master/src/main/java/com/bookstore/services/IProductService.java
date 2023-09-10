package com.bookstore.services;

import com.bookstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IProductService {
	
	List<Product> getProducts();
	Product createProduct(Product product);
	Product updateProduct(int productId, Product product);
	Product getProduct(int productId);
	boolean deleteProduct(int productId);

	List<Product> getPasring(Pageable pageable);

	Page<Map<String, Object>> searchParsing(String search, Pageable pageable);


}
