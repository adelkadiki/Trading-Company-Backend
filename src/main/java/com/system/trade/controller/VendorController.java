package com.system.trade.controller;

import com.system.trade.model.Product;
import com.system.trade.model.Vendor;
import com.system.trade.repository.ProductRepository;
import com.system.trade.repository.VendorRepository;
import com.system.trade.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/allvendors")
    public ResponseEntity<?> getAllVendors(){
        return new ResponseEntity<>(vendorService.getAllVendors(), HttpStatus.OK);
    }

    @PostMapping(value = "/addvendor")
    public ResponseEntity<?> addVendor(@RequestBody Vendor vendor){

        return new ResponseEntity<>(vendorRepository.save(vendor), HttpStatus.CREATED);
    }

    @GetMapping("/findvendorbyid/{id}")
    public ResponseEntity<Vendor> findVendorById(@PathVariable(value = "id") Long cid){

        Vendor vendor = vendorService.findById(cid);

        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    @PutMapping("/updatevendor")
    public ResponseEntity<?> updateVendor(@RequestBody Vendor vendor){

        Vendor updateVendor = vendorService.findById(vendor.getId());

        if(updateVendor== null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        vendor.setProducts(updateVendor.getProducts());
        return new ResponseEntity<>(vendorRepository.save(vendor), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletevendor/{id}")
    public ResponseEntity<?> deleteVendor(@PathVariable Long id){

        vendorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getproducts/{id}")
    public ResponseEntity<?> getProducts(@PathVariable Long id){

        Vendor vendor = vendorService.findById(id);
        Set<Product> products= vendor.getProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);

    }

}
