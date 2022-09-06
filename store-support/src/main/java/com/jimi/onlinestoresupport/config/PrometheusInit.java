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


package com.jimi.onlinestoresupport.config;

import com.jimi.prometheus.TrackerPrometheus;
import com.jimi.tracker.util.metrics.MonitorMetrics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 普罗米修斯初始化
 *
 * @author Mei.Mengling
 * @date 2022-08-18
 * @since 1.0.0
 */
@Component
public class PrometheusInit {

    @Value("${prometheus.port:18280}")
    private int prometheusPort;

    public static final String APPLICATION_NAME = "portal";

    @PostConstruct
    public void init() {
        TrackerPrometheus.init(prometheusPort, APPLICATION_NAME);
        MonitorMetrics.registerPrometheus(TrackerPrometheus.registry);
    }
}
