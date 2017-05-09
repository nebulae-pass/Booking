package com.graduation.design.bestellen.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.design.bestellen.R;
import com.graduation.design.bestellen.base.BaseViewHolder;


/**
 * Created by pxh on 2016/4/4.
 * extends this Adapter can have a footerView
 */
public abstract class FooterAdapter extends BaseRecyclerAdapter
{
    final int LOAD_MORE = R.layout.view_load_more;
    boolean isLoadComplete = false;

    int isVisibility = View.VISIBLE;

    public FooterAdapter(Context context)
    {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (viewType == LOAD_MORE)
            return new BaseViewHolder(inflater.inflate(viewType, parent, false));
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position)
    {
        //if(getItemCount()>)
        if (position == getItemCount() - 1) {
            if (isLoadComplete) {
                holder.obtainView(R.id.progressbar).setVisibility(View.GONE);
                holder.setText(R.id.load_text, "没有更多了");
                return;
            }
            holder.obtainView(R.id.progressbar).setVisibility(View.VISIBLE);
            holder.setText(R.id.load_text, "加载更多");
            holder.obtainView(R.id.load_more_container).setVisibility(isVisibility);
            return;
        }
        super.onBindViewHolder(holder,position);
    }

    @Override
    abstract public void setViews(BaseViewHolder viewHolder, int position);

    @Override
    public int getItemViewType(int position)
    {
        if (position == getItemCount() - 1)
            return LOAD_MORE;
        return getItemViewTypeWithoutFooterView(position);
    }

    public void setLoadComplete(boolean loadComplete)
    {
        isLoadComplete = loadComplete;
    }

    public void setLoadMoreVisibility(int visibility)
    {
        isVisibility = visibility;
    }

    abstract protected int getItemViewTypeWithoutFooterView(int position);

    @Override
    public int getItemCount()
    {
        if(getItemCountWithoutFooterView()==0)
            return 0;
        return getItemCountWithoutFooterView() + 1;

    }

    abstract protected int getItemCountWithoutFooterView();
}
