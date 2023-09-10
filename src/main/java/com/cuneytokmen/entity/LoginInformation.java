package com.cuneytokmen.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LoginInformation {

    @Id
    private long id;
    private String username;
    private String password;

}
