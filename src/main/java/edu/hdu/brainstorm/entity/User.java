package edu.hdu.brainstorm.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-03-21 20:20:05
 */
@Data
public class User implements Serializable {
    
    private String userid;
    private String username;
    private String userpassword;
    private Date date;
    private Object pic;

    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public Date getDate() {
        return date;
    }

    public Object getPic() {
        return pic;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPic(Object pic) {
        this.pic = pic;
    }
}