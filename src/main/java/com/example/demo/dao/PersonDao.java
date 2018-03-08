package com.example.demo.dao;

import java.util.List;
import com.example.demo.model.Person;

public interface PersonDao {

	public List<Person> listPersons();

	public void add(Person user);
//		public void update(User user);
//	    public void delete(User user);
	public Person findPersonById(String Id);
//	public Person addPerson(Person person);

}
