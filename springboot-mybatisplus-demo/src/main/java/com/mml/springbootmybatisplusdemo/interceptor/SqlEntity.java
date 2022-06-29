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
 * 2022-06-16             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package com.mml.springbootmybatisplusdemo.interceptor;

import lombok.Data;
import org.apache.ibatis.mapping.SqlCommandType;

import java.util.*;

/**
 *
 * @author Mei.Mengling
 * @date 2022-06-16
 * @since 1.0.0
 */
@Data
public class SqlEntity {

    private Map<String, Object> whereMap;

    private List<String> findList;

    private Map<String, Object> updateMap;

    private String tableName;

    private Set<String> joinTableName;

    private SqlCommandType type;

    private String id;

    public void setWhere(String param, Object value) {
        if (whereMap == null) {
            whereMap = new HashMap<>();
        }
        Object o = whereMap.get(param);
        if (o != null) {
            if (o instanceof List) {
                List list = (List)o;
                list.add(value);
            } else {
                List list = new ArrayList();
                list.add(o);
                list.add(value);
                whereMap.put(param, list);
            }
        } else {
            whereMap.put(param, value);
        }
    }

    public void setResult(String param, Object value) {
        if (updateMap == null) {
            updateMap = new HashMap<>();
        }
        updateMap.put(param, value);
    }

    public void setResult (String param) {
        if (findList == null) {
            findList = new ArrayList<>();
        }
        findList.add(param);
    }

    public void setTableName(String name) {
        if (tableName != null) {
            if (joinTableName == null) {
                joinTableName = new HashSet<>();
            }
            joinTableName.add(name);
        } else {
            this.tableName = name;
        }
    }
}
