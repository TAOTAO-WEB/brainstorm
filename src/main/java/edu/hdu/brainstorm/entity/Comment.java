package edu.hdu.brainstorm.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2020-03-21 20:32:57
 */
@Data
public class Comment implements Serializable {
    
    private String commentid;
    private String topicid;
    private String userid;
    private String context;
    private Date date;
    private String isdeleted;
}