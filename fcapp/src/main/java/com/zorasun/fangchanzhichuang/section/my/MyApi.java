package com.zorasun.fangchanzhichuang.section.my;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.general.base.BaseApi;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AppHelper;
import com.zorasun.fangchanzhichuang.general.util.HttpCallback;
import com.zorasun.fangchanzhichuang.general.util.HttpUtils;
import com.zorasun.fangchanzhichuang.section.my.entiy.CollectionListEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.InfoVersionEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.MyAttentionListEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.MyDemandEntity;

import android.content.Context;

public class MyApi extends BaseApi {

	protected static final String TAG = "BaseApi";

	private static MyApi mMyApi;

	private MyApi() {

	}

	public static MyApi getInstance() {
		if (mMyApi == null) {
			mMyApi = new MyApi();
		}
		return mMyApi;
	}

	public interface VersionUpdateCallback {
		public void onInUpdate(int code, String msg);

		public void onNetworkError();

		public void onUpdate(int code, InfoVersionEntity infoVersionEntity, String msg);
	}

	/**
	 * 反馈意见
	 * 
	 * @param aContext
	 * 
	 */
	public void requestFankuiYijian(Context aContext, String feedbackId, String feedbackContent, String contactWay,
			RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("feedbackId", feedbackId);
		params.put("feedbackContent", feedbackContent);
		params.put("contactWay", contactWay);
		post(aContext, ApiConfig.FANKUIYIJIAN, params, 1, callBack, BaseEntity.class);
	}

	/**
	 * 用户需求列表
	 * 
	 * @param pageNum
	 */
	public void requestDemand(Context mContext, int pageNum, int type, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("pageNum", pageNum);
		params.put("type", type);
		post(mContext, ApiConfig.DEMANDLIST, params, 1, aCallBack, MyDemandEntity.class);
	}

	/**
	 * 用户关注列表
	 * 
	 * @param pageNum
	 */
	public void requestAttentionList(Context mContext, int pageNum, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("pageNum", pageNum);
		post(mContext, ApiConfig.ATTENTIONLIST, params, 1, aCallBack, MyAttentionListEntity.class);
	}

	/**
	 * 取消需求
	 * 
	 * @param pageNum
	 */
	public void requestCancleDemand(Context mContext, int demandId, String remark, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("demandId", demandId);
		params.put("remark", remark);
		post(mContext, ApiConfig.CANCLEDEMAND, params, 1, aCallBack, BaseEntity.class);
	}

	/**
	 * 用户收藏列表
	 * 
	 * @param pageNum
	 * @param type
	 *            1二手房 2租房 4新房
	 */
	public void requestCollectionList(Context mContext, int type, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("type", type);
		post(mContext, ApiConfig.COLLECTIONLIST, params, 1, aCallBack, CollectionListEntity.class);
	}

	/**
	 * 需求评价
	 * 
	 * @param pageNum
	 */
	public void requestEvaluateDemand(Context mContext, RequestParams params, final RequestCallBack aCallBack) {
		post(mContext, ApiConfig.EVALUATEDEMAND, params, 1, aCallBack, CollectionListEntity.class);
	}

	// 版本更新
	public void requestUpdataSoft(Context context, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("version", AppHelper.getVersionCode(context));
		params.put("type", 0);
		// post(context, ApiConfig.UPDATA_SOFT, params, 1, callBack,
		// UpdataSoftEntity.class);
	}

	/**
	 * 版本更新
	 * 
	 * @param aContext
	 * @param versioncode
	 * @param aInfoCallback
	 */
	public void checkVersionUpdate(Context aContext, int versioncode, int showLoading, final boolean repeat,
			final VersionUpdateCallback aInfoCallback) {
		RequestParams params = new RequestParams();
		params.put("version", String.valueOf(versioncode));
		HttpUtils.postNoRepeat(aContext, ApiConfig.VERSION_CHECK_UPDATE, params, showLoading, repeat,
				new HttpCallback() {
					@Override
					public void onSuccess(String content) {
						try {
							AppLog.redLog(TAG, content);
							JSONObject responsejson = new JSONObject(content);
							InfoVersionEntity entity = new InfoVersionEntity();
							int code = HttpUtils.getJSONInt(responsejson, "code");
							String msg = HttpUtils.getJSONString(responsejson, "msg");
							// entity.url =
							// HttpUtils.getJSONString(responsejson, "content");
							// entity.isMustToUpdate = 2;
							JSONObject contents = HttpUtils.getJSONObject(responsejson, "content");
							entity.url = HttpUtils.getJSONString(contents, "url");
							entity.isMustToUpdate = HttpUtils.getJSONInt(contents, "isMustToUpdate");

							if (code == 0) // 不是最新版本
							{
								aInfoCallback.onUpdate(code, entity, msg);
							} else { // 是最新版本
								aInfoCallback.onInUpdate(code, msg);
							}
						} catch (JSONException e) {
							AppLog.redLog(TAG, e.toString());
							e.printStackTrace();
						}
					}

					@Override
					public void onNetworkError() {
						aInfoCallback.onNetworkError();
					}

					@Override
					public void onProgress(int bytesWritten, int totalSize) {

					}
				});

	}

}
