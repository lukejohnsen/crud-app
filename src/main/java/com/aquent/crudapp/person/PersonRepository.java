package com.aquent.crudapp.person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    // JPA automatically gives us:
    // save()
    // findById()
    // findAll()
    // deleteById()
    // count()
}
