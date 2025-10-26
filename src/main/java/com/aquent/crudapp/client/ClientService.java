package com.aquent.crudapp.client;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ClientService {

    List<Client> findAll();

    /**
     * gets client records by company name starting with letter.
     *
     * @param letter the starting letter
     * @return list of client records
     */
    List<Client> findByCompanyNameStartingWith(String letter);

    /**
     * this creates a new client record.
     *
     * @param client the values to save
     * @return the new client ID
     */
    Integer createClient(Client client);

    /** this gets a client record by ID.
     *
     * @param id the client ID
     * @return the client record
     */
    Client findClientById(Integer id);

    /** this updates an existing client record.
     *
     * @param client the new values to save
     */
    void updateClient(Client client);

    /** this deletes a client record by ID.
     *
     * @param id the client ID
     */
    void deleteClient(Integer id);

    /** this validates client data.
     *
     * @param client the values to validate
     * @return list of error messages
     */
    List<String> validateClient(Client client);
}
