package com.songcw.customer.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.songcw.customer.R;
import com.songcw.customer.home.mvp.model.AgentManagementBean;

/**
 * @name employee
 * @class name：com.songcw.employee.home.adapter
 * @class describe
 * @anthor wjb
 * @time 2018/9/11 13:58
 * @change
 * @chang time
 * @class 经纪人管理 adapter
 */
public class AgentManagementAdapter extends BaseQuickAdapter<AgentManagementBean.Data,BaseViewHolder>{

    private Context mContext;
    public AgentManagementAdapter(Context context) {
        super(R.layout.item_agent_management, null);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AgentManagementBean.Data item) {
        Glide.with(mContext).load(item.qrCodeUrl).into((ImageView) helper.getView(R.id.iv_agent_avatar));
        helper.setText(R.id.tv_agent_name,item.userName);
        helper.setText(R.id.tv_agent_phone,item.userMobile);
        helper.setText(R.id.tv_agent_status,item.isActivated ? mContext.getString(R.string.activated) : mContext.getString(R.string.pending_activate));
        ((TextView) helper.getView(R.id.tv_agent_status)).setTextColor(item.isActivated ? mContext.getResources().getColor(R.color.color_A0A0A0)
                : mContext.getResources().getColor(R.color.color_E7B24D));
    }
}
