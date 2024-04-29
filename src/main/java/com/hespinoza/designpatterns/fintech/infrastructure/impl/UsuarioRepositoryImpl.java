package com.hespinoza.designpatterns.fintech.infrastructure.impl;

import com.hespinoza.designpatterns.fintech.domain.Usuario;
import com.hespinoza.designpatterns.fintech.infrastructure.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Component
public class UsuarioRepositoryImpl implements UsuarioRepository  {
  private static final Map<Long, Usuario> usuariosDB = new HashMap<>();
  @Override
  public Optional<Usuario> findById(Long id) {
    return Optional.ofNullable(usuariosDB.get(id));
  }

  @Override
  public Usuario save(Usuario usuario) {
    usuariosDB.put(usuario.getId(), usuario);
    return usuario;
  }
}
