package edu.hdu.brainstorm.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import edu.hdu.brainstorm.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("tokenService")
public class TokenService {
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getUserid()) // 将 user id 保存到 token 里面
                .withExpiresAt(new Date(System.currentTimeMillis()+3600000)) //设置过期时间
                .sign(Algorithm.HMAC256(user.getUsername()));// 以 username 作为 token 的密钥
        return token;
    }
}
