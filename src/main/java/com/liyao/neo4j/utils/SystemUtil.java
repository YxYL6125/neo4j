package com.liyao.neo4j.utils;

/**
 * @program: neo4j
 * @description: 系统工具类
 * @author: YxYL
 * @create: 2022-06-05 10:48
 **/

public class SystemUtil {


    /**
     * 判断字符串是否为NULL
     */
    public static boolean isNull(String str) {
        return null == str;
    }

    /**
     * 判断字符串是否为空或NULL
     */
    public static boolean isNullOrEmpty(String str) {
        return null == str || str.trim().equals("");
    }
}
