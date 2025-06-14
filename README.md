
# Projeto Equals Estágio

Este projeto consiste em um sistema full stack com backend em **Spring Boot** e frontend em **React**, que exibe relatórios de vendas com filtro por datas.

---

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter:

- Java 17+
- Apache Maven
- Node.js e npm
- MySQL Server e MySQL Workbench (ou outro cliente MySQL)
Backend (Spring Boot)
Instalação e execução
Instale o Maven e configure o PATH no sistema.

---

## Banco de Dados

1. Configure o MySQL Server (usuário, senha, porta) no arquivo `application.properties`:
spring.datasource.url=jdbc:mysql://localhost:3306/financeiro?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha


2. Crie o banco de dados executando o script SQL fornecido

---

## Backend em **Spring Boot**

1. Compile o projeto e execute os testes:

mvn clean package

2. Inicie a aplicação:

java -jar target/equals-estagio-1.0-SNAPSHOT.jar
ou
mvn spring-boot:run

A API estará disponível em: http://localhost:8080/vendas

3. Filtro por datas:

A rota /vendas aceita dois parâmetros opcionais (inicio, fim) no formato yyyy-MM-dd:
Exemplo:
GET /vendas?inicio=2023-01-01&fim=2023-12-31

---

## Frontend (React)
1. Instalação e execução
Navegue até a pasta do frontend e instale as dependências:

npm install

2. Instale a biblioteca de gráficos:

npm install recharts

3. Inicie o servidor de desenvolvimento:

npm start
O frontend estará disponível em: http://localhost:3000

---

## Funcionalidades
Listagem das vendas

Filtro por intervalo de datas

Visualização de resumos de vendas por bandeira

Gráficos de pizza e barras com a biblioteca recharts