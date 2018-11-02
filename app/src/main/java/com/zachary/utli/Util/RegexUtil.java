package com.zachary.zachary.Util;

/**
 * Created by Zachary on 2017-04-28.
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
