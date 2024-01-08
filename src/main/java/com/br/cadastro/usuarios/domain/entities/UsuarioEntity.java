package com.br.cadastro.usuarios.domain.entities;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Table(name = "usuario")
@Entity
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nomeUser;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;

    @Column(name = "dataNascimento")
    private Date dataNascimento;

    @Embedded
    private EnderecoEntity endereco;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "status")
    private String status;

    @Column(name = "dataCriacao")
    private Date dataCriacao;

    @Column(name = "responsavelCriacao")
    private String responsavelCriacao;

    @Column(name = "dataAtualizacao")
    private Date dataAtualizacao;

    @Column(name = "responsavelAtualizacao")
    private String responsavelAtualizacao;

    @Column(name = "dataRemocao")
    private Date dataRemocao;

    @Column(name = "responsavelRemocao")
    private String responsavelRemocao;

}
