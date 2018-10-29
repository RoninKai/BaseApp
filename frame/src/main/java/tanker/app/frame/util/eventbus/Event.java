package tanker.app.frame.util.eventbus;

/**
 * @author : zhoukai
 * e-mail : zhoukai@zto.cn
 * time   : 2018/07/11
 * desc   :
 */
public class Event<T> {

    private int code;
    private T data;

    public Event(int code) {
        this.code = code;
    }

    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public static final class EventCode {
        public static final int SEITCH_LOCALE = 1001;
    }

}