package com.aquent.crudapp.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    // JPA automatically gives: save() (for create and update), findById(), findAll(), deleteById(), count()
}
