package com.zorasun.fangchanzhichuang.section.account;

import java.util.Map;

import org.json.JSONObject;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.MD5;
import com.zorasun.fangchanzhichuang.general.util.RegexValidateUtil;
import com.zorasun.fangchanzhichuang.general.util.SharedPreferencesUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.section.HomeActivity;
import com.zorasun.fangchanzhichuang.section.account.entity.LoginEntity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 登录界面
 *
 * @author 林文熹
 * @version v1.0
 * @e-mail 635991604@qq.com
 * @copyright 2010-2015
 * @create-time 2016年3月16日11:11:05
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

    // private UMShareAPI mShareAPI;
    //
    // private UMAuthListener umAuthListener = new UMAuthListener() {
    // @Override
    // public void onComplete(SHARE_MEDIA platform, int action, Map<String,
    // String> data) {
    // Toast.makeText(getApplicationContext(), "Authorize succeed",
    // Toast.LENGTH_SHORT).show();
    // }
    //
    // @Override
    // public void onError(SHARE_MEDIA platform, int action, Throwable t) {
    // Toast.makeText(getApplicationContext(), "Authorize fail",
    // Toast.LENGTH_SHORT).show();
    // }
    //
    // @Override
    // public void onCancel(SHARE_MEDIA platform, int action) {
    // Toast.makeText(getApplicationContext(), "Authorize cancel",
    // Toast.LENGTH_SHORT).show();
    // }
    //
    // };

    private EditText etPhone;
    private EditText etPSW;
    private String phone;
    private String psw;
    private static final String TAG = "LoginActivity.class";
    private int mThirdLoginType;
    private String thirdAvatar;
    private String thirdNickName;
    private String openId;

    private AccountApi mAccountApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // mShareAPI = UMShareAPI.get(this);
        // SHARE_MEDIA platform = SHARE_MEDIA.SINA;
        // mShareAPI.doOauthVerify(this, platform, umAuthListener);

        initView();
        initData();
    }

    private void initData() {
        // 友盟社会化接口(授权)
        mAccountApi = new AccountApi();
    }

    private void initView() {
        etPhone = (EditText) findViewById(R.id.et_loginphone);
        etPSW = (EditText) findViewById(R.id.et_loginpwd);
        findViewById(R.id.tv_login).setOnClickListener(this);
        TextView title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("登录");
        findViewById(R.id.title_left).setOnClickListener(this);
        TextView title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        title_right_tv.setVisibility(View.VISIBLE);
        title_right_tv.setText("注册");
        title_right_tv.setOnClickListener(this);
        findViewById(R.id.tv_forgetpwd).setOnClickListener(this);

        findViewById(R.id.tv_weixin).setVisibility(View.GONE);
        findViewById(R.id.tv_QQ).setVisibility(View.GONE);
    }

    public void toHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            // 登录
            case R.id.tv_login:
                logIn();
                break;
            // 注册
            case R.id.title_right_tv:
                Intent registerintent = new Intent(this, RegisterActivity.class);
                startActivity(registerintent);
                break;
            // 忘记密码
            case R.id.tv_forgetpwd:
                Intent forgetPSWintent = new Intent(this, ForgetPSWActivity.class);
                forgetPSWintent.putExtra("fogetPSW", SystemConstant.FORGETPSW_INTENT);
                startActivity(forgetPSWintent);
                break;
            case R.id.tv_weixin:
                break;
            case R.id.tv_QQ:
                break;

            default:
                break;
        }
    }

    private void logIn() {
        phone = etPhone.getText().toString();
        psw = etPSW.getText().toString();

        if (phone.equals("")) {
            ToastUtil.toastShow(this, "手机号不能为空");
            return;
        }
        if (phone.length() < 11) {
            ToastUtil.toastShow(this, "请输入11位手机号码");
            return;
        }
        if (!RegexValidateUtil.checkMobileNumber(phone)) {
            ToastUtil.toastShow(this, "请输入正确的手机号码");
            return;
        }
        if (TextUtils.isEmpty(psw)) {
            ToastUtil.toastShow(this, "请输入您的密码");
            return;
        }
        if (!CommonUtils.isPassWord(psw)) {
            ToastUtil.toastShow(this, R.string.psw_rule);
            return;
        }
        psw = MD5.getMD5ofStr(psw).toLowerCase();

        AccountApi.getInstance().login(this, phone, psw, new RequestCallBack() {

            @Override
            public void onSuccess(int code, String msg, Object object) {
                LoginEntity info = (LoginEntity) object;
                if (info.getContent() != null) {
                    AccountConfig.saveLoginData(true, info.getContent().getAccountId(), null, 0,
                            info.getContent().getAddress(), info.getContent().getNickName(), -1,
                            info.getContent().getMobile(), null, 0, null, 0, null);

                    // AppLog.redLog("233", alias);
                    setResult(RESULT_OK);
                    LoginActivity.this.finish();
                }
                ToastUtil.toastShow(LoginActivity.this, msg);
            }

            @Override
            public void onNetworkError() {
                ToastUtil.toastShow(LoginActivity.this, R.string.net_error);
            }

            @Override
            public void onFailure(int code, String msg, Object object) {
                ToastUtil.toastShow(LoginActivity.this, msg);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SystemConstant.REQUEST_CODE_BIND_THIRD_ACCOUNT
                && resultCode == SystemConstant.RESULT_CODE_BIND_THIRD_ACCOUNT) {
            setResult(SystemConstant.RESULT_CODE_LOGIN);
            AppLog.blackLog(TAG, "onActivityResult");
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
