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
import java.util.Random;

@Service
public class ProductService implements IProductService {

	private boolean defaultStatus = true;
	@Autowired
	private IProductDAO dao;

	private ProductRepo repo;

	@Autowired
	private JwtService jwtService;

	public ProductService(ProductRepo repository) {
		this.repo = repository;
	}

	@Override
	public List<Product> getProducts() {
		return dao.getProducts();
	}

	public static Long generateUniqueId() {
		Random random = new Random();
		long min = 10000L;
		long max = 99999L;
		return min + ((long) (random.nextDouble() * (max - min)));
	}

	@Override
	public Product createProduct(Product product) {
		product.setId(generateUniqueId());
		return repo.save(product);
	}

	@Override
	public Product updateProduct(Long productId, Product product) {
		return dao.updateProduct(productId, product);
	}

	@Override
	public Product getProduct(Long productId) {
		return dao.getProduct(productId);
	}

	@Override
	public boolean deleteProduct(Long productId) {
		return dao.deleteProduct(productId);
	}

	@Override
	public List<Product> getPasring(Pageable pageable) {
		return repo.findByStatus( defaultStatus, pageable);
	}

	@Override
	public Page<Map<String, Object>> searchParsing( String token, String search, Pageable pageable){
//		String userId = null;
//		userId = jwtService.getUserNameJwt(token);
		return repo.search(defaultStatus, search, pageable);
	}


}
