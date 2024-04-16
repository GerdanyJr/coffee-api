# Coffee Api #

This project is a back-end made with Java and Spring Boot for a fictional coffee shop.

##  Model and Entities ##

The model layer of this project is constituted by 2 main entities, called Coffee and Category:

### Category ### 

- **id**: Integer
- **name**: String

### Coffee ###

- **id**: Integer 
- **image**: String 
- **name**: String 
- **description**: String 
- **price**: BigDecimal 
- **categories**: List<Category>

--- 

Coffee has a many-to-many relationship with Category. Therefore, the database has an additional table called "coffee_categories", this table have 2 attributes:

- **Categories_id**: Integer
- **Coffee_id**: Integer

## Tools and Dependencies ##

- spring-boot-starter-cache
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- spring-cloud-starter-openfeign
- spring-boot-devtools (optional)
- h2 (runtime database)

## Use instructions ##

The project has a Postman collection attached with all the endpoints.
