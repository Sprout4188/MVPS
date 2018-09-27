package com.songcw.customer.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.songcw.basecore.widget.nicetoast.Toasty;
import com.songcw.basecore.widget.textviewfliper.BaseFlipperAdapter;
import com.songcw.customer.R;
import com.songcw.customer.util.PageTool;
import com.songcw.customer.home.mvp.model.NewsEntity;

import java.util.List;

/**
 * @name employee
 * @class name：com.songcw.employee.home.adapter
 * @class describe
 * @anthor wjb
 * @time 2018/9/7 16:52
 * @change
 * @chang time
 * @class describe
 */
public class FlipperAdapter extends BaseFlipperAdapter{

    private List<NewsEntity> mData;
    private Context mContext;

    public FlipperAdapter(Context context) {
        this.mData = mData;
        this.mContext = context;
    }

    public void setData(List<NewsEntity> data){
        mData = data;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public View getView(View convertView, final int position) {
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_headline, null);
        }

        TextView textView = convertView.findViewById(R.id.tv_headline_title);
        textView.setText(mData.get(position).name);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.normal(mContext,mData.get(position).name);
                if(mContext!=null){
                    String url = "http://dev-songchedai.oss-cn-shanghai.aliyuncs.com/other/201805/25/20180525114559130.jpg";
                    //String url = "https://feh5-test.songchedai.com/#/newsdetail?id=68fe6c33-ff39-47ec-a0c9-cbcf8bb9e170";
                    PageTool.toNewsDetailActivity(mContext,"toutiao",url,"");
                }
            }
        });

        return convertView;
    }
}
