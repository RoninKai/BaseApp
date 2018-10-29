package tanker.app.frame.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * author : zhoukai
 * time   : 2018/02/27
 * desc   : RecyclerView 的 ViewHolder
 */
public class ViewHolder extends BaseViewHolder {

    public ViewHolder(View view) {
        super(view);
    }

    /**
     * 加载网络图片
     *
     * @param context
     * @param id
     * @param url
     * @return
     */
    public ViewHolder loadImage(Context context, @IdRes int id, String url) {
        Glide.with(context).load(url).into((ImageView) getView(id));
        return this;
    }

    /**
     * 设置控件是否可见
     *
     * @param viewId
     * @param visibility
     * @return
     */
    public ViewHolder setVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    /**
     * 设置控件是否可见
     *
     * @param viewId
     * @param isShow
     * @return
     */
    public ViewHolder setVisibility(int viewId, boolean isShow) {
        getView(viewId).setVisibility(isShow ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置TextView显示内容，加非空处理
     *
     * @param viewId
     * @param value
     * @return
     */
    public ViewHolder setText(@IdRes int viewId, String value) {
        if (value == null) {
            return this;
        }
        ((TextView) getView(viewId)).setText(value);
        return this;
    }

}