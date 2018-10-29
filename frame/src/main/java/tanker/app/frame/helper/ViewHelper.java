package tanker.app.frame.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import tanker.app.frame.R;
import tanker.app.frame.adapter.BaseAdapter;
import tanker.app.frame.listener.CustomListener;
import tanker.app.frame.util.ImageManager;

/**
 * @author : Tanker
 * @date : 2018/08/21
 * @describe : 只要提供一些activity以及fragment公用的View操作
 */
public class ViewHelper {

    private Activity mActivity;
    private View mFragmentView;
    private AlertDialog mConfirmationDialog, mInfoDialog;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ViewHelper() {
    }

    public ViewHelper(Activity activity) {
        this.mActivity = activity;
    }

    public ViewHelper(Activity activity, View fragmentView) {
        this.mActivity = activity;
        this.mFragmentView = fragmentView;
    }

    /**
     * 根据id获取View
     *
     * @param viewId 控件id
     * @return 该id对应的View
     */
    public <T extends View> T findView(@IdRes int viewId) {
        if (mFragmentView != null) {
            //Fragment
            return (T) mFragmentView.findViewById(viewId);
        }
        //Activity
        return (T) mActivity.findViewById(viewId);
    }

    /**
     * 设置显示内容
     *
     * @param viewId viewId 控件id
     * @param resId  字符串id
     */
    public void setText(@IdRes int viewId, @StringRes int resId) {
        View view = findView(viewId);
        if (isTextView(view)) {
            ((TextView) view).setText(mActivity.getString(resId));
        }
        if (isButton(view)) {
            ((Button) view).setText(mActivity.getString(resId));
        }
        if (isEditText(view)) {
            EditText editText = (EditText) view;
            String text = mActivity.getString(resId);
            editText.setText(text);
            editText.setSelection(text.length());
        }
    }

    /**
     * 获取View的内容值
     *
     * @param viewId viewId 控件id
     */
    public String getInput(@IdRes int viewId) {
        String text = "";
        View view = findView(viewId);
        if (isTextView(view)) {
            text = ((TextView) view).getText().toString();
        }
        if (isButton(view)) {
            text = ((Button) view).getText().toString();
        }
        if (isEditText(view)) {
            text = ((EditText) view).getText().toString();
        }
        return text;
    }

    /**
     * 判断输入框是否为空（支持EditText）
     *
     * @param viewId
     * @return 默认为空
     */
    public boolean isEmptyInput(@IdRes int viewId) {
        View view = findView(viewId);
        if (isEditText(view)) {
            return TextUtils.isEmpty(((EditText) view).getText().toString());
        }
        return true;
    }

    /**
     * 设置字体颜色
     *
     * @param viewId
     * @param colorId
     */
    public void setTextColor(@IdRes int viewId, int colorId) {
        View view = findView(viewId);
        if (isTextView(view)) {
            ((TextView) view).setTextColor(ContextCompat.getColor(mActivity,colorId));
        }
        if (isButton(view)) {
            ((Button) view).setTextColor(ContextCompat.getColor(mActivity,colorId));
        }
        if (isEditText(view)) {
            ((EditText) view).setTextColor(ContextCompat.getColor(mActivity,colorId));
        }
    }

    /**
     * 设置View可见性
     *
     * @param viewId viewId 控件id
     * @param status 显示状态（View.VISIBLE，View.GONE，View.INVISIBLE）
     */
    public void setVisibility(@IdRes int viewId, @IntegerRes int status) {
        findView(viewId).setVisibility(status);
    }

    /**
     * 设置View可见性
     *
     * @param viewId     viewId 控件id
     * @param visibility 是否显示
     */
    public void setVisibility(@IdRes int viewId, boolean visibility) {
        setVisibility(viewId, visibility ? View.VISIBLE : View.GONE);
    }

    /**
     * 加载图片
     *
     * @param viewId
     * @param url
     */
    public void loadImage(@IdRes int viewId, String url) {
        ImageManager.load(mActivity, url).into((ImageView) findView(viewId));
    }

    /**
     * 加载本地图片
     *
     * @param viewId
     * @param fileUrl 本机图片地址
     */
    public void loadFileImage(@IdRes int viewId, String fileUrl) {
        ImageManager.loadFile(mActivity, fileUrl).into((ImageView) findView(viewId));
    }

    /**
     * toast提示框
     *
     * @param resId viewId 控件id
     */
    public void toast(@StringRes int resId) {

    }

    /**
     * toast提示框
     *
     * @param text
     */
    public void toast(String text) {

    }

    /**
     * 界面等待框
     *
     * @param resId viewId 控件id
     */
    public ProgressDialog showWaiting(@StringRes int resId) {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(mActivity.getString(resId));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    /**
     * 使用系统的dialog
     *
     * @param titleId
     * @param msgId
     * @param confimationOnListener
     * @param negativeId
     * @param positiveId
     */
    public AlertDialog showConfirmation(@StringRes int titleId, @StringRes int msgId, final CustomListener.ConfimationOnListener confimationOnListener, @StringRes int negativeId, @StringRes int positiveId) {
        return mConfirmationDialog = new AlertDialog.Builder(mActivity)
                .setCancelable(true)
                .setTitle(titleId)
                .setMessage(msgId)
                .setNegativeButton(negativeId, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confimationOnListener.negative();
                        confimationOnListener.onNext();
                    }
                })
                .setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confimationOnListener.positive();
                        confimationOnListener.onNext();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        confimationOnListener.onDismiss();
                    }
                })
                .show();
    }

    /**
     * 信息提示框
     *
     * @param messageId
     * @param confimationInfoOnListener
     * @param positiveId
     */
    public AlertDialog showConfirmation(@StringRes int messageId, CustomListener.ConfimationInfoOnListener confimationInfoOnListener, @StringRes int positiveId) {
        return showConfirmation(R.string.empty_str, messageId, confimationInfoOnListener, positiveId);
    }

    /**
     * 信息提示框（包含title）
     *
     * @param titleId
     * @param messageId
     * @param confimationOnListener
     * @param positiveId
     */
    public AlertDialog showConfirmation(@StringRes int titleId, @StringRes int messageId, final CustomListener.ConfimationInfoOnListener confimationOnListener, @StringRes int positiveId) {
        return mInfoDialog = new AlertDialog.Builder(mActivity)
                .setCancelable(true)
                .setTitle(titleId)
                .setMessage(messageId)
                .setNeutralButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(positiveId, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confimationOnListener.positive();
                        confimationOnListener.onNext();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        confimationOnListener.onDismiss();
                    }
                })
                .show();
    }

    /**
     * 关闭
     */
    public void dismiss() {
        //等待框
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        //下拉按钮
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    /**
     * 初始化RecyclerView
     *
     * @param recyclerViewId
     * @param itemDecoration
     */
    public RecyclerView initRecyclerView(int recyclerViewId, RecyclerView.ItemDecoration itemDecoration, BaseAdapter adapter) {
        if (recyclerViewId < 0) {
            return null;
        }
        RecyclerView recyclerView = findView(recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        if (itemDecoration != null) {
            recyclerView.addItemDecoration(itemDecoration);
        }
        if(adapter != null){
            recyclerView.setAdapter(adapter);
        }
        return recyclerView;
    }

    /**
     * 初始化SwipeRefreshLayout
     *
     * @param swipeRefreshId
     * @return
     */
    public SwipeRefreshLayout initSwipeRefresh(int swipeRefreshId) {
        if (swipeRefreshId < 0) {
            return swipeRefreshLayout;
        }
        swipeRefreshLayout = findView(swipeRefreshId);
        //设置颜色
//        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#35B8FB"), Color.parseColor("#D7FB35"), Color.parseColor("#1B1D94"));
        return swipeRefreshLayout;
    }

    /**
     * 打开下拉刷新状态
     */
    public void openSwipeRefresh() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    /**
     * 控件是否为TextView
     *
     * @param view
     * @return
     */
    private boolean isTextView(View view) {
        return view instanceof TextView;
    }

    /**
     * 控件是否为EditText
     *
     * @param view
     * @return
     */
    private boolean isEditText(View view) {
        return view instanceof EditText;
    }

    /**
     * 控件是否为Button
     *
     * @param view
     * @return
     */
    private boolean isButton(View view) {
        return view instanceof Button;
    }

}