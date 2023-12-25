package com.bookstore.services;

import com.bookstore.entity.User;

public interface IUserService {
	
//	List<User> getUsers();
	User createUser(User user);

	String LoginUser(User user);
//
//		User updateUser(Long userId, User user);
//	User getUser(Long userId);
//	boolean deleteUser(Long userId);
//
//	List<User> getPasring(Pageable pageable);
//
//	Page<Map<String, Object>> searchParsing(String search, Pageable pageable);


}
