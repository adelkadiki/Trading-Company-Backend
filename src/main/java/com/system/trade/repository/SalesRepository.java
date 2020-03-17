package com.system.trade.repository;

import com.system.trade.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SalesRepository extends JpaRepository<Sales, Long> {

    Sales findSalesById(Long id);
}
