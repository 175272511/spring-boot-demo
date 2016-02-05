package com.example.demo.domain;

import java.util.Date;

public class TAuthUserRole {
    private Long id;

    private Long userid;

    private Long roleid;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "TAuthUserRole{" +
                "id=" + id +
                ", userid=" + userid +
                ", roleid=" + roleid +
                ", createtime=" + createtime +
                '}';
    }
}