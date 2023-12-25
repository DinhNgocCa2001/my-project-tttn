package com.bookstore.services;

import com.bookstore.entity.DTO.OrderRequest;
import com.bookstore.entity.Order;

public interface IOrderService {
	
//	List<User> getUsers();
	Order createOrder(OrderRequest order);
	Order addOrder(String token);

//
//		User updateUser(Long userId, User user);
//	User getUser(Long userId);
//	boolean deleteUser(Long userId);
//
//	List<User> getPasring(Pageable pageable);
//
//	Page<Map<String, Object>> searchParsing(String search, Pageable pageable);


}
