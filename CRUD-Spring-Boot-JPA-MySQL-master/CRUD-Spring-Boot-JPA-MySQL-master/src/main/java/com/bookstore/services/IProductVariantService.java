package com.bookstore.services;

import com.bookstore.entity.DTO.ColorItem;
import com.bookstore.entity.DTO.SizeItem;

import java.util.List;
import java.util.Map;

public interface IProductVariantService {

    //	List<User> getUsers();
    List<Map<String, Object>> getByProductId(Long ProductId);

    List<ColorItem> getColorByProductId(Long ProductId);
    List<SizeItem> getSizeByProductId(Long ProductId);

//
//		User updateUser(Long userId, User user);
//	User getUser(Long userId);
//	boolean deleteUser(Long userId);
//
//	List<User> getPasring(Pageable pageable);
//
//	Page<Map<String, Object>> searchParsing(String search, Pageable pageable);


}
