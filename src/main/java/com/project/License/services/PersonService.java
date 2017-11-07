package com.project.License.services;
import com.project.License.models.Person;
import com.project.License.repositories.PersonRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> allPersons() {
        return personRepository.findAll();
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }
    
    public Person getPerson(Long id) {
        return personRepository.findOne(id);
    }
}