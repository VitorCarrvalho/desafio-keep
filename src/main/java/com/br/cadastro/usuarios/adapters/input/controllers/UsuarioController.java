package com.br.cadastro.usuarios.adapters.input.controllers;


import com.br.cadastro.usuarios.adapters.input.entities.Usuario;
import com.br.cadastro.usuarios.application.services.useCase.UsuarioUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
@Slf4j
public class UsuarioController {


    @Autowired
    UsuarioUseCase usuarioUseCase;

    @PostMapping
    public ResponseEntity<Usuario> post(@RequestBody Usuario user) {

        log.info("Iniciando o cadastro de um novo usuário");

        String username = obterNomeUsuarioDoToken();
        user.setResponsavelCriacao(username);
        Usuario novoUsuarioCadastrado = usuarioUseCase.cadastraUsuario(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuarioCadastrado);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> put(@RequestBody Usuario user, @PathVariable Long id) {
        log.info("Iniciando a atualização de um usuário existente." + id);
        String username = obterNomeUsuarioDoToken();
        Usuario userAtualizado = usuarioUseCase.atualizarUsuario(user, id, username);

        if (Objects.isNull(userAtualizado)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userAtualizado);

        }
        return ResponseEntity.status(HttpStatus.OK).body(userAtualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> get(@PathVariable Long id) {
                Usuario usuario = usuarioUseCase.listarUsuario(id);

        return ResponseEntity.status(HttpStatus.OK).body(usuario);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario>  delete(@PathVariable Long id) {
        String username = obterNomeUsuarioDoToken();
        Usuario usuario = usuarioUseCase.removerUsuario(id, username);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
    }

    private String obterNomeUsuarioDoToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object claims = authentication.getPrincipal();
        String user = ((User) claims).getUsername();
        return user;

    }
}