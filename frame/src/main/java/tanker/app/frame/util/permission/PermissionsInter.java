package tanker.app.frame.util.permission;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/07/18
 * @describe : 权限申请结果回调
 */
public interface PermissionsInter {

    /**
     * 成功回调
     */
    void success();

    /**
     * 失败回调
     */
    void failure();
}
