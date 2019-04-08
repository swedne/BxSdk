package com.zorasun.fangchanzhichuang.section.senddemand;

import java.util.ArrayList;
import java.util.List;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.commonadapter.CommonAdapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.ViewHolder;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.timer.TimerTextView;
import com.zorasun.fangchanzhichuang.section.account.AccountApi;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.account.GetVerificationUtil;
import com.zorasun.fangchanzhichuang.section.account.entity.LoginEntity;
import com.zorasun.fangchanzhichuang.section.my.MyDemandActivity;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.XiaMenAreaListEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.XiaMenAreaListEntity.AreaList;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.XiaMenAreaListEntity.BusinessList;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.XiaMenAreaListEntity.HouseTypeList;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.XiaMenAreaListEntity.XiamenInitList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class BuySecondHouseActivity extends BaseActivity implements OnClickListener {

	private View titleBar;
	private EditText etPhone;
	private EditText etName;
	private EditText etCode;
	private List<HouseTypeList> houseTypeList = new ArrayList<>();
	private List<XiamenInitList> xiamenInitList = new ArrayList<>();
	private List<BusinessList> businessList = new ArrayList<>();
	private List<AreaList> areaList = new ArrayList<>();
	private List<String> popListInfo = new ArrayList<>();
	private EditText etClassify;
	private EditText etAddress;
	private EditText etBusiness;
	private EditText etCommunity;
	private EditText etArea;
	private EditText etSumprice;
	private int areaId = -1;
	private int businessListId = -1;
	private RequestParams params;
	private int flag;
	private EditText etRoomNum;
	private EditText etHallNum;
	private EditText etToiletNum;
	private TimerTextView btnGetMsg;
	private AccountApi mAccountApi;
	private PopAdapterBusiness businessAdapter;
	private PopAdapterClassify classifyAdapter;
	private PopAdapterAdress addressAdapter;
	private PopAdapterCommunity communityAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy_second_house);
		mAccountApi = new AccountApi();
		businessAdapter = new PopAdapterBusiness(this, areaList, R.layout.item_textview);
		classifyAdapter = new PopAdapterClassify(this, houseTypeList, R.layout.item_textview);
		addressAdapter = new PopAdapterAdress(this, xiamenInitList, R.layout.item_textview);
		communityAdapter = new PopAdapterCommunity(this, businessList, R.layout.item_textview);
		initView();
		initData();
	}

	private void initData() {
		if (params == null) {
			params = new RequestParams();
		}
		SendDemandApi.getInstance().requestXiaMenInfo(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				XiaMenAreaListEntity xiaMenAreaListEntity = (XiaMenAreaListEntity) object;
				houseTypeList.clear();
				xiamenInitList.clear();
				areaList.clear();
				businessList.clear();
				List<HouseTypeList> houseTypeList2 = xiaMenAreaListEntity.getContent().getHouseTypeList();
				if (houseTypeList2 != null) {
					houseTypeList.addAll(houseTypeList2);

				}
				List<XiamenInitList> xiamenInitList2 = xiaMenAreaListEntity.getContent().getXiamenInitList();
				if (xiamenInitList2 != null) {
					xiamenInitList.addAll(xiamenInitList2);
				}
				List<AreaList> areaList2 = xiaMenAreaListEntity.getContent().getAreaList();
				if (areaList2 != null) {
					areaList.addAll(areaList2);
				}

				List<BusinessList> businessList2 = xiaMenAreaListEntity.getContent().getBusinessList();
				if (businessList2 != null) {
					businessList.addAll(businessList2);
				}

				businessAdapter.notifyDataSetChanged();
				classifyAdapter.notifyDataSetChanged();
				addressAdapter.notifyDataSetChanged();
				communityAdapter.notifyDataSetChanged();

			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {

			}
		});

	}

	private void initView() {
		initTitlebar();
		etName = (EditText) findViewById(R.id.et_buysecondehousename);
		etPhone = (EditText) findViewById(R.id.et_buysecondhousephone);
		etCode = (EditText) findViewById(R.id.et_buysecondhousecode);

		btnGetMsg = (TimerTextView) findViewById(R.id.btn_getMsg);
		btnGetMsg.setOnClickListener(this);
		findViewById(R.id.ll_buysecondhouseclassify);
		findViewById(R.id.ll_buysecondhouseaddress);
		findViewById(R.id.ll_buysecondhousebusiness);
		findViewById(R.id.ll_buysecondhousecommunity);
		findViewById(R.id.ll_buysecondhousearea);
		findViewById(R.id.ll_buysecondhousesumprice);
		etRoomNum = (EditText) findViewById(R.id.et_roomnum);
		etHallNum = (EditText) findViewById(R.id.et_hallnum);
		etToiletNum = (EditText) findViewById(R.id.et_toiletnum);

		TextView tvBuySecondHouseName = (TextView) findViewById(R.id.tv_buysecondhousename);
		TextView tvBuySecondHousePhone = (TextView) findViewById(R.id.tv_buysecondhousephone);
		TextView tvBuySecondHouseGetcode = (TextView) findViewById(R.id.tv_buysecondehousegetcode);
		TextView tvBuySecondHouseAddress = (TextView) findViewById(R.id.tv_buysecondhouseaddress);
		TextView tvBuySecondHouseBusiness = (TextView) findViewById(R.id.tv_buysecondhousebusiness);
		TextView tvBuySecondHouseClassify = (TextView) findViewById(R.id.tv_wantrentclassify);
		SpannableString ss = new SpannableString(tvBuySecondHouseName.getText().toString());
		ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBuySecondHouseName.setText(ss);

		SpannableString ss1 = new SpannableString(tvBuySecondHousePhone.getText().toString());
		ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBuySecondHousePhone.setText(ss1);

		SpannableString ss2 = new SpannableString(tvBuySecondHouseGetcode.getText().toString());
		ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBuySecondHouseGetcode.setText(ss2);

		SpannableString ss3 = new SpannableString(tvBuySecondHouseAddress.getText().toString());
		ss3.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBuySecondHouseAddress.setText(ss3);

		SpannableString ss4 = new SpannableString(tvBuySecondHouseClassify.getText().toString());
		ss4.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBuySecondHouseClassify.setText(ss4);

		SpannableString ss5 = new SpannableString(tvBuySecondHouseBusiness.getText().toString());
		ss5.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvBuySecondHouseBusiness.setText(ss5);

		etClassify = (EditText) findViewById(R.id.et_buysecondhouseclassify);
		etAddress = (EditText) findViewById(R.id.et_buysecondhouseaddress);
		etBusiness = (EditText) findViewById(R.id.et_buysecondhousebusiness);
		etCommunity = (EditText) findViewById(R.id.et_buysecondhousecommunity);
		etArea = (EditText) findViewById(R.id.et_buysecondhousearea);
		etSumprice = (EditText) findViewById(R.id.et_buysecondhousesumprice);
		etSumprice.setOnClickListener(this);
		etArea.setOnClickListener(this);
		etCommunity.setOnClickListener(this);
		etBusiness.setOnClickListener(this);
		etAddress.setOnClickListener(this);
		etClassify.setOnClickListener(this);
	}

	private void initTitlebar() {
		titleBar = findViewById(R.id.titleBar);
		findViewById(R.id.title_left).setOnClickListener(this);
		findViewById(R.id.title_right_tv).setOnClickListener(this);
		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("求购二手房");
		findViewById(R.id.tv_send).setOnClickListener(this);
	}

	private void showPopwindowClassify() {
		View view = getLayoutInflater().inflate(R.layout.buysecondhouse_popitem, null);
		ListView poplist = (ListView) view.findViewById(R.id.poplist);
		poplist.setAdapter(classifyAdapter);
		final PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyleX);
		popupWindow.showAsDropDown(titleBar);

		view.findViewById(R.id.tv_popclose).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		poplist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				etClassify.setText(houseTypeList.get(position).getTypeName());
				popupWindow.dismiss();

			}
		});
	}

	class PopAdapterClassify extends CommonAdapter<HouseTypeList> {

		public PopAdapterClassify(Context context, List<HouseTypeList> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(ViewHolder helper, HouseTypeList item, int position) {
			helper.setText(R.id.item_tv, item.getTypeName());
		}

	}

	private void showPopwindowAdress() {

		etBusiness.setText("");
		etCommunity.setText("");
		View view = getLayoutInflater().inflate(R.layout.buysecondhouse_popitem, null);
		ListView poplist = (ListView) view.findViewById(R.id.poplist);
		poplist.setAdapter(addressAdapter);
		final PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyleX);
		popupWindow.showAsDropDown(titleBar);

		view.findViewById(R.id.tv_popclose).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		poplist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				etAddress.setText(xiamenInitList.get(position).getAreaName());
				areaId = xiamenInitList.get(position).getAreaId();
				popupWindow.dismiss();
			}
		});
	}

	class PopAdapterAdress extends CommonAdapter<XiamenInitList> {

		public PopAdapterAdress(Context context, List<XiamenInitList> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(ViewHolder helper, XiamenInitList item, int position) {
			helper.setText(R.id.item_tv, item.getAreaName());
		}

	}

	private void showPopwindowBusiness() {
		etCommunity.setText("");
		View view = getLayoutInflater().inflate(R.layout.buysecondhouse_popitem, null);
		ListView poplist = (ListView) view.findViewById(R.id.poplist);
		poplist.setAdapter(businessAdapter);
		final PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyleX);
		popupWindow.showAsDropDown(titleBar);

		view.findViewById(R.id.tv_popclose).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		poplist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				etBusiness.setText(areaList.get(position).getBusinessListName());
				businessListId = areaList.get(position).getBusinessListId();
				popupWindow.dismiss();
			}
		});
	}

	class PopAdapterBusiness extends CommonAdapter<AreaList> {

		public PopAdapterBusiness(Context context, List<AreaList> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(ViewHolder helper, AreaList item, int position) {
			helper.setText(R.id.item_tv, item.getBusinessListName());
		}

	}

	private void showPopwindowCommunity() {
		View view = getLayoutInflater().inflate(R.layout.buysecondhouse_popitem, null);
		ListView poplist = (ListView) view.findViewById(R.id.poplist);
		poplist.setAdapter(communityAdapter);
		final PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyleX);
		popupWindow.showAsDropDown(titleBar);

		view.findViewById(R.id.tv_popclose).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		poplist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				etCommunity.setText(businessList.get(position).getAreaListName());
				popupWindow.dismiss();
			}
		});
	}

	class PopAdapterCommunity extends CommonAdapter<BusinessList> {

		public PopAdapterCommunity(Context context, List<BusinessList> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(ViewHolder helper, BusinessList item, int position) {
			helper.setText(R.id.item_tv, item.getAreaListName());
		}

	}

	private void showPopwindow() {
		View view = getLayoutInflater().inflate(R.layout.buysecondhouse_popitem, null);
		ListView poplist = (ListView) view.findViewById(R.id.poplist);
		View setPriceView = view.findViewById(R.id.ll_setprice);
		poplist.setAdapter(new PopAdapter(this, popListInfo, R.layout.item_textview));
		final PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyleX);

		// 设置弹出窗体需要软键盘
		popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
		// 设置模式，和Activity的一样，覆盖，调整大小。
		popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		popupWindow.showAsDropDown(titleBar);
		view.findViewById(R.id.tv_popclose).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		poplist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (flag == 0) {
					etArea.setText(popListInfo.get(position));
				} else {
					etSumprice.setText(popListInfo.get(position));
				}
				popupWindow.dismiss();
			}
		});
		if (flag == 1) {
			setPriceView.setVisibility(View.VISIBLE);
			final EditText etLowPrice = (EditText) setPriceView.findViewById(R.id.et_lowprice);
			final EditText etHighPrice = (EditText) setPriceView.findViewById(R.id.et_highprice);
			setPriceView.findViewById(R.id.bt_sure).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String lowPrice = etLowPrice.getText().toString();
					String highPrice = etHighPrice.getText().toString();
					if (TextUtils.isEmpty(lowPrice) && TextUtils.isEmpty(highPrice)) {
						if (TextUtils.isEmpty(highPrice)) {
							etSumprice.setText("不限");
							return;
						}
					}
					if (!TextUtils.isEmpty(lowPrice) && !TextUtils.isEmpty(highPrice)) {
						double lPrice = Double.parseDouble(lowPrice);
						double hPrice = Double.parseDouble(highPrice);
						if (lPrice > hPrice) {
							ToastUtil.toastShow(BuySecondHouseActivity.this, "最低价格不能高于最高价格");
							return;
						}
					}
					if (TextUtils.isEmpty(lowPrice)) {
						lowPrice = "0";
					}
					if (TextUtils.isEmpty(highPrice)) {
						highPrice = "-1";
					}

					int lowPrice1 = Integer.parseInt(lowPrice);
					int highPrice1 = Integer.parseInt(highPrice);
					String showString = null;
					if (lowPrice1 == 0) {
						showString = highPrice1 + "万以下";
					}
					if (highPrice1 == -1) {
						showString = lowPrice1 + "万以上";
					}
					if (TextUtils.isEmpty(showString)) {
						showString = lowPrice1 + "-" + highPrice1 + "万";
					}
					etSumprice.setText(showString);
					popupWindow.dismiss();
				}
			});
		} else {
			setPriceView.setVisibility(View.GONE);
		}

	}

	class PopAdapter extends CommonAdapter<String> {

		public PopAdapter(Context context, List<String> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			helper.setText(R.id.item_tv, item);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 获取验证码
		case R.id.btn_getMsg:
			if (AccountConfig.isLogin()) {
				String phone = etPhone.getText().toString();
				if (!phone.equals(AccountConfig.getAccountPhone())) {
					ToastUtil.toastShow(this, "号码不匹配,请重新输入");
					return;
				}
			}
			new GetVerificationUtil(this, etPhone, btnGetMsg, mAccountApi).getVerification("common");
			break;
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		// 类型
		case R.id.et_buysecondhouseclassify:
			params = new RequestParams();
			initData();
			showPopwindowClassify();
			break;
		// 区域
		case R.id.et_buysecondhouseaddress:
			params = new RequestParams();
			initData();
			showPopwindowAdress();
			break;
		// 商圈
		case R.id.et_buysecondhousebusiness:
			if (TextUtils.isEmpty(etAddress.getText().toString())) {
				ToastUtil.toastShow(this, "请先选择区域");
				return;
			}
			params = new RequestParams();
			params.put("areaId", areaId);
			initData();
			showPopwindowBusiness();
			break;
		// 搜索小区
		case R.id.et_buysecondhousecommunity:
			startActivityForResult(new Intent(this, SearchArealistActivity.class), 0);
			break;
		case R.id.et_buysecondhousearea:
			flag = 0;
			popListInfo.clear();
			String[] areaStrings = getResources().getStringArray(R.array.pop_area);
			for (int i = 0; i < areaStrings.length; i++) {
				popListInfo.add(areaStrings[i]);
			}
			showPopwindow();
			break;
		// 总价
		case R.id.et_buysecondhousesumprice:
			flag = 1;
			popListInfo.clear();
			String[] priceStrings = getResources().getStringArray(R.array.pop_sumprice);
			for (int i = 0; i < priceStrings.length; i++) {
				popListInfo.add(priceStrings[i]);
			}
			showPopwindow();
			break;
		// 发布
		case R.id.tv_send:
			commitDemand();
			break;
		default:
			break;
		}
	}

	private void commitDemand() {
		RequestParams params2 = new RequestParams();
		String name = etName.getText().toString();
		if (TextUtils.isEmpty(name)) {
			ToastUtil.toastShow(this, "请输入联系人姓名");
			return;
		}
		if (!CommonUtils.isCh_En(name) || name.length() > 20) {
			ToastUtil.toastShow(this, "请输入正确的格式姓名");
			return;
		}
		String phone = etPhone.getText().toString();
		if (TextUtils.isEmpty(phone)) {
			ToastUtil.toastShow(this, "请输入手机号码");
			return;
		}
		if (!CommonUtils.isMobileNoValid(phone)) {
			ToastUtil.toastShow(this, "请输入正确格式的手机号码");
			return;
		}
		if (AccountConfig.isLogin()) {
			if (!phone.equals(AccountConfig.getAccountPhone())) {
				ToastUtil.toastShow(this, "号码不匹配,请重新输入");
				return;
			}
		}
		String msg = etCode.getText().toString();
		if (TextUtils.isEmpty(msg)) {
			ToastUtil.toastShow(this, "请输入验证码");
			return;
		}
		String classify = etClassify.getText().toString();
		if (TextUtils.isEmpty(classify)) {
			ToastUtil.toastShow(this, "请选择类型");
			return;
		}
		String area = etAddress.getText().toString();
		if (TextUtils.isEmpty(area)) {
			ToastUtil.toastShow(this, "请选择区域");
			return;
		}
		String business = etBusiness.getText().toString();
		if (TextUtils.isEmpty(business)) {
			ToastUtil.toastShow(this, "请选择商圈");
			return;
		}
		params2.put("userName", name);
		params2.put("telNum", phone);
		params2.put("checkCode", msg);
		params2.put("houseType", etClassify.getText().toString());
		params2.put("intetionType", "二手房求购");
		params2.put("areaName", area);
		params2.put("businessName", business);
		params2.put("areaNum", etArea.getText().toString());
		params2.put("totalPrice", etSumprice.getText().toString());
		params2.put("roomNum", etRoomNum.getText().toString());
		params2.put("hallNum", etHallNum.getText().toString());
		params2.put("toiletNum", etToiletNum.getText().toString());
		params2.put("areaListValue", etCommunity.getText().toString());
		SendDemandApi.getInstance().requestSendBuyWantRentDemand(this, params2, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				startActivity(new Intent(BuySecondHouseActivity.this, MyDemandActivity.class));
				ToastUtil.toastShow(BuySecondHouseActivity.this, msg);
				if (!AccountConfig.isLogin()) {
					LoginEntity info = (LoginEntity) object;
					if (info.getContent() != null) {
						AccountConfig.saveLoginData(true, info.getContent().getPublicUser().getAccountId(), null, 0,
								info.getContent().getPublicUser().getAddress(),
								info.getContent().getPublicUser().getNickName(), -1,
								info.getContent().getPublicUser().getMobile(), null, 0, null, 0, null);

						// AppLog.redLog("233", alias);
					}
				}
				finish();
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(BuySecondHouseActivity.this, R.string.net_error);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(BuySecondHouseActivity.this, msg);

			}
		});

	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent data) {
		super.onActivityResult(arg0, arg1, data);
		if (data != null) {
			if (arg0 == 0) {
				String areaListName = data.getStringExtra("areaListName");
				String areaName = data.getStringExtra("areaName");
				String businessListName = data.getStringExtra("businessListName");
				areaId = data.getIntExtra("areaId", -1);
				etAddress.setText(areaName);
				etBusiness.setText(businessListName);
				etCommunity.setText(areaListName);
			}
		}
	}

}
