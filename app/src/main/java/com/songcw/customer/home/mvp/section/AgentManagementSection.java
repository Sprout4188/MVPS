package com.songcw.customer.home.mvp.section;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.songcw.basecore.mvp.BaseSection;
import com.songcw.basecore.widget.nicetoast.Toasty;
import com.songcw.customer.R;
import com.songcw.customer.home.adapter.AgentManagementAdapter;
import com.songcw.customer.home.mvp.presenter.AgentManagementPresenter;
import com.songcw.customer.home.mvp.model.AgentManagementBean;
import com.songcw.customer.util.PageUtil;

/**
 * @name employee
 * @class name：com.songcw.employee.home.mvp.section
 * @class describe
 * @anthor wjb
 * @time 2018/9/11 13:48
 * @change
 * @chang time
 * @class 经纪人管理 section
 */
public class AgentManagementSection extends BaseSection<AgentManagementPresenter> implements AgentManagementView{

    private static final int PAGE_SIZE = 10;
    public static final int CODE_AGENT_ADD = 0x100;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private AgentManagementAdapter mAgentAdapter;

    private int pageIndex = 1;
    private int listTotal;

    public AgentManagementSection(Object source) {
        super(source);
    }

    @Override
    public AgentManagementPresenter onCreatePresenter() {
        return new AgentManagementPresenter(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case CODE_AGENT_ADD:
                refresh(false);
                break;
        }
    }

    @Override
    protected void initViews() {

        mSwipeRefreshLayout = (SwipeRefreshLayout) findView(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findView(R.id.recyclerView);

        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAgentAdapter = new AgentManagementAdapter(getContext());
        mAgentAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(mAgentAdapter);

    }

    @Override
    protected void initEvents() {

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(false);
            }
        });
        //添加Android自带的分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mAgentAdapter.setEnableLoadMore(false);//开启上拉
        mAgentAdapter.bindToRecyclerView(mRecyclerView);
        mAgentAdapter.disableLoadMoreIfNotFullPage(); //当item数量不够满屏时禁用加载更多
        mAgentAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);//开启动画
        mAgentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                refresh(true);
            }
        },mRecyclerView);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {

            }
        });

        mSwipeRefreshLayout.setRefreshing(true);
        refresh(false);

    }

    private void refresh(boolean isLoadMore) {

        if(!isLoadMore){
            pageIndex = 1;
            mAgentAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        }else {
            pageIndex++;
        }
        showLoading();
        mPresenter.getAgentsList(pageIndex,PAGE_SIZE,isLoadMore);
    }


    @Override
    public void getPageDataSucc(AgentManagementBean datas, boolean isLoadMore) {
        listTotal = Integer.valueOf(datas.count);
        hideLoading();
        if(listTotal <=0 ){
            mAgentAdapter.setEmptyView(View.inflate(getContext(), R.layout.layout_empty, null));
        }
        if (!isLoadMore) {
            mAgentAdapter.setNewData(datas.data);
        }else {
            //是否还有下一页数据
            if (PageUtil.hasMorePage(listTotal,PAGE_SIZE,pageIndex)) {
                mAgentAdapter.addData(datas.data);
                mAgentAdapter.loadMoreComplete();
            } else {
                //数据全部加载完毕
                mAgentAdapter.loadMoreEnd(!isLoadMore);
            }
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getPageDataFail(String message, boolean isLoadMore) {

        Toasty.normal(getContext(),message);
        hideLoading();
        if (isLoadMore) {
            pageIndex--;
            mAgentAdapter.loadMoreFail();
            mAgentAdapter.setEnableLoadMore(true);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
