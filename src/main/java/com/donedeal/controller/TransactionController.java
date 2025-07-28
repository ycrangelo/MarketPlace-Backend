package com.donedeal.controller;


import com.donedeal.LocalSession;
import com.donedeal.schema.TransactionsSchema;
import com.donedeal.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.donedeal.paymentMethod.stripe.StripeResponse;
import com.donedeal.paymentMethod.stripe.ProductRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private LocalSession localSession;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @RequestMapping("/product/payment/stripe")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody ProductRequest productRequest) {
        StripeResponse stripeResponse = transactionService.checkoutProducts(productRequest);
        localSession.setSessionPaymentId(stripeResponse.getSessionId());
//        System.out.println("session payment id is "+localSession.getSessionPaymentId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }

    @PostMapping("/product/payment/transaction/save")
    public TransactionsSchema postTransaction(@RequestBody TransactionsSchema transactionsSchema) {

        return transactionService.postTransaction(transactionsSchema);
    }


}


