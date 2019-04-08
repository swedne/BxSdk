package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.commonadapter.Common2Adapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.CommonAdapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.ViewHolder;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseListEntity.AreaList;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseListEntity.Content;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseListEntity.NewHouseList_;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseListEntity.SpecialsList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ScrollView;
import android.widget.TextView;

public class XinFangActivity extends BaseActivity
		implements OnItemClickListener, OnClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private TextView tvQuYu;
	private TextView tvJunJia;
	private TextView tvTeSe;
	private int poptab;
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<String> popList1 = new ArrayList<>();
	private ArrayList<String> popList2 = new ArrayList<>();
	private ArrayList<String> popList3 = new ArrayList<>();
	private View viewBottom;
	private int select1;
	private int select2;
	private int select3;
	private List<NewHouseList_> newHouseList = new ArrayList<>();
	private MyAdapter myAdapter;
	private RequestParams params;
	private Content content;
	private ListView mListView;
	private List<AreaList> areaList = new ArrayList<>();
	private HashMap<Integer, Integer> hasMap = new HashMap<>();
	private ArrayList<Integer> intList = new ArrayList<>();
	private PullToRefreshListView ptrListView;
	protected int pageSize;
	private CustomView customview;
	private int page = 1;
	private boolean isRefresh;
	private int pageCount;
	private int popIndex = -1;
	private PopupWindow popupWindow;
	private String averagePriceRange;
	private String newHouseSpecialName;
	private LocationClient locationClient;
	protected int businessListId = -1;
	private double latitude = -1;
	private double longitude = -1;
	private int type = -1;
	private int indexFlag;
	protected String paiXu;
	private int orderBy = -1;
	private String paiXuKey;
	private String likeSelect;
	private View searchEmpty;
	private Drawable rightDrawableSelect;
	private Drawable rightDrawableNormal;
	private Drawable rightDrawableUp;
	private EditText etSearch;
	private View delete;
	private int areaId = -1;
	private HashMap<String, Integer> hasMapAreaId = new HashMap<>();
	public int index = -1;
	private HashMap<Integer, Integer> hasRecord = new HashMap<>();
	private boolean searchBusiness = true;
	private int height;
	private int first = -1;
	private int third = -3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xinfang);
		hasRecord.put(0, -1);
		hasRecord.put(1, -1);
		hasRecord.put(2, -1);
		hasRecord.put(3, -1);
		hasRecord.put(first, -1);
		hasRecord.put(third, -1);

		list.add("更新时间");
		list.add("价格由低到高");
		list.add("价格由高到低");
		list.add("发布时间");

		initView();
		initData();
	}

	private void initData() {
		searchEmpty.setVisibility(View.GONE);
		selectParams();
		IndexApi.getInstance().requestNewHouseList(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				NewHouseListEntity newHouseListEntity = (NewHouseListEntity) object;
				if (isRefresh) {
					newHouseList.clear();
					areaList.clear();
				}
				// hasMapSpecialId.clear();
				content = newHouseListEntity.getContent();
				pageCount = content.getPageCount();
				if (pageCount <= page) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}
				newHouseList.addAll(content.getNewHouseList());

				if (newHouseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
					ToastUtil.toastShow(XinFangActivity.this, "无满足条件的房源信息");
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				// if (newHouseList.isEmpty()
				// && ((!TextUtils.isEmpty(likeSelect)) ||
				// !TextUtils.isEmpty(newHouseSpecialName))) {
				// // searchEmpty.setVisibility(View.VISIBLE);
				// ToastUtil.toastShow(XinFangActivity.this, "无满足条件的房源信息");
				// } else {
				// searchEmpty.setVisibility(View.GONE);
				// }

				areaList.addAll(content.getAreaList());

				for (int i = 0; i < areaList.size(); i++) {
					if (!hasMapAreaId.containsKey(areaList.get(i).getAreaName())) {
						hasMapAreaId.put(areaList.get(i).getAreaName(), areaList.get(i).getAreaId());
					}
				}

				myAdapter.notifyDataSetChanged();
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
				if (locationClient != null) {
					locationClient.stop();
				}

			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(XinFangActivity.this, getResources().getString(R.string.net_error));
				customview.showLoadStateView(CustomView.STATE_ERROR);
				ptrListView.postDelayed(new Runnable() {

					@Override
					public void run() {
						ptrListView.onRefreshComplete();
					}
				}, 1000);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(XinFangActivity.this, msg);
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});
	}

	private void initView() {
		searchEmpty = findViewById(R.id.search_empty);
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		viewBottom = findViewById(R.id.topline);
		etSearch = (EditText) findViewById(R.id.et_title_Search);
		etSearch.setOnClickListener(this);
		etSearch.setHint(R.string.secondhouseseach);
		ImageView title_right_img = (ImageView) findViewById(R.id.title_right_iv);
		title_right_img.setImageResource(R.drawable.ditu_white);
		title_right_img.setOnClickListener(this);

		tvQuYu = (TextView) findViewById(R.id.tv_quyu);
		tvJunJia = (TextView) findViewById(R.id.tv_junjia);
		// tvMianJi = (TextView) findViewById(R.id.tv_mianji);
		tvTeSe = (TextView) findViewById(R.id.tv_tese);
		tvQuYu.setOnClickListener(this);
		tvJunJia.setOnClickListener(this);
		// tvMianJi.setOnClickListener(this);
		tvTeSe.setOnClickListener(this);

		findViewById(R.id.title_left).setOnClickListener(this);

		myAdapter = new MyAdapter(this, newHouseList, R.layout.index_zufang_item);
		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);
		mListView = ptrListView.getRefreshableView();
		mListView.setAdapter(myAdapter);
		mListView.setOnItemClickListener(this);
		findViewById(R.id.img_paixu).setOnClickListener(this);
		delete = findViewById(R.id.img_delete);
		delete.setOnClickListener(this);

		rightDrawableSelect = getResources().getDrawable(R.drawable.sanjiao_p);
		rightDrawableSelect.setBounds(0, 0, rightDrawableSelect.getMinimumWidth(),
				rightDrawableSelect.getMinimumHeight());
		rightDrawableNormal = getResources().getDrawable(R.drawable.sanjiao_n);
		rightDrawableNormal.setBounds(0, 0, rightDrawableNormal.getMinimumWidth(),
				rightDrawableNormal.getMinimumHeight());
		rightDrawableUp = getResources().getDrawable(R.drawable.sanjiao_p_up);
		rightDrawableUp.setBounds(0, 0, rightDrawableNormal.getMinimumWidth(), rightDrawableUp.getMinimumHeight());

	}

	class MyAdapter extends CommonAdapter<NewHouseList_> {

		public MyAdapter(Context context, List<NewHouseList_> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(ViewHolder helper, NewHouseList_ item, int position) {
			LinearLayout llSpecial = (LinearLayout) helper.getView(R.id.ll_special);
			TextView tvTitle = (TextView) helper.getView(R.id.tv_title);
			TextView tvBusinessName = (TextView) helper.getView(R.id.tv_areaListName);
			TextView tvAverage = (TextView) helper.getView(R.id.tv_rentMoney);
			TextView tvUnit = (TextView) helper.getView(R.id.tv_unit);
			ImageView imgNewhousePic = (ImageView) helper.getView(R.id.img_newhousepic);
			AsyncImageLoader.setAsynImages(imgNewhousePic, item.getUrl());
			tvTitle.setText(item.getNewhouseName());
			tvBusinessName.setText(item.getBusinessName());
			tvAverage.setText(item.getAverage() + "");
			tvUnit.setText("元/㎡");
			List<SpecialsList> specialsList = item.getSpecialsList();
			String color = "";
			llSpecial.removeAllViews();
			if (specialsList.size() > 0) {
				for (int i = 0; i < specialsList.size(); i++) {
					View child = getLayoutInflater().inflate(R.layout.item_text, null);
					TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
					if (i == 0) {
						tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
						color = "#FD641D";
					} else if (i == 1) {
						tvHouseTag.setBackgroundResource(R.drawable.shape_text_purple);
						color = "#4970E1";
					} else {
						tvHouseTag.setBackgroundResource(R.drawable.shape_text_green);
						color = "#20B648";
					}
					tvHouseTag.setTextColor(Color.parseColor(color));
					tvHouseTag.setText(specialsList.get(i).getSpecialName());
					llSpecial.addView(child);
				}
			} else {
				View child = getLayoutInflater().inflate(R.layout.item_text, null);
				TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
				tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
				tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
				tvHouseTag.setText("暂无");
				llSpecial.addView(child);
			}
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		position -= mListView.getHeaderViewsCount();
		Intent intent = new Intent(this, XinFangxqActivity.class);
		intent.putExtra("NewhouseId", newHouseList.get(position).getNewhouseId());
		startActivity(intent);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_quyu:
			poptab = 0;
			showPopWindow();
			break;
		case R.id.tv_junjia:
			poptab = 1;
			showPopWindow();
			break;
		case R.id.tv_mianji:
			poptab = 2;
			showPopWindow();
			break;
		case R.id.tv_tese:
			poptab = 2;
			showPopWindow();
			break;
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		// 跳转到搜索界面
		case R.id.et_title_Search:
			Intent newhouseIntent = new Intent(this, IndexSerachActivity.class);
			newhouseIntent.putExtra("stageNum", SystemConstant.STATE_PAGE_NEWHOUSE);
			startActivityForResult(newhouseIntent, SystemConstant.STATE_PAGE_NEWHOUSE);
			break;
		// 打开地图
		case R.id.title_right_iv:
			ToastUtil.toastShow(this,"暂未开放");
//			Intent intent = new Intent(this, DiTuZhaoFangActivity.class);
//			intent.putExtra("selectTypeId", 3);
//			intent.putExtra("classify", 1);
//			startActivity(intent);
			break;
		// 排序
		case R.id.img_paixu:
			showPaiXuWindow();
			break;
		case R.id.img_delete:
			likeSelect = null;
			newHouseSpecialName = null;
			etSearch.setText("");
			delete.setVisibility(View.GONE);
			newHouseList.clear();
			areaList.clear();
			page = 1;
			mListView.setSelection(1);
			myAdapter.notifyDataSetChanged();
			initData();
			break;
		default:
			break;
		}
	}

	public void selectParams() {
		params = new RequestParams();
		if (params != null) {
			params.put("pageNum", page);

			if (businessListId != -1) {
				params.put("businessListId", businessListId);
			}
			if (latitude != -1) {
				params.put("latitude", latitude);
			}
			if (longitude != -1) {
				params.put("longitude", longitude);
			}
			if (type != -1) {
				params.put("type", type);
			}
			if (!TextUtils.isEmpty(averagePriceRange)) {
				params.put("averagePriceRange", averagePriceRange);
			}
			if (!TextUtils.isEmpty(newHouseSpecialName)) {
				params.put("newHouseSpecialName", newHouseSpecialName);
			}
			if (!TextUtils.isEmpty(paiXu)) {
				params.put(paiXuKey, paiXu);
			}
			if (orderBy != -1) {
				params.put("orderBy", orderBy);
			}
			if (!TextUtils.isEmpty(likeSelect)) {
				params.put("likeSelect", likeSelect);
			}
			if (areaId != -1) {
				params.put("areaId", areaId);
			}
		}
	}

	public void cancleParams() {
		searchBusiness = true;
		hasRecord.put(0, -1);
		hasRecord.put(1, -1);
		hasRecord.put(2, -1);
		hasRecord.put(3, -1);
		hasRecord.put(first, -1);
		hasRecord.put(third, -1);
		page = 1;
		areaId = -1;
		businessListId = -1;
		latitude = -1;
		longitude = -1;
		type = -1;
		averagePriceRange = null;
		newHouseSpecialName = null;
		paiXu = null;
		orderBy = -1;
		likeSelect = null;
		setNormalState();
	}

	public void setNormalState() {
		// 默认字体的颜色和图片
		tvQuYu.setTextColor(Color.parseColor("#919191"));
		tvJunJia.setTextColor(Color.parseColor("#919191"));
		tvTeSe.setTextColor(Color.parseColor("#919191"));

		tvQuYu.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvJunJia.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvTeSe.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvQuYu.setText("区域");
		tvJunJia.setText("均价");
		tvTeSe.setText("特色");
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent data) {
		super.onActivityResult(arg0, arg1, data);
		if (data != null) {
			cancleParams();
			if (arg1 == 0) {
				likeSelect = data.getStringExtra("likeSelect");
				if (!TextUtils.isEmpty(likeSelect)) {
					page = 1;
					mListView.setSelection(1);
					popIndex = 4;
					delete.setVisibility(View.VISIBLE);
					etSearch.setText(likeSelect);
				} else {
					etSearch.setText("");
					delete.setVisibility(View.GONE);
				}
			} else {
				// params = new RequestParams();
				newHouseSpecialName = data.getStringExtra("newHouseSpecialName");
				if (!TextUtils.isEmpty(newHouseSpecialName)) {
					page = 1;
					mListView.setSelection(1);
					popIndex = 2;
					delete.setVisibility(View.VISIBLE);
					etSearch.setText(newHouseSpecialName);
					// params.put("newHouseSpecialName", newHouseSpecialName);
				}
			}
			index = -1;
			newHouseList.clear();
			areaList.clear();
			myAdapter.notifyDataSetChanged();
			mListView.setSelection(1);
			myAdapter.notifyDataSetChanged();
			page = 1;
			initData();
		}

	}

	private void showPaiXuWindow() {
		View view = getLayoutInflater().inflate(R.layout.item_listview, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyle);
		popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		ListView paiXuList = (ListView) view.findViewById(R.id.list_PaiXu);
		paiXuList.setAdapter(new PaiXuPopAdapter(this, list, R.layout.paixu_search_item));
		view.findViewById(R.id.textView1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		paiXuList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				index = position;
				popIndex = 3;
				if (position == 0) {
					paiXu = "更新时间";
					paiXuKey = "updateTime";
					orderBy = 0;
				} else if (position == 1) {
					paiXu = "均价";
					paiXuKey = "averagePrice";
					orderBy = 1;
				} else if (position == 2) {
					paiXu = "均价";
					paiXuKey = "averagePrice";
					orderBy = 0;
				} else {
					paiXu = "发布时间";
					paiXuKey = "createTime";
					orderBy = 0;
				}
				// params.put(paiXuKey, paiXu);
				// params.put("orderBy", orderBy);
				newHouseList.clear();
				areaList.clear();
				page = 1;
				mListView.setSelection(1);
				myAdapter.notifyDataSetChanged();
				initData();
			}
		});
	}

	private void showPopWindow() {
		// params = new RequestParams();
		// select1 = -1;
		// select2 = -1;
		// select3 = -1;
		View view = getLayoutInflater().inflate(R.layout.jingjirenpop_item, null);
		final LinearLayout rlPopWindow = (LinearLayout) view.findViewById(R.id.relativeLayout);
		final ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView2);
		ListView poplv1 = (ListView) view.findViewById(R.id.poplist1);
		final ListView poplv2 = (ListView) view.findViewById(R.id.poplist2);
		ListView poplv3 = (ListView) view.findViewById(R.id.poplist3);
		View setPriceView = view.findViewById(R.id.ll_setprice);
		View headView = view.findViewById(R.id.include_zufang_head);
		final View line0 = view.findViewById(R.id.line0);
		final View line1 = view.findViewById(R.id.line1);

		LinearLayout ll_popleft = (LinearLayout) view.findViewById(R.id.lv_popleft);
		final ScrollView sl_popmiddle = (ScrollView) view.findViewById(R.id.sl_popmiddle);
		LinearLayout sl_popright = (LinearLayout) view.findViewById(R.id.lv_popright);

		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		// popupWindow.setAnimationStyle(R.style.PopupStyle);
		popupWindow.showAsDropDown(viewBottom);
		TextView tv_bottom = (TextView) view.findViewById(R.id.tv_bottom);

		if (poptab != 0) {
			line0.setVisibility(View.GONE);
			line1.setVisibility(View.GONE);
		}
		popList1.clear();
		popList2.clear();
		popList3.clear();
		select1 = hasRecord.get(first);
		select2 = hasRecord.get(poptab);
		select3 = hasRecord.get(third);
		if (poptab == 0) {
			if (!searchBusiness) {
				popList1.add("区域");
				popList1.add("附近");
				String[] strings3 = getResources().getStringArray(R.array.pop_fujin);
				for (int i = 0; i < strings3.length; i++) {
					popList3.add(strings3[i]);
				}
				line0.setVisibility(View.GONE);
				sl_popmiddle.setVisibility(View.GONE);
			} else {
				popList1.add("区域");
				popList1.add("附近");
				line0.setVisibility(View.VISIBLE);
				sl_popmiddle.setVisibility(View.VISIBLE);
				line1.setVisibility(View.VISIBLE);
				popList2.add("不限");
				for (int i = 0; i < areaList.size(); i++) {
					String areaName = areaList.get(i).getAreaName();
					if (!popList2.contains(areaName)) {
						popList2.add(areaName);
					}
				}
				if (select2 != 0 && select2 != -1) {
					popList3.add("不限");
					switch (select2) {
					case 0:
						break;
					case 1:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("思明区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}
						break;
					case 2:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("海沧区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}
						break;
					case 3:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("湖里区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}
						break;
					case 4:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("集美区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}
						break;
					case 5:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("同安区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}
						break;
					case 6:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("翔安区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}
						break;
					default:
						break;
					}
				}
				if (height != 0) {
					LayoutParams layoutParams = rlPopWindow.getLayoutParams();
					layoutParams.height = height;
				}
			}
			tvQuYu.setTextColor(Color.parseColor("#1693FE"));
			tvQuYu.setCompoundDrawables(null, null, rightDrawableUp, null);
			setPriceView.setVisibility(View.GONE);
		}
		final EditText etLowPrice = (EditText) setPriceView.findViewById(R.id.et_lowprice);
		final EditText etHighPrice = (EditText) setPriceView.findViewById(R.id.et_highprice);
		if (poptab == 1) {

			String[] strings = getResources().getStringArray(R.array.pop_junjia);
			for (int i = 0; i < strings.length; i++) {
				popList2.add(strings[i]);
			}
			setPriceView.setVisibility(View.VISIBLE);
			setPriceView.findViewById(R.id.bt_sure).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					newHouseList.clear();
					areaList.clear();
					popIndex = 1;
					String lowPrice = etLowPrice.getText().toString();
					String highPrice = etHighPrice.getText().toString();
					if (TextUtils.isEmpty(lowPrice)) {
						if (TextUtils.isEmpty(highPrice)) {
							averagePriceRange = null;
							tvJunJia.setText("均价");
							myAdapter.notifyDataSetChanged();
							page = 1;
							initData();
							return;
						}
					}
					if (!TextUtils.isEmpty(lowPrice) && !TextUtils.isEmpty(highPrice)) {
						int lPrice = Integer.parseInt(lowPrice);
						int hPrice = Integer.parseInt(highPrice);
						if (lPrice > hPrice) {
							ToastUtil.toastShow(XinFangActivity.this, "最低价格不能高于最高价格");
							return;
						}
					}
					if (TextUtils.isEmpty(lowPrice)) {
						lowPrice = "0";
					}
					if (TextUtils.isEmpty(highPrice)) {
						highPrice = "1";
					}
					page = 1;
					int lowPrice1 = Integer.parseInt(lowPrice);
					int highPrice1 = Integer.parseInt(highPrice);
					String showString = null;
					if (lowPrice1 == 0) {
						showString = highPrice1 + "万以下";
					}
					if (highPrice1 == 1) {
						showString = lowPrice1 + "万以上";
					}
					if (TextUtils.isEmpty(showString)) {
						showString = lowPrice1 + "-" + highPrice1 + "万";
					}

					averagePriceRange = null;
					averagePriceRange = Integer.parseInt(lowPrice) * 10000 + "," + Integer.parseInt(highPrice) * 10000;
					hasRecord.put(poptab, -1);
					// params.put("averagePriceRange", averagePriceRange);
					mListView.setSelection(1);
					tvJunJia.setText(showString);
					tvJunJia.setTextColor(Color.parseColor("#1693FE"));
					tvJunJia.setCompoundDrawables(null, null, rightDrawableSelect, null);
					myAdapter.notifyDataSetChanged();
					page = 1;
					initData();
					// setSearch(popupWindow);
				}
			});
			ll_popleft.setVisibility(View.GONE);
			sl_popright.setVisibility(View.GONE);
			tvJunJia.setTextColor(Color.parseColor("#1693FE"));
			tvJunJia.setCompoundDrawables(null, null, rightDrawableUp, null);

		}

		if (poptab == 2) {
			String[] strings = getResources().getStringArray(R.array.pop_xinfang_tese);
			for (int i = 0; i < strings.length; i++) {
				popList2.add(strings[i]);
			}
			ll_popleft.setVisibility(View.GONE);
			setPriceView.setVisibility(View.GONE);
			// sl_popmiddle.setVisibility(View.GONE);
			sl_popright.setVisibility(View.GONE);
			tvTeSe.setTextColor(Color.parseColor("#1693FE"));
			tvTeSe.setCompoundDrawables(null, null, rightDrawableUp, null);
		}
		final PopAdapter1 adapter1 = new PopAdapter1();
		poplv1.setAdapter(adapter1);
		final PopAdapter2 adapter2 = new PopAdapter2();
		poplv2.setAdapter(adapter2);
		final PopAdapter3 adapter3 = new PopAdapter3();
		poplv3.setAdapter(adapter3);

		tv_bottom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();

			}
		});

		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {

				if (tvQuYu.getText().toString().equals("区域")) {
					tvQuYu.setTextColor(Color.parseColor("#919191"));
					tvQuYu.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
				if (tvJunJia.getText().toString().equals("均价")) {
					tvJunJia.setTextColor(Color.parseColor("#919191"));
					tvJunJia.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvJunJia.setCompoundDrawables(null, null, rightDrawableSelect, null);

				}
				if (tvTeSe.getText().toString().equals("特色")) {
					tvTeSe.setTextColor(Color.parseColor("#919191"));
					tvTeSe.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvTeSe.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
			}
		});
		poplv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				select1 = position;
				adapter1.notifyDataSetChanged();
				// 点击区域或者附近的时候清空后面两个
				select2 = -1;
				select3 = -1;
				if (position == 1) {
					sl_popmiddle.setVisibility(View.GONE);
					line0.setVisibility(View.GONE);
					String[] strings = getResources().getStringArray(R.array.pop_fujin);
					popList3.clear();
					for (int i = 0; i < strings.length; i++) {
						popList3.add(strings[i]);
					}

				} else {
					line0.setVisibility(View.VISIBLE);
					sl_popmiddle.setVisibility(View.VISIBLE);
					line1.setVisibility(View.VISIBLE);
					popList2.clear();
					popList2.add("不限");
					for (int i = 0; i < areaList.size(); i++) {
						String areaName = areaList.get(i).getAreaName();
						if (!popList2.contains(areaName)) {
							popList2.add(areaName);
						}
					}
					popList3.clear();
					adapter2.notifyDataSetChanged();
				}
				adapter3.notifyDataSetChanged();
				myAdapter.notifyDataSetChanged();
				mListView.setSelection(0);
			}
		});
		poplv2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				select2 = position;
				popList3.clear();
				hasMap.clear();
				intList.clear();
				// areaList.get(position).getBusinessName();
				if (poptab == 0) {
					LayoutParams layoutParams = rlPopWindow.getLayoutParams();
					height = poplv2.getHeight();
					if (position == 0) {
						hasRecord.put(first, 0);
						hasRecord.put(poptab, select2);
						searchBusiness = true;
						type = -1;
						latitude = -1;
						longitude = -1;
						areaId = -1;
						businessListId = -1;
						tvQuYu.setText("区域");
						adapter3.notifyDataSetChanged();
						newHouseList.clear();
						areaList.clear();
						myAdapter.notifyDataSetChanged();
						page = 1;
						initData();
					} else {
						// 点击商圈的时候将第三个ListView的索引置-1
						select3 = -1;
						layoutParams.height = height;// 设置同listView2同样的高度
						popList3.add("不限");
						switch (position) {
						case 0:
							break;
						case 1:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("思明区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}

							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
							break;
						case 2:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("海沧区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}

							break;
						case 3:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("湖里区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
							break;
						case 4:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("集美区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
							break;
						case 5:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("同安区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
							break;
						case 6:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("翔安区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
							break;
						default:
							break;
						}
						scrollView.scrollTo(0, 0);
					}

					adapter3.notifyDataSetChanged();
					adapter2.notifyDataSetChanged();
					return;
				}
				if (poptab == 1) {
					hasRecord.put(poptab, position);
					averagePriceRange = null;
					switch (position) {
					case 0:
						averagePriceRange = "不限";
						break;
					case 1:
						averagePriceRange = 0 + "," + 20000;
						break;
					case 2:
						averagePriceRange = 20000 + "," + 30000;
						break;
					case 3:
						averagePriceRange = 30000 + "," + 40000;
						break;
					case 4:
						averagePriceRange = 40000 + "," + 50000;
						break;
					case 5:
						averagePriceRange = 50000 + "," + 60000;
						break;
					case 6:
						averagePriceRange = 60000 + "," + 80000;
						break;
					case 7:
						averagePriceRange = 80000 + "," + 1;
						break;
					default:
						break;
					}
					popIndex = 1;
					// params.put("averagePriceRange", averagePriceRange);
					if (position == 0) {
						tvJunJia.setText("均价");
						popupWindow.dismiss();
					} else {
						tvJunJia.setText(popList2.get(position));
						tvJunJia.setTextColor(Color.parseColor("#1693FE"));
						tvJunJia.setCompoundDrawables(null, null, rightDrawableSelect, null);
					}
				}

				if (poptab == 2) {
					hasRecord.put(poptab, position);
					popIndex = 2;
					// newhouseSpecialId = hasMapSpecialId.get(position);
					newHouseSpecialName = popList2.get(position);
					// params.put("newHouseSpecialName", newHouseSpecialName);
					if (position == 0) {
						tvTeSe.setText("特色");
						popupWindow.dismiss();
					} else {
						tvTeSe.setText(popList2.get(position));
						tvTeSe.setTextColor(Color.parseColor("#1693FE"));
						tvTeSe.setCompoundDrawables(null, null, rightDrawableSelect, null);
					}
				}
				newHouseList.clear();
				areaList.clear();
				page = 1;
				mListView.setSelection(1);
				myAdapter.notifyDataSetChanged();
				initData();
				adapter2.notifyDataSetChanged();
			}
		});
		poplv3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				popIndex = 0;
				select3 = position;
				hasRecord.put(third, position);
				hasRecord.put(poptab, select2);
				if (sl_popmiddle.getVisibility() == View.VISIBLE) {
					hasRecord.put(first, 0);
					searchBusiness = true;
					indexFlag = 1;
					type = -1;
					latitude = -1;
					longitude = -1;
					if (position == 0) {
						businessListId = -1;
						if (select2 == 0) {
							// areaId = -1;
							// tvQuYu.setText("区域");
							// adapter3.notifyDataSetChanged();
							// newHouseList.clear();
							// areaList.clear();
							// initData();
							return;
						} else {
							tvQuYu.setText(popList2.get(select2));
							tvQuYu.setTextColor(Color.parseColor("#1693FE"));
							tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
							adapter3.notifyDataSetChanged();
							areaId = hasMapAreaId.get(popList2.get(select2));
							newHouseList.clear();
							areaList.clear();
							myAdapter.notifyDataSetChanged();
							page = 1;
							initData();
							return;
						}
					} else {
						// 解决点了其他筛选之后，直接点击商圈闪退问题
						if (hasMap.get(position - 1) == null) {
							intList.clear();
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals(popList2.get(select2))) {
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
						}
						businessListId = hasMap.get(position - 1);
					}

				} else {
					hasRecord.put(first, 1);
					searchBusiness = false;
					areaId = -1;
					businessListId = -1;
					type = position + 1;
					initLocation(type);
					if (!locationClient.isStarted()) {
						locationClient.start();
						adapter3.notifyDataSetChanged();
					}
					tvQuYu.setText(popList3.get(position));
					tvQuYu.setTextColor(Color.parseColor("#1693FE"));
					tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
					return;
				}

				newHouseList.clear();
				areaList.clear();
				page = 1;
				mListView.setSelection(1);
				tvQuYu.setText(popList3.get(position));
				tvQuYu.setTextColor(Color.parseColor("#1693FE"));
				tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
				myAdapter.notifyDataSetChanged();
				initData();
				adapter3.notifyDataSetChanged();

			}
		});
	}

	private void initLocation(final int type) {
		locationClient = new LocationClient(getApplicationContext());
		// 设置定位条件
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 是否打开GPS
		option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
		// option.setProdName("LocationDemo"); //
		// 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		// option.setScanSpan(UPDATE_TIME);// 设置定时定位的时间间隔。单位毫秒
		option.setScanSpan(1);
		locationClient.setLocOption(option);

		// 注册位置监听器
		locationClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				if (location == null) {
					return;
				}
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				// params.put("latitude", latitude);
				// params.put("longitude", longitude);
				// params.put("type", type);
				newHouseList.clear();
				areaList.clear();
				page = 1;
				mListView.setSelection(1);
				myAdapter.notifyDataSetChanged();
				initData();

			}
		});

	}

	class PopAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 6;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = getLayoutInflater().inflate(R.layout.searchkey_item, null);
			return inflate;
		}
	}

	class PopAdapter1 extends BaseAdapter {

		@Override
		public int getCount() {
			return popList1.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = getLayoutInflater().inflate(R.layout.searchkey_item, null);
			TextView tv = (TextView) view.findViewById(R.id.tvSearchKey);
			tv.setText(popList1.get(position));
			if (select1 == position) {
				tv.setTextColor(Color.parseColor("#1693FE"));
			}
			return view;
		}

	}

	class PopAdapter2 extends BaseAdapter {

		@Override
		public int getCount() {
			return popList2.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = getLayoutInflater().inflate(R.layout.searchkey_item, null);
			TextView tv = (TextView) view.findViewById(R.id.tvSearchKey);
			if (popIndex == 2) {
				tv.setGravity(Gravity.LEFT);
			}
			tv.setText(popList2.get(position));
			if (select2 == position) {
				tv.setTextColor(Color.parseColor("#1693FE"));
			}
			return view;
		}

	}

	class PopAdapter3 extends BaseAdapter {

		@Override
		public int getCount() {
			return popList3.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = getLayoutInflater().inflate(R.layout.searchkey_item, null);
			TextView tv = (TextView) view.findViewById(R.id.tvSearchKey);
			tv.setText(popList3.get(position));
			if (select3 == position) {
				tv.setTextColor(Color.parseColor("#1693FE"));
			}
			return view;
		}

	}

	class PaiXuPopAdapter extends Common2Adapter<String> {

		public PaiXuPopAdapter(Context context, List<String> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(View helper, String item, int position) {
			TextView tv = (TextView) helper.findViewById(R.id.tvSearchKey);
			if (index == position) {
				tv.setTextColor(Color.parseColor("#FF2B2B"));
			}
			tv.setText(item);

		}

	}

	// private void setSearch(final PopupWindow popupWindow) {
	// IndexApi.getInstance().requestSearchNewHouseList(this, params, new
	// RequestCallBack() {
	// @Override
	// public void onSuccess(int code, String msg, Object object) {
	// NewHouseListEntity newHoustListEntity = (NewHouseListEntity) object;
	// newHouseList.clear();
	// newHouseList.addAll(newHoustListEntity.getContent().getNewHouseList());
	// myAdapter.notifyDataSetChanged();
	// popupWindow.dismiss();
	// }
	//
	// @Override
	// public void onNetworkError() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onFailure(int code, String msg, Object object) {
	// // TODO Auto-generated method stub
	//
	// }
	// });
	// }

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		// params = new RequestParams();
		// params.put("pageNum", page);
		// if (popIndex == 0) {
		// if (indexFlag == 1) {
		// params.put("businessListId", businessListId);
		// } else {
		// params.put("latitude", latitude);
		// params.put("longitude", longitude);
		// params.put("type", type);
		// }
		// } else if (popIndex == 1) {
		// params.put("averagePriceRange", averagePriceRange);
		// } else if (popIndex == 2) {
		// params.put("newHouseSpecialName", newHouseSpecialName);
		// } else if (popIndex == 3) {
		// params.put(paiXuKey, paiXu);
		// params.put("orderBy", orderBy);
		// } else if (popIndex == 4) {
		// params.put("likeSelect", likeSelect);
		// } else if (popIndex == 5) {
		// params.put("updateTime", "更新时间");
		// params.put("orderBy ", 0);
		// }
		isRefresh = true;
		initData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page++;
		isRefresh = false;
		initData();
	}

	@Override
	public void onLoadData() {
		initData();
	}

}
