package k2.hot.white.library;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Jerry-Lee on 2016/2/25.
 * Function is
 * Modify By
 * Modify Function is
 */
public class UIHelper {

    /**
     * @param activity
     * @return 返回屏幕的宽度 默认480
     */
    public static int getScreenWidth(Activity activity) {
        if (activity != null) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            return metrics.widthPixels;
        }

        return 480;
    }

    /**
     * @param activity
     * @return 返回屏幕的高度 默认800
     */
    public static int getScreenHeight(Activity activity) {
        if (activity != null) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            return metrics.heightPixels;
        }
        return 800;
    }

    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    /***
     * dp转px
     * @param context
     * @param dp
     * @return
     */
    public static float dp2px(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, (float) dp, metrics);
    }




    /***
     * px转dp
     * @param context
     * @param px
     * @return
     */
    public static float px2dp(Context context, int px) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) px, metrics);
    }

    public static float sp2px(Context context,int sp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, (float) sp, metrics);
    }

    /***
     * 动态设置listview的高度
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    //动态设置listview高度
    public static void setListViewHeight(ListView listView, ListAdapter adapter, int count) {
        int totalHeight = 0;
        for (int i = 0; i < count; i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * count);

        listView.setLayoutParams(params);
    }


    /***
     * 清除父布局
     * @param v
     */
    public static void removeParent(View v){
        if(v != null){
            ViewParent parent = v.getParent();
            if(parent instanceof ViewGroup){
                ((ViewGroup)parent).removeView(v);
            }
        }
    }

    public static void calGridViewWidthAndHeigh(int numColumns ,GridView gridView) {

        // 获取GridView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0); // 计算子项View 的宽高

            if ((i+1)%numColumns == 0) {
                totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
            }

            if ((i+1) == len && (i+1)%numColumns != 0) {
                totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
            }
        }

        totalHeight += 40;

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        gridView.setLayoutParams(params);
    }

}
