package com.accelhack.application.api.shared.functional;

import com.accelhack.accelparts.*;

@FunctionalInterface
public interface ParameterizedApi<O extends Operand, R> {
  /**
   * Computes a result, or throws an exception if unable to do so.
   * @return computed result
   * @throws Exception if unable to compute a result
   */
  ResponseSet<R> call(Request<O> param) throws Exception;
}
