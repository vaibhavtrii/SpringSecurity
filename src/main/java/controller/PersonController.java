package controller;


import POJO.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import repo.PersonRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepo personRepo;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public Object getData() {
        return "Fuck You Yatin!";
    }

    @PostMapping("/save")
    public Object savePerson(@RequestBody Person person) {

        Optional<Person> person1 = personRepo.findByEmail(person.getEmail());
        if (!person1.isEmpty())
            return "Email exist";
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepo.save(person);
        return "Person Saved";
    }

    @GetMapping("/get/{id}")
    public Object getPerson(Long id) {
        Optional<Person> person1 = personRepo.findById(id);
        if (!person1.isPresent())
            return "Product does not exist";
        return person1.get();
    }

    @GetMapping("/get/all")
    public List<Person> getAllPerson() {
        return personRepo.findAll();
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(long id) {
        Optional<Person> person = personRepo.findById(id);
        if (!person.isPresent())
            return "Product does not exist";
        personRepo.deleteById(id);
        return "Successfully Deleted";
    }
}
