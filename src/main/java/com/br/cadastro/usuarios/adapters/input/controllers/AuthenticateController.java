package com.br.cadastro.usuarios.adapters.input.controllers;

import com.br.cadastro.usuarios.adapters.input.entities.GenerateToken;
import com.br.cadastro.usuarios.adapters.input.entities.Usuario;
import com.br.cadastro.usuarios.application.services.useCase.UsuarioUseCase;
import com.br.cadastro.usuarios.infraestructure.config.security.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticateController {

    @Autowired
    UsuarioUseCase usuarioUseCase;

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<GenerateToken> generateToken(@RequestParam("client_id") String usuario,
                                                       @RequestParam("password") String password) {

            log.info("INICIO - Autenticação de usuário:" + usuario);

        Usuario user = usuarioUseCase.validaUsuario(Usuario.builder()
                        .usuario(usuario)
                        .senha(password)
                .build());

        if (Objects.isNull(user)) {
            log.error("Não foi possível gerar o token, pois o usuário ou senha são incorretos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GenerateToken.builder().build());
        }

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

        String token = jwtTokenUtil.generateToken(usuario);

        GenerateToken tokenResponse = GenerateToken.builder()
                .token(token)
                .usuarioId(user.getId())
                .build();

        log.info("token gerado com sucesso para o usuário: " + usuario + " Em: " + System.currentTimeMillis());

        log.info("FIM - Autenticação de usuário: " + usuario);
        return ResponseEntity.ok(tokenResponse);
    }
}
