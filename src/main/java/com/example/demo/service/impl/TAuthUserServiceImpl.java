package com.example.demo.service.impl;

import com.example.demo.domain.TAuthUser;
import com.example.demo.mapper.TAuthUserMapper;
import com.example.demo.service.TAuthUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by liuhui on 2016/2/5.
 */
@Service
public class TAuthUserServiceImpl implements TAuthUserService {

    @Resource
    private TAuthUserMapper tAuthUserMapper;

    @Override
    public List<TAuthUser> getUserInfo() {
        return tAuthUserMapper.selectUserData();
    }

    @Override
    public Set<String> findRoles(String username) {
        return tAuthUserMapper.selectRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return tAuthUserMapper.selectPermissions(username);
    }

    @Override
    public TAuthUser findByUsername(String username) {
        return tAuthUserMapper.selectByUsername(username);
    }
}
