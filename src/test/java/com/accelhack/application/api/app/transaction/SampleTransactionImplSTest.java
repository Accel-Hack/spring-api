package com.accelhack.application.api.app.transaction;

import com.accelhack.accelparts.Request;
import com.accelhack.accelparts.ResponseSet;
import com.accelhack.accelparts.executor.OperandExecutor;
import com.accelhack.accelparts.response.ListResponse;
import com.accelhack.application.api.app.dto.SampleDto;
import com.accelhack.application.api.app.entity.Sample;
import com.accelhack.application.api.app.entity.SampleSelector;
import com.accelhack.application.api.app.service.SampleService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validator;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SampleTransactionImplSTest {
  @InjectMocks
  SampleTransactionImpl sampleTransaction;

  @Mock
  SampleService sampleService;

  @Mock
  Validator validator;

  @Nested
  @DisplayName("取得")
  class GET {
    @Test
    @DisplayName("get")
    public void get() {
      try (var mockedOperandExecutor = Mockito.mockStatic(OperandExecutor.class)) {
        // data
        Request<Sample> sampleRequest = new Request<>();
        ResponseSet<Sample> sampleResponseSet = new ResponseSet<>();

        // mock setting
        OperandExecutor<Sample, Sample> executor = mock(OperandExecutor.class);
        mockedOperandExecutor.when(() -> OperandExecutor.build(any())).thenReturn(executor);
        doReturn(sampleResponseSet).when(executor).run(eq(sampleRequest), any());

        // result
        assertEquals(sampleResponseSet, sampleTransaction.get(sampleRequest));

        // called functions count
        mockedOperandExecutor.verify(() -> OperandExecutor.build(any()), times(1));
        verify(executor, times(1)).run(sampleRequest, validator);
      }
    }

    @Nested
    @DisplayName("Executor")
    class Executor {
      private final Sample sample = Sample.builder()
        .id(10)
        .name("三浦")
        .birthday(Instant.parse("2000-06-24T12:34:56Z"))
        .isJapanese(Boolean.TRUE)
        .build();

      @Test
      @DisplayName("[正常系]")
      public void success() {
        // data
        SampleDto sampleDto = SampleDto.builder()
          .id(sample.getId())
          .name(sample.getName())
          .birthday(sample.getBirthday())
          .isJapanese(sample.getIsJapanese())
          .build();

        // mock setting
        doReturn(sampleDto).when(sampleService).get(sample.getId());

        // result
        Sample result = sampleTransaction.getSample(sample);
        assertAll("result",
          () -> assertEquals(sampleDto.getId(), result.getId()),
          () -> assertEquals(sampleDto.getName(), result.getName()),
          () -> assertEquals(sampleDto.getBirthday(), result.getBirthday()),
          () -> assertEquals(sampleDto.getIsJapanese(), result.getIsJapanese())
        );

        // called functions count
        verify(sampleService, times(1)).get(sample.getId());
      }

      @Test
      @DisplayName("[失敗系]")
      public void failure() {
        // mock setting
        doReturn(null).when(sampleService).get(sample.getId());

        // result
        assertThrows(NullPointerException.class, () -> sampleTransaction.getSample(sample));

        // called functions count
        verify(sampleService, times(1)).get(sample.getId());
      }
    }
  }

  @Nested
  @DisplayName("検索")
  class Search {
    @Test
    @DisplayName("search")
    public void search() {
      try (var mockedOperandExecutor = Mockito.mockStatic(OperandExecutor.class)) {
        // data
        Request<SampleSelector> request = new Request<>();
        ResponseSet<ListResponse<Sample>> responseSet = new ResponseSet<>();

        // mock setting
        OperandExecutor<SampleSelector, ListResponse<Sample>> executor = mock(OperandExecutor.class);
        mockedOperandExecutor.when(() -> OperandExecutor.build(any())).thenReturn(executor);
        doReturn(responseSet).when(executor).run(eq(request), any());

        // result
        assertEquals(responseSet, sampleTransaction.search(request));

        // called functions count
        mockedOperandExecutor.verify(() -> OperandExecutor.build(any()), times(1));
        verify(executor, times(1)).run(request, validator);
      }
    }

    @Nested
    @DisplayName("Executor")
    class Executor {
      private final SampleSelector sampleSelector = SampleSelector.builder().build();

      @Test
      @DisplayName("[正常系]")
      public void success() {
        // data
        SampleDto sampleDto1 = SampleDto.builder()
          .id(1)
          .name("三浦")
          .birthday(Instant.parse("2000-06-24T12:34:56Z"))
          .isJapanese(Boolean.TRUE)
          .total(2)
          .build(),
          sampleDto2 = SampleDto.builder()
            .id(2)
            .name("鈴木")
            .birthday(Instant.parse("2001-03-02T11:11:11Z"))
            .isJapanese(Boolean.FALSE)
            .total(2)
            .build();
        List<SampleDto> sampleDtos = List.of(sampleDto1, sampleDto2);

        // mock setting
        doReturn(sampleDtos).when(sampleService).search(sampleSelector);

        // result
        ListResponse<Sample> result = sampleTransaction.searchSample(sampleSelector);
        List<Sample> resultItems = result.getItems();
        assertAll("resultItems",
          () -> assertEquals(sampleDtos.get(0).getTotal(), result.getTotal()),
          () -> assertEquals(sampleDtos.size(), resultItems.size()),
          () -> assertEquals(sampleDtos.get(0).getId(), resultItems.get(0).getId()),
          () -> assertEquals(sampleDtos.get(0).getName(), resultItems.get(0).getName()),
          () -> assertEquals(sampleDtos.get(0).getBirthday(), resultItems.get(0).getBirthday()),
          () -> assertEquals(sampleDtos.get(0).getIsJapanese(), resultItems.get(0).getIsJapanese()),
          () -> assertEquals(sampleDtos.get(1).getId(), resultItems.get(1).getId()),
          () -> assertEquals(sampleDtos.get(1).getName(), resultItems.get(1).getName()),
          () -> assertEquals(sampleDtos.get(1).getBirthday(), resultItems.get(1).getBirthday()),
          () -> assertEquals(sampleDtos.get(1).getIsJapanese(), resultItems.get(1).getIsJapanese())
        );

        // called functions count
        verify(sampleService, times(1)).search(sampleSelector);
      }

      @Test
      @DisplayName("[空の配列]")
      public void empty() {
        // data
        SampleSelector sampleSelector = SampleSelector.builder().build();

        // mock setting
        doReturn(Collections.emptyList()).when(sampleService).search(sampleSelector);

        // result
        ListResponse<Sample> result = sampleTransaction.searchSample(sampleSelector);
        assertAll("result",
          () -> assertEquals(0, result.getTotal()),
          () -> assertEquals(0, result.getItems().size())
        );

        // called functions count
        verify(sampleService, times(1)).search(sampleSelector);
      }
    }
  }

  @Nested
  @DisplayName("追加")
  class Add {
    @Test
    @DisplayName("add")
    public void add() {
      try (var mockedOperandExecutor = Mockito.mockStatic(OperandExecutor.class)) {
        // data
        Request<Sample> request = new Request<>();
        ResponseSet<Sample> responseSet = new ResponseSet<>();

        // mock setting
        OperandExecutor<Sample, Sample> executor = mock(OperandExecutor.class);
        mockedOperandExecutor.when(() -> OperandExecutor.build(any())).thenReturn(executor);
        doReturn(responseSet).when(executor).run(eq(request), any());

        // result
        assertEquals(responseSet, sampleTransaction.add(request));

        // called functions count
        mockedOperandExecutor.verify(() -> OperandExecutor.build(any()), times(1));
        verify(executor, times(1)).run(request, validator);
      }
    }

    @Nested
    @DisplayName("Executor")
    class Executor {
      private final Sample sample = Sample.builder()
        .name("三浦")
        .birthday(Instant.parse("2000-06-24T12:34:56Z"))
        .isJapanese(Boolean.TRUE)
        .build();

      private boolean assertSampleDto(SampleDto sampleDto) {
        assertAll("assertSampleDto",
          () -> assertEquals(sample.getName(), sampleDto.getName()),
          () -> assertEquals(sample.getBirthday(), sampleDto.getBirthday()),
          () -> assertEquals(sample.getIsJapanese(), sampleDto.getIsJapanese())
        );
        return true;
      }

      @Test
      @DisplayName("[正常系]")
      public void success() {
        // data
        SampleDto sampleDto = SampleDto.builder()
          .id(10)
          .name(sample.getName())
          .birthday(sample.getBirthday())
          .isJapanese(sample.getIsJapanese())
          .build();

        // mock setting
        doReturn(sampleDto).when(sampleService).add(argThat(this::assertSampleDto));

        // result
        Sample result = sampleTransaction.addSample(sample);
        assertAll("result",
          () -> assertEquals(sampleDto.getId(), result.getId()),
          () -> assertEquals(sampleDto.getName(), result.getName()),
          () -> assertEquals(sampleDto.getBirthday(), result.getBirthday()),
          () -> assertEquals(sampleDto.getIsJapanese(), result.getIsJapanese())
        );

        // called functions count
        verify(sampleService, times(1)).add(argThat(this::assertSampleDto));
      }

      @Test
      @DisplayName("[失敗系]")
      public void failure() {
        // mock setting
        doReturn(null).when(sampleService).add(argThat(this::assertSampleDto));

        // result
        assertThrows(NullPointerException.class, () -> sampleTransaction.addSample(sample));

        // called functions count
        verify(sampleService, times(1)).add(argThat(this::assertSampleDto));
      }
    }
  }

  @Nested
  @DisplayName("更新")
  class Edit {
    @Test
    @DisplayName("edit")
    public void edit() {
      try (var mockedOperandExecutor = Mockito.mockStatic(OperandExecutor.class)) {
        // data
        Request<Sample> request = new Request<>();
        ResponseSet<Sample> responseSet = new ResponseSet<>();

        // mock setting
        OperandExecutor<Sample, Sample> executor = mock(OperandExecutor.class);
        mockedOperandExecutor.when(() -> OperandExecutor.build(any())).thenReturn(executor);
        doReturn(responseSet).when(executor).run(eq(request), any());

        // result
        assertEquals(responseSet, sampleTransaction.edit(request));

        // called functions count
        mockedOperandExecutor.verify(() -> OperandExecutor.build(any()), times(1));
        verify(executor, times(1)).run(request, validator);
      }
    }

    @Nested
    @DisplayName("Executor")
    class Executor {

      private final Sample sample = Sample.builder()
        .id(10)
        .name("三浦")
        .birthday(Instant.parse("2000-06-24T12:34:56Z"))
        .isJapanese(Boolean.TRUE)
        .build();

      private boolean assertSampleDto(SampleDto sampleDto) {
        assertAll("assertSampleDto",
          () -> assertEquals(sample.getId(), sampleDto.getId()),
          () -> assertEquals(sample.getName(), sampleDto.getName()),
          () -> assertEquals(sample.getBirthday(), sampleDto.getBirthday()),
          () -> assertEquals(sample.getIsJapanese(), sampleDto.getIsJapanese())
        );
        return true;
      }

      @Test
      @DisplayName("[正常系]")
      public void success() {
        // data
        SampleDto sampleDto = SampleDto.builder()
          .id(10)
          .name(sample.getName())
          .birthday(sample.getBirthday())
          .isJapanese(sample.getIsJapanese())
          .build();

        // mock setting
        doReturn(sampleDto).when(sampleService).edit(argThat(this::assertSampleDto));

        // result
        Sample result = sampleTransaction.editSample(sample);
        assertAll("result",
          () -> assertEquals(sampleDto.getId(), result.getId()),
          () -> assertEquals(sampleDto.getName(), result.getName()),
          () -> assertEquals(sampleDto.getBirthday(), result.getBirthday()),
          () -> assertEquals(sampleDto.getIsJapanese(), result.getIsJapanese())
        );

        // called functions count
        verify(sampleService, times(1)).edit(argThat(this::assertSampleDto));
      }

      @Test
      @DisplayName("[失敗系]")
      public void failure() {
        // mock setting
        doReturn(null).when(sampleService).edit(argThat(this::assertSampleDto));

        // result
        assertThrows(NullPointerException.class, () -> sampleTransaction.editSample(sample));

        // called functions count
        verify(sampleService, times(1)).edit(argThat(this::assertSampleDto));
      }
    }
  }

  @Nested
  @DisplayName("削除")
  class Remove {
    @Test
    @DisplayName("remove")
    public void remove() {
      try (var mockedOperandExecutor = Mockito.mockStatic(OperandExecutor.class)) {
        // data
        Request<Sample> request = new Request<>();
        ResponseSet<Sample> responseSet = new ResponseSet<>();

        // mock setting
        OperandExecutor<Sample, Sample> executor = mock(OperandExecutor.class);
        mockedOperandExecutor.when(() -> OperandExecutor.build(any())).thenReturn(executor);
        doReturn(responseSet).when(executor).run(eq(request), any());

        // result
        assertEquals(responseSet, sampleTransaction.remove(request));

        // called functions count
        mockedOperandExecutor.verify(() -> OperandExecutor.build(any()), times(1));
        verify(executor, times(1)).run(request, validator);
      }
    }

    @Nested
    @DisplayName("Executor")
    class Executor {

      private final Sample sample = Sample.builder()
        .name("三浦")
        .birthday(Instant.parse("2000-06-24T12:34:56Z"))
        .isJapanese(Boolean.TRUE)
        .build();

      private boolean assertSampleDto(SampleDto sampleDto) {
        assertAll("assertSampleDto",
          () -> assertEquals(sample.getName(), sampleDto.getName()),
          () -> assertEquals(sample.getBirthday(), sampleDto.getBirthday()),
          () -> assertEquals(sample.getIsJapanese(), sampleDto.getIsJapanese())
        );
        return true;
      }

      @Test
      @DisplayName("[正常系]")
      public void success() {
        // data
        SampleDto sampleDto = SampleDto.builder()
          .id(10)
          .name(sample.getName())
          .birthday(sample.getBirthday())
          .isJapanese(sample.getIsJapanese())
          .build();

        // mock setting
        doReturn(sampleDto).when(sampleService).remove(argThat(this::assertSampleDto));

        // result
        Sample result = sampleTransaction.removeSample(sample);
        assertAll("result",
          () -> assertEquals(sampleDto.getId(), result.getId()),
          () -> assertEquals(sampleDto.getName(), result.getName()),
          () -> assertEquals(sampleDto.getBirthday(), result.getBirthday()),
          () -> assertEquals(sampleDto.getIsJapanese(), result.getIsJapanese())
        );

        // called functions count
        verify(sampleService, times(1)).remove(argThat(this::assertSampleDto));
      }

      @Test
      @DisplayName("[失敗系]")
      public void failure() {
        // mock setting
        doReturn(null).when(sampleService).remove(argThat(this::assertSampleDto));

        // result
        assertThrows(NullPointerException.class, () -> sampleTransaction.removeSample(sample));

        // called functions count
        verify(sampleService, times(1)).remove(argThat(this::assertSampleDto));
      }
    }
  }
}