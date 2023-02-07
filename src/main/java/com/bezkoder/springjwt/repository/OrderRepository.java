package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository <Order,Long> {
    Optional<Order> findFirstByUsername(String username);

    Optional<Order> findFirstById(Long id);

    List<Order> findAllByUsername(String username);


}
