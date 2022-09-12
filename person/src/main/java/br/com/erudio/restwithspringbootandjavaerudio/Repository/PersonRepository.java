package br.com.erudio.restwithspringbootandjavaerudio.Repository;

import br.com.erudio.restwithspringbootandjavaerudio.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


  Person findByfirstName(String firstName);

  List<Person>findBylastName(String lastName);
}
