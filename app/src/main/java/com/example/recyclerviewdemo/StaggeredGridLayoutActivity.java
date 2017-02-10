package com.example.recyclerviewdemo;

import java.util.ArrayList;
import java.util.List;

import com.example.recyclerviewdemo.MySimpleAdapter.OnItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class StaggeredGridLayoutActivity extends Activity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据源
        initDatas();
        //初始化view
        initViews();
        //设置适配器
        mAdapter=new StaggeredAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        //设置RecyclerView的布局管理
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        //设置点击监听事件
        mAdapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemLongClick(View view, int position) {
                //长按时删除
                mAdapter.deleteData(position);
            }

            @Override
            public void onItemClick(View view, int position) {
                //处理逻辑

            }
        });

        //设置RecyclerView的Item间分割线
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    /**
     * 初始化数据源
     */
    private void initDatas() {
        mDatas=new ArrayList<String>();
        //大写A到小写z
        for (int i = 'A'; i <= 'z'; i++) {
            //转换i的类型
            mDatas.add(""+(char)i);
        }
    }

    /**
     * 初始化View
     */
    private void initViews() {
        mRecyclerView=(RecyclerView) findViewById(R.id.id_recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 菜单回调方法
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //瀑布流高度mHeights是随机的，此处不需要内容
        switch (id) {
        }
        return super.onOptionsItemSelected(item);
    }
}
