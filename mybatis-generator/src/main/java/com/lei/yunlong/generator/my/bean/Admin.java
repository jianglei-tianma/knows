package com.lei.yunlong.generator.my.bean;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_admin")
public class Admin implements Serializable {
    @Id
    private Integer id;

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private String createtime;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return loginacct
     */
    public String getLoginacct() {
        return loginacct;
    }

    /**
     * @param loginacct
     */
    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct == null ? null : loginacct.trim();
    }

    /**
     * @return userpswd
     */
    public String getUserpswd() {
        return userpswd;
    }

    /**
     * @param userpswd
     */
    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd == null ? null : userpswd.trim();
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return createtime
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginacct=").append(loginacct);
        sb.append(", userpswd=").append(userpswd);
        sb.append(", username=").append(username);
        sb.append(", email=").append(email);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}