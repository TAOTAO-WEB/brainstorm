package edu.hdu.brainstorm.controller;

import edu.hdu.brainstorm.entity.Topic;
import edu.hdu.brainstorm.service.TopicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Topic)表控制层
 *
 * @author makejava
 * @since 2020-03-21 20:32:29
 */
@RestController
@RequestMapping("topic")
public class TopicController {
    /**
     * 服务对象
     */
    @Resource
    private TopicService topicService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Topic selectOne(String id) {
        return this.topicService.queryById(id);
    }

}