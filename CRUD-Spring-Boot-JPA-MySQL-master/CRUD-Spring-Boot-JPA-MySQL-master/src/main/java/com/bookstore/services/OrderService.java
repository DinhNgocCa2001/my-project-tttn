package com.bookstore.services;

import com.bookstore.common.Constants;
import com.bookstore.dao.IProductDAO;
import com.bookstore.entity.DTO.OrderRequest;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderItem;
import com.bookstore.repo.OrderItemRepo;
import com.bookstore.repo.OrderRepo;
import com.bookstore.repo.ProductVariantRepo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService implements IOrderService {

	private boolean defaultStatus = true;
	@Autowired
	private IProductDAO dao;

	private OrderRepo repo;

	private OrderItemRepo orderItemRepo;

	private ProductVariantRepo productVariantRepo;

	@Autowired
	private JwtService jwtService;

	public OrderService(IProductDAO dao, OrderRepo repo, OrderItemRepo orderItemRepo, ProductVariantRepo productVariantRepo) {
		this.dao = dao;
		this.repo = repo;
		this.orderItemRepo = orderItemRepo;
		this.productVariantRepo = productVariantRepo;
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
	public Order addOrder(String token) {
		Long userId = null;
		userId = Long.parseLong(jwtService.getUserNameJwt(token));
		Optional<Order> orderPending =  repo.findByUserIdAndStatus(userId, Constants.STATUS_ORDER.PENDING);
		if(!orderPending.isPresent()) {
			Order newOrder = new Order();
			newOrder.setId(generateUniqueId());
			newOrder.setUserId(userId);
			newOrder.setTotalAmount(BigDecimal.valueOf(0));
			newOrder.setShippingAddress(null);
			newOrder.setStatus(Constants.STATUS_ORDER.PENDING);
			repo.save(newOrder);
			return newOrder;
		}else{
			return orderPending.get();
		}
	}

	@Override
	public Order createOrder(OrderRequest request) {

		var orderPending = repo.findByStatus("Pending");
		//tính tổng tiền
		var abc  = productVariantRepo.findBySizeIdAndColorId(request.getSizeId(), request.getColorId());
		var total = abc.get().getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));

		if(Objects.isNull(orderPending)){
			Order order = new Order();

			order.setId(generateUniqueId());
			order.setUserId(request.getUserId());
			order.setTotalAmount(total);

			OrderItem orderItem = new OrderItem();
			orderItem.setId(generateUniqueId());
			orderItem.setOrderId(order.getId());
			orderItem.setProductId(request.getProductId());
			orderItem.setQuantity(request.getQuantity());
			orderItem.setPrice(abc.get().getPrice());
			orderItemRepo.save(orderItem);
			//chưa biết return có mục đích gì
			repo.save(order);
			//tính lại tổng tiền //bỏ qua
//			var orderAfter = orderItemRepo.findByOrderId(order.getId());
//			BigDecimal tongAfter = BigDecimal.valueOf(0);
//			for( OrderItem a : orderAfter){
//				tongAfter = tongAfter.add( a.getPrice().multiply(BigDecimal.valueOf(a.getQuantity())));
//			}
//
//			var orderPending1 = repo.findByStatus("Pending");
//			orderPending1.get().setTotalAmount(tongAfter);
//			repo.save(orderPending1.get());

			return order;
		}else{
			//nếu có thui add thêm sản phẩm vào giỏ hàng qua bảng orderItem
			//kiểu tra xem
			boolean check;

			Optional<OrderItem> hi = orderItemRepo.findByOrderIdAndProductIdAndPrice(orderPending.get().getId(), request.getProductId(), abc.get().getPrice());
			try{
				check = Objects.isNull(hi.get().getId());
			}catch(Exception ex){
				check = true;
			}
			if(check){
				OrderItem orderItem = new OrderItem();
				orderItem.setId(generateUniqueId());
				orderItem.setOrderId(orderPending.get().getId());
				orderItem.setProductId(request.getProductId());
				orderItem.setQuantity(request.getQuantity());
				orderItem.setPrice(abc.get().getPrice());
				orderItemRepo.save(orderItem);
			}else{
				//nếu có thì cập nhập lại số lượng
				var hi1 = orderItemRepo.findByOrderIdAndProductIdAndPrice(orderPending.get().getId(), request.getProductId(), abc.get().getPrice());
				hi1.get().setQuantity(hi1.get().getQuantity() + request.getQuantity());
				orderItemRepo.save(hi1.get());

				var orderAfter = orderItemRepo.findByOrderId(orderPending.get().getId());
				BigDecimal tongAfter = BigDecimal.valueOf(0);
				for( OrderItem a : orderAfter){
					tongAfter = tongAfter.add( a.getPrice().multiply(BigDecimal.valueOf(a.getQuantity())));
				}

				var orderPending1 = repo.findByStatus("Pending");
				orderPending1.get().setTotalAmount(tongAfter);
				repo.save(orderPending1.get());
			}
			//return cho có thôi
			return orderPending.get();
		}
	}


}
