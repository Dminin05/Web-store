package com.minin.web.repository;

import com.minin.web.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}
