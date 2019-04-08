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
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreRentHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreRentHouseEntity.Content;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreRentHouseEntity.RentHouseSpecialtyList;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreSecondHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreSecondHouseEntity.NearbyHouseResourceList;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreSecondHouseEntity.SecondHouseSpecialtyList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;

public class FuJinFangActivity extends BaseActivity
		implements OnClickListener, OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private int intExtra;
	private int pageNum = 1;
	private int areaListId;
	private boolean isRefresh;
	private PullToRefreshListView ptrListView;
	private ListView mListView;
	private List<Object> houseList = new ArrayList<>();
	private int pageCount;
	private MyAdapter adapter;
	private RequestParams params;
	private int sign = 2;
	private ArrayList<String> list = new ArrayList<>();
	private PopupWindow popupWindow;
	protected int popIndex;
	protected String paiXu = "0";
	protected String paiXuKey = "defaultOrder";
	protected int orderBy = -1;
	protected int index = -1;
	private CustomView customview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fu_jin_fang);

		intExtra = getIntent().getIntExtra("fujinfang", 0);
		areaListId = getIntent().getIntExtra("areaListId", -1);

		initView();
		if (params == null) {
			params = new RequestParams();
			params.put("pageNum", pageNum);
			params.put("areaListId", areaListId);
			params.put("sign", sign);
		}
		if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
			params.put(paiXuKey, paiXu);
			list.add("默认排序");
			list.add("总价由低到高");
			list.add("总价由高到低");
			// list.add("单价由低到高");
			// list.add("单价由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
			initSecondData();
		} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
			params.put(paiXuKey, paiXu);
			list.add("默认排序");
			list.add("租金由低到高");
			list.add("租金由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
			initRentData();
		}
	}

	private void initRentData() {
		IndexApi.getInstance().requestCommunityRenthouse(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				CommunityMoreRentHouseEntity communityMoreRentHouseEntity = (CommunityMoreRentHouseEntity) object;
				if (isRefresh) {
					houseList.clear();
				}
				Content content = communityMoreRentHouseEntity.getContent();
				if (content != null) {
					if (content.getNearbyHouseResourceList() != null) {
						houseList.addAll(content.getNearbyHouseResourceList());
					}
					pageCount = content.getPageCount();

					if (pageCount <= pageNum) {
						ptrListView.setMode(Mode.PULL_FROM_START);
					} else {
						ptrListView.setMode(Mode.BOTH);
					}
				}
				if (houseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}

				adapter.notifyDataSetChanged();
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(FuJinFangActivity.this, getResources().getString(R.string.net_error));
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
				ToastUtil.toastShow(FuJinFangActivity.this, getResources().getString(R.string.net_error));
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});
	}

	private void initSecondData() {
		IndexApi.getInstance().requestCommunitySecondhouse(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				CommunityMoreSecondHouseEntity communityMoreSecondHouseEntity = (CommunityMoreSecondHouseEntity) object;
				if (isRefresh) {
					houseList.clear();
				}
				houseList.addAll(communityMoreSecondHouseEntity.getContent().getNearbyHouseResourceList());
				if (houseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				pageCount = communityMoreSecondHouseEntity.getContent().getPageCount();
				if (pageCount <= pageNum) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}
				adapter.notifyDataSetChanged();
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(FuJinFangActivity.this, getResources().getString(R.string.net_error));
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_ERROR);
				ptrListView.setMode(Mode.DISABLED);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(FuJinFangActivity.this, getResources().getString(R.string.net_error));
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
			}
		});
	}

	private void initView() {
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		tvTitle.setText("附近的房");
		findViewById(R.id.title_left).setOnClickListener(this);

		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);

		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);

		mListView = ptrListView.getRefreshableView();
		adapter = new MyAdapter();
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
		findViewById(R.id.img_around_paixu).setOnClickListener(this);
	}

	class MyAdapter extends BaseAdapter {
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
			Object object = houseList.get(position);
			View inflate = null;
			if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
				inflate = getLayoutInflater().inflate(R.layout.item_secondhouse, null);
				NearbyHouseResourceList nearbyHouseInfo = (NearbyHouseResourceList) object;
				inflate.findViewById(R.id.rl_secondhandhouse).setVisibility(View.GONE);
				TextView tvRoomHall = (TextView) inflate.findViewById(R.id.tv_room_hall);
				String roomNum = nearbyHouseInfo.getRoomNum();
				if (TextUtils.isEmpty(roomNum)) {
					roomNum = "0";
				}
				String hallNum = nearbyHouseInfo.getHallNum();
				if (TextUtils.isEmpty(hallNum)) {
					hallNum = "0";
				}
				tvRoomHall.setText(roomNum + "室" + hallNum + "厅");
				TextView tvTitle = (TextView) inflate.findViewById(R.id.tv_collect_title01);
				tvTitle.setText(nearbyHouseInfo.getTitle());
				TextView tvBerry = (TextView) inflate.findViewById(R.id.tv_berryGG);
				if (!TextUtils.isEmpty(nearbyHouseInfo.getBerryGG())) {
					tvBerry.setText(nearbyHouseInfo.getBerryGG() + "㎡");
				} else {
					tvBerry.setText("0");
				}
				TextView tvSalePrice = (TextView) inflate.findViewById(R.id.tv_saleprice);
				ImageView imgPic = (ImageView) inflate.findViewById(R.id.img_newhousepic);
				View imgRenZheng = inflate.findViewById(R.id.img_renzheng_web);
				View imgWeiRenZheng = inflate.findViewById(R.id.img_weirenzheng_web);
				if (!TextUtils.isEmpty(nearbyHouseInfo.getSurFaceUrl())) {
					AsyncImageLoader.setAsynImages(imgPic, nearbyHouseInfo.getSurFaceUrl());
				}
				if (!TextUtils.isEmpty(nearbyHouseInfo.getSalePrice())) {
					tvSalePrice.setText("" + nearbyHouseInfo.getSalePrice());
				} else {
					tvSalePrice.setText("0");

				}

				TextView tvPrice = (TextView) inflate.findViewById(R.id.tv_price);
				String price = nearbyHouseInfo.getPrice();
				if (price != null) {
					tvPrice.setText(price + "元/平");
				} else {
					tvPrice.setText("0");
				}
				TextView tvAreaListName = (TextView) inflate.findViewById(R.id.tv_collect_arealistname);
				tvAreaListName.setText(nearbyHouseInfo.getAreaListName());
				// View isAuthView = inflate.findViewById(R.id.img_isAuth);
				if (nearbyHouseInfo.getIsAuth() == 0) {
					imgRenZheng.setVisibility(View.GONE);
					imgWeiRenZheng.setVisibility(View.VISIBLE);
				} else {
					imgWeiRenZheng.setVisibility(View.GONE);
					imgRenZheng.setVisibility(View.VISIBLE);
				}

				LinearLayout llSecondSpecialist = (LinearLayout) inflate.findViewById(R.id.ll_secondHouseSpecialist);
				List<SecondHouseSpecialtyList> secondHouseSpecialtyList = nearbyHouseInfo.getSecondHouseSpecialtyList();
				if (secondHouseSpecialtyList.size() > 0) {
					for (int i = 0; i < secondHouseSpecialtyList.size(); i++) {
						if (secondHouseSpecialtyList.size() > 4) {
							break;
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
					tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
					tvHouseTag.setText("暂无");
					llSecondSpecialist.addView(child);
				}
			} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
				com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreRentHouseEntity.NearbyHouseResourceList nearbyHouseInfo = (com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreRentHouseEntity.NearbyHouseResourceList) object;
				inflate = getLayoutInflater().inflate(R.layout.index_zufang_item, null);
				TextView tvRentTitle = (TextView) inflate.findViewById(R.id.tv_title);
				TextView tvRentAreaListName = (TextView) inflate.findViewById(R.id.tv_areaListName);
				TextView tvRentMoney = (TextView) inflate.findViewById(R.id.tv_rentMoney);
				ImageView imgPic = (ImageView) inflate.findViewById(R.id.img_newhousepic);
				LinearLayout llRentSpecial = (LinearLayout) inflate.findViewById(R.id.ll_special);
				inflate.findViewById(R.id.ll_housetype).setVisibility(View.VISIBLE);
				TextView tvHouseType = (TextView) inflate.findViewById(R.id.tv_housetype);
				TextView tvBerryGG = (TextView) inflate.findViewById(R.id.tv_berryGG);
				if (nearbyHouseInfo.getRoomNum() != null && nearbyHouseInfo.getHallNum() != null) {
					tvHouseType.setText(nearbyHouseInfo.getRoomNum() + "室" + nearbyHouseInfo.getHallNum() + "厅");
				} else {
					tvHouseType.setText(0 + "室" + 0 + "厅");
				}
				if (!TextUtils.isEmpty(nearbyHouseInfo.getBerryGG())) {
					tvBerryGG.setText(nearbyHouseInfo.getBerryGG() + "㎡");
				} else {
					tvBerryGG.setText(0 + "㎡");
				}
				tvRentTitle.setText(nearbyHouseInfo.getTitle());
				if (!TextUtils.isEmpty(nearbyHouseInfo.getSurFaceUrl())) {
					AsyncImageLoader.setAsynImages(imgPic, nearbyHouseInfo.getSurFaceUrl());
				}
				tvRentAreaListName.setText(nearbyHouseInfo.getAreaListName());
				String rental = nearbyHouseInfo.getRental();
				if (!TextUtils.isEmpty(rental)) {
					tvRentMoney.setText(rental);
				} else {
					tvRentMoney.setText("-");
				}

				List<RentHouseSpecialtyList> rentHouseSpecialtyList = nearbyHouseInfo.getRentHouseSpecialtyList();
				if (rentHouseSpecialtyList.size() > 0) {
					for (int i = 0; i < rentHouseSpecialtyList.size(); i++) {
						if (rentHouseSpecialtyList.size() > 4) {
							break;
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
						tvHouseTag.setText(rentHouseSpecialtyList.get(i).getSpecialtyName());
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

	private void showSecondPaiXuWindow() {
		orderBy = -1;
		params = new RequestParams();
		View view = getLayoutInflater().inflate(R.layout.item_listview, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyle);
		popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		ListView paiXuList = (ListView) view.findViewById(R.id.list_PaiXu);
		final PaiXuPopAdapter paixuAdapter = new PaiXuPopAdapter(this, list, R.layout.paixu_search_item);
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
				if (position == 0) {
					paiXu = "0";
					paiXuKey = "defaultOrder";
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
				// }
				// else if (position == 4) {
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
				pageNum = 1;
				houseList.clear();
				adapter.notifyDataSetChanged();
				params.put("pageNum", pageNum);
				params.put("areaListId", areaListId);
				params.put("sign", sign);
				params.put(paiXuKey, paiXu);
				if (orderBy != -1) {
					params.put("orderBy", orderBy);
				}
				paixuAdapter.notifyDataSetChanged();
				mListView.setSelection(1);
				initSecondData();
			}

		});
	}

	private void showRentPaiXuWindow() {
		orderBy = -1;
		View view = getLayoutInflater().inflate(R.layout.item_listview, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyle);
		popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		ListView paiXuList = (ListView) view.findViewById(R.id.list_PaiXu);
		final PaiXuPopAdapter paixuAdapter = new PaiXuPopAdapter(this, list, R.layout.paixu_search_item);
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
				params = new RequestParams();
				popIndex = 1;
				index = position;
				if (position == 0) {
					paiXu = "默认排序";
					paiXuKey = "defaultOrder";
					orderBy = -1;
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
					paiXuKey = "berryggOrder";
					orderBy = 1;

				} else if (position == 4) {
					paiXu = "面积由大到小";
					paiXuKey = "berryggOrder";
					orderBy = 0;
				}
				pageNum = 1;
				houseList.clear();
				adapter.notifyDataSetChanged();
				params.put("pageNum", pageNum);
				params.put(paiXuKey, paiXu);
				if (orderBy != -1) {
					params.put("orderBy", orderBy);
				}
				params.put("areaListId", areaListId);
				params.put("sign", sign);
				mListView.setSelection(1);
				paixuAdapter.notifyDataSetChanged();
				initRentData();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_around_paixu:
			if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
				showSecondPaiXuWindow();
			} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
				showRentPaiXuWindow();
			}
			break;
		case R.id.title_left:
			finish();
			super.onBackPressed();
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = null;
		position -= mListView.getHeaderViewsCount();
		Object object = houseList.get(position);
		if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
			NearbyHouseResourceList nearbyHouseInfo = (NearbyHouseResourceList) object;
			intent = new Intent(this, SecondHouseDetailActivity.class);
			intent.putExtra("secondHouseId", nearbyHouseInfo.getId());
			intent.putExtra("areaListId", nearbyHouseInfo.getAreaListId());
		} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
			com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreRentHouseEntity.NearbyHouseResourceList nearbyHouseInfo = (com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreRentHouseEntity.NearbyHouseResourceList) object;
			intent = new Intent(this, ZuFangxqActivity.class);
			intent.putExtra("rentHouseId", nearbyHouseInfo.getId());
		}
		startActivity(intent);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum = 1;
		isRefresh = true;
		params = new RequestParams();
		params.put("pageNum", pageNum);
		params.put("areaListId", areaListId);
		params.put("sign", sign);
		params.put(paiXuKey, paiXu);
		if (orderBy != -1) {
			params.put("orderBy", orderBy);
		}
//		if (popIndex == 0) {
			if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
				initSecondData();
			} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
				initRentData();
			}
//		} else if (popIndex == 1) {
//
//			if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
//				initSecondData();
//			} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
//				initRentData();
//			}
//		}
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum++;
		isRefresh = false;
		params = new RequestParams();
		params.put("pageNum", pageNum);
		params.put("areaListId", areaListId);
		params.put("sign", sign);
		params.put(paiXuKey, paiXu);
		if (orderBy != -1) {
			params.put("orderBy", orderBy);
		}
//		if (popIndex == 0) {
			if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
				initSecondData();
			} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
				initRentData();
			}
		// } else if (popIndex == 1) {
		// if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
		// initSecondData();
		// } else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
		// initRentData();
		// }
		// }
	}

	@Override
	public void onLoadData() {
		if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
			initSecondData();
		} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
			initRentData();
		}
	}

}
