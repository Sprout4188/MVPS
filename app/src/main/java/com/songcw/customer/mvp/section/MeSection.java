package com.songcw.customer.mvp.section;

import com.songcw.basecore.mvp.BasePresenter;
import com.songcw.basecore.mvp.BaseSection;
import com.songcw.basecore.mvp.IController;

public class MeSection extends BaseSection<BasePresenter> implements IController.IView {

    public MeSection(Object source) {
        super(source);
    }

    @Override
    public BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void init() {

    }
}