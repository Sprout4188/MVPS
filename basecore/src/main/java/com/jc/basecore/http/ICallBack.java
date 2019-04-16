package com.jc.basecore.http;

import com.jc.basecore.exception.NoNetException;
import com.jc.basecore.grobal.Config;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by Sprout on 2018/8/28
 */
public abstract class ICallBack<T extends BaseBean> extends DisposableObserver<T> {
    public abstract void onSuccess(T bean);

    public abstract void onFail(String errorMsg);


    @Override
    public void onNext(T response) {
        if (Config.Http.codeSucc.equals(response.code)) {
            onSuccess(response);
        } else {
            onFail(response.message);
        }
        if (!isDisposed()) dispose();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof NoNetException) {
            onFail("没有网络");
        } else if (e instanceof UnknownHostException) {
            onFail("无效Host");
        } else if (e instanceof SocketTimeoutException) {
            onFail("请求超时");
        } else if (e instanceof ConnectException) {
            onFail("连接失败");
        } else if (e instanceof HttpException) {
            onFail(((HttpException) e).message());
        } else {
            onFail(e.getMessage());
        }
        e.printStackTrace();
        if (!isDisposed()) dispose();
    }

    @Override
    public void onComplete() {
        if (!isDisposed()) dispose();
    }
}
