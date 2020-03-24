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



}