package com.bookstore.api;

//import com.bookstore.model.Currency;

import com.bookstore.entity.DTO.Response;
import com.bookstore.entity.Product;
import com.bookstore.entity.User;
import com.bookstore.repo.ProductRepo;
import com.bookstore.services.JwtService;
import com.bookstore.services.ProductService;
import com.bookstore.services.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserApi {

    private ProductService productService;
    private UserService userService;
    private ProductRepo repo;

    @Autowired
    private JwtService jwtService;

    public UserApi(ProductService productService, UserService userService, ProductRepo repo) {
        this.productService = productService;
        this.userService = userService;
        this.repo = repo;
    }

    @GetMapping("get-by-id/{id}")
    protected Product getByCid(
            @PathVariable("id") Long id
    ) throws Exception {
        Product result = productService.getProduct(id);
        return result;
    }

    @GetMapping("get-all")
    protected List<Product> getAll(Pageable pageable) throws Exception {
//        List<Product> result = productService.getPasring(pageable);
        return productService.getPasring(pageable);
    }

    @PutMapping("update/{id}")
    protected ResponseEntity<Product> updateByid(
            @PathVariable("id") Long id,
            @RequestBody Product book
    ) throws Exception {
        Product result = productService.updateProduct(id, book);
        return new ResponseEntity<Product>(result, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    protected ResponseEntity<Product> delete(
            @PathVariable("id") Long id,
            @RequestBody Product book
    ) throws Exception {
        Product result = productService.updateProduct(id, book);
        return new ResponseEntity<Product>(result, HttpStatus.OK);
    }

    @PostMapping("create")
    protected ResponseEntity<User> create(
            @RequestBody User user
    ) throws Exception {
        User result = userService.createUser(user);
        return new ResponseEntity<User>(result, HttpStatus.OK);
    }

    @PostMapping("login")
    protected ResponseEntity<?> login(
            @RequestBody User user
    ) {
        try {
            String token = null;
            token = userService.LoginUser(user);
//            return new ResponseEntity<?>(result, HttpStatus.OK);
            if(Objects.nonNull(token)){
                Response response = new Response("00", null, true, token);
                return ResponseEntity.ok(response);
            } else {
                Response response = new Response("00", "Không có tài khoản: " + user.getEmail() , true, token);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception ex) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }

    @GetMapping("search")
    protected ResponseEntity<?> searchParsing(
            @RequestParam(value = "search", defaultValue = "#") String search
            , @RequestHeader("Authorization") String bearerToken
            , Pageable pageable) {
        String userId = null;
        String token = null;
        try {
            token = jwtService.validateToken(bearerToken);
            userId = jwtService.getUserNameJwt(token);
        } catch (Exception ex) {
            //Sử dụng Constants.ResponseCode.EXPIRED_JWT hoặc Constants.ResponseCode.INVALID_JWT
//                Response response = new Response(Constants.ResponseCode.EXPIRED_JWT, null, true, ex.toString());
            Response response = new Response("00", "Chưa đăng nhập" , true, null);
            return ResponseEntity.ok(response);
        }
        var result =  productService.searchParsing(token, search, pageable);
        Response response = new Response("00", "Chưa đăng nhập" , true, result);
        return ResponseEntity.ok(response);
    }
}
