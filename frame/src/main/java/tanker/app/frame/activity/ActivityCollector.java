package tanker.app.frame.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/08/27
 * @describe : Activity管理类
 */
public class ActivityCollector {

    public static List<Activity> activityList = new ArrayList<>();

    /**
     * 添加Activity
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 退出全部Activity
     */
    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activityList.clear();
    }

}