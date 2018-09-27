package com.songcw.basecore.mvp;

import android.content.Context;

import com.songcw.basecore.http.BaseBean;
import com.songcw.basecore.http.CommonApi;
import com.songcw.basecore.http.ICallBack;
import com.songcw.basecore.http.RetrofitUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Sprout on 2018/8/27
 */
public abstract class BasePresenter<V extends IController.IView> implements IController.IPresenter {
    private final String MSG_ERROR = "view isn't instance of BaseSection and can't be null";
    protected V mView;

    public BasePresenter(V view) {
        if (view instanceof BaseSection) {
            mView = view;
        } else {
            throw new IllegalArgumentException(MSG_ERROR);
        }
    }


    public Context getContext() {
        if (mView != null) return ((BaseSection) mView).getContext();
        else throw new IllegalArgumentException(MSG_ERROR);
    }

    public boolean isViewAttach() {
        return mView != null;
    }

    /**
     * 默认当Section onDestroy时是取消订阅
     */
    public void addDisposable(Observable observable, ICallBack callBack) {
        this.addDisposable(true, observable, callBack);
    }

    /**
     * @param isCancelSubsribe 当Section onDestroy时是否取消订阅(true = 取消, false = 不取消)
     */
    public void addDisposable(boolean isCancelSubsribe, Observable observable, ICallBack callBack) {
        Disposable disposable = (Disposable) observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(callBack);
        if (isCancelSubsribe) {
            ((BaseSection) mView).addDisposable(disposable);
        }
    }

    /**
     * 上传文件
     */
    protected <T extends BaseBean> void uploadFiles(List<File> files, ICallBack<T> callBack) {
        if (files == null || files.size() == 0) return;
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), "{}");
        List<MultipartBody.Part> parts = new ArrayList<>();
        //将Uri转换成File
        for (File file : files) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
            parts.add(part);
        }
        addDisposable(RetrofitUtil.create(CommonApi.class).uploadFile(requestBody, parts), callBack);
    }
}
