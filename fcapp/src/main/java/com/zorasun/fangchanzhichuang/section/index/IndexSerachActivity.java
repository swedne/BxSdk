package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.util.ScreenUtils;
import com.zorasun.fangchanzhichuang.general.util.SharedPreferencesUtil;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollGridView;
import com.zorasun.fangchanzhichuang.section.index.entity.HotTagEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.HotTagEntity.BrokerSpecialtyList;
import com.zorasun.fangchanzhichuang.section.index.entity.HotTagEntity.HouseResourceSpecialtyList;
import com.zorasun.fangchanzhichuang.section.index.entity.HotTagEntity.NewHouseSpecialList;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.SearchAreaListEntity;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.SearchAreaListEntity.XiamenAreaList;
import com.zorasun.fangchanzhichuang.section.senddemand.entity.SearchAreaListEntity.XiamenBusinessList;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class IndexSerachActivity extends BaseActivity implements OnClickListener, OnLoadStateLinstener {

	private int flag;
	private SpecialAdapter specialAdapter;
	private SearchHoristAdapter searchHisAdapter;
	private int rechargeType;// 1经纪人 2二手房 3租房 4新房
	private ArrayList<String> historyList = new ArrayList<>();
	private EditText etTitleSearch;
	private String searchKey;
	private List<Object> hotList = new ArrayList<>();

	public static final int SECHOSSEARCH = 20;
	public static final int RENTSEARCH = 21;
	public boolean isFirst = true;
	private List<Object> xiamenAreaList = new ArrayList<>();
	private CustomView customview;
	private TextView tv2;
	private View clearView;
	private View imgDelete;
	private TextView tv1;
	private NoScrollGridView gvSpecial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_indexsearch);
		flag = getIntent().getIntExtra("stageNum", 1);
		if (flag == SystemConstant.STATE_PAGE_JINGJIREN) {
			rechargeType = 1;
			initBrokerHot();
		} else if (flag == SystemConstant.STATE_PAGE_SECONDHOUSE) {
			rechargeType = 2;
			// initSecondHot();
		} else if (flag == SystemConstant.STATE_PAGE_RENTHOUSE) {
			rechargeType = 3;
			// initRentHot();
		} else if (flag == SystemConstant.STATE_PAGE_NEWHOUSE) {
			rechargeType = 4;
			// initNewHot();
		} else if (flag == SystemConstant.STATE_PAGE_SHOUYE) {
			rechargeType = 2;
			// initSecondHot();
		}
		initView();
		Log.e("=========", "density" + ScreenUtils.getScreenDensity(this));

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	private void initNewHot() {
		IndexApi.getInstance().requestNewHot(this, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				HotTagEntity hotTagEntity = (HotTagEntity) object;
				hotList.clear();
				hotList.addAll(hotTagEntity.getContent().getNewHouseSpecialList());
				specialAdapter.notifyDataSetChanged();
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				// TODO Auto-gdenerated method stub

			}
		});
	}

	private void initRentHot() {
		IndexApi.getInstance().requestSecondHot(this, 0, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				HotTagEntity hotTagEntity = (HotTagEntity) object;
				hotList.clear();
				hotList.addAll(hotTagEntity.getContent().getHouseResourceSpecialtyList());
				specialAdapter.notifyDataSetChanged();
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

	private void initSecondHot() {
		IndexApi.getInstance().requestSecondHot(this, 1, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				HotTagEntity hotTagEntity = (HotTagEntity) object;
				hotList.clear();
				hotList.addAll(hotTagEntity.getContent().getHouseResourceSpecialtyList());
				specialAdapter.notifyDataSetChanged();
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

	private void initBrokerHot() {
		IndexApi.getInstance().requestBrokerHot(this, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				HotTagEntity hotTagEntity = (HotTagEntity) object;
				hotList.clear();
				hotList.addAll(hotTagEntity.getContent().getBrokerSpecialtyList());
				specialAdapter.notifyDataSetChanged();
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
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		clearView = findViewById(R.id.tv_clear);
		clearView.setOnClickListener(this);
		imgDelete = findViewById(R.id.img_delete);
		imgDelete.setOnClickListener(this);
		etTitleSearch = (EditText) findViewById(R.id.et_title_Search);
		tv1 = (TextView) findViewById(R.id.tv_special);
		tv2 = (TextView) findViewById(R.id.tv_history);
		if (flag == SystemConstant.STATE_PAGE_JINGJIREN) {
			tv1.setText("经纪人特长");
			tv2.setText("历史记录");
			etTitleSearch.setHint("请输入商圈、小区或经纪人姓名");
		} else {
			tv1.setText("特色标签");
			tv2.setText("历史记录");
			etTitleSearch.setHint("请输入关键词");
		}
		searchData();
		View rlSearchKey = findViewById(R.id.rl_SearchKey);
		View rlBottom = findViewById(R.id.ll_bottom);
		// 从首页搜索栏进入的，隐藏特色标签并显示身边房源和地图定位两个按钮
		if (flag == SystemConstant.STATE_PAGE_JINGJIREN) {
			rlSearchKey.setVisibility(View.VISIBLE);
		} else {
			rlSearchKey.setVisibility(View.GONE);
		}
		if (flag == SystemConstant.STATE_PAGE_SHOUYE || flag == SystemConstant.STATE_PAGE_SECONDHOUSE
				|| flag == SystemConstant.STATE_PAGE_RENTHOUSE) {
			rlBottom.setVisibility(View.VISIBLE);
		} else {
			rlBottom.setVisibility(View.GONE);
		}
		TextView title_right_tv = (TextView) findViewById(R.id.title_right_tv);
		title_right_tv.setText("搜索");
		title_right_tv.setOnClickListener(this);
		gvSpecial = (NoScrollGridView) findViewById(R.id.gv_special);
		specialAdapter = new SpecialAdapter();
		gvSpecial.setAdapter(specialAdapter);
		ListView gvHistory = (ListView) findViewById(R.id.gv_history);
		searchHisAdapter = new SearchHoristAdapter();
		gvHistory.setAdapter(searchHisAdapter);
		gvSpecial.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Object object = hotList.get(position);
				if (flag == SystemConstant.STATE_PAGE_JINGJIREN) {
					BrokerSpecialtyList brokerSpecial = (BrokerSpecialtyList) object;
					setResult(1, getIntent().putExtra("houseTypeName", brokerSpecial.getSpecial_name()));
				} else if (flag == SystemConstant.STATE_PAGE_SECONDHOUSE) {
					HouseResourceSpecialtyList secondHouseSpecial = (HouseResourceSpecialtyList) object;
					setResult(1, getIntent().putExtra("specialtyName", secondHouseSpecial.getSpecialtyName()));
				} else if (flag == SystemConstant.STATE_PAGE_RENTHOUSE) {
					HouseResourceSpecialtyList secondHouseSpecial = (HouseResourceSpecialtyList) object;
					setResult(1, getIntent().putExtra("specialtyName", secondHouseSpecial.getSpecialtyName()));
				} else if (flag == SystemConstant.STATE_PAGE_NEWHOUSE) {
					NewHouseSpecialList newHouseSpecial = (NewHouseSpecialList) object;
					setResult(1, getIntent().putExtra("newHouseSpecialName", newHouseSpecial.getSpecialName()));
				} else if (flag == SystemConstant.STATE_PAGE_SHOUYE) {
					HouseResourceSpecialtyList secondHouseSpecial = (HouseResourceSpecialtyList) object;
					Intent intent = new Intent(IndexSerachActivity.this, IndexSecondHouseActivity.class);
					intent.putExtra("specialtyName", secondHouseSpecial.getSpecialtyName());
					startActivity(intent);
				}
				finish();
			}
		});
		gvHistory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(IndexSerachActivity.this, IndexSecondHouseActivity.class);
				if (isFirst) {
					if (flag == SystemConstant.STATE_PAGE_SHOUYE) {
						intent.putExtra("likeSelect", historyList.get(position));
						startActivity(intent);
					} else {
						setResult(0, getIntent().putExtra("likeSelect", historyList.get(position)));
					}
					saveHistory(historyList.get(position));
				} else {
					if (flag == SystemConstant.STATE_PAGE_NEWHOUSE) {
						XiamenBusinessList object = (XiamenBusinessList) xiamenAreaList.get(position);
						intent.putExtra("likeSelect", object.getBusinessListName());
						setResult(0, getIntent().putExtra("likeSelect", object.getBusinessListName()));
						saveHistory(object.getBusinessListName());
					} else {
						XiamenAreaList object = (XiamenAreaList) xiamenAreaList.get(position);
						if (flag == SystemConstant.STATE_PAGE_SHOUYE) {
							intent.putExtra("likeSelect", object.getAreaListName());
							startActivity(intent);
						} else {
							setResult(0, getIntent().putExtra("likeSelect", object.getAreaListName()));
						}
						saveHistory(object.getAreaListName());
					}
				}
				finish();
			}
		});

		findViewById(R.id.tv_aroundhouse).setOnClickListener(this);
		findViewById(R.id.tv_location).setOnClickListener(this);
		removeEmoji(etTitleSearch);

	}

	private void searchData() {
		etTitleSearch.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			public void afterTextChanged(Editable s) {
				// searchHis.setVisibility(View.GONE);
				// clearView.setVisibility(View.GONE);
				// params.put("isInit", 1);
				// params.put("likeSelect", s.toString());
				// params.put("selectTypeId", selectTypeId);
				if (!TextUtils.isEmpty(s.toString())) {
					isFirst = false;
					imgDelete.setVisibility(View.VISIBLE);
					tv2.setVisibility(View.GONE);
					clearView.setVisibility(View.GONE);
					customview.setVisibility(View.VISIBLE);
					if (flag == SystemConstant.STATE_PAGE_JINGJIREN) {
						tv1.setVisibility(View.GONE);
						gvSpecial.setVisibility(View.GONE);
					}
					if (flag == SystemConstant.STATE_PAGE_NEWHOUSE) {
						initNewHousData(s.toString());
					} else {
						initData(s.toString());
					}
				} else {
					imgDelete.setVisibility(View.GONE);
					tv2.setVisibility(View.VISIBLE);
					clearView.setVisibility(View.VISIBLE);
					customview.setVisibility(View.GONE);
					if (flag == SystemConstant.STATE_PAGE_JINGJIREN) {
						tv1.setVisibility(View.VISIBLE);
						gvSpecial.setVisibility(View.VISIBLE);
					}
					isFirst = true;
					// xiamenAreaList.clear();
					getHisData();
					searchHisAdapter.notifyDataSetChanged();
				}
			}
		});
	}

	private void initNewHousData(String keyword) {
		IndexApi.getInstance().requestSearchNewList(this, keyword, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				SearchAreaListEntity searchAreaListEntity = (SearchAreaListEntity) object;
				xiamenAreaList.clear();
				xiamenAreaList.addAll(searchAreaListEntity.getContent().getXiamenBusinessList());
				if (xiamenAreaList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				searchHisAdapter.notifyDataSetChanged();
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
				searchHisAdapter.notifyDataSetChanged();
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
								Toast.makeText(IndexSerachActivity.this, "不支持输入Emoji表情符号", Toast.LENGTH_SHORT).show();
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

	private class SpecialAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return hotList.size();
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
				convertView = getLayoutInflater().inflate(R.layout.grid_item_text, null);
				holder = new ViewHolder();
				holder.tvSearchKey = (TextView) convertView.findViewById(R.id.tvSearchKey);
				holder.tvSearchLine = convertView.findViewById(R.id.search_line);
				holder.horiztalLine = convertView.findViewById(R.id.horizontal_line);
				convertView.setTag(holder);
			}
			// 将最后一个框的边缘线去掉
			if (getCount() % 3 != 0) {
				if (position == getCount() - 1) {
					holder.tvSearchLine.setVisibility(View.GONE);
				}
			}
			float screenDensity = ScreenUtils.getScreenDensity(IndexSerachActivity.this);
			// 设置首尾底部边缘线的长度
			if (position % 3 == 0) {
				holder.horiztalLine.setPadding(24 * (int) screenDensity, 0, 0, 0);
			} else if ((position + 1) % 3 == 0) {
				holder.horiztalLine.setPadding(0, 0, 24 * (int) screenDensity, 0);
			}
			// 将最后一行的边缘线去掉
			if (getCount() - position + 1 < 3) {
				holder.horiztalLine.setVisibility(View.GONE);
			}

			Object object = hotList.get(position);
			// holder.tvSearchKey.setText("热门标签");
			if (flag == SystemConstant.STATE_PAGE_JINGJIREN) {
				BrokerSpecialtyList brokerSpecial = (BrokerSpecialtyList) object;
				holder.tvSearchKey.setText(brokerSpecial.getSpecial_name());
			} else if (flag == SystemConstant.STATE_PAGE_SECONDHOUSE) {
				HouseResourceSpecialtyList houseResourceSpecialtyList = (HouseResourceSpecialtyList) object;
				holder.tvSearchKey.setText(houseResourceSpecialtyList.getSpecialtyName());
			} else if (flag == SystemConstant.STATE_PAGE_NEWHOUSE) {
				NewHouseSpecialList newHouseSpecial = (NewHouseSpecialList) object;
				holder.tvSearchKey.setText(newHouseSpecial.getSpecialName());
			} else if (flag == SystemConstant.STATE_PAGE_RENTHOUSE) {
				HouseResourceSpecialtyList houseResourceSpecialtyList = (HouseResourceSpecialtyList) object;
				holder.tvSearchKey.setText(houseResourceSpecialtyList.getSpecialtyName());
			} else if (flag == SystemConstant.STATE_PAGE_SHOUYE) {
				HouseResourceSpecialtyList houseResourceSpecialtyList = (HouseResourceSpecialtyList) object;
				holder.tvSearchKey.setText(houseResourceSpecialtyList.getSpecialtyName());
			}
			return convertView;
		}

		private class ViewHolder {
			TextView tvSearchKey;
			View tvSearchLine;
			View horiztalLine;
		}
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
				convertView = getLayoutInflater().inflate(R.layout.item_textview, null);
				holder = new ViewHolder();
				holder.tvSearchKey = (TextView) convertView.findViewById(R.id.item_tv);
				convertView.setTag(holder);
			}
			// holder.tvSearchKey.setGravity(Gravity.LEFT);
			if (isFirst) {
				holder.tvSearchKey.setText(historyList.get(position));
			} else {
				if (flag == SystemConstant.STATE_PAGE_NEWHOUSE) {
					XiamenBusinessList object = (XiamenBusinessList) xiamenAreaList.get(position);
					holder.tvSearchKey.setText(object.getBusinessListName());

				} else {
					XiamenAreaList object = (XiamenAreaList) xiamenAreaList.get(position);
					holder.tvSearchKey.setText(object.getAreaListName());
				}
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
			searchKey = etTitleSearch.getText().toString();
			if (!TextUtils.isEmpty(searchKey)) {
				saveHistory(searchKey);
			}
			if (flag == SystemConstant.STATE_PAGE_SHOUYE) {
				Intent intent = new Intent(this, IndexSecondHouseActivity.class);
				intent.putExtra("likeSelect", searchKey);
				startActivity(intent);
				finish();
			} else {
				setResult(0, getIntent().putExtra("likeSelect", searchKey));
				finish();
			}
			break;
		case R.id.tv_clear:
			SharedPreferencesUtil.removeString("history" + rechargeType);
			historyList.clear();
			searchHisAdapter.notifyDataSetChanged();
			break;
		// 查看身边的房源
		case R.id.tv_aroundhouse:
			Intent intent0 = new Intent(this, AroundSecHouseActivity.class);
			if (flag == SystemConstant.STATE_PAGE_SHOUYE || flag == SystemConstant.STATE_PAGE_SECONDHOUSE) {
				intent0.putExtra("houseResourceTypeName", "二手房");
			} else if (flag == SystemConstant.STATE_PAGE_RENTHOUSE) {
				intent0.putExtra("houseResourceTypeName", "租房");
			}
			startActivity(intent0);
			break;
		// 地图定位
		case R.id.tv_location:
			ToastUtil.toastShow(this,"暂未开放");

//			Intent intent = new Intent(this, DiTuZhaoFangActivity.class);
//			if (flag == SystemConstant.STATE_PAGE_SHOUYE || flag == SystemConstant.STATE_PAGE_SECONDHOUSE) {
//				intent.putExtra("flag", this.SECHOSSEARCH);
//				intent.putExtra("selectTypeId", 1);
//				intent.putExtra("classify", 1);
//			} else if (flag == SystemConstant.STATE_PAGE_RENTHOUSE) {
//				intent.putExtra("flag", this.RENTSEARCH);
//				intent.putExtra("selectTypeId", 2);
//				intent.putExtra("classify", 0);
//			}
//			startActivity(intent);
			break;
		case R.id.img_delete:
			etTitleSearch.setText("");
			break;
		default:
			break;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		getHisData();
		searchHisAdapter.notifyDataSetChanged();

	}

	private void getHisData() {
		String string = SharedPreferencesUtil.getString("history" + rechargeType, null);
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

	private void saveHistory(String searchKey) {
		SharedPreferencesUtil.saveBoolean("hasHistory" + rechargeType, true);
		String history = SharedPreferencesUtil.getString("history" + rechargeType, "");
		// if (!history.contains(searchKey + ",")) {
		StringBuilder sb = new StringBuilder(history);
		sb.insert(0, searchKey + ",");
		SharedPreferencesUtil.saveString("history" + rechargeType, sb.toString());
		// }
	}

	public static boolean containsEmoji(String source) {
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (!isEmojiCharacter(codePoint)) { // 如果不能匹配,则该字符是Emoji表情
				return true;
			}
		}
		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	@Override
	public void onLoadData() {
		if (flag != SystemConstant.STATE_PAGE_JINGJIREN) {
			if (!TextUtils.isEmpty(etTitleSearch.getText().toString())) {
				if (flag == SystemConstant.STATE_PAGE_NEWHOUSE) {
					initNewHousData(etTitleSearch.getText().toString());
				} else {
					initData(etTitleSearch.getText().toString());
				}
			}
		}
	}
}
