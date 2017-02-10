package com.example.recyclerviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;

public class StaggeredAdapter extends MySimpleAdapter {
    //定义一个随机的高度
    private List<Integer> mHeights;

    /**
     * 带参构造
     */
    public StaggeredAdapter(Context context,List<String> datas) {
        super(context, datas);
        //变量mHeights初始化、给值
        mHeights=new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100+Math.random()*300));
        }
    }

    /**
     * 绑定ViewHolder
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int pos) {
        //item动态高度的设定
        LayoutParams lp=holder.itemView.getLayoutParams();
        lp.height=mHeights.get(pos);
        holder.itemView.setLayoutParams(lp);
        //给TextView赋值
        holder.tv.setText(mDatas.get(pos));
        //监听点击事件
        setUpItemEvent(holder);
    }

}


