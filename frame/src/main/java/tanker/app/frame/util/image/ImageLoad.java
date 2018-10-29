package tanker.app.frame.util.image;

import android.content.Context;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/07/24
 * @describe : 图片加载工具类
 */
public class ImageLoad {

    /**
     * 加载图片
     * 将图片设置的操作交由Activity处理(调用into(ImageView))
     *
     * @param context
     * @param url     网络图片
     * @return
     */
    public static DrawableRequestBuilder load(Context context, String url) {
        return Glide.with(context)
                //加载图片地址
                .load(url)
                //占位图片
//                .placeholder(R.mipmap.app_icon)
                //错误图片
//                .error(R.mipmap.withdraw_fail)
                //设置动画,默认300
//                .crossFade(800)
                //关闭动画
//                .dontAnimate()
                //内存缓存处理图
                .skipMemoryCache(false)
                //磁盘缓存原图
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                //图片压缩
//                .override(100, 100)
                ;
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param filePath
     * @return
     */
    public static DrawableRequestBuilder loadFile(Context context, String filePath) {
        return Glide.with(context).load(new File(filePath)).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.SOURCE);
    }

    /**
     * 清理所有缓存
     * 需要在UI线程操作
     *
     * @param context
     */
    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 清除所有磁盘缓存
     *
     * @param context
     */
    public static void clearDiskCache(Context context) {
    }
}