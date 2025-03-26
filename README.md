# Gestão de Vagas

### Objetivo

Esse projeto visa a criação de um CRUD de uma aplicação de vagas de emprego com o foco
em aprendizado sobre O *framework* Spring Boot, utilizando-se de autenticação com Spring Security, Spring Data, 
JPA e Hibernate, Testes utilizando JUnit e Mockito com integração a documentação do Swagger.

### Tecnologias usadas

* Java 17
* Spring Boot
* Banco de dados MySQL
* Spring Data, JPA e Hibernate
* Swagger
* JUnit
* Mockito
### Como utilizar

1. Clone o repositorio com o comando ```git clone```
2. Rode o arquivo do `docker-compose.yml` para subir a imagem do docker do banco de dados utilizado.
3. É nescessário a criar um arquivo dentro da pasta `resource` chamada de `application.properties` contendo as seguintes informações:

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
4. Na CLI do Maven rode o seguinte comando para instalar as dependências do projeto: `mvn clean install`, caso
não possua maven instalado é recomendado que baixe, ou usar outro tipo de configuração de build de aplicação como
o gradle.

5. Acesse o seguinte *link*: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
para poder ter um entendimento melhor da documentação de cada rota da aplicação.
6. Faça requisições **HTTP** dos tipos **GET, POST** para a **URL** específica na documentação para cadastrar e obter
dados.

### Futuros Objetivos

Considerando obter um máximo aprendizado e fixação dos conceitos, após a implementação dos testes
tanto unitários como de integração para as rotas já existentes, será implementado os outros metódos HTTP como
**PUT, PATCH e DELETE** assim como um sistema de envio de _e-mail_ para os _e-mails_ cadastrados.
