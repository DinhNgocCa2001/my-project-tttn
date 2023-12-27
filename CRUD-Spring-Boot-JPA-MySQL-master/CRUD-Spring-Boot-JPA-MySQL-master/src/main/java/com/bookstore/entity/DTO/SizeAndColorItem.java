package com.bookstore.entity.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SizeAndColorItem {
    private Long sizeId;
    private String sizeName;
    private Long colorId;
    private String colorName;
}
