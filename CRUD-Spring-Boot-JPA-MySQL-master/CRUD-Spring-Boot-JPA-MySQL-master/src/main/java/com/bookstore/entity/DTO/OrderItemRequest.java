package com.bookstore.entity.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderItemRequest {
    private  Long id;
    private Long colorId;
    private Long orderId;
    private Long productId;
    private Long sizeId;
    private Long userId;
    private Long quantity;
}
