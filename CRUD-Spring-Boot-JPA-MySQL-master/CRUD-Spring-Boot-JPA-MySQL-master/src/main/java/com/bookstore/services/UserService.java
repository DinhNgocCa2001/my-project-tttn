package com.bookstore.services;

import com.bookstore.dao.IProductDAO;
import com.bookstore.entity.User;
import com.bookstore.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements IUserService {

	private boolean defaultStatus = true;
	@Autowired
	private IProductDAO dao;

	private UserRepo repo;
	@Autowired
	private AuthService authService;

	public UserService(UserRepo repository) {
		this.repo = repository;
	}

//	@Override
//	public List<User> getUsers() {
//		return dao.getUsers();
//	}

	public static Long generateUniqueId() {
		Random random = new Random();
		long min = 10000L;
		long max = 99999L;
		return min + ((long) (random.nextDouble() * (max - min)));
	}

	@Override
	public User createUser(User user) {
		user.setId(generateUniqueId());
		user.setUsername(user.getUsername());
		user.setPassword(user.getPassword());
		return repo.save(user);
	}

	@Override
	public String LoginUser(User user) {
		String token= null;
		user.setId(generateUniqueId());
		user.setEmail(user.getEmail());
		user.setPassword(user.getPassword());
		Optional<User> abc = repo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if(abc.isPresent()){
			token = authService.generateToken(abc.get().getId().toString());
		}
		return token;
	}

//	@Override
//	public User updateUser(Long productId, User product) {
//		return dao.updateUser(productId, product);
//	}
//
//	@Override
//	public User getUser(Long productId) {
//		return dao.getUser(productId);
//	}
//
//	@Override
//	public boolean deleteUser(Long productId) {
//		return dao.deleteUser(productId);
//	}
//
//	@Override
//	public List<User> getPasring(Pageable pageable) {
//		return repo.findByStatus( defaultStatus, pageable);
//	}
//
//	@Override
//	public Page<Map<String, Object>> searchParsing( String search, Pageable pageable){
//		return repo.search(defaultStatus, search, pageable);
//	}


}
