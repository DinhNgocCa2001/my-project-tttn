package com.bookstore.api;

//import com.bookstore.model.Currency;

import com.bookstore.entity.Product;
import com.bookstore.repo.ProductRepo;
import com.bookstore.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("product")
public class ProductApi {

    private ProductService productService;
    private ProductRepo repo;

    public ProductApi(ProductService productService, ProductRepo repo) {

        this.productService = productService;
        this.repo = repo;
    }


    @GetMapping("get-by-id/{id}")
    protected Product getByCid(
            @PathVariable("id") Integer id
    ) throws Exception {
        Product result = productService.getProduct(id);
        return result;
    }

    @GetMapping("get-all")
    protected List<Product> getAll( Pageable pageable) throws Exception {
//        List<Product> result = productService.getPasring(pageable);
        return productService.getPasring(pageable);
    }

    @PutMapping("update/{id}")
    protected ResponseEntity<Product> updateByid(
            @PathVariable("id") Integer id,
            @RequestBody Product book
    ) throws Exception {
        Product result = productService.updateProduct(id,book);
        return new ResponseEntity<Product>(result, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    protected ResponseEntity<Product> delete(
            @PathVariable("id") Integer id,
            @RequestBody Product book
    ) throws Exception {
        Product result = productService.updateProduct(id,book);
        return new ResponseEntity<Product>(result, HttpStatus.OK);
    }

    @PostMapping("create")
    protected ResponseEntity<Product> create(
            @RequestBody Product book
    ) throws Exception {
        Product result = productService.createProduct(book);
        return new ResponseEntity<Product>(result, HttpStatus.OK);
    }

//    @GetMapping("count")
//    protected Long countItem(
//    ) throws Exception {
//        Long result = repo.count();
//        return result;
//    }

    @GetMapping("search")
    protected Page<Map<String, Object>> searchParsing(
            @RequestParam(value = "search", defaultValue = "#") String search
            , Pageable pageable) {
        return productService.searchParsing(search, pageable);
    }
}
