/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2021.
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
 * 2021-10-18             huang.jiaming   Create the class
 * http://www.jimilab.com/
 */


package com.mml.springbootmybatisplusdemo.encrypt;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author huang.jiaming
 * @date 2021-10-18
 * @since 1.0.0
 */
@Inherited
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveField {
    String type() default "" ;
}
