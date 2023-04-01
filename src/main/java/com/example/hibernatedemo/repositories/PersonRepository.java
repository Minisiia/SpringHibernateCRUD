package com.example.hibernatedemo.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PersonRepository {

    private final EntityManager entityManager;

    @Autowired
    public PersonRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
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
