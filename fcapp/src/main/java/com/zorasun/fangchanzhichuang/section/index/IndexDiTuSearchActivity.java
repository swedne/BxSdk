package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.List;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.SharedPreferencesUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollListView;
import com.zorasun.fangchanzhichuang.section.index.entity.MapSearchEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.MapSearchEntity.AreaListNameList;
import com.zorasun.fangchanzhichuang.section.index.entity.MapSearchEntity.BusinessListNameList;
import com.zorasun.fangchanzhichuang.section.index.entity.MapSearchEntity.MapSearchHouseAreaInfoList;
import com.zorasun.fangchanzhichuang.section.index.entity.MapSearchEntity.MapSearchNewHouseAreaInfoList;
import com.zorasun.fangchanzhichuang.section.index.entity.MapSearchEntity.NewHouseNameList;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class IndexDiTuSearchActivity extends BaseActivity implements OnClickListener {

	private NoScrollListView lvSearchHistory;
	private EditText etText;
	private List<String> historyList = new ArrayList<>();
	private SearchHoristAdapter adapter;
	private String searchKey;
	private RequestParams params = new RequestParams();
	private boolean isFirst = true;
	protected int selectTypeId;// 1 二手房 2租房 3新房
	private List<MapSearchHouseAreaInfoList> searchList = new ArrayList<>();
	private List<AreaListNameList> areaListNameList = new ArrayList<>();
	private List<BusinessListNameList> businessListNameList = new ArrayList<>();
	// private List<MapSearchNewHouseAreaInfoList> mapSearchNewHouseAreaInfoList
	// = new ArrayList<>();
	private List<String> seachListInfo = new ArrayList<>();
	private List<String> businessNameList = new ArrayList<>();
	private View searchHis;
	private View clearView;
	private CustomView customview;
	private List<NewHouseNameList> newHouseNameList = new ArrayList<>();
	private View imgDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index_di_tu_search);
		selectTypeId = getIntent().getIntExtra("selectTypeId", -1);
		initView();
	}

	private void initView() {
		customview = (CustomView) findViewById(R.id.data_error);
		imgDelete = findViewById(R.id.img_delete);
		imgDelete.setOnClickListener(this);
		searchHis = findViewById(R.id.tv_SearchHis);
		clearView = findViewById(R.id.tv_clear);
		clearView.setOnClickListener(this);
		lvSearchHistory = (NoScrollListView) findViewById(R.id.lv_SearchHistory);
		findViewById(R.id.title_right_tv).setOnClickListener(this);
		etText = (EditText) findViewById(R.id.et_title_Search);
		adapter = new SearchHoristAdapter();
		lvSearchHistory.setAdapter(adapter);
		lvSearchHistory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = getIntent();
				if (isFirst) {
					intent.putExtra("likeSelect", historyList.get(position));
					searchKey = historyList.get(position);
					setResult(0, intent);
				} else {
					intent.putExtra("likeSelect", seachListInfo.get(position));
					setResult(1, intent);
					searchKey = seachListInfo.get(position);
					// if (position < businessNameList.size()) {
					// } else {
					// position -= businessNameList.size();
					// intent.putExtra("likeSelect",
					// areaListNameList.get(position));
					// setResult(2, intent);
					// saveHistory(areaListNameList.get(position).getAreaListName());
					// }
				}
				saveHistory(searchKey);
				finish();
			}
		});
		etText.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			public void afterTextChanged(Editable s) {
				if (TextUtils.isEmpty(s.toString())) {
					searchHis.setVisibility(View.VISIBLE);
					clearView.setVisibility(View.VISIBLE);
					isFirst = true;
					seachListInfo.clear();
					getHistData();
					adapter.notifyDataSetChanged();
					imgDelete.setVisibility(View.GONE);
				} else {
					searchHis.setVisibility(View.GONE);
					clearView.setVisibility(View.GONE);
					isFirst = false;
					params.put("isInit", 1);
					params.put("likeSelect", s.toString());
					params.put("selectTypeId", selectTypeId);
					imgDelete.setVisibility(View.VISIBLE);
					initData();
				}
			}
		});
	}

	protected void initData() {
		IndexApi.getInstance().requestMapList(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				MapSearchEntity mapSearchEntity = (MapSearchEntity) object;
				seachListInfo.clear();
				businessNameList.clear();
				areaListNameList.clear();
				if (mapSearchEntity.getContent() != null) {
					if (selectTypeId != 3) {
						if (mapSearchEntity.getContent().getMapSearchHouseAreaInfoList() != null) {
							searchList.clear();
							businessListNameList.clear();
							if (mapSearchEntity.getContent().getMapSearchHouseAreaInfoList() != null) {
								searchList.addAll(mapSearchEntity.getContent().getMapSearchHouseAreaInfoList());
							}
							if (mapSearchEntity.getContent().getAreaListNameList() != null) {
								areaListNameList.addAll(mapSearchEntity.getContent().getAreaListNameList());
							}
							if (mapSearchEntity.getContent().getBusinessListNameList() != null) {
								businessListNameList.addAll(mapSearchEntity.getContent().getBusinessListNameList());
							}
							for (int i = 0; i < businessListNameList.size(); i++) {
								if (!businessNameList.contains(businessListNameList.get(i).getBusinessListName())) {
									businessNameList.add(businessListNameList.get(i).getBusinessListName());
								}
							}
							for (int i = 0; i < businessNameList.size(); i++) {
								seachListInfo.add(businessNameList.get(i));
							}
							for (int j = 0; j < areaListNameList.size(); j++) {
								seachListInfo.add(areaListNameList.get(j).getAreaListName());
							}

						}
					} else {
						businessListNameList.clear();
						if (mapSearchEntity.getContent().getBusinessListNameList() != null) {
							businessListNameList.addAll(mapSearchEntity.getContent().getBusinessListNameList());
							for (int j = 0; j < businessListNameList.size(); j++) {
								seachListInfo.add(businessListNameList.get(j).getBusinessListName());
							}
						}
						newHouseNameList.clear();
						if (mapSearchEntity.getContent().getNewHouseNameList() != null) {
							newHouseNameList.addAll(mapSearchEntity.getContent().getNewHouseNameList());
							for (int i = 0; i < newHouseNameList.size(); i++) {
								seachListInfo.add(newHouseNameList.get(i).getNewHouseName());
							}
						}
					}

					// // mapSearchNewHouseAreaInfoList.clear();
					// if
					// (mapSearchEntity.getContent().getMapSearchNewHouseAreaInfoList()
					// != null) {
					// // mapSearchNewHouseAreaInfoList
					// //
					// .addAll(mapSearchEntity.getContent().getMapSearchNewHouseAreaInfoList());
					// // for (int i = 0; i <
					// // mapSearchNewHouseAreaInfoList.size(); i++) {
					// //
					// seachListInfo.add(mapSearchNewHouseAreaInfoList.get(i).getBusinessListName());
					// // }
					//
					// }

				}
				if (seachListInfo.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {

			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		getHistData();
		adapter.notifyDataSetChanged();

	}

	private void getHistData() {
		String string = SharedPreferencesUtil.getString("historyMap", null);
		if (string != null) {
			String[] arr = string.split(",");
			historyList.clear();
			for (int i = 0; i < arr.length; i++) {
				if (historyList.contains(arr[i])) {
					continue;
				}
				historyList.add(arr[i]);
			}
		}
	}

	private class SearchHoristAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			if (isFirst) {
				return historyList.size();
			} else {
				return seachListInfo.size();
			}
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView != null) {
				holder = (ViewHolder) convertView.getTag();
			} else {
				convertView = getLayoutInflater().inflate(R.layout.searchkey_item, null);
				holder = new ViewHolder();
				holder.tvSearchKey = (TextView) convertView.findViewById(R.id.tvSearchKey);
				convertView.setTag(holder);
			}
			if (isFirst) {
				holder.tvSearchKey.setText(historyList.get(position));
			} else {
				holder.tvSearchKey.setText(seachListInfo.get(position));
				// if (position < businessNameList.size()) {
				// holder.tvSearchKey.setText(businessNameList.get(position));
				// } else {
				// position -= businessNameList.size();
				// holder.tvSearchKey.setText(areaListNameList.get(position).getAreaListName());
				// }
			}
			return convertView;
		}

		private class ViewHolder {
			TextView tvSearchKey;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_right_tv:
			searchKey = etText.getText().toString();
			if (!TextUtils.isEmpty(searchKey)) {
				saveHistory(searchKey);
			}
			setResult(0, getIntent().putExtra("likeSelect", searchKey));
			finish();
			break;
		case R.id.tv_clear:
			SharedPreferencesUtil.removeString("historyMap");
			historyList.clear();
			adapter.notifyDataSetChanged();
			break;
		case R.id.img_delete:
			etText.setText("");
			break;
		default:
			break;
		}
	}

	private void saveHistory(String searchKey) {
		SharedPreferencesUtil.saveBoolean("hasMapHistory", true);
		String history = SharedPreferencesUtil.getString("historyMap", "");
		// if (!history.contains(searchKey + ",")) {
		StringBuilder sb = new StringBuilder(history);
		sb.insert(0, searchKey + ",");
		SharedPreferencesUtil.saveString("historyMap", sb.toString());
		// }
	}

}
