package com.br.cadastro.usuarios.adapters.input.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include. NON_NULL)
public class Usuario {


    private int id;
    private String nome;
    private String usuario;
    private String senha;
    private Date dataNascimento;
    private Endereco endereco;
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Formato de CPF inválido")
    private String cpf;
    private String status;
    private Date dataCriacao;
    private String responsavelCriacao;
    private Date dataAtualizacao;
    private String responsavelAtualizacao;
    private Date dataRemocao;
    private String responsavelRemocao;
}

