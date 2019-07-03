package com.hc.admin.common.utils;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/6/11 13:25
 * @description：存放一些常量
 * @version: 1.0
 */
public enum HcEnum {
    /**
     * // user缓存前缀
     */
    USER_CACHE_PREFIX("hc.cache.user."),
    /**
     * user角色缓存前缀
     */
    USER_ROLE_CACHE_PREFIX("hc.cache.user.role."),
    /**
     * user权限缓存前缀
     */
    USER_PERMISSION_CACHE_PREFIX("hc.cache.user.permission."),
    /**
     * token信息
     */
    TOKEN_CACHE_PREFIX("hc.cache.token."),
    /**
     * 存储在线用户的 zset前缀
     */
    ACTIVE_USERS_ZSET_PREFIX("hc.user.active"),
    /**
     * 异常信息
     */
    Hc_EXC("hc.exc"),
    /**
     *排序规则： descend 降序
     */
    ORDER_DESC("descend"),
    /**
     *排序规则： ascend 升序
     */
    ORDER_ASC("ascend"),
    /**
     * 按钮
     */
    TYPE_BUTTON("1"),
    /**
     * 菜单
     */
    TYPE_MENU("0")
    ;
    private String value;

    HcEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
