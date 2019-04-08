package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.SharedPreferencesUtil;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollListView;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.SearchAreaListEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.SearchAreaListEntity.XiamenAreaList;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
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
import android.widget.Toast;

public class BusinessSearchActivity extends BaseActivity implements OnClickListener, OnLoadStateLinstener {

	private NoScrollListView lvSearchHistory;
	private EditText etText;
	private List<String> historyList = new ArrayList<>();
	private SearchHoristAdapter adapter;
	private String searchKey;
	private View clearView;
	private boolean isFirst = true;
	private View tvHis;
	private CustomView customview;
	private List<XiamenAreaList> xiamenAreaList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index_di_tu_search);
		initView();
	}

	private void initView() {
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		clearView = findViewById(R.id.tv_clear);
		clearView.setOnClickListener(this);
		tvHis = findViewById(R.id.tv_SearchHis);
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
					saveHistory(historyList.get(position));
				} else {
					intent.putExtra("likeSelect", xiamenAreaList.get(position).getAreaListName());
					saveHistory(xiamenAreaList.get(position).getAreaListName());
				}
				setResult(0, intent);
				finish();
			}
		});
		removeEmoji(etText);
		searchData();
	}

	private void removeEmoji(final EditText et) {
		if (et != null) {

			et.addTextChangedListener(new TextWatcher() {
				private boolean resetText;
				private int cursorPos;
				private String inputAfterText;

				@Override
				public void beforeTextChanged(CharSequence s, int start, int before, int count) {

					if (!resetText) {
						cursorPos = et.getSelectionEnd();
						// 这里用s.toString()而不直接用s是因为如果用s，
						// 那么，inputAfterText和s在内存中指向的是同一个地址，s改变了，
						// inputAfterText也就改变了，那么表情过滤就失败了
						inputAfterText = s.toString();
					}
				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {

					if (!resetText) {
						if (before != 0) {
							return;
						}
						if (count >= 2) {// 表情符号的字符长度最小为2

							CharSequence input = s.subSequence(cursorPos, cursorPos + count);

							if (StringUtils.containsEmoji(input.toString())) {
								resetText = true;
								Toast.makeText(BusinessSearchActivity.this, "不支持输入Emoji表情符号", Toast.LENGTH_SHORT)
										.show();
								// 是表情符号就将文本还原为输入表情符号之前的内容
								et.setText(inputAfterText);
								CharSequence text = et.getText();
								if (text instanceof Spannable) {
									Spannable spanText = (Spannable) text;
									Selection.setSelection(spanText, text.length());
								}
							}
						}
					} else {
						resetText = false;
					}

				}

				@Override
				public void afterTextChanged(Editable editable) {

				}

			});
		}
	}

	private void searchData() {
		etText.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			public void afterTextChanged(Editable s) {
				isFirst = false;
				// searchHis.setVisibility(View.GONE);
				// clearView.setVisibility(View.GONE);
				// params.put("isInit", 1);
				// params.put("likeSelect", s.toString());
				// params.put("selectTypeId", selectTypeId);
				tvHis.setVisibility(View.GONE);
				clearView.setVisibility(View.GONE);
				customview.setVisibility(View.VISIBLE);
				if (!TextUtils.isEmpty(s.toString())) {
					initData(s.toString());
				} else {
					xiamenAreaList.clear();
					adapter.notifyDataSetChanged();
					customview.showLoadStateView(CustomView.STATE_EMPTY);
				}
			}
		});
	}

	protected void initData(String keyword) {
		IndexApi.getInstance().requestSearchList(this, keyword, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				SearchAreaListEntity searchAreaListEntity = (SearchAreaListEntity) object;
				xiamenAreaList.clear();
				xiamenAreaList.addAll(searchAreaListEntity.getContent().getXiamenAreaList());
				if (xiamenAreaList.isEmpty()) {
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

	@Override
	protected void onStart() {
		super.onStart();
		String string = SharedPreferencesUtil.getString("historyBusiness", null);
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

		adapter.notifyDataSetChanged();
	}

	private class SearchHoristAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			if (isFirst) {
				return historyList.size();
			} else {
				return xiamenAreaList.size();
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
				holder.tvSearchKey.setText(xiamenAreaList.get(position).getAreaListName());
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
			SharedPreferencesUtil.removeString("historyBusiness");
			historyList.clear();
			adapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}

	private void saveHistory(String searchKey) {
		SharedPreferencesUtil.saveBoolean("hasBusinessHistory", true);
		String history = SharedPreferencesUtil.getString("historyBusiness", "");
		StringBuilder sb = new StringBuilder(history);
		sb.insert(0, searchKey + ",");
		SharedPreferencesUtil.saveString("historyBusiness", sb.toString());
	}

	@Override
	public void onLoadData() {
		if (!TextUtils.isEmpty(etText.getText().toString())) {
			initData(etText.getText().toString());
		}
	}

}
