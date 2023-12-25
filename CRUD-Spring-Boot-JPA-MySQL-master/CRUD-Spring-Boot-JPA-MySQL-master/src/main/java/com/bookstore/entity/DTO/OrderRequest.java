package com.bookstore.entity.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderRequest {
    private Long productId;
    private Long sizeId;
    private Long colorId;
    private Long quantity;
    private Long userId;
}
