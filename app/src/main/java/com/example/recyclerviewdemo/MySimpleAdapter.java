package com.example.recyclerviewdemo;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MySimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    protected List<String> mDatas;

    /**
     * 定义ItemClickListener接口
     */
    public interface OnItemClickListener{
        //点击方法
        void onItemClick(View view,int position);
        //长点击方法
        void onItemLongClick(View view,int position);
    }

    //定义点击监听事件对象
    private OnItemClickListener mOnItemClickListener;

    /**
     * 设置点击监听事件的方法，供外部Activity回调
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener=listener;
    }

    public MySimpleAdapter(Context context,List<String> datas) {
        super();
        this.mContext=context;
        this.mDatas=datas;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 绑定ViewHolder
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int pos) {
        //给TextView赋值
        holder.tv.setText(mDatas.get(pos));
        //判断点击监听事件是否为空
        setUpItemEvent(holder);

    }

    /**
     * 监听点击事件是否为空
     */
    protected void setUpItemEvent(final MyViewHolder holder) {
        if (mOnItemClickListener != null) {
            //click
            holder.itemView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    //更新每个Item的位置索引position
                    int layoutPosition=holder.getPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, layoutPosition);
                }
            });

            //longClick
            holder.itemView.setOnLongClickListener(new OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    //更新每个Item的位置索引position
                    int layoutPosition=holder.getPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutPosition);
                    return false;
                }
            });
        }
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        View view=mInflater.inflate(R.layout.item_single_textview, arg0,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    /**
     * 添加动画
     */
    public void addData(int pos){
        mDatas.add(pos,"Insert One");
        notifyItemInserted(pos);//不可使用notifyDataSetChanged();
    }

    /**
     * 删除动画
     */
    public void deleteData(int pos){
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

}

/**
 * 自定义内部类MyViewHolder
 * ViewHolder一般应包含所有控件
 */
class MyViewHolder extends ViewHolder{
    TextView tv;
    public MyViewHolder(View itemView) {
        super(itemView);
        //初始化TextView
        tv=(TextView) itemView.findViewById(R.id.id_tv);
    }

}
