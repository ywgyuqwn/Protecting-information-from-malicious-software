package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hobbies")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hobbyName;

    // ManyToMany с Person
    @ManyToMany(mappedBy = "hobbies")
    private Set<Person> persons = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getHobbyName() {
        return hobbyName;
    }
    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public Set<Person> getPersons() {
        return persons;
    }
    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
