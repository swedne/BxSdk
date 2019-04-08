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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class WantRentActivity extends BaseActivity implements OnClickListener {

	private View titleBar;
	private EditText etPhone;
	private List<HouseTypeList> houseTypeList = new ArrayList<>();
	private List<XiamenInitList> xiamenInitList = new ArrayList<>();
	private List<BusinessList> businessList = new ArrayList<>();
	private List<AreaList> areaList = new ArrayList<>();
	private List<String> popListInfo = new ArrayList<>();
	private RequestParams params;
	private EditText etClassify;
	private EditText etAdress;
	private EditText etCommunity;
	private EditText etBusiness;
	private EditText etPrice;
	private int areaId = -1;
	private int businessListId = -1;
	private PopAdapterBusiness businessAdapter;
	private PopAdapterCommunity communityAdapter;
	private EditText etName;
	private EditText etCode;
	private TimerTextView btnGetMsg;
	private AccountApi mAccountApi;
	private PopAdapterClassify classifyAdapter;
	private PopAdapterAdress addressAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wantrent_second_house);
		mAccountApi = new AccountApi();
		classifyAdapter = new PopAdapterClassify(this, houseTypeList, R.layout.item_textview);
		businessAdapter = new PopAdapterBusiness(this, areaList, R.layout.item_textview);
		addressAdapter = new PopAdapterAdress(this, xiamenInitList, R.layout.item_textview);

		initTitlebar();
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
				if (communityAdapter != null) {
					communityAdapter.notifyDataSetChanged();
				}

				businessAdapter.notifyDataSetChanged();
				classifyAdapter.notifyDataSetChanged();
				addressAdapter.notifyDataSetChanged();

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

	private void initView() {
		etCode = (EditText) findViewById(R.id.et_wantrentgetcode);
		etName = (EditText) findViewById(R.id.et_wantrentname);
		etPhone = (EditText) findViewById(R.id.et_wantrentphone);
		etClassify = (EditText) findViewById(R.id.et_wantrentclassify);
		etAdress = (EditText) findViewById(R.id.et_wantrentaddress);
		etCommunity = (EditText) findViewById(R.id.et_wantrentcommunity);
		etBusiness = (EditText) findViewById(R.id.et_wantrentbusiness);
		etPrice = (EditText) findViewById(R.id.et_wantrentprice);
		btnGetMsg = (TimerTextView) findViewById(R.id.btn_getMsg);
		btnGetMsg.setOnClickListener(this);
		findViewById(R.id.rl_wantrentclassify);
		findViewById(R.id.rl_wantrentaddress);
		findViewById(R.id.ll_wantrentcommunity);
		findViewById(R.id.rl_wantrentbusiness);
		findViewById(R.id.rl_wantrentprice);
		etClassify.setOnClickListener(this);
		etAdress.setOnClickListener(this);
		etCommunity.setOnClickListener(this);
		etBusiness.setOnClickListener(this);
		etPrice.setOnClickListener(this);
		titleBar = findViewById(R.id.titleBar);
		TextView tvConnectPerson = (TextView) findViewById(R.id.tv_buysecondhousename);
		TextView tvWantrentphone = (TextView) findViewById(R.id.tv_wantrentphone);
		TextView tvWantrentgetcode = (TextView) findViewById(R.id.tv_wantrentgetcode);
		TextView tvWantrentaddress = (TextView) findViewById(R.id.tv_wantrentaddress);
		TextView tv_wantrentclassify = (TextView) findViewById(R.id.tv_wantrentclassify);
		TextView tvWantrentbusiness = (TextView) findViewById(R.id.tv_wantrentbusiness);
		SpannableString ss = new SpannableString(tvConnectPerson.getText().toString());
		ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvConnectPerson.setText(ss);
		SpannableString ss1 = new SpannableString(tvWantrentphone.getText().toString());
		ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWantrentphone.setText(ss1);
		SpannableString ss2 = new SpannableString(tvWantrentgetcode.getText().toString());
		ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWantrentgetcode.setText(ss2);
		SpannableString ss3 = new SpannableString(tvWantrentaddress.getText().toString());
		ss3.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWantrentaddress.setText(ss3);
		SpannableString ss4 = new SpannableString(tv_wantrentclassify.getText().toString());
		ss4.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv_wantrentclassify.setText(ss4);
		SpannableString ss5 = new SpannableString(tvWantrentbusiness.getText().toString());
		ss5.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvWantrentbusiness.setText(ss5);
	}

	private void initTitlebar() {
		findViewById(R.id.title_left).setOnClickListener(this);
		findViewById(R.id.title_right_tv).setOnClickListener(this);
		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("求租");
		findViewById(R.id.tv_send).setOnClickListener(this);
		// title_right_tv.setVisibility(View.VISIBLE);
		// title_right_tv.setText("发布");

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
				etAdress.setText(xiamenInitList.get(position).getAreaName());
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
		communityAdapter = new PopAdapterCommunity(this, businessList, R.layout.item_textview);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		// 类型
		case R.id.et_wantrentclassify:
			params = new RequestParams();
			initData();
			showPopwindowClassify();
			break;
		// 区域
		case R.id.et_wantrentaddress:
			params = new RequestParams();
			initData();
			showPopwindowAdress();
			break;
		// 小区
		case R.id.et_wantrentcommunity:
			startActivityForResult(new Intent(this, SearchArealistActivity.class), 0);
			// params = new RequestParams();
			// params.put("businessListId", businessListId);
			// initData();
			// showPopwindowCommunity();
			break;
		// 商圈
		case R.id.et_wantrentbusiness:
			if (TextUtils.isEmpty(etAdress.getText().toString())) {
				ToastUtil.toastShow(this, "请先选择区域");
				return;
			}
			params = new RequestParams();
			params.put("areaId", areaId);
			initData();
			showPopwindowBusiness();
			break;
		// 价格
		case R.id.et_wantrentprice:
			popListInfo.clear();
			String[] priceStrings = getResources().getStringArray(R.array.pop_wantprice);
			for (int i = 0; i < priceStrings.length; i++) {
				popListInfo.add(priceStrings[i]);
			}
			showPopwindow();
			break;
		// 获得验证码
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
		// 发布
		case R.id.tv_send:
			commitDemand();
			break;
		default:
			break;
		}
	}

	private void showPopwindow() {
		View view = getLayoutInflater().inflate(R.layout.buysecondhouse_popitem, null);
		ListView poplist = (ListView) view.findViewById(R.id.poplist);
		poplist.setAdapter(new PopAdapter(this, popListInfo, R.layout.item_textview));
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
				etPrice.setText(popListInfo.get(position));
				popupWindow.dismiss();
			}
		});
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
		String areaName = etAdress.getText().toString();
		if (TextUtils.isEmpty(areaName)) {
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
		params2.put("houseType", classify);
		params2.put("intetionType", "求租");
		params2.put("areaName", areaName);
		params2.put("businessName", business);
		params2.put("totalPrice", etPrice.getText().toString());
		params2.put("areaListValue", etCommunity.getText().toString());
		SendDemandApi.getInstance().requestSendBuyWantRentDemand(this, params2, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				startActivity(new Intent(WantRentActivity.this, MyDemandActivity.class));
				ToastUtil.toastShow(WantRentActivity.this, msg);
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
				ToastUtil.toastShow(WantRentActivity.this, R.string.net_error);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(WantRentActivity.this, msg);

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
				etAdress.setText(areaName);
				etBusiness.setText(businessListName);
				etCommunity.setText(areaListName);
			}
		}
	}

}
