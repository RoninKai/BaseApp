package tanker.app.frame.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * @author : zhoukai
 * @e-mail : zhoukai@zto.cn
 * @time   : 2018/07/05
 * @desc   : 封装PopupWindow，可重写相关函数设置属性
 */
public abstract class BasePopWindow extends PopupWindow implements PopupWindow.OnDismissListener {

    public Context mContext;
    /**
     * 配合setFocusable(),setOutsideTouchable() 实现设置窗口响应 ，具体设置查看配置注释
     */
    public final ColorDrawable BACKGROUNDDRAWABLE = null;

    public BasePopWindow(Context context) {
        mContext = context;
        setWidth(getWidthSize());
        setHeight(getHeightSize());
        initData();
        View view = getContentView(this);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        setContentView(view);
        /*
        * setBackGroundDrawable   setFocusable    setOutsideTouchable     “点击返回按钮”      “点击外部区域”
        *  colorDrawable           true            true                     关闭弹框              关闭弹框
        *  colorDrawable           true            false                    关闭弹框              关闭弹框
        *  colorDrawable           false           true                     退出当前Activity      关闭弹框
        *  colorDrawable           false           false                    退出当前Activity      不关闭弹框
        *  null                    true            true                     无操作响应            不关闭弹框
        *  null                    true            false                    无操作响应            不关闭弹框
        *  null                    false           true                     退出当前Activity      不关闭弹框
        *  null                    false           false                    退出当前Activity      不关闭弹框
        * */
        setBackgroundDrawable(getBackgroundDrawable());
        setFocusable(getFocusable());
        setOutsideTouchable(getOutsideTouchable());
        if (getAnimaStyle() > 0) {
            setAnimationStyle(getAnimaStyle());
        }
        setOnDismissListener(this);
    }

    @Override
    public void onDismiss() {
        onDismissListener();
    }

    /**
     * 显示时X轴偏移值
     *
     * @return DP2PX
     */
    protected int showXoff() {
        return 0;
    }

    /**
     * 显示时Y轴偏移值
     *
     * @return DP2PX
     */
    protected int showYoff() {
        return 0;
    }

    /**
     * 显示popupWindow宽度
     *
     * @return DP2PX
     */
    protected int getWidthSize() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 显示popupWindow高度
     *
     * @return DP2PX
     */
    protected int getHeightSize() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 是否点击外部关闭PopupWindow
     *
     * @return
     */
    protected boolean getOutsideTouchable() {
        return true;
    }

    /**
     * 是否获取焦点
     *
     * @return
     */
    protected boolean getFocusable() {
        return true;
    }

    /**
     * 获取背景图片
     *
     * @return
     */
    protected ColorDrawable getBackgroundDrawable() {
        return new ColorDrawable(android.graphics.Color.TRANSPARENT);
    }

    /**
     * 获取动画
     *
     * @return
     */
    protected int getAnimaStyle() {
        return 0;
    }

    /**
     * 关闭窗口监听回调
     */
    protected void onDismissListener() {
        modifyScreenBrightness(1.0f);
    }

    /**
     * 修改屏幕亮度
     *
     * @param bgAlpha 0.1~1.0
     */
    public void modifyScreenBrightness(float bgAlpha) {
        Window window = ((Activity) mContext).getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = bgAlpha;
        window.setAttributes(lp);
    }

    /**
     * 获取布局之前获取数据
     */
    protected void initData() {
    }

    /**
     * 居中显示在上边
     *
     * @param parent
     */
    public void showAsUpCneter(View parent) {
        if (this.isShowing()) {
            this.dismiss();
            return;
        }
        this.showAsDropDown(parent, -(getPopWidth() / 2 - parent.getMeasuredWidth() / 2) + showXoff(), -(getPopHeight() + parent.getMeasuredHeight() + showYoff()));
    }

    /**
     * 居中显示在下边
     *
     * @param parent
     */
    public void showAsDownCneter(View parent) {
        if (this.isShowing()) {
            this.dismiss();
            return;
        }
        this.showAsDropDown(parent,- (getPopWidth() / 2 - parent.getMeasuredWidth() / 2) + showXoff(), showYoff());
    }

    /**
     * 居中显示在左边
     *
     * @param parent
     */
    public void showAsLeftCneter(View parent) {
        if (this.isShowing()) {
            this.dismiss();
            return;
        }
        this.showAsDropDown(parent,- getPopWidth() + showXoff(), -(parent.getMeasuredHeight() / 2 - getPopHeight() / 2) - getPopHeight() + showYoff());
    }

    /**
     * 居中显示在右边
     *
     * @param parent
     */
    public void showAsRightCneter(View parent) {
        if (this.isShowing()) {
            this.dismiss();
            return;
        }
        this.showAsDropDown(parent,parent.getMeasuredWidth() - showXoff(), -(parent.getMeasuredHeight() / 2 - getPopHeight() / 2) - getPopHeight() + showYoff());
    }

    /**
     * 显示在屏幕顶部
     */
    public void showAsScreenTop(View parent){
        this.showAtLocation(parent, Gravity.TOP, showXoff(), showYoff());
    }

    /**
     * 显示在屏幕中间
     */
    public void showAsScreenCenter(View parent){
        this.showAtLocation(parent, Gravity.CENTER, showXoff(), showYoff());
    }

    /**
     * 显示在屏幕底部
     */
    public void showAsScreenBottom(View parent){
        this.showAtLocation(parent, Gravity.BOTTOM, showXoff(), showYoff());
    }

    /**
     * 获取窗口的宽度
     *
     * @return
     */
    private int getPopWidth() {
        if(getWidthSize() >= 0){
            return getWidthSize();
        }
        return this.getContentView().getMeasuredWidth();
    }

    /**
     * 获取窗口的高度
     *
     * @return
     */
    private int getPopHeight() {
        if (getHeightSize() >= 0) {
            return getHeightSize();
        }
        return this.getContentView().getMeasuredHeight();
    }

    /**
     * 获取显示布局
     *
     * @param popupWindow
     * @return
     */
    protected abstract View getContentView(PopupWindow popupWindow);

}