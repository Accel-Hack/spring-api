package com.accelhack.spring.api.shared.config;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyContext implements ApplicationContextAware {

  private static ApplicationContext context;

  @Override
  public void setApplicationContext(@NonNull ApplicationContext argApplicationContext)
      throws BeansException {
    context = argApplicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return context;
  }

  public static <T> T getBean(Class<T> clazz) {
    return context.getBean(clazz);
  }
}
