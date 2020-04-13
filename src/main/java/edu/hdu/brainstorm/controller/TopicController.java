package edu.hdu.brainstorm.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import edu.hdu.brainstorm.annotation.UserLoginToken;
import edu.hdu.brainstorm.entity.Topic;
import edu.hdu.brainstorm.entity.User;
import edu.hdu.brainstorm.interceptor.AuthenticationInterceptor;
import edu.hdu.brainstorm.service.TopicService;
import edu.hdu.brainstorm.service.impl.TokenService;
import edu.hdu.brainstorm.util.idutils;
import edu.hdu.brainstorm.util.shautils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * (Topic)表控制层
 *
 * @author makejava
 * @since 2020-03-21 20:32:29
 */
@CrossOrigin
@RestController
@RequestMapping("topic")
public class TopicController {
    /**
     * 服务对象
     */
    @Resource
    private TopicService topicService;

    /**
     * 发布主题
     * @param request     用来获取userid
     * @param context     主题名字
     * @param description 主题描述
     * @param tag         主题标签
     * @return
     */
    @UserLoginToken
    @PostMapping("publishtopic")
    public Object register(HttpServletRequest request, @RequestParam("context") String context,
                           @RequestParam("description") String description, @RequestParam("tag") String tag) {
        JSONObject jsonObject = new JSONObject();
        User loginUser = (User) request.getAttribute("currentUser"); //获取当前用户信息
        // 主题帖内容不为空
        if (!context.equals("") && !description.equals("")) {
            Topic topic = new Topic();
            topic.setTopicid(idutils.getid());
            topic.setUserid(loginUser.getUserid());
            topic.setContext(context);
            topic.setDescription(description);
            topic.setTag(tag);
            topic.setDate(new Date());
            topicService.insert(topic);
            //jsonObject.put("topic",topic);
            jsonObject.put("msg", "发布主题帖成功");
            return jsonObject;
        } else {
            jsonObject.put("msg", "参数非法");
            return jsonObject;
        }
    }

    /**
     *
     * 获取个人发布的所有主题
     * @param request 获取userid
     * @return
     */
    @UserLoginToken
    @GetMapping("getPersonalTopic")
    public Object getPersonalTopic(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        User loginUser = (User) request.getAttribute("currentUser"); //获取当前用户信息
        List<Topic> topicList=topicService.queryAllByUserid(loginUser.getUserid());
        jsonObject.put("topicList",topicList);
        return jsonObject;
    }
    /**
     *
     * 所有评论
     * @param order ASC升序 DESC 倒序
     * @return
     */
    @PostMapping("getAllTopic")
    public Object getAllTopic(@RequestParam("order") String order) {
        JSONObject jsonObject = new JSONObject();
        List<Topic> topicList=topicService.queryAll(order);
        jsonObject.put("topicList",topicList);
        return jsonObject;
    }

    /**
     * 删除该主题
     * @param topicid
     * @return
     */
    @UserLoginToken
    @PostMapping("deleteTopic")
    public Object deleteTopic(@RequestParam("topicid") String topicid) {
        JSONObject jsonObject = new JSONObject();
        topicService.deleteById(topicid);
        jsonObject.put("message","删除该主题帖成功");
        return jsonObject;
    }
}