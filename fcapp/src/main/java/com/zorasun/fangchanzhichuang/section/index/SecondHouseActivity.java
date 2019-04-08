package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.List;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.commonadapter.Common2Adapter;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerHouseResEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerHouseResEntity.Broker;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerHouseResEntity.HouseList;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerHouseResEntity.SpecialtyListForList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SecondHouseActivity extends BaseActivity
		implements OnClickListener, OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {
	private TextView tv_secondhouse;
	private TextView tv_shangpu;
	private TextView tv_xiezilou
	;
	private TextView tv_ll_zhuzhai;
	private TextView lin01;
	private TextView lin02;
	private TextView lin03;
	private TextView lin04;
	private int tab = 0;
	private Myadapter myAdapter;
	private int intExtra;
	private int brokerId;
	private int pageNum = 1;
	private String houseResourceTypeName;
	private String houseTypeName = "住宅";
	private List<HouseList> houseList = new ArrayList<>();
	private TextView tvBrokerName;
	private TextView tvIsExpert;
	private String houseTypeName2;
	private ListView mListView;
	private PullToRefreshListView ptrListView;
	private boolean isRefresh;
	private int pageCount;
	private Broker broker;
	private RequestParams params;
	private int popIndex;
	private PopupWindow popupWindow;
	private String paiXu = "0";
	private String paiXuKey = "defaultOrder";
	protected int orderBy = -1;
	private ArrayList<String> list = new ArrayList<>();
	public int index = -1;
	private CustomView customview;
	private ImageView imgBroker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secondhouse);

		intExtra = getIntent().getIntExtra("houseres", 0);
		params = new RequestParams();
		if (intExtra == SystemConstant.JINGJIREN_SECONDHOUSE) {
			houseResourceTypeName = "二手房";
			list.add("默认排序");
			list.add("总价由低到高");
			list.add("总价由高到低");
			// list.add("单价由低到高");
			// list.add("单价由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
			params.put(paiXuKey, paiXu);
		} else {
			list.add("默认排序");
			list.add("租金由低到高");
			list.add("租金由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
			houseResourceTypeName = "租房";
		}
		brokerId = getIntent().getIntExtra("brokerId", -1);
		initview();
		params.put("pageNum", pageNum);
		params.put("houseTypeName", houseTypeName);
		params.put("houseResourceTypeName", houseResourceTypeName);
		params.put("brokerId", brokerId);
		params.put("defaultOrder", "默认排序");
		initData();
	}

	private void initData() {
		IndexApi.getInstance().requestBrokerHouseResList(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				BrokerHouseResEntity brokerHouseResEntity = (BrokerHouseResEntity) object;
				broker = brokerHouseResEntity.getContent().getBroker();
				houseTypeName2 = brokerHouseResEntity.getContent().getHouseTypeName();

				if (isRefresh) {
					houseList.clear();
				}
				houseList.addAll(brokerHouseResEntity.getContent().getHouseList());
				if (houseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}

				tvBrokerName.setText(brokerHouseResEntity.getContent().getBrokerName());
				if (!TextUtils.isEmpty(brokerHouseResEntity.getContent().getBroker().getHeadUrl())) {
					AsyncImageLoader.setAsynImages(imgBroker,
							brokerHouseResEntity.getContent().getBroker().getHeadUrl());
				}
//				if (broker.getIsExpert() == 1) {
//					tvIsExpert.setVisibility(View.VISIBLE);
//				} else {
//					tvIsExpert.setVisibility(View.GONE);
//				}
				tvIsExpert.setText(broker.getRealName());
				pageCount = brokerHouseResEntity.getContent().getPageCount();
				if (pageCount <= pageNum) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}
				myAdapter.notifyDataSetChanged();
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(SecondHouseActivity.this, getResources().getString(R.string.net_error));
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
				ToastUtil.toastShow(SecondHouseActivity.this, msg);
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});

	}

	private void initview() {
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		View rlBottom = findViewById(R.id.rl_bottom);
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		if (intExtra == SystemConstant.JINGJIREN_SECONDHOUSE) {
			tvTitle.setText("二手房源");
			rlBottom.setVisibility(View.VISIBLE);
		} else {
			tvTitle.setText("出租房源");
			rlBottom.setVisibility(View.VISIBLE);
		}
		LinearLayout ll_secondhouse = (LinearLayout) findViewById(R.id.ll_secondhouse);
		LinearLayout ll_shangpu = (LinearLayout) findViewById(R.id.ll_shangpu);
		LinearLayout ll_xiezilou = (LinearLayout) findViewById(R.id.ll_xiezilou);
		LinearLayout ll_changfang = (LinearLayout) findViewById(R.id.ll_changfang);
		tv_secondhouse = (TextView) findViewById(R.id.tv_secondhouse);
		tv_shangpu = (TextView) findViewById(R.id.tv_shangpu);
		tv_xiezilou = (TextView) findViewById(R.id.tv_xiezilou);
		tv_ll_zhuzhai = (TextView) findViewById(R.id.tv_ll_zhuzhai);
		lin01 = (TextView) findViewById(R.id.lin01);
		lin02 = (TextView) findViewById(R.id.lin02);
		lin03 = (TextView) findViewById(R.id.lin03);
		lin04 = (TextView) findViewById(R.id.lin04);
		ll_secondhouse.setOnClickListener(this);
		ll_shangpu.setOnClickListener(this);
		ll_xiezilou.setOnClickListener(this);
		ll_changfang.setOnClickListener(this);

		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);

		mListView = ptrListView.getRefreshableView();
		myAdapter = new Myadapter();
		mListView.setAdapter(myAdapter);
		mListView.setOnItemClickListener(this);

		tvBrokerName = (TextView) findViewById(R.id.tv_brokerName);
		tvIsExpert = (TextView) findViewById(R.id.tv_isExpert);
		findViewById(R.id.rl_call).setOnClickListener(this);
		findViewById(R.id.rl_sendMSG).setOnClickListener(this);
		imgBroker = (ImageView) findViewById(R.id.img_broker);
		imgBroker.setOnClickListener(this);
		findViewById(R.id.img_paixu).setOnClickListener(this);

	}

	private void showCallWindow() {
		View view = getLayoutInflater().inflate(R.layout.item_pop_call, null);
		final PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		view.findViewById(R.id.textView1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		view.findViewById(R.id.tv_niming_call).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		view.findViewById(R.id.tv_call).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CommonUtils.call(SecondHouseActivity.this, broker.getMobile());
			}
		});
		view.findViewById(R.id.tv_cancle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
	}

	@Override
	public void onClick(View v) {
		params = new RequestParams();
		switch (v.getId()) {
		case R.id.ll_secondhouse:
			popIndex = 0;
			tab = 0;
			index = -1;
			houseTypeName = "住宅";
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			myAdapter.notifyDataSetChanged();
			params = new RequestParams();
			paiXu = "0";
			paiXuKey = "defaultOrder";
			params.put(paiXuKey, paiXu);
			params.put("pageNum", pageNum);
			params.put("houseTypeName", houseTypeName);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("brokerId", brokerId);
			initData();
			tv_secondhouse.setTextColor(this.getResources().getColorStateList(R.color.title_bg));
			lin01.setBackgroundResource(R.color.title_bg);
			tv_shangpu.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin02.setBackgroundResource(R.color.txt_gray);
			tv_xiezilou.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			tv_ll_zhuzhai.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin03.setBackgroundResource(R.color.txt_gray);
			myAdapter.notifyDataSetChanged();
			lin04.setBackgroundResource(R.color.txt_gray);
			break;
		case R.id.ll_shangpu:
			popIndex = 0;
			tab = 1;
			index = -1;
			houseTypeName = "商铺";
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			myAdapter.notifyDataSetChanged();
			params = new RequestParams();
			paiXu = "0";
			paiXuKey = "defaultOrder";
			params.put(paiXuKey, paiXu);
			params.put("pageNum", pageNum);
			params.put("houseTypeName", houseTypeName);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("brokerId", brokerId);
			initData();
			tv_secondhouse.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin01.setBackgroundResource(R.color.txt_gray);
			tv_shangpu.setTextColor(this.getResources().getColorStateList(R.color.title_bg));
			lin02.setBackgroundResource(R.color.title_bg);
			tv_xiezilou.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin03.setBackgroundResource(R.color.txt_gray);
			tv_ll_zhuzhai.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin04.setBackgroundResource(R.color.txt_gray);
			myAdapter.notifyDataSetChanged();
			break;
		case R.id.ll_xiezilou:
			popIndex = 0;
			tab = 2;
			index = -1;
			houseTypeName = "写字楼";
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			myAdapter.notifyDataSetChanged();
			params = new RequestParams();
			paiXu = "0";
			paiXuKey = "defaultOrder";
			params.put(paiXuKey, paiXu);
			params.put("pageNum", pageNum);
			params.put("houseTypeName", houseTypeName);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("brokerId", brokerId);
			initData();
			tv_secondhouse.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin01.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			tv_shangpu.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin02.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			tv_xiezilou.setTextColor(this.getResources().getColorStateList(R.color.title_bg));
			lin03.setBackgroundColor(getResources().getColor(R.color.title_bg));
			tv_ll_zhuzhai.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin04.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			myAdapter.notifyDataSetChanged();

			break;
		case R.id.ll_changfang:
			popIndex = 0;
			tab = 3;
			index = -1;
			houseTypeName = "厂房";
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			myAdapter.notifyDataSetChanged();
			params = new RequestParams();
			paiXu = "0";
			paiXuKey = "defaultOrder";
			params.put(paiXuKey, paiXu);
			params.put("pageNum", pageNum);
			params.put("houseTypeName", houseTypeName);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("brokerId", brokerId);
			initData();
			tv_secondhouse.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin01.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			tv_shangpu.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin02.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			tv_xiezilou.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin03.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			tv_ll_zhuzhai.setTextColor(this.getResources().getColorStateList(R.color.title_bg));
			lin04.setBackgroundColor(getResources().getColor(R.color.title_bg));
			myAdapter.notifyDataSetChanged();

			break;
		case R.id.rl_call:
			showCallWindow();
			break;
		case R.id.rl_sendMSG:
			sendSmsWithNumber(this, broker.getMobile());
			break;
		case R.id.img_broker:
			Intent intent = new Intent(this, JingjirenXqActivity.class);
			intent.putExtra("brokerId", brokerId);
			startActivity(intent);
			break;
		case R.id.img_paixu:

			if (intExtra == SystemConstant.JINGJIREN_SECONDHOUSE) {
				showSecondXuWindow();
			} else {
				showRentPaiXuWindow();
			}
			break;
		default:
			break;
		}
	}

	private void showSecondXuWindow() {
		list.clear();
		if (tab == 0) {
			list.add("默认排序");
			list.add("总价由低到高");
			list.add("总价由高到低");
			// list.add("单价由低到高");
			// list.add("单价由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
		} else {
			list.add("默认排序");
			list.add("总价由低到高");
			list.add("总价由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
		}
		View view = getLayoutInflater().inflate(R.layout.item_listview, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyle);
		popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		ListView paiXuList = (ListView) view.findViewById(R.id.list_PaiXu);
		final PaiXuPopAdapter paixuAdapter = new PaiXuPopAdapter(this, list, R.layout.paixu_search_item);
		paixuAdapter.notifyDataSetChanged();
		paiXuList.setAdapter(paixuAdapter);
		view.findViewById(R.id.textView1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		paiXuList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				popIndex = 1;
				index = position;
				if (tab == 0) {
					if (position == 0) {
						paiXu = "0";
						paiXuKey = "defaultOrder";
						orderBy = -1;
					} else if (position == 1) {
						paiXu = "总价由低到高";
						paiXuKey = "totalPriceOrder";
						orderBy = 1;
					} else if (position == 2) {
						paiXu = "总价由高到低";
						paiXuKey = "totalPriceOrder";
						orderBy = 0;

					}
					// else if (position == 3) {
					// paiXu = "单价由低到高";
					// paiXuKey = "priceOrder";
					// orderBy = 1;
					//
					// } else if (position == 4) {
					// paiXu = "单价由高到低";
					// paiXuKey = "priceOrder";
					// orderBy = 0;
					// }
					else if (position == 3) {
						paiXu = "面积由小到大";
						paiXuKey = "berryggOrder";
						orderBy = 1;
					} else if (position == 4) {
						paiXu = "面积由大到小";
						paiXuKey = "berryggOrder";
						orderBy = 0;
					}
				} else {
					if (position == 0) {
						paiXu = "默认排序";
						paiXuKey = "defaultOrder";
					} else if (position == 1) {
						paiXu = "总价由低到高";
						paiXuKey = "totalPriceOrder";
						orderBy = 1;
					} else if (position == 2) {
						paiXu = "总价由高到低";
						paiXuKey = "totalPriceOrder";
						orderBy = 0;
					} else if (position == 3) {
						paiXu = "面积由小到大";
						if (tab == 3) {
							paiXuKey = "plantAcreageOrder";
						} else {
							paiXuKey = "berryggOrder";
						}
						orderBy = 1;
					} else if (position == 4) {
						paiXu = "面积由大到小";
						if (tab == 3) {
							paiXuKey = "plantAcreageOrder";
						} else {
							paiXuKey = "berryggOrder";
						}
						orderBy = 0;
					}
				}
				pageNum = 1;
				houseList.clear();
				myAdapter.notifyDataSetChanged();
				params.put("pageNum", pageNum);
				params.put("houseTypeName", houseTypeName);
				params.put("houseResourceTypeName", houseResourceTypeName);
				params.put("brokerId", brokerId);
				params.put(paiXuKey, paiXu);
				if (position != 0) {
					params.put("orderBy", orderBy);
				}
				mListView.setSelection(1);
				paixuAdapter.notifyDataSetChanged();
				initData();
			}

		});
	}

	private void showRentPaiXuWindow() {
		View view = getLayoutInflater().inflate(R.layout.item_listview, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyle);
		popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		ListView paiXuList = (ListView) view.findViewById(R.id.list_PaiXu);
		final PaiXuPopAdapter paixuAdapter = new PaiXuPopAdapter(this, list, R.layout.paixu_search_item);
		paiXuList.setAdapter(paixuAdapter);
		paixuAdapter.notifyDataSetChanged();
		view.findViewById(R.id.textView1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		paiXuList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				popIndex = 1;
				index = position;
				if (position == 0) {
					paiXu = "默认排序";
					paiXuKey = "defaultOrder";
				} else if (position == 1) {
					paiXu = "租金由低到高";
					paiXuKey = "rentalOrder";
					orderBy = 1;
				} else if (position == 2) {
					paiXu = "租金由高到低";
					paiXuKey = "rentalOrder";
					orderBy = 0;
				} else if (position == 3) {
					paiXu = "面积由小到大";
					if (tab == 3) {
						paiXuKey = "plantAcreageOrder";
					} else {
						paiXuKey = "berryggOrder";
					}
					orderBy = 1;

				} else if (position == 4) {
					paiXu = "面积由大到小";
					if (tab == 3) {
						paiXuKey = "plantAcreageOrder";
					} else {
						paiXuKey = "berryggOrder";
					}
					orderBy = 0;
				}
				pageNum = 1;
				houseList.clear();
				myAdapter.notifyDataSetChanged();
				params.put("pageNum", pageNum);
				params.put("houseTypeName", houseTypeName);
				params.put("houseResourceTypeName", houseResourceTypeName);
				params.put("brokerId", brokerId);
				params.put(paiXuKey, paiXu);
				if (position != 0) {
					params.put("orderBy", orderBy);
				}
				mListView.setSelection(1);
				paixuAdapter.notifyDataSetChanged();
				initData();
			}

		});
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

	public void sendSmsWithNumber(Context context, String number) {
		Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
		context.startActivity(sendIntent);
	}

	class Myadapter extends BaseAdapter {

		@Override
		public int getCount() {
			return houseList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = null;
			HouseList houseResInfo = houseList.get(position);
			if (intExtra == SystemConstant.JIGOU_SECONDHOUSE || intExtra == SystemConstant.JINGJIREN_SECONDHOUSE) {
				inflate = getLayoutInflater().inflate(R.layout.item_secondhouse, null);
				// 二手房行布局
				TextView tvTitle = (TextView) inflate.findViewById(R.id.tv_collect_title01);
				TextView tvRoomHall = (TextView) inflate.findViewById(R.id.tv_room_hall);
				TextView tvBerryGG = (TextView) inflate.findViewById(R.id.tv_berryGG);
				TextView tvArealistName = (TextView) inflate.findViewById(R.id.tv_collect_arealistname);
				TextView tvSalePrice = (TextView) inflate.findViewById(R.id.tv_saleprice);
				TextView tvPrice = (TextView) inflate.findViewById(R.id.tv_price);
				ImageView img1 = (ImageView) inflate.findViewById(R.id.img_newhousepic);
				ImageView imgAuth = (ImageView) inflate.findViewById(R.id.img_isAuth);
				View imgRenZheng = inflate.findViewById(R.id.img_renzheng_web);
				View imgWeiRenZheng = inflate.findViewById(R.id.img_weirenzheng_web);

				// 不同的行布局
				TextView tvTitle2 = (TextView) inflate.findViewById(R.id.tv_collect_title);
				TextView tvArealistName2 = (TextView) inflate.findViewById(R.id.tv_collect_address);
				TextView tvSalePrice2 = (TextView) inflate.findViewById(R.id.tv_collect_price);
				TextView tvBerryGG2 = (TextView) inflate.findViewById(R.id.tv_area);
				ImageView img2 = (ImageView) inflate.findViewById(R.id.imageView1);
				ImageView imgAuth1 = (ImageView) inflate.findViewById(R.id.img_renzheng1);
				View imgRenZhengNoPrice = inflate.findViewById(R.id.img_renzheng_web_noprice);
				View imgWeiRenZhengNoPrice = inflate.findViewById(R.id.img_weirenzheng_web_noprice);

				LinearLayout llSpecial = (LinearLayout) inflate.findViewById(R.id.ll_secondHouseSpecialist);

				RelativeLayout rl_secondhandhouse = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse);
				// 二手房
				RelativeLayout rl_secondhandhouse02 = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse02);
				if (tab == 0) {
					rl_secondhandhouse02.setVisibility(View.VISIBLE);
					rl_secondhandhouse.setVisibility(View.GONE);
					tvTitle.setText(houseResInfo.getTitle());
					String roomNum = houseResInfo.getRoomNum();
					String hallNum = houseResInfo.getHallNum();
					if (TextUtils.isEmpty(roomNum)) {
						roomNum = "0";
					}
					if (TextUtils.isEmpty(hallNum)) {
						hallNum = "0";
					}
					tvRoomHall.setText(roomNum + "室" + hallNum + "厅");
					if (!TextUtils.isEmpty(houseResInfo.getBerryGG())) {
						tvBerryGG.setText(houseResInfo.getBerryGG() + "㎡");
					} else {
						tvBerryGG.setText("0");
					}
					tvArealistName.setText(houseResInfo.getAreaListName());
					if (!TextUtils.isEmpty(houseResInfo.getSalePrice())) {
						tvSalePrice.setText(houseResInfo.getSalePrice() + "");
					} else {
						tvSalePrice.setText("0");
					}
					if (!TextUtils.isEmpty(houseResInfo.getPrice())) {
						tvPrice.setText(houseResInfo.getPrice() + "元/平");
					} else {
						tvPrice.setText("0");

					}
					if (!TextUtils.isEmpty(houseResInfo.getSurFaceUrl())) {
						AsyncImageLoader.setAsynImages(img1, houseResInfo.getSurFaceUrl());
					}

					if (houseResInfo.getIsAuth() == 0) {
						imgRenZheng.setVisibility(View.GONE);
						imgWeiRenZheng.setVisibility(View.VISIBLE);
					} else {
						imgWeiRenZheng.setVisibility(View.GONE);
						imgRenZheng.setVisibility(View.VISIBLE);
					}

					// if (houseResInfo.getIsAuth() == 0) {
					// imgAuth.setVisibility(View.GONE);
					// } else {
					// imgAuth.setVisibility(View.VISIBLE);
					// }
					List<SpecialtyListForList> specialtyListForList = houseResInfo.getSpecialtyListForList();
					String color = "";
					if (specialtyListForList.size() > 0) {
						for (int i = 0; i < specialtyListForList.size(); i++) {
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
							tvHouseTag.setText(specialtyListForList.get(i).getSpecialtyName());
							llSpecial.addView(child);
						}
					} else {
						View child = getLayoutInflater().inflate(R.layout.item_text, null);
						TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
						tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
						color = "#FD641D";
						tvHouseTag.setTextColor(Color.parseColor(color));
						tvHouseTag.setText("暂无");
						llSpecial.addView(child);
					}
				} else {
					rl_secondhandhouse.setVisibility(View.VISIBLE);
					rl_secondhandhouse02.setVisibility(View.GONE);
					TextView tvPrice1 = (TextView) inflate.findViewById(R.id.tv_price1);
					tvPrice1.setText(houseResInfo.getPrice()+"元/平");
					tvTitle2.setText(houseResInfo.getTitle());
					tvArealistName2.setText(houseResInfo.getAreaListName());
					if (!TextUtils.isEmpty(houseResInfo.getSalePrice())) {
						tvSalePrice2.setText(houseResInfo.getSalePrice() + "");
					} else {
						tvSalePrice2.setText("-");
					}
					if (!TextUtils.isEmpty(houseResInfo.getSurFaceUrl())) {
						AsyncImageLoader.setAsynImages(img2, houseResInfo.getSurFaceUrl());
					}

					if (houseResInfo.getIsAuth() == 0) {
						imgRenZhengNoPrice.setVisibility(View.GONE);
						imgWeiRenZhengNoPrice.setVisibility(View.VISIBLE);
					} else {
						imgWeiRenZhengNoPrice.setVisibility(View.GONE);
						imgRenZhengNoPrice.setVisibility(View.VISIBLE);
					}
					// if (houseResInfo.getIsAuth() == 0) {
					// imgAuth1.setVisibility(View.GONE);
					// } else {
					// imgAuth1.setVisibility(View.VISIBLE);
					// }
					if (tab == 3) {
						tvBerryGG2.setText(houseResInfo.getPlantAcreage() + "㎡");
					} else {
						tvBerryGG2.setText(houseResInfo.getBerryGG() + "㎡");
					}
				}

			} else {
				inflate = getLayoutInflater().inflate(R.layout.index_zufang_item, null);
				TextView tvRentTitle = (TextView) inflate.findViewById(R.id.tv_title);
				TextView tvRentAreaListName = (TextView) inflate.findViewById(R.id.tv_areaListName);
				TextView tvRentMoney = (TextView) inflate.findViewById(R.id.tv_rentMoney);
				TextView tvRentBerry = (TextView) inflate.findViewById(R.id.tv_rent_berry);
				ImageView imgPic = (ImageView) inflate.findViewById(R.id.img_newhousepic);
				LinearLayout llRentSpecial = (LinearLayout) inflate.findViewById(R.id.ll_special);
				if (tab == 0) {
					inflate.findViewById(R.id.ll_housetype).setVisibility(View.VISIBLE);
					TextView tvHouseType = (TextView) inflate.findViewById(R.id.tv_housetype);
					TextView tvBerryGG = (TextView) inflate.findViewById(R.id.tv_berryGG);
					if (houseResInfo.getRoomNum() != null && houseResInfo.getHallNum() != null) {
						tvHouseType.setText(houseResInfo.getRoomNum() + "室" + houseResInfo.getHallNum() + "厅");
					} else {
						tvHouseType.setText(0 + "室" + 0 + "厅");
					}
					if (!TextUtils.isEmpty(houseResInfo.getBerryGG())) {
						tvBerryGG.setText(houseResInfo.getBerryGG() + "㎡");
					} else {
						tvBerryGG.setText(0 + "㎡");
					}
					if (!TextUtils.isEmpty(houseResInfo.getSurFaceUrl())) {
						AsyncImageLoader.setAsynImages(imgPic, houseResInfo.getSurFaceUrl());
					}
				} else {
					inflate.findViewById(R.id.ll_housetype).setVisibility(View.GONE);
					if (!TextUtils.isEmpty(houseResInfo.getSurFaceUrl())) {
						AsyncImageLoader.setAsynImages(imgPic, houseResInfo.getSurFaceUrl());
					}
				}
				if (tab != 0) {
					tvRentBerry.setVisibility(View.VISIBLE);
					llRentSpecial.setVisibility(View.GONE);
					if (tab == 3) {
						tvRentBerry.setText(houseResInfo.getPlantAcreage() + "㎡");
					} else {
						tvRentBerry.setText(houseResInfo.getBerryGG() + "㎡");
					}
				}
				tvRentTitle.setText(houseResInfo.getTitle());
				tvRentAreaListName.setText(houseResInfo.getAreaListName());
				tvRentMoney.setText(houseResInfo.getRental() + "");
				List<SpecialtyListForList> specialtyListForList = houseResInfo.getSpecialtyListForList();
				String color = "";
				if (specialtyListForList.size() > 0) {
					for (int i = 0; i < specialtyListForList.size(); i++) {
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
						tvHouseTag.setText(specialtyListForList.get(i).getSpecialtyName());
						llRentSpecial.addView(child);
					}
				} else {
					View child = getLayoutInflater().inflate(R.layout.item_text, null);
					TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
					tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
					color = "#FD641D";
					tvHouseTag.setTextColor(Color.parseColor(color));
					tvHouseTag.setText("暂无");
					llRentSpecial.addView(child);
				}
			}

			return inflate;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		position -= mListView.getHeaderViewsCount();
		HouseList houseinfo = houseList.get(position);
		Intent intent = null;
		if (intExtra == SystemConstant.JIGOU_SECONDHOUSE || intExtra == SystemConstant.JINGJIREN_SECONDHOUSE) {
			if (houseTypeName2.equals("住宅")) {
				intent = new Intent(this, SecondHouseDetailActivity.class);
				intent.putExtra("secondHouseId", houseinfo.getId());
				intent.putExtra("areaListId", houseinfo.getAreaListId());
			} else if (houseTypeName2.equals("商铺")) {
				intent = new Intent(this, ShangPuDetailActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 1);
			} else if (houseTypeName2.equals("写字楼")) {
				intent = new Intent(this, XieZiLouActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 5);
			} else if (houseTypeName2.equals("厂房")) {
				intent = new Intent(this, ChangFangDetailActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 3);
			}

			startActivity(intent);
		} else {
			if (houseTypeName2.equals("住宅")) {
				intent = new Intent(this, ZuFangxqActivity.class);
				intent.putExtra("rentHouseId", houseinfo.getId());
			} else if (houseTypeName2.equals("商铺")) {
				intent = new Intent(this, ShangPuDetailActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 0);
			} else if (houseTypeName2.equals("写字楼")) {
				intent = new Intent(this, XieZiLouActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 4);
			} else if (houseTypeName2.equals("厂房")) {
				intent = new Intent(this, ChangFangDetailActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 2);
			}
			startActivity(intent);
		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum = 1;
		isRefresh = true;
		params = new RequestParams();
		if (tab == 0) {
			houseTypeName = "住宅";
		} else if (tab == 1) {
			houseTypeName = "商铺";
		} else if (tab == 2) {
			houseTypeName = "写字楼";
		} else if (tab == 3) {
			houseTypeName = "厂房";
		}
		params.put("pageNum", pageNum);
		params.put("houseTypeName", houseTypeName);
		params.put("houseResourceTypeName", houseResourceTypeName);
		params.put("brokerId", brokerId);
		params.put(paiXuKey, paiXu);
		if (popIndex == 1) {
			if (orderBy != -1) {
				params.put("orderBy", orderBy);
			}
		}
		initData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum++;
		isRefresh = false;
		params = new RequestParams();
		if (tab == 0) {
			houseTypeName = "住宅";
		} else if (tab == 1) {
			houseTypeName = "商铺";
		} else if (tab == 2) {
			houseTypeName = "写字楼";
		} else if (tab == 3) {
			houseTypeName = "厂房";
		}
		params.put("pageNum", pageNum);
		params.put("houseTypeName", houseTypeName);
		params.put("houseResourceTypeName", houseResourceTypeName);
		params.put("brokerId", brokerId);
		params.put(paiXuKey, paiXu);
		if (popIndex == 1) {
			if (orderBy != -1) {
				params.put("orderBy", orderBy);
			}
		}
		initData();
	}

	@Override
	public void onLoadData() {
		initData();
	}

}
