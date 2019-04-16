package com.jc.basecore.mvp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.jakewharton.rxbinding2.view.RxView;
import com.jc.basecore.R;
import com.jc.basecore.event.JumpConfigBaseUrlEvent;
import com.jc.basecore.lifecycle.ButterKnifeLifecycle;
import com.jc.basecore.lifecycle.RxBusLifecycle;
import com.jc.basecore.util.BitmapUtil;
import com.jc.basecore.util.DensityUtil;
import com.jc.basecore.util.statusbar.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by Sprout on 2018/8/27
 */
public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {
    public String TAG = this.getClass().getSimpleName();
    public List<BaseSection> sections = new ArrayList<>();
    private Disposable disposable;

    private LinearLayout llRoot;    //根布局
    private AVLoadingIndicatorView loading;
    private View actionBar;         //标题栏布局
    private View contentView;       //内容布局
    //Left   topTitle  layout
    private LinearLayout mLLLeftTopTitle;
    private TextView mTVLeftText;
    //Right  topTitle  layout
    private LinearLayout mLLRightTopTitle;
    private TextView mTVRightText;
    //center  topTitle  layout
    private RelativeLayout mRLCenterTopTitle;
    //top title
    private TextView mTVTopTitle;
    //search layout
    private LinearLayout mLLTopSearch;
    private EditText mEtTopSearch;//搜索框
    private ImageView mIVSearchClear;

    public void addSection(BaseSection section) {
        sections.add(section);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏字体颜色为黑色
//        StatusBarUtil.statusBarLightMode(this);
        //设置状态栏背景颜色
        StatusBarUtil.setStatusBarColor(this, R.color.color_statusbar);
        //设置根布局
        setContentView(R.layout.activity_root);
        //设置标题栏
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        loading = (AVLoadingIndicatorView) findViewById(R.id.avi);
        addActionBar(setMyActionBar());
        //设置内容布局
        addContentView(View.inflate(this, setContentLayout(), null));
        RxBus.get().register(this);
        addSections();
        for (BaseSection section : sections) {
            section.addLifecycle(new ButterKnifeLifecycle(section, this));
            section.addLifecycle(new RxBusLifecycle(section));
            section.onCreate(savedInstanceState);
        }
    }

    /**
     * 添加actionbar
     */
    private void addActionBar(View view) {
        if (llRoot != null && view != null) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            actionBar = view;
            llRoot.addView(view, lp);
            //默认隐藏, 只有当setTitle()时才显示
            actionBar.setVisibility(View.GONE);
        }
    }

    /**
     * 添加contentview
     */
    private void addContentView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        if (llRoot != null && view != null) {
            contentView = view;
            llRoot.addView(view, lp);
        }
    }

    /**
     * 设置内容布局
     */
    protected abstract @LayoutRes
    int setContentLayout();

    protected abstract void addSections();

    /**
     * 获取acitonbar
     */
    protected View setMyActionBar() {
        View view = getLayoutInflater().inflate(R.layout.layout_top_actionbar, null);
        //Left   topTitle  layout
        mLLLeftTopTitle = view.findViewById(R.id.ll_left_text);
        mTVLeftText = view.findViewById(R.id.tv_left_text);
        //Right  topTitle  layout
        mLLRightTopTitle = view.findViewById(R.id.ll_right_text);
        mTVRightText = view.findViewById(R.id.tv_right_text);
        //center  topTitle  layout
        mRLCenterTopTitle = view.findViewById(R.id.rl_center_top_title);
        mTVTopTitle = view.findViewById(R.id.tv_top_title);
        mLLTopSearch = view.findViewById(R.id.ll_top_search);
        mEtTopSearch = view.findViewById(R.id.et_top_search_input);
        mIVSearchClear = view.findViewById(R.id.iv_search_clear);
        registerConfigBaseUrl();
        BitmapUtil.setViewRadius(this, mLLTopSearch, 18, Color.WHITE, Color.parseColor("#E5E5E5"), 1);
        mLLLeftTopTitle.setOnClickListener(this);
        mLLRightTopTitle.setOnClickListener(this);
        mLLTopSearch.setOnClickListener(this);
        mIVSearchClear.setOnClickListener(this);
        mEtTopSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(sequence)) mIVSearchClear.setVisibility(View.GONE);
                else mIVSearchClear.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    /********************** VISIBLE Or GONE*************************/
    /**
     * 显示 left topTitle layout
     */
    public void showLeftLayout() {
        if (mLLLeftTopTitle != null) mLLLeftTopTitle.setVisibility(View.VISIBLE);
    }

    /**
     * 显示 right topTitle layout
     */
    public void showRightLayout() {
        if (mLLRightTopTitle != null) mLLRightTopTitle.setVisibility(View.VISIBLE);
    }


    /**
     * 隐藏 left topTitle layout
     */
    public void hideLeftLayout() {
        if (mLLLeftTopTitle != null) mLLLeftTopTitle.setVisibility(View.INVISIBLE);
    }

    /**
     * 隐藏 Center topTitle layout
     */
    public void hideCenterLayout() {
        if (mRLCenterTopTitle != null) mRLCenterTopTitle.setVisibility(View.GONE);
    }

    /**
     * 隐藏 Right topTitle layout
     */
    public void hideRightLayout() {
        if (mLLRightTopTitle != null) mLLRightTopTitle.setVisibility(View.INVISIBLE);
    }

    /********************** Set Left text/img *************************/

    /**
     * 设置  left topTitle text only
     *
     * @param leftText
     */
    public void setLeftText(String leftText, boolean isShowLeftDrawable, boolean isShowRightDrawable, int imgRes, int padding) {
        if (!TextUtils.isEmpty(leftText) || isShowLeftDrawable || isShowLeftDrawable) {
            mLLLeftTopTitle.setVisibility(View.VISIBLE);
        }
        Drawable drawable = null;
        if (imgRes > 0) drawable = getResources().getDrawable(imgRes);

        if (mTVLeftText != null) {
            mTVLeftText.setText(leftText);
            if (isShowLeftDrawable && drawable != null) {// left
                actionBar.setVisibility(View.VISIBLE);
                //drawable.setBounds(0, 0,  drawable.getMinimumWidth(), drawable.getMinimumHeight());
                // mTVLeftText.setCompoundDrawables(drawable, null, null, null);
                mTVLeftText.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                mTVLeftText.setCompoundDrawablePadding(padding);
            } else if (isShowRightDrawable && drawable != null) {// right
                actionBar.setVisibility(View.VISIBLE);
                mTVLeftText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                mTVLeftText.setCompoundDrawablePadding(padding);
            }
        } else {
            actionBar.setVisibility(View.GONE);
        }
    }

    /********************** Set Right text/img *************************/

    /**
     * 设置  left topTitle text only
     *
     * @param rightText           text string
     * @param isShowLeftDrawable  is has  text left drawable
     * @param isShowRightDrawable is has  text right drawable
     * @param imgRes              drawable resource
     * @param padding             drawable padding
     */
    public void setRightText(String rightText, boolean isShowLeftDrawable, boolean isShowRightDrawable, int imgRes, int padding) {
        if (!TextUtils.isEmpty(rightText) || isShowLeftDrawable || isShowLeftDrawable) {
            mLLRightTopTitle.setVisibility(View.VISIBLE);
        }
        Drawable drawable = null;
        if (imgRes > 0) drawable = getResources().getDrawable(imgRes);
        if (mTVRightText != null) {
            mTVRightText.setText(rightText);
            if (isShowLeftDrawable && drawable != null) {// left
                actionBar.setVisibility(View.VISIBLE);
//                 drawable.setBounds(0, 0,  drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                 mTVLeftText.setCompoundDrawables(drawable, null, null, null);
                mTVRightText.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                mTVRightText.setCompoundDrawablePadding(padding);
            } else if (isShowRightDrawable && drawable != null) {// right
                actionBar.setVisibility(View.VISIBLE);
                drawable.setBounds(0, 0, DensityUtil.dp2px(this, 20), DensityUtil.dp2px(this, 20));
//                mTVLeftText.setCompoundDrawables(null, null, drawable, null);
                mTVRightText.setCompoundDrawables(null, null, drawable, null);
//                mTVRightText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                mTVRightText.setCompoundDrawablePadding(padding);
            } else {
                actionBar.setVisibility(View.VISIBLE);
                mTVRightText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                mTVRightText.setCompoundDrawablePadding(padding);
            }
        } else {
            actionBar.setVisibility(View.GONE);
        }
    }

    public TextView getRightText() {
        return mTVRightText;
    }


    /**
     * 设置title
     */
    public void setTitle(String title) {
        if (mTVTopTitle != null && !TextUtils.isEmpty(title)) {
            actionBar.setVisibility(View.VISIBLE);
            //actionBar.setBackgroundColor(getResources().getColor(R.color.color_3984ff,null));
            mTVTopTitle.setVisibility(View.VISIBLE);
            mTVTopTitle.setText(title);
            mLLTopSearch.setVisibility(View.GONE);
        } else {
            actionBar.setVisibility(View.GONE);
        }
    }

    /**
     * 设置搜索框 hint
     */
    public void setSearchHint(String hint) {
        if (mLLTopSearch != null && !TextUtils.isEmpty(hint)) {
            actionBar.setVisibility(View.VISIBLE);
            mLLTopSearch.setVisibility(View.VISIBLE);
            mEtTopSearch.setHint(hint);
            mTVTopTitle.setVisibility(View.GONE);
        } else {
//            actionBar.setVisibility(View.GONE);
            mTVTopTitle.setVisibility(View.VISIBLE);
            mLLTopSearch.setVisibility(View.GONE);
        }
    }


    /**
     * Left  topTitle  layout  onClick
     */
    public void onLeftLayoutClick() {
        finish();
    }

    /**
     * Center  topTitle  layout
     */
    public void onCenterLayoutClick() {

    }

    /**
     * Right  topTitle  layout
     */
    public void onRightLayoutClick() {

    }

    /**
     * 注册配置根地址监听
     */
    private void registerConfigBaseUrl() {
        Observable<Object> observable = RxView.clicks(mTVTopTitle).share();
        //表示连续点击3次时, 才会发送给下游
        disposable = observable.buffer(observable.debounce(800, TimeUnit.MILLISECONDS))
                .filter(new Predicate<List<Object>>() {
                    @Override
                    public boolean test(@io.reactivex.annotations.NonNull List<Object> objects)
                            throws Exception {
                        return objects.size() == 3;   //表示连续点击3次时, 才会发送给下游
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<List<Object>>bindToLifecycle())
                .subscribe(new Consumer<List<Object>>() {
                    @Override
                    public void accept(List<Object> objects) throws Exception {
                        // to = EventReceiveSection
                        RxBus.get().post(new JumpConfigBaseUrlEvent());
                    }
                });
    }

    /**
     * 获取根布局
     */
    public LinearLayout getMyRootView() {
        return llRoot;
    }

    /**
     * 获取actionbar
     */
    public View getMyActionBar() {
        return actionBar;
    }

    /**
     * 获取contentView
     */
    public View getMyContentView() {
        return contentView;
    }

    /**
     * 获取 搜索框 EditText
     */
    public EditText getSearchEditText() {
        return mEtTopSearch;
    }

    /**
     * 获取 搜索框
     */
    public LinearLayout getSearchContainer() {
        return mLLTopSearch;
    }

    /**
     * 获取 搜索框 clear ImageView
     */
    public ImageView getSearchClearImageView() {
        return mIVSearchClear;
    }


    public void showLoading() {
        if (loading != null) loading.show();
    }

    public void hideLoading() {
        if (loading != null) loading.hide();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        for (BaseSection section : sections) section.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (BaseSection section : sections) section.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (BaseSection section : sections) section.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (BaseSection section : sections) section.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (BaseSection section : sections) section.onStop();
        hideLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (BaseSection section : sections) section.onDestroy();
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
        RxBus.get().unregister(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        for (BaseSection section : sections) section.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //下发到该Activity的所有Section
        for (BaseSection section : sections) section.onActivityResult(requestCode, resultCode, data);
        //下发到该Activity的所有Fragment
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (BaseSection section : sections)
            section.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        for (BaseSection section : sections) section.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        for (BaseSection section : sections) section.onBackPressed();
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_left_text) {
            onLeftLayoutClick();
        } else if (i == R.id.ll_top_search) {
            onCenterLayoutClick();
        } else if (i == R.id.ll_right_text) {
            onRightLayoutClick();
        } else if (i == R.id.iv_search_clear) {
            if (mEtTopSearch != null) mEtTopSearch.setText("");
        }
    }
}
