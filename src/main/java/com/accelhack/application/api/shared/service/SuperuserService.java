package com.accelhack.application.api.shared.service;

import com.accelhack.application.api.shared.dto.SuperuserDto;

public interface SuperuserService {
  /**
   * get user info by id(primary key)
   * 
   * @param id user id
   * @return SuperuserDto
   */
  SuperuserDto getById(int id);

  /**
   * create entity for the superuser
   * 
   * @param superuserDto entity to create
   * @return SuperuserDto
   */
  SuperuserDto create(SuperuserDto superuserDto);

  /**
   * update the limit of superuser
   * 
   * @param superuserDto entity to update
   * @return SuperuserDto
   */
  SuperuserDto updateExpireTime(SuperuserDto superuserDto);

  /**
   * delete all the superuser for the source user
   * 
   * @param superuserDto entity to delete
   * @return count of deleted records
   */
  SuperuserDto delete(SuperuserDto superuserDto);
}
