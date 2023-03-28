package com.accelhack.application.api.shared.config;

import com.accelhack.application.api.shared.controller.UIController;
import com.accelhack.application.api.shared.interceptor.LoggerInterceptor;
import com.accelhack.application.api.shared.interceptor.SuperuserInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

  private final LoggerInterceptor loggerInterceptor;
  private final SuperuserInterceptor superuserInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loggerInterceptor).order(999);
    registry.addInterceptor(superuserInterceptor).order(100).addPathPatterns(UIController.USER_CONTEXT + "/**", UIController.MANAGER_CONTEXT + "/**");
  }
}
