package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.*;
import com.accelhack.application.api.shared.mapper.*;
import com.accelhack.application.api.shared.registry.AuthRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
  private final AuthRegistry authRegistry;
  private final RoleMapper roleMapper;
  private final UserRoleMapper userRoleMapper;
  private final RolePolicyMapper rolePolicyMapper;

  @Override
  public RoleDto create(RoleDto roleDto) {
    RoleDto role = roleDto.toCreate();
    roleMapper.insert(role, authRegistry.requireAuthorizedOperator());
    return role;
  }

  @Override
  public List<RolePolicyDto> updatePolicies(List<RolePolicyDto> rolePolicyDtoList) {
    assert rolePolicyDtoList.stream().map(RolePolicyDto::getRoleId).distinct().count() != 1;

    // Delete bind with policies
    int roleId = rolePolicyDtoList.stream().map(RolePolicyDto::getRoleId).distinct().toList().get(0);
    deleteBindPolicies(roleId);

    // Add new bind with policies
    List<RolePolicyDto> rolePolicies = rolePolicyDtoList.stream().map(RolePolicyDto::toCreate).toList();
    rolePolicyMapper.insert(rolePolicies, authRegistry.requireAuthorizedOperator());

    return rolePolicies;
  }

  @Override
  public RoleDto remove(RoleDto roleDto) {
    // Delete role
    RoleDto role = roleDto.toDelete();
    roleMapper.delete(role, authRegistry.requireAuthorizedOperator());

    // Delete bind with policies
    deleteBindPolicies(role.getId());

    // Delete bind with users
    UserRoleDto userRole = UserRoleDto.builder().roleId(role.getId()).build().toDelete();
    userRoleMapper.delete(userRole, authRegistry.requireAuthorizedOperator());

    return role;
  }

  private void deleteBindPolicies(int roleId) {
    RolePolicyDto rolePolicyDto = RolePolicyDto.builder().roleId(roleId).build().toDelete();
    rolePolicyMapper.delete(rolePolicyDto, authRegistry.requireAuthorizedOperator());
  }
}
