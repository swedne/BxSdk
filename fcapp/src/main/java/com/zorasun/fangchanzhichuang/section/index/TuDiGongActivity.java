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
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.index.entity.AroundBrokerListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.AroundBrokerListEntity.BorkList;
import com.zorasun.fangchanzhichuang.section.index.entity.AroundBrokerListEntity.Content;
import com.zorasun.fangchanzhichuang.section.index.entity.AroundBrokerListEntity.SpecialSkillList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class TuDiGongActivity extends BaseActivity
		implements OnClickListener, OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private LocationClient locationClient;
	private double latitude;
	private double longitude;
	private int pageNum = 1;
	private List<BorkList> brokerList = new ArrayList<>();
	private MyAdapter myAdapter;
	private PullToRefreshListView ptrListView;
	private ListView mListView;
	private boolean isRefresh;
	private CustomView customview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tu_di_gong);
		initView();
	}

	private void initView() {
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		findViewById(R.id.title_left).setOnClickListener(this);
		TextView tvName = (TextView) findViewById(R.id.title_name);
		tvName.setText("周边的土地公");
		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.PULL_FROM_START);
		ptrListView.setOnRefreshListener(this);
		mListView = ptrListView.getRefreshableView();
		myAdapter = new MyAdapter(this, brokerList, R.layout.jingjiren_item);
		mListView.setAdapter(myAdapter);
		mListView.setOnItemClickListener(this);

	}

	@Override
	protected void onStart() {
		super.onStart();
		// LocationManager locationManager = (LocationManager)
		// this.getSystemService(Context.LOCATION_SERVICE);
		// if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		// {
		//
		// }
		initLocation();
		if (!locationClient.isStarted()) {
			locationClient.start();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initData() {
		IndexApi.getInstance().requestAroundBrokerList(this, latitude, longitude, pageNum, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				AroundBrokerListEntity aroundBrokerListEntity = (AroundBrokerListEntity) object;
				Content content = aroundBrokerListEntity.getContent();
				if (isRefresh) {
					brokerList.clear();
				}
				brokerList.addAll(content.getBorkList());
				if (brokerList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				myAdapter.notifyDataSetChanged();

			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(TuDiGongActivity.this, getResources().getString(R.string.net_error));
				ptrListView.postDelayed(new Runnable() {

					@Override
					public void run() {
						ptrListView.onRefreshComplete();
					}
				}, 1000);
				ptrListView.setMode(Mode.DISABLED);
				customview.showLoadStateView(CustomView.STATE_ERROR);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(TuDiGongActivity.this, msg);
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
			}
		});

	}

	@Override
	protected void onStop() {
		super.onStop();
		locationClient.stop();
		// openGPSSettings();
	}

	private void initLocation() {
		locationClient = new LocationClient(getApplicationContext());
		// 设置定位条件
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 是否打开GPS
		option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
		// option.setPriority(LocationClientOption.GpsOnly); // 设置定位优先级
		// option.setProdName("LocationDemo"); //
		// 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		// option.setScanSpan(UPDATE_TIME);// 设置定时定位的时间间隔。单位毫秒
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
				initData();
			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;

		default:
			break;
		}
	}

	class MyAdapter extends Common2Adapter<BorkList> {

		public MyAdapter(Context context, List<BorkList> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@SuppressLint("InflateParams")
		@Override
		public void convert(View helper, BorkList item, int position) {
			ImageView imgLevel = (ImageView) helper.findViewById(R.id.img_leavel);
			imgLevel.setVisibility(View.VISIBLE);
			switch (position) {
			case 0:
				imgLevel.setImageResource(R.drawable.jin);
				break;
			case 1:
				imgLevel.setImageResource(R.drawable.yin);
				break;
			case 2:
				imgLevel.setImageResource(R.drawable.tong);
				break;
			default:
				imgLevel.setVisibility(View.GONE);
				break;
			}
			TextView tvBrokerName = (TextView) helper.findViewById(R.id.tv_brokerName);
			TextView tvRealName = (TextView) helper.findViewById(R.id.tv_realName);
			TextView tvIsExpert = (TextView) helper.findViewById(R.id.tv_isExpert);
			TextView tvHarkBackHouse = (TextView) helper.findViewById(R.id.tv_harkBackHouse);
			TextView tvBusinessName = (TextView) helper.findViewById(R.id.tv_businessname);
			View line = helper.findViewById(R.id.line);
			LinearLayout llSkill = (LinearLayout) helper.findViewById(R.id.ll_biaoqian);
			ImageView imgBroker = (ImageView) helper.findViewById(R.id.img_broker);
			if (!TextUtils.isEmpty(item.getHeadUrl())) {
				AsyncImageLoader.setAsynImages(imgBroker, item.getHeadUrl());
			} else {
				imgBroker.setImageResource(R.drawable.wutu);
			}
			tvBrokerName.setText(item.getBrokerName());
			tvRealName.setText(item.getRealName());
			tvHarkBackHouse.setText(item.getHarkBackHouse());
			tvBusinessName.setText(item.getBusinessListName());
			if (item.getIsExpert() == 1) {
				tvIsExpert.setVisibility(View.VISIBLE);
				line.setVisibility(View.VISIBLE);
			} else {
				tvIsExpert.setVisibility(View.GONE);
				line.setVisibility(View.GONE);
			}
			List<SpecialSkillList> specialSkillList = item.getSpecialSkillList();
			if (specialSkillList.size() > 0) {
				for (int i = 0; i < specialSkillList.size(); i++) {
					View childLayout = getLayoutInflater().inflate(R.layout.childview_jingjiren_item, null);
					TextView tvSkill = (TextView) childLayout.findViewById(R.id.tv_skill);
					tvSkill.setText(specialSkillList.get(i).getSpeciaName());
					llSkill.addView(childLayout);
				}
			} else {
				View childLayout = getLayoutInflater().inflate(R.layout.childview_jingjiren_item, null);
				TextView tvSkill = (TextView) childLayout.findViewById(R.id.tv_skill);
				tvSkill.setText("暂无");
				llSkill.addView(childLayout);
			}
		}

	}

	// class MyAdapter extends BaseAdapter {
	// @Override
	// public int getCount() {
	// return 5;
	// }
	//
	// @Override
	// public Object getItem(int position) {
	// return null;
	// }
	//
	// @Override
	// public long getItemId(int position) {
	// return 0;
	// }
	//
	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// View inflate = getLayoutInflater().inflate(R.layout.jingjiren_item,
	// null);
	// return inflate;
	// }
	// }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		position -= mListView.getHeaderViewsCount();
		Intent intent = new Intent(this, JingjirenXqActivity.class);
		intent.putExtra("brokerId", brokerList.get(position).getBrokerId());
		startActivity(intent);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum = 1;
		isRefresh = true;
		initData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// pageNum++;
		// if (pageNum == pageCount) {
		// ptrListView.setMode(Mode.PULL_FROM_START);
		// }
		isRefresh = false;
		initData();
	}

	@Override
	public void onLoadData() {
		initData();
	}
}
