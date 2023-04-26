package com.accelhack.application.api.http;

import com.google.common.collect.Streams;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Deprecated
public abstract class OperandExecutor<Req extends Operand, Res> {
  protected abstract Res execute(Req operand);

  public ResponseSet<Res> run(Request<Req> request, Validator validator, Class<?>... groups) {
    ResponseSet<Res> errorResponseSet = request.validate(validator, groups);
    if (Objects.nonNull(errorResponseSet))
      return errorResponseSet;
    List<Response<Res>> results = Streams.mapWithIndex(request.getOperands().stream(),
        (operand, index) -> Response.withResult(index, execute(operand))).toList();
    return ResponseSet.ok(results.size(), results);
  }

  public static <Req extends Operand, Res> OperandExecutor<Req, Res> build(
      Function<Req, Res> function) {
    return new Builder<Req, Res>().function(function).build();
  }

  public static class Builder<Req extends Operand, Res> {
    private Function<Req, Res> _function;

    public Builder<Req, Res> function(Function<Req, Res> function) {
      _function = function;
      return this;
    }

    public OperandExecutor<Req, Res> build() {
      return new OperandExecutor<>() {
        @Override
        public Res execute(Req req) {
          assert Objects.nonNull(_function);
          return _function.apply(req);
        }
      };
    }
  }
}
