package edu.hdu.brainstorm.dao;

import edu.hdu.brainstorm.entity.Comment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-21 20:32:57
 */
public interface CommentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param commentid 主键
     * @return 实例对象
     */
    Comment queryById(String commentid);
    /**
     * 通过userid查询个人的所有主题帖
     *
     * @param userid
     * @return
     */
    List<Comment> queryAllByUserid(@Param("userid") String userid);

    /**
     * 通过topicid查询某个话题的所有评论
     *
     * @param topic
     * @return
     */
    List<Comment> queryAllByTopicid(@Param("topicid") String topic);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param comment 实例对象
     * @return 对象列表
     */
    List<Comment> queryAll(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param commentid 主键
     * @return 影响行数
     */
    int deleteById(String commentid);

}