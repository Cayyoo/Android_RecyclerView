package com.example.recyclerviewdemo;

import java.util.ArrayList;
import java.util.List;

import com.example.recyclerviewdemo.MySimpleAdapter.OnItemClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * RecyclerView通过设置不同的LayoutManager显示不同的效果
 *
 * 重要的类：
 * Adapter
 * ViewHolder
 * LayoutManager
 * ItemDecoration
 * ItemAnimator
 */

/**
 * 本例实现RecycylerView的功能：
 * 1.LayoutManager实现ListView、GridView、横向ListView/GridView、瀑布流
 * 2.ItemDecoration实现的原理、定制
 * 3.ItemAnimator的使用
 * 4.RecyclerView没有提供Item Click/longClick的回调。
 * 需要手动在Adapter中提供Item Click/longClick的回调
 */
public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MySimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据源
        initDatas();
        //初始化view
        initViews();
        //设置适配器
        mAdapter=new MySimpleAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        //设置RecyclerView的布局管理为ListView样式
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置动画效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置点击监听事件
        mAdapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "long click_"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click_"+position, Toast.LENGTH_SHORT).show();
            }
        });

        //设置RecyclerView的Item间分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
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

        switch (id) {
            case R.id.action_add:
                //添加动画
                mAdapter.addData(1);
                break;
            case R.id.action_delete:
                //删除动画
                mAdapter.deleteData(1);
                break;
            case R.id.action_gridview:
                //设置为3列的gridview样式
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.action_listview:
                //设置为listview
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_staggered:
                Intent intent=new Intent(this,StaggeredGridLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.action_hor_gridview:
                //设置为5列的水平gridview
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
