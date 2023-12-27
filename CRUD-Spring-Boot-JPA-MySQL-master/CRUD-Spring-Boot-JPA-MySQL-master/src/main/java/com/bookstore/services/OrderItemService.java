package com.bookstore.services;

import com.bookstore.common.Constants;
import com.bookstore.dao.IProductDAO;
import com.bookstore.entity.DTO.OrderItemReponse;
import com.bookstore.entity.DTO.OrderItemRequest;
import com.bookstore.entity.Order;
import com.bookstore.entity.OrderItem;
import com.bookstore.entity.ProductVariant;
import com.bookstore.repo.OrderItemRepo;
import com.bookstore.repo.OrderRepo;
import com.bookstore.repo.ProductVariantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderItemService implements IOrderItemService {

    private boolean defaultStatus = true;
    @Autowired
    private IProductDAO dao;

    private OrderRepo repo;

    private OrderItemRepo orderItemRepo;

    private ProductVariantRepo productVariantRepo;

    @Autowired
    private JwtService jwtService;

    public OrderItemService(IProductDAO dao, OrderRepo repo, OrderItemRepo orderItemRepo, ProductVariantRepo productVariantRepo) {
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


    public List<OrderItemReponse> getListOrderItemByOrderId(String token) {
        Long userId = null;
        userId = Long.parseLong(jwtService.getUserNameJwt(token));
        Optional<Order> order = repo.findByUserIdAndStatus(userId, Constants.STATUS_ORDER.PENDING);
        List<OrderItemReponse> listOrderItemReponse = new ArrayList<OrderItemReponse>();
        if(order.isPresent()){
            List<Object[]> listObj = orderItemRepo.getByOrderId(order.get().getId());
            for (Object[] obj : listObj) {
                OrderItemReponse orderItemReponse = new OrderItemReponse();
                orderItemReponse = convert_result_to_orderItemReponse(obj);

                listOrderItemReponse.add(orderItemReponse);
            }
        }
        return listOrderItemReponse;
    }

    private OrderItemReponse convert_result_to_orderItemReponse(Object[] obj) {
        OrderItemReponse orderItemReponse = new OrderItemReponse();
        orderItemReponse.setId(Objects.nonNull(obj[0]) ? ((Integer) obj[0]).longValue() : null);
        orderItemReponse.setOrderId(Objects.nonNull(obj[1]) ? ((Integer) obj[1]).longValue() : null);
        orderItemReponse.setProductId(Objects.nonNull(obj[2]) ? ((Integer) obj[2]).longValue() : null);
        orderItemReponse.setProductVariantId(Objects.nonNull(obj[3]) ? ((Integer) obj[3]).longValue() : null);
        orderItemReponse.setQuantity(Objects.nonNull(obj[4]) ? ((Integer) obj[4]).longValue() : null);
        orderItemReponse.setPrice(Objects.nonNull(obj[5]) ? (BigDecimal) obj[5] : null);
        orderItemReponse.setSizeName(Objects.nonNull(obj[6]) ? obj[6].toString() : null);
        orderItemReponse.setColorName(Objects.nonNull(obj[7]) ? obj[7].toString() : null);
        orderItemReponse.setProductName(Objects.nonNull(obj[8]) ? obj[8].toString() : null);
        orderItemReponse.setSizeId(Objects.nonNull(obj[9]) ? ((Integer) obj[9]).longValue() : null);
        orderItemReponse.setColorId(Objects.nonNull(obj[10]) ? ((Integer) obj[10]).longValue() : null);
        orderItemReponse.setProductImage(Objects.nonNull(obj[11]) ? obj[11].toString() : null);
        return orderItemReponse;
    }

    @Override
    public String addOrderItem(OrderItemRequest orderItemRequest) {
        Long userId = null;
//		userId = Long.parseLong(jwtService.getUserNameJwt(token));
        //lấy product variant: sizeId, colorId, productId
        Optional<ProductVariant> productVariant = productVariantRepo.findBySizeIdAndColorIdAndProductId(
                orderItemRequest.getSizeId()
                , orderItemRequest.getColorId()
                , orderItemRequest.getProductId()
        );
        if (!productVariant.isPresent()) {
            return "FALSE";
        }

        Optional<OrderItem> orderItem = orderItemRepo.findByOrderIdAndProductIdAndProductVariantId(
                orderItemRequest.getOrderId()
                , orderItemRequest.getProductId()
                , productVariant.get().getId()
        );
        if (orderItem.isPresent()) {
            return "HAS";
        } else {
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setId(generateUniqueId());
            newOrderItem.setOrderId(orderItemRequest.getOrderId());
            newOrderItem.setProductId(orderItemRequest.getProductId());
            newOrderItem.setProductVariantId(productVariant.get().getId());
            newOrderItem.setQuantity(orderItemRequest.getQuantity());
            newOrderItem.setPrice(BigDecimal.valueOf(productVariant.get().getStock()));
            orderItemRepo.save(newOrderItem);
            return "NEW";
        }
    }

    @Override
    public String updateOrderItem(OrderItemRequest orderItemRequest) {
        Optional<OrderItem> findById = orderItemRepo.findById(orderItemRequest.getId());
        if(findById.isPresent()){
            findById.get().setQuantity(orderItemRequest.getQuantity());
            orderItemRepo.save(findById.get());
            return "DONE";
        }
        return "FALSE";
    }
}
