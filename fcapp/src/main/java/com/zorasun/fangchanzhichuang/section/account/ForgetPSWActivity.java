package com.zorasun.fangchanzhichuang.section.account;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.MD5;
import com.zorasun.fangchanzhichuang.general.util.RegexValidateUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.timer.TimerTextView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ForgetPSWActivity extends BaseActivity implements OnClickListener {

	private TextView etPhone;
	private TextView etSMS;
	private TextView etpsw;
	private AccountApi mAccountApi;
	private TimerTextView btnGetSMS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_psw);
		mAccountApi = new AccountApi();
		initView();
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
		if (TextUtils.isEmpty(sms)) {
			ToastUtil.toastShow(this, "请输入验证码");
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
		AccountApi.getInstance().sureFindPwd(this, phone, sms, psw, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				finish();
				ToastUtil.toastShow(ForgetPSWActivity.this, msg);
			}

			@Override
			public void onNetworkError() {

				ToastUtil.toastShow(ForgetPSWActivity.this, R.string.net_error);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(ForgetPSWActivity.this, msg);

			}
		});

	}
	// else {
	//
	// AccountApi.getInstance().changePwd(this, sms, psw, new
	// RequestCallBack() {
	//
	// @Override
	// public void onSuccess(int code, String msg, Object object) {
	// AccountConfig.saveLoginData(false, null, null, 0, null, null, -1,
	// null, null, 0, null, 0, null);
	// startActivity(new Intent(ForgetPSWActivity.this,
	// LoginActivity.class));
	// finish();
	// ToastUtil.toastShow(ForgetPSWActivity.this, msg);
	// }
	//
	// @Override
	// public void onNetworkError() {
	//
	// }
	//
	// @Override
	// public void onFailure(int code, String msg, Object object) {
	// ToastUtil.toastShow(ForgetPSWActivity.this, msg);
	// }
	// });
	// }

	private void initView() {
		int i = getIntent().getIntExtra("xiugaipsw", 0);
		TextView tvName = (TextView) findViewById(R.id.title_name);
		if (i == SystemConstant.XIUGAI_PSW) {
			tvName.setText("修改密码");
		} else {
			tvName.setText("忘记密码");
		}
		btnGetSMS = (TimerTextView) findViewById(R.id.btn_get_verification);
		btnGetSMS.setOnClickListener(this);
		findViewById(R.id.title_left).setOnClickListener(this);
		findViewById(R.id.btn_submit).setOnClickListener(this);
		etPhone = (TextView) findViewById(R.id.et_phone);
		etSMS = (TextView) findViewById(R.id.et_verification);
		etpsw = (TextView) findViewById(R.id.et_password);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_submit:
			commitInfo();
			break;
		case R.id.title_left:
			super.onBackPressed();
			finish();
			break;
		case R.id.btn_get_verification:
			String phoneString = etPhone.getText().toString().trim();
			boolean checkMobileNumber = RegexValidateUtil.checkMobileNumber(phoneString);
			String type = "";
			if (checkMobileNumber) {
				// 电话号码输入正确
				type = "forget";
				new GetVerificationUtil(this, etPhone, btnGetSMS, mAccountApi).getVerification(type);
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
