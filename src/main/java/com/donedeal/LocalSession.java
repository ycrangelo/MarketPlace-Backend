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

}
