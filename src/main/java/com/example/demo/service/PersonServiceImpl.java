package com.example.demo.service;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.dao.PersonDao;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonDao personDao;
    
	public Hashtable<String, Person> persons = new Hashtable<String, Person>(); 
	
	public PersonServiceImpl() {
	
	}
	public Person getPerson(String Id) {
		return personDao.findPersonById(Id);
	}
	
	public List<Person> getAll(){
		return personDao.listPersons();
	}
	
	public void add(Person person) {
		personDao.add(person);
	}
	


}
