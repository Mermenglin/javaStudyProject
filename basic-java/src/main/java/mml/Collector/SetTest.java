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
 * 2022-06-24             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package mml.Collector;

import org.junit.Test;

import java.util.*;

/**
 * TODO
 *
 * @author Mei.Mengling
 * @date 2022-06-24
 * @since 1.0.0
 */
public class SetTest {

    @Test
    public void test1() {
        int[] i = new int[]{32,192,101,97,69,156,32,241,220,205,206,47,199,193,210,9,117,109,197,42,120,234,235,221,121,131,220,220,203,196,198,203,196,208,203,196,131,112,223,112,231,231,222,220,208,226,47,221,131,135,197,220,208,194,14,118,46,220,203,196,131,220,221,224,217,219,208,131,112,223,197,231,47,206,39,220,43,131,42,220,131,42,199,41,42,199,40,136,245,101,220,131,221,154,242,258,243,241,257};
        Set<Integer> set = new HashSet<>();
        for (int t : i) {
            set.add(t);
        }
        System.out.println(set);
        List<Integer> list = new ArrayList<>();
        list.addAll(set);
        Collections.sort(list);
        System.out.println(list);
        System.out.println(list.size());
    }
}
