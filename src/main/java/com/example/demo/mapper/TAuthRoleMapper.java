package com.example.demo.mapper;

import com.example.demo.domain.TAuthRole;

public interface TAuthRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAuthRole record);

    int insertSelective(TAuthRole record);

    TAuthRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAuthRole record);

    int updateByPrimaryKey(TAuthRole record);
}