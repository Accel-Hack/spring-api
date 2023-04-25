package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
  private final UserService UserService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDto userDto = UserService.getByUsername(username);
    if (Objects.isNull(userDto)) {
      throw new UsernameNotFoundException("");
    }

    return org.springframework.security.core.userdetails.User.builder()
      .username(userDto.getUsername())
      .password(userDto.getPassword())
      .authorities(List.of(userDto.getActor().toAuthority()))
      .build();
  }
}
