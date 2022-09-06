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
 * 2022-09-06             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package com.jimi.onlinestoresupport.util;

import java.util.Random;
import java.util.UUID;

/**
 * TODO
 *
 * @author Mei.Mengling
 * @date 2022-09-06
 * @since 1.0.0
 */
public class RandomUtil {

    public static int getRandom(int i) {
        Random random = new Random();
        return random.nextInt(i);
    }

    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
