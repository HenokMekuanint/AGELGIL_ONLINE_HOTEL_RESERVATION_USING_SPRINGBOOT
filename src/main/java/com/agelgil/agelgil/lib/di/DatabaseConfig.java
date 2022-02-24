package com.agelgil.agelgil.lib.di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Bean
  public DataSource dataSource() {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
  }
}
