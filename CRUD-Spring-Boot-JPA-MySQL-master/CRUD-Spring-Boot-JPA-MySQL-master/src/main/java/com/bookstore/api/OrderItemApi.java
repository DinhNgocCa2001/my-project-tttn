package com.bookstore.api;

//import com.bookstore.model.Currency;

import com.bookstore.entity.DTO.OrderItemReponse;
import com.bookstore.entity.DTO.Response;
import com.bookstore.repo.ColorRepo;
import com.bookstore.repo.ProductRepo;
import com.bookstore.repo.SizeRepo;
import com.bookstore.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("order-item")
public class OrderItemApi {
    private ProductService productService;
    private OrderItemService orderItemService;
    private ProductVariantService productVariantService;
    private ProductRepo repo;
    private SizeRepo sizeRepo;
    private ColorRepo colorRepo;

    @Autowired
    private JwtService jwtService;


    public OrderItemApi(OrderItemService orderItemService, OrderService orderService, ProductService productService, ProductRepo repo, SizeRepo sizeRepo, ColorRepo colorRepo, ProductVariantService productVariantService) {

        this.productService = productService;
        this.orderItemService = orderItemService;
        this.repo = repo;
        this.productVariantService = productVariantService;
        this.sizeRepo = sizeRepo;
        this.colorRepo = colorRepo;
    }
    @GetMapping("get-by-id/{id}")
    protected ResponseEntity<?> getSizeByProductId(
            @PathVariable("id") Long id
    ) throws Exception {
        List<OrderItemReponse> result = orderItemService.getListOrderItemByOrderId(id);
        Response response = new Response("00", "", true, result);
        return ResponseEntity.ok(response);
    }
}
