package com.br.cadastro.usuarios.infraestructure.config.security;

import com.br.cadastro.usuarios.domain.entities.UsuarioEntity;
import com.br.cadastro.usuarios.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username){

        UsuarioEntity userValido = usuarioRepository.findByUsuario(username);

        if (userValido != null){
            return new User(username, userValido.getSenha(), new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("Nenhum usuário encontrado com esta propriedade de token, user: " + username);
        }
    }

}
