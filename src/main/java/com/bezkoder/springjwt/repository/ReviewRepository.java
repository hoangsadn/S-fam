package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByProductId(Long id);
}