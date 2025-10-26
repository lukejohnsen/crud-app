package com.aquent.crudapp.person;

import java.util.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of {@link PersonService}.
 */
@Component
public class DefaultPersonService implements PersonService {

    private final PersonRepository personRepository;
    private final Validator validator;

    public DefaultPersonService(PersonRepository personRepository, Validator validator) {
        this.personRepository = personRepository;
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person findPersonById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return savedPerson.getPersonId();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }

    // JPA provides automatic validation with save(). consider changing later.
    @Override
    public List<String> validatePerson(Person person) {
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        List<String> errors = new ArrayList<String>(violations.size());
        for (ConstraintViolation<Person> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
