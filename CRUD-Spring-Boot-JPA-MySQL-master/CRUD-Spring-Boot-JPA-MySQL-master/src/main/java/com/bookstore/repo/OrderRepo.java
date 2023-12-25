package com.bookstore.repo;

import com.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long>, PagingAndSortingRepository<Order, Long> {
    Optional<Order> findByStatus(@Param("status") String status);
    Optional<Order> findByUserIdAndStatus(@Param("userId") Long userId ,@Param("status") String status);
}
