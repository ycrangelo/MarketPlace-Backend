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
public class TransactionSession {

    private int transactionBuyerId;
    private int transactionSellerId;
    private int transactionItemId;
    private int transactionPrice;
    private  String transactionPaymenetMethod;
    private  String transactionPaymentId;
    private int transactionStatus;

}
