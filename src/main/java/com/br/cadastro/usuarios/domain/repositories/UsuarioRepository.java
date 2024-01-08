package com.br.cadastro.usuarios.domain.repositories;

import com.br.cadastro.usuarios.domain.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByUsuario(String nome);

    UsuarioEntity findById(int id);

    UsuarioEntity deleteById(int id);
}
