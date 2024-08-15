# Spring Boot 3 Microservices Project

Monorepo project for learning purposes. In progress.

List of services:
 - Product service
 - Inventory Service
 - Order Service

Current Stack:
Spring Boot 3, Java 17, Gradle, MongoDB, PostgreSql, REST API

Common library versions placed to one place at [libs.versions.toml](gradle%2Flibs.versions.toml)

## Run all microservices in parallel
./gradlew bootRun

## Run individually from root

./gradlew :product-service:bootRun
./gradlew :order-service:bootRun
./gradlew :inventory-service:bootRun

# Restapi Documentation

product-service
http://localhost:8080/swagger-ui/index.html#/

order-service
http://localhost:8081/swagger-ui/index.html#/

inventory service
http://localhost:8082/swagger-ui/index.html#/

# PostgreSql commands
Login in terminal**
```sql
psql -U order_user -d order_db
```

**All tables**
```sql
\dt;
```
```sql
SELECT table_name FROM information_schema.tables;
```

**Select - reserved keyword table**
```sql
select * from "order";
```

