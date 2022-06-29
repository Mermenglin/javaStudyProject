package com.mml.springbootgatewaydemo.constant;

/**
 * Token 相关常量接口
 *
 * @author fan.jinpeng
 * @date 2021-05-13
 */
public interface TokenConstant {

    /**
     * 盐
     */
    String SALT = "aQlM6Qb3";

    /**
     * token
     */
    String AUTHORIZATION = "Authorization";

    /**
     * 当前登录用户信息 key 值
     */
    String X_CLIENT_ACCOUNT = "x-client-account";

    /**
     * 账户 ID
     */
    String ACCOUNT_ID = "accountId";

    /**
     * 是否为虚拟账号
     */
    String IS_USER_FICTITIOUS = "isUserFictitious";

    /**
     * 如果为虚拟账号，其父ID，即主账号ID
     * 如果不是虚拟账号，值为空
     */
    String USER_FICTITIOUS_PARENT_ID = "userFictitiousParentId";

    /**
     * 为了适配旧代码多端登录，保存 TOKEN 时，Redis key 格式如下
     * TOKEN:accountId:randomId
     */
    String TOKEN_RANDOM_ID = "randomId";

    /**
     * 当前登录账户上下文 key
     */
    String CURRENT_LOGIN_ACCOUNT_INFO_CONTEXT = "CURRENT_LOGIN_ACCOUNT_INFO_CONTEXT";

}
