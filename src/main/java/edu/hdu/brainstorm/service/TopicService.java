package edu.hdu.brainstorm.service;

import edu.hdu.brainstorm.entity.Topic;
import java.util.List;

/**
 * (Topic)表服务接口
 *
 * @author makejava
 * @since 2020-03-21 20:32:29
 */
public interface TopicService {

    /**
     * 通过ID查询单条数据
     *
     * @param topicid 主键
     * @return 实例对象
     */
    Topic queryById(String topicid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Topic> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    Topic insert(Topic topic);

    /**
     * 修改数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    Topic update(Topic topic);

    /**
     * 通过主键删除数据
     *
     * @param topicid 主键
     * @return 是否成功
     */
    boolean deleteById(String topicid);

}