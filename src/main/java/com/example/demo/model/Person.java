package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.data.annotation.Id;

@Document(collection = "person")
public class Person {

	@Id
	private String Id;
	
	public String firstName;
	public String lastName;
	public int age;
	
	public Person() {
	        super();
	}

	public Person(String id, String firstName, String lastName, int age) {
		super();    
		this.Id = id;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.age = age;
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
