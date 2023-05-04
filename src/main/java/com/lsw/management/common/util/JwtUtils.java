package com.lsw.management.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lsw.management.admin.model.vo.user.UserAccountVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  9:08
 */
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    //私钥
    private static final String TOKEN_SECRET = "123456";


    private static Long expiration=86400L;

    /**
     * 生成token，自定义过期时间 毫秒
     */
    public static String generateToken(UserAccountVo user) {
        try {
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");

            return JWT.create()
                    .withHeader(header)
                    .withClaim("token", JSONObject.toJSONString(user))
                    .withExpiresAt(generateExpirationDate())
                    //.withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            logger.error("generate token occur error, error is:{}", e);
            return null;
        }
    }

    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 检验token是否正确
     *
     * @param token
     * @return
     */
    public static UserAccountVo parseToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String tokenInfo = jwt.getClaim("token").asString();
        return JSON.parseObject(tokenInfo, UserAccountVo.class);
    }
}
