package com.hespinoza.designpatterns.singleton.infrastructure;

import com.hespinoza.designpatterns.singleton.domain.User;

import java.util.Optional;

public interface UserRepository {
  Optional<User> findById(Long id);
  User save(User user);
}
