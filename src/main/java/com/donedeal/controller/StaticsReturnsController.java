package com.donedeal.controller;

import com.donedeal.paymentMethod.stripe.TransactionReciept;
import com.donedeal.service.TransactionService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticsReturnsController {

    @Autowired
    private TransactionService transactionService;


        @GetMapping("/success")
        public TransactionReciept success() throws StripeException {
//            transactionService.transactionReciept();
            transactionService.postTransaction();
            return transactionService.transactionReciept();
        }

        @GetMapping("/failed")
        public String failed(){
            return "failed";
        }

}
