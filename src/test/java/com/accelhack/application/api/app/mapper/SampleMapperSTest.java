package com.accelhack.application.api.app.mapper;

import com.accelhack.application.api.app.dto.SampleDto;
import com.accelhack.application.api.app.entity.SampleSelector;
import com.accelhack.application.api.config.DataSourceKeys;
import com.accelhack.application.api.dataset.CsvDataSetLoader;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("FIXME: データベースの時間を合わせる")
@ActiveProfiles("unit")
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionDbUnitTestExecutionListener.class})
public class SampleMapperSTest {
  @Autowired
  SampleMapper sampleMapper;

  @Nested
  @DisplayName("取得")
  @DbUnitConfiguration(databaseConnection = DataSourceKeys.YOUR_APPLICATION,
      dataSetLoader = CsvDataSetLoader.class)
  class Select {
    @Test
    @DisplayName("[存在するID(=2)]")
    @DatabaseSetup(value = "/database/testdata/app/SampleMapperSTest/select-init",
        connection = DataSourceKeys.YOUR_APPLICATION)
    public void success() {
      // data
      int id = 2;

      // use target function
      SampleDto sampleDto = sampleMapper.select(id);

      // check value
      assertAll("sampleDto", () -> assertEquals(2, sampleDto.getId()),
          () -> assertEquals("tanaka", sampleDto.getName()),
          () -> assertEquals(Instant.parse("2001-09-21T00:00:00Z"), sampleDto.getBirthday()),
          () -> assertEquals(Boolean.TRUE, sampleDto.getIsJapanese()));
    }

    @Test
    @DisplayName("[存在しないID(=99)")
    @DatabaseSetup(value = "/database/testdata/app/SampleMapperSTest/select-init",
        connection = DataSourceKeys.YOUR_APPLICATION)
    public void failure() {
      // data
      int id = 99;

      // use target function
      SampleDto sampleDto = sampleMapper.select(id);

      // check value
      assertNull(sampleDto);
    }
  }

  @Nested
  @DisplayName("検索")
  @DbUnitConfiguration(databaseConnection = {DataSourceKeys.YOUR_APPLICATION},
      dataSetLoader = CsvDataSetLoader.class)
  class SelectBy {
    @Test
    @DisplayName("[条件()]")
    @DatabaseSetup(value = "/database/testdata/app/SampleMapperSTest/selectBy-init",
        connection = DataSourceKeys.YOUR_APPLICATION)
    public void selectBy() {
      // data
      SampleSelector selector = SampleSelector.builder().limit(2).offset(1).build();

      // use target function
      List<SampleDto> sampleDtoList = sampleMapper.selectBy(selector);

      // check value
      assertAll("sampleDtoList", () -> assertEquals(2, sampleDtoList.size()),
          () -> assertAll("sampleDtoList.get(0)",
              () -> assertEquals(2, sampleDtoList.get(0).getId()),
              () -> assertEquals("tanaka", sampleDtoList.get(0).getName()),
              () -> assertEquals(Instant.parse("2001-09-21T00:00:00Z"),
                  sampleDtoList.get(0).getBirthday()),
              () -> assertEquals(Boolean.TRUE, sampleDtoList.get(0).getIsJapanese())),
          () -> assertAll("sampleDtoList.get(1)",
              () -> assertEquals(3, sampleDtoList.get(1).getId()),
              () -> assertEquals("Tom", sampleDtoList.get(1).getName()),
              () -> assertEquals(Instant.parse("1998-02-09T00:00:00Z"),
                  sampleDtoList.get(1).getBirthday()),
              () -> assertEquals(Boolean.FALSE, sampleDtoList.get(1).getIsJapanese())));
    }
  }

  @Nested
  @DisplayName("作成")
  @DbUnitConfiguration(databaseConnection = {DataSourceKeys.YOUR_APPLICATION},
      dataSetLoader = CsvDataSetLoader.class)
  class Add {
    @Test
    @DisplayName("[正常系]")
    @DatabaseSetup(value = "/database/testdata/app/SampleMapperSTest/insert-init",
        connection = DataSourceKeys.YOUR_APPLICATION)
    @ExpectedDatabase(value = "/database/testdata/app/SampleMapperSTest/insert-expected",
        assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void success() {
      // data
      SampleDto sampleDto = SampleDto.builder().name("takahashi")
          .birthday(Instant.parse("2000-09-05T00:00:00Z")).isJapanese(Boolean.TRUE).build();

      // use target function
      int result = sampleMapper.insert(sampleDto);

      // check value
      assertAll("result", () -> assertEquals(1, result), () -> assertNotNull(sampleDto.getId()));
    }
  }

  @Nested
  @DisplayName("更新")
  @DbUnitConfiguration(databaseConnection = {DataSourceKeys.YOUR_APPLICATION},
      dataSetLoader = CsvDataSetLoader.class)
  class Update {
    @Test
    @DisplayName("[正常系]")
    @DatabaseSetup(value = "/database/testdata/app/SampleMapperSTest/update-init",
        connection = DataSourceKeys.YOUR_APPLICATION)
    @ExpectedDatabase(value = "/database/testdata/app/SampleMapperSTest/update-expected1",
        assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void success() {
      // data
      SampleDto sampleDto = SampleDto.builder().id(2).name("takahashi")
          .birthday(Instant.parse("2000-09-05T00:00:00Z")).isJapanese(Boolean.TRUE).build();

      // use target function
      int result = sampleMapper.update(sampleDto);

      // check value
      assertEquals(1, result);
    }

    @Test
    @DisplayName("[失敗系]")
    @DatabaseSetup(value = "/database/testdata/app/SampleMapperSTest/update-init",
        connection = DataSourceKeys.YOUR_APPLICATION)
    @ExpectedDatabase(value = "/database/testdata/app/SampleMapperSTest/update-expected2",
        assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void failure() {
      // data
      SampleDto sampleDto = SampleDto.builder().id(99).name("takahashi")
          .birthday(Instant.parse("2000-09-05T00:00:00Z")).isJapanese(Boolean.TRUE).build();

      // use target function
      int result = sampleMapper.update(sampleDto);

      // check value
      assertEquals(0, result);
    }
  }

  @Nested
  @DisplayName("削除")
  @DbUnitConfiguration(databaseConnection = {DataSourceKeys.YOUR_APPLICATION},
      dataSetLoader = CsvDataSetLoader.class)
  class Delete {
    @Test
    @DisplayName("[正常系]")
    @DatabaseSetup(value = "/database/testdata/app/SampleMapperSTest/delete-init",
        connection = DataSourceKeys.YOUR_APPLICATION)
    @ExpectedDatabase(value = "/database/testdata/app/SampleMapperSTest/delete-expected1",
        assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void success() {
      // data
      SampleDto sampleDto = SampleDto.builder().id(2).build();

      // use target function
      int result = sampleMapper.delete(sampleDto);

      // check value
      assertEquals(1, result);
    }

    @Test
    @DisplayName("[失敗系]")
    @DatabaseSetup(value = "/database/testdata/app/SampleMapperSTest/delete-init",
        connection = DataSourceKeys.YOUR_APPLICATION)
    @ExpectedDatabase(value = "/database/testdata/app/SampleMapperSTest/delete-expected2",
        assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void failure() {
      // data
      SampleDto sampleDto = SampleDto.builder().id(99).build();

      // use target function
      int result = sampleMapper.delete(sampleDto);

      // check value
      assertEquals(0, result);
    }
  }
}
