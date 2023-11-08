package com.smartdiscover.dailybanking.service;

import com.smartdiscover.dailybanking.entity.Client;
import com.smartdiscover.dailybanking.model.CreateClientModel;
import com.smartdiscover.dailybanking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client getClient(Long id) {
        Optional<Client> clientVal = clientRepository.findById(id);
        return clientVal.isPresent() ? clientVal.get() : null;
    }

    public Client saveClient(CreateClientModel clientModel) {
        Client client = new Client(clientModel);
        return clientRepository.save(client);
    }

}
