package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.models.Review;
import com.bezkoder.springjwt.repository.ProductRepository;
import com.bezkoder.springjwt.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Product> getListProduct(String cate,String sort,String key){

        List<Product> products = repository.findAll();
        if (!cate.equalsIgnoreCase("")){
            products = products.stream().filter(item-> item.getCategory().equalsIgnoreCase(cate)).collect(Collectors.toList());
        }
        if (sort.equalsIgnoreCase("highest")){
            Collections.sort(products, Comparator.comparing(Product::getPrice).reversed());
        }
        if (sort.equalsIgnoreCase("lowest")){
            Collections.sort(products, Comparator.comparing(Product::getPrice));
        }
        if (!key.equalsIgnoreCase(""))
            products = products.stream().filter(item->item.getName().contains(key)).collect(Collectors.toList());

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


    public Boolean editProduct(Long id, Product res) {
        repository.save(res);
        return true;
    }
}
