package com.hespinoza.designpatterns.fintech.infrastructure.impl;

import com.hespinoza.designpatterns.fintech.domain.User;
import com.hespinoza.designpatterns.fintech.infrastructure.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Component
public class UserRepositoryImpl implements UserRepository {
  private static final Map<Long, User> userDB = new HashMap<>();
  @Override
  public Optional<User> findById(Long id) {
    return Optional.ofNullable(userDB.get(id));
  }

  @Override
  public User save(User user) {
    userDB.put(user.getId(), user);
    return user;
  }
}
