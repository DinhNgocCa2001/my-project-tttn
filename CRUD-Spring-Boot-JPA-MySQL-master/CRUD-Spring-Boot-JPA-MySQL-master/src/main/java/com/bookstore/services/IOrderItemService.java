package com.bookstore.services;

import com.bookstore.entity.DTO.OrderItemRequest;

public interface IOrderItemService {
	
//	List<User> getUsers();

//	List<OrderItemReponse> getListOrderItemByOrderId(Long orderId);
	String addOrderItem(OrderItemRequest orderItemRequest);
	String updateOrderItem(OrderItemRequest orderItemRequest);

//
//		User updateUser(Long userId, User user);
//	User getUser(Long userId);
//	boolean deleteUser(Long userId);
//
//	List<User> getPasring(Pageable pageable);
//
//	Page<Map<String, Object>> searchParsing(String search, Pageable pageable);


}
