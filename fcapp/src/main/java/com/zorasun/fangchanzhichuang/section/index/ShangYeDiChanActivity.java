package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.commonadapter.Common2Adapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.CommonAdapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.ViewHolder;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollGridView;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.index.entity.ShangYeDiChanListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.ShangYeDiChanListEntity.AreaList;
import com.zorasun.fangchanzhichuang.section.index.entity.ShangYeDiChanListEntity.BusinessEstateList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ScrollView;
import android.widget.TextView;

public class ShangYeDiChanActivity extends BaseActivity
		implements OnClickListener, OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private View layoutXieZilou;
	private TextView tvXieZiLou;
	private TextView tvQuYu;
	private TextView tvGengDuo;
	private int poptab = -1;
	private View view;
	private ListView poplist2;
	private ListView poplist3;
	private View line0;
	private View line1;
	private ScrollView ll_popmiddle;
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<String> popList1 = new ArrayList<>();
	private ArrayList<String> popList2 = new ArrayList<>();
	private ArrayList<String> popList3 = new ArrayList<>();
	private int select1;
	private int select2;
	private int select3;
	private List<BusinessEstateList> businessList = new ArrayList<>();
	private MyAdapter myAdapter;
	private RequestParams params;
	private PopupWindow popupWindow;
	private List<AreaList> areaList = new ArrayList<>();
	private ListView lvShangYe;
	private HashMap<Integer, Integer> hasMap = new HashMap<>();
	private ArrayList<Integer> intList = new ArrayList<>();
	private LocationClient locationClient;
	private double latitude = -1;
	private double longitude = -1;
	private int selectTypeId = 5;
	private PullToRefreshListView ptrListView;
	private int page = 1;
	private boolean isRefresh;
	private int pageCount;
	private ArrayList<String> berryList = new ArrayList<>();
	private ArrayList<String> rentalList = new ArrayList<>();
	private ArrayList<String> classifyList = new ArrayList<>();
	private ArrayList<String> decorateList = new ArrayList<>();
	private ArrayList<String> houseageList = new ArrayList<>();
	private ArrayList<String> floornumList = new ArrayList<>();
	private ArrayList<String> downPaymentList = new ArrayList<>();
	private ArrayList<String> jiBieList = new ArrayList<>();
	private ArrayList<String> priceList = new ArrayList<>();
	private ArrayList<String> totalPriceList = new ArrayList<>();
	private ArrayList<String> cheweiPriceList = new ArrayList<>();
	private ArrayList<String> locationList = new ArrayList<>();
	private ArrayList<String> teSeList = new ArrayList<>();
	private NoScrollGridView gvArea;
	private NoScrollGridView gvRental;
	private NoScrollGridView gvClassify;
	private NoScrollGridView gvDecorate;
	private NoScrollGridView gvHouseAge;
	private NoScrollGridView gvFloornum;
	private AreaAdapter areaAdapter;
	private RentalAdapter rentalAdapter;
	private ClassifyAdapter classifyAdapter;
	private DecorateAdapter decorateAdapter;
	private HouseAgeAdapter houseAgeAdapter;
	private FloorNumAdapter floorNumAdapter;
	protected int decorateIndex = -1;
	protected String classifyIndex;
	protected String floorNumIndex;
	protected String areaIndex;
	protected int moreIndex1 = -1;
	protected int moreIndex2 = -1;
	protected int moreIndex3 = -1;
	protected int moreIndex4 = -1;
	protected int moreIndex5 = -1;
	protected int moreIndex6 = -1;
	protected int moreIndex7 = -1;
	protected int moreIndex8 = -1;
	protected int moreIndex9 = -1;
	protected int moreIndex10 = -1;
	protected String houseAgeIndex;
	protected String rentalIndex;
	private NoScrollGridView gvDownPayment;
	private DownPaymentAdapter downPaymentAdapter;
	private JiBieAdapter jiBieAdapter;
	private NoScrollGridView gvJiBie;
	private LocationAdapter locationAdapter;
	private TeSeAdapter teSeAdapter;
	private NoScrollGridView gvLocation;
	private NoScrollGridView gvTeSe;
	protected int paiXu = -1;
	protected String paiXuKey;
	// protected int orderType = -1;
	private int popIndex;
	protected String totalPriceRange;
	protected String downPaymentRange;
	protected int officeLevel = -1;
	protected String areaParkingRange;
	protected int addressId = -1;
	protected int specialId = -1;
	private int type = -1;
	protected int indexFlag;
	protected Integer businessListId = -1;
	protected String rentalParkingRange;
	private String likeSelect;
	private View searchEmpty;
	private boolean isFirst = true;
	private HashMap<String, Integer> hasMapAreaId = new HashMap<>();

	private int areaId = -1;
	private Drawable rightDrawableSelect;
	private Drawable rightDrawableNormal;
	private Drawable rightDrawableUp;
	public int index = -1;
	private PaiXuPopAdapter adapter;
	protected boolean isSelect;
	private EditText etSearch;
	private View delete;
	private CustomView customview;
	private int sign = 1;
	private int orderType = -1;

	private HashMap<Integer, Integer> hasRecord = new HashMap<>();
	private boolean searchBusiness = true;
	private int height;
	private int first = -1;
	private int third = -3;
	private boolean isDefault = true;
	private View viewBottom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shang_ye_di_chan);
		hasRecord.put(0, -1);
		hasRecord.put(1, -1);
		hasRecord.put(2, -1);
		hasRecord.put(3, -1);
		hasRecord.put(first, -1);
		hasRecord.put(third, -1);

		list.add("默认排序");
		list.add("发布时间");
		list.add("更新时间");
		list.add("总价由低到高");
		list.add("总价由高到低");
		list.add("面积由小到大");
		list.add("面积由大到小");
		adapter = new PaiXuPopAdapter(this, list, R.layout.paixu_search_item);
		initView();
		initAdapterList();
		initData();
	}

	private void initAdapterList() {
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_area_more).length; i++) {
			berryList.add(getResources().getStringArray(R.array.shangye_area_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_rental_more).length; i++) {
			rentalList.add(getResources().getStringArray(R.array.shangye_rental_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_shoujia_more).length; i++) {
			totalPriceList.add(getResources().getStringArray(R.array.shangye_shoujia_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_cheweiprice_more).length; i++) {
			cheweiPriceList.add(getResources().getStringArray(R.array.shangye_cheweiprice_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_classify_more).length; i++) {
			classifyList.add(getResources().getStringArray(R.array.shangye_classify_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_decoreate_mored).length; i++) {
			decorateList.add(getResources().getStringArray(R.array.shangye_decoreate_mored)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_houseage_more).length; i++) {
			houseageList.add(getResources().getStringArray(R.array.shangye_houseage_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_floornum_more).length; i++) {
			floornumList.add(getResources().getStringArray(R.array.shangye_floornum_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_downpayment_more).length; i++) {
			downPaymentList.add(getResources().getStringArray(R.array.shangye_downpayment_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_jibie_more).length; i++) {
			jiBieList.add(getResources().getStringArray(R.array.shangye_jibie_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_weizhi_more).length; i++) {
			locationList.add(getResources().getStringArray(R.array.shangye_weizhi_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_tese_more).length; i++) {
			teSeList.add(getResources().getStringArray(R.array.shangye_tese_more)[i]);
		}
		areaAdapter = new AreaAdapter(this, berryList, R.layout.gric_item_pop_more);
		priceList.addAll(rentalList);
		rentalAdapter = new RentalAdapter(this, priceList, R.layout.gric_item_pop_more);
		classifyAdapter = new ClassifyAdapter(this, classifyList, R.layout.gric_item_pop_more);
		decorateAdapter = new DecorateAdapter(this, decorateList, R.layout.gric_item_pop_more);
		houseAgeAdapter = new HouseAgeAdapter(this, houseageList, R.layout.gric_item_pop_more);
		floorNumAdapter = new FloorNumAdapter(this, floornumList, R.layout.gric_item_pop_more);
		downPaymentAdapter = new DownPaymentAdapter(this, downPaymentList, R.layout.gric_item_pop_more);
		jiBieAdapter = new JiBieAdapter(this, jiBieList, R.layout.gric_item_pop_more);
		locationAdapter = new LocationAdapter(this, locationList, R.layout.gric_item_pop_more);
		teSeAdapter = new TeSeAdapter(this, teSeList, R.layout.gric_item_pop_more);

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	private void initData() {
		searchEmpty.setVisibility(View.GONE);
		selectParams();
		IndexApi.getInstance().requestShangYeDiChanList(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				ShangYeDiChanListEntity shangYeDiChanListEntity = (ShangYeDiChanListEntity) object;
				if (isRefresh) {
					businessList.clear();
				}
				businessList.addAll(shangYeDiChanListEntity.getContent().getBusinessEstateLists());
				if (isFirst) {
					if (shangYeDiChanListEntity.getContent().getAreaList() != null) {
						areaList.addAll(shangYeDiChanListEntity.getContent().getAreaList());
					}
					for (int i = 0; i < areaList.size(); i++) {
						if (!hasMapAreaId.containsKey(areaList.get(i).getAreaName())) {
							hasMapAreaId.put(areaList.get(i).getAreaName(), areaList.get(i).getAreaId());
						}
					}
					isFirst = false;
				}
				pageCount = shangYeDiChanListEntity.getContent().getPageCount();
				if (pageCount <= page) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}
				myAdapter.notifyDataSetChanged();
				if (popupWindow != null) {
					popupWindow.dismiss();
				}
				myAdapter.notifyDataSetChanged();
				if (locationClient != null) {
					locationClient.stop();
				}

				if (businessList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
					ToastUtil.toastShow(ShangYeDiChanActivity.this, "无满足条件的房源信息");
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				// if (businessList.isEmpty() &&
				// ((!TextUtils.isEmpty(likeSelect)))) {
				// // searchEmpty.setVisibility(View.VISIBLE);
				// ToastUtil.toastShow(ShangYeDiChanActivity.this,
				// "无满足条件的房源信息");
				// } else {
				// searchEmpty.setVisibility(View.GONE);
				// }

			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(ShangYeDiChanActivity.this, getResources().getString(R.string.net_error));
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
				ToastUtil.toastShow(ShangYeDiChanActivity.this, msg);
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});

	}

	@SuppressLint("InflateParams")
	private void initView() {
		searchEmpty = findViewById(R.id.search_empty);
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		findViewById(R.id.img_paixu).setOnClickListener(this);
		findViewById(R.id.title_left).setOnClickListener(this);
		etSearch = (EditText) findViewById(R.id.et_title_Search);
		etSearch.setHint("请输入关键词");
		etSearch.setOnClickListener(this);
		delete = findViewById(R.id.img_delete);
		delete.setOnClickListener(this);

		findViewById(R.id.title_right_iv).setOnClickListener(this);
		// ImageView titleRight = (ImageView)
		// findViewById(R.id.title_right_img);
		// titleRight.setVisibility(View.VISIBLE);
		// titleRight.setImageResource(R.drawable.ditu_white);
		// titleRight.setOnClickListener(this);

		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);

		lvShangYe = ptrListView.getRefreshableView();
		myAdapter = new MyAdapter(this, businessList, R.layout.item_shangyedichan);
		lvShangYe.setAdapter(myAdapter);
		lvShangYe.setOnItemClickListener(this);
		tvXieZiLou = (TextView) findViewById(R.id.tv_xiezilou);
		tvQuYu = (TextView) findViewById(R.id.tv_quyu);
		tvGengDuo = (TextView) findViewById(R.id.tv_more);
		// tvXieZiLou.setOnClickListener(this);
		// tvQuYu.setOnClickListener(this);
		// tvGengDuo.setOnClickListener(this);

		viewBottom = findViewById(R.id.view1);
		layoutXieZilou = findViewById(R.id.ry_xieZilou);
		layoutXieZilou.setOnClickListener(this);
		findViewById(R.id.rl_quyu).setOnClickListener(this);
		findViewById(R.id.rl_more).setOnClickListener(this);

		view = getLayoutInflater().inflate(R.layout.jingjirenpop_item, null);
		poplist2 = (ListView) view.findViewById(R.id.poplist2);
		poplist3 = (ListView) view.findViewById(R.id.poplist3);
		line0 = view.findViewById(R.id.line0);
		line1 = view.findViewById(R.id.line1);

		ll_popmiddle = (ScrollView) view.findViewById(R.id.sl_popmiddle);

		rightDrawableSelect = getResources().getDrawable(R.drawable.sanjiao_p);
		rightDrawableSelect.setBounds(0, 0, rightDrawableSelect.getMinimumWidth(),
				rightDrawableSelect.getMinimumHeight());
		rightDrawableNormal = getResources().getDrawable(R.drawable.sanjiao_n);
		rightDrawableNormal.setBounds(0, 0, rightDrawableNormal.getMinimumWidth(),
				rightDrawableNormal.getMinimumHeight());
		rightDrawableUp = getResources().getDrawable(R.drawable.sanjiao_p_up);
		rightDrawableUp.setBounds(0, 0, rightDrawableNormal.getMinimumWidth(), rightDrawableUp.getMinimumHeight());
	}

	private void initLocation() {
		locationClient = new LocationClient(getApplicationContext());
		// 设置定位条件
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 是否打开GPS
		option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
		// option.setProdName("LocationDemo"); //
		// 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		// option.setScanSpan(UPDATE_TIME);// 设置定时定位的时间间隔。单位毫秒
		option.setScanSpan(1);
		locationClient.setLocOption(option);

		// 注册位置监听器
		locationClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				if (location == null) {
					return;
				}
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				businessList.clear();
				lvShangYe.setSelection(1);
				page = 1;
				sign = 2;
				myAdapter.notifyDataSetChanged();
				initData();

			}
		});

	}

	class MyAdapter extends CommonAdapter<BusinessEstateList> {

		public MyAdapter(Context context, List<BusinessEstateList> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, BusinessEstateList item, int position) {
			TextView tvTitle = (TextView) helper.getView(R.id.tv_title);
			TextView tvAreaListName = (TextView) helper.getView(R.id.tv_arealistName);
			TextView tvSalePrice = (TextView) helper.getView(R.id.tv_salePrice);
			TextView tvBerry = (TextView) helper.getView(R.id.tv_berryGG);
			TextView tvWan = (TextView) helper.getView(R.id.text_wan);
			TextView tvPrice = (TextView) helper.getView(R.id.tv_price);
			View renZhengView = helper.getView(R.id.img_renzheng);
			View imgRenZheng = helper.getView(R.id.img_renzheng_web);
			View imgWeiRenZheng = helper.getView(R.id.img_weirenzheng_web);
			if (selectTypeId == 1 || selectTypeId == 3 || selectTypeId == 5 || selectTypeId == 7) {
				tvPrice.setText(item.getPrice()+"元/平");
				if (item.getIsAuth() == 0) {
					imgRenZheng.setVisibility(View.GONE);
					imgWeiRenZheng.setVisibility(View.VISIBLE);
				} else {
					imgWeiRenZheng.setVisibility(View.GONE);
					imgRenZheng.setVisibility(View.VISIBLE);
				}
			}else{
				tvPrice.setText("");
			}

			ImageView imgPic = (ImageView) helper.getView(R.id.img_newhousepic);

			if (item.getSurFaceUrl() != null) {
				AsyncImageLoader.setAsynImages(imgPic, item.getSurFaceUrl());
			}

			tvTitle.setText(item.getTitle());
			tvAreaListName.setText(item.getAreaListName());
			if (selectTypeId == 0 || selectTypeId == 2 || selectTypeId == 4 || selectTypeId == 6) {
				tvSalePrice.setText("" + item.getRental());
				tvWan.setText("元/月");
			} else {
				tvSalePrice.setText("" + item.getSalePrice());
				tvWan.setText("万");
			}
			if (selectTypeId == 2 || selectTypeId == 3) {
				if (item.getPlantAcreage() != null) {
					tvBerry.setText(item.getPlantAcreage() + "㎡");
				}
			} else {
				tvBerry.setText(item.getBerryGG() + "㎡");
			}
		}
	}

	private void showPaiXuWindow() {
		adapter.notifyDataSetChanged();
		params = new RequestParams();
		View view = getLayoutInflater().inflate(R.layout.item_listview, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyle);
		popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		ListView paiXuList = (ListView) view.findViewById(R.id.list_PaiXu);
		paiXuList.setAdapter(adapter);
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
				if (selectTypeId == 0 || selectTypeId == 4 || selectTypeId == 6) {
					if (position == 0) {
						paiXu = 0;
						orderType = -1;
						paiXuKey = "defaultOrder";
					} else if (position == 1) {
						// paiXu = "发布时间";
						paiXu = 0;
						paiXuKey = "createTimeOrder";
						// orderType = 0;
					} else if (position == 2) {
						paiXu = 0;
						paiXuKey = "updateTimeOrder";
						// orderType = 0;
					} else if (position == 3) {
						paiXu = 1;
						if (selectTypeId == 6) {
							paiXuKey = "rental";
						} else {
							paiXuKey = "rentalOrder";
						}
						orderType = 1;
					} else if (position == 4) {
						paiXu = 0;
						if (selectTypeId == 6) {
							paiXuKey = "rental";
						} else {
							paiXuKey = "rentalOrder";
						}
						orderType = 0;

					} else if (position == 5) {
						paiXu = 1;
						if (selectTypeId == 6) {
							paiXuKey = "berrygg";
						} else {
							paiXuKey = "berryggOrder";
						}
						orderType = 1;

					} else if (position == 6) {
						paiXu = 0;
						if (selectTypeId == 6) {
							paiXuKey = "berrygg";
						} else {
							paiXuKey = "berryggOrder";
						}
						orderType = 0;
					}

				} else if (selectTypeId == 1 || selectTypeId == 5 || selectTypeId == 7) {
					if (position == 0) {
						paiXu = 0;
						paiXuKey = "defaultOrder";
						orderType = -1;
					} else if (position == 1) {
						paiXu = 0;
						paiXuKey = "createTimeOrder";
						// orderType = 0;
					} else if (position == 2) {
						paiXu = 0;
						paiXuKey = "updateTimeOrder";
						// orderType = 0;
					} else if (position == 3) {
						paiXu = 1;
						if (selectTypeId == 7) {
							paiXuKey = "totalPrice";
						} else {
							paiXuKey = "totalPriceOrder";
						}
						orderType = 1;

					} else if (position == 4) {
						paiXu = 0;
						if (selectTypeId == 7) {
							paiXuKey = "totalPrice";
						} else {
							paiXuKey = "totalPriceOrder";
						}
						orderType = 0;

					} else if (position == 5) {
						paiXu = 1;
						if (selectTypeId == 7) {
							paiXuKey = "berrygg";
						} else {
							paiXuKey = "berryggOrder";
						}
						orderType = 1;

					} else if (position == 6) {
						paiXu = 0;
						if (selectTypeId == 7) {
							paiXuKey = "berrygg";
						} else {
							paiXuKey = "berryggOrder";
						}
						orderType = 0;
					}
				} else if (selectTypeId == 2) {
					if (position == 0) {
						paiXu = 0;
						paiXuKey = "defaultOrder";
						// orderType = -1;
					} else if (position == 1) {
						paiXu = 0;
						paiXuKey = "createTimeOrder";
						// orderType = 0;
					} else if (position == 2) {
						paiXu = 0;
						paiXuKey = "updateTimeOrder";
						// orderType = 0;
					} else if (position == 3) {
						paiXu = 1;
						paiXuKey = "rentalOrder";
						// orderType = 1;
					} else if (position == 4) {
						paiXu = 0;
						paiXuKey = "rentalOrder";
						// orderType = 0;

					} else if (position == 5) {
						paiXu = 1;
						paiXuKey = "plantAcreageOrder";
						// orderType = 1;
					} else if (position == 6) {
						paiXu = 0;
						paiXuKey = "plantAcreageOrder";
						// orderType = 0;
					}
				} else if (selectTypeId == 3) {
					if (position == 0) {
						paiXu = -1;
						paiXuKey = "defaultOrder";
						// orderType = -1;
					} else if (position == 1) {
						paiXu = 0;
						paiXuKey = "createTimeOrder";
						// orderType = 0;
					} else if (position == 2) {
						paiXu = 0;
						paiXuKey = "updateTimeOrder";
						// orderType = 0;
					} else if (position == 3) {
						paiXu = 1;
						paiXuKey = "totalPriceOrder";
						// orderType = 1;
					} else if (position == 4) {
						paiXu = 0;
						paiXuKey = "totalPriceOrder";
						// orderType = 0;
					} else if (position == 5) {
						paiXu = 1;
						paiXuKey = "plantAcreageOrder";
						// orderType = 1;
					} else if (position == 6) {
						paiXu = 0;
						paiXuKey = "plantAcreageOrder";
						// orderType = 0;
					}
				}
				page = 1;
				businessList.clear();
				lvShangYe.setSelection(1);
				adapter.notifyDataSetChanged();
				myAdapter.notifyDataSetChanged();
				initData();
			}

		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		case R.id.title_right_iv:
			ToastUtil.toastShow(this,"暂未开放");
//			Intent intent = new Intent(this, DiTuDaoHangActivity.class);
//			intent.putExtra("selectTypeId", selectTypeId);
//			startActivity(intent);
			break;
		case R.id.ry_xieZilou:
			poptab = 0;
			showPopWindow();
			break;
		case R.id.rl_quyu:
			poptab = 1;
			showPopWindow();
			break;
		case R.id.rl_more:
			poptab = 2;
			showPopWindow();
			break;
		// 搜索
		case R.id.et_title_Search:
			Intent zufangIntent = new Intent(this, BusinessSearchActivity.class);
			startActivityForResult(zufangIntent, SystemConstant.STATE_PAGE_SHANGYEDICHAN);
			break;
		case R.id.img_paixu:
			showPaiXuWindow();
			break;
		case R.id.img_delete:
			likeSelect = null;
			etSearch.setText("");
			delete.setVisibility(View.GONE);
			page = 1;
			sign = 1;
			lvShangYe.setSelection(1);
			businessList.clear();
			myAdapter.notifyDataSetChanged();
			initData();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent data) {
		super.onActivityResult(arg0, arg1, data);
		if (data != null) {
			isDefault = true;
			cancleParams();
			sign = 1;
			likeSelect = data.getStringExtra("likeSelect");
			if (!TextUtils.isEmpty(likeSelect)) {
				params = new RequestParams();
				page = 1;
				lvShangYe.setSelection(1);
				popIndex = 11;
			} else {
				delete.setVisibility(View.GONE);
			}
			businessList.clear();
			lvShangYe.setSelection(1);
			myAdapter.notifyDataSetChanged();
			myAdapter.notifyDataSetChanged();
			page = 1;
			initData();
			index = -1;
			delete.setVisibility(View.VISIBLE);
			etSearch.setText(likeSelect);
		}
	}

	// 写字楼出售、区域、更多popWindow
	private void showPopWindow() {
		params = new RequestParams();
		// select1 = -1;
		// select2 = -1;
		// select3 = -1;

		View view = getLayoutInflater().inflate(R.layout.jingjirenpop_item, null);
		final LinearLayout rlPopWindow = (LinearLayout) view.findViewById(R.id.relativeLayout);
		final ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView2);
		ListView poplv1 = (ListView) view.findViewById(R.id.poplist1);
		final ListView poplv2 = (ListView) view.findViewById(R.id.poplist2);
		ListView poplv3 = (ListView) view.findViewById(R.id.poplist3);
		View setPriceView = view.findViewById(R.id.ll_setprice);
		View headView = view.findViewById(R.id.include_shangyedichan_shangpuchuzu_head);
		final View line0 = view.findViewById(R.id.line0);
		final View line1 = view.findViewById(R.id.line1);

		LinearLayout ll_popleft = (LinearLayout) view.findViewById(R.id.lv_popleft);
		final ScrollView sl_popmiddle = (ScrollView) view.findViewById(R.id.sl_popmiddle);
		LinearLayout sl_popright = (LinearLayout) view.findViewById(R.id.lv_popright);

		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.showAsDropDown(viewBottom);

		TextView tv_bottom = (TextView) view.findViewById(R.id.tv_bottom);

		if (poptab != 1) {
			line0.setVisibility(View.GONE);
			line1.setVisibility(View.GONE);
		}
		popList1.clear();
		popList2.clear();
		popList3.clear();
		select1 = hasRecord.get(first);
		select2 = hasRecord.get(poptab);
		select3 = hasRecord.get(third);

		if (poptab == 0) {
			String[] strings = getResources().getStringArray(R.array.pop_xiezilou);
			for (int i = 0; i < strings.length; i++) {
				popList2.add(strings[i]);
			}
			ll_popleft.setVisibility(View.GONE);
			setPriceView.setVisibility(View.GONE);
			sl_popright.setVisibility(View.GONE);
			tvXieZiLou.setTextColor(Color.parseColor("#1693FE"));
			tvXieZiLou.setCompoundDrawables(null, null, rightDrawableUp, null);

		}

		if (poptab == 1) {
			if (!searchBusiness) {
				popList1.add("区域");
				popList1.add("附近");
				String[] strings3 = getResources().getStringArray(R.array.pop_fujin);
				for (int i = 0; i < strings3.length; i++) {
					popList3.add(strings3[i]);
				}
				line0.setVisibility(View.GONE);
				sl_popmiddle.setVisibility(View.GONE);
			} else {
				popList1.add("区域");
				popList1.add("附近");
				line0.setVisibility(View.VISIBLE);
				sl_popmiddle.setVisibility(View.VISIBLE);
				line1.setVisibility(View.VISIBLE);
				popList2.add("不限");
				for (int i = 0; i < areaList.size(); i++) {
					String areaName = areaList.get(i).getAreaName();
					if (!popList2.contains(areaName)) {
						popList2.add(areaName);
					}
				}
				if (select2 != 0 && select2 != -1) {
					popList3.add("不限");
					switch (select2) {
					case 0:
						break;
					case 1:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("思明区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}

						}
						break;
					case 2:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("海沧区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}

						break;
					case 3:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("湖里区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}
						break;
					case 4:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("集美区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}
						break;
					case 5:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("同安区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}
						break;
					case 6:
						for (int i = 0; i < areaList.size(); i++) {
							if (areaList.get(i).getAreaName().equals("翔安区")) {
								popList3.add(areaList.get(i).getBusinessName());
							}
						}
						break;
					default:
						break;
					}
				}
				if (height != 0) {
					LayoutParams layoutParams = rlPopWindow.getLayoutParams();
					layoutParams.height = height;
				}
			}
			tvQuYu.setTextColor(Color.parseColor("#1693FE"));
			tvQuYu.setCompoundDrawables(null, null, rightDrawableUp, null);
			setPriceView.setVisibility(View.GONE);
		}

		if (poptab == 2) {
			headView.setVisibility(View.VISIBLE);
			ll_popleft.setVisibility(View.GONE);
			sl_popmiddle.setVisibility(View.GONE);
			tvGengDuo.setTextColor(Color.parseColor("#1693FE"));
			tvGengDuo.setCompoundDrawables(null, null, rightDrawableUp, null);
			setPopMore(view);
		}
		final PopAdapter1 adapter1 = new PopAdapter1();
		poplv1.setAdapter(adapter1);
		final PopAdapter2 adapter2 = new PopAdapter2();
		poplv2.setAdapter(adapter2);
		final PopAdapter3 adapter3 = new PopAdapter3();
		poplv3.setAdapter(adapter3);

		tv_bottom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();

			}
		});

		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {

				if (tvQuYu.getText().toString().equals("区域")) {
					tvQuYu.setTextColor(Color.parseColor("#919191"));
					tvQuYu.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
				if (!isSelect) {
					tvXieZiLou.setTextColor(Color.parseColor("#919191"));
					tvXieZiLou.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvXieZiLou.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
				if (TextUtils.isEmpty(areaIndex) && TextUtils.isEmpty(rentalIndex) && TextUtils.isEmpty(classifyIndex)
						&& decorateIndex == -1 && TextUtils.isEmpty(houseAgeIndex) && TextUtils.isEmpty(floorNumIndex)
						&& TextUtils.isEmpty(totalPriceRange) && TextUtils.isEmpty(downPaymentRange)
						&& officeLevel == -1 && addressId == -1 && specialId == -1
						&& TextUtils.isEmpty(areaParkingRange) && TextUtils.isEmpty(rentalParkingRange)) {
					tvGengDuo.setTextColor(Color.parseColor("#919191"));
					tvGengDuo.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvGengDuo.setTextColor(Color.parseColor("#1693FE"));
					tvGengDuo.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
			}
		});
		poplv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				select1 = position;
				adapter1.notifyDataSetChanged();
				// 点击区域或者附近的时候清空后面两个
				select2 = -1;
				select3 = -1;
				if (position == 1) {
					sl_popmiddle.setVisibility(View.GONE);
					line0.setVisibility(View.GONE);
					String[] strings = getResources().getStringArray(R.array.pop_fujin);
					popList3.clear();
					for (int i = 0; i < strings.length; i++) {
						popList3.add(strings[i]);
					}
				} else {
					line0.setVisibility(View.VISIBLE);
					sl_popmiddle.setVisibility(View.VISIBLE);
					line1.setVisibility(View.VISIBLE);
					popList2.clear();
					popList2.add("不限");
					for (int i = 0; i < areaList.size(); i++) {
						String areaName = areaList.get(i).getAreaName();
						if (!popList2.contains(areaName)) {
							popList2.add(areaName);
						}
					}
					adapter2.notifyDataSetChanged();

					popList3.clear();
				}
				adapter3.notifyDataSetChanged();
				lvShangYe.setSelection(1);
			}
		});
		poplv2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				page = 1;
				businessList.clear();
				select2 = position;
				if (poptab == 0) {
					index = -1;
					popIndex = 0;
					cancleParams();
					// priceList.clear();
					selectTypeId = position;
					isSelect = true;
					isDefault = true;
					tvXieZiLou.setText(popList2.get(select2));
					tvXieZiLou.setTextColor(Color.parseColor("#1693FE"));
					tvXieZiLou.setCompoundDrawables(null, null, rightDrawableSelect, null);
					if (position == 1 || position == 3 || position == 5 || position == 7) {
						list.clear();
						list.add("默认排序");
						list.add("发布时间");
						list.add("更新时间");
						list.add("总价由低到高");
						list.add("总价由高到低");
						list.add("面积由小到大");
						list.add("面积由大到小");
					} else if (position == 0 || position == 2 || position == 4 || position == 6) {
						list.clear();
						list.add("默认排序");
						list.add("发布时间");
						list.add("更新时间");
						list.add("租金由低到高");
						list.add("租金由高到低");
						list.add("面积由小到大");
						list.add("面积由大到小");
					}
				}
				hasMap.clear();
				intList.clear();
				popList3.clear();
				if (poptab == 1) {
					LayoutParams layoutParams = rlPopWindow.getLayoutParams();
					height = poplv2.getHeight();
					if (position == 0) {
						hasRecord.put(first, 0);
						hasRecord.put(poptab, select2);
						searchBusiness = true;
						type = -1;
						latitude = -1;
						longitude = -1;
						businessListId = -1;
						areaId = -1;
						tvQuYu.setText("区域");
						adapter2.notifyDataSetChanged();
						adapter3.notifyDataSetChanged();
						businessList.clear();
						myAdapter.notifyDataSetChanged();
						page = 1;
						initData();
						return;
					} else {
						// 点击商圈的时候将第三个ListView的索引置-1
						select3 = -1;
						layoutParams.height = height;// 设置同listView2同样的高度
						popList3.add("不限");
						switch (position) {
						case 0:
							break;
						case 1:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("思明区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
							break;
						case 2:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("海沧区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}

							break;
						case 3:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("湖里区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
							break;
						case 4:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("集美区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
							break;
						case 5:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("同安区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
							break;
						case 6:
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals("翔安区")) {
									popList3.add(areaList.get(i).getBusinessName());
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
							break;
						default:
							break;
						}
						scrollView.scrollTo(0, 0);

					}
					adapter3.notifyDataSetChanged();
					adapter2.notifyDataSetChanged();
					return;
				}
				adapter2.notifyDataSetChanged();
				myAdapter.notifyDataSetChanged();
				page = 1;
				initData();
			}
		});
		poplv3.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				select3 = position;
				popIndex = 2;
				page = 1;
				hasRecord.put(third, position);
				hasRecord.put(poptab, select2);
				businessList.clear();
				params.put("selectTypeId", selectTypeId);
				if (sl_popmiddle.getVisibility() == View.VISIBLE) {
					hasRecord.put(first, 0);
					searchBusiness = true;
					indexFlag = 1;
					type = -1;
					latitude = -1;
					longitude = -1;
					if (position == 0) {
						businessListId = -1;
						if (select2 == 0) {
							// areaId = -1;
							// tvQuYu.setText("区域");
							// adapter3.notifyDataSetChanged();
							// businessList.clear();
							// initData();
							return;
						} else {
							tvQuYu.setText(popList2.get(select2));
							tvQuYu.setTextColor(Color.parseColor("#1693FE"));
							tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
							adapter3.notifyDataSetChanged();
							areaId = hasMapAreaId.get(popList2.get(select2));
							businessList.clear();
							myAdapter.notifyDataSetChanged();
							page = 1;
							initData();
							return;
						}
					} else {
						businessListId = hasMap.get(position - 1);
					}
				} else {
					hasRecord.put(first, 1);
					searchBusiness = false;
					businessListId = -1;
					areaId = -1;
					type = position + 1;
					initLocation();
					if (!locationClient.isStarted()) {
						locationClient.start();
						adapter3.notifyDataSetChanged();
					}
					tvQuYu.setText(popList3.get(position));
					tvQuYu.setTextColor(Color.parseColor("#1693FE"));
					tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
					return;
				}
				lvShangYe.setSelection(1);
				tvQuYu.setText(popList3.get(position));
				tvQuYu.setTextColor(Color.parseColor("#1693FE"));
				tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
				myAdapter.notifyDataSetChanged();
				page = 1;
				initData();
				adapter3.notifyDataSetChanged();
			}
		});

	}

	private void setPopMore(View view) {
		berryList.clear();
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_area_more).length; i++) {
			berryList.add(getResources().getStringArray(R.array.shangye_area_more)[i]);
		}
		View line1 = view.findViewById(R.id.zujin_line);
		View line2 = view.findViewById(R.id.line2);
		View line3 = view.findViewById(R.id.line3);
		View line4 = view.findViewById(R.id.line4);
		View line5 = view.findViewById(R.id.line5);
		View lineTeSe = view.findViewById(R.id.line_tese);
		View llPrice = view.findViewById(R.id.ll_shangyedichan_price);
		View llDownPayment = view.findViewById(R.id.ll_shangyedichan_downpayment);
		View llClassify = view.findViewById(R.id.ll_shangyedichan_classify);
		View llDecorate = view.findViewById(R.id.ll_shangyedichan_decorate);
		View llHouseage = view.findViewById(R.id.ll_shangyedichan_houseage);
		View llFloorNum = view.findViewById(R.id.ll_shangyedichan_floornum);
		View llJiBie = view.findViewById(R.id.ll_shangyedichan_jibie);
		View llLocation = view.findViewById(R.id.ll_shangyedichan_location);
		View llTeSe = view.findViewById(R.id.ll_shangyedichan_tese);
		TextView tvPrice = (TextView) view.findViewById(R.id.tv_rental_price);
		if (selectTypeId == 0) {
			priceList.clear();
			priceList.addAll(rentalList);

		} else if (selectTypeId == 1) {
			llDownPayment.setVisibility(View.VISIBLE);
			tvPrice.setText("总价");
			priceList.clear();
			priceList.addAll(totalPriceList);
		} else if (selectTypeId == 2) {
			tvPrice.setText("租金");
			priceList.clear();
			priceList.addAll(rentalList);
			line1.setVisibility(View.GONE);
			llClassify.setVisibility(View.GONE);
			llDecorate.setVisibility(View.GONE);
			llHouseage.setVisibility(View.GONE);
			llFloorNum.setVisibility(View.GONE);
			line1.setVisibility(View.GONE);

		} else if (selectTypeId == 3) {
			tvPrice.setText("总价");
			priceList.clear();
			priceList.addAll(totalPriceList);
			llDownPayment.setVisibility(View.VISIBLE);
			llClassify.setVisibility(View.GONE);
			llDecorate.setVisibility(View.GONE);
			llHouseage.setVisibility(View.GONE);
			llFloorNum.setVisibility(View.GONE);
			// line3.setVisibility(View.GONE);
			// line4.setVisibility(View.GONE);
			// line5.setVisibility(View.GONE);
		} else if (selectTypeId == 4) {
			tvPrice.setText("租金");
			priceList.clear();
			priceList.addAll(rentalList);
			llJiBie.setVisibility(View.VISIBLE);
			llClassify.setVisibility(View.GONE);
		} else if (selectTypeId == 5) {
			tvPrice.setText("总价");
			priceList.clear();
			priceList.addAll(totalPriceList);
			llJiBie.setVisibility(View.VISIBLE);
			llClassify.setVisibility(View.GONE);
		} else if (selectTypeId == 6) {
			tvPrice.setText("租金");
			priceList.clear();
			priceList.addAll(cheweiPriceList);
			berryList.clear();
			for (int i = 0; i < getResources().getStringArray(R.array.shangye_cheweiare_more).length; i++) {
				berryList.add(getResources().getStringArray(R.array.shangye_cheweiare_more)[i]);
			}
			line1.setVisibility(View.GONE);
			llLocation.setVisibility(View.VISIBLE);
			llTeSe.setVisibility(View.VISIBLE);
			llClassify.setVisibility(View.GONE);
			llDecorate.setVisibility(View.GONE);
			llHouseage.setVisibility(View.GONE);
			llFloorNum.setVisibility(View.GONE);
		} else if (selectTypeId == 7) {
			// tvPrice.setText("总价");
			// priceList.clear();
			// priceList.addAll(cheweiPriceList);
			berryList.clear();
			for (int i = 0; i < getResources().getStringArray(R.array.shangye_cheweiare_more).length; i++) {
				berryList.add(getResources().getStringArray(R.array.shangye_cheweiare_more)[i]);
			}
			lineTeSe.setVisibility(View.GONE);
			llPrice.setVisibility(View.GONE);
			line1.setVisibility(View.GONE);
			llLocation.setVisibility(View.VISIBLE);
			llTeSe.setVisibility(View.VISIBLE);
			llClassify.setVisibility(View.GONE);
			llDecorate.setVisibility(View.GONE);
			llHouseage.setVisibility(View.GONE);
			llFloorNum.setVisibility(View.GONE);
		}
		gvDownPayment = (NoScrollGridView) view.findViewById(R.id.grid_downpayment);
		gvArea = (NoScrollGridView) view.findViewById(R.id.grid_shangyedichan_area);
		gvRental = (NoScrollGridView) view.findViewById(R.id.grid_rental);
		gvClassify = (NoScrollGridView) view.findViewById(R.id.grid_classify);
		gvDecorate = (NoScrollGridView) view.findViewById(R.id.grid_decoreate);
		gvHouseAge = (NoScrollGridView) view.findViewById(R.id.grid_shangyedichan_houseage);
		gvFloornum = (NoScrollGridView) view.findViewById(R.id.grid_shangyedichan_floornum);
		gvJiBie = (NoScrollGridView) view.findViewById(R.id.grid_shangyedichan_jibie);
		gvLocation = (NoScrollGridView) view.findViewById(R.id.grid_shagyedichan_location);
		gvTeSe = (NoScrollGridView) view.findViewById(R.id.grid_shagyedichan_tese);

		view.findViewById(R.id.tv_shangyedichan_clear).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				areaIndex = null;
				rentalIndex = null;
				classifyIndex = null;
				decorateIndex = -1;
				houseAgeIndex = null;
				floorNumIndex = null;
				totalPriceRange = null;
				downPaymentRange = null;
				officeLevel = -1;
				addressId = -1;
				specialId = -1;
				areaParkingRange = null;
				rentalParkingRange = null;
				moreIndex1 = -1;
				moreIndex2 = -1;
				moreIndex3 = -1;
				moreIndex4 = -1;
				moreIndex5 = -1;
				moreIndex6 = -1;
				moreIndex7 = -1;
				moreIndex8 = -1;
				moreIndex9 = -1;
				moreIndex10 = -1;
				areaAdapter.notifyDataSetChanged();
				rentalAdapter.notifyDataSetChanged();
				classifyAdapter.notifyDataSetChanged();
				decorateAdapter.notifyDataSetChanged();
				floorNumAdapter.notifyDataSetChanged();
				houseAgeAdapter.notifyDataSetChanged();
				downPaymentAdapter.notifyDataSetChanged();
				jiBieAdapter.notifyDataSetChanged();
				locationAdapter.notifyDataSetChanged();
				teSeAdapter.notifyDataSetChanged();
			}
		});

		view.findViewById(R.id.btn_shangyedichan_sure).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO
				if (selectTypeId == 0) {
					popIndex = 3;
				} else if (selectTypeId == 1) {
					popIndex = 4;
					// params.put("areaHouseRange", areaIndex);
					// params.put("totalPriceRange", totalPriceRange);
					// params.put("shopTypeName", classifyIndex);
					// params.put("downPaymentRange", downPaymentRange);
					// params.put("decorateDegreeTypeId", decorateIndex);
					// params.put("houseAgeRange", houseAgeIndex);
					// params.put("floorNumRange", floorNumIndex);
				} else if (selectTypeId == 2) {
					popIndex = 5;
					// params.put("areaFactoryRange", areaIndex);
					// params.put("rentalRange", rentalIndex);
				} else if (selectTypeId == 3) {
					popIndex = 6;
					// params.put("areaFactoryRange", areaIndex);
					// params.put("totalPriceRange", totalPriceRange);
					// params.put("downPaymentRange", downPaymentRange);
				} else if (selectTypeId == 4) {
					popIndex = 7;
					// params.put("areaHouseRange", areaIndex);
					// params.put("rentalRange", rentalIndex);
					// params.put("officeLevel", officeLevel);
					// params.put("decorateDegreeTypeId", decorateIndex);
					// params.put("houseAgeRange", houseAgeIndex);
					// params.put("floorNumRange", floorNumIndex);
				} else if (selectTypeId == 5) {
					popIndex = 8;
					// params.put("areaHouseRange", areaIndex);
					// params.put("totalPriceRange", totalPriceRange);
					// params.put("officeLevel", officeLevel);
					// params.put("decorateDegreeTypeId", decorateIndex);
					// params.put("houseAgeRange", houseAgeIndex);
					// params.put("floorNumRange", floorNumIndex);
				} else if (selectTypeId == 6) {
					popIndex = 9;
					// params.put("areaParkingRange", areaParkingRange);
					// params.put("addressId", addressId);
					// if (specialId != -1) {
					// params.put("specialId", specialId);
					// }
					// params.put("rentalParkingRange", rentalParkingRange);
				} else if (selectTypeId == 7) {
					popIndex = 10;
					// params.put("areaParkingRange", areaParkingRange);
					// params.put("addressId", addressId);
					// if (specialId != -1) {
					// params.put("specialId", specialId);
					// }
				}
				// params.put("selectTypeId", selectTypeId);
				businessList.clear();
				page = 1;
				lvShangYe.setSelection(1);
				myAdapter.notifyDataSetChanged();
				initData();
			}
		});
		gvArea.setAdapter(areaAdapter);
		gvRental.setAdapter(rentalAdapter);
		gvClassify.setAdapter(classifyAdapter);
		gvDecorate.setAdapter(decorateAdapter);
		gvHouseAge.setAdapter(houseAgeAdapter);
		gvFloornum.setAdapter(floorNumAdapter);
		gvDownPayment.setAdapter(downPaymentAdapter);
		gvJiBie.setAdapter(jiBieAdapter);
		gvLocation.setAdapter(locationAdapter);
		gvTeSe.setAdapter(teSeAdapter);
		areaAdapter.notifyDataSetChanged();
		rentalAdapter.notifyDataSetChanged();
		classifyAdapter.notifyDataSetChanged();
		decorateAdapter.notifyDataSetChanged();
		floorNumAdapter.notifyDataSetChanged();
		houseAgeAdapter.notifyDataSetChanged();
		downPaymentAdapter.notifyDataSetChanged();
		jiBieAdapter.notifyDataSetChanged();
		locationAdapter.notifyDataSetChanged();
		teSeAdapter.notifyDataSetChanged();
		gvArea.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex1 = position;
				if (selectTypeId == 6 || selectTypeId == 7) {
					if (position == 0) {
						areaParkingRange = "不限";
					} else if (position == 1) {
						areaParkingRange = 0 + "," + 20;
					} else if (position == 2) {
						areaParkingRange = 20 + "," + 25;
					} else if (position == 3) {
						areaParkingRange = 25 + "," + 30;
					} else if (position == 4) {
						areaParkingRange = 30 + "," + 35;
					} else if (position == 5) {
						areaParkingRange = 35 + "," + 40;
					} else {
						areaParkingRange = 40 + "," + 1;
					}

				} else {
					if (position == 0) {
						areaIndex = "不限";
					} else if (position == 1) {
						areaIndex = 50 + "," + 70;
					} else if (position == 2) {
						areaIndex = 70 + "," + 90;
					} else if (position == 3) {
						areaIndex = 90 + "," + 120;
					} else if (position == 4) {
						areaIndex = 120 + "," + 150;
					} else {
						areaIndex = 150 + "," + -1;
					}
				}
				areaAdapter.notifyDataSetChanged();
			}
		});
		gvRental.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex2 = position;
				if (selectTypeId == 0 || selectTypeId == 2 || selectTypeId == 4) {
					if (position == 0) {
						rentalIndex = "不限";
					} else if (position == 1) {
						rentalIndex = 0 + "," + 2000;
					} else if (position == 2) {
						rentalIndex = 2000 + "," + 3000;
					} else if (position == 3) {
						rentalIndex = 3000 + "," + 4000;
					} else if (position == 4) {
						rentalIndex = 4000 + "," + 5000;
					} else if (position == 5) {
						rentalIndex = 5000 + "," + 8000;
					} else {
						rentalIndex = 8000 + "," + -1;
					}
				} else if (selectTypeId == 1 || selectTypeId == 3 || selectTypeId == 5) {
					if (position == 0) {
						totalPriceRange = "不限";
					} else if (position == 1) {
						totalPriceRange = 0 + "," + 100;
					} else if (position == 2) {
						totalPriceRange = 100 + "," + 200;
					} else if (position == 3) {
						totalPriceRange = 200 + "," + 300;
					} else if (position == 4) {
						totalPriceRange = 300 + "," + 400;
					} else if (position == 5) {
						totalPriceRange = 400 + "," + 600;
					} else if (position == 6) {
						totalPriceRange = 600 + "," + 1000;
					} else {
						totalPriceRange = 1000 + "," + -1;
					}
				} else if (selectTypeId == 6) {

					if (position == 0) {
						rentalParkingRange = "不限";
					} else if (position == 1) {
						rentalParkingRange = 0 + "," + 500;
					} else if (position == 2) {
						rentalParkingRange = 500 + "," + 1000;
					} else if (position == 3) {
						rentalParkingRange = 1000 + "," + 1500;
					} else if (position == 4) {
						rentalParkingRange = 1500 + "," + 2000;
					} else if (position == 5) {
						rentalParkingRange = 2000 + "," + 2500;
					} else {
						rentalParkingRange = 2500 + "," + 1;
					}
				}
				rentalAdapter.notifyDataSetChanged();
			}
		});
		gvClassify.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex3 = position;
				classifyIndex = classifyList.get(position);
				classifyAdapter.notifyDataSetChanged();
			}
		});
		gvDecorate.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex4 = position;
				decorateIndex = position;
				decorateAdapter.notifyDataSetChanged();
			}
		});
		gvHouseAge.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex5 = position;
				if (position == 0) {
					houseAgeIndex = "不限";
				} else if (position == 1) {
					houseAgeIndex = 0 + "," + 2;
				} else if (position == 2) {
					houseAgeIndex = 2 + "," + 5;
				} else if (position == 3) {
					houseAgeIndex = 5 + "," + 10;
				} else if (position == 4) {
					houseAgeIndex = 10 + "," + -1;
				}
				houseAgeAdapter.notifyDataSetChanged();
			}
		});
		gvFloornum.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex6 = position;
				if (position == 0) {
					floorNumIndex = "不限";
				} else if (position == 1) {
					floorNumIndex = "地下";
				} else if (position == 2) {
					floorNumIndex = "1层";
				} else if (position == 3) {
					floorNumIndex = -1 + "," + 6;
				} else if (position == 4) {
					floorNumIndex = 6 + "," + 12;
				} else {
					floorNumIndex = 12 + "," + -1;
				}
				floorNumAdapter.notifyDataSetChanged();
			}
		});
		gvJiBie.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex7 = position;
				officeLevel = position;
				jiBieAdapter.notifyDataSetChanged();
			}
		});
		gvLocation.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex8 = position;
				addressId = position;
				locationAdapter.notifyDataSetChanged();
			}
		});
		gvTeSe.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex9 = position;
				specialId = position;
				teSeAdapter.notifyDataSetChanged();
			}
		});
		gvDownPayment.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex10 = position;
				if (position == 0) {
					downPaymentRange = "不限";
				} else if (position == 1) {
					downPaymentRange = 0 + "," + 50;
				} else if (position == 2) {
					downPaymentRange = 50 + "," + 100;
				} else if (position == 3) {
					downPaymentRange = 100 + "," + 150;
				} else if (position == 4) {
					downPaymentRange = 150 + "," + 300;
				} else {
					downPaymentRange = 300 + "," + -1;
				}
				downPaymentAdapter.notifyDataSetChanged();
			}
		});

	}

	public void selectParams() {
		params = new RequestParams();
		if (params != null) {
			params.put("pageNum", page);
			params.put("sign", sign);
			params.put("selectTypeId", selectTypeId);
			if (businessListId != -1) {
				params.put("businessListId", businessListId);
			}
			if (latitude != -1) {
				params.put("latitude", latitude);
			}
			if (longitude != -1) {
				params.put("longitude", longitude);
			}
			if (type != -1) {
				params.put("type", type);
			}
			if (selectTypeId == 2 || selectTypeId == 3) {
				if (!TextUtils.isEmpty(areaIndex)) {
					params.put("areaFactoryRange", areaIndex);
				}
			} else {
				if (!TextUtils.isEmpty(areaIndex)) {
					params.put("areaHouseRange", areaIndex);
				}
			}
			if (!TextUtils.isEmpty(rentalIndex)) {
				params.put("rentalRange", rentalIndex);
			}
			if (!TextUtils.isEmpty(classifyIndex)) {
				params.put("shopTypeName", classifyIndex);
			}
			if (decorateIndex != -1) {
				params.put("decorateDegreeTypeId", decorateIndex);
			}
			if (!TextUtils.isEmpty(houseAgeIndex)) {
				params.put("houseAgeRange", houseAgeIndex);
			}
			if (!TextUtils.isEmpty(floorNumIndex)) {
				params.put("floorNumRange", floorNumIndex);
			}
			if (!TextUtils.isEmpty(totalPriceRange)) {
				params.put("totalPriceRange", totalPriceRange);
			}
			if (!TextUtils.isEmpty(downPaymentRange)) {
				params.put("downPaymentRange", downPaymentRange);
			}

			if (officeLevel != -1) {
				params.put("officeLevel", officeLevel);
			}
			if (addressId != -1) {
				params.put("addressId", addressId);
			}
			if (specialId != -1) {
				params.put("specialId", specialId);
			}

			if (!TextUtils.isEmpty(areaParkingRange)) {
				params.put("areaParkingRange", areaParkingRange);
			}
			if (!TextUtils.isEmpty(rentalParkingRange)) {
				params.put("rentalParkingRange", rentalParkingRange);
			}
			if (selectTypeId == 6 || selectTypeId == 7) {
				if (orderType != -1) {
					params.put("orderType", orderType);
				}
			}
			if (paiXu != -1) {
				params.put(paiXuKey, paiXu);
				isDefault = false;
			}
			if (!TextUtils.isEmpty(likeSelect)) {
				params.put("likeSelect", likeSelect);
			}
			if (areaId != -1) {
				params.put("areaId", areaId);
			}
			if (isDefault) {
				params.put("defaultOrder", 0);
			}
		}
	}

	public void cancleParams() {
		searchBusiness = true;
		hasRecord.put(0, -1);
		hasRecord.put(1, -1);
		hasRecord.put(2, -1);
		hasRecord.put(3, -1);
		hasRecord.put(first, -1);
		hasRecord.put(third, -1);
		sign = 1;
		page = 1;
		areaId = -1;
		businessListId = -1;
		latitude = -1;
		longitude = -1;
		type = -1;
		areaIndex = null;
		rentalIndex = null;
		classifyIndex = null;
		decorateIndex = -1;
		houseAgeIndex = null;
		floorNumIndex = null;
		totalPriceRange = null;
		downPaymentRange = null;
		officeLevel = -1;
		addressId = -1;
		specialId = -1;
		areaParkingRange = null;
		rentalParkingRange = null;
		paiXu = -1;
		orderType = -1;
		likeSelect = null;
		etSearch.setText("");
		delete.setVisibility(View.GONE);

		moreIndex1 = -1;
		moreIndex2 = -1;
		moreIndex3 = -1;
		moreIndex4 = -1;
		moreIndex5 = -1;
		moreIndex6 = -1;
		moreIndex7 = -1;
		moreIndex8 = -1;
		moreIndex9 = -1;
		moreIndex10 = -1;
		setNormalState();
	}

	public void setNormalState() {
		// 默认字体的颜色和图片
		tvQuYu.setTextColor(Color.parseColor("#919191"));
		tvGengDuo.setTextColor(Color.parseColor("#919191"));
		tvQuYu.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvGengDuo.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvQuYu.setText("区域");
		tvGengDuo.setText("更多");
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
		Intent intent = null;
		position -= lvShangYe.getHeaderViewsCount();
		if (selectTypeId == 0 || selectTypeId == 1) {
			intent = new Intent(this, ShangPuDetailActivity.class);
		} else if (selectTypeId == 2 || selectTypeId == 3) {
			intent = new Intent(this, ChangFangDetailActivity.class);
		} else if (selectTypeId == 4 || selectTypeId == 5) {
			intent = new Intent(this, XieZiLouActivity.class);
		} else if (selectTypeId == 6 || selectTypeId == 7) {
			intent = new Intent(this, CheWeiActivity.class);
		}
		intent.putExtra("id", businessList.get(position).getId());
		intent.putExtra("selectTypeId", selectTypeId);

		if (intent != null) {
			startActivity(intent);
		}
	}

	class PopAdapter1 extends BaseAdapter {

		@Override
		public int getCount() {
			return popList1.size();
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
			View view = getLayoutInflater().inflate(R.layout.searchkey_item, null);
			TextView tv = (TextView) view.findViewById(R.id.tvSearchKey);
			tv.setText(popList1.get(position));
			if (select1 == position) {
				tv.setTextColor(Color.parseColor("#1693FE"));
			}
			return view;
		}

	}

	class PopAdapter2 extends BaseAdapter {

		@Override
		public int getCount() {
			return popList2.size();
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
			View view = getLayoutInflater().inflate(R.layout.searchkey_item, null);
			TextView tv = (TextView) view.findViewById(R.id.tvSearchKey);
			tv.setText(popList2.get(position));
			if (select2 == position) {
				tv.setTextColor(Color.parseColor("#1693FE"));
			}
			return view;
		}

	}

	class PopAdapter3 extends BaseAdapter {

		@Override
		public int getCount() {
			return popList3.size();
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
			View view = getLayoutInflater().inflate(R.layout.searchkey_item, null);
			TextView tv = (TextView) view.findViewById(R.id.tvSearchKey);
			tv.setText(popList3.get(position));
			if (select3 == position) {
				tv.setTextColor(Color.parseColor("#1693FE"));
			}
			return view;
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
		// openGPSSettings();
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		isRefresh = true;
		initData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		isRefresh = false;
		page++;
		initData();
	}

	public class AreaAdapter extends CommonAdapter<String> {

		public AreaAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			TextView tv = helper.getView(R.id.tv_item);
			tv.setTextColor(Color.parseColor("#919191"));
			tv.setBackgroundResource(R.drawable.shape_text);
			if (moreIndex1 == position) {
				tv.setBackgroundColor(0xff0089fe);
				tv.setTextColor(Color.parseColor("#ffffffff"));
			}
			helper.setText(R.id.tv_item, item);
		}

	}

	public class RentalAdapter extends CommonAdapter<String> {

		public RentalAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			TextView tv = helper.getView(R.id.tv_item);
			tv.setTextColor(Color.parseColor("#919191"));
			tv.setBackgroundResource(R.drawable.shape_text);
			if (moreIndex2 == position) {
				tv.setBackgroundColor(0xff0089fe);
				tv.setTextColor(Color.parseColor("#ffffffff"));
			}
			helper.setText(R.id.tv_item, item);
		}

	}

	public class ClassifyAdapter extends CommonAdapter<String> {

		public ClassifyAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			TextView tv = helper.getView(R.id.tv_item);
			tv.setTextColor(Color.parseColor("#919191"));
			tv.setBackgroundResource(R.drawable.shape_text);
			if (moreIndex3 == position) {
				tv.setBackgroundColor(0xff0089fe);
				tv.setTextColor(Color.parseColor("#ffffffff"));
			}
			helper.setText(R.id.tv_item, item);
		}

	}

	public class DecorateAdapter extends CommonAdapter<String> {

		public DecorateAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			TextView tv = helper.getView(R.id.tv_item);
			tv.setTextColor(Color.parseColor("#919191"));
			tv.setBackgroundResource(R.drawable.shape_text);
			if (moreIndex4 == position) {
				tv.setBackgroundColor(0xff0089fe);
				tv.setTextColor(Color.parseColor("#ffffffff"));
			}
			helper.setText(R.id.tv_item, item);
		}

	}

	public class HouseAgeAdapter extends CommonAdapter<String> {

		public HouseAgeAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			TextView tv = helper.getView(R.id.tv_item);
			tv.setTextColor(Color.parseColor("#919191"));
			tv.setBackgroundResource(R.drawable.shape_text);
			if (moreIndex5 == position) {
				tv.setBackgroundColor(0xff0089fe);
				tv.setTextColor(Color.parseColor("#ffffffff"));
			}
			helper.setText(R.id.tv_item, item);
		}

	}

	public class FloorNumAdapter extends CommonAdapter<String> {

		public FloorNumAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			TextView tv = helper.getView(R.id.tv_item);
			tv.setTextColor(Color.parseColor("#919191"));
			tv.setBackgroundResource(R.drawable.shape_text);
			if (moreIndex6 == position) {
				tv.setBackgroundColor(0xff0089fe);
				tv.setTextColor(Color.parseColor("#ffffffff"));
			}
			helper.setText(R.id.tv_item, item);
		}

	}

	public class DownPaymentAdapter extends CommonAdapter<String> {

		public DownPaymentAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			TextView tv = helper.getView(R.id.tv_item);
			tv.setTextColor(Color.parseColor("#919191"));
			tv.setBackgroundResource(R.drawable.shape_text);
			if (moreIndex10 == position) {
				tv.setBackgroundColor(0xff0089fe);
				tv.setTextColor(Color.parseColor("#ffffffff"));
			}
			helper.setText(R.id.tv_item, item);
		}

	}

	public class JiBieAdapter extends CommonAdapter<String> {

		public JiBieAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			TextView tv = helper.getView(R.id.tv_item);
			tv.setTextColor(Color.parseColor("#919191"));
			tv.setBackgroundResource(R.drawable.shape_text);
			if (moreIndex7 == position) {
				tv.setBackgroundColor(0xff0089fe);
				tv.setTextColor(Color.parseColor("#ffffffff"));
			}
			helper.setText(R.id.tv_item, item);
		}

	}

	public class LocationAdapter extends CommonAdapter<String> {

		public LocationAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			TextView tv = helper.getView(R.id.tv_item);
			tv.setTextColor(Color.parseColor("#919191"));
			tv.setBackgroundResource(R.drawable.shape_text);
			if (moreIndex8 == position) {
				tv.setBackgroundColor(0xff0089fe);
				tv.setTextColor(Color.parseColor("#ffffffff"));
			}
			helper.setText(R.id.tv_item, item);
		}

	}

	public class TeSeAdapter extends CommonAdapter<String> {

		public TeSeAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, String item, int position) {
			TextView tv = helper.getView(R.id.tv_item);
			tv.setTextColor(Color.parseColor("#919191"));
			tv.setBackgroundResource(R.drawable.shape_text);
			if (moreIndex9 == position) {
				tv.setBackgroundColor(0xff0089fe);
				tv.setTextColor(Color.parseColor("#ffffffff"));
			}
			helper.setText(R.id.tv_item, item);
		}
	}

	@Override
	public void onLoadData() {
		initData();
	}
}
