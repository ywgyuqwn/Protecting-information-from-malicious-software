package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    // Внедрение через конструктор
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // CREATE
    public Person createPerson(Person person) {
        Optional<Person> existingPerson = personRepository.findByNameAndAge(
                person.getName(), person.getAge()
        );

        if (existingPerson.isPresent()) {
            return existingPerson.get(); // Если человек уже есть, возвращаем его
        }

        return personRepository.save(person);
    }

    // READ: получить всех
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // READ: получить по ID
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    // UPDATE: обновить
    public Person updatePerson(Long id, Person newData) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();
            // обновляем поля
            existingPerson.setName(newData.getName());
            existingPerson.setAge(newData.getAge());
            return personRepository.save(existingPerson);
        }
        return null; // или бросить исключение
    }

    // DELETE
    public boolean deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

}
