package com.bookstore.services;

import com.bookstore.entity.Book;
import com.bookstore.repo.CurrencyRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@Component
//@Log4j2
public class CurrencyService {
    
    private final CurrencyRepo repo;

    public CurrencyService(CurrencyRepo repository) {
//        super(repository);
        this.repo = repository;
    }
    public Book getAllTest(Integer id) throws Exception {
        Book currency = repo.findById(id).orElseThrow(() -> new Exception("currency-not-found"));
        return currency;
    }

    public List<Book> search(int price, Pageable pageable) {
        return repo.findAllByPrice(price, pageable);
    }

    public Page<Map<String, Object>> esearch(String search, Pageable pageable) {
        String esearch = search;
        return repo.search(esearch.toLowerCase(), pageable);
    }


//    public Book update(Book request) throws Exception{
//        Optional<Book> result = repo.findById(request.getId());
//        result = repo.()
//        return result;
//    }
}