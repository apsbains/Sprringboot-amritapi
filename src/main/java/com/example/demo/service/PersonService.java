package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Person;

public interface PersonService {

	public Person getPerson(String Id);
	public List <Person> getAll();
	public void add(Person person);	
	
}
