package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.commonadapter.Common2Adapter;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.index.entity.NearByListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.NearByListEntity.HouseListNearby;
import com.zorasun.fangchanzhichuang.section.index.entity.NearByListEntity.RentHouseSpecialtyList;
import com.zorasun.fangchanzhichuang.section.index.entity.NearByListEntity.SecondHouseSpecialtyList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AroundSecHouseActivity extends BaseActivity
		implements OnLoadStateLinstener, OnRefreshListener2<ListView>, OnItemClickListener {

	private PullToRefreshListView ptrListView;
	private CustomView customview;
	private ListView mListView;
	private MyAdapter myAdapter;
	private LocationClient locationClient;
	protected double latitude;
	protected double longitude;
	protected int page = 1;
	private List<HouseListNearby> houseListNearby = new ArrayList<>();
	protected int pageCount;
	private String houseResourceTypeName;
	protected boolean isRefresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fu_jin_fang);
		houseResourceTypeName = getIntent().getStringExtra("houseResourceTypeName");
		initView();
		initLocation();
	}

	private void initLocation() {
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
				if (location == null) {
					return;
				}
				// brokerList.clear();
				// areaList.clear();
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				// params.put("latitude", latitude);
				// params.put("longitude", longitude);
				// params.put("type", type);
				page = 1;
				mListView.setSelection(1);
				initData();

			}
		});

	}

	private void initData() {
		IndexApi.getInstance().requestSearchNearByHouse(this, "住宅", houseResourceTypeName, latitude, longitude, page,
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {
						ptrListView.onRefreshComplete();
						NearByListEntity nearByListEntity = (NearByListEntity) object;
						if (isRefresh) {
							houseListNearby.clear();
						}
						houseListNearby.addAll(nearByListEntity.getContent().getHouseListNearby());

						pageCount = nearByListEntity.getContent().getPageCount();
						if (pageCount <= page) {
							ptrListView.setMode(Mode.PULL_FROM_START);
						} else {
							ptrListView.setMode(Mode.BOTH);
						}
						if (houseListNearby.isEmpty()) {
							customview.showLoadStateView(CustomView.STATE_EMPTY);
							ptrListView.setMode(Mode.DISABLED);
						} else {
							customview.showLoadStateView(CustomView.STATE_NONE);
						}
						myAdapter.notifyDataSetChanged();
					}

					@Override
					public void onNetworkError() {
						ToastUtil.toastShow(AroundSecHouseActivity.this, getResources().getString(R.string.net_error));
						customview.showLoadStateView(CustomView.STATE_ERROR);
						ptrListView.postDelayed(new Runnable() {

							@Override
							public void run() {
								ptrListView.onRefreshComplete();
							}
						}, 1000);
						ptrListView.setMode(Mode.DISABLED);
					}

					@Override
					public void onFailure(int code, String msg, Object object) {
						ToastUtil.toastShow(AroundSecHouseActivity.this, "附近暂无房源信息");
						ptrListView.onRefreshComplete();
						customview.showLoadStateView(CustomView.STATE_EMPTY);
						ptrListView.setMode(Mode.DISABLED);
					}
				});
	}

	private void initView() {
		findViewById(R.id.rl_paixu).setVisibility(View.GONE);

		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		tvTitle.setText("附近的房源");

		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);

		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);
		if (houseResourceTypeName.equals("二手房")) {
			myAdapter = new MyAdapter(this, houseListNearby, R.layout.item_secondhouse);
		} else {
			myAdapter = new MyAdapter(this, houseListNearby, R.layout.index_zufang_item);
		}
		mListView = ptrListView.getRefreshableView();
		mListView.setAdapter(myAdapter);
		mListView.setOnItemClickListener(this);
	}

	class MyAdapter extends Common2Adapter<HouseListNearby> {

		public MyAdapter(Context context, List<HouseListNearby> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(View helper, HouseListNearby item, int position) {
			if (houseResourceTypeName.equals("二手房")) {
				helper.findViewById(R.id.rl_secondhandhouse).setVisibility(View.GONE);
				TextView tvRoomHall = (TextView) helper.findViewById(R.id.tv_room_hall);
				if (item.getRoomNum() != null && item.getHallNum() != null) {
					tvRoomHall.setText(item.getRoomNum() + "室" + item.getHallNum() + "厅");
				} else {
					tvRoomHall.setText(0 + "室" + 0 + "厅");
				}
				TextView tvTitle = (TextView) helper.findViewById(R.id.tv_collect_title01);
				tvTitle.setText(item.getTitle());
				TextView tvBerry = (TextView) helper.findViewById(R.id.tv_berryGG);
				String berryGG = item.getBerryGG();
				if (!TextUtils.isEmpty(berryGG)) {
					tvBerry.setText(berryGG + "㎡");
				} else {
					tvBerry.setText(0 + "㎡");
				}
				TextView tvSalePrice = (TextView) helper.findViewById(R.id.tv_saleprice);
				tvSalePrice.setText("" + item.getSalePrice());
				TextView tvPrice = (TextView) helper.findViewById(R.id.tv_price);
				tvPrice.setText(CommonUtils.checkUnnecessaryZero(String.valueOf(item.getPrice())) + "元/平");
				TextView tvAreaListName = (TextView) helper.findViewById(R.id.tv_collect_arealistname);
				tvAreaListName.setText(item.getAreaListName());
				View imgRenZheng = helper.findViewById(R.id.img_renzheng_web);
				View imgWeiRenZheng = helper.findViewById(R.id.img_weirenzheng_web);
				if (item.getIsAuth() == 0) {
					imgRenZheng.setVisibility(View.GONE);
					imgWeiRenZheng.setVisibility(View.VISIBLE);
				} else {
					imgWeiRenZheng.setVisibility(View.GONE);
					imgRenZheng.setVisibility(View.VISIBLE);
				}
				ImageView imgTitle = (ImageView) helper.findViewById(R.id.img_newhousepic);
				if (!TextUtils.isEmpty(item.getSurFaceUrl())) {
					AsyncImageLoader.setAsynImages(imgTitle, item.getSurFaceUrl());
				}
				LinearLayout llSecondSpecialist = (LinearLayout) helper.findViewById(R.id.ll_secondHouseSpecialist);
				List<SecondHouseSpecialtyList> secondHouseSpecialtyList = item.getSecondHouseSpecialtyList();
				if (secondHouseSpecialtyList.size() > 0) {
					for (int i = 0; i < secondHouseSpecialtyList.size(); i++) {
						if (secondHouseSpecialtyList.size() > 4) {
							return;
						}
						View child = getLayoutInflater().inflate(R.layout.item_text, null);
						TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
						String color = "";
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
						tvHouseTag.setText(secondHouseSpecialtyList.get(i).getSpecialtyName());
						llSecondSpecialist.addView(child);
					}
				} else {
					View child = getLayoutInflater().inflate(R.layout.item_text, null);
					TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
					tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
					tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
					tvHouseTag.setText("暂无");
					llSecondSpecialist.addView(child);
				}
			} else {
				TextView tvTitle = (TextView) helper.findViewById(R.id.tv_title);
				TextView tvAreaListName = (TextView) helper.findViewById(R.id.tv_areaListName);
				TextView tvRentMoney = (TextView) helper.findViewById(R.id.tv_rentMoney);
				LinearLayout llSpecial = (LinearLayout) helper.findViewById(R.id.ll_special);
				helper.findViewById(R.id.ll_housetype).setVisibility(View.VISIBLE);
				TextView tvHouseType = (TextView) helper.findViewById(R.id.tv_housetype);
				TextView tvBerryGG = (TextView) helper.findViewById(R.id.tv_berryGG);
				if (item.getRoomNum() != null && item.getHallNum() != null) {
					tvHouseType.setText(item.getRoomNum() + "室" + item.getHallNum() + "厅");
				} else {
					tvHouseType.setText(0 + "室" + 0 + "厅");
				}
				if (!TextUtils.isEmpty(item.getBerryGG())) {
					tvBerryGG.setText(item.getBerryGG() + "㎡");
				} else {
					tvBerryGG.setText(0 + "㎡");
				}
				tvTitle.setText(item.getTitle());
				tvAreaListName.setText(item.getAreaListName());
				Integer rentMoney = item.getRentMoney();
				if (rentMoney == null) {
					rentMoney = 0;
				}
				tvRentMoney.setText(rentMoney + "");
				ImageView imgTitle = (ImageView) helper.findViewById(R.id.img_newhousepic);
				if (!TextUtils.isEmpty(item.getSurFaceUrl())) {
					AsyncImageLoader.setAsynImages(imgTitle, item.getSurFaceUrl());
				}
				List<RentHouseSpecialtyList> rentHouseSpecialtyList = item.getRentHouseSpecialtyList();
				String color = "";
				if (rentHouseSpecialtyList.size() > 0) {
					for (int i = 0; i < rentHouseSpecialtyList.size(); i++) {
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
						tvHouseTag.setText(rentHouseSpecialtyList.get(i).getSpecialtyName());
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

	}

	@Override
	protected void onStart() {
		super.onStart();
		if (!locationClient.isStarted()) {
			locationClient.start();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (locationClient != null) {
			locationClient.stop();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		position -= mListView.getHeaderViewsCount();
		Intent intent = null;
		if (houseResourceTypeName.equals("二手房")) {
			intent = new Intent(this, SecondHouseDetailActivity.class);
			intent.putExtra("secondHouseId", houseListNearby.get(position).getId());
			intent.putExtra("areaListId", houseListNearby.get(position).getAreaListId());
		} else {
			intent = new Intent(this, ZuFangxqActivity.class);
			intent.putExtra("rentHouseId", houseListNearby.get(position).getId());

		}
		startActivity(intent);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
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
