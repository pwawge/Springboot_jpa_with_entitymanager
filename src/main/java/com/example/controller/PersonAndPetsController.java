package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Person;
import com.example.service.PersonPetService;

@RestController
@RequestMapping("/person")
public class PersonAndPetsController {

	@Autowired
	private PersonPetService personPetService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> savePerson(@RequestBody List<Person> personList) {
		 personPetService.savePersonAndPets(personList);
		return new ResponseEntity<>("Data saved",HttpStatus.OK);
	}
	@RequestMapping(value = "/get-all-pets/{personId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getllPets(@PathVariable Long personId) {
		Person person=  personPetService.getAllPets(personId);
		return new ResponseEntity<>(person.getPets(),HttpStatus.OK);
	}
	
}
