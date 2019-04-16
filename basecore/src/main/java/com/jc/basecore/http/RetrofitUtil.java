package com.jc.basecore.http;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.jc.basecore.exception.NoNetException;
import com.jc.basecore.grobal.Config;
import com.jc.basecore.sp.PermanentInfoSP;
import com.jc.basecore.util.MD5Util;
import com.jc.basecore.util.NetUtil;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sprout on 2018/8/28
 */
public class RetrofitUtil {
    private volatile static Retrofit retrofit;

    public static <T> T create(Class<T> clazz) {
        return getInstance().create(clazz);
    }

    private static Retrofit getInstance() {
        if (retrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofit == null) {
                    retrofit = getRetrofit();
                }
            }
        }
        return RetrofitUtil.retrofit.newBuilder().baseUrl(PermanentInfoSP.baseUrl.getValue()).build();
    }

    private static Retrofit getRetrofit() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new MyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (sslSocketFactory != null) {
            builder.sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });
        }
        OkHttpClient okHttpClient = builder
                .addInterceptor(new AddHeaderInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(Config.Http.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(Config.Http.readTimeout, TimeUnit.SECONDS)
                .writeTimeout(Config.Http.writeTimeout, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PermanentInfoSP.baseUrl.getValue())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    static class MyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    static class AddHeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            if (!NetUtil.getInstance().isNetworkConnected()) throw new NoNetException();
            Request oldRequest = chain.request();
            //获取原始JSON
            String oldJson = "{}";
            if (oldRequest.body() instanceof FormBody) {    //非文件上传
                FormBody oldFormBody = (FormBody) oldRequest.body();
                HashMap<String, String> hashMap = new HashMap<>();
                for (int i = 0; i < oldFormBody.size(); i++) {
                    String key = oldFormBody.name(i);
                    String value = oldFormBody.value(i);
                    hashMap.put(key, value);
                }
                oldJson = new Gson().toJson(hashMap);
                oldJson = oldJson.replaceAll("\\\\", "");
                oldJson = oldJson.replace("\"[", "[");
                oldJson = oldJson.replace("]\"", "]");
                oldJson = oldJson.replace("\"{", "{");
                oldJson = oldJson.replace("}\"", "}");
            }
            //添加sign的Header
            String preSign = Config.Http.appKey.concat(oldJson).concat(Config.Http.appId).concat("UTF-8");
            String sign = MD5Util.md5(preSign).toUpperCase();
            Request.Builder newRequestBuilder = oldRequest.newBuilder();
            newRequestBuilder.addHeader("sign", sign);
            //添加其它Header
            Map<String, String> header = Config.Http.buildHeader();   //添加公共Header
            for (Map.Entry<String, String> entry : header.entrySet()) {
                newRequestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
            //构建新请求
            Request newRequest = null;
            if (oldRequest.body() instanceof FormBody) {        //非文件上传
                FormBody newFormBody = new FormBody.Builder().add("message", oldJson).build();
                Logger.t("OkHttp").d(oldRequest.url().toString() + "的原始请求参数\n " + oldJson);
                newRequest = newRequestBuilder.post(newFormBody).build();
            }
            if (oldRequest.body() instanceof MultipartBody) {   //文件上传
                newRequest = newRequestBuilder.build();
            }
            if (newRequest == null) throw new IllegalArgumentException("Request can not be null");
            return chain.proceed(newRequest);
        }
    }
}
