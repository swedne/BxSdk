package com.zorasun.fangchanzhichuang.section.index;

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
import com.zorasun.fangchanzhichuang.section.senddemand.SendDemandApi;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.XiaMenAreaListEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.XiaMenAreaListEntity.HouseTypeList;

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

public class QiuGouXinFangActivity extends BaseActivity implements OnClickListener {

	private List<HouseTypeList> houseTypeList = new ArrayList<>();
	private View titleBar;
	private EditText etClassify;
	private ArrayList<String> areaList = new ArrayList<>();
	private EditText etArea;
	private EditText etCode;
	private EditText etPhone;
	private EditText etName;
	private EditText etPrice;
	private TimerTextView btnGetMsg;
	private AccountApi mAccountApi;
	private PopAdapterClassify classifyAdapter;
	private PopAdapter areaAdapter;
	private int newhouseId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qiu_gou_xin_fang);
		newhouseId = getIntent().getIntExtra("newhouseId", -1);
		mAccountApi = new AccountApi();
		String[] areaStrings = getResources().getStringArray(R.array.pop_area);
		for (int i = 0; i < areaStrings.length; i++) {
			areaList.add(areaStrings[i]);
		}
		classifyAdapter = new PopAdapterClassify(this, houseTypeList, R.layout.item_textview);
		areaAdapter = new PopAdapter(this, areaList, R.layout.item_textview);

		initView();
		initData();
	}

	private void initView() {
		titleBar = findViewById(R.id.titleBar);
		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("求购新房");
		findViewById(R.id.tv_send).setOnClickListener(this);
		etClassify = (EditText) findViewById(R.id.et_buynewhouseclassify);
		etClassify.setOnClickListener(this);
		etArea = (EditText) findViewById(R.id.et_buynewhousearea);
		etArea.setOnClickListener(this);
		etName = (EditText) findViewById(R.id.et_buynewehousename);
		etPhone = (EditText) findViewById(R.id.et_buynewhousephone);
		etCode = (EditText) findViewById(R.id.et_buynewhousecode);
		etPrice = (EditText) findViewById(R.id.et_buynewhouseprice);

		TextView tvSellSecondHouseName = (TextView) findViewById(R.id.tv_buynewhousename);
		TextView tvSellSecondHousePhone = (TextView) findViewById(R.id.tv_buynewhousephone);
		TextView tvSellSecondHouseGetcode = (TextView) findViewById(R.id.tv_buynewehousegetcode);
		SpannableString ss = new SpannableString(tvSellSecondHouseName.getText().toString());
		ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvSellSecondHouseName.setText(ss);
		SpannableString ss1 = new SpannableString(tvSellSecondHousePhone.getText().toString());
		ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvSellSecondHousePhone.setText(ss1);
		SpannableString ss2 = new SpannableString(tvSellSecondHouseGetcode.getText().toString());
		ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvSellSecondHouseGetcode.setText(ss2);
		btnGetMsg = (TimerTextView) findViewById(R.id.btn_getMsg);
		btnGetMsg.setOnClickListener(this);
	}

	private void initData() {
		RequestParams params = new RequestParams();
		SendDemandApi.getInstance().requestXiaMenInfo(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				XiaMenAreaListEntity xiaMenAreaListEntity = (XiaMenAreaListEntity) object;
				houseTypeList.clear();
				List<HouseTypeList> houseTypeList2 = xiaMenAreaListEntity.getContent().getHouseTypeList();
				if (houseTypeList2 != null) {
					houseTypeList.addAll(houseTypeList2);
				}
				classifyAdapter.notifyDataSetChanged();

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

	private void showPopwindow() {
		View view = getLayoutInflater().inflate(R.layout.buysecondhouse_popitem, null);
		ListView poplist = (ListView) view.findViewById(R.id.poplist);
		poplist.setAdapter(areaAdapter);
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
				etArea.setText(areaList.get(position));
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
		String phone = etPhone.getText().toString();
		String code = etCode.getText().toString();
		if (TextUtils.isEmpty(name)) {
			ToastUtil.toastShow(this, "请输入姓名");
			return;
		}
		if (!CommonUtils.isCh_En(name) || name.length() > 20) {
			ToastUtil.toastShow(this, "请输入正确的格式姓名");
			return;
		}
		if (TextUtils.isEmpty(phone)) {
			ToastUtil.toastShow(this, "请输入您的手机号码");
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
		params2.put("userName", name);
		params2.put("mobile", phone);
		params2.put("code", code);
		params2.put("houseType", etClassify.getText().toString());
		params2.put("intetionType", "一手房求购");
		params2.put("areaNum", etArea.getText().toString());
		if (!TextUtils.isEmpty(etPrice.getText().toString())) {
			params2.put("totalPrice", etPrice.getText().toString() + "万");
		}
		params2.put("newhouseId", newhouseId);

		SendDemandApi.getInstance().requestQiuGouNewDemand(this, params2, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ToastUtil.toastShow(QiuGouXinFangActivity.this, msg);
				startActivity(new Intent(QiuGouXinFangActivity.this, MyDemandActivity.class));
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
				ToastUtil.toastShow(QiuGouXinFangActivity.this, R.string.net_error);

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(QiuGouXinFangActivity.this, msg);

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
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
		case R.id.et_buynewhouseclassify:
			showPopwindowClassify();
			break;
		case R.id.et_buynewhousearea:
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
}
