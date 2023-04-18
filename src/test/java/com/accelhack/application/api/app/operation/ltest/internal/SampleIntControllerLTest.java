package com.accelhack.application.api.app.operation.ltest.internal;

import com.accelhack.accelparts.Request;
import com.accelhack.application.api.app.entity.Sample;
import com.accelhack.application.api.app.entity.SampleSelector;
import com.accelhack.application.api.config.DataSourceKeys;
import com.accelhack.application.api.dataset.CsvDataSetLoader;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("FIXME: データベースの時間を合わせる")
public class SampleIntControllerLTest extends BaseIntControllerLTest {
  @BeforeEach
  void setUp() {
    setController();
  }

  @Nested
  @DisplayName("取得")
  @DbUnitConfiguration(databaseConnection = {DataSourceKeys.YOUR_APPLICATION, DataSourceKeys.API_APPLICATION}, dataSetLoader = CsvDataSetLoader.class)
  class Get {
    @Test
    @DisplayName("POST: /api/int/v1/sample/get")
    @DatabaseSetups({
      @DatabaseSetup(value = "/database/testdata/ltest/init/app", connection = DataSourceKeys.YOUR_APPLICATION),
      @DatabaseSetup(value = "/database/testdata/ltest/init/shared", connection = DataSourceKeys.API_APPLICATION)
    })
    public void get() throws Exception {
      // data
      Sample sample = Sample.builder().id(1).build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      // result
      performPost("/api/int/v1/sample/get", request)
        // check status
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
        .andExpect(jsonPath("$.total").value(1))
        .andExpect(jsonPath("$.results[0].operationKey").value(0))
        .andExpect(jsonPath("$.results[0].operationStatus").value(true))
        .andExpect(jsonPath("$.results[0].result.id").value(1))
        .andExpect(jsonPath("$.results[0].result.name").value("miura"))
        .andExpect(jsonPath("$.results[0].result.birthday").value("2000-06-24T00:00:00Z"))
        .andExpect(jsonPath("$.results[0].result.isJapanese").value(Boolean.TRUE));
    }
  }

  @Nested
  @DisplayName("検索")
  @DbUnitConfiguration(databaseConnection = {DataSourceKeys.YOUR_APPLICATION, DataSourceKeys.API_APPLICATION}, dataSetLoader = CsvDataSetLoader.class)
  class Search {
    @Test
    @DisplayName("POST: /api/int/v1/sample/search")
    @DatabaseSetups({
      @DatabaseSetup(value = "/database/testdata/ltest/init/app", connection = DataSourceKeys.YOUR_APPLICATION),
      @DatabaseSetup(value = "/database/testdata/ltest/init/shared", connection = DataSourceKeys.API_APPLICATION)
    })
    public void search() throws Exception {
      // data
      SampleSelector selector = SampleSelector.builder().limit(2).offset(1).build();
      Request<SampleSelector> request = new Request<>();
      request.setOperands(List.of(selector));

      // result
      performPost("/api/int/v1/sample/search", request)
        // check status
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
        .andExpect(jsonPath("$.total").value(1))
        .andExpect(jsonPath("$.results[0].operationKey").value(0))
        .andExpect(jsonPath("$.results[0].operationStatus").value(true))
        .andExpect(jsonPath("$.results[0].result.total").value(3))
        .andExpect(jsonPath("$.results[0].result.start").value(0))
        .andExpect(jsonPath("$.results[0].result.items[0].id").value(2))
        .andExpect(jsonPath("$.results[0].result.items[0].name").value("tanaka"))
        .andExpect(jsonPath("$.results[0].result.items[0].birthday").value("2001-09-21T00:00:00Z"))
        .andExpect(jsonPath("$.results[0].result.items[0].isJapanese").value(Boolean.TRUE))
        .andExpect(jsonPath("$.results[0].result.items[1].id").value(3))
        .andExpect(jsonPath("$.results[0].result.items[1].name").value("Tom"))
        .andExpect(jsonPath("$.results[0].result.items[1].birthday").value("1998-02-09T00:00:00Z"))
        .andExpect(jsonPath("$.results[0].result.items[1].isJapanese").value(Boolean.FALSE));
    }
  }

  @Nested
  @DisplayName("追加")
  @DbUnitConfiguration(databaseConnection = {DataSourceKeys.YOUR_APPLICATION, DataSourceKeys.API_APPLICATION}, dataSetLoader = CsvDataSetLoader.class)
  class Add {
    @Test
    @DisplayName("POST: /api/int/v1/sample/add")
    @DatabaseSetups({
      @DatabaseSetup(value = "/database/testdata/ltest/init/app", connection = DataSourceKeys.YOUR_APPLICATION),
      @DatabaseSetup(value = "/database/testdata/ltest/init/shared", connection = DataSourceKeys.API_APPLICATION)
    })
    @ExpectedDatabase(value = "/database/testdata/ltest/result/SampleIntControllerImplLTest/create-expected", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void add() throws Exception {
      // data
      Sample sample = Sample.builder()
        .name("takahashi")
        .birthday(Instant.parse("2000-09-05T00:00:00Z"))
        .isJapanese(Boolean.TRUE)
        .build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      // result
      performPost("/api/int/v1/sample/add", request)
        // check status
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
        .andExpect(jsonPath("$.total").value(1))
        .andExpect(jsonPath("$.results[0].operationKey").value(0))
        .andExpect(jsonPath("$.results[0].operationStatus").value(true))
        .andExpect(jsonPath("$.results[0].result.id").value(notNullValue()))
        .andExpect(jsonPath("$.results[0].result.name").value("takahashi"))
        .andExpect(jsonPath("$.results[0].result.birthday").value("2000-09-05T00:00:00Z"))
        .andExpect(jsonPath("$.results[0].result.isJapanese").value(Boolean.TRUE));
    }
  }

  @Nested
  @DisplayName("更新")
  @DbUnitConfiguration(databaseConnection = {DataSourceKeys.YOUR_APPLICATION, DataSourceKeys.API_APPLICATION}, dataSetLoader = CsvDataSetLoader.class)
  class Edit {
    @Test
    @DisplayName("POST: /api/int/v1/sample/edit")
    @DatabaseSetups({
      @DatabaseSetup(value = "/database/testdata/ltest/init/app", connection = DataSourceKeys.YOUR_APPLICATION),
      @DatabaseSetup(value = "/database/testdata/ltest/init/shared", connection = DataSourceKeys.API_APPLICATION)
    })
    @ExpectedDatabase(value = "/database/testdata/ltest/result/SampleIntControllerImplLTest/update-expected", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void edit() throws Exception {
      // data
      Sample sample = Sample.builder()
        .id(2)
        .name("takahashi")
        .birthday(Instant.parse("2000-09-05T00:00:00Z"))
        .isJapanese(Boolean.TRUE)
        .build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      // result
      performPost("/api/int/v1/sample/edit", request)
        // check status
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
        .andExpect(jsonPath("$.total").value(1))
        .andExpect(jsonPath("$.results[0].operationKey").value(0))
        .andExpect(jsonPath("$.results[0].operationStatus").value(true))
        .andExpect(jsonPath("$.results[0].result.id").value(2))
        .andExpect(jsonPath("$.results[0].result.name").value("takahashi"))
        .andExpect(jsonPath("$.results[0].result.birthday").value("2000-09-05T00:00:00Z"))
        .andExpect(jsonPath("$.results[0].result.isJapanese").value(Boolean.TRUE));
    }
  }

  @Nested
  @DisplayName("削除")
  @DbUnitConfiguration(databaseConnection = {DataSourceKeys.YOUR_APPLICATION, DataSourceKeys.API_APPLICATION}, dataSetLoader = CsvDataSetLoader.class)
  class Remove {
    @Test
    @DisplayName("POST: /api/int/v1/sample/remove")
    @DatabaseSetups({
      @DatabaseSetup(value = "/database/testdata/ltest/init/app", connection = DataSourceKeys.YOUR_APPLICATION),
      @DatabaseSetup(value = "/database/testdata/ltest/init/shared", connection = DataSourceKeys.API_APPLICATION)
    })
    @ExpectedDatabase(value = "/database/testdata/ltest/result/SampleIntControllerImplLTest/delete-expected", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void remove() throws Exception {
      // data
      Sample sample = Sample.builder()
        .id(2)
        .name("tanaka")
        .birthday(Instant.parse("2001-09-21T00:00:00Z"))
        .isJapanese(Boolean.TRUE)
        .build();
      Request<Sample> request = new Request<>();
      request.setOperands(List.of(sample));

      // result
      performPost("/api/int/v1/sample/remove", request)
        // check status
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()))
        .andExpect(jsonPath("$.total").value(1))
        .andExpect(jsonPath("$.results[0].operationKey").value(0))
        .andExpect(jsonPath("$.results[0].operationStatus").value(true))
        .andExpect(jsonPath("$.results[0].result.id").value(2))
        .andExpect(jsonPath("$.results[0].result.name").value("tanaka"))
        .andExpect(jsonPath("$.results[0].result.birthday").value("2001-09-21T00:00:00Z"))
        .andExpect(jsonPath("$.results[0].result.isJapanese").value(Boolean.TRUE));
    }
  }

}
