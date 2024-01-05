package com.br.cadastro.usuarios.adapters.input.controllers;


import com.br.cadastro.usuarios.adapters.input.entities.Usuario;
import com.br.cadastro.usuarios.application.services.useCase.UsuarioUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
@Slf4j
public class UsuarioController {


    @Autowired
    UsuarioUseCase usuarioUseCase;

    @PostMapping
    public ResponseEntity<Usuario> post(@RequestBody Usuario user){

        log.info("Iniciando o cadastro de um novo usuário");
        Usuario novoUsuarioCadastrado = usuarioUseCase.cadastraUsuario(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuarioCadastrado);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> put(@RequestBody Usuario user, @PathVariable Long id){
        log.info("Iniciando a atualização de um usuário existente." + id);

        Usuario userAtualizado = usuarioUseCase.atualizarUsuario(user, id);

        if(Objects.isNull(userAtualizado)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userAtualizado);

        }
        return ResponseEntity.status(HttpStatus.OK).body(userAtualizado);
    }

}
