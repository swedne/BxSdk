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
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreBrokerListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreBrokerListEntity.BrokerList;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreBrokerListEntity.SpecialSkillList;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreRentHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreRentHouseEntity.RentHouseListMore;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreSecondHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.CommunityMoreSecondHouseEntity.SecondHouseListMore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.TextureView;
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

public class IndexSheQuCommonActivity extends BaseActivity
		implements OnClickListener, OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private int intExtra;
	private ArrayList<String> list = new ArrayList<>();
	private ImageView imgPaiXu;
	private int pageNum = 1;
	private int houseTypeId = 1;
	private int areaListId;
	private ArrayList<Object> houseList = new ArrayList<>();
	private ListView mListView;
	private MyAdapter adapter;
	private PullToRefreshListView ptrListView;
	private boolean isRefresh;
	private int pageCount;
	private RequestParams params;
	private int sign = 1;
	private int popIndex;
	private PopupWindow popupWindow;
	protected String paiXu;
	protected String paiXuKey;
	protected int orderBy;
	private TextView tv_secondhouse;
	private TextView tv_shangpu;
	private TextView tv_xiezilou;
	private TextView tv_ll_zhuzhai;
	private TextView lin01;
	private TextView lin02;
	private TextView lin03;
	private TextView lin04;
	private View llBar;
	private CustomView customview;
	private int index = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_she_qu_zhuan_jia);

		intExtra = getIntent().getIntExtra("shequindex", 0);
		initView();

		areaListId = getIntent().getIntExtra("areaListId", -1);

		if (params == null) {
			params = new RequestParams();
		}
		if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
			params.put("pageNum", pageNum);
			params.put("areaListId", areaListId);
			params.put("sign", 1);
			list.add("默认排序");
			list.add("总价由低到高");
			list.add("总价由高到低");
			// list.add("单价由低到高");
			// list.add("单价由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
			initData();
			llBar.setVisibility(View.VISIBLE);
		} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
			list.add("默认排序");
			list.add("租金由低到高");
			list.add("租金由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
			params.put("pageNum", pageNum);
			params.put("areaListId", areaListId);
			params.put("sign", sign);
			initRentData();
			llBar.setVisibility(View.VISIBLE);
		} else {
			params.put("pageNum", pageNum);
			params.put("areaListId", areaListId);
			initBrokerData();
			llBar.setVisibility(View.GONE);
		}
	}

	private void initData() {
		IndexApi.getInstance().requestCommunitySecondhouse(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				CommunityMoreSecondHouseEntity communityMoreSecondHouseEntity = (CommunityMoreSecondHouseEntity) object;
				if (isRefresh) {
					houseList.clear();
				}
				houseList.addAll(communityMoreSecondHouseEntity.getContent().getSecondHouseListMore());
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
				ToastUtil.toastShow(IndexSheQuCommonActivity.this, getResources().getString(R.string.net_error));
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_ERROR);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(IndexSheQuCommonActivity.this, msg);
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
		IndexApi.getInstance().requestCommunityRenthouse(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				CommunityMoreRentHouseEntity communityMoreRentHouseEntity = (CommunityMoreRentHouseEntity) object;
				houseList.clear();
				houseList.addAll(communityMoreRentHouseEntity.getContent().getRentHouseListMore());
				if (houseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				pageCount = communityMoreRentHouseEntity.getContent().getPageCount();
				if (pageCount <= pageNum) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}
				adapter.notifyDataSetChanged();
				ptrListView.onRefreshComplete();
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(IndexSheQuCommonActivity.this, getResources().getString(R.string.net_error));
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
				ToastUtil.toastShow(IndexSheQuCommonActivity.this, msg);
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});
	}

	private void initBrokerData() {
		IndexApi.getInstance().requestCommunityBroker(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				CommunityMoreBrokerListEntity brokerListEntity = (CommunityMoreBrokerListEntity) object;
				houseList.clear();
				houseList.addAll(brokerListEntity.getContent().getBrokerList());
				if (houseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				// pageCount = brokerListEntity.getContent().getPageCount();
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
				ToastUtil.toastShow(IndexSheQuCommonActivity.this, getResources().getString(R.string.net_error));
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_ERROR);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(IndexSheQuCommonActivity.this, msg);
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});
	}

	private void initView() {
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		imgPaiXu = (ImageView) findViewById(R.id.img_paixu);
		imgPaiXu.setOnClickListener(this);
		View rlPaiXu = findViewById(R.id.rl_paixu);
		TextView titleRight = (TextView) findViewById(R.id.title_right_tv);
		titleRight.setOnClickListener(this);
		findViewById(R.id.title_left).setOnClickListener(this);
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
			titleRight.setVisibility(View.VISIBLE);
			tvTitle.setText("二手房");
			titleRight.setText("附近");
		} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
			titleRight.setVisibility(View.VISIBLE);
			tvTitle.setText("租房");
			titleRight.setText("附近");
		} else {
			tvTitle.setText("查看社区专家");
			titleRight.setVisibility(View.GONE);
			rlPaiXu.setVisibility(View.GONE);
		}

		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);
		mListView = ptrListView.getRefreshableView();
		mListView.setOnItemClickListener(this);
		adapter = new MyAdapter();
		mListView.setAdapter(adapter);

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
		llBar = findViewById(R.id.ll_bar);
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
			View inflate = null;
			Object object = houseList.get(position);
			if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
				SecondHouseListMore houseResInfo = (SecondHouseListMore) object;
				inflate = getLayoutInflater().inflate(R.layout.item_secondhouse, null);
				LinearLayout llSpecial = (LinearLayout) inflate.findViewById(R.id.ll_secondHouseSpecialist);
				TextView tvTitle = (TextView) inflate.findViewById(R.id.tv_collect_title01);
				TextView tvRoomHall = (TextView) inflate.findViewById(R.id.tv_room_hall);
				TextView tvBerryGG = (TextView) inflate.findViewById(R.id.tv_berryGG);
				TextView tvArealistName = (TextView) inflate.findViewById(R.id.tv_collect_arealistname);
				TextView tvSalePrice = (TextView) inflate.findViewById(R.id.tv_saleprice);
				TextView tvPrice = (TextView) inflate.findViewById(R.id.tv_price);
				ImageView imgAuth = (ImageView) inflate.findViewById(R.id.img_isAuth);
				View imgRenZheng = inflate.findViewById(R.id.img_renzheng_web);
				View imgWeiRenZheng = inflate.findViewById(R.id.img_weirenzheng_web);
				ImageView img1 = (ImageView) inflate.findViewById(R.id.img_newhousepic);
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
				if (houseTypeId == 1) {
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
					if (specials != null) {

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
					tvArealistName2.setText(houseResInfo.getAreaListName());
					tvSalePrice2.setText(houseResInfo.getSalePrice() + "");
					if (!TextUtils.isEmpty(houseResInfo.getPicUrl())) {
						AsyncImageLoader.setAsynImages(img2, houseResInfo.getPicUrl());
					}
					if (houseTypeId == 4) {
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
			} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
				RentHouseListMore houseInfo = (RentHouseListMore) object;
				inflate = getLayoutInflater().inflate(R.layout.index_zufang_item, null);
				TextView tvRentTitle = (TextView) inflate.findViewById(R.id.tv_title);
				TextView tvRentAreaListName = (TextView) inflate.findViewById(R.id.tv_areaListName);
				TextView tvRentMoney = (TextView) inflate.findViewById(R.id.tv_rentMoney);
				TextView tvRentBerry = (TextView) inflate.findViewById(R.id.tv_rent_berry);
				LinearLayout llRentSpecial = (LinearLayout) inflate.findViewById(R.id.ll_special);
				ImageView imgPic = (ImageView) inflate.findViewById(R.id.img_newhousepic);
				if (houseTypeId == 1) {
					inflate.findViewById(R.id.ll_housetype).setVisibility(View.VISIBLE);
					TextView tvHouseType = (TextView) inflate.findViewById(R.id.tv_housetype);
					TextView tvBerryGG = (TextView) inflate.findViewById(R.id.tv_berryGG);
					if (houseInfo.getRoomNum() != null && houseInfo.getHallNum() != null) {
						tvHouseType.setText(houseInfo.getRoomNum() + "室" + houseInfo.getHallNum() + "厅");
					} else {
						tvHouseType.setText(0 + "室" + 0 + "厅");
					}
					if (!TextUtils.isEmpty(houseInfo.getBerryGG())) {
						tvBerryGG.setText(houseInfo.getBerryGG() + "㎡");
					} else {
						tvBerryGG.setText(0 + "㎡");
					}
					if (!TextUtils.isEmpty(houseInfo.getPicUrl())) {
						AsyncImageLoader.setAsynImages(imgPic, houseInfo.getPicUrl());
					}
				} else {
					inflate.findViewById(R.id.ll_housetype).setVisibility(View.GONE);
					if (!TextUtils.isEmpty(houseInfo.getPicUrl())) {
						AsyncImageLoader.setAsynImages(imgPic, houseInfo.getSurFaceUrl());
					}
				}
				if (houseTypeId != 1) {
					tvRentBerry.setVisibility(View.VISIBLE);
					llRentSpecial.setVisibility(View.GONE);
					if (houseTypeId == 4) {
						if (!TextUtils.isEmpty(houseInfo.getPlantAcreage())) {
							tvRentBerry.setText(houseInfo.getPlantAcreage() + "㎡");
						} else {
							tvRentBerry.setText("-");

						}
					} else {
						if (!TextUtils.isEmpty(houseInfo.getBerryGG())) {
							tvRentBerry.setText(houseInfo.getBerryGG() + "㎡");
						} else {
							tvRentBerry.setText("-");
						}
					}
				}
				tvRentTitle.setText(houseInfo.getTitle());
				tvRentAreaListName.setText(houseInfo.getAreaListName());
				if (!TextUtils.isEmpty(houseInfo.getRental())) {
					tvRentMoney.setText(houseInfo.getRental() + "");
				} else {
					tvRentMoney.setText("-");

				}

				List<String> specials = houseInfo.getSpecials();
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

			} else {
				BrokerList houseInfo = (BrokerList) object;
				inflate = getLayoutInflater().inflate(R.layout.jingjiren_item, null);
				ImageView imgBroker = (ImageView) inflate.findViewById(R.id.img_broker);
				TextView tvBrokerName = (TextView) inflate.findViewById(R.id.tv_brokerName);
				TextView tvRealName = (TextView) inflate.findViewById(R.id.tv_realName);
				TextView tvIsExpert = (TextView) inflate.findViewById(R.id.tv_isExpert);
				TextView tvHarkBackHouse = (TextView) inflate.findViewById(R.id.tv_harkBackHouse);
				TextView tvBusinessName = (TextView) inflate.findViewById(R.id.tv_businessname);
				View line = inflate.findViewById(R.id.line);
				LinearLayout llSkill = (LinearLayout) inflate.findViewById(R.id.ll_biaoqian);
				tvBrokerName.setText(houseInfo.getBrokerName());
				tvRealName.setText(houseInfo.getRealName());
				tvHarkBackHouse.setText(houseInfo.getHarkBackHouse());
				tvBusinessName.setText(houseInfo.getBusinessListName());
				AsyncImageLoader.setAsynImages(imgBroker, houseInfo.getHeadUrl());
				ImageView imgLevel = (ImageView) inflate.findViewById(R.id.img_leavel);
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
				if (houseInfo.getIsExpert() == 1) {
					tvIsExpert.setVisibility(View.VISIBLE);
					line.setVisibility(View.VISIBLE);
				} else {
					tvIsExpert.setVisibility(View.GONE);
					line.setVisibility(View.GONE);
				}

				List<SpecialSkillList> specialSkillList = houseInfo.getSpecialSkillList();
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
			return inflate;
		}
	}

	@Override
	public void onClick(View v) {
		params = new RequestParams();
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		// 附近的房
		case R.id.title_right_tv:
			Intent intent = new Intent(this, FuJinFangActivity.class);
			if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
				intent.putExtra("fujinfang", SystemConstant.SHEQU_SELLSECONDEHOUSE);
				intent.putExtra("areaListId", areaListId);
			} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
				intent.putExtra("fujinfang", SystemConstant.SHEQU_RENTHOUSE);
				intent.putExtra("areaListId", areaListId);
			}
			startActivity(intent);
			break;
		case R.id.img_paixu:
			if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
				showSecondPaiXuWindow();
			} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
				showRentPaiXuWindow();
			}
			break;
		case R.id.ll_secondhouse:
			index = -1;
			popIndex = 0;
			houseTypeId = 1;
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("areaListId", areaListId);
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
			adapter.notifyDataSetChanged();
			lin04.setBackgroundColor(getResources().getColor(R.color.txt_gray));
			break;
		case R.id.ll_shangpu:
			index = -1;
			popIndex = 0;
			houseTypeId = 2;
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("areaListId", areaListId);
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
			adapter.notifyDataSetChanged();
			break;
		case R.id.ll_xiezilou:
			index = -1;
			popIndex = 0;
			houseTypeId = 3;
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("areaListId", areaListId);
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
			adapter.notifyDataSetChanged();

			break;
		case R.id.ll_changfang:
			index = -1;
			popIndex = 0;
			houseTypeId = 4;
			pageNum = 1;
			mListView.setSelection(1);
			houseList.clear();
			params.put("pageNum", pageNum);
			params.put("houseTypeId", houseTypeId);
			params.put("areaListId", areaListId);
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
			adapter.notifyDataSetChanged();
			break;
		default:
			break;
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
						paiXuKey = "berryggOrder";
						orderBy = 1;
					} else if (position == 4) {
						paiXu = "面积由大到小";
						paiXuKey = "berryggOrder";
						orderBy = 0;
					}
				}

				pageNum = 1;
				houseList.clear();
				params.put("pageNum", pageNum);
				params.put("houseTypeId", houseTypeId);
				params.put("areaListId", areaListId);
				params.put(paiXuKey, paiXu);
				if (position != 0) {
					params.put("orderBy", orderBy);
				}
				paixuAdapter.notifyDataSetChanged();
				mListView.setSelection(1);
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
					paiXuKey = "berryggOrder";
					orderBy = 1;

				} else if (position == 4) {
					paiXu = "面积由大到小";
					paiXuKey = "berryggOrder";
					orderBy = 0;
				}
				pageNum = 1;
				houseList.clear();
				houseList.clear();
				params.put("pageNum", pageNum);
				params.put("areaListId", areaListId);
				params.put("sign", 1);
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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		position -= mListView.getHeaderViewsCount();
		Intent intent = null;
		Object object = houseList.get(position);
		if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
			SecondHouseListMore houseInfo = (SecondHouseListMore) object;
			if (houseTypeId == 1) {
				intent = new Intent(this, SecondHouseDetailActivity.class);
				intent.putExtra("secondHouseId", houseInfo.id);
				intent.putExtra("areaListId", areaListId);
			} else if (houseTypeId == 2) {
				intent = new Intent(this, ShangPuDetailActivity.class);
				intent.putExtra("id", houseInfo.id);
				intent.putExtra("selectTypeId", 1);
			} else if (houseTypeId == 3) {
				intent = new Intent(this, XieZiLouActivity.class);
				intent.putExtra("id", houseInfo.id);
				intent.putExtra("selectTypeId", 5);
			} else if (houseTypeId == 4) {
				intent = new Intent(this, ChangFangDetailActivity.class);
				intent.putExtra("id", houseInfo.id);
				intent.putExtra("selectTypeId", 3);
			}

		} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
			RentHouseListMore houseInfo = (RentHouseListMore) object;
			if (houseTypeId == 1) {
				intent = new Intent(this, ZuFangxqActivity.class);
				intent.putExtra("rentHouseId", houseInfo.getHouseResourceId());
			} else if (houseTypeId == 2) {
				intent = new Intent(this, ShangPuDetailActivity.class);
				intent.putExtra("id", houseInfo.getHouseResourceId());
				intent.putExtra("selectTypeId", 0);
			} else if (houseTypeId == 3) {
				intent = new Intent(this, XieZiLouActivity.class);
				intent.putExtra("id", houseInfo.getHouseResourceId());
				intent.putExtra("selectTypeId", 4);
			} else if (houseTypeId == 4) {
				intent = new Intent(this, ChangFangDetailActivity.class);
				intent.putExtra("id", houseInfo.getHouseResourceId());
				intent.putExtra("selectTypeId", 2);
			}

		} else {
			BrokerList houseInfo = (BrokerList) object;
			intent = new Intent(this, JingjirenXqActivity.class);
			intent.putExtra("brokerId", houseInfo.getBrokerId());
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

		if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
			params.put("houseTypeId", houseTypeId);
			if (popIndex == 0) {
				params.put("sign", 1);
			} else if (popIndex == 1) {
				params.put("sign", 1);
				params.put(paiXuKey, paiXu);
				params.put("orderBy", orderBy);
			}
			initData();
		} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
			params.put("houseTypeId", houseTypeId);
			if (popIndex == 0) {
				params.put("sign", 1);
			} else if (popIndex == 1) {
				params.put("sign", 1);
				params.put(paiXuKey, paiXu);
				params.put("orderBy", orderBy);
			}
			initRentData();
		} else {
			initBrokerData();
		}

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum++;
		isRefresh = false;
		params.put("pageNum", pageNum);
		params.put("areaListId", areaListId);
		if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
			if (popIndex == 0) {
				params.put("sign", 1);
			} else if (popIndex == 1) {
				params.put("sign", 1);
				params.put(paiXuKey, paiXu);
				params.put("orderBy", orderBy);
			}
			initData();
		} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
			if (popIndex == 0) {
				params.put("sign", 1);
			} else if (popIndex == 1) {
				params.put("sign", 1);
				params.put(paiXuKey, paiXu);
				params.put("orderBy", orderBy);
			}
			initRentData();
		} else {
			initBrokerData();
		}
	}

	@Override
	public void onLoadData() {
		if (intExtra == SystemConstant.SHEQU_SELLSECONDEHOUSE) {
			initData();
		} else if (intExtra == SystemConstant.SHEQU_RENTHOUSE) {
			initRentData();
		} else {
			initBrokerData();
		}
	}

	// @Override
	// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long
	// arg3) {
	// startActivity(intent);
	// }
}
