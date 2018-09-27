package com.songcw.customer.home.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.songcw.customer.R;
import com.songcw.customer.home.mvp.model.NewsEntity;


/**
 * @name employee
 * @class name：com.songcw.employee.home.adapter
 * @class describe
 * @anthor wjb
 * @time 2018/9/5 10:51
 * @change
 * @chang time
 * @class describe
 */
public class NewsListAdapter extends BaseQuickAdapter<NewsEntity,BaseViewHolder>{

    public NewsListAdapter() {
        super(R.layout.item_news_list, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        Glide.with(mContext).load(item.img).into((ImageView) helper.getView(R.id.iv_news_img));
        helper.setText(R.id.tv_news_name,item.name);
        helper.setText(R.id.tv_news_time,item.time);
    }
}

