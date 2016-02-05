package com.example.demo.mapper;

import com.example.demo.domain.TAuthRolePermission;

public interface TAuthRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAuthRolePermission record);

    int insertSelective(TAuthRolePermission record);

    TAuthRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAuthRolePermission record);

    int updateByPrimaryKey(TAuthRolePermission record);
}