package com.hespinoza.designpatterns.singleton.domain.impl;

import com.hespinoza.designpatterns.singleton.domain.User;
import com.hespinoza.designpatterns.singleton.infrastructure.UserRepository;
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
