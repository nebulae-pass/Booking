package com.graduation.design.bestellen.common;


import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pxh on 2016/3/11.
 */
public class ScrollingFABBehavior extends FloatingActionButton.Behavior {
    private int preY = 0;


    public ScrollingFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        return super.layoutDependsOn(parent, fab, dependency) || (dependency instanceof AppBarLayout);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        if (dependency instanceof AppBarLayout) {
            if (dependency.getY() >= preY) {
                fab.show();
                fab.setVisibility(View.VISIBLE);
            } else {
                fab.hide();
                fab.setVisibility(View.GONE);
            }
            preY = (int) dependency.getY();

        }
        return super.onDependentViewChanged(parent, fab, dependency);
    }
}
