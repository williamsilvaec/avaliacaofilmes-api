<center>
  <p align="center">
    <img src="https://icon-library.com/images/java-icon-png/java-icon-png-15.jpg"  width="150" />
    <img src="/src/main/resources/static/spring-boot-logo.png" alt="Spring Boot Logo" width="100" >
  </p>  
  <h1 align="center">🚀 Microserviço: Avaliação de filmes indicados e vencedores
da categoria Pior Filme do Golden Raspberry Awards com Java e Spring boot 3</h1>
  <p align="center">
    Microserviço referente ao backend da Avaliação de filmes indicados e vencedores da categoria Pior Filme<br />
    Utilizando Spring Boot 3, Banco de dados H2 (embarcado) e as boas práticas atuais de mercado
  </p>
</center>
<br />

## Ferramentas necessárias

- JDK 21
- IDE de sua preferência
- Docker

## Como executar?

1. Clonar o repositório:
```sh
git clone https://github.com/williamsilvaec/avaliacaofilmes-api.git
```

2. Subir a aplicação localmente com Docker:
```shell
docker compose up
```
> Também é possível executar como uma aplicação Java através do
> método main() na classe AvaliacaofilmesApiApplication.java


## Como executar os testes?

### Usando o Maven instalado globalmente
```shell
mvn test
```

### Usando o Maven Wrapper

No Windows:
```shell
mvnw.cmd test
```
No Linux ou MacOS:
```shell
./mvnw test
```

## Banco de dados

O banco de dados principal é o H2 embarcado na propria aplicação

## Documentação da API

A documentação da API foi feita com Swagger e pode ser acessada através do link: http://localhost:8085/documentation

