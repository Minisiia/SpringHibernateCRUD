package com.example.hibernatedemo.dao;

import com.example.hibernatedemo.entity.Person;
import com.example.hibernatedemo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class PersonDAO {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDAO(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> index() {
        return personRepository.index();
    }

    public List<Person> findByName(String searchName) {
        return personRepository.findByName(searchName);
    }

    public List<Person> findByAge(Integer searchAge) {
        return personRepository.findByAge(searchAge);
    }

    public Person show(int id) {
        return personRepository.show(id);
    }


    public void save(Person person) {
        personRepository.save(person);
    }


    public void update(int id, Person updatedPerson) {
        personRepository.update(id, updatedPerson);
    }

    public void updateNameById(int id, String name) {
        personRepository.updateNameById(id, name);
    }


    public void delete(int id) {
        personRepository.delete(id);
        ;
    }

    public void deletePersonByName(String name) {
        personRepository.deleteByName(name);
    }

    public void updateEmailByName(String name, String email) {
        personRepository.updateEmailByName(name, email);
    }

}
