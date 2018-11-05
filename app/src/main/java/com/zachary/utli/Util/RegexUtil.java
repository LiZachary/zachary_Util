package com.zachary.utli.Util;

/**
 * Created by Zachary on 2018-04-28.
 */
public class RegexUtil {

    /**
     * 正则匹配是否为纯数字
     */
    public static boolean matchNum(String str){
        if(str.matches("^\\d+$")){
            return true;
        }
        return  false;
    }
}
