package edu.hdu.brainstorm.controller;

import com.alibaba.fastjson.JSONObject;
import edu.hdu.brainstorm.annotation.UserLoginToken;
import edu.hdu.brainstorm.entity.Comment;
import edu.hdu.brainstorm.entity.User;
import edu.hdu.brainstorm.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
}