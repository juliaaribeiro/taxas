
# Projeto Equals Estágio

## Como rodar

1. Compile o projeto:
   ```bash
   mvn clean install
   ```

2. Execute:
   ```bash
   mvn spring-boot:run
   ```

A aplicação estará disponível em: `http://localhost:8080/vendas`

## Filtro por datas

Exemplo de uso:
```
GET /vendas?inicio=2023-01-01&fim=2023-12-31
```
