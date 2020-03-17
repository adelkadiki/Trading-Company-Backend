package com.system.trade.controller;

import com.system.trade.model.Product;
import com.system.trade.model.Vendor;
import com.system.trade.repository.ProductRepository;
import com.system.trade.service.ProductService;
import com.system.trade.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private VendorService vendorService;

    @GetMapping("/allproducts")
    public ResponseEntity<?> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/addproduct/{id}")
    public ResponseEntity<?> addProduct(@PathVariable(value = "id") Long id, @RequestBody Set<Product> product){

        Vendor vendor = vendorService.findById(id);
        for(Product p : product){
            productRepository.save(p);

        }
           vendor.getProducts().addAll(product);
           vendorService.saveVendor(vendor);
           return new ResponseEntity<>(HttpStatus.CREATED);


    }

    @GetMapping("/findproductbyid/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable(value = "id") Long cid){

        Product product = productService.findById(cid);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/updateproduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product){

        Product updateProduct = productService.findById(product.getId());

        if(updateProduct== null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){

        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
