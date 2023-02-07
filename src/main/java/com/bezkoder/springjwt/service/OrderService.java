package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Order;
import com.bezkoder.springjwt.models.OrderItem;
import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.repository.OrderItemRepository;
import com.bezkoder.springjwt.repository.OrderRepository;
import com.bezkoder.springjwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> getListOrders(String username){

        List<Order> orders = repository.findAllByUsername(username);

        return orders;
    }

    public List<Order> getAll(){
        return repository.findAll();
    }

    public String delOrder(Long id){
        repository.deleteById(id);
        return "del order success";
    }




    public Order getOrderById(Long id){

        Order order = repository.findFirstById(id).orElse(null);

        return order;
    }

    public Order insertOrder(Order req){
        List<OrderItem> itemList = req.getOrderItems();
        req.setOrderItems(null);
        Order order =  repository.save(req);
        itemList.stream().forEach(item -> {
            item.setOrder(order);
            orderItemRepository.save(item);
        });
        return repository.findFirstByUsername(req.getUsername()).orElse(null);
    }
}
