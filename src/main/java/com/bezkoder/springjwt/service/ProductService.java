package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getListProduct(){

        List<Product> products = repository.findAll();

        return products;
    }

    public Product getProductById(Integer id){

        List<Product> products = repository.findAll();

        return products.stream().filter(item -> item.getId().equals(Long.valueOf(id))).findFirst().orElse(null);
    }


    public Boolean insertProduct(Product req){

        repository.save(req);
        return true;

    }


}
