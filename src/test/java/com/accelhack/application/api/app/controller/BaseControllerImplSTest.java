// package com.accelhack.application.api.app.controller;
//
// import com.accelhack.application.api.http.ResponseSet;
// import com.accelhack.application.api.shared.functional.ParameterizedApi;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
// import java.util.Collections;
// import java.util.concurrent.Callable;
//
// public class BaseControllerImplSTest {
// protected final ResponseSet<?> responseSet = ResponseSet.ok(0, Collections.emptyList());
// protected final ObjectMapper objectMapper = ObjectMapperUtils.getMapper();
// protected MockMvc mockMvc;
//
// protected <V> boolean execute(Callable<V> callable) {
// try {
// callable.call();
// } catch (Exception e) {
// return false;
// }
// return true;
// }
//
// protected <O extends Operand, R> boolean execute(ParameterizedApi<O, R> callable,
// Request<O> param) {
// return execute(() -> callable.call(param));
// }
//
// protected <T extends Operand> ResultActions performPost(String path, Request<T> request)
// throws Exception {
// return mockMvc.perform(MockMvcRequestBuilders.post(path).contentType(MediaType.APPLICATION_JSON)
// .content(objectMapper.writeValueAsString(request)));
// }
// }
