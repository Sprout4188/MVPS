package com.jc.basecore.grobal;

import com.jc.basecore.sp.MemberInfoSP;
import com.jc.basecore.util.AndroidUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sprout on 2018/8/29
 */
public class Config {
    public static class Logger {
        public static String baseTag = "songcw";           //Log日志的全局TAG
        public static int methodCount = 0;                 //Log日志打印的方法数
        public static boolean showThread = false;          //Log日志是否打印线程信息
    }

    public static class Http {
//        public static String urlDev = "http://192.168.0.110:3000";           //开发环境根地址
        public static String urlDev = "http://183.230.93.93:5000/";          //开发环境根地址
        public static String urlSit = "http://192.168.31.208:8180";          //测试环境根地址
        public static String urlPro = "https://v200-openapi.songchedai.com"; //生产环境根地址

        public static int connectTimeout = 30;            //OkHttp超时配置, 单位: 秒
        public static int readTimeout = 30;
        public static int writeTimeout = 60;

        public static String codeSucc = "0000";           //响应成功的Code

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
