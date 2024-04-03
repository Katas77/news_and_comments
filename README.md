<center><font size="3" face="Georgia"> <h3> "News comments"
</h3></font>
</center>

## Overview:
-  "News comments"The News Comments application is a small console application based on the Representational State Transfe software architecture. 
- It Implements a REST API for a news service


## Features:
- create and manage users,
- create and manage news categories,
- create and manage news,
- create and manage news comments,
- implements news filtering by categories and authors.


## Prerequisites
- Java 17
- Maven (for building the application)
- Spring Boot 3.2.3
- Docker Desktop

## Setup and Installation
- Clone the repository:
- git clone [https://github.com/Katas77]
- Navigate to the project directory:
- cd contacts-application
- Build the application using Maven:
- mvn clean install 
- Run the application:
- For general use:
- - Work with the database occurs through Spring Boot Data JPA and org.postgresql
- - Launch and configure the database via Docker
- - To run using Docker, you need to enter the following commands in the terminal:
- - cd docker   
- - docker-compose up


____

### Usage
- Editing and deleting a news item is permitted only to the user who created it.
- Editing and deleting a comment on a news story is permitted only to the user who created it.
- Returning a list of entities (findAll) occurs using pagination. 
- The exception is the list of comments. list of comments to return only for specific news.
### Contact Management
Contacts are managed through a simple command-line interface. 
Input errors are handled gracefully, with prompts for correct input.

## Technologies used:

- Java
- Spring Boot
- Docker
- Spring Boot Data JPA
- Thymeleaf
- AOP


____
✉ Почта для обратной связи:
<a href="">krp77@mail.ru</a>