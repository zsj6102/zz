package com.property.colpencil.colpencilandroidlibrary.ControlerBase.MVP;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;

import com.property.colpencil.colpencilandroidlibrary.Function.Permission.OnPermissionCallback;
import com.property.colpencil.colpencilandroidlibrary.Function.Permission.PermissionManager;
import com.property.colpencil.colpencilandroidlibrary.Function.Tools.AndroidVersionUtil;


/**
 * @Description:权限回调帮助类
 * @author 汪 亮
 * @Email  DramaScript@outlook.com
 * @date 16/6/23
 */
class PermissionHelp {
    public static final Object LOCK = new Object();
    public volatile static PermissionHelp INSTANCE = null;
    public PermissionManager mPm;

    public static PermissionHelp getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                INSTANCE = new PermissionHelp();
            }
        }
        return INSTANCE;
    }

    private PermissionHelp() {
        mPm = PermissionManager.getInstance();
    }

    /**
     * 处理权限申请回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void handlePermissionCallback(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int state : grantResults) {
            if (state == PackageManager.PERMISSION_GRANTED) {
                mPm.onSuccess(permissions);
            } else {
                mPm.onFail(permissions);
            }
        }
    }

    /**
     * 处理特殊权限申请，如悬浮框，系统设置修改等特殊权限
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void handleSpecialPermissionCallback(Context context, int requestCode, int resultCode, Intent data) {
        if (AndroidVersionUtil.hasM()) {
            if (requestCode == OnPermissionCallback.PERMISSION_ALERT_WINDOW) {
                if (Settings.canDrawOverlays(context)) {       //在这判断是否请求权限成功
                    mPm.onSuccess(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                } else {
                    mPm.onFail(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                }
            } else if (requestCode == OnPermissionCallback.PERMISSION_WRITE_SETTING) {
                if (Settings.System.canWrite(context)) {
                    mPm.onSuccess(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                } else {
                    mPm.onFail(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                }
            }
        }
    }

}
