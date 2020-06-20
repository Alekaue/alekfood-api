package com.alekaue.alekfood.domain.repository;

import java.util.Optional;

import com.alekaue.alekfood.domain.model.Usuario;

public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
}
