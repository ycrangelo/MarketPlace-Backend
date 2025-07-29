package com.donedeal.schema;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "TRANSACTIONS")


//generate a contructor with argument using lombok
@AllArgsConstructor

//generate a contructor without argument using lombok
@NoArgsConstructor
public class TransactionsSchema {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "BUYERID")
    private int buyerId;

    @Column(name = "SELLERID")
    private int sellerId;

    @Column(name = "ITEMID")
    private int itemId;

    @Column(name = "Price")
    private int price;

    @Column(name = "PAYMENTMETHOD")
    //payment method if paypal or idk
    private String paymenetMethod;

    @Column(name = "paymentid")
    private String paymentId;

    @Column(name = "STATUS")
    //1 if sold
    private int Status;
}
