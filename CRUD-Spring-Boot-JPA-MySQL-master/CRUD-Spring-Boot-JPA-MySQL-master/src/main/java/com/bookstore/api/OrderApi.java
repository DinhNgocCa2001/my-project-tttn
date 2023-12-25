package com.bookstore.api;

//import com.bookstore.model.Currency;

import com.bookstore.entity.Color;
import com.bookstore.entity.DTO.ColorItem;
import com.bookstore.entity.DTO.Response;
import com.bookstore.entity.DTO.SizeItem;
import com.bookstore.entity.Order;
import com.bookstore.entity.Product;
import com.bookstore.entity.Size;
import com.bookstore.repo.ColorRepo;
import com.bookstore.repo.ProductRepo;
import com.bookstore.repo.SizeRepo;
import com.bookstore.services.JwtService;
import com.bookstore.services.OrderService;
import com.bookstore.services.ProductService;
import com.bookstore.services.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("cart")
public class OrderApi {

    private ProductService productService;

    private OrderService orderService;

    private ProductVariantService productVariantService;
    private ProductRepo repo;
    private SizeRepo sizeRepo;
    private ColorRepo colorRepo;

    @Autowired
    private JwtService jwtService;


    public OrderApi(OrderService orderService,ProductService productService, ProductRepo repo, SizeRepo sizeRepo, ColorRepo colorRepo, ProductVariantService productVariantService) {

        this.productService = productService;
        this.orderService = orderService;
        this.repo = repo;
        this.productVariantService = productVariantService;
        this.sizeRepo = sizeRepo;
        this.colorRepo = colorRepo;
    }

//    @PostMapping("add-order")
//    protected ResponseEntity<?> addOrder(
//            @PathVariable("id") Long id,
//            @RequestHeader("Authorization") String bearerToken
//    ) throws Exception {
//        try {
//            String userId = null;
//            String token = null;
//            try {
//                token = jwtService.validateToken(bearerToken);
//                userId = jwtService.getUserNameJwt(token);
//            } catch (Exception ex) {
//                Response response = new Response("00", "Chưa đăng nhập", true, null);
//                return ResponseEntity.ok(response);
//            }
//            Page<Map<String, Object>> result = orderService.createOrder(token);
//            Response response = new Response("00", "", true, result);
//            return ResponseEntity.ok(response);
//        } catch (Exception ex) {
//            Response response = new Response("00", "Có lỗi xảy ra", true, "");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//    }

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
//    protected Page<Map<String, Object>> searchParsing(
    protected ResponseEntity<Response> searchParsing(
            @RequestHeader("Authorization") String bearerToken
            , @RequestParam(value = "search", defaultValue = "#") String search
            , Pageable pageable) {
        try {
            String userId = null;
            String token = null;
            try {
                token = jwtService.validateToken(bearerToken);
                userId = jwtService.getUserNameJwt(token);
            } catch (Exception ex) {
                //Sử dụng Constants.ResponseCode.EXPIRED_JWT hoặc Constants.ResponseCode.INVALID_JWT
//                Response response = new Response(Constants.ResponseCode.EXPIRED_JWT, null, true, ex.toString());
                Response response = new Response("00", "Chưa đăng nhập", true, null);
                return ResponseEntity.ok(response);
            }
            Page<Map<String, Object>> result = productService.searchParsing(token, search, pageable);
            Response response = new Response("00", "", true, result);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            Response response = new Response("00", "Có lỗi xảy ra", true, "");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("get-productVariant-by-id/{id}")
    protected List<Map<String, Object>> getByProductId(
            @PathVariable("id") Long id
    ) throws Exception {
        List<Map<String, Object>> result = productVariantService.getByProductId(id);
        return result;
    }

    @GetMapping("get-productVariant-by-id-color/{id}")
    protected List<ColorItem> getColorByProductId(
            @PathVariable("id") Long id
    ) throws Exception {
        List<ColorItem> result = productVariantService.getColorByProductId(id);
        return result;
    }

    @GetMapping("get-productVariant-by-id-size/{id}")
    protected List<SizeItem> getSizeByProductId(
            @PathVariable("id") Long id
    ) throws Exception {
        List<SizeItem> result = productVariantService.getSizeByProductId(id);
        return result;
    }

    @GetMapping("get-all-color")
    protected List<Color> getAllColor(
    ) throws Exception {
        List<Color> result = colorRepo.findAll();
        return result;
    }

    @GetMapping("get-all-size")
    protected List<Size> getAllSize(
    ) throws Exception {
        List<Size> result = sizeRepo.findAll();
        return result;
    }

    // add order
    @PostMapping("add-order")
    protected ResponseEntity<?> addOrder(
            @RequestHeader("Authorization") String bearerToken
    ) throws Exception {
        try {
            String userId = null;
            String token = null;
            try {
                token = jwtService.validateToken(bearerToken);
                userId = jwtService.getUserNameJwt(token);
            } catch (Exception ex) {
                Response response = new Response("00", "Chưa đăng nhập", true, null);
                return ResponseEntity.ok(response);
            }
            Response response = null;
            Order order = orderService.addOrder(token);
            if(Objects.isNull(order)){
                response = new Response("03", "", true, order);
            } else {
                response = new Response("01", "", true, order);
            }
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            Response response = new Response("02", "Có lỗi xảy ra", true, null);
            return ResponseEntity.ok(response);
        }
    }
}
