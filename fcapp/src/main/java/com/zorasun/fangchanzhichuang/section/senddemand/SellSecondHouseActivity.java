package com.zorasun.fangchanzhichuang.section.senddemand;

import java.util.ArrayList;
import java.util.List;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class SellSecondHouseActivity extends BaseActivity implements OnClickListener, OnTouchListener {

	private View titleBar;
	private RequestParams params;
	private List<HouseTypeList> houseTypeList = new ArrayList<>();
	private List<XiamenInitList> xiamenInitList = new ArrayList<>();
	private List<BusinessList> businessList = new ArrayList<>();
	private List<AreaList> areaList = new ArrayList<>();
	private List<String> popListInfo = new ArrayList<>();
	private int areaId;
	private int businessListId;
	private EditText etClassify;
	private EditText etAddress;
	private EditText etBusiness;
	private EditText etCommunity;
	private PopAdapterBusiness businessAdapter;
	private int flag;
	private EditText etArea;
	private EditText etPrice;
	private EditText etName;
	private EditText etPhone;
	private EditText etCode;
	private int intExtra;
	private View llMianJi;
	private TimerTextView btnGetMsg;
	private AccountApi mAccountApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sell_second_house);
		mAccountApi = new AccountApi();
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

	private void commitDemand() {
		String name = etName.getText().toString();
		String phone = etPhone.getText().toString();
		String code = etCode.getText().toString();
		String community = etCommunity.getText().toString();
		String classify = etClassify.getText().toString();
		// if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) ||
		// TextUtils.isEmpty(code)
		// || TextUtils.isEmpty(community)) {
		// ToastUtil.toastShow(SellSecondHouseActivity.this, "请将信息补充完整");
		// return;
		// }
		if (TextUtils.isEmpty(name)) {
			ToastUtil.toastShow(this, "请输入联系人姓名");
			return;
		}
		if (!CommonUtils.isCh_En(name) || name.length() > 20) {
			ToastUtil.toastShow(this, "请输入正确的格式姓名");
			return;
		}
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
		if (TextUtils.isEmpty(code)) {
			ToastUtil.toastShow(this, "请输入验证码");
			return;
		}
		if (TextUtils.isEmpty(classify)) {
			ToastUtil.toastShow(this, "请选择类型");
			return;
		}
		if (TextUtils.isEmpty(community)) {
			ToastUtil.toastShow(this, "请选择小区");
			return;
		}

		RequestParams params2 = new RequestParams();
		params2.put("userName", name);
		params2.put("telNum", phone);
		params2.put("checkCode", code);
		params2.put("houseType", etClassify.getText().toString());
		if (intExtra == SystemConstant.CHUZU_INTENT) {
			params2.put("intetionType", "出租");
			if (!TextUtils.isEmpty(etPrice.getText().toString())) {
				params2.put("totalPrice", etPrice.getText().toString() + "元/月");
			}
		} else {
			params2.put("intetionType", "二手房出售");
			if (!TextUtils.isEmpty(etPrice.getText().toString())) {
				params2.put("totalPrice", etPrice.getText().toString() + "万");
			}
			params2.put("areaNum", etArea.getText().toString());
		}
		// params2.put("areaName", etAddress.getText().toString());
		// params2.put("businessName", etBusiness.getText().toString());
		params2.put("areaListValue", community);
		SendDemandApi.getInstance().requestSendSellRentDemand(this, params2, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				startActivity(new Intent(SellSecondHouseActivity.this, MyDemandActivity.class));
				ToastUtil.toastShow(SellSecondHouseActivity.this, msg);
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
				ToastUtil.toastShow(SellSecondHouseActivity.this, R.string.net_error);

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(SellSecondHouseActivity.this, msg);

			}
		});
	}

	private void initView() {
		initTitlebar();
		findViewById(R.id.rl_sellsecondhouseclassify);
		// findViewById(R.id.ll_selladdress).setOnClickListener(this);
		// findViewById(R.id.ll_sellbusiness).setOnClickListener(this);
		findViewById(R.id.ll_sellsecondhousecommunity);
		llMianJi = findViewById(R.id.ll_sellsecondhousearea);
		btnGetMsg = (TimerTextView) findViewById(R.id.btn_getMsg);
		btnGetMsg.setOnClickListener(this);
		TextView tvSellSecondHouseName = (TextView) findViewById(R.id.tv_sellsecondhousename);
		TextView tvSellSecondHousePhone = (TextView) findViewById(R.id.tv_sellsecondhousephone);
		TextView tvSellSecondHouseGetcode = (TextView) findViewById(R.id.tv_sellsecondhousepgetcode);
		TextView tvSellSecondHouseCommunity = (TextView) findViewById(R.id.tv_sellsecondhousecommunity);
		TextView tvSellSecondHouseClassify = (TextView) findViewById(R.id.tv_sellsecondhouseclassify);
		SpannableString ss = new SpannableString(tvSellSecondHouseName.getText().toString());
		ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvSellSecondHouseName.setText(ss);
		SpannableString ss1 = new SpannableString(tvSellSecondHousePhone.getText().toString());
		ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvSellSecondHousePhone.setText(ss1);
		SpannableString ss2 = new SpannableString(tvSellSecondHouseGetcode.getText().toString());
		ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvSellSecondHouseGetcode.setText(ss2);
		SpannableString ss3 = new SpannableString(tvSellSecondHouseCommunity.getText().toString());
		ss3.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvSellSecondHouseCommunity.setText(ss3);
		SpannableString ss4 = new SpannableString(tvSellSecondHouseClassify.getText().toString());
		ss4.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvSellSecondHouseClassify.setText(ss4);

		etName = (EditText) findViewById(R.id.et_sellsecondhousename);
		etPhone = (EditText) findViewById(R.id.et_sellsecondhousephone);
		etCode = (EditText) findViewById(R.id.et_sellsecondhousegetcode);
		etClassify = (EditText) findViewById(R.id.et_sellsecondclassify);
		// etAddress = (EditText) findViewById(R.id.et_sellsecondaddress);
		// etBusiness = (EditText) findViewById(R.id.et_sellsecondbusiness);
		etCommunity = (EditText) findViewById(R.id.et_sellsecondcommunity);
		etArea = (EditText) findViewById(R.id.et_sellsecondarea);
		etPrice = (EditText) findViewById(R.id.et_sellsecondhouseprice);
		etPrice.setOnTouchListener(this);
		TextView tvDanWei = (TextView) findViewById(R.id.tv_wanyuan);
		etArea.setOnClickListener(this);
		etCommunity.setOnClickListener(this);
		etClassify.setOnClickListener(this);
		if (intExtra == SystemConstant.CHUZU_INTENT) {
			etPrice.setHint("期望租金");
			tvDanWei.setText("元/月");
			llMianJi.setVisibility(View.GONE);
		} else {
			etPrice.setHint("期望价格");
			llMianJi.setVisibility(View.VISIBLE);
			tvDanWei.setText("万");
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.et_sellsecondhouseprice:
			// 解决scrollView中嵌套EditText导致不能上下滑动的问题
			v.getParent().requestDisallowInterceptTouchEvent(true);
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_UP:
				v.getParent().requestDisallowInterceptTouchEvent(false);
				break;
			}
		}
		return false;
	}

	private void initTitlebar() {
		TextView title_name = (TextView) findViewById(R.id.title_name);
		intExtra = getIntent().getIntExtra("wantRend", -1);
		if (intExtra == SystemConstant.CHUZU_INTENT) {
			title_name.setText("出租");
		} else {
			title_name.setText("出售二手房");
		}
		titleBar = findViewById(R.id.titleBar);
		findViewById(R.id.title_left).setOnClickListener(this);
		findViewById(R.id.tv_send).setOnClickListener(this);
		// title_right_tv.setVisibility(View.VISIBLE);
		// title_right_tv.setText("发布");
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
				if (flag == 0) {
					etArea.setText(popListInfo.get(position));
				} else {
					etPrice.setText(popListInfo.get(position));
				}
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
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
		// 类型
		case R.id.et_sellsecondclassify:
			params = new RequestParams();
			initData();
			showPopwindowClassify();
			// break;
			// // 区域
			// case R.id.ll_selladdress:
			// params = new RequestParams();
			// initData();
			// showPopwindowAdress();
			// break;
			// // 商圈
			// case R.id.ll_sellbusiness:
			// params = new RequestParams();
			// params.put("areaId", areaId);
			// initData();
			// showPopwindowBusiness();
			// break;
			// 小区
			break;
		case R.id.et_sellsecondcommunity:
			params = new RequestParams();
			// params.put("businessListId", businessListId);

			// initData();
			// showPopwindowCommunity();
			startActivityForResult(new Intent(this, SearchArealistActivity.class), 0);
			// startActivity(new Intent(this, SearchArealistActivity.class));
			break;
		// 面积
		case R.id.et_sellsecondarea:
			flag = 0;
			popListInfo.clear();
			String[] areaStrings = getResources().getStringArray(R.array.pop_area);
			for (int i = 0; i < areaStrings.length; i++) {
				popListInfo.add(areaStrings[i]);
			}
			showPopwindow();
			break;
		default:
			break;
		}
	}

	private void showPopwindowClassify() {
		View view = getLayoutInflater().inflate(R.layout.buysecondhouse_popitem, null);
		ListView poplist = (ListView) view.findViewById(R.id.poplist);

		poplist.setAdapter(new PopAdapterClassify(this, houseTypeList, R.layout.item_textview));
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

		poplist.setAdapter(new PopAdapterAdress(this, xiamenInitList, R.layout.item_textview));
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
		businessAdapter = new PopAdapterBusiness(this, areaList, R.layout.item_textview);
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
		poplist.setAdapter(new PopAdapterCommunity(this, businessList, R.layout.item_textview));
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
	protected void onActivityResult(int arg0, int arg1, Intent data) {
		super.onActivityResult(arg0, arg1, data);
		if (data != null) {
			if (arg0 == 0) {
				String areaListName = data.getStringExtra("areaListName");
				etCommunity.setText(areaListName);
			}
		}
	}

}
