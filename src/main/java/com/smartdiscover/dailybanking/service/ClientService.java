package com.smartdiscover.service;

import com.smartdiscover.entity.Client;
import com.smartdiscover.model.CreateClientModel;
import com.smartdiscover.repository.ClientRepository;
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
