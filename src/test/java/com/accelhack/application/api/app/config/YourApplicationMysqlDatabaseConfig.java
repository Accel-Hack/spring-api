package com.accelhack.application.api.app.config;

import com.accelhack.application.api.config.AbstractMysqlDatabaseConfig;
import com.accelhack.application.api.config.DataSourceKeys;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.your-application")
public class YourApplicationMysqlDatabaseConfig extends AbstractMysqlDatabaseConfig {

  @Primary
  @Bean(DataSourceKeys.YOUR_APPLICATION)
  public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() {
    return super.dbUnitDatabaseConnection();
  }
}
