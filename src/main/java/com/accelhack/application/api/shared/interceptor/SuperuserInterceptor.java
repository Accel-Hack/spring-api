//package com.accelhack.application.api.shared.interceptor;
//
//import com.accelhack.application.api.shared.dto.SuperuserDto;
//import com.accelhack.application.api.shared.dto.UserDto;
//import com.accelhack.application.api.shared.enums.Actor;
//import com.accelhack.application.api.shared.exception.NotImplementedException;
//import com.accelhack.application.api.shared.registry.AuthRegistry;
//import com.accelhack.application.api.shared.transaction.SuperuserTransaction;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Objects;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class SuperuserInterceptor implements HandlerInterceptor {
//  private final AuthRegistry authRegistry;
//  private final SuperuserTransaction superuserTransaction;
//
//  private boolean isValidSession(Actor actor) {
//    UserDto userDto = authRegistry.getLoginUser();
//    if (!userDto.getActor().equals(Actor.SYSTEM)) {
//      // not superuser
//      return true;
//    }
//    SuperuserDto superuserDto = superuserTransaction.getSession(userDto.getId());
//    if (Objects.isNull(superuserDto)) {
//      log.info("superuser without session");
//      return false;
//    }
//    if (superuserDto.getTargetUser().getActor() != actor) {
//      log.info("superuser with wrong session");
//      return false;
//    }
//    // proper sudo
//    superuserTransaction.updateExpireTime(superuserDto);
//    return true;
//  }
//
//  @Override
//  public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
//    String requestUri = request.getRequestURI();
//
//    Actor actor;
//    if (requestUri.startsWith(UIController.MANAGER_CONTEXT)) {
//      actor = Actor.MANAGER;
//    } else if (requestUri.startsWith(UIController.USER_CONTEXT)) {
//      actor = Actor.USER;
//    } else {
//      throw new NotImplementedException();
//    }
//
//    if (isValidSession(actor)) {
//      return true;
//    }
//
//    response.sendRedirect("/error");
//    return false;
//  }
//}
