package com.system.trade.service;

import com.system.trade.model.Product;
import com.system.trade.model.Vendor;
import com.system.trade.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> getAllVendors(){

        return vendorRepository.findAll();
    }

    public void saveVendor(Vendor vendor){

        vendorRepository.save(vendor);
    }

    public Vendor findById(Long id){

        return vendorRepository.findVendorById(id);
    }


}
