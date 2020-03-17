package com.system.trade.repository;

import com.system.trade.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ItemsRepository extends JpaRepository<Item, Long> {

    Item findItemById(Long id);
}
