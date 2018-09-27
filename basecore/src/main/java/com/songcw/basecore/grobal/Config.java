package com.songcw.basecore.grobal;

import com.songcw.basecore.sp.MemberInfoSP;
import com.songcw.basecore.util.AndroidUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sprout on 2018/8/29
 */
public class Config {
    public static class Logger {
        public static String Base_Tag = "songcw";           //Log日志的全局TAG
        public static int Method_Count = 0;                 //Log日志打印的方法数
        public static boolean Show_Thread = false;          //Log日志是否打印线程信息
    }

    public static class Http {
        public static String Url_Dev = "http://192.168.31.111:3000";          //开发环境根地址(汪)
        public static String Url_Sit = "http://192.168.31.208:8180";          //测试环境根地址(郑朋)
        public static String Url_Pro = "https://v200-openapi.songchedai.com"; //生产环境根地址

        public static int ConnectTimeout = 30;            //OkHttp超时配置, 单位: 秒
        public static int ReadTimeout = 30;
        public static int WriteTimeout = 60;

        public static String Code_Succ = "0000";          //响应成功的Code

        public static String appId = "100001";            //用于请求时添加Header:sign,计算MD5值
        public static String appKey = "1526C5B066FDF3465324C763D68D2512";

        public static Map<String, String> buildHeader() {   //公共的Header参数
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("device", AndroidUtil.getDeviceModle());
            headerMap.put("appId", appId);
            headerMap.put("version", AndroidUtil.APP_VERSION_NAME);
            headerMap.put("token", MemberInfoSP.token.getValue());
            return headerMap;
        }
    }
}
