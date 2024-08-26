package com.hespinoza.designpatterns.fintech.infrastructure;

import com.hespinoza.designpatterns.fintech.domain.User;

import java.util.Optional;

public interface UserRepository {
  Optional<User> findById(Long id);
  User save(User user);
}
