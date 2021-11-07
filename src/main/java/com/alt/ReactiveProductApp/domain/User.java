package com.alt.ReactiveProductApp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "user")
@Data
public class User implements Serializable {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String role;

}
