package com.br.cadastro.usuarios.adapters.input.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include. NON_NULL)
public class Endereco {

    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Formato de CEP inválido")
    private String cep;
}
