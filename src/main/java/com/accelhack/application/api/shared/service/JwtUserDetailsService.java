package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.base.domain.User;
import com.accelhack.application.api.base.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (Objects.isNull(user))
      throw new UsernameNotFoundException("User not found:[%s]".formatted(username));

    return user.toUserDetails();
  }
}
