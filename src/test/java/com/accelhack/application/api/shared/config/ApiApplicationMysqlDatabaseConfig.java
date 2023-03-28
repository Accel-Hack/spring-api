package com.accelhack.application.api.shared.config;

import com.accelhack.application.api.config.AbstractMysqlDatabaseConfig;
import com.accelhack.application.api.config.DataSourceKeys;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.api-application")
public class ApiApplicationMysqlDatabaseConfig extends AbstractMysqlDatabaseConfig {

  @Primary
  @Bean(DataSourceKeys.API_APPLICATION)
  public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() {
    return super.dbUnitDatabaseConnection();
  }
}
