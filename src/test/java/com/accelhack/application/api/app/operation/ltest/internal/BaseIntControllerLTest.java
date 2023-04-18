package com.accelhack.application.api.app.operation.ltest.internal;

import com.accelhack.application.api.app.operation.ltest.AbstractControllerLTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

public class BaseIntControllerLTest extends AbstractControllerLTest {
  @Autowired
  UserDetailsService userDetailsService;

  @Override
  protected MockHttpServletRequestBuilder builder(String path, String content) {
    return super.builder(path, content)
      .with(user(userDetailsService.loadUserByUsername("manager")));
  }
}
