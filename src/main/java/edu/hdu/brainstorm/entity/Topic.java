package edu.hdu.brainstorm.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Topic)实体类
 *
 * @author makejava
 * @since 2020-03-21 20:32:29
 */
@Data
public class Topic implements Serializable {
    
    private String topicid;
    private String userid;
    private String context;
    private String description;
    private String tag;
    private Date date;
    private String isdeleted;

}