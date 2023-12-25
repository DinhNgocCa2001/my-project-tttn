package com.bookstore.repo;

import com.bookstore.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SizeRepo extends JpaRepository<Size, Long>, PagingAndSortingRepository<Size, Long> {
    List<Size> findAll();
}
