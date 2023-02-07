package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository  extends JpaRepository <OrderItem,Long> {
}
