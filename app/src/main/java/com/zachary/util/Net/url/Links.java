package com.zachary.util.Net.url;

/**
 * @Title: LInks.全局网络请求地址
 */
public class Links {

    public static boolean production;

    public static final String HTTP_PRODUCT_URL = "http://yl.fiberhomecloudnj.com:3334";//生产环境

    //public static final String HTTP_INNER_TEST_URL = "http://172.16.51.30:8080";//内网地址
    public static final String HTTP_INNER_TEST_URL = "http://172.16.36.199:3334";//公司内网地址
    //http://172.16.36.199:3334/smartSiteApp/sendVerifyCode
    //获取经纬度的数据
    public static final String HTTP_MAP_POSTION_URL = "http://yl.fiberhomecloudnj.com:2324";
    public static final String HTTP_MAP_WORKER_POSTIONS_URL = "http://172.16.36.199:4344";

    //获取实时环境变化数据
    //PM2.5     getPmtpf?deviceCode=523
    //PM10      getPmten?deviceCode=523
    //噪音       getNoise?deviceCode=523
    public static final String HTTP_MONITOR_CHANGE_URL = "http://yl.fiberhomecloudnj.com:4344";

    //站点信息：/apiservice/spary/getSparySiteList
    public static final String HTTP_SPRAY_URL = "http://ykzt-hjjc.com:8086";

    //http://yl.fiberhomecloudnj.com:8041
    public static final String HTTP_WEB_MAP_URL = "http://yl.fiberhomecloudnj.com:8041";

    public static String getHttpUrl(){
        if (production) {
            return HTTP_PRODUCT_URL;
        }else {
            return HTTP_INNER_TEST_URL;
        }

        //return HTTP_INNER_TEST_URL;
    }

}
