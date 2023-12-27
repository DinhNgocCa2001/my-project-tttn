package com.bookstore.services;

import com.bookstore.entity.DTO.ColorItem;
import com.bookstore.entity.DTO.SizeAndColorItem;
import com.bookstore.entity.DTO.SizeItem;
import com.bookstore.entity.ProductVariant;

import java.util.List;
import java.util.Map;

public interface IProductVariantService {

    //	List<User> getUsers();
    List<Map<String, Object>> getByProductId(Long ProductId);

    List<ColorItem> getColorByProductId(Long ProductId);
    List<SizeItem> getSizeByProductId(Long ProductId);
    ProductVariant findBySizeIdAndColorIdAndProductId(Long sizeId, Long colorId, Long productId);

    List<SizeAndColorItem> getSizeAndColorByProductId(Long ProductId);


//
//		User updateUser(Long userId, User user);
//	User getUser(Long userId);
//	boolean deleteUser(Long userId);
//
//	List<User> getPasring(Pageable pageable);
//
//	Page<Map<String, Object>> searchParsing(String search, Pageable pageable);


}
