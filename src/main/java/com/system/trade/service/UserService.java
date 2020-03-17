package com.system.trade.service;

import com.system.trade.model.Client;
import com.system.trade.model.Product;
import com.system.trade.repository.ClientRepository;
import com.system.trade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients(){

        return clientRepository.findAll();
    }

    public void saveClient(Client client){

        clientRepository.save(client);
    }

    public Client findById(Long id){
        return clientRepository.findClientById(id);
    }



}
