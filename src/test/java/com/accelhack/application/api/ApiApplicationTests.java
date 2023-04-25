package com.accelhack.application.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("l-test")
@SpringBootTest(classes = ApiApplication.class)
public class ApiApplicationTests {
  @Test
  public void contextLoads() {
  }
}
