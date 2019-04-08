package com.zorasun.fangchanzhichuang.section.account;

import java.lang.ref.WeakReference;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.ImageUploadUtils;
import com.zorasun.fangchanzhichuang.general.util.RegexValidateUtil;
import com.zorasun.fangchanzhichuang.general.util.SharedPreferencesUtil;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.TimeUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.timer.TimerTextView;
import com.zorasun.fangchanzhichuang.section.account.entity.MemberModel;
import com.zorasun.fangchanzhichuang.section.account.entity.PictureEntity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

///**
// * 绑定账号登录
// * 
// * @author thm
// * @e-mail 443237034@qq.com
// * @version v1.0
// * @copyright 2010-2015
// * @create-time 2016年2月17日 下午6:02:30
// */
public class BindAccountActivity extends BaseActivity implements OnClickListener {

	public static final String KEY_BIND_OPENID = "key_bind_opend_id";
	public static final String KEY_BIND_THIRD_LOGIN_TYPE = "third_login_type";
	public static final String KEY_BIND_THIRD_THIRDNAME = "third_name";
	public static final String KEY_BIND_THIRD_THIRDIMGURL = "third_imgurl";
	public static final String TAG = "BindAccountActivity";
	// public static final String KEY_THREE_BACK_CONTENT =
	// "key_three_back_content";
	private EditText etTel;
	// private ImageView ivSee;
	private EditText etAuthCode;
	private TimerTextView tvGetAuthCode;
	private TextView tvTitle;
	private ImageButton back;
	private Button btnLogin;
	private boolean isPwd = false;
	private TimeUtil helper;
	public String mOpenId;
	private AccountApi mAccountApi;
	private int mBindThreeType; // 1:微信，0:QQ
	private String thirdAvatar;
	private String thirdNickName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bindlogin_layout);
		bindViews();
		initData();
	}

	private void bindViews() {
		etTel = (EditText) findViewById(R.id.etTel);
		etAuthCode = (EditText) findViewById(R.id.etAuthCode);
		tvGetAuthCode = (TimerTextView) findViewById(R.id.tvGetAuthCode);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		tvGetAuthCode.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		findViewById(R.id.tv_bind_register).setOnClickListener(this);
	}

	private void initData() {
		mAccountApi = new AccountApi();
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			mOpenId = bundle.getString(KEY_BIND_OPENID);
			mBindThreeType = bundle.getInt(KEY_BIND_THIRD_LOGIN_TYPE);
			thirdAvatar = bundle.getString(KEY_BIND_THIRD_THIRDIMGURL);
			thirdNickName = bundle.getString(KEY_BIND_THIRD_THIRDNAME);
		}
	}

	/**
	 * 登录
	 */
	private void login() {
		String phoneNum = etTel.getText().toString().trim();

		if (TextUtils.isEmpty(phoneNum)) {
			ToastUtil.toastShow(this, "手机号不能为空");
			return;
		}
		if (phoneNum.length() < 11) {
			ToastUtil.toastShow(this, "请输入11位手机号码");
			return;
		}
		if (!RegexValidateUtil.checkMobileNumber(phoneNum)) {
			ToastUtil.toastShow(this, "请输入正确的手机号码");
			return;
		}
		if (StringUtils.isEmpty(etAuthCode.getText().toString())) {
			ToastUtil.toastShow(this, "请输入验证码");
			return;
		}

		// 登录
		if (StringUtils.isEmpty(mOpenId)) {
			return;
		}
		String phoneNo = etTel.getText().toString().trim();
		String authCode = etAuthCode.getText().toString().trim();
		if (TextUtils.isEmpty(thirdAvatar)) {
			thirdAvatar = "";
		}
		if (TextUtils.isEmpty(thirdNickName)) {
			thirdNickName = "";
		}
		mAccountApi.requestBindThridPartyNoPsw(this, 1, phoneNo, mBindThreeType, mOpenId, authCode, thirdNickName,
				thirdAvatar, mLoginCallBack);

	}

	private RequestCallBack mLoginCallBack = new RequestCallBack() {

		@Override
		public void onSuccess(int code, String msg, Object object) {
			ToastUtil.toastShow(BindAccountActivity.this, msg);
			MemberModel memberModel = (MemberModel) object;
			AccountConfig.saveLoginData(memberModel);
			AccountConfig.setLoginState(true);
			// uploadAvatar();// 上传第三方账号头像
			// uploadNickName();// 把昵称设置为第三方昵称

			String alias = "xmfcgz_" + AccountConfig.getAccountId();
			if (!SharedPreferencesUtil.getString(SharedPreferencesUtil.SET_ALIAS_SUCCESS, "").equals(alias)) {
				// 极光推送设置tag
			}
			setResult(SystemConstant.RESULT_CODE_BIND_THIRD_ACCOUNT);
			finish();
		}

		@Override
		public void onNetworkError() {
			ToastUtil.toastShow(BindAccountActivity.this, R.string.net_error);
		}

		@Override
		public void onFailure(int code, String msg, Object object) {
			ToastUtil.toastShow(BindAccountActivity.this, msg);
		}
	};

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
				// TODO Auto-generated method stub

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

				ImageUploadUtils.uploadImg(BindAccountActivity.this, ApiConfig.UPLOADAVATAR, fileName, bitmap,
						new MyHandler(BindAccountActivity.this));

			}

			@Override
			public void onLoadingCancelled(String arg0, View arg1) {

			}
		});

	}

	private class MyHandler extends Handler {
		public WeakReference<BindAccountActivity> wrActivity;

		public MyHandler(BindAccountActivity activity) {
			wrActivity = new WeakReference<BindAccountActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what != 1) {
				if (wrActivity.get() == null) {
					return;
				}
				AppLog.blackLog(TAG, "MyHandler>>msg.what>> " + msg.what);
				BindAccountActivity activity = wrActivity.get();
				ToastUtil.toastShow(activity, "图片上传失败");
				return;
			} else { // 发送头像链接给服务器

				PictureEntity entity = (PictureEntity) msg.obj;
				// reHead(entity.getContent().getAddress());
				SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_AVATARUL,
						entity.getContent().publicUser.getAddress());
				// PictureEntity entity = (PictureEntity) msg.obj;
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
				ToastUtil.toastShow(BindAccountActivity.this, msg);
				// AccountConfig.setAccountAvatarUrl(urls);
				// AsyncImageLoader.setAsynAvatarImages(civ,
				// ApiConfig.IMAGE_URL+"/"+urls);
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(BindAccountActivity.this, R.string.net_error);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(BindAccountActivity.this, msg);
			}
		});
	}

	// /**
	// * 获取验证码
	// */
	//
	// private void getCode() {
	// // 请求完毕记得helper.cancel()
	// String phoneNum = etTel.getText().toString().trim();
	// mAccountApi.requestCode(this, mVerifyCodeCallBack,
	// AccountApi.MEMBER_THIRD_BIND, phoneNum);
	// }

	// private RequestCallBack mVerifyCodeCallBack = new RequestCallBack() {
	//
	// @Override
	// public void onSuccess(int code, String msg, Object object) {
	// VertifyCodeMode vertifyCodeMode = (VertifyCodeMode) object;
	// String num = vertifyCodeMode.content;
	// AppLog.blackLog(TAG, "短信验证码：" + num != null ? num : " 空 ");
	// // etAuthCode.setText(useRealData(num));
	// ToastUtil.toastShow(BindAccountActivity.this, msg);
	// }
	//
	// @Override
	// public void onNetworkError() {
	// ToastUtil.toastShow(BindAccountActivity.this, R.string.net_error);
	// }
	//
	// @Override
	// public void onFailure(int code, String msg, Object object) {
	// ToastUtil.toastShow(BindAccountActivity.this, msg);
	//
	// }
	// };

	/**
	 * 当为调试状态时data为真实数据
	 *
	 * @param data
	 * @return
	 */
	private String useRealData(String data) {
		if (!ApiConfig.IS_LOG) {
			data = "";
		}
		return data;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:// 返回
			finish();
			break;
		case R.id.tvGetAuthCode:// 获取验证码
			new GetVerificationUtil(this, etTel, tvGetAuthCode, mAccountApi).getThidVerification(1);
			break;
		case R.id.btnLogin:// 登录
			login();
			break;
		// 注册并绑定手机号
		case R.id.tv_bind_register:
			Intent intent = new Intent(this, RegisterActivity.class);
			intent.putExtra("flag", 1);
			intent.putExtra("thirdAvatar", thirdAvatar);
			intent.putExtra("thirdNickName", thirdNickName);
			intent.putExtra("mBindThreeType", mBindThreeType);
			intent.putExtra("mOpenId", mOpenId);
			startActivityForResult(intent, SystemConstant.REQUEST_CODE_BIND_THIRD_ACCOUNT);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);

		if (arg0 == SystemConstant.REQUEST_CODE_BIND_THIRD_ACCOUNT
				&& arg1 == SystemConstant.RESULT_CODE_BIND_THIRD_ACCOUNT) {
			setResult(SystemConstant.RESULT_CODE_BIND_THIRD_ACCOUNT);
			finish();
		}
	}
}
