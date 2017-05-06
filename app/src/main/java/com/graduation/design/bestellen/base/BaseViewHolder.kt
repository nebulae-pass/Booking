package com.graduation.design.bestellen.base

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by pan on 2017/5/5.
 */
class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private var mViews: SparseArray<View> = SparseArray()

    fun obtainView(viewId: Int): View {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            if (view == null)
                throw NullPointerException("layout id error or View id error")
            mViews.put(viewId, view)
        }
        return view
    }

    fun setText(id: Int, text: String): BaseViewHolder {
        (obtainView(id) as TextView).text = text
        return this
    }

    fun getImageView(id: Int): ImageView {
        return obtainView(id) as ImageView
    }

    fun setButtonListener(id: Int, onClickListener: View.OnClickListener): BaseViewHolder {
        obtainView(id).setOnClickListener(onClickListener)
        return this
    }

    fun getButton(resId: Int): Button {
        return obtainView(resId) as Button
    }
}