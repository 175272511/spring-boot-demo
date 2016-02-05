package com.example.demo.service;

import com.example.demo.domain.TAuthUser;

import java.util.List;
import java.util.Set;

/**
 * Created by liuhui on 2016/2/5.
 */
public interface TAuthUserService {

    List<TAuthUser> getUserInfo();

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

    TAuthUser findByUsername(String username);
}
