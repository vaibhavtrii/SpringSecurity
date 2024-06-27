package com.springSecurity.springSecurity.SaveCoDTO;

import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.xml.transform.sax.SAXResult;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonUpdateCo {
    //@NonNull
    Long id;
    String name;

    public String getPassword() {
        return password;
    }

    String password;
    String contact;
    @Email
    String email;


    public Long getId() {
        return id;
    }
}
