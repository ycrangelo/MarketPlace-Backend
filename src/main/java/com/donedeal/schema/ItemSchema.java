package com.donedeal.schema;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ITEM")


//generate a contructor with argument using lombok
@AllArgsConstructor

//generate a contructor without argument using lombok
@NoArgsConstructor
public class ItemSchema {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "ITEMNAME")
    private String itemName;

    @Column(name = "ITEMDESCRIPTION")
    private String itemDescription;

    @Column(name = "ITEMPRICE")
    private double itemPrice;

    @Column(name = "STATUS")
    //false if posted/binebenta
    //true if bought na, tama ba bought? AHAHAH
    private int status;


    @Column(name = "SELLERID")
    private int sellerId;
}
