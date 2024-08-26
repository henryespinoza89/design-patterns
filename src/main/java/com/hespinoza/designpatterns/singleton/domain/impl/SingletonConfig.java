package com.hespinoza.designpatterns.singleton.domain.impl;

import org.springframework.stereotype.Component;

@Component
public class SingletonConfig {
  private static SingletonConfig instance;
  private String appName;
  private int maxConnections;

  private SingletonConfig() {
    appName = "My application"; // Default configuration
    maxConnections = 100;
  }

  public static synchronized SingletonConfig getInstance() {
    if (instance == null) {
      instance = new SingletonConfig();
    }
    return instance;
  }

  public String getAppName() {
    return appName;
  }
  public void setAppName(String appName) {
    this.appName = appName;
  }
  public int getMaxConnections() {
    return maxConnections;
  }
  public void setMaxConnections(int maxConnections) {
    this.maxConnections = maxConnections;
  }
}
