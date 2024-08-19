# Spring Boot 3 Microservices Project

Monorepo project for learning purposes. In progress.

List of services:
 - Product service
 - Inventory Service
 - Order Service

Current Stack:
Spring Boot 3, Java 17, Gradle, MongoDB, PostgreSql, REST API, TestContainers

Common library versions placed to one place at [libs.versions.toml](gradle%2Flibs.versions.toml)

## Start Microservices

### In Docker
```shell
./start_services.sh
```
```shell
./stop_services.sh
```

You may start service individually using
```shell
docker compose up
```


### Localy
```shell
./gradlew bootRun
```
#### Each
```shell
./gradlew :product-service:bootRun
```

```shell
./gradlew :order-service:bootRun
```

```shell
./gradlew :inventory-service:bootRun
```

# OpenAPI Documentation

Product service (MongoDB)
- http://localhost:8080/swagger-ui/index.html#/
- http://localhost:8080/v3/api-docs

Order Service (PostgreSQL)
- http://localhost:8081/swagger-ui/index.html#/
- http://localhost:8081/v3/api-docs

Inventory Service (PostgreSQL)
- http://localhost:8082/swagger-ui/index.html#/
- http://localhost:8082/v3/api-docs

# Useful PostgreSQL Commands
**Login**
```sql
psql -U product_user -d product_db
```
```sql
psql -U order_user -d order_db
```
```sql
psql -U inventory_user -d inventory_db
```

**Tables Info**
```sql
\dt;
```
```sql
SELECT table_name FROM information_schema.tables;
```

**Select - reserved keyword table**
```sql
select * from "order";
select * from order_line_items;
select * from order_order_line_items;
select * from inventory;
...
```

# Misc
```shell
./gradlew test
```
### Each
```shell
./gradlew :product-service:test
```

```shell
./gradlew :order-service:test
```

```shell
./gradlew :inventory-service:test
```

``
docker compose up
``