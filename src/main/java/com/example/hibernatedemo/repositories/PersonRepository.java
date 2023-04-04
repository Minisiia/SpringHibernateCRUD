package com.example.hibernatedemo.repositories;

import com.example.hibernatedemo.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class PersonRepository {

    private final EntityManager entityManager;

    @Autowired
    public PersonRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Person> index() {
        return entityManager.createQuery("select p from Person p", Person.class).getResultList();
    }

    public List<Person> findByName(String searchName) {
        return entityManager.createQuery("select p from Person p where p.name = :searchName", Person.class)
                .setParameter("searchName", searchName)
                .getResultList();
    }

    public List<Person> findByAge(Integer searchAge) {
        return entityManager.createQuery("select p from Person p where p.age = :searchAge", Person.class)
                .setParameter("searchAge", searchAge)
                .getResultList();
    }

    public Person show(int id) {
        return entityManager.find(Person.class, id);
    }


    public void save(Person person) {
        entityManager.persist(person);
    }


    public void update(int id, Person updatedPerson) {

        Person personToBeUpdated = entityManager.find(Person.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void updateNameById(int id, String name) {
        Person personToBeUpdated = entityManager.find(Person.class, id);
        personToBeUpdated.setName(name);
    }


    public void delete(int id) {
        entityManager.remove(entityManager.find(Person.class, id));
    }

    @Modifying
    public void deleteByName(String name) {
        Query query = entityManager.createQuery("DELETE FROM Person p WHERE p.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Modifying
    public void updateEmailByName(String name, String email) {
        Query query = entityManager.createQuery("UPDATE Person p SET p.email =: email WHERE p.name = :name");
        query.setParameter("name", name);
        query.setParameter("email", email);
        query.executeUpdate();
    }
}
