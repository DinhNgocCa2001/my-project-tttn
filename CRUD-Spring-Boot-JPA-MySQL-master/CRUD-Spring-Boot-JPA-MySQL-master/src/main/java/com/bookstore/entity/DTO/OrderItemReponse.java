package com.bookstore.entity.DTO;

import com.bookstore.entity.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderItemReponse extends OrderItem {
    private String sizeName;
    private String colorName;
    private String productName;
    private Long sizeId;
    private Long colorId;
    private String productImage;
}
