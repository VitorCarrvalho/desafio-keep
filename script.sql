-- Arquivo: script.sql

-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS usuarios;
USE usuarios;

-- Criação da tabela endereco
CREATE TABLE IF NOT EXISTS endereco (
    rua VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    cep VARCHAR(9),
    PRIMARY KEY (cep)
);

-- Criação da tabela usuario
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    usuario VARCHAR(40),
    senha VARCHAR(255),
    dataNascimento DATE,
    rua VARCHAR(255),
    numero VARCHAR(10),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    cep VARCHAR(9),
    cpf VARCHAR(14),
    status VARCHAR(255),
    dataCriacao DATE,
    responsavelCriacao VARCHAR(255),
    dataAtualizacao DATE,
    responsavelAtualizacao VARCHAR(255),
    dataRemocao DATE,
    responsavelRemocao VARCHAR(255)
);


-- Adicionando o usuário admin
INSERT INTO `usuarios`.`usuario` (`nome`, `usuario`, `senha`) VALUES ('Administrador', 'admin', '12345');

