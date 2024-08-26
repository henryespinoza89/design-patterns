package com.hespinoza.designpatterns.fintech.domain.impl;

import com.hespinoza.designpatterns.fintech.domain.User;
import com.hespinoza.designpatterns.fintech.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public User getUser(Long id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
  }

  public User saveUser(User user) {
    return repository.save(user);
  }
}
