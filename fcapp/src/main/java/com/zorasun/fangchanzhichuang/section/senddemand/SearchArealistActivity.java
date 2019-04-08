package com.zorasun.fangchanzhichuang.section.senddemand;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.SearchAreaListEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.SearchAreaListEntity.XiamenAreaList;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.SearchInfo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchArealistActivity extends BaseActivity implements OnItemClickListener, OnLoadStateLinstener {

	private ListView mListView;
	protected double latitude;
	protected double longitude;
	private List<SearchInfo> areaList = new ArrayList<>();
	private List<XiamenAreaList> xiamenAreaList = new ArrayList<>();
	private MyAdapter adapter;
	private EditText etSearch;
	private int showLoading = 0;
	private CustomView customview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_arealist);
		// 默认软键盘不弹出
		// getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		initView();
		// initLocation();
	}

	@Override
	protected void onStart() {
		super.onStart();
		// if (!locationClient.isStarted()) {
		// locationClient.start();
		// }
	}

	@Override
	protected void onStop() {
		super.onStop();
		// if (locationClient != null) {
		// locationClient.stop();
		// }
	}

	// private void initLocation() {
	// locationClient = new LocationClient(getApplicationContext());
	// // 设置定位条件
	// LocationClientOption option = new LocationClientOption();
	// option.setOpenGps(true); // 是否打开GPS
	// option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
	// option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
	// // option.setProdName("LocationDemo"); //
	// // 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
	// // option.setScanSpan(UPDATE_TIME);// 设置定时定位的时间间隔。单位毫秒
	// option.setScanSpan(1);
	// locationClient.setLocOption(option);
	//
	// // 注册位置监听器
	// locationClient.registerLocationListener(new BDLocationListener() {
	//
	// @Override
	// public void onReceiveLocation(BDLocation location) {
	// if (location == null) {
	// return;
	// }
	// latitude = location.getLatitude();
	// longitude = location.getLongitude();
	// initData();
	// }
	// });
	// }

	// protected void initData() {
	// flag = 0;
	// SendDemandApi.getInstance().requestArealist(SearchArealistActivity.this,
	// latitude, longitude,
	// new RequestCallBack() {
	//
	// @Override
	// public void onSuccess(int code, String msg, Object object) {
	// AreaListInitEntity areaListInitEntity = (AreaListInitEntity) object;
	// areaListByNearby.clear();
	// areaListByNearbyDiff.clear();
	// areaListByNearby.addAll(areaListInitEntity.getContent().getInitXiamenAreaListByNearby());
	// areaListByNearbyDiff
	// .addAll(areaListInitEntity.getContent().getInitXiamenAreaListByNearbyDiff());
	// areaList.clear();
	// // areaList.addAll(areaListByNearby);
	// // areaList.addAll(areaListByNearbyDiff);
	// for (int i = 0; i < areaListByNearby.size(); i++) {
	// areaList.add(new SearchInfo(areaListByNearby.get(i).getAreaId(),
	// areaListByNearby.get(i).getAreaName(),
	// areaListByNearby.get(i).getAreaListId(),
	// areaListByNearby.get(i).getBusinessListId(),
	// areaListByNearby.get(i).getBusinessListName(),
	// areaListByNearby.get(i).getAreaListName()));
	// }
	// for (int j = 0; j < areaListByNearbyDiff.size(); j++) {
	// areaList.add(new SearchInfo(areaListByNearbyDiff.get(j).getAreaId(),
	// areaListByNearbyDiff.get(j).getAreaName(),
	// areaListByNearbyDiff.get(j).getAreaListId(),
	// areaListByNearbyDiff.get(j).getBusinessListId(),
	// areaListByNearbyDiff.get(j).getBusinessListName(),
	// areaListByNearbyDiff.get(j).getAreaListName()));
	// }
	// adapter.notifyDataSetChanged();
	//
	// }
	//
	// @Override
	// public void onNetworkError() {
	//
	// }
	//
	// @Override
	// public void onFailure(int code, String msg, Object object) {
	//
	// }
	// });
	// }

	protected void searchData(String areaListName) {
		SendDemandApi.getInstance().requestSearchArealist(SearchArealistActivity.this, showLoading, areaListName,
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {
						SearchAreaListEntity searchAreaListEntity = (SearchAreaListEntity) object;
						areaList.clear();
						xiamenAreaList.clear();
						xiamenAreaList.addAll(searchAreaListEntity.getContent().getXiamenAreaList());
						for (int j = 0; j < xiamenAreaList.size(); j++) {
							areaList.add(new SearchInfo(xiamenAreaList.get(j).getAreaId(),
									xiamenAreaList.get(j).getAreaName(), xiamenAreaList.get(j).getAreaListId(),
									xiamenAreaList.get(j).getBusinessListId(),
									xiamenAreaList.get(j).getBusinessListName(),
									xiamenAreaList.get(j).getAreaListName()));
						}
						if (areaList.isEmpty()) {
							customview.showLoadStateView(CustomView.STATE_EMPTY);
						} else {
							customview.showLoadStateView(CustomView.STATE_NONE);
						}
						adapter.notifyDataSetChanged();
					}

					@Override
					public void onNetworkError() {
						customview.showLoadStateView(CustomView.STATE_ERROR);
					}

					@Override
					public void onFailure(int code, String msg, Object object) {
						customview.showLoadStateView(CustomView.STATE_EMPTY);
					}
				});
	}

	private void initView() {
		TextView tvTitleName = (TextView) findViewById(R.id.title_name);
		tvTitleName.setText("小区");
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		etSearch = (EditText) findViewById(R.id.et_Search);
		mListView = (ListView) findViewById(R.id.lv_arealist);
		adapter = new MyAdapter();
		mListView.setAdapter(adapter);
		etSearch.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(final CharSequence s, int start, int before, int count) {

			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			public void afterTextChanged(Editable s) {
				// if (TextUtils.isEmpty(s)) {
				// initData();
				// } else {
				// searchData(s.toString());
				// }
				if (!TextUtils.isEmpty(s)) {
					searchData(s.toString());
				} else {
					areaList.clear();
					adapter.notifyDataSetChanged();
					// searchData("");
				}

			}
		});
		mListView.setOnItemClickListener(this);
	}
	//
	// public class SearchAdapter extends BaseAdapter {
	//
	// @Override
	// public int getCount() {
	// // TODO Auto-generated method stub
	// return areaListByNearby.size() + areaListByNearbyDiff.size();
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
	// View inflate = getLayoutInflater().inflate(R.layout.list_item_arealist,
	// null);
	// TextView tvArea = (TextView) inflate.findViewById(R.id.tv_area);
	// if (position >= areaListByNearby.size()) {
	// tvArea.setText(areaListByNearbyDiff.get(position -=
	// areaListByNearby.size()).getAreaListName());
	// } else {
	// tvArea.setText(areaListByNearby.get(position).getAreaListName());
	// }
	// return inflate;
	// }
	//
	// }

	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return areaList.size();
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
			View inflate = getLayoutInflater().inflate(R.layout.list_item_arealist, null);
			TextView tvArea = (TextView) inflate.findViewById(R.id.tv_area);
			SearchInfo areaInfo = areaList.get(position);
			// if (flag == 1) {
			// XiamenAreaList areaInfo = (XiamenAreaList)
			// areaList.get(position);
			tvArea.setText(areaInfo.getAreaListName());
			// } else {
			// if (position >= areaListByNearby.size()) {
			// tvArea.setText(areaListByNearbyDiff.get(position -=
			// areaListByNearby.size()).getAreaListName());
			// } else {
			// tvArea.setText(areaListByNearby.get(position).getAreaListName());
			// }
			// }
			return inflate;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		SearchInfo searchInfo = areaList.get(position);
		Intent intent = getIntent();
		intent.putExtra("areaListName", searchInfo.getAreaListName());
		intent.putExtra("areaName", searchInfo.getAreaName());
		intent.putExtra("businessListName", searchInfo.getBusinessListName());
		intent.putExtra("areaId", searchInfo.getAreaId());
		setResult(1, intent);
		finish();
	}

	@Override
	public void onLoadData() {
		if (!TextUtils.isEmpty(etSearch.getText().toString())) {
			searchData(etSearch.getText().toString());
		}
	}

}
