package com.mml.springbootgatewaydemo.util;

import com.mml.springbootgatewaydemo.constant.TokenConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * JWT 工具类
 */
public final class JwtUtil {

    private JwtUtil() {
    }

    public static String createToken(String accountId) {
        return Jwts.builder()
                .setId("token")
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("GMT")).toInstant()))
                .claim(TokenConstant.ACCOUNT_ID, accountId)
                .signWith(SignatureAlgorithm.HS256, TokenConstant.SALT)
                .compact();
    }

    /**
     * 生成 token
     *
     * @param accountId        账号ID
     * @param isUserFictitious true：是虚拟账号；false：不是虚拟账号
     * @return token
     */
    public static String createToken(String accountId, String isUserFictitious, String userFictitiousParentId, String randomId) {
        return Jwts.builder()
                .setId("token")
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("GMT")).toInstant()))
                .claim(TokenConstant.ACCOUNT_ID, accountId)
                .claim(TokenConstant.IS_USER_FICTITIOUS, isUserFictitious)
                .claim(TokenConstant.USER_FICTITIOUS_PARENT_ID, userFictitiousParentId)
                .claim(TokenConstant.TOKEN_RANDOM_ID, randomId)
                .signWith(SignatureAlgorithm.HS256, TokenConstant.SALT)
                .compact();
    }

    /**
     * 账号ID 值
     *
     * @param claims claims
     * @return 账号ID 值
     */
    public static String getAccountIdFromToken(Claims claims) {
        return claims.get(TokenConstant.ACCOUNT_ID, String.class);
    }

    public static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(TokenConstant.SALT)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 是否为虚拟账号
     *
     * @param claims claims
     * @return true：是虚拟账号；false：不是虚拟账号
     */
    public static String isUserFictitiousFromToken(Claims claims) {
        return claims.get(TokenConstant.IS_USER_FICTITIOUS, String.class);
    }

    /**
     * 虚拟账号对应的主账号ID
     *
     * @param claims claims
     * @return 虚拟账号对应的主账号ID
     */
    public static String getUserFictitiousParentIdFromToken(Claims claims) {
        return claims.get(TokenConstant.USER_FICTITIOUS_PARENT_ID, String.class);
    }

    /**
     * 多用户登录ID时随机ID
     *
     * @param claims claims
     * @return 随机ID
     */
    public static String getRandomIdFromToken(Claims claims) {
        return claims.get(TokenConstant.TOKEN_RANDOM_ID, String.class);
    }

}
