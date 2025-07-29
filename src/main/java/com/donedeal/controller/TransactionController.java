package com.donedeal.controller;


import com.donedeal.LocalSession;
import com.donedeal.schema.ItemSchema;
import com.donedeal.schema.TransactionsSchema;
import com.donedeal.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.donedeal.paymentMethod.stripe.StripeResponse;
import com.donedeal.paymentMethod.stripe.ProductRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody ProductInfo productInfo ) {
        StripeResponse stripeResponse = transactionService.checkoutProducts(productInfo.getBuyerId(), productInfo.getSerllerId(), productInfo.getItemId() );
        localSession.setSessionPaymentId(stripeResponse.getSessionId());
//        System.out.println("session payment id is "+localSession.getSessionPaymentId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }

    @GetMapping("/user/get/allBought")
    public List<ItemSchema> getItemsByBuyerId(@RequestBody GetAllBought getAllBought){
        return transactionService.getItemsByBuyerId(getAllBought.getBuyerId());
    }





    // this is for setter and getter
    @Data
//this is for constructor
    @NoArgsConstructor
    @AllArgsConstructor
// use in  DTA ATA? OR JDC? dont new pero parang pang sanitize or centrilize ito eh
    static class ProductInfo{
        private int buyerId;
        private int serllerId;
        private int itemId;
    }

    // this is for setter and getter
    @Data
//this is for constructor
    @NoArgsConstructor
    @AllArgsConstructor
// use in  DTA ATA? OR JDC? dont new pero parang pang sanitize or centrilize ito eh
    static class GetAllBought{
        private int buyerId;
    }


}


