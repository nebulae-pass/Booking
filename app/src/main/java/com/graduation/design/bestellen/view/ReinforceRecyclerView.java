package com.graduation.design.bestellen.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.graduation.design.bestellen.R;

/**
 * Created by pxh on 2016/4/3.
 * extends
 */
@SuppressWarnings("unused")
public class ReinforceRecyclerView extends FrameLayout {
    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    ViewStub emptyStub;
    ViewStub loadingStub;

    OnLoadMoreListener loadMoreListener;

    boolean isNeedRefresh;
    boolean isLoadComplete = false;

    int emptyLayoutId;
    int loadingLayoutId;

    int lastVisibleItem;

    public ReinforceRecyclerView(Context context) {
        this(context, null);
    }

    public ReinforceRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReinforceRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ReinforceRecyclerView);
        try {
            isNeedRefresh = ta.getBoolean(R.styleable.ReinforceRecyclerView_needRefresh, true);
            emptyLayoutId = ta.getResourceId(R.styleable.ReinforceRecyclerView_layout_empty, 0);
            loadingLayoutId = ta.getResourceId(R.styleable.ReinforceRecyclerView_layout_loading, 0);
        } finally {
            ta.recycle();
        }
        initViews();
    }

    private void initViews() {
        View v;
        if (isNeedRefresh) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.view_reinforce_recyclerview, this);
            refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refresh);
        } else {
            //when choose no refreshing layout , use the loading interface instead of
            v = LayoutInflater.from(getContext()).inflate(R.layout.view_reinforce_recyclerview_no_refresh, this);
            loadingStub = (ViewStub) v.findViewById(R.id.loadingStub);
            loadingStub.setLayoutResource(loadingLayoutId);
        }
        recyclerView = (RecyclerView) v.findViewById(R.id.exList);
        emptyStub = (ViewStub) v.findViewById(R.id.emptyStub);
        emptyStub.setLayoutResource(emptyLayoutId);
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary),
                ContextCompat.getColor(getContext(), R.color.colorAccent));
        initRecyclerView();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter == null)
            return;
        recyclerView.setAdapter(adapter);
        RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                update();
            }

            private void update() {
                if (emptyLayoutId == 0)
                    return;
                if (recyclerView.getAdapter().getItemCount() == 0) {
                    emptyStub.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);

                } else {
                    emptyStub.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        };
        adapter.registerAdapterDataObserver(observer);
    }

    private void initRecyclerView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleCount = manager.findLastVisibleItemPosition() - manager.findFirstVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE      //scroll stop
                        && loadMoreListener != null                 //have listener
                        && recyclerView.getAdapter() != null        //have adapter
                        && recyclerView.getAdapter().getItemCount() - 1 > visibleCount
                        && lastVisibleItem + 1 == recyclerView.getAdapter().getItemCount()
                        && !isLoadComplete) {
                    loadMoreListener.loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (loadMoreListener != null)
                    lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager())
                            .findLastVisibleItemPosition();
            }
        });
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decoration) {
        recyclerView.addItemDecoration(decoration);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decoration, int index) {
        recyclerView.addItemDecoration(decoration, index);
    }


    public void setColorSchemeResources(@ColorRes int... colorResIds) {
        if (refreshLayout != null)
            refreshLayout.setColorSchemeResources(colorResIds);
    }

    public void setLoadComplete(boolean loadComplete) {
        isLoadComplete = loadComplete;
        ((FooterAdapter) recyclerView.getAdapter()).setLoadComplete(loadComplete);
    }

    public void setRefreshing(boolean refreshing) {
        if (refreshLayout == null)
            return;
        if (refreshing)
            refreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(true);
                }
            });
        else
            refreshLayout.setRefreshing(false);
    }

    public void setRefreshingAndHide(boolean refreshing) {
        setRefreshing(refreshing);
        if (refreshing) {
            recyclerView.setVisibility(GONE);
        } else
            recyclerView.setVisibility(VISIBLE);
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener refreshListener) {
        if (refreshLayout != null)
            refreshLayout.setOnRefreshListener(refreshListener);
    }

    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void showLoadingView() {
        if (loadingLayoutId != 0)
            loadingStub.setVisibility(View.VISIBLE);
    }

    public void hideLoadingView() {
        if (loadingLayoutId != 0)
            loadingStub.setVisibility(View.GONE);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public interface OnLoadMoreListener {
        void loadMore();
    }
}
