package com.zorasun.fangchanzhichuang.section.my;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.my.entiy.MyDemandEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.MyDemandEntity.DemandList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MyDemandActivity extends BaseActivity
		implements OnItemClickListener, OnClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private ArrayList<View> list = new ArrayList<>();
	private ArrayList<TextView> textViewlist = new ArrayList<>();
	private int tab;
	private int pageNum = 1;
	private int type;
	private List<DemandList> demandList = new ArrayList<>();
	private PullToRefreshListView ptrListView;
	private ListView mListView;
	private boolean isRefresh;
	private Integer pageCount;
	private Myadapter adapter;
	private boolean login;
	private CustomView customview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mydemand);

		initTitle();
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		login = AccountConfig.isLogin();
		if (login) {
			demandList.clear();
			initData();
		} else {
			ToastUtil.toastShow(this, "请先登录");
		}
	}

	private void initData() {
		MyApi.getInstance().requestDemand(this, pageNum, type, new RequestCallBack() {

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
				ToastUtil.toastShow(MyDemandActivity.this, getResources().getString(R.string.net_error));
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_ERROR);
				ptrListView.setMode(Mode.DISABLED);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(MyDemandActivity.this, getResources().getString(R.string.net_error));
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

	private void initTitle() {
		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("我的需求");
	}

	private void initView() {
		findViewById(R.id.title_left).setOnClickListener(this);
		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);

		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);

		mListView = ptrListView.getRefreshableView();
		adapter = new Myadapter();
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);

		findViewById(R.id.ly_quanbu).setOnClickListener(this);
		findViewById(R.id.ly_secondQiugou).setOnClickListener(this);
		findViewById(R.id.ly_qiuZu).setOnClickListener(this);
		findViewById(R.id.ly_secondQiushou).setOnClickListener(this);
		findViewById(R.id.ly_chuZu).setOnClickListener(this);
		findViewById(R.id.ll_qiugouxinfang).setOnClickListener(this);

		TextView tv1 = (TextView) findViewById(R.id.tv_quanbu);
		TextView tv2 = (TextView) findViewById(R.id.tv_secondQiugou);
		TextView tv3 = (TextView) findViewById(R.id.tv_qiuZu);
		TextView tv4 = (TextView) findViewById(R.id.tv_secondQiushou);
		TextView tv5 = (TextView) findViewById(R.id.tv_chuZu);
		TextView tv6 = (TextView) findViewById(R.id.tv_qiugouxinfang);

		textViewlist.add(tv1);
		textViewlist.add(tv2);
		textViewlist.add(tv3);
		textViewlist.add(tv4);
		textViewlist.add(tv5);
		textViewlist.add(tv6);

		ImageView img_line1 = (ImageView) findViewById(R.id.img_line1);
		ImageView img_line2 = (ImageView) findViewById(R.id.img_line2);
		ImageView img_line3 = (ImageView) findViewById(R.id.img_line3);
		ImageView img_line4 = (ImageView) findViewById(R.id.img_line4);
		ImageView img_line5 = (ImageView) findViewById(R.id.img_line5);
		ImageView img_line6 = (ImageView) findViewById(R.id.img_line6);
		list.add(img_line1);
		list.add(img_line2);
		list.add(img_line3);
		list.add(img_line4);
		list.add(img_line5);
		list.add(img_line6);

	}

	// 设置选中字体的颜色及下划线
	public void selectState(int select) {
		for (int i = 0; i < list.size(); i++) {
			if (i == select) {
				list.get(i).setVisibility(View.VISIBLE);
				textViewlist.get(i).setTextColor(Color.parseColor("#0089fe"));
			} else {
				list.get(i).setVisibility(View.GONE);
				textViewlist.get(i).setTextColor(Color.parseColor("#666666"));
			}
		}
		type = select;
		mListView.setSelection(1);
		demandList.clear();
		adapter.notifyDataSetChanged();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ly_quanbu:
			tab = 0;
			pageNum = 1;
			selectState(0);
			break;
		//
		case R.id.ly_secondQiugou:
			tab = 1;
			pageNum = 1;
			selectState(1);
			break;
		case R.id.ly_qiuZu:
			tab = 2;
			pageNum = 1;
			selectState(2);
			break;
		case R.id.ly_secondQiushou:
			tab = 3;
			pageNum = 1;
			selectState(3);
			break;
		case R.id.ly_chuZu:
			pageNum = 1;
			selectState(4);
			tab = 4;
			break;
		case R.id.ll_qiugouxinfang:
			pageNum = 1;
			selectState(5);
			tab = 5;
			break;
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;

		default:
			break;
		}
	}

	class Myadapter extends BaseAdapter {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
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
			View view = getLayoutInflater().inflate(R.layout.item_mydemand, null);
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

	private String convertTime(long mills) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(mills);
		return formatter.format(calendar.getTime());
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		position -= mListView.getHeaderViewsCount();
		DemandList demandInfo = demandList.get(position);
		Intent demandxqIntent = new Intent(this, DemandDetaliActivity.class);
		Integer demandId = demandList.get(position).getDemandId();
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

}
