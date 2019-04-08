package com.zorasun.fangchanzhichuang.section.message;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.general.base.BaseApi;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.section.message.entity.MyMessageEntity;

import android.content.Context;

public class MessageApi extends BaseApi {
	protected static final String TAG = "MessageApi";
	static MessageApi mInstance = null;

	public static MessageApi getInstance() {
		if (mInstance == null) {
			mInstance = new MessageApi();
		}
		return mInstance;
	}

	/**
	 * 获取消息列表
	 * 
	 * @param aContext
	 * @param type
	 *            2全部 0消息 1公告
	 * 
	 */
	public void requestMessageList(Context aContext, int type, int pageNum, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("type", type);
		params.put("pageNum", pageNum);
		post(aContext, ApiConfig.MESSAGELIST, params, 1, callBack, MyMessageEntity.class);
	}

	/**
	 * 获取公告详情
	 * 
	 * @param aContext
	 * 
	 */
	public void requestNoticeInfo(Context aContext, int advicesId, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("advicesId", advicesId);
		post(aContext, ApiConfig.NOTICEINFO, params, 1, callBack, MyMessageEntity.class);
	}

}
