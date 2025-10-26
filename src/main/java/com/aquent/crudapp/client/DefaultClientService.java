package com.aquent.crudapp.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DefaultClientService implements ClientService{
    private final ClientRepository clientRepository;
    private final Validator validator;

    public DefaultClientService(ClientRepository clientRepository, Validator validator) {
        this.clientRepository = clientRepository;
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client findClientById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createClient(Client client) {
        Client savedClient = clientRepository.save(client);
        return savedClient.getClientId();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<String> validateClient(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        List<String> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<Client> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }


}