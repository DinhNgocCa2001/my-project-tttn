package com.bookstore.repo;

import com.bookstore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long>, PagingAndSortingRepository<OrderItem, Long> {
//    List<OrderItem> findByUserId();

    Optional<OrderItem> findByOrderIdAndProductIdAndPrice(
            @Param("orderId") Long orderId,
            @Param("productId") Long productId,
            @Param("price") BigDecimal price
            );

    Optional<OrderItem> findByOrderIdAndProductIdAndProductVariantId(
            @Param("orderId") Long orderId,
            @Param("productId") Long productId,
            @Param("productVariantId") Long productVariantId
    );

    List<OrderItem> findByOrderId(
            @Param("orderId") Long orderId
    );

    Optional<OrderItem> findById(
            @Param("id") Long id
    );

    @Query(
            value = "SELECT " +
                    " oi.id " +
                    " , oi.order_id  " +
                    " , oi.product_id  " +
                    " , oi.product_variant_id " +
                    " , oi.quantity " +
                    " , oi.price " +
                    " , s.name as sizeName " +
                    " , c.name as colorName " +
                    " , p.name as productName " +
                    " , pv.size_id as sizeId " +
                    " , pv.color_id as colorId " +
                    " , p.image as imageProduct " +
                    " FROM orderitems oi " +
                    " LEFT JOIN Productvariants pv ON pv.id = oi.product_variant_id  " +
                    " LEFT JOIN sizes s ON s.id = pv.size_id  " +
                    " LEFT JOIN colors c ON c.id = pv.color_id  " +
                    " LEFT JOIN product p ON p.id = oi.product_id  " +
                    " WHERE oi.order_id = :orderId"
            ,
            nativeQuery = true
    )
    List<Object[]> getByOrderId(@Param("orderId") Long orderId);

}
