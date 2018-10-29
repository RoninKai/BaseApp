package tanker.base.app;

import tanker.app.frame.activity.BaseActivity;
import tanker.app.frame.util.LogUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected String getTitleText() {
        return null;
    }

    @Override
    protected void initView() {
        LogUtils.i("hahahhah ");
    }

}