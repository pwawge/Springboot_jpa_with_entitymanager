package com.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name="first_name")
    private String firstName;
    
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="person", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Pet> pets = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public Set<Pet> getPets() {
        
        return pets;
    }

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", pets=" + pets + "]";
	}
	
	
}
