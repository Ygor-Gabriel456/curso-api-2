package br.com.erudio.restwithspringbootandjavaerudio.services;

import br.com.erudio.restwithspringbootandjavaerudio.Repository.PersonRepository;
import br.com.erudio.restwithspringbootandjavaerudio.exception.ResourceNotFoundException;
import br.com.erudio.restwithspringbootandjavaerudio.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PersonServices {

  @Autowired
  PersonRepository repository;


  private Logger logger = Logger.getLogger(PersonServices.class.getName());


  public Person findById(Long id){

    logger.info("Finding one person!");
    Person person =  new Person();

    return repository.findById(id)
      .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
  }

  public Person findByfirstName(String firstName) {
    return repository.findByfirstName(firstName);
  }
  
  public List<Person> findAll(){
    return repository.findAll();
  }

  public Person create(Person person){
    logger.info("Creating one person!");
    return repository.save(person);
  }

  public Person update(Person person){

    logger.info("updating one person!");

    var entity =  repository.findById(person.getId())
      .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    return repository.save(person);
  }

  public void delete(Long id){

    logger.info("Deleting one person!");

    var entity =  repository.findById(id)
      .orElseThrow(()-> new ResourceNotFoundException("No records foung for this ID"));
    repository.delete(entity);
  }


 public List<Person> findBylastName( String lastName){
    return repository.findBylastName(lastName).stream().map(Person::create).collect(Collectors.toList());
 }
}
