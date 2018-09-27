package com.songcw.customer.home.mvp.section;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.songcw.basecore.mvp.BaseSection;
import com.songcw.basecore.mvp.IController;
import com.songcw.basecore.widget.bannerView.Banner;
import com.songcw.basecore.widget.bannerView.listener.OnBannerListener;
import com.songcw.basecore.widget.bannerView.loader.GlideImageLoader;
import com.songcw.basecore.widget.textviewfliper.TextViewFlipper;
import com.songcw.customer.R;
import com.songcw.customer.application.Constant;
import com.songcw.customer.home.adapter.FlipperAdapter;
import com.songcw.customer.home.adapter.HomeManagementGridAdapter;
import com.songcw.customer.home.mvp.model.HomeManagementFuncBean;
import com.songcw.customer.home.mvp.model.NewsEntity;
import com.songcw.customer.home.mvp.view.AgentManagementActivity;
import com.songcw.customer.home.mvp.view.NewsListActivity;
import com.songcw.customer.util.PageTool;

import java.util.ArrayList;
import java.util.List;

/**
 * home
 */
public class HomeSection extends BaseSection implements View.OnClickListener, OnBannerListener {

    private Banner mViewBanner;
    private LinearLayout mLLGridView;
    private LinearLayout mLLHeadline;
    private TextViewFlipper mViewFlipperTopTip;
    private TextView mTvHeadlineMore;
    private GridView mManagementGridview;

    private HomeManagementGridAdapter mGridAdapter;
    private FlipperAdapter mFlipperAdapter;

    public HomeSection(Object source) {
        super(source);
    }

    @Override
    public IController.IPresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initViews() {

        mViewBanner = (Banner) findView(R.id.view_banner);
        mLLGridView = (LinearLayout) findView(R.id.ll_grid_view);
        mManagementGridview = (GridView) findView(R.id.management_gridview);
        mLLHeadline = (LinearLayout) findView(R.id.ll_headline);
        mViewFlipperTopTip = (TextViewFlipper) findView(R.id.vf_headline);
        mTvHeadlineMore = (TextView) findView(R.id.tv_headline_more);
        mLLHeadline.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initEvents() {

        mTvHeadlineMore.setOnClickListener(this);
        mViewFlipperTopTip.setOnClickListener(this);
        mManagementGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tag = ((HomeManagementFuncBean) mGridAdapter.getItem(position)).tag;
                switch (tag){
                    case Constant.HomeConstant.TAG_QUICK_COLLECTION:
                        break;
                    case Constant.HomeConstant.TAG_AGENT_MANAGEMENT:
                        startActivity(new Intent(getContext(), AgentManagementActivity.class));
                        break;
                    case Constant.HomeConstant.TAG_REPAYMENT_CUSTOMER_MANAGEMENT:
                        break;
                    case Constant.HomeConstant.TAG_OVERDUE_CUSTOMER_MANAGEMENT:
                        break;
                }
            }
        });

        loadData();
    }

    private void loadData() {
        getBannerData();
        getHeadlineData();
        initManagement();
    }

    private void getBannerData() {
        List<Integer> urlList = new ArrayList<>();
        urlList.add(R.mipmap.img_banner);
        urlList.add(R.mipmap.img_banner);
        urlList.add(R.mipmap.img_banner);
        mViewBanner.setImages(urlList)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    private void getHeadlineData() {
        List<NewsEntity> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            NewsEntity bean = new NewsEntity();
            bean.name = "头条测试+++++"+ i;
            data.add(bean);
        }

        mFlipperAdapter = (FlipperAdapter)mViewFlipperTopTip.getAdapter();
        if(mFlipperAdapter == null){
            mFlipperAdapter = new FlipperAdapter(getContext());
        }
        mFlipperAdapter.setData(data);
        mViewFlipperTopTip.setFlipperAdapter(mFlipperAdapter);
        mViewFlipperTopTip.startFlipping();
    }

    public void initManagement(){

        List<HomeManagementFuncBean> funcList = new ArrayList<>();
        HomeManagementFuncBean quickBean = new HomeManagementFuncBean();
        quickBean.icon = R.mipmap.ic_quick_reminders;
        quickBean.name = getContext().getString(R.string.quick_collection);
        quickBean.tag = Constant.HomeConstant.TAG_QUICK_COLLECTION;


        HomeManagementFuncBean agentBean = new HomeManagementFuncBean();
        agentBean.icon = R.mipmap.ic_agent_management;
        agentBean.name = getContext().getString(R.string.agent_management);
        agentBean.tag = Constant.HomeConstant.TAG_AGENT_MANAGEMENT;


        HomeManagementFuncBean repaymentBean = new HomeManagementFuncBean();
        repaymentBean.icon = R.mipmap.ic_repayment_customer_management;
        repaymentBean.name = getContext().getString(R.string.repayment_customer_management);
        repaymentBean.tag = Constant.HomeConstant.TAG_REPAYMENT_CUSTOMER_MANAGEMENT;


        HomeManagementFuncBean overdueBean = new HomeManagementFuncBean();
        overdueBean.icon = R.mipmap.ic_overdue_customer_management;
        overdueBean.name = getContext().getString(R.string.overdue_customer_management);
        overdueBean.tag = Constant.HomeConstant.TAG_OVERDUE_CUSTOMER_MANAGEMENT;

        funcList.add(quickBean);
        funcList.add(agentBean);
        funcList.add(repaymentBean);
        funcList.add(overdueBean);

        if(funcList!=null && funcList.size()>0){
            mLLGridView.setVisibility(View.VISIBLE);
            mGridAdapter = new HomeManagementGridAdapter(getContext(),funcList);
            mManagementGridview.setAdapter(mGridAdapter);
        }else {
            mLLGridView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mViewFlipperTopTip != null){
            mViewFlipperTopTip.startFlipping();
        }
        mViewBanner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mViewFlipperTopTip != null){
            mViewFlipperTopTip.stopFlipping();
        }
        //结束轮播
        mViewBanner.stopAutoPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewBanner != null) {
            mViewBanner.releaseBanner();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tv_headline_more:
                startActivity(new Intent(getContext(), NewsListActivity.class));
                break;
        }
    }

    @Override
    public void OnBannerClick(int position) {
        String url = "http://192.168.31.232:8020/jsbridge-test/index.html";
        //String url = "http://dev-songchedai.oss-cn-shanghai.aliyuncs.com/other/201805/25/20180525114559130.jpg";
        //String url = "https://feh5-test.songchedai.com/#/newsdetail?id=68fe6c33-ff39-47ec-a0c9-cbcf8bb9e170";
        PageTool.toNewsDetailActivity(getContext(),"Banner",url,"");
    }
}
