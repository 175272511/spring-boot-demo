package com.example.demo.domain;

import java.util.Date;

public class TAuthPermission {
    private Long id;

    private String permissionname;

    private String permissionsign;

    private String href;

    private String description;

    private Long status;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname == null ? null : permissionname.trim();
    }

    public String getPermissionsign() {
        return permissionsign;
    }

    public void setPermissionsign(String permissionsign) {
        this.permissionsign = permissionsign == null ? null : permissionsign.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "TAuthPermission{" +
                "id=" + id +
                ", permissionname='" + permissionname + '\'' +
                ", permissionsign='" + permissionsign + '\'' +
                ", href='" + href + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createtime=" + createtime +
                '}';
    }
}