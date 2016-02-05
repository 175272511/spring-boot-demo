package com.example.demo.mapper;

import com.example.demo.domain.TAuthUser;

import java.util.List;
import java.util.Set;

public interface TAuthUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TAuthUser record);

    int insertSelective(TAuthUser record);

    TAuthUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TAuthUser record);

    int updateByPrimaryKey(TAuthUser record);

    List<TAuthUser> selectUserData();

    Set<String> selectRoles(String username);

    Set<String> selectPermissions(String username);

    TAuthUser selectByUsername(String username);
}