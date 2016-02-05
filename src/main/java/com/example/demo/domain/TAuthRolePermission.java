package com.example.demo.domain;

import java.util.Date;

public class TAuthRolePermission {
    private Long id;

    private Long roleid;

    private Long permissionid;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Long permissionid) {
        this.permissionid = permissionid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "TAuthRolePermission{" +
                "id=" + id +
                ", roleid=" + roleid +
                ", permissionid=" + permissionid +
                ", createtime=" + createtime +
                '}';
    }
}