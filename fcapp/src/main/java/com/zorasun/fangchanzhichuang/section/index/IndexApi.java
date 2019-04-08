package com.zorasun.fangchanzhichuang.section.index;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.general.base.BaseApi;
import com.zorasun.fangchanzhichuang.general.base.BaseEntity;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.HttpCallback;
import com.zorasun.fangchanzhichuang.general.util.HttpUtils;
import com.zorasun.fangchanzhichuang.section.index.entity.AdMangerEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.AllCityEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.AreaListInfoEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.AroundBrokerListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerHouseResEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreBrokerListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreRentHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreSecondHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.EvaluateEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.HotTagEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouInfoEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouRentHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouSecondHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.MapSearchBusinessEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.MapSearchEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.NearByListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.PropertyEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.RateEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseInfoEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.ShangYeDiChanDetailEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.ShangYeDiChanListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.SearchAreaListEntity;

import android.content.Context;

public class IndexApi extends BaseApi {
	protected static final String TAG = "IndexApi";
	private static IndexApi mIndexApi;

	private IndexApi() {

	}

	public static IndexApi getInstance() {
		mIndexApi = new IndexApi();
		return mIndexApi;
	}

	public interface RateCallback {
		public void onSuccess(int code, String msg, List<RateEntity> entitys);

		public void onFailure(int code, String msg);

		public void onNetworkError();
	}

	/**
	 * 获取经纪人列表
	 * 
	 * @param aContext
	 * 
	 */
	public void requestBrokerList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.BROKERLIST, params, 1, callBack, BrokerListEntity.class);
	}

	/**
	 * 获取经纪人信息
	 * 
	 * @param aContext
	 * @param brokerId
	 * @param accountId
	 * 
	 */
	public void requestBrokerInfo(Context aContext, int brokerId, String accountId, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("brokerId", brokerId);
		params.put("accountId", accountId);
		post(aContext, ApiConfig.BROKERINFO, params, 1, callBack, BrokerInfoEntity.class);
	}

	/**
	 * 添加关注经纪人
	 * 
	 * @param aContext
	 * 
	 */
	public void requestBrokerAttion(Context aContext, int brokerId, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("brokerId", brokerId);
		post(aContext, ApiConfig.BROKERATTION, params, 1, callBack, BaseEntity.class);
	}

	/**
	 * 取消关注经纪人
	 * 
	 * @param aContext
	 * 
	 */
	public void requestBrokerAttionCancle(Context aContext, int brokerId, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("brokerId", brokerId);
		post(aContext, ApiConfig.BROKERATTIONCANCLE, params, 1, callBack, BaseEntity.class);
	}

	/**
	 * 搜索经纪人列表
	 * 
	 * @param aContext
	 * 
	 */
	public void requestSearchBrokerList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.BROKERLIST, params, 1, callBack, BrokerListEntity.class);
	}

	/**
	 * 搜索附近的房列表
	 * 
	 * @param aContext
	 * @param houseTypeName
	 *            住宅、商铺、写字楼、厂房
	 * @param houseResourceTypeName
	 *            二手房、租房
	 * @param latitude
	 * @param longitude
	 * @param pageNum
	 * 
	 */
	public void requestSearchNearByHouse(Context aContext, String houseTypeName, String houseResourceTypeName,
			double latitude, double longitude, int pageNum, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("houseTypeName", houseTypeName);
		params.put("houseResourceTypeName", houseResourceTypeName);
		params.put("latitude", latitude);
		params.put("longitude", longitude);
		params.put("pageNum", pageNum);

		post(aContext, ApiConfig.NEARBYHOUSE, params, 1, callBack, NearByListEntity.class);
	}

	/**
	 * 周边土地公列表
	 * 
	 * @param aContext
	 * 
	 */
	public void requestAroundBrokerList(Context aContext, double latitude, double longitude, int pageNum,
			RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("latitude", latitude);
		params.put("longitude", longitude);
		params.put("pageSize", pageNum);
		post(aContext, ApiConfig.AROUNDBROKERLIST, params, 1, callBack, AroundBrokerListEntity.class);
	}

	/**
	 * 获取租房列表
	 * 
	 * @param aContext
	 * 
	 */
	public void requestRentHouseList(Context aContext, RequestParams params, RequestCallBack callBack) {

		post(aContext, ApiConfig.ZUFANGLIST, params, 1, callBack, RentHouseListEntity.class);
	}

	/**
	 * 搜索租房列表
	 * 
	 * @param aContext
	 * 
	 */
	public void requestSearchHouseList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.ZUFANGLIST, params, 1, callBack, RentHouseListEntity.class);
	}

	/**
	 * 获取租房详情
	 * 
	 * @param aContext
	 * 
	 */
	public void requestRentHouseInfo(Context aContext, int rentHouseId, String accountId, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("rentHouseId", rentHouseId);
		params.put("accountId", accountId);
		post(aContext, ApiConfig.ZUFANGINFO, params, 1, callBack, RentHouseEntity.class);
	}

	/**
	 * 获取新房列表
	 * 
	 * @param aContext
	 * 
	 */
	public void requestNewHouseList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.NEWHOUSELIST, params, 1, callBack, NewHouseListEntity.class);
	}

	/**
	 * 搜索新房列表
	 * 
	 * @param aContext
	 * 
	 */
	public void requestSearchNewHouseList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.NEWHOUSELIST, params, 1, callBack, NewHouseListEntity.class);
	}

	/**
	 * 获取新房详情
	 * 
	 * @param aContext
	 * 
	 */
	public void requestNewHouseInfo(Context aContext, int newhouseId, String accountId, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("newHouseId", newhouseId);
		params.put("accountId", accountId);
		post(aContext, ApiConfig.NEWHOUSEINFO, params, 1, callBack, NewHouseEntity.class);
	}

	/**
	 * 搜索二手房列表
	 * 
	 * @param aContext
	 * 
	 */
	public void requestSecondHouseList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.SECONDHOUSELIST, params, 1, callBack, SecondHouseListEntity.class);
	}

	/**
	 * 获取二手房详情
	 * 
	 * @param aContext
	 * 
	 */
	public void requestSecondHouseInfo(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.SECONDHOUSEINFO, params, 1, callBack, SecondHouseInfoEntity.class);
	}

	/**
	 * 小区详情
	 * 
	 * @param aContext
	 * 
	 */
	public void requestArealistInfo(Context aContext, int areaListId, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("areaListId", areaListId);
		post(aContext, ApiConfig.AREALISTINFO, params, 1, callBack, AreaListInfoEntity.class);
	}

	/**
	 * 机构详情
	 * 
	 * @param aContext
	 * 
	 */
	public void requestJiGouInfo(Context aContext, int agencyId, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("agencyId", agencyId);
		post(aContext, ApiConfig.JIGOUINFO, params, 1, callBack, JiGouInfoEntity.class);
	}

	/**
	 * 获取商业地产列表
	 * 
	 * @param aContext
	 * 
	 */
	public void requestShangYeDiChanList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.SHANGYEDICHANLIST, params, 1, callBack, ShangYeDiChanListEntity.class);
	}

	/**
	 * 获取商业详情
	 * 
	 * @param aContext
	 * 
	 */
	public void requestShangYeDiChanDetail(Context aContext, String accountId, int selectTypeId, int id,
			RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("selectTypeId", selectTypeId);
		params.put("id", id);
		params.put("accountId", accountId);
		post(aContext, ApiConfig.SHANGYEDICHANDETAIL, params, 1, callBack, ShangYeDiChanDetailEntity.class);
	}

	/**
	 * 获取经纪人评价信息
	 * 
	 * @param aContext
	 * 
	 */
	public void requestBrokerEvaluate(Context aContext, int page, int brokerId, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("brokerId", brokerId);
		params.put("pageNum", page);
		post(aContext, ApiConfig.BROKEREVALUATE, params, 1, callBack, EvaluateEntity.class);
	}

	/**
	 * 二手房、租房、新房收藏
	 * 
	 * @param aContext
	 * 
	 */
	public void requestCollectHouse(Context aContext, int id, int collectionType, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("id", id);
		params.put("collectionType", collectionType);
		post(aContext, ApiConfig.HOUSECOLLECT, params, 1, callBack, BaseEntity.class);
	}

	/**
	 * 二手房举报
	 * 
	 * @param aContext
	 * 
	 */
	public void requestSecondHouseReport(Context aContext, String accountId, int id, String content,
			RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("accountId", accountId);
		params.put("secondHouseId", id);
		params.put("reportContent", content);
		post(aContext, ApiConfig.SECONDHOUSEREPORT, params, 1, callBack, BaseEntity.class);
	}

	/**
	 * 二手房、租房、新房取消收藏
	 * 
	 * @param aContext
	 * @param collectionType
	 * 
	 */
	public void requestCancleCollectHouse(Context aContext, int id, int collectionType, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("id", id);
		params.put("collectionType", collectionType);
		post(aContext, ApiConfig.CANCLEHOUSECOLLECT, params, 1, callBack, BaseEntity.class);
	}

	/**
	 * 获取经纪人二手房、租房更多列表
	 * 
	 * @param aContext
	 * @param pageNum
	 * @param houseResourceTypeName
	 * @param brokerId
	 * 
	 */
	public void requestBrokerHouseResList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.BROKERHOUSERESLIST, params, 1, callBack, BrokerHouseResEntity.class);
	}

	/**
	 * 获取机构详情二手房更多列表
	 * 
	 * @param aContext
	 * @param pageNum
	 * @param houseResourceTypeName
	 * @param brokerId
	 * 
	 */
	public void requestJiGouHouseResList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.JIGOUHOUSERESLIST, params, 1, callBack, JiGouSecondHouseEntity.class);
	}

	/**
	 * 获取机构详情租房更多列表
	 * 
	 * @param aContext
	 * @param pageNum
	 * @param houseResourceTypeName
	 * @param brokerId
	 * 
	 */
	public void requestJiGouRentHouseResList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.JIGOURENTHOUSERESLIST, params, 1, callBack, JiGouRentHouseEntity.class);
	}

	/**
	 * 获取小区二手房更多
	 * 
	 * @param aContext
	 * @param pageNum
	 * @param areaListId
	 * @param houseTypeId
	 * 
	 */
	public void requestCommunitySecondhouse(Context aContext, RequestParams params, RequestCallBack callBack) {

		post(aContext, ApiConfig.COMMUNITYSECONDHOUSE, params, 1, callBack, CommunityMoreSecondHouseEntity.class);
	}

	/**
	 * 获取小区租房更多
	 * 
	 * @param aContext
	 * 
	 */
	public void requestCommunityRenthouse(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.COMMUNITYRENTDHOUSE, params, 1, callBack, CommunityMoreRentHouseEntity.class);
	}

	/**
	 * 获取小区经纪人更多
	 * 
	 * @param aContext
	 * 
	 */
	public void requestCommunityBroker(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.COMMUNITYBROKER, params, 1, callBack, CommunityMoreBrokerListEntity.class);
	}

	/**
	 * 获取经纪人热门标签
	 * 
	 * @param aContext
	 * 
	 */
	public void requestBrokerHot(Context aContext, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		post(aContext, ApiConfig.BROKERTAG, params, 1, callBack, HotTagEntity.class);
	}

	/**
	 * 获取二手房热门标签
	 * 
	 * @param aContext
	 * 
	 */
	public void requestSecondHot(Context aContext, int classify, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("classify", classify);
		post(aContext, ApiConfig.SECONDRENTHOUSETAG, params, 1, callBack, HotTagEntity.class);
	}

	/**
	 * 获取租房热门标签
	 * 
	 * @param aContext
	 * 
	 */
	public void requestRentHot(Context aContext, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		post(aContext, ApiConfig.ALLCITYLIST, params, 1, callBack, HotTagEntity.class);
	}

	/**
	 * 获取新房热门标签
	 * 
	 * @param aContext
	 * 
	 */
	public void requestNewHot(Context aContext, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		post(aContext, ApiConfig.NEWHOUSETAG, params, 1, callBack, HotTagEntity.class);
	}

	/**
	 * 获取城市列表
	 * 
	 * @param aContext
	 * 
	 */
	public void requestCityList(Context aContext, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		post(aContext, ApiConfig.ALLCITYLIST, params, 1, callBack, AllCityEntity.class);
	}

	/**
	 * 获取二手房行情
	 * 
	 * @param aContext
	 * @param type
	 * @param currentMonth
	 * @param currentTime
	 * 
	 */
	public void requestWidePrice(Context aContext, int type, int currentMonth, String currentTime,
			RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("type", type);
		params.put("currentMonth", currentMonth);
		params.put("currentTime", currentTime);
		post(aContext, ApiConfig.WIDEPRICE, params, 1, callBack, WidePriceEntity.class);
	}

	// 获取小区行情
	public void requestCommunityWidePrice(Context aContext, int type, int areaListId, int currentMonth,
			String currentTime, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("type", type);
		params.put("areaListId", areaListId);
		params.put("currentMonth", currentMonth);
		params.put("currentTime", currentTime);
		post(aContext, ApiConfig.WIDEPRICE, params, 1, callBack, WidePriceEntity.class);
	}

	/**
	 * 获取地图找房信息
	 * 
	 * @param aContext
	 * 
	 */
	public void requestMapList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.MAPINFOLIST, params, 1, callBack, MapSearchEntity.class);
	}

	/**
	 * 搜索二手房、租房
	 * 
	 * @param aContext
	 * 
	 */
	public void requestSearchList(Context aContext, String keyword, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("keyword", keyword);
		post(aContext, ApiConfig.SEARCHLIST, params, 0, callBack, SearchAreaListEntity.class);
	}

	/**
	 * 搜索新房
	 * 
	 * @param aContext
	 * 
	 */
	public void requestSearchNewList(Context aContext, String keyword, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("keyword", keyword);
		post(aContext, ApiConfig.SEARCHNEWLIST, params, 0, callBack, SearchAreaListEntity.class);
	}

	/**
	 * 地图找商业地产
	 * 
	 * @param aContext
	 * 
	 */
	public void requestMapBusinessList(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.MAPBUSINESSLIST, params, 1, callBack, MapSearchBusinessEntity.class);
	}

	/**
	 * 广告管理
	 * 
	 * @param aContext
	 * @param currentTime
	 * @param currentMonth
	 * 
	 */
	public void requestAdmanager(Context aContext, String currentTime, int currentMonth, RequestCallBack callBack) {
		RequestParams params = new RequestParams();
		params.put("currentTime", currentTime);
		params.put("currentMonth", currentMonth);
		post(aContext, ApiConfig.ADMANAGER, params, 1, callBack, AdMangerEntity.class);
	}

	/**
	 * 产权验证
	 * 
	 * @param aContext
	 * @param propertyNo
	 *            产权验证号
	 * @param idCardNo
	 *            身份证号
	 * @param landlord
	 *            房东姓名
	 * 
	 */
	public void requestChanQuan(Context aContext, RequestParams params, RequestCallBack callBack) {
		post(aContext, ApiConfig.CHANQUAN, params, 1, callBack, PropertyEntity.class);
	}

	/**
	 * 请求比率
	 * 
	 * @param context
	 * @param aCallBack
	 */
	public void requestRate(Context context, final RateCallback aCallBack) {

		RequestParams params = new RequestParams();

		HttpUtils.postNoRepeat2(context, ApiConfig.RATE, params, 1, false, false, new HttpCallback() {
			@Override
			public void onSuccess(String content) {
				AppLog.redLog(TAG, content);
				try {

					JSONObject responsejson = new JSONObject(content);
					int code = HttpUtils.getJSONInt(responsejson, "code");
					String msg = HttpUtils.getJSONString(responsejson, "msg");

					if (code == 1) {
						String object = responsejson.getString("content");
						Gson gson = new Gson();
						Type type = new TypeToken<List<RateEntity>>() {
						}.getType();
						List<RateEntity> entitys = gson.fromJson(object, type);
						aCallBack.onSuccess(code, msg, entitys);
					} else {
						aCallBack.onFailure(code, msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onNetworkError() {
				aCallBack.onNetworkError();
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {

			}
		});

	}

}
