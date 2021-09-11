package com.example.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Person;
import com.example.model.Pet;

@Service

public class PersonPetService {
	private static final Logger logger = LoggerFactory.getLogger(PersonPetService.class);

	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public void savePersonAndPets(List<Person> personList) {
		
		for (Person person : personList) {
			for(Pet pet : person.getPets()) {
				pet.setPerson(person);
			}

		}
		for (Person person : personList) {
			entityManager.persist(person);
			entityManager.flush();
		}
		
		
	}
	@Transactional
	public Person getAllPets(Long id) {
		Person person = entityManager.find(Person.class, id);
		entityManager.flush();
		return person;
	}

}
