<h2>Sistema de gerenciamento e cadastro de pessoas - API REST com Spring Boot</h2>

Nesse projeto foi desenvolvido um pequeno sistema de gerenciamento e cadastro de pessoas de uma empresa através de uma API REST, criada com o Spring Boot.

* Setup inicial com Spring Boot Initialzr
* Criação de modelo de dados para o mapeamento de entidades em bancos de dados
* Desenvolvimento das operações de gerenciamento de usuários (CRUD)
* Padrão arquitetural REST
* Deploy na nuvem com Heroku

<hr>
<h3>Tecnologias utilizadas</h3>

* Java 11
* Maven
* Spring Boot (dependências: Spring Boot Dev Tools, Spring Web, Spring Data JPA, Spring Boot Actuator, H2 Database)
* Heroku

<hr>
<h3>Modelo de dados</h3>

![modelo conceitual](https://github.com/yornellas/person-registry-API/blob/main/assets/modelo-dados.png?raw=true)

<hr>
<h3>Para executar o projeto:</h3>
Clone o projeto e digite o seguinte comando no terminal:

```bash
mvn spring-boot:run 
```

Após executar o comando acima, basta abrir o seguinte endereço e visualizar a execução do projeto:

```
http://localhost:8080/api/v1/people
```







