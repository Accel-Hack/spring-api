package com.accelhack.application.api.app.service;

import com.accelhack.application.api.app.dto.SampleDto;
import com.accelhack.application.api.app.entity.SampleSelector;
import com.accelhack.application.api.app.mapper.SampleMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SampleServiceImplSTest {
  @InjectMocks
  private SampleServiceImpl sampleServiceImpl;

  @Mock
  private SampleMapper sampleMapper;

  @Nested
  @DisplayName("取得")
  public class Get {
    @Test
    @DisplayName("[ID(=10)]")
    public void get() {
      // data
      int id = 10;
      SampleDto sampleDto = SampleDto.builder().id(10).build();

      // mock setting
      doReturn(sampleDto).when(sampleMapper).select(id);

      // result
      assertEquals(sampleDto, sampleServiceImpl.get(id));

      // called functions count
      verify(sampleMapper, times(1)).select(id);
    }
  }

  @Nested
  @DisplayName("検索")
  class Search {
    @Test
    @DisplayName("[条件()]")
    public void search() {
      // data
      SampleSelector selector = SampleSelector.builder().build();
      List<SampleDto> sampleDtos = List.of(SampleDto.builder().id(1).build(), SampleDto.builder().id(2).build());

      // mock setting
      doReturn(sampleDtos).when(sampleMapper).selectBy(selector);

      // result
      assertEquals(sampleDtos, sampleServiceImpl.search(selector));

      // called functions count
      verify(sampleMapper, times(1)).selectBy(selector);
    }
  }

  @Nested
  @DisplayName("作成")
  class Add {
    @Test
    @DisplayName("[正常系]")
    public void success() {
      // data
      int newId = 10;
      SampleDto sampleDto = SampleDto.builder()
        .name("miura")
        .birthday(Instant.parse("2000-06-24T12:34:56Z"))
        .isJapanese(Boolean.TRUE)
        .build();

      ArgumentMatcher<SampleDto> matcher = s -> {
        assertAll("ArgumentMatcher<SampleDto>",
          () -> assertEquals(sampleDto.getName(), s.getName()),
          () -> assertEquals(sampleDto.getBirthday(), s.getBirthday()),
          () -> assertEquals(Boolean.TRUE, s.getIsJapanese())
        );
        return true;
      };

      // mock setting
      when(sampleMapper.insert(argThat(matcher))).thenAnswer(invocation -> {
        SampleDto sample = invocation.getArgument(0, SampleDto.class);
        sample.setId(newId);
        return 1;
      });

      // result
      SampleDto result = sampleServiceImpl.add(sampleDto);
      assertAll("result(SampleDto)",
        () -> assertEquals(newId, result.getId()),
        () -> assertEquals(sampleDto.getName(), result.getName()),
        () -> assertEquals(sampleDto.getBirthday(), result.getBirthday()),
        () -> assertEquals(sampleDto.getIsJapanese(), result.getIsJapanese())
      );

      // called functions count
      verify(sampleMapper, times(1)).insert(argThat(matcher));
    }

    @Test
    @DisplayName("[失敗系]")
    public void failure() {
      // data
      SampleDto sampleDto = SampleDto.builder()
        .name("miura")
        .birthday(Instant.parse("2000-06-24T12:34:56Z"))
        .isJapanese(Boolean.TRUE)
        .build();

      ArgumentMatcher<SampleDto> matcher = s -> {
        assertAll("ArgumentMatcher<SampleDto>",
          () -> assertEquals(sampleDto.getName(), s.getName()),
          () -> assertEquals(sampleDto.getBirthday(), s.getBirthday()),
          () -> assertEquals(Boolean.TRUE, s.getIsJapanese())
        );
        return true;
      };

      // mock setting
      doReturn(0).when(sampleMapper).insert(argThat(matcher));

      // result
      assertNull(sampleServiceImpl.add(sampleDto));

      // called functions count
      verify(sampleMapper, times(1)).insert(argThat(matcher));
    }
  }

  @Nested
  @DisplayName("更新")
  class Edit {
    @Test
    @DisplayName("[正常系]")
    public void success() {
      // data
      SampleDto sampleDto = SampleDto.builder()
        .id(10)
        .name("miura")
        .birthday(Instant.parse("2000-06-24T12:34:56Z"))
        .isJapanese(Boolean.TRUE)
        .build();

      ArgumentMatcher<SampleDto> matcher = s -> {
        assertAll("ArgumentMatcher<SampleDto>",
          () -> assertEquals(sampleDto.getId(), s.getId()),
          () -> assertEquals(sampleDto.getName(), s.getName()),
          () -> assertEquals(sampleDto.getBirthday(), s.getBirthday()),
          () -> assertEquals(Boolean.TRUE, s.getIsJapanese())
        );
        return true;
      };

      // mock setting
      doReturn(1).when(sampleMapper).update(argThat(matcher));

      // result
      SampleDto result = sampleServiceImpl.edit(sampleDto);
      assertAll("result(SampleDto)",
        () -> assertEquals(sampleDto.getId(), result.getId()),
        () -> assertEquals(sampleDto.getName(), result.getName()),
        () -> assertEquals(sampleDto.getBirthday(), result.getBirthday()),
        () -> assertEquals(sampleDto.getIsJapanese(), result.getIsJapanese())
      );

      // called functions count
      verify(sampleMapper, times(1)).update(argThat(matcher));
    }

    @Test
    @DisplayName("[失敗系]")
    public void failure() {
      // data
      SampleDto sampleDto = SampleDto.builder()
        .id(10)
        .name("miura")
        .birthday(Instant.parse("2000-06-24T12:34:56Z"))
        .isJapanese(Boolean.TRUE)
        .build();

      ArgumentMatcher<SampleDto> matcher = s -> {
        assertAll("ArgumentMatcher<SampleDto>",
          () -> assertEquals(sampleDto.getId(), s.getId()),
          () -> assertEquals(sampleDto.getName(), s.getName()),
          () -> assertEquals(sampleDto.getBirthday(), s.getBirthday()),
          () -> assertEquals(Boolean.TRUE, s.getIsJapanese())
        );
        return true;
      };

      // mock setting
      doReturn(0).when(sampleMapper).update(argThat(matcher));

      // result
      assertNull(sampleServiceImpl.edit(sampleDto));

      // called functions count
      verify(sampleMapper, times(1)).update(argThat(matcher));
    }
  }

  @Nested
  @DisplayName("削除")
  class Remove {
    @Test
    @DisplayName("[正常系]")
    public void success() {
      // data
      SampleDto sampleDto = SampleDto.builder().id(10).build();

      ArgumentMatcher<SampleDto> matcher = s -> s.getId().equals(sampleDto.getId());

      // mock setting
      doReturn(sampleDto).when(sampleMapper).select(sampleDto.getId());
      doReturn(1).when(sampleMapper).delete(argThat(matcher));

      // result
      SampleDto result = sampleServiceImpl.remove(sampleDto);
      assertEquals(sampleDto.getId(), result.getId());

      // called functions count
      verify(sampleMapper, times(1)).select(sampleDto.getId());
      verify(sampleMapper, times(1)).delete(argThat(matcher));
    }

    @Test
    @DisplayName("[失敗系]")
    public void failure() {
      // data
      SampleDto sampleDto = SampleDto.builder().id(10).build();

      ArgumentMatcher<SampleDto> matcher = s -> s.getId().equals(sampleDto.getId());

      // mock setting
      doReturn(sampleDto).when(sampleMapper).select(sampleDto.getId());
      doReturn(0).when(sampleMapper).delete(argThat(matcher));

      // result
      assertNull(sampleServiceImpl.remove(sampleDto));

      // called functions count
      verify(sampleMapper, times(1)).select(sampleDto.getId());
      verify(sampleMapper, times(1)).delete(argThat(matcher));
    }
  }
}
