package com.system.trade.service;

import com.system.trade.model.Item;
import com.system.trade.model.Product;
import com.system.trade.repository.ItemsRepository;
import com.system.trade.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemsRepository itemsRepository;

    public List<Item> getAllItems(){

        return itemsRepository.findAll();
    }

    public void saveItem(Item item){

        itemsRepository.save(item);
    }

    public Item findById(Long id){

        return itemsRepository.findItemById(id);
    }

}
