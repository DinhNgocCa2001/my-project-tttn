package com.bookstore.services;

import com.bookstore.entity.DTO.OrderItemReponse;
import com.bookstore.entity.DTO.OrderRequest;
import com.bookstore.entity.Order;

import java.util.List;

public interface IOrderItemService {
	
//	List<User> getUsers();
	Order createOrder(OrderRequest order);
	Order addOrder(String token);
//	List<OrderItem> getListOrderItemByOrderId(Long orderId);

	List<OrderItemReponse> getListOrderItemByOrderId(Long orderId);

//
//		User updateUser(Long userId, User user);
//	User getUser(Long userId);
//	boolean deleteUser(Long userId);
//
//	List<User> getPasring(Pageable pageable);
//
//	Page<Map<String, Object>> searchParsing(String search, Pageable pageable);


}
