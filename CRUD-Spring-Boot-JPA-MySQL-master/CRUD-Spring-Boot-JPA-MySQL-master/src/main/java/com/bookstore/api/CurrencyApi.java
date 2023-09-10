package com.bookstore.api;

//import com.bookstore.model.Currency;

import com.bookstore.entity.Book;
import com.bookstore.services.BookStoreService;
import com.bookstore.services.CurrencyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("currency")
public class CurrencyApi {

    private final CurrencyService service;

    private BookStoreService bookService;

    public CurrencyApi(CurrencyService service, BookStoreService bookService) {
//        super(service);
        this.service = service;
        this.bookService = bookService;
    }

    @GetMapping("get-by-id/{id}")
    protected Book getByCid(
            @PathVariable("id") Integer id
            ) throws Exception {
            Book result = service.getAllTest(id);
            return result;
    }

    @PutMapping("update/{id}")
    protected ResponseEntity<Book> updateByCid(
            @PathVariable("id") Integer id,
            @RequestBody Book book
    ) throws Exception {
        Book result = bookService.updateBook(id,book);
        //return bookService.updateBook(id,book);
        return new ResponseEntity<Book>(result, HttpStatus.OK);
    }

    @GetMapping("search")
    protected Page<Map<String, Object>> search(
            @RequestParam(value = "search", defaultValue = "#") String search
            , Pageable pageable) {
            return service.esearch(search, pageable);
    }
}
