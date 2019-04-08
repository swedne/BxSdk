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
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouRentHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouRentHouseEntity.RentHouseList;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouSecondHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouSecondHouseEntity.SecondHouseList;

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

public class JiGouHouseResActivity extends BaseActivity
		implements OnClickListener, OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {
	private TextView tv_secondhouse;
	private TextView tv_shangpu;
	private TextView tv_xiezilou;
	private TextView tv_ll_zhuzhai;
	private TextView lin01;
	private TextView lin02;
	private TextView lin03;
	private TextView lin04;
	private int tab = 0;
	private Myadapter myAdapter;
	private int intExtra;
	private int agencyId;
	private int pageNum = 1;
	private String houseResourceTypeName;
	private int houseTypeId = 1;
	private List<Object> houseList = new ArrayList<>();
	private ListView mListView;
	private PullToRefreshListView ptrListView;
	private boolean isRefresh;
	private int pageCount;
	private RequestParams params;
	private PopupWindow popupWindow;
	private ArrayList<String> list = new ArrayList<>();
	protected String paiXu = "0";
	protected int orderBy = -1;
	protected String paiXuKey = "defaultOrder";
	private int popIndex;
	public int index = -1;
	private CustomView customview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ji_gou_house_res);

		intExtra = getIntent().getIntExtra("houseres", 0);
		agencyId = getIntent().getIntExtra("agencyId", -1);
		if (params == null) {
			// params.put("defaultOrder", "默认排序");
			params = new RequestParams();
		}
		params.put("pageNum", pageNum);
		params.put("houseTypeId", houseTypeId);
		params.put("agencyId", agencyId);
		if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
			houseResourceTypeName = "二手房";
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put(paiXuKey, paiXu);
			initData();
		} else {
			list.add("默认排序");
			list.add("租金由低到高");
			list.add("租金由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
			houseResourceTypeName = "租房";
			params.put("houseResourceTypeName", houseResourceTypeName);
			initRentData();
		}
		initview();
	}

	private void initData() {
		IndexApi.getInstance().requestJiGouHouseResList(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				JiGouSecondHouseEntity jiGouSecondHouseEntity = (JiGouSecondHouseEntity) object;

				if (isRefresh) {
					houseList.clear();
				}
				pageCount = jiGouSecondHouseEntity.getContent().getPageCount();
				if (pageCount <= pageNum) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}
				houseList.addAll(jiGouSecondHouseEntity.getContent().getSecondHouseList());
				if (houseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				myAdapter.notifyDataSetChanged();
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(JiGouHouseResActivity.this, getResources().getString(R.string.net_error));
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
				ptrListView.setMode(Mode.DISABLED);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(JiGouHouseResActivity.this, getResources().getString(R.string.net_error));
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});

	}

	private void initRentData() {
		IndexApi.getInstance().requestJiGouRentHouseResList(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				JiGouRentHouseEntity jiGouRentHouseEntity = (JiGouRentHouseEntity) object;

				if (isRefresh) {
					houseList.clear();
				}
				pageCount = jiGouRentHouseEntity.getContent().getPageCount();
				if (pageCount <= pageNum) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}

				houseList.addAll(jiGouRentHouseEntity.getContent().getRentHouseList());
				if (houseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				myAdapter.notifyDataSetChanged();
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(JiGouHouseResActivity.this, getResources().getString(R.string.net_error));
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
				customview.showLoadStateView(CustomView.STATE_ERROR);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(JiGouHouseResActivity.this, getResources().getString(R.string.net_error));
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
			}
		});

	}

	private void initview() {
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
			tvTitle.setText("二手房源");
		} else if (intExtra == SystemConstant.JIGOU_RENTHOUSR) {
			tvTitle.setText("出租房源");
		}
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
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
		findViewById(R.id.img_paixu).setOnClickListener(this);

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

	private void showSecondPaiXuWindow() {
		list.clear();
		if (houseTypeId == 1) {
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
		params = new RequestParams();
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
				index = position;
				popIndex = 1;
				if (houseTypeId == 1) {
					if (position == 0) {
						paiXu = "默认排序";
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
				params.put("houseTypeId", houseTypeId);
				params.put("houseResourceTypeName", houseResourceTypeName);
				params.put("agencyId", agencyId);
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
				index = position;
				popIndex = 1;
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
				params.put("pageNum", pageNum);
				params.put("houseTypeId", houseTypeId);
				params.put("houseResourceTypeName", houseResourceTypeName);
				params.put("agencyId", agencyId);
				params.put(paiXuKey, paiXu);
				if (position != 0) {
					params.put("orderBy", orderBy);
				}
				mListView.setSelection(1);
				paixuAdapter.notifyDataSetChanged();
				initRentData();
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
			houseTypeId = 1;
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			myAdapter.notifyDataSetChanged();
			paiXu = "0";
			paiXuKey = "defaultOrder";
			params.put(paiXuKey, paiXu);
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("agencyId", agencyId);
			if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
				initData();
			} else {
				initRentData();
			}
			tv_secondhouse.setTextColor(this.getResources().getColorStateList(R.color.title_bg));
			lin01.setBackgroundColor(getResources().getColor(R.color.title_bg));
			tv_shangpu.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin02.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			tv_xiezilou.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			tv_ll_zhuzhai.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin03.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			myAdapter.notifyDataSetChanged();
			lin04.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			break;
		case R.id.ll_shangpu:
			index = -1;
			popIndex = 0;
			tab = 1;
			houseTypeId = 2;
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			myAdapter.notifyDataSetChanged();
			paiXu = "0";
			paiXuKey = "defaultOrder";
			params.put(paiXuKey, paiXu);
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("agencyId", agencyId);
			if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
				initData();
			} else {
				initRentData();
			}
			tv_secondhouse.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin01.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			tv_shangpu.setTextColor(this.getResources().getColorStateList(R.color.title_bg));
			lin02.setBackgroundColor(getResources().getColor(R.color.title_bg));
			tv_xiezilou.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin03.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			tv_ll_zhuzhai.setTextColor(this.getResources().getColorStateList(R.color.txt_gray));
			lin04.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			myAdapter.notifyDataSetChanged();
			break;
		case R.id.ll_xiezilou:
			index = -1;
			popIndex = 0;
			tab = 2;
			houseTypeId = 3;
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			myAdapter.notifyDataSetChanged();
			paiXu = "0";
			paiXuKey = "defaultOrder";
			params.put(paiXuKey, paiXu);
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("agencyId", agencyId);
			if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
				initData();
			} else {
				initRentData();
			}
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
			index = -1;
			popIndex = 0;
			tab = 3;
			houseTypeId = 4;
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			myAdapter.notifyDataSetChanged();
			paiXu = "0";
			paiXuKey = "defaultOrder";
			params.put(paiXuKey, paiXu);
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("agencyId", agencyId);
			if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
				initData();
			} else {
				initRentData();
			}
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
		case R.id.img_paixu:
			if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
				showSecondPaiXuWindow();
			} else if (intExtra == SystemConstant.JIGOU_RENTHOUSR) {
				showRentPaiXuWindow();
			}
			break;

		default:
			break;
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = null;

			Object houseResInfo1 = houseList.get(position);

			if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
				SecondHouseList houseResInfo = (SecondHouseList) houseResInfo1;
				inflate = getLayoutInflater().inflate(R.layout.item_secondhouse, null);
				LinearLayout llSpecial = (LinearLayout) inflate.findViewById(R.id.ll_secondHouseSpecialist);
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
				ImageView imgAuth1 = (ImageView) inflate.findViewById(R.id.img_renzheng1);
				ImageView img2 = (ImageView) inflate.findViewById(R.id.imageView1);
				View imgRenZhengNoPrice = inflate.findViewById(R.id.img_renzheng_web_noprice);
				View imgWeiRenZhengNoPrice = inflate.findViewById(R.id.img_weirenzheng_web_noprice);

				RelativeLayout rl_secondhandhouse = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse);
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
					tvArealistName.setText(houseResInfo.areaListName);
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
					if (!TextUtils.isEmpty(houseResInfo.getPicUrl())) {
						AsyncImageLoader.setAsynImages(img1, houseResInfo.getPicUrl());
					}

					if (houseResInfo.getIsAuth() == 0) {
						imgRenZheng.setVisibility(View.GONE);
						imgWeiRenZheng.setVisibility(View.VISIBLE);
					} else {
						imgWeiRenZheng.setVisibility(View.GONE);
						imgRenZheng.setVisibility(View.VISIBLE);
					}

					List<String> specials = houseResInfo.getSpecials();
					String color = "";
					if (specials.size() > 0) {
						for (int i = 0; i < specials.size(); i++) {
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
							tvHouseTag.setText(specials.get(i));
							llSpecial.addView(child);
						}
					} else {
						View child = getLayoutInflater().inflate(R.layout.item_text, null);
						TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
						tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
						tvHouseTag.setText("暂无");
						llSpecial.addView(child);
					}
				} else {
					rl_secondhandhouse.setVisibility(View.VISIBLE);
					rl_secondhandhouse02.setVisibility(View.GONE);
					TextView tvPrice1 = (TextView) inflate.findViewById(R.id.tv_price1);
					tvPrice1.setText(houseResInfo.getPrice()+"元/平");
					tvTitle2.setText(houseResInfo.getTitle());
					tvArealistName2.setText(houseResInfo.areaListName);
					tvSalePrice2.setText(houseResInfo.getSalePrice() + "");
					if (!TextUtils.isEmpty(houseResInfo.getPicUrl())) {
						AsyncImageLoader.setAsynImages(img2, houseResInfo.getPicUrl());
					}

					if (tab == 3) {
						if (!TextUtils.isEmpty(houseResInfo.plantArea)) {
							tvBerryGG2.setText(houseResInfo.plantArea + "㎡");
						} else {
							tvBerryGG2.setText("-");
						}
					} else {
						if (!TextUtils.isEmpty(houseResInfo.getBerryGG())) {
							tvBerryGG2.setText(houseResInfo.getBerryGG() + "㎡");
						} else {
							tvBerryGG2.setText("-");

						}
					}

					if (houseResInfo.getIsAuth() == 0) {
						imgRenZhengNoPrice.setVisibility(View.GONE);
						imgWeiRenZhengNoPrice.setVisibility(View.VISIBLE);
					} else {
						imgWeiRenZhengNoPrice.setVisibility(View.GONE);
						imgRenZhengNoPrice.setVisibility(View.VISIBLE);
					}

				}
			} else {
				RentHouseList houseResInfo = (RentHouseList) houseResInfo1;
				inflate = getLayoutInflater().inflate(R.layout.index_zufang_item, null);
				TextView tvRentTitle = (TextView) inflate.findViewById(R.id.tv_title);
				TextView tvRentAreaListName = (TextView) inflate.findViewById(R.id.tv_areaListName);
				TextView tvRentMoney = (TextView) inflate.findViewById(R.id.tv_rentMoney);
				TextView tvRentBerry = (TextView) inflate.findViewById(R.id.tv_rent_berry);
				ImageView imgPic = (ImageView) inflate.findViewById(R.id.img_newhousepic);
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
				} else {
					inflate.findViewById(R.id.ll_housetype).setVisibility(View.GONE);

				}

				if (!TextUtils.isEmpty(houseResInfo.getPicUrl())) {
					AsyncImageLoader.setAsynImages(imgPic, houseResInfo.getPicUrl());
				}
				LinearLayout llRentSpecial = (LinearLayout) inflate.findViewById(R.id.ll_special);
				if (tab != 0) {
					tvRentBerry.setVisibility(View.VISIBLE);
					llRentSpecial.setVisibility(View.GONE);
					if (tab == 3) {
						if (!TextUtils.isEmpty(houseResInfo.plantArea)) {
							tvRentBerry.setText(houseResInfo.plantArea + "㎡");
						} else {
							tvRentBerry.setText("-");
						}
					} else {
						if (!TextUtils.isEmpty(houseResInfo.getBerryGG())) {
							tvRentBerry.setText(houseResInfo.getBerryGG() + "㎡");
						} else {
							tvRentBerry.setText("-");

						}
					}
				}
				tvRentTitle.setText(houseResInfo.getTitle());
				tvRentAreaListName.setText(houseResInfo.areaListName);
				if (!TextUtils.isEmpty(houseResInfo.getRental())) {
					tvRentMoney.setText(houseResInfo.getRental() + "");
				} else {
					tvRentMoney.setText("-");

				}

				List<String> specials = houseResInfo.getSpecials();
				String color = "";
				if (specials.size() > 0) {
					for (int i = 0; i < specials.size(); i++) {
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
						tvHouseTag.setText(specials.get(i));
						llRentSpecial.addView(child);
					}
				} else {
					View child = getLayoutInflater().inflate(R.layout.item_text, null);
					TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
					tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
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
		Object houseinfo1 = houseList.get(position);
		Intent intent = null;
		if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
			SecondHouseList houseinfo = (SecondHouseList) houseinfo1;
			if (tab == 0) {
				intent = new Intent(this, SecondHouseDetailActivity.class);
				intent.putExtra("secondHouseId", houseinfo.id);
				intent.putExtra("areaListId", houseinfo.getAreaListId());
			} else if (tab == 1) {
				intent = new Intent(this, ShangPuDetailActivity.class);
				intent.putExtra("id", houseinfo.id);
				intent.putExtra("selectTypeId", 1);
			} else if (tab == 2) {
				intent = new Intent(this, XieZiLouActivity.class);
				intent.putExtra("id", houseinfo.id);
				intent.putExtra("selectTypeId", 5);
			} else if (tab == 3) {
				intent = new Intent(this, ChangFangDetailActivity.class);
				intent.putExtra("id", houseinfo.id);
				intent.putExtra("selectTypeId", 3);
			}
			startActivity(intent);
		} else {
			RentHouseList houseinfo = (RentHouseList) houseinfo1;
			if (tab == 0) {
				intent = new Intent(this, ZuFangxqActivity.class);
				intent.putExtra("rentHouseId", houseinfo.id);
			} else if (tab == 1) {
				intent = new Intent(this, ShangPuDetailActivity.class);
				intent.putExtra("id", houseinfo.id);
				intent.putExtra("selectTypeId", 0);
			} else if (tab == 2) {
				intent = new Intent(this, XieZiLouActivity.class);
				intent.putExtra("id", houseinfo.id);
				intent.putExtra("selectTypeId", 4);
			} else if (tab == 3) {
				intent = new Intent(this, ChangFangDetailActivity.class);
				intent.putExtra("id", houseinfo.id);
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
			houseTypeId = 1;
		} else if (tab == 1) {
			houseTypeId = 2;
		} else if (tab == 2) {
			houseTypeId = 3;
		} else if (tab == 3) {
			houseTypeId = 4;
		}

		if (popIndex == 0) {
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("agencyId", agencyId);
		} else if (popIndex == 1) {
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("agencyId", agencyId);
			params.put(paiXuKey, paiXu);
			params.put("orderBy", orderBy);
		}
		if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
			initData();
		} else {
			initRentData();
		}
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		isRefresh = false;
		pageNum++;
		if (tab == 0) {
			houseTypeId = 1;
		} else if (tab == 1) {
			houseTypeId = 2;
		} else if (tab == 2) {
			houseTypeId = 3;
		} else if (tab == 3) {
			houseTypeId = 4;
		}
		if (popIndex == 0) {
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("agencyId", agencyId);
		} else if (popIndex == 1) {
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("houseResourceTypeName", houseResourceTypeName);
			params.put("agencyId", agencyId);
			params.put(paiXuKey, paiXu);
			if (orderBy != -1) {
				params.put("orderBy", orderBy);
			}
		}
		if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
			initData();
		} else {
			initRentData();
		}
	}

	@Override
	public void onLoadData() {
		if (intExtra == SystemConstant.JIGOU_SECONDHOUSE) {
			initData();
		} else {
			initRentData();
		}
	}

}
