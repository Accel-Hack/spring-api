package com.accelhack.spring.api.shared.handler;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.accelhack.spring.api.domain.model.shared.Operator;

import java.sql.*;

@RequiredArgsConstructor
public class OperatorHandler extends BaseTypeHandler<Operator> {

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Operator operator,
      JdbcType jdbcType) throws SQLException {
    preparedStatement.setObject(i, operator.getCode());
  }

  @Override
  public Operator getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return new Operator(resultSet.getString(s));
  }

  @Override
  public Operator getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return new Operator(resultSet.getString(i));
  }

  @Override
  public Operator getNullableResult(CallableStatement callableStatement, int i)
      throws SQLException {
    return new Operator(callableStatement.getString(i));
  }
}

