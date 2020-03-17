package com.system.trade.repository;

import com.system.trade.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ClientRepository  extends JpaRepository<Client, Long> {

    Client findClientById(Long id);
    Client findClientByCompany(String company);
}
