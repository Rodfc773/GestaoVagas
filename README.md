# Gestão de Vagas

### Objetivo

Esse projeto tem como obejtivo a criação de um CRUD de gestão de vagas com o foco
em aprendizado sobre O framework Spring Boot, utilizando-se de autenticação com Spring Security
,Spring Data, JPA e Hibernate com integração a documentação do Swagger.

### Tecnologias usadas

* Java 17
* Spring Boot
* Banco de dados MySQL
* Spring Data, JPA e Hibernate
* Swagger

### Como utilizar

1. É nescessário rodar o arquivo do `docker-compose.yml` para subir a imagem do banco de dados utilizado, o qual é o MySQL
2. É nescessário a criar um arquivo dentro da pasta `resource` chamada de `application.properties` contendo as seguintes informações:

```
spring.application.name=GestaoVagas
spring.datasource.url=DATABASE_URL //seguindo o padrão estabelecido pel JDBC
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update

security.token.secret =YOUR_SECRET
security.token.secret.candidate=YOUR_SECRET

springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
```
3. Na CLI do Maven rode o seguinte comando para instalar as dependências do projeto: `mvn clean install`

### Documentação

Papa acessar a documentação da API basta navegar até `localhost:8080/swagger-ui/index.html`