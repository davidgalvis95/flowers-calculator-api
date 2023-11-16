# Flowers Calculator API

### Description:

This is an API developed in java - spring boot that takes care of the calculations regarding the products offered by different companies, these calculations include the calculation of freight for the products offered by a company, as well as the adjusted price that can be offered to a customer and also some codified names for different products.

### How to use it:

* Download the repo from `https://github.com/davidgalvis95/flowers-calculator-api`
* Stand over the root directory of the project and run `mvn clean install` 
* Stand over the root of the project and run `docker-compose up` to start up the postgres db.
* Stand over the root directory of the project and run `java -jar target/myapplication-0.0.1-SNAPSHOT.jar` to run the project.

### Curl requests examples to the project endpoints:

* Get Freight Cost By Company: `curl --location 'http://localhost:9000/api/v1/products/freight-cost?companyId=3'`
* Get Products Price For Customer: `curl --location 'http://localhost:9000/api/v1/products/price?customerId=1'`
* Get Product Name Codified: `curl --location 'http://localhost:9000/api/v1/products/code?productId=3'`



