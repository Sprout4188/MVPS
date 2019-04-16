package com.jc.efire.mvp.presenter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jc.basecore.http.ICallBack;
import com.jc.basecore.http.RetrofitUtil;
import com.jc.basecore.mvp.BasePresenter;
import com.jc.basecore.mvp.BaseSection;
import com.jc.basecore.util.DensityUtil;
import com.jc.basecore.widget.RecycleViewDivider;
import com.jc.basecore.widget.nicetoast.Toasty;
import com.jc.efire.R;
import com.jc.efire.common.Api;
import com.jc.efire.mvp.model.BaseListEntity;
import com.jc.efire.mvp.view.BaseListView;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Sprout on 2018/10/24
 */
public abstract class BaseListPresent<T, A extends BaseQuickAdapter, V extends BaseListView> extends BasePresenter<V> {
    private final int TYPE_REFRESH = 1;     //表示下拉刷新
    private final int TYPE_MORE = 2;        //表示加载更多
    private final int PAGE_SIZE = 10;       //每页多少条数据
    private final int PAGE_START = 1;       //起始页码
    private int mPageIndex = PAGE_START;    //1加载第1页, ...
    private int TOTAL_COUNTER = 0;          //总共多少条数据
    private int mCurrentCount = 0;          //当前已加载的item数量

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private A mAdapter;

    public BaseListPresent(V view) {
        super(view);
    }

    public BaseListPresent bind(SwipeRefreshLayout mSwipeRefreshLayout, RecyclerView mRecyclerView) {
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
        this.mRecyclerView = mRecyclerView;
        return this;
    }

    /**
     * 初始化配置, 请求第一页数据, 注册上拉下拉监听
     */
    public A init(int dividerHeightDp, int dividerColor) {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(0, 0, 0));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL, DensityUtil.dp2px(getContext(), dividerHeightDp), dividerColor));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        if (mAdapter == null) mAdapter = createAdapter();
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(View.inflate(getContext(), R.layout.layout_empty, null));
        mAdapter.openLoadAnimation();       //开启动画
        mAdapter.setEnableLoadMore(true);   //开启上拉
        mAdapter.disableLoadMoreIfNotFullPage();    //当item数量不够满屏时禁用加载更多
        mAdapter.setPreLoadNumber(2);       // 当列表滑动到倒数第2个Item的时候(默认是1)回调onLoadMoreRequested方法

        //进入界面时请求第一页数据
        loadFirstPage();

        //下拉刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFirstPage();
            }
        });
        //加载更多监听
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPageIndex++;
                Logger.t(TAG).d("加载第" + mPageIndex + "页数据, 每页大小为" + PAGE_SIZE);
                getPageDatas(TYPE_MORE);
                Logger.t(TAG).d("当前页码指针为" + mPageIndex);
            }
        }, mRecyclerView);
        return mAdapter;
    }

    @NonNull
    public abstract A createAdapter();

    /**
     * 加载第一页
     */
    public void loadFirstPage() {
        ((BaseSection) mView).showLoading();
        reset();
        Logger.t(TAG).d("进入时加载第" + mPageIndex + "页数据, 每页大小为" + PAGE_SIZE);
        getPageDatas(TYPE_REFRESH);
        Logger.t(TAG).d("当前页码指针为" + mPageIndex);
    }

    /**
     * 重置分页计数
     */
    public void reset() {
        mPageIndex = PAGE_START;
        TOTAL_COUNTER = 0;
        mCurrentCount = 0;
    }

    /**
     * 分页获取数据
     *
     * @param type 1:下拉刷新, 2:加载更多
     */
    private void getPageDatas(final int type) {
        Map<String, String> map = new HashMap<>();
        map.put("pageSize", PAGE_SIZE + "");
        map.put("currentPage", mPageIndex + "");
        putExtras(map);
        Api api = RetrofitUtil.create(Api.class);
        addDisposable(getQueryObservable(map, api), new ICallBack<BaseListEntity<T>>() {
            @Override
            public void onSuccess(BaseListEntity<T> results) {
                ((BaseSection) mView).hideLoading();
                TOTAL_COUNTER = Integer.valueOf(results.count);
                List<T> datas = mAdapter.getData();
                if (type == TYPE_REFRESH) {
                    datas.clear();
                    datas.addAll(results.data);
                    mAdapter.setNewData(datas);
                    mCurrentCount = datas.size();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                if (type == TYPE_MORE) {
                    if (mCurrentCount < TOTAL_COUNTER) {  //还有下一页数据
                        mAdapter.addData(results.data);
                        mCurrentCount = datas.size();
                        mAdapter.loadMoreComplete();
                    } else {
                        mAdapter.loadMoreEnd();         //数据全部加载完毕
                    }
                }
                if (isViewAttach()) mView.getPageDataSucc(results, type);
            }

            @Override
            public void onFail(String errorMsg) {
                ((BaseSection) mView).hideLoading();
                if (type == TYPE_REFRESH) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                if (type == TYPE_MORE) {
                    Logger.t(TAG).d("加载更多失败, 当前页码指针为" + mPageIndex);
                    mPageIndex--;
                    Logger.t(TAG).d("加载更多失败, 当前页码指针回退到上一页, 回退后为" + mPageIndex);
                    mAdapter.loadMoreFail();
                }
                Toasty.error(getContext(), errorMsg);
                if (isViewAttach()) mView.getPageDataFail(errorMsg, type);
            }
        });
    }

    /**
     * 发起请求的Observable
     */
    public abstract Observable<BaseListEntity<T>> getQueryObservable(Map<String, String> map, Api api);

    /**
     * 补充额外请求参数
     */
    private void putExtras(Map<String, String> map) {
        if (isViewAttach()) mView.putExtras(map);
    }
}
