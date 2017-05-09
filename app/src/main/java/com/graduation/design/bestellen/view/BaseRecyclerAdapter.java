package com.graduation.design.bestellen.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.graduation.design.bestellen.base.BaseViewHolder;

/**
 * Created by pxh on 2016/2/6.
 * 添加了OnItemClickListener，用于RecyclerView的项目点击事件
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    protected LayoutInflater inflater; //用于Inflater控件
    protected Context context;          //Activity的context

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    private OnItemClickListener mOnItemClickListener = null;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        setViews(holder, position);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

        }
    }

    /**
     * 在此方法中初始化View
     *
     * @param viewHolder viewHolder
     * @param position   数据的position
     */
    abstract public void setViews(BaseViewHolder viewHolder, int position);

    /**
     * 通过这个传入该位置的布局id
     *
     * @param position position
     * @return layout id
     */
    @Override
    abstract public int getItemViewType(int position);



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
