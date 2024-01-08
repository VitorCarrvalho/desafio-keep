package com.br.cadastro.usuarios.application.services.useCase;

import com.br.cadastro.usuarios.adapters.input.entities.Usuario;

public interface UsuarioUseCase {

    Usuario cadastraUsuario(Usuario user);

    Usuario validaUsuario(Usuario user);

    Usuario atualizarUsuario(Usuario user, Long id, String username);

    Usuario listarUsuario(Long id);

    Usuario removerUsuario(Long id, String username);

}
