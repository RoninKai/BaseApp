package tanker.app.frame.adapter;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

import tanker.app.frame.R;

/**
 * 功能说明： RecyclerView 分页加载状态的自定义View
 */
public class CustomLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.quick_view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

}