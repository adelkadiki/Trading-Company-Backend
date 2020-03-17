package com.system.trade.repository;

import com.system.trade.model.Product;
import com.system.trade.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Vendor findVendorById(Long id);

}
