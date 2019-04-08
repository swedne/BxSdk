package com.zorasun.fangchanzhichuang.section.my;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.my.entiy.MyDemandEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.MyDemandEntity.DemandList;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class SecBuyDemandFragment extends BaseFragment
		implements OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private PullToRefreshListView ptrListView;
	private ListView mListView;
	private LayoutInflater mInflater;
	private int pageNum=1;
	protected boolean isRefresh;
	protected List<DemandList> demandList = new ArrayList<>();
	protected CustomView customview;
	protected Integer pageCount;
	protected BaseAdapter adapter;

	public SecBuyDemandFragment() {

	}

	@Override
	public void onResume() {
		super.onResume();
		demandList.clear();
		if (MyDemandActivity2.position == 1) {
			initData();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mInflater = inflater;
		View view = inflater.inflate(R.layout.fragment_all_demand, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		ptrListView = (PullToRefreshListView) view.findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);
		customview = (CustomView) view.findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);

		mListView = ptrListView.getRefreshableView();
		adapter = new Myadapter();
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
	}

	private void initData() {
		MyApi.getInstance().requestDemand(getActivity(), pageNum, 1, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				MyDemandEntity myDemandEntity = (MyDemandEntity) object;
				if (isRefresh) {
					demandList.clear();
				}
				demandList.addAll(myDemandEntity.getContent().getDemandList());
				if (demandList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				pageCount = myDemandEntity.getContent().getTotalPage();
				if (pageCount <= pageNum) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(getActivity(), getResources().getString(R.string.net_error));
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_ERROR);
				ptrListView.setMode(Mode.DISABLED);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(getActivity(), getResources().getString(R.string.net_error));
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.postDelayed(new Runnable() {

					@Override
					public void run() {
						ptrListView.onRefreshComplete();
					}
				}, 1000);
				ptrListView.setMode(Mode.DISABLED);
			}
		});

	}

	class Myadapter extends BaseAdapter {
		@Override
		public int getCount() {
			return demandList.size();
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
			View view = mInflater.inflate(R.layout.item_mydemand, null);
			DemandList demandInfo = demandList.get(position);
			TextView tvState = (TextView) view.findViewById(R.id.tv_mydemand_state);
			TextView tvName = (TextView) view.findViewById(R.id.tv_item_demandname);
			TextView tvType = (TextView) view.findViewById(R.id.tv_mydemand_type);
			TextView tvAddress = (TextView) view.findViewById(R.id.tv_mydemand_address);
			TextView tvArea = (TextView) view.findViewById(R.id.tv_mydemand_area);
			TextView tvUniqueNum = (TextView) view.findViewById(R.id.tv_mydemand_uniqueNum);
			TextView tvBusiness = (TextView) view.findViewById(R.id.tv_mydemand_business);
			TextView tvPrice = (TextView) view.findViewById(R.id.tv_mydemand_price);
			TextView tvCreateTime = (TextView) view.findViewById(R.id.tv_mydemand_createTime);
			tvName.setText(demandInfo.getDemandTypeName());
			tvType.setText(demandInfo.getHouseTypeName());
			tvAddress.setText(demandInfo.getAreaName());
			String square = demandInfo.getSquare();
			if (!TextUtils.isEmpty(square)) {
				tvArea.setText(square);
			} else {
				tvArea.setText("无");
			}

			tvUniqueNum.setText(demandInfo.getUniqueNum());
			tvBusiness.setText(demandInfo.getBusinessName());

			// String price = demandInfo.getPrice();
			// if (!TextUtils.isEmpty(price)) {
			// boolean isNum = price.matches("[0-9]*");
			// if (isNum) {
			// tvPrice.setText(price);
			// }
			// }
			if (!TextUtils.isEmpty(demandInfo.getPrice())) {
				String price = demandInfo.getPrice();
				boolean onlyNum = price.matches("[0-9]{1,}");

				// 判断字符串是否含有数字，如果有直接显示没有不显示
				Pattern p = Pattern.compile(".*\\d+.*");
				Matcher m = p.matcher(price);
				boolean containNum = m.matches();
				if (onlyNum) {
					if (demandInfo.getDemandTypeName().equals("二手房出售")) {
						tvPrice.setText(price + "万");
					} else if (demandInfo.getDemandTypeName().equals("一手房求购")) {
						tvPrice.setText(price + "万");
					} else {
						demandInfo.getDemandTypeName().equals("出租");
						tvPrice.setText(price + "元/月");
					}
				} else {
					if (containNum) {
						tvPrice.setText(price);
					}
				}
			}

			tvCreateTime.setText(convertTime(demandInfo.getCreatedTime()));
			switch (demandInfo.getState()) {
			case 0:
				tvState.setText("待抢单");
				tvState.setTextColor(Color.parseColor("#08CB3F"));
				break;
			case 1:
				tvState.setText("已抢单");
				tvState.setTextColor(Color.parseColor("#E42414"));
				break;
			case 2:
				tvState.setText("取消需求");
				tvState.setTextColor(Color.parseColor("#919191"));
				break;
			case 4:
				tvState.setText("进行中");
				tvState.setTextColor(Color.parseColor("#E42414"));
				break;
			case 5:
				tvState.setText("已结束");
				tvState.setTextColor(Color.parseColor("#08CB3F"));
				break;

			default:
				break;
			}
			return view;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		position -= mListView.getHeaderViewsCount();
		DemandList demandInfo = demandList.get(position);
		Intent demandxqIntent = new Intent(getActivity(), DemandDetaliActivity.class);
		Integer demandId = demandInfo.getDemandId();
		// demandxqIntent.putExtra("advicesId", demandInfo.get)
		demandxqIntent.putExtra("demandId", demandId);
		startActivity(demandxqIntent);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum = 1;
		isRefresh = true;
		initData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum++;
		isRefresh = false;
		initData();
	}

	@Override
	public void onLoadData() {
		initData();
	}

	private String convertTime(long mills) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(mills);
		return formatter.format(calendar.getTime());
	}

	@Override
	public void initView() {
		if (demandList.size() <= 0) {
			initData();
		} else {
			adapter.notifyDataSetChanged();
		}
	}

}
