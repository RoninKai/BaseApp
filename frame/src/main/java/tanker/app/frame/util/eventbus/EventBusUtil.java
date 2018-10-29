package tanker.app.frame.util.eventbus;


import org.greenrobot.eventbus.EventBus;

import tanker.app.frame.util.BuildTypeUtils;


/**
 * @author : zhoukai
 * @e-mail : zhoukai@zto.cn
 * @time   : 2018/07/11
 * @desc   :
 */
public class EventBusUtil {

    public static void initEventBus() {
        //配置eventbus 只在debug模式下抛出异常 便于自测，同时不会导致release环境的crash
        EventBus.builder().throwSubscriberException(BuildTypeUtils.isDebug()).installDefaultEventBus();
    }

    /**
     * 注册
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        if (isRegistered(subscriber)) {
            return;
        }
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 反注册
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        if (!isRegistered(subscriber)) {
            return;
        }
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发送事件
     *
     * @param event
     */
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送黏性事件
     *
     * @param event
     */
    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }

    /**
     * 是否已注册
     *
     * @param subscriber
     * @return
     */
    private static boolean isRegistered(Object subscriber) {
        return EventBus.getDefault().isRegistered(subscriber);
    }

}