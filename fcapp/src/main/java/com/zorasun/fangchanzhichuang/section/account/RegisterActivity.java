package com.zorasun.fangchanzhichuang.section.account;

import java.lang.ref.WeakReference;

import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.marco.SystemConstant;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.ImageUploadUtils;
import com.zorasun.fangchanzhichuang.general.util.MD5;
import com.zorasun.fangchanzhichuang.general.util.SharedPreferencesUtil;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.timer.TimerTextView;
import com.zorasun.fangchanzhichuang.section.account.entity.MemberModel;
import com.zorasun.fangchanzhichuang.section.account.entity.MemberModel.Content;
import com.zorasun.fangchanzhichuang.section.account.entity.PictureEntity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends BaseActivity implements OnClickListener {

	public static final String TAG = "BindAccountActivity";
	private EditText etPhone;
	private EditText etCode;
	private EditText etPSW;
	private TimerTextView btnGetSMS;
	private AccountApi mAccountApi;
	private int flag;
	private String thirdAvatar;
	private String thirdNickName;
	private String mOpenId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mAccountApi = new AccountApi();

		// flag=1 注册并绑定手机号
		flag = getIntent().getIntExtra("flag", 0);
		thirdAvatar = getIntent().getStringExtra("thirdAvatar");
		thirdNickName = getIntent().getStringExtra("thirdNickName");
		mBindThreeType = getIntent().getIntExtra("mBindThreeType", -1);
		mOpenId = getIntent().getStringExtra("mOpenId");
		initview();
	}

	private void initview() {
		TextView title_name = (TextView) findViewById(R.id.title_name);
		Button btnSure = (Button) findViewById(R.id.btn_register);
		if (flag == 1) {
			title_name.setText("注册并绑定手机号");
		} else {
			title_name.setText("注册");
		}
		btnSure.setText("注册");
		etCode = (EditText) findViewById(R.id.et_verification);
		btnGetSMS = (TimerTextView) findViewById(R.id.btn_get_verification);
		btnGetSMS.setOnClickListener(this);
		etPSW = (EditText) findViewById(R.id.et_password);
		etPhone = (EditText) findViewById(R.id.et_phone);
		btnSure.setOnClickListener(this);

	}

	public void register() {
		String phoneString = etPhone.getText().toString().trim();
		String code = etCode.getText().toString().trim();
		String psw = etPSW.getText().toString().trim();
		if (TextUtils.isEmpty(phoneString)) {
			ToastUtil.toastShow(this, "手机号不能为空");
			return;
		}
		if (phoneString.length() < 11) {
			ToastUtil.toastShow(this, "请输入11位手机号码");
			return;
		}
		if (TextUtils.isEmpty(code)) {
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
		if (flag == 1) {
			// mAccountApi.requestBindThridParty(this, mOpenId, mBindThreeType,
			// phoneString, 0, code, thirdNickName, psw,
			// mLoginCallBack);
			RequestParams params = new RequestParams();
			params.put("uid", mOpenId);
			params.put("loginType", mBindThreeType);
			params.put("mobile", phoneString);
			params.put("isHasBindType", 0);
			params.put("code", code);
			params.put("nickName", thirdNickName);
			params.put("password", psw);
			params.put("headImageUrl", thirdAvatar);
			mAccountApi.requestBindThridParty1(this, params, mLoginCallBack);
		} else {
			AccountApi.getInstance().register(this, phoneString, code, psw, new RequestCallBack() {

				@Override
				public void onSuccess(int code, String msg, Object object) {
					finish();
					ToastUtil.toastShow(RegisterActivity.this, msg);
				}

				@Override
				public void onNetworkError() {
					ToastUtil.toastShow(RegisterActivity.this, R.string.net_error);

				}

				@Override
				public void onFailure(int code, String msg, Object object) {
					ToastUtil.toastShow(RegisterActivity.this, msg);

				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			register();
			break;
		case R.id.btn_get_verification:
			if (flag == 1) {
				new GetVerificationUtil(this, etPhone, btnGetSMS, mAccountApi).getThidVerification(0);
			} else {
				new GetVerificationUtil(this, etPhone, btnGetSMS, mAccountApi).getVerification("regist");
			}
		default:
			break;
		}
	}

	private RequestCallBack mLoginCallBack = new RequestCallBack() {

		@Override
		public void onSuccess(int code, String msg, Object object) {
			ToastUtil.toastShow(RegisterActivity.this, msg);
			MemberModel memberModel = (MemberModel) object;
			Content content = memberModel.getContent();
			SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_ID, content.getId());
			SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_PHONE, content.getMobile());
			SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_NAME, content.getNickName());
			SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_AVATARUL, content.getAddress());
			AccountConfig.setLoginState(true);
			// uploadAvatar();// 上传第三方账号头像
			// uploadNickName();// 把昵称设置为第三方昵称
			// 设置别名
			String alias = "xmfcgz_" + AccountConfig.getAccountId();
			if (!SharedPreferencesUtil.getString(SharedPreferencesUtil.SET_ALIAS_SUCCESS, "").equals(alias)) {
				// 极光推送设置tag
			}
			setResult(SystemConstant.RESULT_CODE_BIND_THIRD_ACCOUNT);
			finish();
		}

		@Override
		public void onNetworkError() {
			ToastUtil.toastShow(RegisterActivity.this, R.string.net_error);
		}

		@Override
		public void onFailure(int code, String msg, Object object) {
			ToastUtil.toastShow(RegisterActivity.this, msg);
		}
	};
	private int mBindThreeType;

	/**
	 * 更新服务器上和本地的昵称
	 */

	private void uploadNickName() {
		AccountApi.getInstance().changeNickName(this, thirdNickName, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				AppLog.blackLog(TAG, "昵称设置成功");
				AccountConfig.setAccountName(thirdNickName);
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 上传第三方登录头像
	 */
	protected void uploadAvatar() {

		final String fileName = System.currentTimeMillis() + AccountConfig.getAccountPhone() + ".jpg";
		final String url = ApiConfig.generateModifyAvatarUrl("member");
		if (StringUtils.isEmpty(url)) {
			return;
		}
		ImageLoader.getInstance().loadImage(thirdAvatar, new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String arg0, View arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap bitmap) {
				if (bitmap == null) {
					AppLog.blackLog(TAG, "avatar = null");
				}

				ImageUploadUtils.uploadImg(RegisterActivity.this, ApiConfig.UPLOADAVATAR, fileName, bitmap,
						new MyHandler(RegisterActivity.this));

			}

			@Override
			public void onLoadingCancelled(String arg0, View arg1) {

			}
		});

	}

	private class MyHandler extends Handler {
		public WeakReference<RegisterActivity> wrActivity;

		public MyHandler(RegisterActivity activity) {
			wrActivity = new WeakReference<RegisterActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what != 1) {
				if (wrActivity.get() == null) {
					return;
				}
				AppLog.blackLog(TAG, "MyHandler>>msg.what>> " + msg.what);
				RegisterActivity activity = wrActivity.get();
				ToastUtil.toastShow(activity, "图片上传失败");
				return;
			} else { // 发送头像链接给服务器
				PictureEntity entity = (PictureEntity) msg.obj;
				SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_AVATARUL,
						entity.getContent().publicUser.getAddress());
				// if (entity.getContent() == null ||
				// entity.getContent().getAddress() == null) {
				// return;
				// }
				// sendAvatarUrl(entity.getContent().getAddress(),
				// AccountConfig.getAccountId());
			}
		}
	}

	/**
	 * 修改头像
	 *
	 * @param urls
	 * @param memberId
	 */
	private void sendAvatarUrl(final String urls, String memberId) {
		mAccountApi.requestModifyAvatarUrl(this, memberId, urls, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ToastUtil.toastShow(RegisterActivity.this, msg);
				// AsyncImageLoader.setAsynAvatarImages(civ,
				// ApiConfig.IMAGE_URL+"/"+urls);
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(RegisterActivity.this, R.string.net_error);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(RegisterActivity.this, msg);
			}
		});
	}
}
