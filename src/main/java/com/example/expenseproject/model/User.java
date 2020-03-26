package com.example.expenseproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor // creates a constructor with all args
@NoArgsConstructor // creates a No Args Constructor - required in JPA
@Entity // JPA knows that we will build a table in the database
@Data // Lombok creates Getters and Setters
@Table(name="user")
public class User {

    @Id // primary key
    private Long id;
    private String name;
    private String email;

}
