package com.jimi.onlinestoresupport.util.jackson;/*
 * COPYRIGHT. Zong.Huan JiMi Technology Co., Ltd. 2022.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording,
 * or otherwise, without the prior written permission of ShenZhen JiMi Network Technology Co., Ltd.
 *
 * Amendment History:
 *
 * Date                                      By              Description
 * -------------------                      -----------     -------------------------------------------
 * 2022/6/21 19:56                              Zong.Huan            Description
 * http://www.jimilab.com/
 * @Auther: Zong.Huan
 * @Date: 2022/6/21 19:56
 * @Description:
 */

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String time = jsonParser.getText().trim();
        // 0时区
        return  Instant.ofEpochMilli(NumberUtils.toLong(time)).atZone(ZoneOffset.ofHours(0)).toLocalTime();
    }
}
