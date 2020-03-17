package com.system.trade.controller;

import com.system.trade.model.Item;
import com.system.trade.model.Product;
import com.system.trade.model.Sales;
import com.system.trade.model.Vendor;
import com.system.trade.repository.ItemsRepository;
import com.system.trade.repository.SalesRepository;
import com.system.trade.service.ItemService;
import com.system.trade.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private ItemService itemService;


    @GetMapping("/allsales")
    public ResponseEntity<?> getAllSales(){
        return new ResponseEntity<>(salesService.getAllSales(), HttpStatus.OK);
    }

    @PostMapping("/addsales")
    public ResponseEntity<?> addVendor(@RequestBody Sales sales){

        return new ResponseEntity<>(salesRepository.save(sales), HttpStatus.CREATED);
    }

    @GetMapping("/findsalesbyid/{id}")
    public ResponseEntity<Sales> findSalesById(@PathVariable(value = "id") Long cid){

        Sales sales = salesService.findById(cid);

        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PutMapping("/updatesales")
    public ResponseEntity<?> updateProduct(@RequestBody Sales sales){

        Sales updateSales = salesService.findById(sales.getId());

        if(updateSales== null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(salesRepository.save(sales), HttpStatus.CREATED);
    }


    @DeleteMapping("/deletesales/{id}")
    public ResponseEntity<?> deleteSales(@PathVariable Long id){

        salesRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Items section


    @PostMapping("/additem/{id}")
    public ResponseEntity<?> addItems(@PathVariable(value = "id") Long id, @RequestBody Set<Item> items){

        Sales sales = salesService.findById(id);
        for(Item i: items){
            itemsRepository.save(i);

        }

        sales.getItems().addAll(items);
        salesService.saveSales(sales);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/updateitem")
    public ResponseEntity<?> updateItem(@RequestBody Item item){

        Item updateItem = itemService.findById(item.getId());

        if(updateItem== null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(itemsRepository.save(item), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteitem/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){

        itemsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getsalesitems/{id}")
    public ResponseEntity<?> getSalesItems(@PathVariable Long id){

        Sales sales = salesService.findById(id);
        Set<Item> items = sales.getItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
