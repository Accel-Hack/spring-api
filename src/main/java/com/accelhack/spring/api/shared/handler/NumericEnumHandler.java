package com.accelhack.spring.api.shared.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.accelhack.spring.api.domain.model.user.Actor;
import com.accelhack.spring.api.domain.model.user.NumericEnum;

import lombok.RequiredArgsConstructor;

@MappedTypes({Actor.class})
@RequiredArgsConstructor
public class NumericEnumHandler<E extends Enum<E> & NumericEnum<E>>
    extends BaseTypeHandler<NumericEnum<E>> {

  private final Class<E> type;

  private boolean equals(int right, int left) {
    return right == left;
  }

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i,
      NumericEnum<E> eNumericEnum, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i, eNumericEnum.getCode());
  }

  @Override
  public NumericEnum<E> getNullableResult(ResultSet resultSet, String s) throws SQLException {
    final int code = resultSet.getInt(s);
    Predicate<E> filter = e -> equals(e.getCode(), code);
    return NumericEnum.getByCode(type, filter);
  }

  @Override
  public NumericEnum<E> getNullableResult(ResultSet resultSet, int i) throws SQLException {
    final int code = resultSet.getInt(i);
    Predicate<E> filter = e -> equals(e.getCode(), code);
    return NumericEnum.getByCode(type, filter);
  }

  @Override
  public NumericEnum<E> getNullableResult(CallableStatement callableStatement, int i)
      throws SQLException {
    final int code = callableStatement.getInt(i);
    Predicate<E> filter = e -> equals(e.getCode(), code);
    return NumericEnum.getByCode(type, filter);
  }
}
