package com.graduation.design.bestellen.view

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewStub
import android.widget.FrameLayout
import com.graduation.design.bestellen.R

/**
 * Created by pxh on 2016/4/3.
 * extends
 */
@Suppress("unused")
class ReinforceRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    var refreshLayout: SwipeRefreshLayout? = null
    lateinit var recyclerView: RecyclerView
        internal set
    private lateinit var mEmptyStub: ViewStub
    private var mLoadingStub: ViewStub? = null
    private lateinit var mErrorStub: ViewStub
    private var mErrorView: View? = null


    private var loadMoreListener: OnLoadMoreListener? = null

    private var isNeedRefresh: Boolean = false
    private var isLoadComplete = false

    private var mEmptyLayoutId: Int = 0
    private var mLoadingLayoutId: Int = 0
    private var mErrorLayoutId: Int = 0

    private var lastVisibleItem: Int = 0

    init {
        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.ReinforceRecyclerView)
        try {
            isNeedRefresh = ta.getBoolean(R.styleable.ReinforceRecyclerView_needRefresh, true)
            mEmptyLayoutId = ta.getResourceId(R.styleable.ReinforceRecyclerView_layout_empty, 0)
            mLoadingLayoutId = ta.getResourceId(R.styleable.ReinforceRecyclerView_layout_loading, 0)
            mErrorLayoutId = ta.getResourceId(R.styleable.ReinforceRecyclerView_layout_error, 0)

        } finally {
            ta.recycle()
        }
        initViews()
    }

    private fun initViews() {
        val v: View
        if (isNeedRefresh) {
            v = LayoutInflater.from(context).inflate(R.layout.view_reinforce_recyclerview, this)
            refreshLayout = v.findViewById(R.id.refresh) as SwipeRefreshLayout
        } else {
            //when choose no refreshing layout , use the loading interface instead of
            v = LayoutInflater.from(context).inflate(R.layout.view_reinforce_recyclerview_no_refresh, this)
            mLoadingStub = v.findViewById(R.id.loadingStub) as ViewStub
            mLoadingStub?.layoutResource = mLoadingLayoutId
        }
        recyclerView = v.findViewById(R.id.exList) as RecyclerView
        mEmptyStub = v.findViewById(R.id.emptyStub) as ViewStub
        mEmptyStub.layoutResource = mEmptyLayoutId
        mErrorStub = v.findViewById(R.id.errorStub) as ViewStub
        mErrorStub.layoutResource = mErrorLayoutId
        refreshLayout?.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary),
                ContextCompat.getColor(context, R.color.colorAccent))
        initRecyclerView()
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        if (adapter == null)
            return
        recyclerView.adapter = adapter
        val observer = object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                update()
            }

            private fun update() {
                if (mEmptyLayoutId == 0)
                    return
                if (recyclerView.adapter.itemCount == 0) {
                    mEmptyStub.visibility = View.VISIBLE
                    mErrorView?.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                } else {
                    mEmptyStub.visibility = View.GONE
                    mErrorView?.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }
        }
        adapter.registerAdapterDataObserver(observer)
    }

    private fun initRecyclerView() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val manager = recyclerView!!.layoutManager as LinearLayoutManager
                val visibleCount = manager.findLastVisibleItemPosition() - manager.findFirstVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE      //scroll stop
                        && loadMoreListener != null                 //have listener
                        && recyclerView.adapter != null        //have adapter
                        && recyclerView.adapter.itemCount - 1 > visibleCount
                        && lastVisibleItem + 1 == recyclerView.adapter.itemCount
                        && !isLoadComplete) {
                    loadMoreListener?.loadMore()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (loadMoreListener != null)
                    lastVisibleItem = (recyclerView!!.layoutManager as LinearLayoutManager)
                            .findLastVisibleItemPosition()
            }
        })
    }

    fun setError() {
        if (mErrorView == null) {
            mErrorView = mErrorStub.inflate()
        }
        mEmptyStub.visibility = View.GONE
        mLoadingStub?.visibility = View.GONE
        mErrorView?.visibility = View.VISIBLE
    }

    fun addItemDecoration(decoration: RecyclerView.ItemDecoration) {
        recyclerView.addItemDecoration(decoration)
    }

    fun addItemDecoration(decoration: RecyclerView.ItemDecoration, index: Int) {
        recyclerView.addItemDecoration(decoration, index)
    }


    fun setColorSchemeResources(@ColorRes vararg colorResIds: Int) {
        if (refreshLayout != null)
            refreshLayout?.setColorSchemeResources(*colorResIds)
    }

    fun setLoadComplete(loadComplete: Boolean) {
        isLoadComplete = loadComplete
        (recyclerView.adapter as FooterAdapter).setLoadComplete(loadComplete)
    }

    fun setRefreshing(refreshing: Boolean) {
        if (refreshLayout == null)
            return
        if (refreshing)
            refreshLayout?.post { refreshLayout?.isRefreshing = true }
        else
            refreshLayout?.isRefreshing = false
    }

    fun setRefreshingAndHide(refreshing: Boolean) {
        setRefreshing(refreshing)
        if (refreshing) {
            recyclerView.visibility = View.GONE
        } else
            recyclerView.visibility = View.VISIBLE
    }

    fun setOnRefreshListener(onRefresh: () -> Unit) {
        refreshLayout?.setOnRefreshListener {
            onRefresh()
        }
    }

    fun setOnLoadMoreListener(loadMoreListener: OnLoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }

    fun showLoadingView() {
        if (mLoadingLayoutId != 0) {
            mLoadingStub?.visibility = View.VISIBLE
            mErrorView?.visibility = View.GONE
        }
    }

    fun hideLoadingView() {
        if (mLoadingLayoutId != 0) {
            mLoadingStub?.visibility = View.GONE
            mErrorView?.visibility = View.GONE
        }
    }

    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = layoutManager
    }

    fun findErrorViewById(id: Int): View {
        if (mErrorView == null) {
            mErrorView = mErrorStub.inflate()
        }
        return (mErrorView as View).findViewById(id)
    }

    interface OnLoadMoreListener {
        fun loadMore()
    }
}
