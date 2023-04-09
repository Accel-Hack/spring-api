package com.accelhack.application.api.shared.security;

import com.accelhack.application.api.shared.controller.base.ExternalController;
import com.accelhack.application.api.shared.filter.HttpServletRequestFilter;
import com.accelhack.application.api.shared.service.JwtUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private final JwtUserDetailsService userDetailsService;
  private final BCryptPasswordEncoder passwordEncoder;
  private final ObjectMapper objectMapper;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
//      .headers().frameOptions().sameOrigin()
//      .and()
      .csrf().disable();

    routePaths(http);
    addFilters(http);
  }

  /**
   * function to routing paths
   * 1. allow external controller
   * 2. others require authentication
   * @param http HttpSecurity
   * @throws Exception exception
   */
  private void routePaths(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers(ExternalController.CONTEXT_PATH + "/**").permitAll()
      .anyRequest().authenticated();
  }

  /**
   * function to add filters
   * 1. cache filter
   * 2. validate login filter
   * 3. validate jwt token filter
   * @param http HttpSecurity
   * @throws Exception exception
   */
  private void addFilters(HttpSecurity http) throws Exception {
    final Filter cache = new HttpServletRequestFilter();
    // FIXME: bean化を試みる
    final Filter authentication = new JwtAuthenticationFilter(authenticationManager(), userDetailsService, objectMapper);
    final Filter authorization = new JwtAuthorizationFilter(authenticationManager());

    http
      .addFilterBefore(cache, UsernamePasswordAuthenticationFilter.class)
      .addFilter(authentication)
      .addFilter(authorization)
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }


  //  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http
//      // disable cors
//      .cors().and().csrf().disable()
//      // authorize requests
//      .authorizeRequests()
//      // application path
//      .antMatchers(UIController.USER_CONTEXT + "/**").hasAnyRole(Actor.USER.getRole(), Actor.SYSTEM.getRole())
//      .antMatchers(UIController.MANAGER_CONTEXT + "/**").hasAnyRole(Actor.MANAGER.getRole(), Actor.SYSTEM.getRole())
//      .antMatchers(UIController.SYSTEM_CONTEXT + "/**").hasAnyRole(Actor.SYSTEM.getRole())
//      // internal api
//      .antMatchers(InternalController.CONTEXT_PATH + "/**").authenticated()
//      // external api
//      .antMatchers(ExternalController.CONTEXT_PATH + "/**").permitAll()
//      .and()
//      // allow signIn for anonymous users
//      .formLogin()
//      .loginPage(SignInController.SIGN_IN_PATH)
//      .defaultSuccessUrl("/signingIn")
//      .and()
//      // for signOut action
//      .logout().logoutUrl("/signOut")
//      .logoutSuccessUrl(SignInController.SIGN_IN_PATH).and()
//      // add authentication provider
//      .authenticationProvider(authProvider())
//      .exceptionHandling()
//      // response for authentication failure
//      .authenticationEntryPoint(authenticationEntryPoint());
//  }
//
//  @Override
//  public void configure(WebSecurity web) {
//    web.ignoring().antMatchers("/sign.bundle.*");
//  }
//
//  private AuthenticationProvider authProvider() {
//    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//    provider.setUserDetailsService(userDetailsService);
//    provider.setPasswordEncoder(passwordEncoder);
//    return provider;
//  }
//
//  public AuthenticationEntryPoint authenticationEntryPoint() {
//    return new LoginUrlAuthenticationEntryPoint(SignInController.SIGN_IN_PATH) {
//      @Override
//      public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException, IOException {
//        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
//          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//          return;
//        }
//        super.commence(request, response, authException);
//      }
//    };
//  }
}
