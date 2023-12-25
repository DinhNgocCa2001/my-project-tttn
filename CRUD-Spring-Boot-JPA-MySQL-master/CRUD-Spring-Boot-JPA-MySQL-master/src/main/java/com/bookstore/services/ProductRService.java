package com.bookstore.services;

import com.bookstore.entity.Product;
import com.bookstore.repo.ProductRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Component
//@Log4j2
public class ProductRService {

    private final ProductRepo repo;

    public ProductRService(ProductRepo repository) {
        this.repo = repository;
    }
    public Product getById(Long id) throws Exception {
        Product currency = repo.findById(id).orElseThrow(() -> new Exception("currency-not-found"));
        return currency;
    }

//    public List<Book> search(int price, Pageable pageable) {
//        return repo.findAllByPrice(price, pageable);
//    }
}