package com.donedeal.schema;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//use Entity if it is a table or schema
@Entity
@Data
//used for the table
@Table(name = "USER")

//generate a contructor with argument using lombok
@AllArgsConstructor

//generate a contructor without argument using lombok
@NoArgsConstructor
public class UserSchema {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    // 0 seller
    // 1 buyer
    private int role;


}
