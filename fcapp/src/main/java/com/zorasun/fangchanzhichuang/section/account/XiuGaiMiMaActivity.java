package com.zorasun.fangchanzhichuang.section.account;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.RegexValidateUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.timer.TimerTextView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class XiuGaiMiMaActivity extends BaseActivity implements OnClickListener {

	private TimerTextView btnGetSMS;
	private TextView etPhone;
	private TextView etSMS;
	private TextView etpsw;
	private AccountApi mAccountApi;
	public final static int MODIFYPSW = 200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiu_gai_mi_ma);
		mAccountApi = new AccountApi();
		initView();

	}

	private void initView() {
		TextView tvName = (TextView) findViewById(R.id.title_name);
		tvName.setText("修改密码");
		findViewById(R.id.title_right_tv).setVisibility(View.VISIBLE);
		findViewById(R.id.title_right_tv).setOnClickListener(this);

		btnGetSMS = (TimerTextView) findViewById(R.id.btn_get_verification);
		btnGetSMS.setOnClickListener(this);
		etPhone = (TextView) findViewById(R.id.et_phone);
		etSMS = (TextView) findViewById(R.id.et_verification);
		etpsw = (TextView) findViewById(R.id.et_password);
	}

	private void commitInfo() {
		String phone = etPhone.getText().toString().trim();
		String psw = etpsw.getText().toString().trim();
		String sms = etSMS.getText().toString().trim();

		if (TextUtils.isEmpty(phone)) {
			ToastUtil.toastShow(this, "手机号不能为空");
			return;
		}
		if (!RegexValidateUtil.checkMobileNumber(phone)) {
			ToastUtil.toastShow(this, "请输入正确的手机号码");
			return;
		}
		if (!phone.equals(AccountConfig.getAccountPhone())) {
			ToastUtil.toastShow(this, "号码不匹配");
			return;
		}
		if (TextUtils.isEmpty(sms)) {
			ToastUtil.toastShow(this, "请输入验证码");
			return;
		}
		if (TextUtils.isEmpty(psw)) {
			ToastUtil.toastShow(this, "请输入您的新密码");
			return;
		}
		if (!CommonUtils.isPassWord(psw)) {
			ToastUtil.toastShow(this, R.string.psw_rule);
			return;
		}
		AccountApi.getInstance().changePwd(this, sms, psw, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				AccountConfig.saveLoginData(false, null, null, 0, null, null, -1, null, null, 0, null, 0, null);
				// startActivity(new Intent(XiuGaiMiMaActivity.this,
				// LoginActivity.class));
				startActivityForResult(new Intent(XiuGaiMiMaActivity.this, LoginActivity.class),
						XiuGaiMiMaActivity.MODIFYPSW);
				// finish();
				ToastUtil.toastShow(XiuGaiMiMaActivity.this, "修改成功");
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(XiuGaiMiMaActivity.this, msg);
			}
		});
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent data) {
		super.onActivityResult(arg0, arg1, data);
		if (arg0 == XiuGaiMiMaActivity.MODIFYPSW) {
			setResult(1);
			finish();
		}
	}

	@Override
	public void onClickLeft(View view) {
		super.onClickLeft(view);
		setResult(0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_right_tv:
			commitInfo();
			break;
		case R.id.btn_get_verification:
			String phoneString = etPhone.getText().toString().trim();
			boolean checkMobileNumber = RegexValidateUtil.checkMobileNumber(phoneString);
			String type = "";
			if (checkMobileNumber) {
				// 电话号码输入正确
				if (phoneString.equals(AccountConfig.getAccountPhone())) {
					type = "modifypass";
					new GetVerificationUtil(this, etPhone, btnGetSMS, mAccountApi).getVerification(type);
				} else {
					ToastUtil.toastShow(this, "请输入当前号码");
				}
			} else {
				// 电话号码输入不正确
				ToastUtil.toastShow(this, "请输入正确的电话号码");
			}
			break;

		default:
			break;
		}
	}
}
