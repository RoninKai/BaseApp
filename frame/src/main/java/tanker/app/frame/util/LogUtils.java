package tanker.app.frame.util;

import android.util.Log;

/**
 * @author : Tanker
 * time   : 2018/04/08
 * desc   : Log日志输出类  控制正式包不输出日志
 */
public class LogUtils {

    private LogUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 默认Dubug模式打印日志
     *
     * @return
     */
    private static boolean isDebuggable() {
        return BuildTypeUtils.isDebug();
    }

    public static void i(String msg) {
        if (!isDebuggable()) {
            return;
        }
        StackTraceElement[] sElements = new Throwable().getStackTrace();
        Log.i(getClassName(sElements), createLog(sElements,msg));
    }

    public static void i(String tag, String msg) {
        if (!isDebuggable()) {
            return;
        }
        Log.i(tag, createLog(new Throwable().getStackTrace(), msg));
    }

    public static void d(String msg) {
        if (!isDebuggable()) {
            return;
        }
        StackTraceElement[] sElements = new Throwable().getStackTrace();
        Log.d(getClassName(sElements), createLog(sElements,msg));
    }

    public static void d(String tag, String msg) {
        if (!isDebuggable()) {
            return;
        }
        Log.d(tag, createLog(new Throwable().getStackTrace(), msg));
    }

    public static void e(String msg) {
        if (!isDebuggable()) {
            return;
        }
        StackTraceElement[] sElements = new Throwable().getStackTrace();
        Log.e(getClassName(sElements), createLog(sElements,msg));
    }

    public static void e(String tag, String msg) {
        if (!isDebuggable()) {
            return;
        }
        Log.e(tag, createLog(new Throwable().getStackTrace(), msg));
    }

    public static void v(String msg) {
        if (!isDebuggable()) {
            return;
        }
        StackTraceElement[] sElements = new Throwable().getStackTrace();
        Log.v(getClassName(sElements), createLog(sElements,msg));
    }

    public static void v(String tag, String msg) {
        if (!isDebuggable()) {
            return;
        }
        Log.v(tag, createLog(new Throwable().getStackTrace(), msg));
    }

    /**
     * 自定义的log
     *
     * @param log
     * @return
     */
    private static String createLog(StackTraceElement[] sElements, String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(sElements[1].getMethodName())
                .append("(" + sElements[1].getFileName())
                .append(":" + sElements[1].getLineNumber() + ")")
                .append("\n" + "==============================")
                .append("\n" + log)
                .append("\n" + "==============================");
        return buffer.toString();
    }

    /**
     * 获取类名
     * @param sElements
     * @return
     */
    private static String getClassName(StackTraceElement[] sElements){
        return sElements[1].getFileName().replace(".java","");
    }
}