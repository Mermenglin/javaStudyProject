/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2022.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording,
 * or otherwise, without the prior written permission of ShenZhen JiMi Network Technology Co., Ltd.
 *
 * Amendment History:
 *
 * Date                   By              Description
 * -------------------    -----------     -------------------------------------------
 * 2022-06-29             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package com.mml.springbootgatewaydemo.balancerule;

import com.mml.springbootgatewaydemo.config.GatewayConfig;
import com.mml.springbootgatewaydemo.constant.TokenConstant;
import com.mml.springbootgatewaydemo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 实现灰度发布
 *
 * @author Mei.Mengling
 * @date 2022-06-29
 * @since 1.0.0
 */
@Component
public class MyReadBodyPredicateFactory extends AbstractRoutePredicateFactory<MyReadBodyPredicateFactory.Config> {

    @Autowired
    GatewayConfig gatewayConfig;

    public MyReadBodyPredicateFactory() {
        super(MyReadBodyPredicateFactory.Config.class);
    }

    public MyReadBodyPredicateFactory(Class<MyReadBodyPredicateFactory.Config> configClass) {
        super(configClass);
    }

    @Override
    public AsyncPredicate<ServerWebExchange> applyAsync(Consumer consumer) {
        return new AsyncPredicate<ServerWebExchange>() {
            @Override
            public Publisher<Boolean> apply(ServerWebExchange exchange) {
                ServerHttpRequest request = exchange.getRequest();
                //4. 获取请求头
                HttpHeaders headers = request.getHeaders();
                //5. 请求头中获取令牌
                String token = headers.getFirst(TokenConstant.AUTHORIZATION);
                //6. 判断请求头中是否有令牌
                if (StringUtils.isEmpty(token)) {
                    return Mono.just(false);
                }

                //9. 如果请求头中有令牌则解析令牌
                Claims tokenBody = JwtUtil.getTokenBody(token);
                String accountId = JwtUtil.getAccountIdFromToken(tokenBody);
                if (!gatewayConfig.getWhiteList().contains(accountId)) {
                    return Mono.just(false);
                }
                return Mono.just(true);
            }
        };
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyReadBodyPredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                ServerHttpRequest request = serverWebExchange.getRequest();
                HttpHeaders headers = request.getHeaders();
                String token = headers.getFirst(TokenConstant.AUTHORIZATION);
                if (StringUtils.isEmpty(token)) {
                    return false;
                }

                Claims tokenBody = JwtUtil.getTokenBody(token);
                String accountId = JwtUtil.getAccountIdFromToken(tokenBody);
                if (!gatewayConfig.getWhiteList().contains(accountId)) {
                    return false;
                }
                return true;
            }
        };
    }

    public static class Config {

        private String params;

        public MyReadBodyPredicateFactory.Config setParams(String params) {
            this.params = params;
            return this;
        }

        public String getParams() {
            return params;
        }
    }
}
