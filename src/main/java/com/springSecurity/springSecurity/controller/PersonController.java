package com.springSecurity.springSecurity.controller;

import com.springSecurity.springSecurity.SaveCoDTO.PersonUpdateCo;
import com.springSecurity.springSecurity.config.UtilConfigurer;
import com.springSecurity.springSecurity.POJO.GrantedAuthorityImpl;
import com.springSecurity.springSecurity.POJO.Person;
import com.springSecurity.springSecurity.SaveCoDTO.PersonSaveCo;
import com.springSecurity.springSecurity.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converters;
import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepo personRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @GetMapping("/")
    public Object getData() {
        return "Fuck You Yatin!";
    }

    @PostMapping("/save")
    public Object savePerson(@RequestBody PersonSaveCo personSaveCo) {

        Optional<Person> person1 = personRepo.findByEmail(personSaveCo.getEmail());
        if (!person1.isEmpty())
            return "Email exist";

        Person person = modelMapper.map(personSaveCo,Person.class);
        GrantedAuthorityImpl grantedAuthority = new GrantedAuthorityImpl();
        grantedAuthority.setRoles(personSaveCo.getRoles());
        person.setGrantedAuthorities(Collections.singletonList(grantedAuthority));
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepo.save(person);
        return "Person Saved";
    }

    @GetMapping("/get/{id}")
    public Object getPerson(@PathVariable  Long id) {
        Optional<Person> person1 = personRepo.findById(id);
        if (!person1.isPresent())
            return "Product does not exist";
        return person1.get();
    }

    @GetMapping("/get/all")
    public List<Person> getAllPerson() {
        return personRepo.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable long id) {
        Optional<Person> person = personRepo.findById(id);
        if (!person.isPresent())
            return "Product does not exist";
        personRepo.deleteById(id);
        return "Successfully Deleted";
    }
    @PutMapping("/update")
    public String updatePerson(@RequestBody PersonUpdateCo personUpdateCo){
        Optional<Person> personUpdateCo1 =personRepo.findById(personUpdateCo.getId());
        if(personUpdateCo1.isEmpty())
            return "Person Does not exist";
        Person person = personUpdateCo1.get();
        if(personUpdateCo.getPassword() != null)
            personUpdateCo.setPassword(passwordEncoder.encode(personUpdateCo.getPassword()));

        modelMapper.map(personUpdateCo,Person.class);
        personRepo.save(person);
        return "Updated";
    }
}
