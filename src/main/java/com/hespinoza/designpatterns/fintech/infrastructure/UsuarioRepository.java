package com.hespinoza.designpatterns.fintech.infrastructure;

import com.hespinoza.designpatterns.fintech.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
  Optional<Usuario> findById(Long id);
  Usuario save(Usuario usuario);
}
