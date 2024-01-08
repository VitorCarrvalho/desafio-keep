package com.br.cadastro.usuarios.application.services.impl;


import com.br.cadastro.usuarios.adapters.input.entities.Usuario;
import com.br.cadastro.usuarios.application.services.mappers.UsuarioMapper;
import com.br.cadastro.usuarios.application.services.useCase.UsuarioUseCase;
import com.br.cadastro.usuarios.domain.entities.UsuarioEntity;
import com.br.cadastro.usuarios.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.Objects;
import java.util.Optional;


@Service
public class UsuarioUseCaseImpl implements UsuarioUseCase {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    public Usuario validaUsuario(Usuario user) {

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

        UsuarioEntity entity = usuarioMapper.usuarioToUsuarioEntityMapper(user);

        UsuarioEntity novoUsuario = usuarioRepository.save(entity);

        Usuario usuario = usuarioMapper.usuarioEntityToUsuario(novoUsuario);
        usuario.setSenha("*****");

        return usuario;
    }
    public Usuario atualizarUsuario(Usuario user, Long id, String username) {
        Integer intId = (int) (long) id;
        Optional<UsuarioEntity> UsuarioExistente = Optional.ofNullable(usuarioRepository.findById(intId));

        if(UsuarioExistente.isPresent()){
            UsuarioEntity retornoExistente = UsuarioExistente.get();
            long currentTimeMillis = System.currentTimeMillis();
            retornoExistente.setDataAtualizacao(new Date(currentTimeMillis));
            retornoExistente.setResponsavelAtualizacao(username);
            UsuarioEntity usuarioEntityAtualizado = usuarioRepository.save(retornoExistente);

            Usuario usuarioAtualizado = usuarioMapper.usuarioEntityToUsuario(usuarioEntityAtualizado);

           return usuarioAtualizado;
        }

        return null;
    }

    public Usuario listarUsuario(Long id){
        Integer intId = (int) (long) id;
        UsuarioEntity usuarioEntity = usuarioRepository.findById(intId);

        if(Objects.nonNull(usuarioEntity)){
            Usuario usuario = usuarioMapper.usuarioEntityToUsuario(usuarioEntity);

            return usuario;
        }
        return null;
    }

    public Usuario removerUsuario(Long id, String username){
        Integer intId = (int) (long) id;
        UsuarioEntity usuarioEntity = usuarioRepository.findById(intId);

        long currentTimeMillis = System.currentTimeMillis();

        usuarioEntity.setStatus("DESATIVADO");
        usuarioEntity.setDataRemocao(new Date(currentTimeMillis));
        usuarioEntity.setResponsavelRemocao(username);
        UsuarioEntity userRemovido = usuarioRepository.saveAndFlush(usuarioEntity);
        Usuario user = usuarioMapper.usuarioEntityToUsuario(userRemovido);
        return user;
    }
}
