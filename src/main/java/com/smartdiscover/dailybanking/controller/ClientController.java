package com.smartdiscover.dailybanking.controller;

import com.smartdiscover.dailybanking.entity.Client;
import com.smartdiscover.dailybanking.model.CreateClientModel;
import com.smartdiscover.dailybanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") long id) {
        Client client = clientService.getClient(id);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Client> saveClient(@RequestBody CreateClientModel clientModel) {
        return new ResponseEntity<>(clientService.saveClient(clientModel), HttpStatus.OK);
    }

}
