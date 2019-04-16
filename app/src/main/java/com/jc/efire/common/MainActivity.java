package com.jc.efire.common;

import com.jc.basecore.mvp.BaseActivity;
import com.jc.basecore.util.ActivityUtil;
import com.jc.basecore.widget.NoScrollViewPager;
import com.jc.basecore.widget.nicetoast.Toasty;
import com.jc.efire.R;
import com.jc.efire.mvp.section.MainContentSection;
import com.jc.efire.mvp.section.MainEventReceiveSection;

public class MainActivity extends BaseActivity {
    private MainContentSection mainContentSection;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void addSections() {
        addSection(new MainEventReceiveSection(this));
        mainContentSection = new MainContentSection(this);
        addSection(mainContentSection);
    }

    public void onBackPressed() {
        if (ActivityUtil.isOverWhenPressAgain(2000)) {
            Toasty.normal(this, getString(R.string.exit_press_again));
        } else {
            super.onBackPressed();
        }
    }

    public NoScrollViewPager getMainViewPager() {
        if (mainContentSection == null) return null;
        return mainContentSection.getViewPager();
    }
}
