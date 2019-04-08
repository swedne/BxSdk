package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.List;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
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
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
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

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ZhuZaiFragment extends BaseFragment
		implements OnClickListener, OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private String houseResourceTypeName;
	private List<String> list = new ArrayList<>();
	private RequestParams params;
	private int pageNum;
	private String houseTypeName;
	private int brokerId;
	private CustomView customview;
	private PullToRefreshListView ptrListView;
	private ListView mListView;
	private LayoutInflater mInflate;
	protected Broker broker;
	protected String houseTypeName2;
	protected boolean isRefresh;
	protected TextView tvBrokerName;
	protected List<HouseList> houseList = new ArrayList<>();
	private TextView tvIsExpert;
	private ImageView imgBroker;
	private Myadapter myAdapter;
	private PopupWindow popupWindow;
	private int tab;
	private int popIndex;
	private String paiXuKey;
	private String paiXu;
	private int orderBy;

	public ZhuZaiFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.mInflate = inflater;
		View view = inflater.inflate(R.layout.fragment_zhu_zai, container, false);
		if (SecondHouseActivity2.intExtra == SystemConstant.JINGJIREN_SECONDHOUSE) {
			houseResourceTypeName = "二手房";
			list.add("默认排序");
			list.add("总价由低到高");
			list.add("总价由高到低");
//			list.add("单价由低到高");
//			list.add("单价由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
		} else {
			list.add("默认排序");
			list.add("租金由低到高");
			list.add("租金由高到低");
			list.add("面积由小到大");
			list.add("面积由大到小");
			houseResourceTypeName = "租房";
		}
		this.brokerId = SecondHouseActivity2.brokerId;
		initview(view);
		params = new RequestParams();
		params.put("pageNum", pageNum);
		params.put("houseTypeName", houseTypeName);
		params.put("houseResourceTypeName", houseResourceTypeName);
		params.put("brokerId", brokerId);
		initData();
		return view;
	}

	private void initview(View view) {
		View rlBottom = view.findViewById(R.id.rl_bottom);
		customview = (CustomView) view.findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);

		ptrListView = (PullToRefreshListView) view.findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);

		mListView = ptrListView.getRefreshableView();
		myAdapter = new Myadapter();
		mListView.setAdapter(myAdapter);
		mListView.setOnItemClickListener(this);

		tvBrokerName = (TextView) view.findViewById(R.id.tv_brokerName);
		tvIsExpert = (TextView) view.findViewById(R.id.tv_isExpert);
		view.findViewById(R.id.rl_call).setOnClickListener(this);
		view.findViewById(R.id.rl_sendMSG).setOnClickListener(this);
		imgBroker = (ImageView) view.findViewById(R.id.img_broker);
		imgBroker.setOnClickListener(this);
		view.findViewById(R.id.img_paixu).setOnClickListener(this);

	}

	private void initData() {
		IndexApi.getInstance().requestBrokerHouseResList(getActivity(), params, new RequestCallBack() {

			private Integer pageCount;

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
				ToastUtil.toastShow(getActivity(), getResources().getString(R.string.net_error));
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
				ToastUtil.toastShow(getActivity(), msg);
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});

	}

	class Myadapter extends BaseAdapter {

		private int tab;

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
			if (SecondHouseActivity2.intExtra == SystemConstant.JINGJIREN_SECONDHOUSE) {
				inflate = mInflate.inflate(R.layout.item_secondhouse, null);
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
				LinearLayout llSpecial = (LinearLayout) inflate.findViewById(R.id.ll_secondHouseSpecialist);
				RelativeLayout rl_secondhandhouse = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse);
				// 二手房
				RelativeLayout rl_secondhandhouse02 = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse02);
				rl_secondhandhouse02.setVisibility(View.VISIBLE);
				rl_secondhandhouse.setVisibility(View.GONE);
				tvTitle.setText(houseResInfo.getTitle());
				tvRoomHall.setText(houseResInfo.getRoomNum() + "室" + houseResInfo.getHallNum() + "厅");
				tvBerryGG.setText(houseResInfo.getBerryGG() + "㎡");
				tvArealistName.setText(houseResInfo.getAreaListName());
				tvSalePrice.setText(houseResInfo.getSalePrice() + "");
				tvPrice.setText(houseResInfo.getPrice() + "元/平");
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

				// if (houseResInfo.getIsAuth() == 0) {
				// imgAuth.setVisibility(View.GONE);
				// } else {
				// imgAuth.setVisibility(View.VISIBLE);
				// }
				List<SpecialtyListForList> specialtyListForList = houseResInfo.getSpecialtyListForList();
				String color = "";
				if (specialtyListForList.size() > 0) {
					for (int i = 0; i < specialtyListForList.size(); i++) {
						View child = mInflate.inflate(R.layout.item_text, null);
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
					View child = mInflate.inflate(R.layout.item_text, null);
					TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
					tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
					color = "#FD641D";
					tvHouseTag.setTextColor(Color.parseColor(color));
					tvHouseTag.setText("暂无");
					llSpecial.addView(child);
				}
			} else {
				inflate = mInflate.inflate(R.layout.index_zufang_item, null);
				TextView tvRentTitle = (TextView) inflate.findViewById(R.id.tv_title);
				TextView tvRentAreaListName = (TextView) inflate.findViewById(R.id.tv_areaListName);
				TextView tvRentMoney = (TextView) inflate.findViewById(R.id.tv_rentMoney);
				TextView tvRentBerry = (TextView) inflate.findViewById(R.id.tv_rent_berry);
				ImageView imgPic = (ImageView) inflate.findViewById(R.id.img_newhousepic);
				LinearLayout llRentSpecial = (LinearLayout) inflate.findViewById(R.id.ll_special);
				if (tab == 0) {
					if (!TextUtils.isEmpty(houseResInfo.getPicUrl())) {
						AsyncImageLoader.setAsynImages(imgPic, houseResInfo.getPicUrl());
					}
				} else {
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
						View child = mInflate.inflate(R.layout.item_text, null);
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
					View child = mInflate.inflate(R.layout.item_text, null);
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
		if (SecondHouseActivity2.intExtra == SystemConstant.JINGJIREN_SECONDHOUSE) {
			if (houseTypeName2.equals("住宅")) {
				intent = new Intent(getActivity(), SecondHouseDetailActivity.class);
				intent.putExtra("secondHouseId", houseinfo.getId());
				intent.putExtra("areaListId", houseinfo.getAreaListId());
			} else if (houseTypeName2.equals("商铺")) {
				intent = new Intent(getActivity(), ShangPuDetailActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 1);
			} else if (houseTypeName2.equals("写字楼")) {
				intent = new Intent(getActivity(), XieZiLouActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 5);
			} else if (houseTypeName2.equals("厂房")) {
				intent = new Intent(getActivity(), ChangFangDetailActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 3);
			}

			startActivity(intent);
		} else {
			if (houseTypeName2.equals("住宅")) {
				intent = new Intent(getActivity(), ZuFangxqActivity.class);
				intent.putExtra("rentHouseId", houseinfo.getId());
			} else if (houseTypeName2.equals("商铺")) {
				intent = new Intent(getActivity(), ShangPuDetailActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 0);
			} else if (houseTypeName2.equals("写字楼")) {
				intent = new Intent(getActivity(), XieZiLouActivity.class);
				intent.putExtra("id", houseinfo.getId());
				intent.putExtra("selectTypeId", 4);
			} else if (houseTypeName2.equals("厂房")) {
				intent = new Intent(getActivity(), ChangFangDetailActivity.class);
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
		if (popIndex == 1) {
			params.put(paiXuKey, paiXu);
			params.put("orderBy", orderBy);
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
		if (popIndex == 1) {
			params.put(paiXuKey, paiXu);
			params.put("orderBy", orderBy);
		}
		initData();
	}

	@Override
	public void onLoadData() {
		initData();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
