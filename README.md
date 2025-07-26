#  MarketPlace-Backend (P2P Model) Ongoing

A peer-to-peer (P2P) marketplace backend where users act as buyers or sellers and negotiate transactions directly—built with Java, Spring Boot, and MySQL.

##  Features

- **P2P Transactions**: Buyers and sellers interact directly—no central product inventory.
- **User Roles**: Register as buyer or seller. Admin panel for moderation (optional).
- **Offer/Bid System**: Sellers post offers, buyers post bids—matches are negotiated peer-to-peer.
- **Transaction History**: Records of completed deals.
- **Notifications**: Real-time alerts for new offers, bids, and transaction updates.
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

##  How to Download & Use

### 1. Download the Project
Clone this repository to your local machine:
```bash
git clone https://github.com/ycrangelo/MarketPlace-Backend.git
cd MarketPlace-Backend
```

## Configure application properties:
```bash
# src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/marketplace_p2p
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
## Run the application:
```bash
# Using Maven
./mvnw spring-boot:run

# Using Gradle(if youre using gradle)
./gradlew bootRun
```


