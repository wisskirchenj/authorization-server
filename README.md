# Cluster-Authorization-Service

Spring Boot application, that implements a Authorization server for our Kubernetes cluster.

## Technology / External Libraries

- Java 20
- Spring Boot 3.1.1 (with Spring Security 6.1.1 and Oauth2Server 1.1.1)
- Spring AOT native image on GraalVM
- Lombok
- Gradle 8.1.1

## Program description

Native image app based on new Spring-Oauth2-AuthorizationServer, that is supposed to run in our kubernetes cluster
and provides logged in users via OIDC with JWT tokens.

## Project status

Project started on 26.06.23

## Progress

26.06.23 Initial setup with admin-client on client-credentials wotkflow with basic authentication
