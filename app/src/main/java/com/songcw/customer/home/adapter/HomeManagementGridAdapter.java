package com.songcw.customer.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.songcw.customer.R;
import com.songcw.customer.home.mvp.model.HomeManagementFuncBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @name HomeManagementGridAdapter
 * @class name：com.songcw.model.home
 * @class describe
 * @anthor wjb
 * @time 2018/9/4 16:22
 * @change
 * @chang time
 * @class 首页管理功能 adpater
 */
public class HomeManagementGridAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<HomeManagementFuncBean> mResultList = new ArrayList<>();

    public HomeManagementGridAdapter(Context context, List<HomeManagementFuncBean> resultList) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mResultList = resultList;
    }

    @Override
    public int getCount() {
        return mResultList.size() > 10 ? 10 : mResultList.size();
    }

    @Override
    public Object getItem(int i) {
        return mResultList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_home_management_grid, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name_text);
            viewHolder.icon = (ImageView) view.findViewById(R.id.logo_image);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.name.setText(mResultList.get(i).name);

        Glide.with(mContext).load(mResultList.get(i).icon).into(viewHolder.icon);

        return view;
    }

    private class ViewHolder {
        TextView name;
        ImageView icon;
    }
    public List<HomeManagementFuncBean> getResultList(){
        return mResultList;
    }
}
