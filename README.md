# CRUD-SPRING
Crud y Seguridad en Spring Boot

## CONFIGURACION DE BASE DE DATOS
Es necesario la creacion de la base de datos en el sistema gestor para que se creen las tablas por ORM
Se configura el archivo `application.properties` donde se agregan las credenciales a la base de datos

spring.datasource.url=jdbc:postgresql://localhost:5432/basededatosPostgres
spring.datasource.username=usuarioPostgres
spring.datasource.password=contraseñaPostgres
