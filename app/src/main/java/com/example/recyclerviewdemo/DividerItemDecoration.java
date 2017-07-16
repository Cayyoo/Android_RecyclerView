package com.example.recyclerviewdemo;

/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * This class is from the v7 samples of the Android SDK. It's not by me!
 * <p/>
 * See the license above for details.
 *
 *
 */

/**
 * DividerItemDecoration.java的分割线样式默认为白色 。
 *
 * 自定义颜色样式：
 * 1.DividerItemDecoration是在styles.xml中定义的Theme中获取颜色样式，
 * 2.可在res目录下新建drawable文件夹，新建divider.xml并设置样式数据
 * 3.在styles.xml中的Theme中添加item，由item引用。
 * 4.示例：<item name="android:listDivider">@drawable/divider</item>
 *
 * 重要步骤：
 * 在styles.xml的AppTheme中，设置listDivider为我们的divider.xml文件。比如
 * <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
 *   <item name="android:listDivider">@drawable/divider_02</item>
 * </style>
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration
{

    //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;



    private Drawable mDivider;//Item间隔图片资源

    private int mOrientation;//方向：水平/垂直

    public DividerItemDecoration(Context context, int orientation)
    {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    //设置屏幕的方向
    public void setOrientation(int orientation)
    {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST)
        {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    /**
     * 绘制
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent)
    {
        Log.v("recyclerview - itemdecoration", "onDraw()");
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    //画竖线
    public void drawVertical(Canvas c, RecyclerView parent)
    {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);
            
            //获得child的布局信息
            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(
                    parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);//设置范围
            mDivider.draw(c);//绘制
        }
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    public void drawHorizontal(Canvas c, RecyclerView parent)
    {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);
            
            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 提供绘制分割线的间隙。
     * 由于Divider也有长宽高，每一个Item需要向下或者向右偏移
     */
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,RecyclerView parent){
        if (mOrientation == VERTICAL_LIST){
            int childCount = parent.getAdapter().getItemCount();
            //如果是最后一行，则不需要绘制底部
            if (!isLastRaw(parent, itemPosition, childCount)) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            }
            
            //画横线，就是往下偏移一个分割线的高度
            //outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else{
            //画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
    
    //设置LinearLayoutManager时判断是否最后一行
    private boolean isLastRaw(RecyclerView parent, int pos,int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        
        if (layoutManager instanceof LinearLayoutManager) {
            if (pos >= childCount-1)// 如果是最后一行，则不需要绘制底部
                return true;
        }
        return false;
    }
    
}
