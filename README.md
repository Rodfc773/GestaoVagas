# Gestão de Vagas

## PT-BR
### Objetivo :dart:

Esse projeto visa a criação de um CRUD de uma aplicação de vagas de emprego com o foco
em aprendizado sobre O *"framework"* Spring Boot, utilizando-se de autenticação com Spring Security, Spring Data, 
JPA e Hibernate, Testes utilizando JUnit e Mockito com integração a documentação do Swagger.

### Tecnologias usadas :computer:

* Java 17 :hotsprings:
* Spring Boot
* Banco de dados MySQL e PostgreSQL
* Spring Data, JPA e Hibernate
* Swagger
* JUnit e Mockito
* Grafana e Prometheus
* Docker
* Maven

### Como utilizar :page_with_curl:

1. Clone o repositorio com o comando ```git clone```
2. Rode o arquivo do `docker-compose.yml` para subir a imagem do docker do banco de dados utilizado.
3. Preencha as variáveis do arquivo `application-example.properties` no arquivo `application.properties`
4. Na CLI do Maven rode o seguinte comando para instalar as dependências do projeto: `mvn clean install`, caso
não possua maven instalado é recomendado que baixe, ou usar outro tipo de configuração de build de aplicação como
o gradle.

5. Acesse o seguinte *link*: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
para poder ter um entendimento melhor da documentação de cada rota da aplicação.
6. Faça requisições **HTTP** dos tipos **GET, POST** para a **URL** específica na documentação para cadastrar e obter
dados.

### Futuros Objetivos :clipboard:

- Implementação de recuperação de senhas para os usuários
- Implementação do serviço de envio de e-mails.
- Implementação de rotas para Edição/Remoção de conteúdo.
- Permitir envio de arquivos como imagens para perfis de usuários e currículos.

## EN

### Goals :dart:

This project aims a CRUD application creation about a website focused on hire (Enterprises) and 
applying for jobs (Candidates) using the Spring Boot framework for learning purposes.

### Technologies used :computer:

- Java 17
- Spring Boot
- PostgreSQL
- Spring Data, JPA and Hibernate
- Spring Security
- Swagger
- Junit and Mockito for unit and integration tests
- Grafana and Prometheus to observability
- Docker,AWS and Render
- Maven


### How to use :page_with_curl:

1. Do a `git clone` to clone the repository
2. Run the `docker-compose.yml` file to put up the principal services for the application working and the database used for the application.

3. Do a copy of the environment variables of `application-example.properties` to `application.properties` and rewrite those values.

4. At the CLI of your terminal run the **Maven** command `mvn clean install` to initialize the application. If you don't have Maven installed is recommended to install for the application work as expected.

5. The documentation can be found at the follow URL: http://localhost:8080/swagger-ui/index.html note that the `8080` is the default port tha Spring Boot makes available;
6. Using Postman or Insomnia do HTTP Requests to the URLs specified in the documentation, note that some routes need authentication token.


### Future steps :clipboard:

- Implement a functionality to recover passwords.
- Implement the functionality to send emails for candidates and enterprises.
- Implement new routes for updating profiles and content.
- Implement a functionality to permit the sending of images (for profiles) and Resumes.
