package com.accelhack.application.api.app.operation.mtest;

import com.accelhack.accelparts.Operand;
import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.utils.ObjectMapperUtils;
import com.accelhack.application.api.shared.functional.ParameterizedApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.Callable;

public class BaseControllerImplMTest {
  protected MockMvc mockMvc;

  protected final String responseStr = "ok";

  protected final ResponseEntity<?> response = ResponseEntity.ok(responseStr);

  protected final ObjectMapper objectMapper = ObjectMapperUtils.getMapper();

  protected <V> boolean execute(Callable<V> callable) {
    try {
      callable.call();
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  protected <O extends Operand, R> boolean execute(ParameterizedApi<O, R> callable,
      Request<O> param) {
    return execute(() -> callable.call(param));
  }

  protected <T extends Operand> ResultActions performPost(String path, Request<T> request)
      throws Exception {
    return mockMvc.perform(MockMvcRequestBuilders.post(path).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));
  }
}
