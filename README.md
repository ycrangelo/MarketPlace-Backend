#  Simple MarketPlace-Backend (P2P Model)

A peer-to-peer (P2P) marketplace backend where users act as buyers or sellers and negotiate transactions directly—built with Java, Spring Boot, and MySQL.

##  Features

- **P2P Transactions**: Buyers and sellers interact directly—no central product inventory.
- **User Roles**: Register as buyer or seller. Admin panel for moderation (optional).
- **Offer/Bid System ( to be implement )**: Sellers post offers, buyers post bids—matches are negotiated peer-to-peer.
- **Transaction History**: Records of completed deals.
- **Notifications ( to be implement )**: Real-time alerts for new offers, bids, and transaction updates.
- **Robust Error Handling**: Standard HTTP status codes and clear JSON error messages.

## Technology Stack

| Layer          | Technology               |
|----------------|--------------------------|
| Language       | Java                     |
| Framework      | Spring Boot              |
| Database       | MySQL                    |
| API            | RESTful (Spring MVC)     |
| Security       | Springboot Security      |
| Build Tool     | Maven                    |
| Container      | Docker (to be emplement) |

## Marketplace Sequence Diagram
![b1b1ee3b-6056-4813-9239-488550424435](https://github.com/user-attachments/assets/5b0d4ac4-32f9-4e33-a69d-c1a8dc4891e8)



## API Endpoints

### User Authentication

| Method | Endpoint          | Description                          | Request Body Example                              |
|--------|-------------------|--------------------------------------|---------------------------------------------------|
| POST   | `/user/signup`    | Register a new user                  | `{"username":"string", "password":"string", ...}` |
| POST   | `/user/signin`    | Login existing user                  | `{"username":"string", "password":"string"}`      |

### Item Management

| Method | Endpoint          | Description                          |
|--------|-------------------|--------------------------------------|
| POST   | `/post/item`      | Post (add) a new item (as seller)    |
| GET    | `/get/allItems`   | Retrieve all available items         |

### Transaction Processing

| Method | Endpoint                     | Description                                                                 | Request Body Example                          |
|--------|------------------------------|-----------------------------------------------------------------------------|-----------------------------------------------|
| POST   | `/product/payment/stripe`    | Initiate Stripe payment session                                             | `{"buyerId":1, "serllerId":2, "itemId":3}`    |
| GET    | `/success`                   | Successful payment callback (updates transaction status)                    | -                                             |
| GET    | `/failed`                    | Failed payment callback                                                     | -                                              |
| GET    | `/user/get/allBought`        | Retrieve all items purchased by a buyer                                     | `{"buyerId":1}`    

##  How to Download & Use

### 1. Download the Project
Clone this repository to your local machine:
```bash
git clone https://github.com/ycrangelo/MarketPlace-Backend.git
cd MarketPlace-Backend
```

## Configure application properties:
```bash
spring.application.name={your application name}
spring.datasource.url=jdbc:mysql://localhost:3306/{your database}
spring.datasource.username={your db username}
spring.datasource.password={your db password}
myapp.username={your username}


#stripe

stripe.secretkey = {your stripe sk}
stripe.pk = {your stripe pk}


#this is for the config of the databse
spring.jpa.show-sql=true
# for the schema
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


logging.level.org.springframework.security=DEBUG
```
## Run the application:
```bash
# Using Maven
./mvnw spring-boot:run

# Using Gradle(if youre using gradle)
./gradlew bootRun
```


