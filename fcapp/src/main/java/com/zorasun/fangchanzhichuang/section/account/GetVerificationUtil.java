package com.zorasun.fangchanzhichuang.section.account;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.marco.SystemConstant;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.timer.TimerEventListner;
import com.zorasun.fangchanzhichuang.general.widget.timer.TimerTextView;

import android.content.Context;
import android.widget.TextView;

/**
 * 获取验证吗
 */
public class GetVerificationUtil {
	private Context mContext;
	TextView mEt_phone;
	TimerTextView mBtn_get_verification;
	AccountApi mAccountApi;
	private TimerEventListner clockListener;

	public GetVerificationUtil(Context mContext, TextView mEt_phone, TimerTextView mBtn_get_verification,
			AccountApi mAccountApi) {
		this.mContext = mContext;
		this.mEt_phone = mEt_phone;
		this.mBtn_get_verification = mBtn_get_verification;
		this.mAccountApi = mAccountApi;
	}

	/**
	 * 获取验证码
	 */
	public void getVerification(String type) {

		if (StringUtils.isEmpty(mEt_phone.getText().toString().trim())) {
			ToastUtil.toastShow(mContext, mContext.getString(R.string.please_input_phone));
			return;
		} else if (!StringUtils.isPhone(mEt_phone.getText().toString().trim())) {
			ToastUtil.toastShow(mContext, mContext.getString(R.string.please_input_right_phone));
			return;
		} else {

			mBtn_get_verification.setTimeOver(mContext.getString(R.string.get_verification));
			mBtn_get_verification.setTime(SystemConstant.GET_CODE_TIME, mContext.getString(R.string.get_code_time),
					TimerTextView.RIGHT, -1);
			mBtn_get_verification.setTextColor(mContext.getResources().getColor(R.color.white));
			mBtn_get_verification.setBackgroundColor(mContext.getResources().getColor(R.color.txt_balance));
			mBtn_get_verification.setClickable(false);
			clockListener = new TimerEventListner() {

				@Override
				public void onTimeNotice(int position, long markTime) {

				}

				@Override
				public void onTimeOver(int position) {
					mBtn_get_verification.setText(R.string.get_verification);
					mBtn_get_verification.setTextColor(mContext.getResources().getColor(R.color.white));
					mBtn_get_verification.setBackgroundColor(mContext.getResources().getColor(R.color.title_bg));
					mBtn_get_verification.setClickable(true);
				}

			};
			mBtn_get_verification.addObserve(clockListener);
			getCode(type);
		}
	}

	/**
	 * 获取验证码
	 */
	public void getThidVerification(int isHasBindType) {

		if (StringUtils.isEmpty(mEt_phone.getText().toString().trim())) {
			ToastUtil.toastShow(mContext, mContext.getString(R.string.please_input_phone));
			return;
		} else if (!StringUtils.isPhone(mEt_phone.getText().toString().trim())) {
			ToastUtil.toastShow(mContext, mContext.getString(R.string.please_input_right_phone));
			return;
		} else {

			mBtn_get_verification.setTimeOver(mContext.getString(R.string.get_verification));
			mBtn_get_verification.setTime(SystemConstant.GET_CODE_TIME, mContext.getString(R.string.get_code_time),
					TimerTextView.RIGHT, -1);
			mBtn_get_verification.setTextColor(mContext.getResources().getColor(R.color.white));
			mBtn_get_verification.setBackgroundColor(mContext.getResources().getColor(R.color.txt_balance));
			mBtn_get_verification.setClickable(false);
			clockListener = new TimerEventListner() {

				@Override
				public void onTimeNotice(int position, long markTime) {

				}

				@Override
				public void onTimeOver(int position) {
					mBtn_get_verification.setText(R.string.get_verification);
					mBtn_get_verification.setTextColor(mContext.getResources().getColor(R.color.white));
					mBtn_get_verification.setBackgroundColor(mContext.getResources().getColor(R.color.title_bg));
					mBtn_get_verification.setClickable(true);
				}

			};
			mBtn_get_verification.addObserve(clockListener);
			getThirdCode(isHasBindType);
		}
	}

	/**
	 * 獲取验证码
	 */
	void getThirdCode(int isHasBindType) {

		mAccountApi.getThirdVerfication(mContext, isHasBindType, mEt_phone.getText().toString().trim(),
				new RequestCallBack() {

					@Override
					public void onNetworkError() {
						mBtn_get_verification.setText(R.string.get_verification);
						mBtn_get_verification.setTextColor(mContext.getResources().getColor(R.color.white));
						mBtn_get_verification.setClickable(true);
						ToastUtil.toastShow(mContext, mContext.getString(R.string.net_error));
					}

					@Override
					public void onSuccess(int code, String msg, Object object) {
						ToastUtil.toastShow(mContext, msg);
					}

					@Override
					public void onFailure(int code, String msg, Object object) {

						ToastUtil.toastShow(mContext, msg);
						mBtn_get_verification.setText(R.string.get_verification);
						mBtn_get_verification.setTextColor(mContext.getResources().getColor(R.color.white));
						mBtn_get_verification.setBackgroundColor(mContext.getResources().getColor(R.color.title_bg));
						mBtn_get_verification.removeObserve(clockListener);
						mBtn_get_verification.setClickable(true);
					}
				});
	}

	/**
	 * 獲取验证码
	 */
	void getCode(String type) {

		mAccountApi.getVerfication(mContext, mEt_phone.getText().toString().trim(), type, new RequestCallBack() {

			@Override
			public void onNetworkError() {
				mBtn_get_verification.setText(R.string.get_verification);
				mBtn_get_verification.setTextColor(mContext.getResources().getColor(R.color.white));
				mBtn_get_verification.setClickable(true);
				ToastUtil.toastShow(mContext, mContext.getString(R.string.net_error));
			}

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ToastUtil.toastShow(mContext, msg);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(mContext, msg);

				mBtn_get_verification.setText(R.string.get_verification);
				mBtn_get_verification.setTextColor(mContext.getResources().getColor(R.color.white));
				mBtn_get_verification.setBackgroundColor(mContext.getResources().getColor(R.color.title_bg));
				mBtn_get_verification.removeObserve(clockListener);
				mBtn_get_verification.setClickable(true);
			}
		});
	}
}
