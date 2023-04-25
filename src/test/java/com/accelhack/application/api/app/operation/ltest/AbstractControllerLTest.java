package com.accelhack.application.api.app.operation.ltest;

import com.accelhack.accelparts.Operand;
import com.accelhack.accelparts.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@ActiveProfiles("l-test")
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public abstract class AbstractControllerLTest {
  @Autowired
  WebApplicationContext context;

  @Autowired
  ObjectMapper objectMapper;

  MockMvc mockMvc;

  protected void setController() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
      .apply(SecurityMockMvcConfigurers.springSecurity()).build();
  }

  protected <T extends Operand> ResultActions performPost(String path, Request<T> request) throws Exception {
    String json = objectMapper.writeValueAsString(request);
    return performPost(path, json);
  }

  protected ResultActions performPost(String path, String content) throws Exception {
    return mockMvc.perform(builder(path, content));
  }

  protected MockHttpServletRequestBuilder builder(String path, String content) {
    return MockMvcRequestBuilders
      .post(path)
      .contentType(MediaType.APPLICATION_JSON)
      .content(content)
      .with(csrf());
  }
}
