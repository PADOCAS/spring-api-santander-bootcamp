package com.ldsystems.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ldsystems.santander.model.vo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByContaNumero(String numero);

    boolean existsByCartaoNumero(String numero);
}