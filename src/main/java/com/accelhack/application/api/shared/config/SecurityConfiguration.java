package com.accelhack.application.api.shared.config;

import com.accelhack.application.api.shared.controller.SignInController;
import com.accelhack.application.api.shared.controller.UIController;
import com.accelhack.application.api.shared.controller.base.ExternalController;
import com.accelhack.application.api.shared.controller.base.InternalController;
import com.accelhack.application.api.shared.enums.Actor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      // disable cors
      .cors().and().csrf().disable()
      // authorize requests
      .authorizeRequests()
      // application path
      .antMatchers(UIController.USER_CONTEXT + "/**").hasAnyRole(Actor.USER.getRole(), Actor.SYSTEM.getRole())
      .antMatchers(UIController.MANAGER_CONTEXT + "/**").hasAnyRole(Actor.MANAGER.getRole(), Actor.SYSTEM.getRole())
      .antMatchers(UIController.SYSTEM_CONTEXT + "/**").hasAnyRole(Actor.SYSTEM.getRole())
      // internal api
      .antMatchers(InternalController.CONTEXT_PATH + "/**").authenticated()
      // external api
      .antMatchers(ExternalController.CONTEXT_PATH + "/**").permitAll()
      .and()
      // allow signIn for anonymous users
      .formLogin()
      .loginPage(SignInController.SIGN_IN_PATH)
      .defaultSuccessUrl("/signingIn")
      .and()
      // for signOut action
      .logout().logoutUrl("/signOut")
      .logoutSuccessUrl(SignInController.SIGN_IN_PATH).and()
      // add authentication provider
      .authenticationProvider(authProvider())
      .exceptionHandling()
      // response for authentication failure
      .authenticationEntryPoint(authenticationEntryPoint());
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/sign.bundle.*");
  }

  private AuthenticationProvider authProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    return provider;
  }

  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new LoginUrlAuthenticationEntryPoint(SignInController.SIGN_IN_PATH) {
      @Override
      public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException, IOException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }
        super.commence(request, response, authException);
      }
    };
  }
}
