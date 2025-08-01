package com.donedeal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalSession {

    private String username;
    private int userId;
    private int itemId;
    private int sellerId;
    private String paymentMethod;
    private String paymentId;

    private String sessionPaymentId;


    private int transactionBuyerId;
    private int transactionSellerId;
    private int transactionItemId;
    private int transactionPrice;
    private  String transactionPaymenetMethod;
    private  String transactionPaymentId;
    private int transactionStatus;

}
