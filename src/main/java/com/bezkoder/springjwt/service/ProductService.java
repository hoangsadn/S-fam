package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.models.Review;
import com.bezkoder.springjwt.repository.ProductRepository;
import com.bezkoder.springjwt.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

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


    public Boolean insertReview(Long id, Review req){
        Optional<Product> product = repository.findById(id);
        req.setProduct(product.orElse(null));
        reviewRepository.save(req);
        return true;
    }


}
