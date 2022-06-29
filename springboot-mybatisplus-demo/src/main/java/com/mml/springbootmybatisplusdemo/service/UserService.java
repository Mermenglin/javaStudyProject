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
 * 2022-06-15             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package com.mml.springbootmybatisplusdemo.service;

import com.mml.springbootmybatisplusdemo.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO
 *
 * @author Mei.Mengling
 * @date 2022-06-15
 * @since 1.0.0
 */
public interface UserService {
    User getByIds(List<Long> ids);

    Long insert(User user);

    boolean update(User user);

    void delete(Set<Long> ids);

    List<Map> getById(Map<String, Object> map);

    Integer deleteIn(Map<String, Object> map);

    Integer insertUser(Map<String, Object> map);

    List<Map> selectByUser(Map<String, Object> map);

    Integer updateById(Map<String, Object> map);

    Integer updateByAccount(Map<String, Object> map);

    List<Map> selectByAccounts(Map<String, Object> map);
}
