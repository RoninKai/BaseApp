package tanker.app.frame.listener;

/**
 * @author : Tanker
 * @date : 2018/08/22
 * @describe : 自定义的事件
 */
public class CustomListener {

    /**
     * 确认框点击处理回调
     */
    public static abstract class ConfimationOnListener extends ConfimationInfoOnListener{
        public void negative() {
        }
    }

    /**
     * 确认框点击处理回调
     */
    public static abstract class ConfimationInfoOnListener {
        public void onNext() {
        }

        public void onDismiss() {
        }

        public abstract void positive();
    }

}