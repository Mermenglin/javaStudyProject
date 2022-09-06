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
 * 2022-08-18             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package com.jimi.onlinestoresupport.filter;

import com.codahale.metrics.Timer;
import com.jimi.tracker.util.metrics.MonitorMetrics;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 普罗米修斯给所有请求打耗时等信息
 *
 * @author Mei.Mengling
 * @date 2022-08-18
 * @since 1.0.0
 */
@Slf4j
public class PrometheusFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        // 接口请求增加埋点
        try (Timer.Context ignored = MonitorMetrics.getTimer("filter.uri=" + uri).time()) {
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public void destroy() {

    }
}
