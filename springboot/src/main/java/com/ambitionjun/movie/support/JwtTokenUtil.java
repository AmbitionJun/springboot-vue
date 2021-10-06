package com.ambitionjun.movie.support;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

public final class JwtTokenUtil {

    //在http header中的名字
    public final static String TOKEN_HEADER = "Authorization";

    //一个星期过期
    public final static long REMEMBER_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    //一天过期
    public final static long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // 应用密钥
    private static final String APP_SECRET = "movie.api";

    // 角色权限声明
    private static final String ROLE_CLAIMS = "roles";

    /**
     * 生成Token
     */
    public static String createToken(String username, List<String> roles, long expiration) {
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, roles);
        /**
         * 什么是JWT：Json web token (JWT) 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（(RFC 7519)。
         * 定义了一种简洁的，自包含的方法用于通信双方之间以JSON对象的形式安全的传递信息。
         * 因为数字签名的存在，这些信息是可信的，JWT可以使用HMAC算法或者是RSA或ECDSA的公私秘钥对进行签名。
         */
        return Jwts.builder()
                .setSubject(username)
                .setClaims(map) //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setIssuedAt(new Date())  //jwt的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) //过期时间戳
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)  //设置签名使用的签名算法和签名使用的秘钥
                .compact(); //完成，生成JWT
    }

    /**
     * 获取token body
     */
    private static Claims getTokenClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(APP_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch ( ExpiredJwtException e ) {
            claims = e.getClaims();
        }
        return claims;
    }

    /**
     * 从Token中获取username
     */
    public static String getUsername(String token) {
        return getTokenClaims(token).getSubject();
    }

    /**
     * 从Token中获取用户角色
     */
    public static List<String> getTokenRoles(String token) {
        List<String> roles = new ArrayList<>();
        //获取用户角色
        Object object = getTokenClaims(token).get(ROLE_CLAIMS);
        if (object instanceof ArrayList<?>) {
            for (Object o : (List<?>) object) {
//                System.out.println("tokee中角色："+o);
                roles.add((String) o);
            }
        }
        return roles;
    }

    /**
     * 校验Token是否过期
     */
    public static boolean isExpiration(String token) {
        return getTokenClaims(token).getExpiration().before(new Date());
    }

}
