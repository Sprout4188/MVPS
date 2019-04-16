package com.jc.efire.widget.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.jc.basecore.mvp.BaseSection;
import com.jc.basecore.mvp.IController;
import com.jc.efire.R;

public class BaseWebSection<P extends IController.IPresenter> extends BaseSection<P> {

    protected BridgeWebView mBridgeWebView;
    private WebViewJSTool mWebJSTool;
    private Object mSource;
    protected String mUrl;

    public BaseWebSection(Object source) {
        this(source, "");
    }

    public BaseWebSection(Object source, String url) {
        super(source);
        this.mSource = source;
        this.mUrl = url;
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
        mBridgeWebView = (BridgeWebView) findView(R.id.webView);
    }

    @Override
    protected void init() {

        if (mSource instanceof FragmentActivity) {
            mWebJSTool = new WebViewJSTool(((FragmentActivity) mSource));
        } else if (mSource instanceof Fragment) {
            mWebJSTool = new WebViewJSTool(((Fragment) mSource));
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

        load(mUrl);
    }

    protected void load(String url) {
        mBridgeWebView.setWebViewClient(new BridgeWebViewClient(mBridgeWebView) {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                //super.onReceivedSslError(view, handler, error);
                hideLoading();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (mBridgeWebView.canGoBack()) {
                } else {
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mBridgeWebView.canGoBack()) {
                } else {
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
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                }
                super.onProgressChanged(view, newProgress);
            }
        };
        // 设置setWebChromeClient对象
        mBridgeWebView.setWebChromeClient(wvcc);
        if (url != null) {
            mBridgeWebView.loadUrl(url);
            return;
        } else {

        }
    }
}
