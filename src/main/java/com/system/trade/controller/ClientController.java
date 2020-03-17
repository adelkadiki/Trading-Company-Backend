package com.system.trade.controller;

import com.system.trade.model.Client;
import com.system.trade.repository.ClientRepository;
import com.system.trade.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SalesService salesService;

    @PostMapping(value = "/addclient")
    public ResponseEntity<?> addVendor(@RequestBody Client client){

        return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);
    }

    @GetMapping("/allclients")
    public ResponseEntity<?> getAllVendors(){
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteclient/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){

        clientRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/clientbycompany/{company}")
    public ResponseEntity<?> findClientByCompany(@PathVariable String company){

        Client client= salesService.findClientByCompany(company);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


}
