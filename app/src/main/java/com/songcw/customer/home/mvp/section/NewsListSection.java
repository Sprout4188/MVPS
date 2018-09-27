package com.songcw.customer.home.mvp.section;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
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
import com.songcw.customer.home.adapter.NewsListAdapter;
import com.songcw.customer.home.mvp.presenter.NewsListPresenter;
import com.songcw.customer.util.PageTool;
import com.songcw.customer.home.mvp.model.NewsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @name employee
 * @class name：com.songcw.employee.home.mvp.section
 * @class describe
 * @anthor wjb
 * @time 2018/9/5 11:17
 * @change
 * @chang time
 * @class 颂车头条列表 section
 */
public class NewsListSection extends BaseSection<NewsListPresenter> {

    private static final int PAGE_SIZE = 6;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private NewsListAdapter mNewsListAdapter;
    private int nextRequestPage = 1;

    public NewsListSection(Object source) {
        super(source);
    }

    @Override
    public NewsListPresenter onCreatePresenter() {
        return new NewsListPresenter(this);
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findView(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findView(R.id.recyclerView);

        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mNewsListAdapter = new NewsListAdapter();
        mNewsListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecyclerView.setAdapter(mNewsListAdapter);

    }

    @Override
    protected void initEvents() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        //添加Android自带的分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mNewsListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        },mRecyclerView);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {

                String url = "http://dev-songchedai.oss-cn-shanghai.aliyuncs.com/other/201805/25/20180525114559130.jpg";
                //String url = "https://feh5-test.songchedai.com/#/newsdetail?id=68fe6c33-ff39-47ec-a0c9-cbcf8bb9e170";
                PageTool.toNewsDetailActivity(getContext(),"toutiao",url,"");
            }
        });

        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    private void refresh() {
        nextRequestPage = 1;
        mNewsListAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        showLoading();
        new Request(nextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<NewsEntity> data) {
                setData(true, data);
                mNewsListAdapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
                hideLoading();
            }

            @Override
            public void fail(Exception e) {
               Toasty.normal(getContext(),"net error");
                mNewsListAdapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
                hideLoading();
            }
        }).start();
    }

    private void loadMore() {

        new Request(nextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<NewsEntity> data) {
                boolean isRefresh = nextRequestPage == 1;
                setData(isRefresh, getData(10));
            }

            @Override
            public void fail(Exception e) {
                mNewsListAdapter.loadMoreFail();
                Toasty.normal(getContext(),"net error");
            }
        }).start();
    }

    private List<NewsEntity> getData(int count) {
        List<NewsEntity> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            NewsEntity entity = new NewsEntity();
            entity.name = "我在人民广场吃炸鸡";
            entity.time = "2018-9-5 14:51";
            entity.img = R.mipmap.img_smoll_pepole;
            data.add(entity);
        }
        return data;
    }

    private void setData(boolean isRefresh, List data) {
        nextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mNewsListAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mNewsListAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mNewsListAdapter.loadMoreEnd(isRefresh);
            Toasty.normal(getContext(), "no more data");
        } else {
            mNewsListAdapter.loadMoreComplete();
            //hideLoading();
        }
    }


    interface RequestCallBack {
        void success(List<NewsEntity> data);

        void fail(Exception e);
    }

    static class Request extends Thread {
        private static final int PAGE_SIZE = 6;
        private int mPage;
        private RequestCallBack mCallBack;
        private Handler mHandler;

        private static boolean mFirstPageNoMore;
        private static boolean mFirstError = true;

        public Request(int page, RequestCallBack callBack) {
            mPage = page;
            mCallBack = callBack;
            mHandler = new Handler(Looper.getMainLooper());
        }

        private List<NewsEntity> getData(int count) {
            List<NewsEntity> data = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                NewsEntity entity = new NewsEntity();
                entity.name = "我在人民广场吃炸鸡";
                entity.time = "2018-9-5 14:51";
                entity.img = R.mipmap.img_smoll_pepole;
                data.add(entity);
            }
            return data;
        }

        @Override
        public void run() {
            try {Thread.sleep(500);} catch (InterruptedException e) {}

            if (mPage == 2 && mFirstError) {
                mFirstError = false;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallBack.fail(new RuntimeException("fail"));
                    }
                });
            } else {
                int size = PAGE_SIZE;
                if (mPage == 1) {
                    if (mFirstPageNoMore) {
                        size = 1;
                    }
                    mFirstPageNoMore = !mFirstPageNoMore;
                    if (!mFirstError) {
                        mFirstError = true;
                    }
                } else if (mPage == 4) {
                    size = 1;
                }

                final int dataSize = size;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallBack.success(getData(dataSize));
                    }
                });
            }
        }
    }

}
