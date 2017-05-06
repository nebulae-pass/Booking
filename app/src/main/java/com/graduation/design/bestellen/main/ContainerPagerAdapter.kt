package com.graduation.design.bestellen.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by pan on 2017/5/4.
 * container pager adapter
 */
class ContainerPagerAdapter(fm: FragmentManager, fragments: List<Fragment>, titles: List<String>) : FragmentPagerAdapter(fm) {
    val mFragments: List<Fragment> = fragments
    val mTitles: List<String> = titles

    override fun getItem(position: Int): Fragment = mFragments[position]

    override fun getCount() = mFragments.size

    override fun getPageTitle(position: Int) = mTitles[position]
}