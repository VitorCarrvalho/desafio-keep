# Microserviço de Gerenciamento de Usuários

Este é um microserviço responsável pelo gerenciamento de usuários. Ele fornece endpoints para criar, atualizar, listar e remover usuários no sistema.

## Requisitos e detalhes do Microserviço

- **Porta de Execução:** 8083
- **Tenha instalado em sua máquina o Docker**
- **O Micro serviço foi implementado utilizando a arquitetura Hexagonal**
- **Banco de dados MySQL**

## Configuração do Banco de Dados

Para configurar o banco de dados, execute o arquivo `docker-compose` fornecido na raiz do projeto. Esse arquivo cria a tabela de usuário e inclui um usuário padrão através do script.sql que também está na raiz do projeto
Ao executar o `docker-compose` com o comando `docker-compose up -d` via linha de comando na mesma pasta aonde se encontra o arquivo, será criado o usuário abaixo:
- **Usuário Padrão:** admin
- **Senha Padrão:** 12345

Você pode usar essas credenciais para realizar as primeiras operações no sistema.

## Endpoints

# Endpoint de Autenticação

### Gerar Token de Autenticação

- **Método:** `POST`
- **Endpoint:** `/auth`
- **Descrição:** Gera um token de autenticação com base nas credenciais fornecidas.
- **Parâmetros de Requisição:**
    - `client_id`: Nome de usuário (cliente)
    - `password`: Senha do usuário

#### Exemplo de Requisição
```http
POST /auth HTTP/1.1
Host: localhost:8083
Content-Type: application/x-www-form-urlencoded

client_id=admin&password=12345

```
#### Exemplo de Resposta
```json
{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTUxNjIzOTAyMn0.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
"usuarioId": 1
}
```

### 1. Cadastrar um Novo Usuário

- **Método:** `POST`
- **Endpoint:** `/usuarios`
- **Header:** `Bearer token12345`
- **Descrição:** Cadastra um novo usuário no sistema.
  - **Corpo da Requisição:**
    ```json
    {
      "nome": "Nome do Usuário",
      "dataNascimento": "yyyy-MM-dd",
      "cpf": "123.456.789-09",
      "status": "ATIVO",
      "endereco": {
        "rua": "Rua do Usuário",
        "numero": "123",
        "complemento": "Complemento",
        "bairro": "Bairro",
        "cidade": "Cidade",
        "estado": "Estado",
        "cep": "12345-678"
      }
    }
    ```
#### Exemplo de Resposta (Sucesso)

```json
  {
    "nome": "Nome do Usuário",
    "dataNascimento": "yyyy-MM-dd",
    "cpf": "123.456.789-09",
    "status": "ATIVO",
    "endereco": {
      "rua": "Rua do Usuário",
      "numero": "123",
      "complemento": "Complemento",
      "bairro": "Bairro",
      "cidade": "Cidade",
      "estado": "Estado",
      "cep": "12345-678"
    }
  }
```


#### Atualizar um Usuário Existente

- **Método:** `PUT`
- **Endpoint:** `/usuarios/{id}`
- **Header:** `Bearer token12345`
- **Descrição:** Atualiza um usuário existente com base no ID.
- **Parâmetros de Caminho:**
  - `{id}`: ID do usuário a ser atualizado
- **Corpo da Requisição:**
  ```json
  {
    "nome": "Novo Nome do Usuário",
    "dataNascimento": "yyyy-MM-dd",
    "cpf": "123.456.789-09",
    "status": "ATIVO",
    "endereco": {
      "rua": "Nova Rua",
      "numero": "456",
      "complemento": "Novo Complemento",
      "bairro": "Novo Bairro",
      "cidade": "Nova Cidade",
      "estado": "Novo Estado",
      "cep": "54321-987"
    }
  }
    ```
#### Exemplo de Resposta

```json
{
"id": 1,
"nome": "Nome do Usuário",
"dataNascimento": "yyyy-MM-dd",
"cpf": "123.456.789-09",
"status": "ATIVO",
"endereco": {
"rua": "Rua do Usuário",
"numero": "123",
"complemento": "Complemento",
"bairro": "Bairro",
"cidade": "Cidade",
"estado": "Estado",
"cep": "12345-678"
},
"responsavelCriacao": "admin",
"dataCriacao": "timestamp"
}
```

### Obter Detalhes de um Usuário

- **Método:** `GET`
- **Endpoint:** `/usuarios/{id}`
- **Header:** `Bearer token12345`
- **Descrição:** Retorna os detalhes de um usuário com base no ID.
- **Parâmetros de Caminho:**
    - `{id}`: ID do usuário a ser consultado
- **Exemplo de Resposta (Sucesso):**
  ```json
  {
    "id": 1,
    "nome": "Novo Nome do Usuário",
    "dataNascimento": "yyyy-MM-dd",
    "cpf": "123.456.789-09",
    "status": "ATIVO",
    "endereco": {
      "rua": "Nova Rua",
      "numero": "456",
      "complemento": "Novo Complemento",
      "bairro": "Novo Bairro",
      "cidade": "Nova Cidade",
      "estado": "Novo Estado",
      "cep": "54321-987"
    },
    "responsavelCriacao": "admin",
    "dataCriacao": "timestamp",
    "responsavelAtualizacao": "admin",
    "dataAtualizacao": "timestamp"


#### Exemplo de Resposta    
```json
{
"id": 1,
"nome": "Nome do Usuário",
"dataNascimento": "yyyy-MM-dd",
"cpf": "123.456.789-09",
"status": "ATIVO",
"endereco": {
"rua": "Rua do Usuário",
"numero": "123",
"complemento": "Complemento",
"bairro": "Bairro",
"cidade": "Cidade",
"estado": "Estado",
"cep": "12345-678"
},
"responsavelCriacao": "admin",
"dataCriacao": "timestamp"
}
```

### Deletar um Usuário

- **Método:** `DELETE`
- **Endpoint:** `/usuarios/{id}`
- **Header:** `Bearer token12345`
- **Descrição:** Remove um usuário com base no ID.
- **Parâmetros de Caminho:**
    - `{id}`: ID do usuário a ser removido
- **Exemplo de Resposta (Sucesso):**
  ```json
  {
    "id": 1,
    "nome": "Novo Nome do Usuário",
    "dataNascimento": "yyyy-MM-dd",
    "cpf": "123.456.789-09",
    "status": "DESATIVADO",
    "endereco": {
      "rua": "Nova Rua",
      "numero": "456",
      "complemento": "Novo Complemento",
      "bairro": "Novo Bairro",
      "cidade": "Nova Cidade",
      "estado": "Novo Estado",
      "cep": "54321-987"
    },
    "responsavelCriacao": "admin",
    "dataCriacao": "timestamp",
    "responsavelAtualizacao": "admin",
    "dataAtualizacao": "timestamp",
    "dataRemocao": "timestamp",
    "responsavelRemocao": "admin"
  }