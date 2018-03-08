package com.example.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.model.PersonNotFoundException;

import com.example.demo.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@ExposesResourceFor(Person.class)
@RequestMapping(value= "/persons", produces = "application/json")
@Api(value="persons", description="Operations pertaining in Persons")
public class PersonController {

	private static final Logger logger = LogManager.getLogger(PersonController.class);
	
	@Autowired
	private PersonService psSvc;
	
    @Autowired
    EntityLinks entityLinks;
	
	@RequestMapping(value= "/all", method = RequestMethod.GET)	
	@ApiOperation(value = "View a list of available persons", response = Person.class)
	public @ResponseBody Resources<Resource<Person>> getAll() {
		return personToResource(psSvc.getAll());
	}
	
	@RequestMapping(value = "/withid", method = RequestMethod.GET)	
	@ApiOperation(value = "View a Person with ID", response = Person.class)
	public @ResponseBody Resource<Person> getPerson(@RequestParam(value="id", defaultValue="1") String Id) {
	
		Person p = psSvc.getPerson(Id);
		
		if(p == null )
			throw new PersonNotFoundException("No person found with ( Id: " + Id + " )");
		
		return personToResource(p);
						
		/*Person p = psSvc.getPerson(Id);
		
		if (p == null)
		      throw new PersonNotFoundException("No person with that " + Id);
		
		
		//return  personToResource(p);
		return p;
		*/
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST	)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Add two persons with id 1 & 2")
	public void add() {
		Person p1 = new Person();
		p1.setId("1");
		p1.setAge(38);
		p1.setFirstName("Amrit Pal");
		p1.setLastName("Singh");		
		psSvc.add(p1);
		
		Person p2 = new Person();
		p2.setId("2");
		p2.setAge(33);
		p2.setFirstName("Ramanpreet Kaur");
		p2.setLastName("Kaur");
		psSvc.add(p2);
	}
	
	@RequestMapping(value = "/addPerson", method = RequestMethod.POST, consumes = "application/json")
	@ApiOperation(value = "Add person")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		Person p = new Person(person.getId(), person.getFirstName(), person.getLastName(), person.getAge());
		logger.info("Id " + p.getId() + " name " + p.getFirstName());
		psSvc.add(p);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	  private Resources<Resource<Person>> personToResource(List<Person> persons) {

	        Link selfLink = linkTo(methodOn(PersonController.class).getAll()).withSelfRel();

	        List<Resource<Person>> personResources = persons.stream().map(person -> personToResource(person)).collect(Collectors.toList());

	        return new Resources<>(personResources, selfLink);

	    }

	    private Resource<Person> personToResource(Person person) {
	        Link selfLink   = linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel();

	        //Link allInvoiceLink = entityLinks.linkToCollectionResource(Person.class).withRel("all-persons");
	        Link invoiceLink = linkTo(methodOn(PersonController.class).getPerson(person.getId())).withRel("person");

	        //return new Resource<>(person, selfLink,  invoiceLink, allInvoiceLink);
	        return new Resource<>(person, selfLink,  invoiceLink);	
	    }
}
