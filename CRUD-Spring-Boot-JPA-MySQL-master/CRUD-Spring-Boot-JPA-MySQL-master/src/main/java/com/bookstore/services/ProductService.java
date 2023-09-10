package com.bookstore.services;

import com.bookstore.dao.IProductDAO;
import com.bookstore.entity.Product;
import com.bookstore.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {

	private boolean defaultStatus = true;
	@Autowired
	private IProductDAO dao;

	private ProductRepo repo;

	public ProductService(ProductRepo repository) {
		this.repo = repository;
	}

	@Override
	public List<Product> getProducts() {
		return dao.getProducts();
	}

	@Override
	public Product createProduct(Product product) {
		return dao.createProduct(product);
	}

	@Override
	public Product updateProduct(int productId, Product product) {
		return dao.updateProduct(productId, product);
	}

	@Override
	public Product getProduct(int productId) {
		return dao.getProduct(productId);
	}

	@Override
	public boolean deleteProduct(int productId) {
		return dao.deleteProduct(productId);
	}

	@Override
	public List<Product> getPasring( Pageable pageable) {
		return repo.findByStatus( defaultStatus, pageable);
	}

	@Override
	public Page<Map<String, Object>> searchParsing( String search, Pageable pageable){
		return repo.search(defaultStatus, search, pageable);
	}


}
