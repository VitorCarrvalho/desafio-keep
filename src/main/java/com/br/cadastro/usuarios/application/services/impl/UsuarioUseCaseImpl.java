package com.br.cadastro.usuarios.application.services.impl;


import com.br.cadastro.usuarios.adapters.input.entities.Usuario;
import com.br.cadastro.usuarios.application.services.useCase.UsuarioUseCase;
import com.br.cadastro.usuarios.domain.entities.UsuarioEntity;
import com.br.cadastro.usuarios.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;


@Service
public class UsuarioUseCaseImpl implements UsuarioUseCase {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario buscarUsuario(Usuario user) {
        return getUsuario(user);
    }

    private Usuario getUsuario(Usuario user) {

        UsuarioEntity novoUsuario = usuarioRepository.findByUsuario(user.getUsuario());

        if (Objects.nonNull(novoUsuario) && novoUsuario.getSenha().equals(user.getSenha())) {
            return Usuario.builder()
                    .id(novoUsuario.getId())
                    .nome(novoUsuario.getNomeUser())
                    .usuario(novoUsuario.getUsuario())
                    .senha(novoUsuario.getSenha())
                    .build();
        }

        return null;
    }

    public Usuario cadastraUsuario(Usuario user) {
        return cadastroUser(user);
    }

    private Usuario cadastroUser(Usuario user) {

        UsuarioEntity entity = new UsuarioEntity();

        entity.setUsuario(user.getUsuario());
        entity.setNomeUser(user.getNome());
        entity.setSenha(user.getSenha());

        UsuarioEntity novoUsuario = usuarioRepository.save(entity);

        return Usuario.builder()
                .id(novoUsuario.getId())
                .nome(novoUsuario.getNomeUser())
                .usuario(novoUsuario.getUsuario())
                .senha(novoUsuario.getSenha())
                .build();
    }

    public Usuario atualizarUsuario(Usuario user, Long id){
        return atualizaUser(user, id);
    }

    private Usuario atualizaUser(Usuario user, Long id) {
        Integer intId = (int) (long) id;
        Optional<UsuarioEntity> UsuarioExistente = Optional.ofNullable(usuarioRepository.findById(intId));

        if(UsuarioExistente.isPresent()){
            UsuarioEntity retornoExistente = UsuarioExistente.get();
            retornoExistente.setSenha(user.getSenha());
            UsuarioEntity usuarioAtualizado = usuarioRepository.save(retornoExistente);

           return Usuario.builder()
                    .nome(usuarioAtualizado.getNomeUser())
                    .usuario(usuarioAtualizado.getUsuario())
                    .build();
        }

        return null;
    }
}
