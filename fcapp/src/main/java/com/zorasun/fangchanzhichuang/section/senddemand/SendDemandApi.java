package com.zorasun.fangchanzhichuang.section.senddemand;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.general.base.BaseApi;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.section.account.entity.LoginEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.DemandDetailEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.AreaListInitEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.SearchAreaListEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.XiaMenAreaListEntity;

import android.content.Context;

public class SendDemandApi extends BaseApi {
	protected static final String TAG = "SendDemandApi";

	static SendDemandApi mInstance = null;

	public static SendDemandApi getInstance() {
		if (mInstance == null) {
			mInstance = new SendDemandApi();
		}
		return mInstance;
	}

	/**
	 * 用户获取验证码
	 */
	public void getVerfication(Context mContext, String account, String type, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("mobile", account);
		params.put("type", type);
		post(mContext, ApiConfig.VERFICATION, params, 1, aCallBack, BaseEntity.class);
	}

	/**
	 * 获取厦门商圈楼盘信息
	 */
	public void requestXiaMenInfo(Context mContext, RequestParams params, final RequestCallBack aCallBack) {
		post(mContext, ApiConfig.XIAMENAREAINFO, params, 1, aCallBack, XiaMenAreaListEntity.class);
	}

	/**
	 * 用户发布求购，求租需求
	 */
	public void requestSendBuyWantRentDemand(Context mContext, RequestParams params, final RequestCallBack aCallBack) {
		post(mContext, ApiConfig.WANTBUYRENT, params, 1, aCallBack, LoginEntity.class);
	}

	/**
	 * 用户发布出售，出租需求
	 */
	public void requestSendSellRentDemand(Context mContext, RequestParams params, final RequestCallBack aCallBack) {
		post(mContext, ApiConfig.SELLRENT, params, 1, aCallBack, LoginEntity.class);
	}

	/**
	 * 用户求购新房
	 */
	public void requestQiuGouNewDemand(Context mContext, RequestParams params, final RequestCallBack aCallBack) {
		post(mContext, ApiConfig.QIUGOUXINFANG, params, 1, aCallBack, LoginEntity.class);
	}

	/**
	 * 需求详情
	 */
	public void requestDemandDetail(Context mContext, int demandId, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("demandId", demandId);
		post(mContext, ApiConfig.DEMANDDETAIL, params, 1, aCallBack, DemandDetailEntity.class);
	}

	/**
	 * 记录消息读取状态
	 */
	public void requestReadRecord(Context mContext, int adviceId, String accountId, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("adviceId", adviceId);
		params.put("accountId", accountId);
		post(mContext, ApiConfig.READRECORD, params, 1, aCallBack, DemandDetailEntity.class);
	}

	/**
	 * 获取小区数据
	 */
	public void requestArealist(Context mContext, double latitude, double longitude, final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("latitude", latitude);
		params.put("longitude", longitude);
		post(mContext, ApiConfig.AREALISTQUERY, params, 1, aCallBack, AreaListInitEntity.class);
	}

	public void requestSearchArealist(Context mContext, int showLoading, String areaListName,
			final RequestCallBack aCallBack) {
		RequestParams params = new RequestParams();
		params.put("areaListName", areaListName);
		post(mContext, ApiConfig.AREALISTQUERY, params, showLoading, aCallBack, SearchAreaListEntity.class);
	}

}
