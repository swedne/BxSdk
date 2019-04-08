package com.zorasun.fangchanzhichuang.general.base;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.util.HttpCallback;
import com.zorasun.fangchanzhichuang.general.util.HttpUtils;

import android.content.Context;

public class BaseApi {

	private static final String TAG = "BaseApi";

	public interface RequestCallBack {
		public void onSuccess(int code, String msg, Object object);

		public void onFailure(int code, String msg, Object object);

		public void onNetworkError();
	}

	/**
	 * 请求和提交普通数据
	 * 
	 * @param params
	 * @param apiUrl
	 * @param context
	 * @param showProgress
	 * @param mCallback
	 * @param aClass
	 *            (如果是content中没有数据直接传入BaseEntity对象)
	 */
	public void post(Context mContext, String apiUrl, RequestParams params, int showLoading,
			final RequestCallBack mCallback, final Class<?> aClass) {
		HttpUtils.postNoRepeat(mContext, apiUrl.trim(), params, showLoading, false, new HttpCallback() {

			@Override
			public void onSuccess(String content) {
				AppLog.redLog(TAG, content);
				try {
					Gson gson = new Gson();
					BaseEntity object = (BaseEntity) gson.fromJson(content, aClass);
					if (object.getCode() == 1 || object.getCode() == 2) {
						mCallback.onSuccess(object.getCode(), object.getMsg(), object);
					} else {
						mCallback.onFailure(object.getCode(), object.getMsg(), object);
					}
				} catch (Exception e) {
					e.printStackTrace();
					mCallback.onNetworkError();
				}
			}

			@Override
			public void onNetworkError() {
				mCallback.onNetworkError();
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {

			}

		});
	}

	/**
	 * 请求和提交普通数据 可以
	 * 
	 * @param mContext
	 * @param apiUrl
	 * @param params
	 * @param showLoading
	 * @param repeat
	 *            是否可以重复提交(true:可重复， false：不可重复)
	 * @param mCallback
	 * @param aClass
	 *            (如果是content中没有数据直接传入BaseEntity对象)
	 */
	public void postTwo(Context mContext, String apiUrl, RequestParams params, int showLoading, boolean repeat,
			final RequestCallBack mCallback, final Class<?> aClass) {
		HttpUtils.postNoRepeat(mContext, apiUrl, params, showLoading, repeat, new HttpCallback() {

			@Override
			public void onSuccess(String content) {
				AppLog.redLog(TAG, content);
				try {
					Gson gson = new Gson();
					BaseEntity object = (BaseEntity) gson.fromJson(content, aClass);
					if (object.getCode() == 1) {
						mCallback.onSuccess(object.getCode(), object.getMsg(), object);
					} else {
						mCallback.onFailure(object.getCode(), object.getMsg(), object);
					}
				} catch (Exception e) {
					e.printStackTrace();
					mCallback.onNetworkError();
				}
			}

			@Override
			public void onNetworkError() {
				mCallback.onNetworkError();
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {

			}

		});
	}

}
