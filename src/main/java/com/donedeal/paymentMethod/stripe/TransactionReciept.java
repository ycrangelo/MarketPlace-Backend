package com.donedeal.paymentMethod.stripe;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionReciept {

    private String transactionId;
    private String created;
    private String client_secret;
    private String balanceTransaction;
    private String status;
    private String receiptEmail;
    private String customer;
}
