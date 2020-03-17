package com.system.trade.service;

import com.system.trade.model.Product;
import com.system.trade.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){

        return productRepository.findAll();
    }

    public void saveProduct(Product product){

        productRepository.save(product);

    }

    public Product findById(Long id){
        return productRepository.findProductById(id);
    }



}
