package com.donedeal.service;

import com.donedeal.LocalSession;
import com.donedeal.TransactionSession;
import com.donedeal.paymentMethod.stripe.TransactionReciept;
import com.donedeal.repository.ItemRepository;
import com.donedeal.repository.TransactionRepository;
import com.donedeal.paymentMethod.stripe.StripeResponse;
import com.donedeal.paymentMethod.stripe.ProductRequest;
import com.donedeal.schema.ItemSchema;
import com.donedeal.schema.TransactionsSchema;
import com.donedeal.schema.UserSchema;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import java.util.Optional;


@Service
public class TransactionService {
    Session session = null;

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LocalSession localSession;

    @Autowired
    private TransactionSession transactionSession;


    @Value("${stripe.secretkey}")
    private String stripeSk;




    public StripeResponse checkoutProducts(int buyerId, int serllerId, int itemId) {

        Optional<ItemSchema> itemSchema = itemRepository.findById(itemId);
        String itemName = "";
        double itemPrice = 0;
        transactionSession.setTransactionItemId(itemId);
        transactionSession.setTransactionSellerId(serllerId);
        transactionSession.setTransactionBuyerId(buyerId);

        if(itemSchema.isPresent()){
            ItemSchema itemDetailes = itemSchema.get();
            itemName = itemDetailes.getItemName();
            itemPrice = itemDetailes.getItemPrice();
            transactionSession.setTransactionPrice((int) itemPrice);
            transactionSession.setTransactionStatus(1);


            // pass the  apikey
            Stripe.apiKey = stripeSk;

//        System.out.println("this is the api key "+ stripeSk);

            // Create a PaymentIntent with the order amount and currency
            SessionCreateParams.LineItem.PriceData.ProductData productData =
                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName(itemName)
                            .build();

            // Create new line item with the above product data and associated price
            SessionCreateParams.LineItem.PriceData priceData =
                    SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("USD")
                            .setUnitAmount((long) itemPrice)
                            .setProductData(productData)
                            .build();

            // Create new line item with the above price data
            SessionCreateParams.LineItem lineItem =
                    SessionCreateParams
                            .LineItem.builder()
                            .setQuantity(1L)
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
//            System.out.println("this is the api key "+ stripeSk);
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
            return null;


    }






    public TransactionReciept transactionReciept() throws StripeException {

        Session retrievedSession = Session.retrieve(session.getId());

        String paymentIntentId = retrievedSession.getPaymentIntent();

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        transactionSession.setTransactionPaymentId(paymentIntent.getClientSecret());
        System.out.println("this is transactionPaymentId: " + transactionSession.getTransactionPaymentId());
        localSession.setPaymentMethod("stripe");
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

    public TransactionsSchema postTransaction() throws StripeException {
//        transactionsSchema.setPaymenetMethod("stripe");
        updateStatusItem(transactionSession.getTransactionItemId());
        // Convert TransactionSession to TransactionsSchema
        TransactionsSchema transaction = new TransactionsSchema();
        transaction.setBuyerId(transactionSession.getTransactionBuyerId());
        transaction.setSellerId(transactionSession.getTransactionSellerId());
        transaction.setItemId(transactionSession.getTransactionItemId());
        transaction.setPrice(transactionSession.getTransactionPrice());
        transaction.setPaymenetMethod("stripe");
//        String paymentID = transactionSession.getTransactionPaymentId();
//        System.out.println("this is paymentId IN POST TRANSACTION: " + paymentID);

        Session retrievedSession = Session.retrieve(session.getId());

        String paymentIntentId = retrievedSession.getPaymentIntent();

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        
        transaction.setPaymentId(paymentIntent.getClientSecret());
        transaction.setStatus(transactionSession.getTransactionStatus());

        return transactionRepository.save(transaction);
    }

    public void updateStatusItem(int id){
        // Find the entity by its ID
        ItemSchema item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        // Update the specific value
        item.setStatus(0);

        // Save the updated entity back to the database
        itemRepository.save(item);
    }


}
