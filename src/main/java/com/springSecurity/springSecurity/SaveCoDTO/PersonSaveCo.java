package com.springSecurity.springSecurity.SaveCoDTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class PersonSaveCo {
    @NonNull
    private String name;
    @NonNull
    private String password;
    private String contact;
    @NonNull
    private String email;
    @NonNull
    private String roles;


    public @NonNull String getRoles() {
        return roles;
    }
}
