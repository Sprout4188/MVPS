package com.jc.efire.widget.webview;

import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by R-PC-07 on 2016/8/17.
 * webview 与js交互 工具类，注册了js需要调用的的多种接口和调用js的接口
 */
public class WebViewJSTool implements BridgeBeanImpl {

    private static final String TAG = "WebViewJSTool";
    private static final String GET_APP_INFO = "getAPPInfo";
    private static final String GET_USER_INFO = "getUserInfo";
    private static final String LOGIN = "login";
    private static final String SHARE = "share";
    private static final String SETUP_TOP_RIGHT_MENU_ITEMS = "setupTopRightMenuItems";
    private static final String SET_TITLE = "setTitle";
    private static final String SET_NAVIGATION_BAR_HIDDEN = "setNavigationBarHidden";
    private static final String CLOSE_PAGE = "closePage";
    private static final String OPEN_PAGE = "openPage";
    public static final String LOGOUT_NOTICE = "logoutNotice";
    public static final String LOGIN_NOTICE = "loginNotice";
    public static final String CHOOSE_ADDRESS = "chooseAddress";
    public static final String RELOAD = "reload";
    private static final String DELETECACHES = "deleteCaches";
    private static final String BRIDGE_TEST = "bridgeTest";
    /**
     * 返回到webview一级目录
     */
    private static final String BACK_TO_FIRST_LEVEL = "backToFirstLevel";

    /**
     * js向app端发送事件参数
     * param:{
     * eventType:"business",// 例如：business(业务操作)
     * eventName:"orderStatusChange" //例如：处理订单状态改变业务
     * }
     */
    private static final String BROADCAST_EVENT_TO_APP = "broadcastEventToApp";

    private FragmentActivity mActivity;
    private Fragment mFragment;
    private BridgeWebView bridgeWebView;

    private RelativeLayout mWebLayoutTitle;
    private TextView tv_top_title;
    protected ImageView mIVTopMore;

    private CallBackFunction mCallBackFunction;
    private Handler mHandler = new Handler() {};

    private int webViewLevel = 0;

    public WebViewJSTool(FragmentActivity activity) {
        this.mActivity = activity;
    }

    public WebViewJSTool(Fragment fragment) {
        this.mFragment = fragment;
    }

    public WebViewJSTool(BridgeWebView bridgeWebView) {
        this.bridgeWebView = bridgeWebView;
    }

    private void intiBridgeWebview() {
        bridgeWebView.setDefaultHandler(new DefaultHandler());
        bridgeWebView.setWebChromeClient(new WebChromeClient() {
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
                this.openFileChooser(uploadMsg);
            }

            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
                this.openFileChooser(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            }
        });

        bridgeWebView.callHandler(BRIDGE_TEST, "", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Logger.d(data);
            }
        });
        bridgeWebView.registerHandler(GET_APP_INFO, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Logger.d(data);
                //String str = Uri.parse("http://h5.dev.rservice.cn/jsApp/test.html").getHost();
                function.onCallBack("Android");
            }

        });
        bridgeWebView.registerHandler(GET_USER_INFO, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                function.onCallBack("");
            }

        });
        bridgeWebView.registerHandler(LOGIN, new BridgeHandler() {
            @Override
            public void handler(String data, final CallBackFunction function) {

            }
        });
        bridgeWebView.registerHandler(SHARE, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {

            }
        });
        bridgeWebView.registerHandler(SETUP_TOP_RIGHT_MENU_ITEMS, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                showPopMenuView(data);
                doCallBack(function);
            }

        });
        bridgeWebView.registerHandler(SET_TITLE, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                setTitle(tv_top_title, data);
                doCallBack(function);
            }

        });
        bridgeWebView.registerHandler(SET_NAVIGATION_BAR_HIDDEN, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                setNavigationBarHidden(mWebLayoutTitle, tv_top_title, data);
                doCallBack(function);
            }

        });
        bridgeWebView.registerHandler(CLOSE_PAGE, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                closePage();
                doCallBack(function);
            }
        });

        bridgeWebView.registerHandler(OPEN_PAGE, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
            }
        });
        bridgeWebView.registerHandler(RELOAD, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                bridgeWebView.reload();
                doCallBack(function);
            }
        });
        bridgeWebView.registerHandler(DELETECACHES, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                //T.clearWebViewCache(mActivity);
                bridgeWebView.clearCache(true);
                bridgeWebView.clearHistory();
                doCallBack(function);
            }
        });
        bridgeWebView.registerHandler(CHOOSE_ADDRESS, new BridgeHandler() {
            @Override
            public void handler(String data, final CallBackFunction function) {
                if (mCallBackFunction != null) mCallBackFunction = null;
                mCallBackFunction = function;
            }
        });

        bridgeWebView.registerHandler(BACK_TO_FIRST_LEVEL, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {

            }
        });

        bridgeWebView.registerHandler(BROADCAST_EVENT_TO_APP, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {

            }
        });
    }

    private void doCallBack(CallBackFunction function) {
        Gson gson = new Gson();
        String str = gson.toJson("");
        function.onCallBack(str);
    }

    /**
     * 设置页面的标题
     */
    public void setTitle(TextView tv_top_title, String data) {
        tv_top_title.setText(data);
    }

    /**
     * 隐藏或显示导航栏
     */
    public void setNavigationBarHidden(RelativeLayout mWebLayoutTitle, TextView tv_top_title, String data) {
        try {
            JSONObject object = new JSONObject(data);
            boolean hidden = object.optBoolean("hidden");
            boolean animated = object.optBoolean("animated");
            if (hidden) mWebLayoutTitle.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv_top_title.setText(data);
    }

    /**
     * 关闭页面
     */
    public void closePage() {
        mActivity.finish();
    }

    /**
     * 获取app信息
     */
    public JSONObject getAppInfo() {
        JSONObject finalObject = new JSONObject();
        return finalObject;
    }

    /**
     * 分享操作
     */
    public void doShare() {

    }

    public void showPopMenuView(String data) {

    }

    public void setBridgeWebView(BridgeWebView bridgeWebView) {
        this.bridgeWebView = bridgeWebView;
        intiBridgeWebview();
    }

    public void setTv_top_title(TextView tv_top_title) {
        this.tv_top_title = tv_top_title;
    }

    public void setmIVTopMore(ImageView mIVTopMore) {
        this.mIVTopMore = mIVTopMore;
    }

    public void setmWebLayoutTitle(RelativeLayout mWebLayoutTitle) {
        this.mWebLayoutTitle = mWebLayoutTitle;
    }

    public CallBackFunction getmCallBackFunction() {
        return mCallBackFunction;
    }

    @Override
    public BridgeWebView getBridgeWebView() {
        return bridgeWebView;
    }

    public void setWebViewLevel(int webViewLevel) {
        this.webViewLevel = webViewLevel;
    }
}
