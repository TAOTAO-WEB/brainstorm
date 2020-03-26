package edu.hdu.brainstorm.controller;

import com.alibaba.fastjson.JSONObject;
import edu.hdu.brainstorm.annotation.UserLoginToken;
import edu.hdu.brainstorm.entity.Comment;
import edu.hdu.brainstorm.entity.User;
import edu.hdu.brainstorm.service.CommentService;
import edu.hdu.brainstorm.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.hdu.brainstorm.util.idutils;
/**
 * (Comment)表控制层
 *
 * @author makejava
 * @since 2020-03-21 20:32:57
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;

    /**
     * 展示评论详情
     *
     * @param commentid 主键 评论id
     * @return 单条数据
     */
    @UserLoginToken
    @PostMapping("showComment")
    public Object showComment(@RequestParam("commentid") String commentid) {
        JSONObject jsonObject = new JSONObject();
        Comment comment = commentService.queryById(commentid);
        jsonObject.put("comment",comment);
        return jsonObject;
    }

    /**
     *
     * 获取个人发布的所有评论
     * @param request 获取userid
     * @return
     */
    @UserLoginToken
    @GetMapping("getPersonalComment")
    public Object getPersonalTopic(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        User loginUser = (User) request.getAttribute("currentUser"); //获取当前用户信息
        List<Comment> commentList=commentService.queryAllByUserid(loginUser.getUserid());
        jsonObject.put("commentList",commentList);
        return jsonObject;
    }

    /**
     * 删除评论
     * @param commentid
     * @return
     */
    @UserLoginToken
    @PostMapping("deleteComment")
    public Object seleteComment(@RequestParam("commentid") String commentid) {
        JSONObject jsonObject = new JSONObject();
        commentService.deleteById(commentid);
        jsonObject.put("message","删除评论成功");
        return jsonObject;
    }

    /**
     * 发布评论
     * @param request 请求
     * @param topicid 评论的话题的id
     * @param context 评论内容
     * @return
     */
    @UserLoginToken
    @PostMapping("publishcomment")
    public Object publishComment(HttpServletRequest request,@RequestParam("topicid") String topicid,
                                 @RequestParam("context") String context){
        JSONObject jsonObject = new JSONObject();
        if(topicid.equals("")) {
            jsonObject.put("msg","topicid为空");
            return jsonObject;
        }
        else if(context.equals("")){
            jsonObject.put("msg","评论至少写点啥吧");
            return jsonObject;
        }

        User loginUser = (User) request.getAttribute("currentUser");
        Comment comment = new Comment();
        comment.setCommentid(idutils.getid());
        comment.setTopicid(topicid);
        comment.setUserid(loginUser.getUserid());
        comment.setDate(new Date());
        comment.setContext(context);
        commentService.insert(comment);
        jsonObject.put("msg","评论成功");
        jsonObject.put("comment",comment);
        return jsonObject;
    }

    /**
     *
     * 获取某个topic的评论列表
     * @param
     * @return
     */
    @PostMapping("getTopicComment")
    @UserLoginToken
    public List<JSONObject> getTopicComment(@RequestParam("topicid") String topicid){
        List<JSONObject> list = new ArrayList<>();
        List<Comment> commentList = commentService.queryAllByTopicid(topicid);
        for(Comment comment:commentList){
            JSONObject jsonObject = new JSONObject();
            User user = userService.queryById(comment.getUserid());
            jsonObject.put("context",comment.getContext());
            jsonObject.put("commentid",comment.getCommentid());
            jsonObject.put("topicid",comment.getTopicid());
            jsonObject.put("username",user.getUsername());
            jsonObject.put("date",comment.getDate());
            jsonObject.put("pic",user.getPic());
            list.add(jsonObject);
        }
        return list;
    }
}