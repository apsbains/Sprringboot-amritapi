package com.example.demo.dao;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	private Environment env;

	//private static final String COLLECTION_NAME = env.getProperty("database.collection.name");
	private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);
	
	public PersonDaoImpl(){
		//this.add(new Person());
		
	}
	public List<Person> listPersons() {
        
        return mongoTemplate.findAll(Person.class, env.getProperty("database.collection.name"));
	}


	public Person findPersonById(String Id) {
		
		Query query = new Query(Criteria.where("_id").is(Id));
	    return mongoTemplate.findOne(query, Person.class, env.getProperty("database.collection.name"));
		
		
	}
	
	public void add(Person user) {
		 if (!mongoTemplate.collectionExists(Person.class)) {
	            mongoTemplate.createCollection(Person.class);

	        }
	        //user.setId(UUID.randomUUID().toString());
	        mongoTemplate.insert(user, env.getProperty("database.collection.name"));
	
		
	}
	

}
