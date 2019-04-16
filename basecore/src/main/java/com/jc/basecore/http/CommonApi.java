package com.jc.basecore.http;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Sprout on 2018/9/10
 */
public interface CommonApi {

    /**
     * 多文件上传
     */
    @POST("/openapi/common/file/upload")
    @Multipart
    Observable<BaseBean> uploadFile(@Part("message") RequestBody requestBody, @Part() List<MultipartBody.Part> parts);
}
