package edu.hdu.brainstorm.service.impl;

import edu.hdu.brainstorm.entity.Comment;
import edu.hdu.brainstorm.dao.CommentDao;
import edu.hdu.brainstorm.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2020-03-21 20:32:57
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;
    private int showWordNum = 5;

    /**
     * 通过ID查询单条数据
     *
     * @param commentid 主键
     * @return 实例对象
     */
    @Override
    public Comment queryById(String commentid) {
        return this.commentDao.queryById(commentid);
    }

    @Override
    public List<Comment> queryAllByUserid(String userid){
        List<Comment> comment_list = this.commentDao.queryAllByUserid(userid);
        // 只截取showWordNum个字符展示
        for (Comment comment:comment_list) {
            comment.setContext(comment.getContext().substring(0,showWordNum));
        }
        return comment_list;
    }

    @Override
    public List<Comment> queryAllByTopicid(String topicid) {
        List<Comment> comment_list = this.commentDao.queryAllByTopicid(topicid);
        for (Comment comment:comment_list) {
            if(comment.getContext().length()>=showWordNum){
                comment.setContext(comment.getContext().substring(0,showWordNum));
            }else{
                comment.setContext(comment.getContext());
            }
        }
        return comment_list;
    }


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Comment> queryAllByLimit(int offset, int limit) {
        return this.commentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment insert(Comment comment) {
        this.commentDao.insert(comment);
        return comment;
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment update(Comment comment) {
        this.commentDao.update(comment);
        return this.queryById(comment.getCommentid());
    }

    /**
     * 通过主键删除数据
     *
     * @param commentid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String commentid) {
        return this.commentDao.deleteById(commentid) > 0;
    }
}