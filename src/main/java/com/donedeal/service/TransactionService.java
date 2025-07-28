package com.donedeal.service;

import com.donedeal.paymentMethod.stripe.TransactionReciept;
import com.donedeal.repository.TransactionRepository;
import com.donedeal.paymentMethod.stripe.StripeResponse;
import com.donedeal.paymentMethod.stripe.ProductRequest;
import com.donedeal.schema.TransactionsSchema;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {
    Session session = null;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${stripe.secretkey}")
    private String stripeSk;




    public StripeResponse checkoutProducts(ProductRequest productRequest) {
        // pass the  apikey
        Stripe.apiKey = stripeSk;

        System.out.println("this is the api key "+ stripeSk);

        // Create a PaymentIntent with the order amount and currency
        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(productRequest.getName())
                        .build();

        // Create new line item with the above product data and associated price
        SessionCreateParams.LineItem.PriceData priceData =
                SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency(productRequest.getCurrency() != null ? productRequest.getCurrency() : "USD")
                        .setUnitAmount(productRequest.getAmount())
                        .setProductData(productData)
                        .build();

        // Create new line item with the above price data
        SessionCreateParams.LineItem lineItem =
                SessionCreateParams
                        .LineItem.builder()
                        .setQuantity(productRequest.getQuantity())
                        .setPriceData(priceData)
                        .build();

        // Create new session with the line items
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:8080/success")
                        .setCancelUrl("http://localhost:8080/cancel")
                        .addLineItem(lineItem)
                        .build();

        // Create new session
        try {
            session = Session.create(params);
            System.out.println("this is the api key "+ stripeSk);
        }catch (StripeException e) {
            e.printStackTrace(); // log it for debugging
            throw new RuntimeException("Stripe session creation failed: " + e.getMessage());

        }

        return StripeResponse
                .builder()
                .status("SUCCESS")
                .message("Payment session created ")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
    }

    public TransactionReciept transactionReciept() throws StripeException {

        Session retrievedSession = Session.retrieve(session.getId());

        String paymentIntentId = retrievedSession.getPaymentIntent();

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);


        return TransactionReciept
                .builder()
                .transactionId(paymentIntentId)
                .created(String.valueOf(paymentIntent.getCreated()))
                .client_secret(paymentIntent.getClientSecret())
                .status(paymentIntent.getStatus())
                .customer(paymentIntent.getCustomer())
                .receiptEmail(paymentIntent.getReceiptEmail())
                .balanceTransaction(String.valueOf(paymentIntent.getAmount()))
                .build();

    }

    public TransactionsSchema postTransaction(TransactionsSchema transactionsSchema) {
        transactionsSchema.setPaymenetMethod("stripe");
        return transactionRepository.save(transactionsSchema);
    }
}
