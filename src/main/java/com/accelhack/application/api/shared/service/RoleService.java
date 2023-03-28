package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.RoleDto;
import com.accelhack.application.api.shared.dto.RolePolicyDto;

import java.util.List;

public interface RoleService {
  RoleDto create(RoleDto roleDto);

  List<RolePolicyDto> updatePolicies(List<RolePolicyDto> rolePolicyDtoList);

  RoleDto remove(RoleDto roleDto);
}
