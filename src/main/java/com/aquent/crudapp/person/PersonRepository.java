package com.aquent.crudapp.person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // JPA automatically gives: save() (for create and update), findById(), findAll(), deleteById(), count()

    List<Person> findAllByOrderByFirstNameAscLastNameAsc();

    List<Person> findByFirstNameStartingWithIgnoreCaseOrderByFirstNameAscLastNameAsc(String letter);
}
