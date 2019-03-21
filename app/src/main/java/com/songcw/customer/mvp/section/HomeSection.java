package com.songcw.customer.mvp.section;

import com.songcw.basecore.mvp.BaseSection;
import com.songcw.basecore.mvp.IController;
import com.songcw.basecore.widget.bannerView.Banner;
import com.songcw.basecore.widget.bannerView.listener.OnBannerListener;
import com.songcw.basecore.widget.bannerView.loader.GlideImageLoader;
import com.songcw.customer.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * home
 */
public class HomeSection extends BaseSection {
    @BindView(R.id.view_banner)
    Banner mViewBanner;

    public HomeSection(Object source) {
        super(source);
    }

    @Override
    public IController.IPresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void init() {
        getBannerData();
    }

    private void getBannerData() {
        List<Integer> urlList = new ArrayList<>();
        urlList.add(R.mipmap.img_banner);
        urlList.add(R.mipmap.img_banner);
        urlList.add(R.mipmap.img_banner);
        mViewBanner.setImages(urlList)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                })
                .start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewBanner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewBanner.stopAutoPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewBanner != null) mViewBanner.releaseBanner();
    }
}
