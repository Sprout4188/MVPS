package com.songcw.customer.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.songcw.basecore.mvp.BaseSection;
import com.songcw.basecore.mvp.IController;
import com.songcw.customer.R;

/**
 * @name employee
 * @class name：com.songcw.basecore.mvp
 * @class describe
 * @anthor wjb
 * @time 2018/9/10 14:19
 * @change
 * @chang time
 * @class describe
 */
public class BaseWebSection<P extends IController.IPresenter> extends BaseSection<P> {

    protected LinearLayout mLLDetailInfo;
    protected TextView mDetailTitle;
    protected TextView mDetailTime;
    protected BridgeWebView mBridgeWebView;

    protected String mTitle;
    protected String mUrl;
    protected String mContent;

    private WebViewJSTool mWebJSTool;
    private Object mSource;

    public BaseWebSection(Object source) {
        this(source,"","","");
    }

    public BaseWebSection(Object source, String title, String url, String content) {
        super(source);
        this.mSource = source;
        this.mTitle = title;
        this.mUrl = url;
        this.mContent = content;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public P onCreatePresenter() {
        return null;
    }

    @Override
    protected void initViews() {

        mLLDetailInfo = (LinearLayout) findView(R.id.ll_detail_info);
        mDetailTitle = (TextView) findView(R.id.tv_detail_title);
        mDetailTime = (TextView) findView(R.id.tv_detail_time);

        mBridgeWebView = (BridgeWebView) findView(R.id.webView);

    }

    @Override
    protected void initEvents() {

        if(mSource instanceof FragmentActivity){
            mWebJSTool  = new WebViewJSTool(((FragmentActivity) mSource));
        }else if(mSource instanceof Fragment){
            mWebJSTool  = new WebViewJSTool(((Fragment) mSource));
        }
        mWebJSTool.setBridgeWebView(mBridgeWebView);

        if (mBridgeWebView != null) {
            WebSettings settings = mBridgeWebView.getSettings();
            if (settings != null) {
                settings.setDefaultTextEncodingName("utf-8");
                settings.setDomStorageEnabled(true);
                settings.setAppCacheEnabled(true);
                settings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置不使用缓存
                settings.setJavaScriptEnabled(true);//支持js
                // don't show the zoom controls
                settings.setDisplayZoomControls(false);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setBuiltInZoomControls(false);
                //自适应屏幕
                settings.setUseWideViewPort(true);
                settings.setLoadWithOverviewMode(true);
                settings.setDefaultTextEncodingName("utf-8");
                if (Build.VERSION.SDK_INT < 17) {//系统有漏洞
                    mBridgeWebView.removeJavascriptInterface("searchBoxJavaBridge_");
                }
            }
        }

        setDetailInfo();
        load(mUrl, mContent);
    }

    private void setDetailTitle(String str){
        mDetailTitle.setText(str);
    }
    private void setDetailTitle(int res){
        mDetailTitle.setText(getContext().getString(res));
    }

    private void setDetailTime(String str){
        mDetailTime.setText(str);
    }

    public void isShowDetailInfo(boolean isShow){
        mLLDetailInfo.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void setDetailInfo(){
        setDetailTitle(mTitle);
        setDetailTime("2018-9-10 16:42");
    }

    protected void load(String url,String data){

        mBridgeWebView.setWebViewClient(new BridgeWebViewClient(mBridgeWebView) {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                //super.onReceivedSslError(view, handler, error);
                hideLoading();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(mBridgeWebView.canGoBack()){
                }else{
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                if(mBridgeWebView.canGoBack()){
                }else{
                }
                super.onPageFinished(view, url);
                hideLoading();
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoading();
            }
        });
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if(TextUtils.isEmpty(mTitle)){
                    mDetailTitle.setText(title);
                }
                super.onReceivedTitle(view, title);
            }
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                }
                super.onProgressChanged(view, newProgress);
            }
        };
        // 设置setWebChromeClient对象
        mBridgeWebView.setWebChromeClient(wvcc);
        if (url != null) {
            mBridgeWebView.loadUrl(url);
            return;
        }else{
        }
        if (data != null) {
            mBridgeWebView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
            return;
        }
    }
}
