package com.songcw.basecore.mvp;

import com.songcw.basecore.http.FileBean;

/**
 * Created by Sprout on 2018/9/25
 */
public interface IFileView  extends IController.IView{

    /**
     * 上传文件成功
     */
    void uploadFileSucc(FileBean fileBean);

    /**
     * 上传文件失败
     */
    void uploadFileFail(String error);
}
