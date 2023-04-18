package com.accelhack.application.api.app.controller.internal;

import com.accelhack.accelparts.Request;
import com.accelhack.application.api.app.controller.BaseControllerImplSTest;
import com.accelhack.application.api.app.entity.Sample;
import com.accelhack.application.api.app.entity.SampleSelector;
import com.accelhack.application.api.app.mtest.SampleIntControllerImpl;
import com.accelhack.application.api.app.transaction.SampleTransaction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class SampleIntControllerImplSTest extends BaseControllerImplSTest {
  @InjectMocks
  private SampleIntControllerImpl sampleIntController;

  @Mock
  private SampleTransaction sampleTransaction;

  @Nested
  @DisplayName("取得")
  class Get {
    @Test
    @DisplayName("[正常系]")
    public void get() {
      // data
      Sample sample = Sample.builder().id(10).build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      // mock setting
      doReturn(responseSet).when(sampleTransaction).get(request);

      // run target function
      ResponseEntity<?> result = sampleIntController.get(request);

      // check result
      assertEquals(responseSet, result.getBody());
    }
  }

  @Nested
  @DisplayName("検索")
  class Search {
    @Test
    @DisplayName("[正常系]")
    public void search() {
      // data
      SampleSelector sampleSelector = SampleSelector.builder().id(10).build();
      Request<SampleSelector> request = new Request<>();
      request.setOperands(List.of(sampleSelector));

      // mock setting
      doReturn(responseSet).when(sampleTransaction).search(request);

      // run target function
      ResponseEntity<?> result = sampleIntController.search(request);

      // check result
      assertEquals(responseSet, result.getBody());
    }
  }

  @Nested
  @DisplayName("追加")
  class Add {
    @Test
    @DisplayName("[正常系]")
    public void add() {
      // data
      Sample sample = Sample.builder().name("三浦").build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      // mock setting
      doReturn(responseSet).when(sampleTransaction).add(request);

      // run target function
      ResponseEntity<?> result = sampleIntController.add(request);

      // check result
      assertEquals(responseSet, result.getBody());
    }
  }

  @Nested
  @DisplayName("更新")
  class Edit {
    @Test
    @DisplayName("[正常系]")
    public void edit() {
      // data
      Sample sample = Sample.builder().name("三浦").build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      // mock setting
      doReturn(responseSet).when(sampleTransaction).edit(request);

      // run target function
      ResponseEntity<?> result = sampleIntController.edit(request);

      // check result
      assertEquals(responseSet, result.getBody());
    }
  }

  @Nested
  @DisplayName("削除")
  class Remove {
    @Test
    @DisplayName("[正常系]")
    public void remove() {
      // data
      Sample sample = Sample.builder().name("三浦").build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      // mock setting
      doReturn(responseSet).when(sampleTransaction).remove(request);

      // run target function
      ResponseEntity<?> result = sampleIntController.remove(request);

      // check result
      assertEquals(responseSet, result.getBody());
    }
  }
}
