package com.accelhack.application.api.app.operation.mtest.internal;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.response.ListResponse;
import com.accelhack.application.api.app.operation.mtest.BaseControllerImplMTest;
import com.accelhack.application.api.app.mtest.SampleIntControllerImpl;
import com.accelhack.application.api.app.entity.Sample;
import com.accelhack.application.api.app.entity.SampleSelector;
import com.accelhack.application.api.app.transaction.SampleTransaction;
import com.accelhack.application.api.shared.functional.ParameterizedApi;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.reflect.Whitebox;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@ExtendWith(MockitoExtension.class)
public class SampleIntControllerImplMTest extends BaseControllerImplMTest {
  @InjectMocks
  private SampleIntControllerImpl injectedController;

  @Mock
  private SampleTransaction sampleTransaction;

  private SampleIntControllerImpl spiedController;

  @BeforeEach
  void setup() {
    Whitebox.setInternalState(injectedController, "sampleTransaction", sampleTransaction);
    spiedController = Mockito.spy(injectedController);
    this.mockMvc = MockMvcBuilders.standaloneSetup(spiedController).build();
  }

  @Nested
  @DisplayName("取得")
  class Get {
    @Test
    @DisplayName("GET: /api/int/v1/sample/get")
    public void get() throws Exception {
      // data
      Sample sample = Sample.builder().id(10).build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      ArgumentMatcher<Request<Sample>> requestArgumentMatcher = s -> s.findFirst().getId().equals(sample.getId());

      ArgumentMatcher<ParameterizedApi<Sample, Sample>> callableMatcher = callable -> {
        assertTrue(execute(callable, request));
        verify(sampleTransaction, times(1)).get(argThat(requestArgumentMatcher));
        return true;
      };

      // mock setting
      doReturn(response).when(spiedController).execute(argThat(requestArgumentMatcher), argThat(callableMatcher));

      // check result
      performPost("/api/int/v1/sample/get", request).andExpect(content().string(responseStr));
    }
  }

  @Nested
  @DisplayName("検索")
  class Search {
    @Test
    @DisplayName("GET: /api/int/v1/sample/search")
    public void search() throws Exception {
      // data
      SampleSelector sampleSelector = SampleSelector.builder().id(10).build();
      Request<SampleSelector> request = new Request<>();
      request.setOperands(List.of(sampleSelector));

      ArgumentMatcher<Request<SampleSelector>> requestArgumentMatcher = s -> s.findFirst().getId().equals(sampleSelector.getId());

      ArgumentMatcher<ParameterizedApi<SampleSelector, ListResponse<Sample>>> callableMatcher = callable -> {
        assertTrue(execute(callable, request));
        verify(sampleTransaction, times(1)).search(argThat(requestArgumentMatcher));
        return true;
      };

      // mock setting
      doReturn(response).when(spiedController).execute(argThat(requestArgumentMatcher), argThat(callableMatcher));

      // check result
      performPost("/api/int/v1/sample/search", request).andExpect(content().string(responseStr));
    }
  }

  @Nested
  @DisplayName("追加")
  class Add {
    @Test
    @DisplayName("POST: /api/int/v1/sample/add")
    public void add() throws Exception {
      // data
      Sample sample = Sample.builder().name("三浦").build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      ArgumentMatcher<Request<Sample>> requestArgumentMatcher = s -> s.findFirst().getName().equals(sample.getName());

      ArgumentMatcher<ParameterizedApi<Sample, Sample>> callableMatcher = callable -> {
        assertTrue(execute(callable, request));
        verify(sampleTransaction, times(1)).add(argThat(requestArgumentMatcher));
        return true;
      };

      // mock setting
      doReturn(response).when(spiedController).execute(argThat(requestArgumentMatcher), argThat(callableMatcher));

      // check result
      performPost("/api/int/v1/sample/add", request).andExpect(content().string(responseStr));
    }
  }

  @Nested
  @DisplayName("更新")
  class Edit {
    @Test
    @DisplayName("POST: /api/int/v1/sample/edit")
    public void edit() throws Exception {
      // data
      Sample sample = Sample.builder().name("三浦").build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      ArgumentMatcher<Request<Sample>> requestArgumentMatcher = s -> s.findFirst().getName().equals(sample.getName());

      ArgumentMatcher<ParameterizedApi<Sample, Sample>> callableMatcher = callable -> {
        assertTrue(execute(callable, request));
        verify(sampleTransaction, times(1)).edit(argThat(requestArgumentMatcher));
        return true;
      };

      // mock setting
      doReturn(response).when(spiedController).execute(argThat(requestArgumentMatcher), argThat(callableMatcher));

      // check result
      performPost("/api/int/v1/sample/edit", request).andExpect(content().string(responseStr));
    }
  }

  @Nested
  @DisplayName("削除")
  class Remove {
    @Test
    @DisplayName("POST: /api/int/v1/sample/remove")
    public void remove() throws Exception {
      // data
      Sample sample = Sample.builder().name("三浦").build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      ArgumentMatcher<Request<Sample>> requestArgumentMatcher = s -> s.findFirst().getName().equals(sample.getName());

      ArgumentMatcher<ParameterizedApi<Sample, Sample>> callableMatcher = callable -> {
        assertTrue(execute(callable, request));
        verify(sampleTransaction, times(1)).remove(argThat(requestArgumentMatcher));
        return true;
      };

      // mock setting
      doReturn(response).when(spiedController).execute(argThat(requestArgumentMatcher), argThat(callableMatcher));

      // check result
      performPost("/api/int/v1/sample/remove", request).andExpect(content().string(responseStr));
    }
  }
}