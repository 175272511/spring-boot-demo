package com.example.demo.domain;

import java.util.Date;

public class TAuthRole {
    private Long id;

    private String rolename;

    private String rolesign;

    private String desc;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRolesign() {
        return rolesign;
    }

    public void setRolesign(String rolesign) {
        this.rolesign = rolesign == null ? null : rolesign.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "TAuthRole{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", rolesign='" + rolesign + '\'' +
                ", desc='" + desc + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}