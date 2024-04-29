package com.hespinoza.designpatterns.fintech.domain.impl;

import com.hespinoza.designpatterns.fintech.domain.Usuario;
import com.hespinoza.designpatterns.fintech.infrastructure.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
  private final UsuarioRepository repository;

  public UsuarioService(UsuarioRepository repository) {
    this.repository = repository;
  }

  public Usuario obtenerUsuario(Long id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
  }

  public Usuario guardarUsuario(Usuario usuario) {
    return repository.save(usuario);
  }
}
