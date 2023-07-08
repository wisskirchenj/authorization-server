# Cluster-Authorization-Service

Spring Boot application, that implements a Authorization server for our Kubernetes cluster.

## Technology / External Libraries

- Java 20
- Spring Boot 3.1.1 (with Spring Security 6.1.1)
- Spring Authorization Server 1.1.1
- Spring Boot 3.1.1 (with JPA)
- Postgres-Database
- Spring AOT native image on GraalVM (Liberica 17.0.7)
- Lombok
- Gradle 8.2

## Program description

Native image app based on new Spring-Oauth2-AuthorizationServer, that runs in our kubernetes cluster
and provides logged in users via OIDC with JWT tokens. The UserDetailsService is provided with a JPA-repository,
that connects to a `users` database accessed by the postgres-service.
The OIDC-login is triggered by a gateway inside the cluster (Spring Cloud Gateway).

## Project status

Project started on 26.06.23

## Progress

26.06.23 Initial setup with admin-client on client-credentials wotkflow with basic authentication

01.07.23 Add persistent User management on base of Spring Boot Web-MVC / Spring JPA. Integration
testing.

02.07.23 Remove RegisterController (transferred to gateway). Authorization server now uses UserDetailsService
based on the users-database (postgres-service). It connects via a JPA-Repository.