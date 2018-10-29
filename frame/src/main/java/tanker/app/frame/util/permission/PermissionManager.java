package tanker.app.frame.util.permission;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * @author : Tanker
 * @date : 2018/07/18
 * @describe : 权限管理类
 */
public class PermissionManager {

    private Activity activity;

    private PermissionManager() {
    }

    public PermissionManager(Activity activity) {
        this.activity = activity;
    }

    /**
     * 申请手机权限
     *
     * @param permissionsInter
     * @param permissions
     */
    public void requestPermission(final PermissionsInter permissionsInter, String... permissions) {
        new RxPermissions(activity).requestEach(permissions).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                    // 用户已经同意该权限
                    permissionsInter.success();
                } else if (permission.shouldShowRequestPermissionRationale) {
                    // 用户拒绝了该权限，没有选中『不再询问』,那么下次再次启动时，还会提示请求权限的对话框
                    permissionsInter.failure();
                } else {
                    // 用户拒绝了该权限，并且选中『不再询问』
                    permissionsInter.failure();
                }
            }
        });
    }

}