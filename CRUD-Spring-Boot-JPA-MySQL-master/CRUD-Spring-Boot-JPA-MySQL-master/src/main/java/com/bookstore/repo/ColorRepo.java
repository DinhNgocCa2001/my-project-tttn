package com.bookstore.repo;

import com.bookstore.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ColorRepo extends JpaRepository<Color, Long>, PagingAndSortingRepository<Color, Long> {
    List<Color> findAll();
}
