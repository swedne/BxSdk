package com.zorasun.fangchanzhichuang.section;

import com.zorasun.fangchanzhichuang.FcgzApplaciton;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragmentActivityNoSwipe;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.dialog.DownloadingSoftwareDialog;
import com.zorasun.fangchanzhichuang.general.dialog.RemindDialog;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AppHelper;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.account.LoginActivity;
import com.zorasun.fangchanzhichuang.section.index.IndexFragment;
import com.zorasun.fangchanzhichuang.section.message.MessageFragment2;
import com.zorasun.fangchanzhichuang.section.my.MyApi;
import com.zorasun.fangchanzhichuang.section.my.MyApi.VersionUpdateCallback;
import com.zorasun.fangchanzhichuang.section.my.MyFragment;
import com.zorasun.fangchanzhichuang.section.my.entiy.InfoVersionEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.sendDemandFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 主页
 *
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015-1-20 下午2:55:02
 */
public class HomeActivity extends BaseFragmentActivityNoSwipe implements OnClickListener {
    /**
     * 当前页
     */
    int curindex = 0;

    private FragmentTabHost mTabHost;
    private long exitTime = 0;// 退出时间

    int showLoading = 0; // 是否显示loading
    boolean type = true; // 是否是统一类型的请求
    boolean repeat = true; // 是否可重复提交
    boolean args = true;// 判断是否重复提交
    private MessageFragment2 messageFragment;

    /**
     * 请求权限的请求码
     */
    private static final int ACTION_REQUEST_PERMISSIONS = 0x001;

    /**
     * 所需的所有权限信息
     */
    String[] NEEDED_PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS,
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};

    /**
     * 初始化视图页面
     */
    private void bindViews() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(HomeActivity.this, getSupportFragmentManager(), R.id.realtabcontent);

        addTab("首页", R.drawable.tab_selector_index, IndexFragment.class);
        addTab("发需求", R.drawable.tab_selector_fabu, sendDemandFragment.class);
        addTab("消息", R.drawable.tab_selector_message, MessageFragment2.class);
        addTab("我", R.drawable.tab_selector_my, MyFragment.class);


        /**
         * 在选择图片的时候，在android 7.0及以上通过FileProvider获取Uri，不需要文件权限
         */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            List<String> permissionList = new ArrayList<>(Arrays.asList(NEEDED_PERMISSIONS));
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            NEEDED_PERMISSIONS = permissionList.toArray(new String[0]);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermissions(NEEDED_PERMISSIONS)) {
                ActivityCompat.requestPermissions(this, NEEDED_PERMISSIONS, ACTION_REQUEST_PERMISSIONS);
            }
        }
        mTabHost.getTabWidget().getChildTabViewAt(2).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!AccountConfig.isLogin()) {
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivityForResult(intent, SystemConstant.REQUEST_MESSAGE);
                } else {
                    mTabHost.setCurrentTab(2);
                    mTabHost.getTabWidget().requestFocus(View.FOCUS_FORWARD);

                }
            }
        });

    }

    /**
     * 权限检测
     *
     * @param neededPermissions 所需的所有权限
     * @return 是否检测通过
     */
    private boolean checkPermissions(String[] neededPermissions) {
        if (neededPermissions == null || neededPermissions.length == 0) {
            return true;
        }
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted &= ContextCompat.checkSelfPermission(this, neededPermission) == PackageManager.PERMISSION_GRANTED;
        }
        return allGranted;
    }

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_home);
        bindViews();
        afterView();
        toUpdate();
    }

    private void getFragment() {
        final Handler hand = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                messageFragment = (MessageFragment2) getSupportFragmentManager().findFragmentByTag("消息");
                Log.e("aaaa", "=====获得的view1是:=====" + messageFragment);
            }
        };
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);// 休眠1.5s后就能获取到了
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Message msg = hand.obtainMessage();
                hand.sendMessage(msg);
            }
        }.start();

    }

    public void afterView() {
    }

    @SuppressLint("InflateParams")
    private void addTab(String title, int resDrawable, @SuppressWarnings("rawtypes") Class class1) {
        LayoutInflater inflater = getLayoutInflater();
        View tabIndicator1 = inflater.inflate(R.layout.tab_indicator, null);
        TextView tabLabel = (TextView) tabIndicator1.findViewById(R.id.tab_label);
        tabLabel.setText(title);
        Drawable topDrawable = getResources().getDrawable(resDrawable);
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        tabLabel.setCompoundDrawables(null, topDrawable, null, null);
        // TextViewUtils.setTextDrawable(this, resDrawable, tabLabel);
        mTabHost.addTab(mTabHost.newTabSpec(title).setIndicator(tabIndicator1), class1, null);
        // messageFragment = (MessageFragment2)
        // getSupportFragmentManager().findFragmentByTag("消息");
        // getFragment();

    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        // TODO Auto-generated method stub
        super.onActivityResult(arg0, arg1, arg2);
        if (arg0 == SystemConstant.REQUEST_MESSAGE && arg1 == RESULT_OK) {
            mTabHost.setCurrentTab(2);
        }
    }

    /**
     * 按钮设置 设置
     *
     * @param tv
     * @param colorId
     * @param ivId
     */
    public void setNav(TextView tv, int colorId, int ivId) {
        tv.setTextColor(getResources().getColor(colorId));
        Drawable top = getResources().getDrawable(ivId);
        top.setBounds(0, 0, top.getIntrinsicWidth(), top.getIntrinsicHeight());
        tv.setCompoundDrawables(null, top, null, null);
    }

    /***
     * 提醒退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出操作
     */
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.toastShow(this, R.string.exit);
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    /**
     * 版本更新
     */
    void toUpdate() {
        MyApi.getInstance().checkVersionUpdate(HomeActivity.this, AppHelper.getVersionCode(HomeActivity.this),
                showLoading, repeat, new VersionUpdateCallback() {
                    @Override
                    public void onUpdate(int code, final InfoVersionEntity infoVersionEntity, String msg) {
                        if (infoVersionEntity != null && infoVersionEntity.isMustToUpdate == 1) {
                            // 相关操作
                            final RemindDialog dialog = new RemindDialog(HomeActivity.this,
                                    getResources().getString(R.string.version_is_update));
                            dialog.dialog_remind_sure.setOnClickListener(new OnClickListener() {// 确定
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    DownloadingSoftwareDialog mVersionUpdateDialog = new DownloadingSoftwareDialog(
                                            HomeActivity.this);
                                    mVersionUpdateDialog.setDownloadUrl(ApiConfig.updateUrl + infoVersionEntity.url, 1);
                                    // mVersionUpdateDialog.setDownloadUrl(ApiConfig.updateUrl
                                    // + infoVersionEntity.url,
                                    // 1);
                                    mVersionUpdateDialog.setCancelable(false);
                                    mVersionUpdateDialog.show();
                                }

                            });
                            dialog.dialog_remind_cancel.setOnClickListener(new OnClickListener() {// 取消
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    finish();
                                    FcgzApplaciton.getInstance().exit();
                                }
                            });
                            dialog.setOutside();
                            dialog.setback();
                            dialog.show();
                        } else {
                            // 相关操作
                            final RemindDialog dialog = new RemindDialog(HomeActivity.this,
                                    getResources().getString(R.string.version_is_update));
                            dialog.dialog_remind_sure.setOnClickListener(new OnClickListener() {// 确定
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    DownloadingSoftwareDialog mVersionUpdateDialog = new DownloadingSoftwareDialog(
                                            HomeActivity.this);
                                    mVersionUpdateDialog.setDownloadUrl(ApiConfig.updateUrl + infoVersionEntity.url, 1);
                                    mVersionUpdateDialog.setCancelable(false);
                                    mVersionUpdateDialog.show();
                                }

                            });
                            dialog.dialog_remind_cancel.setOnClickListener(new OnClickListener() {// 取消
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                    }

                    @Override
                    public void onNetworkError() {
                    }

                    @Override
                    public void onInUpdate(int code, String msg) {
                    }
                });
    }

}
