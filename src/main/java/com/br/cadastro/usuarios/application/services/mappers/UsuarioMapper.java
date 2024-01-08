package com.br.cadastro.usuarios.application.services.mappers;

import com.br.cadastro.usuarios.adapters.input.entities.Endereco;
import com.br.cadastro.usuarios.adapters.input.entities.Usuario;
import com.br.cadastro.usuarios.domain.entities.EnderecoEntity;
import com.br.cadastro.usuarios.domain.entities.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
public class UsuarioMapper {

    public UsuarioEntity usuarioToUsuarioEntityMapper(Usuario user){
        UsuarioEntity entity = new UsuarioEntity();

        long currentTimeMillis = System.currentTimeMillis();
        entity.setUsuario(user.getUsuario());
        entity.setNomeUser(user.getNome());
        entity.setSenha(user.getSenha());
        entity.setCpf(user.getCpf());
        entity.setDataNascimento(user.getDataNascimento());
        entity.setDataCriacao(new Date(currentTimeMillis));
        entity.setDataAtualizacao(new Date(currentTimeMillis));
        entity.setResponsavelCriacao(user.getResponsavelCriacao());
        entity.setStatus("ATIVO");
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setRua(user.getEndereco().getRua());
        endereco.setNumero(user.getEndereco().getNumero());
        endereco.setCidade(user.getEndereco().getCidade());
        endereco.setComplemento(user.getEndereco().getComplemento());
        endereco.setBairro(user.getEndereco().getBairro());
        endereco.setEstado(user.getEndereco().getEstado());
        endereco.setCep(user.getEndereco().getCep());
        entity.setEndereco(endereco);

        return entity;
    }

    public Usuario usuarioEntityToUsuario(UsuarioEntity entity){
            Endereco endereco = Objects.nonNull(entity.getEndereco()) ? mapperEndereco(entity.getEndereco()) : Endereco.builder().build();

        return Usuario.builder()
                .id(entity.getId())
                .nome(entity.getNomeUser())
                .senha("*****")
                .cpf(entity.getCpf())
                .usuario(entity.getUsuario())
                .dataAtualizacao(entity.getDataAtualizacao())
                .dataCriacao(entity.getDataCriacao())
                .dataNascimento(entity.getDataNascimento())
                .dataRemocao(entity.getDataRemocao())
                .endereco(endereco)
                .status(Objects.isNull(entity.getDataRemocao()) ? "ATIVO" : "REMOVIDO")
                .responsavelCriacao(entity.getResponsavelCriacao())
                .responsavelAtualizacao(entity.getResponsavelAtualizacao())
                .responsavelRemocao(entity.getResponsavelRemocao())
                .build();
    }

    private Endereco mapperEndereco(EnderecoEntity endereco) {

        return Endereco.builder()
                .rua(endereco.getRua().isEmpty() ? "" : endereco.getRua())
                .cep(endereco.getCep().isEmpty() ? "" : endereco.getCep())
                .bairro(endereco.getBairro().isEmpty() ? "" : endereco.getBairro())
                .complemento(endereco.getComplemento().isEmpty() ? "" : endereco.getComplemento())
                .cidade(endereco.getCidade().isEmpty() ? "" : endereco.getCidade())
                .estado(endereco.getEstado().isEmpty() ? "" : endereco.getEstado())
                .numero(endereco.getNumero().isEmpty() ? "" : endereco.getNumero())
                .build();
    }
}
