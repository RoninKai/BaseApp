package tanker.app.frame.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import tanker.app.frame.adapter.BaseAdapter;
import tanker.app.frame.helper.ViewHelper;
import tanker.app.frame.listener.CustomListener;
import tanker.app.frame.util.LogUtils;
import tanker.app.frame.util.eventbus.Event;
import tanker.app.frame.util.eventbus.EventBusUtil;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/08/21
 * @describe : Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private final String TAG = getClass().getSimpleName();
    private final String TAG_ONCREAT = TAG + "_onCreate()";
    private final String TAG_ONDESTROY = TAG + "_onDestroy()";

    private ViewHelper viewHelper;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.v(TAG_ONCREAT);
        setContentView(getContentViewId());
        ActivityCollector.addActivity(this);
        unbinder = ButterKnife.bind(this);
        viewHelper = new ViewHelper(this);
        registerEventBus();
//        initToolbar(getTitleText());
        initRecyclerView();
        initSwipeRefresh();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.v(TAG_ONDESTROY);
        unbinder.unbind();
        unregisterEventBus();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (onBack()) {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuId = getMenuId();
        if (menuId > 0) {
            getMenuInflater().inflate(menuId, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        menuSelected(item.getItemId());
        return true;
    }

    @Override
    public void toast(@StringRes int resId) {
        viewHelper.toast(resId);
    }

    @Override
    public void toast(String text) {
        viewHelper.toast(text);
    }

    @Override
    public ProgressDialog showWaiting(@StringRes int resId) {
        return viewHelper.showWaiting(resId);
    }

    @Override
    public AlertDialog showConfirmation(@StringRes int titleId, @StringRes int messageId, CustomListener.ConfimationOnListener confimationOnListener, @StringRes int negativeId, @StringRes int positiveId) {
        return viewHelper.showConfirmation(titleId, messageId, confimationOnListener, negativeId, positiveId);
    }

    @Override
    public AlertDialog showConfirmation(@StringRes int messageId, CustomListener.ConfimationInfoOnListener confimationInfoOnListener, @StringRes int positiveId) {
        return viewHelper.showConfirmation(messageId, confimationInfoOnListener, positiveId);
    }

    @Override
    public AlertDialog showConfirmation(@StringRes int titleId, @StringRes int messageId, CustomListener.ConfimationInfoOnListener confimationInfoOnListener, @StringRes int positiveId) {
        return viewHelper.showConfirmation(titleId, messageId, confimationInfoOnListener, positiveId);
    }

    @Override
    public void dismiss() {
        viewHelper.dismiss();
    }

    /**
     * 重写执行操作
     *
     * @return
     */
    protected boolean onBack() {
        return true;
    }

    /**
     * 根据id获取View
     *
     * @param viewId 控件id
     * @return 该id对应的View
     */
    protected View findView(@IdRes int viewId) {
        return viewHelper.findView(viewId);
    }

    /**
     * 设置显示内容
     *
     * @param viewId viewId 控件id
     * @param resId  字符串id
     */
    protected void setText(@IdRes int viewId, @StringRes int resId) {
        viewHelper.setText(viewId, resId);
    }

    /**
     * 获取View的内容值
     *
     * @param viewId viewId 控件id
     */
    protected String getInput(@IdRes int viewId) {
        return viewHelper.getInput(viewId);
    }

    /**
     * 判断输入框是否为空
     *
     * @param viewId
     * @return
     */
    protected boolean isEmptyInput(@IdRes int viewId) {
        return viewHelper.isEmptyInput(viewId);
    }

    /**
     * 设置字体颜色
     *
     * @param viewId
     * @param colorId
     */
    protected void setTextColor(@IdRes int viewId, int colorId) {
        viewHelper.setTextColor(viewId, colorId);
    }

    /**
     * 设置View可见性
     *
     * @param viewId viewId 控件id
     * @param status 显示状态（View.VISIBLE，View.GONE，View.INVISIBLE）
     */
    protected void setVisibility(@IdRes int viewId, @IntegerRes int status) {
        viewHelper.setVisibility(viewId, status);
    }

    /**
     * 设置View可见性
     *
     * @param viewId     viewId 控件id
     * @param visibility 是否显示
     */
    protected void setVisibility(@IdRes int viewId, boolean visibility) {
        viewHelper.setVisibility(viewId, visibility);
    }

    /**
     * 加载图片
     *
     * @param viewId
     * @param url
     */
    protected void loadImage(@IdRes int viewId, String url) {
        viewHelper.loadImage(viewId, url);
    }

    /**
     * 加载本地图片
     *
     * @param viewId
     * @param fileUrl 本机图片地址
     */
    protected void loadFileImage(@IdRes int viewId, String fileUrl) {
        viewHelper.loadFileImage(viewId, fileUrl);
    }

    /*------------------------------------Menu-------------------------------*/

    /**
     * 获取menu布局id
     *
     * @return
     */
    protected int getMenuId() {
        return -1;
    }

    /**
     * menuItem事件触发
     *
     * @param menuId
     */
    protected void menuSelected(int menuId) {
    }

    /*-------------------------------RecyclerView---------------------------*/

    /**
     * 初始化RecyclerView（LayoutManager，ItemDecoration）
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = viewHelper.initRecyclerView(getRecyclerViewId(), getItemDecoration(), getRecyclerViewAdapter());
        if (recyclerView == null) {
            return;
        }
        setRecyclerView(recyclerView);
    }

    /**
     * 设置RecyclerView的拓展属性
     *
     * @param recyclerView
     */
    protected void setRecyclerView(RecyclerView recyclerView) {
    }

    /**
     * 获取RecyclerView适配器
     *
     * @return
     */
    protected BaseAdapter getRecyclerViewAdapter() {
        return null;
    }

    /**
     * 获取RecyclerView控件id
     *
     * @return
     */
    protected int getRecyclerViewId() {
        return -1;
    }

    /**
     * 获取分割线
     *
     * @return
     */
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    /*-------------------------------SwipeRefreshLayout---------------------------*/

    /**
     * 初始化SwipeRefreshLayout（OnRefreshListener）
     */
    private void initSwipeRefresh() {
        SwipeRefreshLayout swipeRefreshLayout = viewHelper.initSwipeRefresh(getSwipeRefreshId());
        if (swipeRefreshLayout == null) {
            return;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        setSwipeRefresh(swipeRefreshLayout);
        //首次打开页面加载效果，并获取数据
        viewHelper.openSwipeRefresh();
        refreshData();
    }

    /**
     * 设置SwipeRefreshLayout的拓展属性
     *
     * @param swipeRefreshLayout
     */
    protected void setSwipeRefresh(SwipeRefreshLayout swipeRefreshLayout) {
    }

    /**
     * 下拉刷新数据（子类需重写）
     */
    protected void refreshData() {
    }

    protected int getSwipeRefreshId() {
        return -1;
    }

    /*-------------------------------EventBus-------------------------------*/

    /**
     * 注册EventBus
     */
    private void registerEventBus() {
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
    }

    /**
     * 销毁EventBus
     */
    private void unregisterEventBus() {
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    /**
     * 是否包含event事件
     *
     * @return
     */
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStickyEventBusCome(Event event) {
        if (event == null) {
            return;
        }
    }

    /**
     * 子类重写执行事件处理
     *
     * @param event
     */
    protected void receiveEvent(Event event) {
    }

    protected abstract int getContentViewId();

    protected abstract String getTitleText();

    protected abstract void initView();

}