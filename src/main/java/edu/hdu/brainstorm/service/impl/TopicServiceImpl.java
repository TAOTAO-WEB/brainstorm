package edu.hdu.brainstorm.service.impl;

import edu.hdu.brainstorm.entity.Topic;
import edu.hdu.brainstorm.dao.TopicDao;
import edu.hdu.brainstorm.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Topic)表服务实现类
 *
 * @author makejava
 * @since 2020-03-21 20:32:29
 */
@Service("topicService")
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicDao topicDao;
    private int showWordNum = 5;

    /**
     * 通过ID查询单条数据
     *
     * @param topicid 主键
     * @return 实例对象
     */
    @Override
    public Topic queryById(String topicid) {
        return this.topicDao.queryById(topicid);
    }

    @Override
    public List<Topic> queryAllByUserid(String userid){
        List<Topic> topic_list = this.topicDao.queryAllByUserid(userid);
        return getNumWord(topic_list);
    }
    @Override
    public List<Topic> queryAll(String order){
        List<Topic> topic_list = this.topicDao.queryAll(order);
        return getNumWord(topic_list);
    }

    public List<Topic> getNumWord(List<Topic> topic_list){
        // 只截取showWordNum个字符展示
        for (Topic topic:topic_list) {
            topic.setDescription(topic.getDescription().substring(0,showWordNum));
        }
        return topic_list;
    };
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Topic> queryAllByLimit(int offset, int limit) {
        return this.topicDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    @Override
    public Topic insert(Topic topic) {
        this.topicDao.insert(topic);
        return topic;
    }

    /**
     * 修改数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    @Override
    public Topic update(Topic topic) {
        this.topicDao.update(topic);
        return this.queryById(topic.getTopicid());
    }

    /**
     * 通过主键删除数据
     *
     * @param topicid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String topicid) {
        return this.topicDao.deleteById(topicid) > 0;
    }
}