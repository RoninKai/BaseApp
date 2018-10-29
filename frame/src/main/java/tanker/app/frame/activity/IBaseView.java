package tanker.app.frame.activity;

import android.app.ProgressDialog;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import tanker.app.frame.listener.CustomListener;

/**
 * @author : Tanker
 * @date : 2018/08/21
 * @describe : Base层接口定义
 */
public interface IBaseView {

    /**
     * toast提示框
     *
     * @param resId viewId 控件id
     */
    void toast(@StringRes int resId);

    /**
     * toast提示框
     *
     * @param text
     */
    void toast(String text);

    /**
     * 界面等待框
     *
     * @param resId viewId 控件id
     */
    ProgressDialog showWaiting(@StringRes int resId);

    /**
     * 显示对话框
     *
     * @param titleId
     * @param messageId
     * @param confimationOnListener
     * @param negativeId
     * @param positiveId
     */
    AlertDialog showConfirmation(@StringRes int titleId, @StringRes int messageId, CustomListener.ConfimationOnListener confimationOnListener, @StringRes int negativeId, @StringRes int positiveId);

    /**
     * 信息提示框
     *
     * @param messageId
     * @param confimationInfoOnListener
     * @param positiveId
     */
    AlertDialog showConfirmation(@StringRes int messageId, CustomListener.ConfimationInfoOnListener confimationInfoOnListener, @StringRes int positiveId);

    /**
     * 信息提示框（包含title）
     *
     * @param titleId
     * @param messageId
     * @param confimationInfoOnListener
     * @param positiveId
     */
    AlertDialog showConfirmation(@StringRes int titleId, @StringRes int messageId, CustomListener.ConfimationInfoOnListener confimationInfoOnListener, @StringRes int positiveId);

    /**
     * 关闭所有的窗口
     */
    void dismiss();

}