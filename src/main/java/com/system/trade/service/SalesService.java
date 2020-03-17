package com.system.trade.service;

import com.system.trade.model.Client;
import com.system.trade.model.Product;
import com.system.trade.model.Sales;
import com.system.trade.repository.ClientRepository;
import com.system.trade.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<Sales> getAllSales(){

        return salesRepository.findAll();
    }

    public void saveSales(Sales sales){

        salesRepository.save(sales);

    }

    public Sales findById(Long id){

        return salesRepository.findSalesById(id);
    }

    public Client findClientByCompany(String company){
        return clientRepository.findClientByCompany(company);
    }

}
