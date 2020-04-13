package edu.hdu.brainstorm.controller;

import com.alibaba.fastjson.JSONObject;
import edu.hdu.brainstorm.annotation.UserLoginToken;
import edu.hdu.brainstorm.entity.User;
import edu.hdu.brainstorm.service.UserService;
import edu.hdu.brainstorm.util.idutils;
import edu.hdu.brainstorm.util.shautils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import edu.hdu.brainstorm.service.impl.TokenService;


/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-03-21 20:20:05
 */
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;
    /**
     * 注册接口
     *
     * @param name 用户名
     * @param password 密码
     * @return json
     */
    @PostMapping("register")
    public Object register(@RequestParam("name") String name,@RequestParam("password") String password) {
        JSONObject jsonObject = new JSONObject();
        if(!name.equals("") && !password.equals("")){
            //确保用户名唯一
            if(userService.queryByUsername(name) == null){
                User user = new User();
                user.setUsername(name);
                user.setUserpassword(shautils.getsha(password));
                user.setUserid(idutils.getid());
                user.setDate(new Date());
                userService.insert(user);
                //jsonObject.put("user",user);
                jsonObject.put("msg","注册成功");
                return jsonObject;
            }
            else{
                jsonObject.put("msg","用户名已经存在");
                return jsonObject;
            }
        }
        else {
            jsonObject.put("msg","参数非法");
            return jsonObject;
        }
    }

    /**
     * 登录接口
     *
     * @param name 用户名
     * @param password 密码
     * @return json
     */
    @PostMapping("signup")
    public Object signup(@RequestParam("name") String name, @RequestParam("password") String password){
        User userbase = userService.queryByUsername(name);
        JSONObject jsonObject = new JSONObject();
        if(userbase != null){
            if(userService.queryByUsername(name).getUserpassword().equals(shautils.getsha(password))){
                String token = tokenService.getToken(userbase);
                jsonObject.put("token",token);
                jsonObject.put("user",userbase);
                return jsonObject;
            }
            else {
                jsonObject.put("msg","用户名或密码错误");
                return jsonObject;
            }
        }
        else{
            jsonObject.put("msg","用户名或密码错误");
            return jsonObject;
        }
    }

    @GetMapping("userhome")
    @UserLoginToken
    public Object getuserhome(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        User loginUser = (User) request.getAttribute("currentUser"); //获取当前用户信息
        jsonObject.put("username",loginUser.getUsername());
        jsonObject.put("pic",loginUser.getPic());
        return jsonObject;
    }


//    @GetMapping("changepic")
//    @UserLoginToken
//    public Object updatepic(HttpServletRequest request,@RequestParam("file") MultipartFile file){
//        JSONObject jsonObject = new JSONObject();
//        User loginUser = (User) request.getAttribute("currentUser"); //获取当前用户信息
//        if(file==null) {
//            jsonObject.put("msg","文件为空");
//            return jsonObject;
//        }
//        String type = file.getContentType();
//        if("image/png".equals(type) || "image/jpeg".equals(type)){
//            try {
//
//                file.getInputStream();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else {
//            jsonObject.put("msg","请上传png，jpg图片");
//            return jsonObject;
//        }
//
//    }

}