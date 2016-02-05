package com.example.demo.mapper;

import com.example.demo.domain.TAuthPermission;

public interface TAuthPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAuthPermission record);

    int insertSelective(TAuthPermission record);

    TAuthPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAuthPermission record);

    int updateByPrimaryKey(TAuthPermission record);
}