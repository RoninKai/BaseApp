package tanker.app.frame.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * author : zhoukai
 * time   : 2018/06/28
 * desc   : 对第三方框架进行一次使用包装
 */
public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, ViewHolder> {


    /**
     * 不需要 Context , 需手动添加布局id
     *
     * @param data
     */
    public BaseAdapter(@Nullable List<T> data) {
        this(0, data);
    }

    /**
     * 不需要 Context , 需手动添加布局id
     *
     * @param context
     * @param data
     */
    public BaseAdapter(@Nullable Context context, @Nullable List<T> data) {
        this(0, data);
    }

    /**
     * 不需要 Context
     *
     * @param layoutResId
     * @param data
     */
    public BaseAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        this(null,layoutResId, data);
    }

    /**
     * 需要传入Context
     *
     * @param context
     * @param layoutResId
     * @param data
     */
    public BaseAdapter(Context context, int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        setLoadMoreView(new CustomLoadMoreView());
    }

    @Override
    protected void convert(ViewHolder helper, T item) {
        converts(helper, item);
    }

    public abstract void converts(ViewHolder holder, T t);

}