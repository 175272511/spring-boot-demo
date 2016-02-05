package com.example.demo.mapper;

import com.example.demo.domain.TAuthUserRole;

public interface TAuthUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAuthUserRole record);

    int insertSelective(TAuthUserRole record);

    TAuthUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAuthUserRole record);

    int updateByPrimaryKey(TAuthUserRole record);
}