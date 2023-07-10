package com.accelhack.spring.api;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("l-test")
@SpringBootTest(classes = ApiApplication.class)
public class ApiApplicationTests {

  @Autowired
  private Environment env;

  @Test
  public void contextLoads() {
    System.out.println("Active profiles: " + Arrays.toString(env.getActiveProfiles()));
  }
}
