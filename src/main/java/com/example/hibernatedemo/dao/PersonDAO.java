package com.example.hibernatedemo.dao;

import com.example.hibernatedemo.entity.Person;
import com.example.hibernatedemo.repositories.PersonRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    private PersonRepository personRepository;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory, PersonRepository personRepository) {
        this.sessionFactory = sessionFactory;
        this.personRepository = personRepository;
    }


    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    public List<Person> findByName(String searchName) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p where p.name = :searchName", Person.class)
                .setParameter("searchName", searchName)
                .getResultList();
    }

    public List<Person> findByAge(Integer searchAge) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p where p.age = :searchAge", Person.class)
                .setParameter("searchAge", searchAge)
                .getResultList();
    }

    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }


    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }


    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void updateNameById(int id, String name) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(name);
    }


    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

    public void deletePersonByName(String name) {
        personRepository.deleteByName(name);
    }

    public void updateEmailByName(String name, String email) {
        personRepository.updateEmailByName(name, email);
    }

}
