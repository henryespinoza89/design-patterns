package com.hespinoza.designpatterns.fintech.application;

import com.hespinoza.designpatterns.fintech.domain.User;
import com.hespinoza.designpatterns.fintech.domain.impl.SingletonConfig;
import com.hespinoza.designpatterns.fintech.domain.impl.SingletonCounter;
import com.hespinoza.designpatterns.fintech.domain.impl.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RestUserController {
  private final UserService userService;
  private final SingletonCounter singletonCounter;
  private final SingletonConfig singletonConfig;

  public RestUserController(UserService userService, SingletonCounter singletonCounter, SingletonConfig singletonConfig) {
    this.userService = userService;
    this.singletonCounter = singletonCounter;
    this.singletonConfig = singletonConfig;
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    User user = userService.getUser(id);
    singletonCounter.increment();
    return ResponseEntity.ok(user);
  }

  @PostMapping
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    User newUser = userService.saveUser(user);
    singletonCounter.increment();
    return ResponseEntity.ok(newUser);
  }

  @GetMapping("/counter")
  public ResponseEntity<Integer> getCounter() {
    int counter = singletonCounter.getCount();
    return ResponseEntity.ok(counter);
  }

  @GetMapping("/config")
  public ResponseEntity<String> getConfiguration() {
    String appName = singletonConfig.getAppName();
    int maxConnections = singletonConfig.getMaxConnections();
    String configInfo = "Application name: " + appName + ", Máx. Connections: " + maxConnections;
    return ResponseEntity.ok(configInfo);
  }
}
