# RecyclerView
本例实现RecycylerView的功能：
 * LayoutManager实现ListView、GridView、横向ListView/GridView、瀑布流（RecyclerView通过设置不同的LayoutManager显示不同的效果）
 * ItemDecoration实现的原理、定制
 * ItemAnimator的使用
 * RecyclerView没有提供Item Click/longClick的回调。
 * 需要手动在Adapter中提供Item Click/longClick的回调
 
 
重要的类：
 * Adapter
 * ViewHolder
 * LayoutManager
 * ItemDecoration
 * ItemAnimator
 
 ```java
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
 ```
 
 ```java
    //设置LinearLayoutManager时判断是否最后一行
    private boolean isLastRaw(RecyclerView parent, int pos,int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        
        if (layoutManager instanceof LinearLayoutManager) {
            /*
             * 如果是最后一行，则不需要绘制底部分割线。
             * 若添加了头、尾布局，与头、尾布局衔接处也不绘制分割线，则if条件是pos >= childCount-2 || pos==0
             */
            if (pos >= childCount-1)
                return true;
        }
        return false;
    }
 ```
 
![screenshot](https://github.com/ykmeory/RecyclerView/blob/master/screenshot_01.jpg "截图")
&nbsp;&nbsp;&nbsp;
![screenshot02](https://github.com/ykmeory/Android_RecyclerView/blob/master/screenshot_02.jpg "去掉最后一行分割线")
