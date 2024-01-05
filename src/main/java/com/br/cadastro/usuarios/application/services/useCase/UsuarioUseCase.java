package com.br.cadastro.usuarios.application.services.useCase;

import com.br.cadastro.usuarios.adapters.input.entities.Usuario;

public interface UsuarioUseCase {

    Usuario cadastraUsuario(Usuario user);

    Usuario buscarUsuario(Usuario user);

    Usuario atualizarUsuario(Usuario user, Long id);

}
