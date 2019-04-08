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
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseListEntity.AreaList;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseListEntity.RentHouseList_;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseListEntity.RentHouseSpecialtyList;

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

public class ZuFangActivity extends BaseActivity
		implements OnItemClickListener, OnClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private TextView tvQuyu;
	private TextView tvZuJin;
	private TextView tvFangXing;
	private TextView tvMore;
	private int poptab = -1;
	private ImageView imgPaiXu;
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<String> popList1 = new ArrayList<>();
	private ArrayList<String> popList2 = new ArrayList<>();
	private ArrayList<String> popList3 = new ArrayList<>();
	private View viewBottom;
	private int select1;
	private int select2;
	private int select3;
	private ArrayList<RentHouseList_> rentList = new ArrayList<>();
	private MyAdapter myAdapter;
	private RequestParams params;
	private ListView mListView;
	private List<AreaList> areaList = new ArrayList<>();
	private HashMap<Integer, Integer> hasMap = new HashMap<>();
	private ArrayList<Integer> intList = new ArrayList<>();
	private PullToRefreshListView ptrListView;
	private PopupWindow popupWindow;
	private boolean isRefresh;
	private int page = 1;
	private Integer pageCount;
	private CustomView customview;
	private LocationClient locationClient;
	public int moreIndex1 = -1;
	public int moreIndex2 = -1;
	public int moreIndex3 = -1;
	public int moreIndex4 = -1;
	private ArrayList<String> berryList = new ArrayList<>();
	private ArrayList<String> orientationList = new ArrayList<>();
	private ArrayList<String> floornumList = new ArrayList<>();
	private ArrayList<String> houseageList = new ArrayList<>();
	private AreaAdapter areaAdapter;
	private OrientationAdapter orientationAdapter;
	private FloorNumAdapter floorNumAdapter;
	private HouseAgeAdapter houseAgeAdapter;
	private NoScrollGridView gvArea;
	private NoScrollGridView gvOrientation;
	private NoScrollGridView gvFloornum;
	private NoScrollGridView gvHouseAge;
	protected String areaIndex = "";
	protected String houseAgeIndex = "";
	protected String floorNumIndex = "";
	protected int orientationIndex = -1;
	private String rentalRange = "";
	private int roomNum = -1;
	private int popIndex = -1;
	private int businessListId = -1;
	private double latitude = -1;
	private double longitude = -1;
	private int type = -1;
	protected int indexFlag;
	private int paiXu = -1;
	protected String paiXuKey;
	// protected int orderType = -1;
	private String likeSelect;
	private View searchEmpty;
	private String specialtyName;
	private Drawable rightDrawableSelect;
	private Drawable rightDrawableNormal;
	private Drawable rightDrawableUp;
	private EditText et_title_Search;
	private View delete;
	private int areaId = -1;
	protected HashMap<String, Integer> hasMapAreaId = new HashMap<>();
	protected int index = -1;
	private int sign = 1;
	private HashMap<Integer, Integer> hasRecord = new HashMap<>();
	private boolean searchBusiness = true;
	private int height;
	private int first = -1;
	private int third = -3;
	private boolean isDefault = true;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zufang);
		hasRecord.put(0, -1);
		hasRecord.put(1, -1);
		hasRecord.put(2, -1);
		hasRecord.put(3, -1);
		hasRecord.put(first, -1);
		hasRecord.put(third, -1);
		list.add("默认排序");
		list.add("租金由低到高");
		list.add("租金 由高到低");
		list.add("面积由小到大");
		list.add("面积由大到小");
		initAdapterList();
		initView();
		initData();
	}

	private void initData() {
		searchEmpty.setVisibility(View.GONE);
		selectParams();
		IndexApi.getInstance().requestRentHouseList(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				RentHouseListEntity rentHoustList = (RentHouseListEntity) object;
				if (isRefresh) {
					rentList.clear();
					areaList.clear();
				}

				pageCount = rentHoustList.getContent().getPageCount();
				if (pageCount <= page) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}

				rentList.addAll(rentHoustList.getContent().getRentHouseList());

				if (rentList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
					ToastUtil.toastShow(ZuFangActivity.this, "无满足条件的房源信息");
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				// if (rentList.isEmpty() && ((!TextUtils.isEmpty(likeSelect))
				// || !TextUtils.isEmpty(specialtyName))) {
				// // searchEmpty.setVisibility(View.VISIBLE);
				// ToastUtil.toastShow(ZuFangActivity.this, "无满足条件的房源信息");
				// } else {
				// searchEmpty.setVisibility(View.GONE);
				// }

				areaList.addAll(rentHoustList.getContent().getAreaList());
				myAdapter.notifyDataSetChanged();
				for (int i = 0; i < areaList.size(); i++) {
					if (!hasMapAreaId.containsKey(areaList.get(i).getAreaName())) {
						hasMapAreaId.put(areaList.get(i).getAreaName(), areaList.get(i).getAreaId());
					}
				}

				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}

			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(ZuFangActivity.this, getResources().getString(R.string.net_error));
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
				ToastUtil.toastShow(ZuFangActivity.this, msg);
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (locationClient != null) {
			locationClient.stop();
		}
	}

	private void initView() {
		searchEmpty = findViewById(R.id.search_empty);
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		viewBottom = findViewById(R.id.topline);
		imgPaiXu = (ImageView) findViewById(R.id.img_rent_paixu);
		imgPaiXu.setOnClickListener(this);

		et_title_Search = (EditText) findViewById(R.id.et_title_Search);
		et_title_Search.setHint(R.string.secondhouseseach);
		et_title_Search.setOnClickListener(this);
		ImageView title_right_img = (ImageView) findViewById(R.id.title_right_iv);
		title_right_img.setImageResource(R.drawable.ditu_white);
		title_right_img.setOnClickListener(this);

		findViewById(R.id.title_left).setOnClickListener(this);

		tvQuyu = (TextView) findViewById(R.id.tv_quyu);
		tvZuJin = (TextView) findViewById(R.id.tv_zujin);
		tvFangXing = (TextView) findViewById(R.id.tv_fangxing);
		tvMore = (TextView) findViewById(R.id.tv_gengduo);
		tvQuyu.setOnClickListener(this);
		tvZuJin.setOnClickListener(this);
		tvFangXing.setOnClickListener(this);
		tvMore.setOnClickListener(this);

		myAdapter = new MyAdapter(this, rentList, R.layout.index_zufang_item);
		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);

		mListView = ptrListView.getRefreshableView();
		mListView.setAdapter(myAdapter);
		mListView.setOnItemClickListener(this);
		delete = findViewById(R.id.img_delete);
		delete.setOnClickListener(this);
		findViewById(R.id.tv_aroundhouse).setOnClickListener(this);

		rightDrawableSelect = getResources().getDrawable(R.drawable.sanjiao_p);
		rightDrawableSelect.setBounds(0, 0, rightDrawableSelect.getMinimumWidth(),
				rightDrawableSelect.getMinimumHeight());
		rightDrawableNormal = getResources().getDrawable(R.drawable.sanjiao_n);
		rightDrawableNormal.setBounds(0, 0, rightDrawableNormal.getMinimumWidth(),
				rightDrawableNormal.getMinimumHeight());
		rightDrawableUp = getResources().getDrawable(R.drawable.sanjiao_p_up);
		rightDrawableUp.setBounds(0, 0, rightDrawableNormal.getMinimumWidth(), rightDrawableUp.getMinimumHeight());
	}

	private void initAdapterList() {
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_area_more).length; i++) {
			berryList.add(getResources().getStringArray(R.array.shangye_area_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.zufang_orientation_more).length; i++) {
			orientationList.add(getResources().getStringArray(R.array.zufang_orientation_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_floornum_more).length; i++) {
			floornumList.add(getResources().getStringArray(R.array.shangye_floornum_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_houseage_more).length; i++) {
			houseageList.add(getResources().getStringArray(R.array.shangye_houseage_more)[i]);
		}
		areaAdapter = new AreaAdapter(this, berryList, R.layout.gric_item_pop_more);
		orientationAdapter = new OrientationAdapter(this, orientationList, R.layout.gric_item_pop_more);
		floorNumAdapter = new FloorNumAdapter(this, floornumList, R.layout.gric_item_pop_more);
		houseAgeAdapter = new HouseAgeAdapter(this, houseageList, R.layout.gric_item_pop_more);
	}

	private void setPopMore(View view) {
		gvArea = (NoScrollGridView) view.findViewById(R.id.grid_rent_area);
		gvOrientation = (NoScrollGridView) view.findViewById(R.id.grid_rent_orientataion);
		gvFloornum = (NoScrollGridView) view.findViewById(R.id.grid_rent_floornum);
		gvHouseAge = (NoScrollGridView) view.findViewById(R.id.grid_rent_houseage);
		view.findViewById(R.id.line_rent).setVisibility(View.GONE);
		view.findViewById(R.id.tv_clear).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				moreIndex1 = -1;
				moreIndex2 = -1;
				moreIndex3 = -1;
				moreIndex4 = -1;
				areaIndex = null;
				orientationIndex = -1;
				floorNumIndex = null;
				houseAgeIndex = null;
				areaAdapter.notifyDataSetChanged();
				orientationAdapter.notifyDataSetChanged();
				floorNumAdapter.notifyDataSetChanged();
				houseAgeAdapter.notifyDataSetChanged();
			}
		});

		view.findViewById(R.id.btn_sure).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rentList.clear();
				areaList.clear();
				popIndex = 3;
				// params = new RequestParams();
				// params.put("areaHouseRange", areaIndex);
				// params.put("orientationId", orientationIndex);
				// params.put("floorNumRange", floorNumIndex);
				// params.put("houseAgeRange", houseAgeIndex);
				page = 1;
				mListView.setSelection(1);
				myAdapter.notifyDataSetChanged();
				initData();
			}
		});
		gvArea.setAdapter(areaAdapter);
		gvOrientation.setAdapter(orientationAdapter);
		gvFloornum.setAdapter(floorNumAdapter);
		gvHouseAge.setAdapter(houseAgeAdapter);
		areaAdapter.notifyDataSetChanged();
		orientationAdapter.notifyDataSetChanged();
		floorNumAdapter.notifyDataSetChanged();
		houseAgeAdapter.notifyDataSetChanged();
		gvArea.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex1 = position;
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
				areaAdapter.notifyDataSetChanged();
			}
		});
		gvOrientation.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex2 = position;
				orientationIndex = position;
				orientationAdapter.notifyDataSetChanged();
			}
		});
		gvHouseAge.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex4 = position;
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
				moreIndex3 = position;
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

	}

	class MyAdapter extends CommonAdapter<RentHouseList_> {

		public MyAdapter(Context context, List<RentHouseList_> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, RentHouseList_ item, int position) {
			TextView tvTitle = (TextView) helper.getView(R.id.tv_title);
			TextView tvAreaListName = (TextView) helper.getView(R.id.tv_areaListName);
			TextView tvRentMoney = (TextView) helper.getView(R.id.tv_rentMoney);
			LinearLayout llSpecial = (LinearLayout) helper.getView(R.id.ll_special);
			helper.getView(R.id.ll_housetype).setVisibility(View.VISIBLE);
			TextView tvHouseType = (TextView) helper.getView(R.id.tv_housetype);
			TextView tvBerryGG = (TextView) helper.getView(R.id.tv_berryGG);
			if (item.getRoomNum() != null && item.getHallNum() != null) {
				tvHouseType.setText(item.getRoomNum() + "室" + item.getHallNum() + "厅");
			} else {
				tvHouseType.setText(0 + "室" + 0 + "厅");
			}
			if (!TextUtils.isEmpty(item.getBerryGG())) {
				tvBerryGG.setText(item.getBerryGG() + "㎡");
			} else {
				tvBerryGG.setText(0 + "㎡");
			}

			tvTitle.setText(item.getTitle());
			tvAreaListName.setText(item.getAreaListName());
			tvRentMoney.setText(item.getRentMoney() + "");
			ImageView imgTitle = (ImageView) helper.getView(R.id.img_newhousepic);
			if (!TextUtils.isEmpty(item.getSurFaceUrl())) {
				AsyncImageLoader.setAsynImages(imgTitle, item.getSurFaceUrl());
			}
			List<RentHouseSpecialtyList> rentHouseSpecialtyList = item.getRentHouseSpecialtyList();
			String color = "";
			llSpecial.removeAllViews();
			if (rentHouseSpecialtyList.size() > 0) {
				for (int i = 0; i < rentHouseSpecialtyList.size(); i++) {
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
					tvHouseTag.setText(rentHouseSpecialtyList.get(i).getSpecialtyName());
					llSpecial.addView(child);
				}
			} else {
				View child = getLayoutInflater().inflate(R.layout.item_text, null);
				TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
				tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
				tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
				tvHouseTag.setText("暂无");
				llSpecial.addView(child);
			}
		}


	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		position -= mListView.getHeaderViewsCount();
		Intent intent = new Intent(this, ZuFangxqActivity.class);
		int id = rentList.get(position).getId();
		intent.putExtra("rentHouseId", id);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_quyu:
			poptab = 0;
			showPopWindow();
			break;
		case R.id.tv_zujin:
			poptab = 1;
			showPopWindow();
			break;
		case R.id.tv_fangxing:
			poptab = 2;
			showPopWindow();
			break;
		case R.id.tv_gengduo:
			poptab = 3;
			showPopWindow();
			break;
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		// 地图导航
		case R.id.title_right_iv:
			ToastUtil.toastShow(this,"暂未开放");

//			Intent intent = new Intent(this, DiTuZhaoFangActivity.class);
//			intent.putExtra("selectTypeId", 2);
//			intent.putExtra("classify", 0);
//			startActivity(intent);
			break;
		case R.id.img_rent_paixu:
			showPaiXuWindow();
			break;
		case R.id.et_title_Search:
			Intent zufangIntent = new Intent(this, IndexSerachActivity.class);
			zufangIntent.putExtra("stageNum", SystemConstant.STATE_PAGE_RENTHOUSE);
			startActivityForResult(zufangIntent, SystemConstant.STATE_PAGE_RENTHOUSE);
			break;
		case R.id.img_delete:
			likeSelect = null;
			specialtyName = null;
			et_title_Search.setText("");
			delete.setVisibility(View.GONE);
			rentList.clear();
			areaList.clear();
			sign = 1;
			page = 1;
			mListView.setSelection(1);
			myAdapter.notifyDataSetChanged();
			initData();
			break;
		// 查看附近的房源
		case R.id.tv_aroundhouse:
			Intent intent2 = new Intent(this, AroundSecHouseActivity.class);
			intent2.putExtra("houseResourceTypeName", "租房");
			startActivity(intent2);
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
			if (arg1 == 0) {
				sign = 1;
				likeSelect = data.getStringExtra("likeSelect");
				if (!TextUtils.isEmpty(likeSelect)) {
					page = 1;
					mListView.setSelection(1);
					popIndex = 5;
					delete.setVisibility(View.VISIBLE);
					et_title_Search.setText(likeSelect);
				} else {
					et_title_Search.setText("");
					delete.setVisibility(View.GONE);
				}
			} else {
				sign = 3;
				specialtyName = data.getStringExtra("specialtyName");
				page = 1;
				mListView.setSelection(1);
				popIndex = 6;
				delete.setVisibility(View.VISIBLE);
				et_title_Search.setText(specialtyName);
			}
			index = -1;
			rentList.clear();
			areaList.clear();
			myAdapter.notifyDataSetChanged();
			mListView.setSelection(1);
			page = 1;
			initData();
		}
	}

	private void showPaiXuWindow() {
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
				if (position == 0) {
					paiXu = 0;
					paiXuKey = "defaultOrder";
				} else if (position == 1) {
					// paiXu = "租金";
					paiXu = 1;
					paiXuKey = "rental";
					// orderType = 1;
					popIndex = 4;

				} else if (position == 2) {
					paiXu = 0;
					paiXuKey = "rental";
					// orderType = 0;
					popIndex = 4;

				} else if (position == 3) {
					// paiXu = "房源面积";
					paiXu = 1;
					paiXuKey = "berryGG";
					// orderType = 1;
					popIndex = 4;

				} else if (position == 4) {
					// paiXu = "房源面积";
					paiXu = 0;
					paiXuKey = "berryGG";
					// orderType = 0;
					popIndex = 4;

				}
				page = 1;
				rentList.clear();
				areaList.clear();
				mListView.setSelection(1);
				paixuAdapter.notifyDataSetChanged();
				myAdapter.notifyDataSetChanged();
				initData();
			}

		});
	}

	// 区域、租金、房型、更多popWindow
	private void showPopWindow() {
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
		View headView = view.findViewById(R.id.include_zufang_head);
		final View line0 = view.findViewById(R.id.line0);
		final View line1 = view.findViewById(R.id.line1);

		LinearLayout ll_popleft = (LinearLayout) view.findViewById(R.id.lv_popleft);
		final ScrollView sl_popmiddle = (ScrollView) view.findViewById(R.id.sl_popmiddle);
		LinearLayout sl_popright = (LinearLayout) view.findViewById(R.id.lv_popright);

		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		// popupWindow.setAnimationStyle(R.style.PopupStyle);
		popupWindow.showAsDropDown(viewBottom);
		TextView tv_bottom = (TextView) view.findViewById(R.id.tv_bottom);

		if (poptab != 0) {
			line0.setVisibility(View.GONE);
			line1.setVisibility(View.GONE);
		}

		// // 默认字体的颜色和图片
		// tvQuyu.setTextColor(Color.parseColor("#919191"));
		// tvZuJin.setTextColor(Color.parseColor("#919191"));
		// tvFangXing.setTextColor(Color.parseColor("#919191"));
		// tvMore.setTextColor(Color.parseColor("#919191"));
		//
		// tvQuyu.setCompoundDrawables(null, null, rightDrawableNormal, null);
		// tvZuJin.setCompoundDrawables(null, null, rightDrawableNormal, null);
		// tvFangXing.setCompoundDrawables(null, null, rightDrawableNormal,
		// null);
		// tvMore.setCompoundDrawables(null, null, rightDrawableNormal, null);
		popList1.clear();
		popList2.clear();
		popList3.clear();
		select1 = hasRecord.get(first);
		select2 = hasRecord.get(poptab);
		select3 = hasRecord.get(third);
		if (poptab == 0) {
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
			tvQuyu.setTextColor(Color.parseColor("#1693FE"));
			tvQuyu.setCompoundDrawables(null, null, rightDrawableUp, null);
			setPriceView.setVisibility(View.GONE);
		}
		final EditText etLowPrice = (EditText) setPriceView.findViewById(R.id.et_lowprice);
		final EditText etHighPrice = (EditText) setPriceView.findViewById(R.id.et_highprice);
		if (poptab == 1) {

			String[] strings = getResources().getStringArray(R.array.pop_rentprice);
			for (int i = 0; i < strings.length; i++) {
				popList2.add(strings[i]);
			}
			setPriceView.setVisibility(View.VISIBLE);
			setPriceView.findViewById(R.id.bt_sure).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String lowPrice = etLowPrice.getText().toString();
					String highPrice = etHighPrice.getText().toString();
					popIndex = 1;
					rentList.clear();
					areaList.clear();
					if (TextUtils.isEmpty(lowPrice)) {
						if (TextUtils.isEmpty(highPrice)) {
							rentalRange = null;
							tvZuJin.setText("租金");
							myAdapter.notifyDataSetChanged();
							page = 1;
							initData();
							return;
						}
					}
					if (!TextUtils.isEmpty(lowPrice) && !TextUtils.isEmpty(highPrice)) {
						double lPrice = Double.parseDouble(lowPrice);
						double hPrice = Double.parseDouble(highPrice);
						if (lPrice > hPrice) {
							ToastUtil.toastShow(ZuFangActivity.this, "最低价格不能高于最高价格");
							return;
						}
					}
					if (TextUtils.isEmpty(lowPrice)) {
						lowPrice = "0";
					}
					if (TextUtils.isEmpty(highPrice)) {
						highPrice = "-1";
					}

					int lowPrice1 = Integer.parseInt(lowPrice);
					int highPrice1 = Integer.parseInt(highPrice);
					String showString = null;
					if (lowPrice1 == 0) {
						showString = highPrice1 + "元以下";
					}
					if (highPrice1 == -1) {
						showString = lowPrice1 + "元以上";
					}
					if (TextUtils.isEmpty(showString)) {
						showString = lowPrice1 + "-" + highPrice1;
					}
					rentalRange = null;
					rentalRange = lowPrice + "," + highPrice;
					hasRecord.put(poptab, -1);
					page = 1;
					mListView.setSelection(1);
					tvZuJin.setText(showString);
					tvZuJin.setTextColor(Color.parseColor("#1693FE"));
					tvZuJin.setCompoundDrawables(null, null, rightDrawableSelect, null);
					myAdapter.notifyDataSetChanged();
					initData();
				}
			});
			ll_popleft.setVisibility(View.GONE);
			sl_popright.setVisibility(View.GONE);
			tvZuJin.setTextColor(Color.parseColor("#1693FE"));
			tvZuJin.setCompoundDrawables(null, null, rightDrawableUp, null);

		}
		if (poptab == 2) {
			String[] strings = getResources().getStringArray(R.array.pop_fangxing);
			for (int i = 0; i < strings.length; i++) {
				popList2.add(strings[i]);
			}
			ll_popleft.setVisibility(View.GONE);
			setPriceView.setVisibility(View.GONE);
			// sl_popmiddle.setVisibility(View.GONE);
			sl_popright.setVisibility(View.GONE);
			tvFangXing.setTextColor(Color.parseColor("#1693FE"));
			tvFangXing.setCompoundDrawables(null, null, rightDrawableUp, null);
		}

		if (poptab == 3) {
			setPopMore(view);
			headView.setVisibility(View.VISIBLE);
			ll_popleft.setVisibility(View.GONE);
			sl_popmiddle.setVisibility(View.GONE);
			tvMore.setTextColor(Color.parseColor("#1693FE"));
			tvMore.setCompoundDrawables(null, null, rightDrawableUp, null);
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
				// TODO Auto-generated method stub
				popupWindow.dismiss();

			}
		});

		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				if (tvQuyu.getText().toString().equals("区域")) {
					tvQuyu.setTextColor(Color.parseColor("#919191"));
					tvQuyu.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvQuyu.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
				if (tvZuJin.getText().toString().equals("租金")) {
					tvZuJin.setTextColor(Color.parseColor("#919191"));
					tvZuJin.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvZuJin.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
				if (tvFangXing.getText().toString().equals("房型")) {
					tvFangXing.setTextColor(Color.parseColor("#919191"));
					tvFangXing.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvFangXing.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
				if (TextUtils.isEmpty(areaIndex) && orientationIndex == -1 && TextUtils.isEmpty(floorNumIndex)
						&& TextUtils.isEmpty(houseAgeIndex)) {
					tvMore.setTextColor(Color.parseColor("#919191"));
					tvMore.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvMore.setTextColor(Color.parseColor("#1693FE"));
					tvMore.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
			}
		});
		poplv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				select1 = position;
				// 点击区域或者附近的时候清空后面两个
				select2 = -1;
				select3 = -1;
				adapter1.notifyDataSetChanged();
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
					popList3.clear();
					adapter2.notifyDataSetChanged();

				}
				adapter3.notifyDataSetChanged();
				mListView.setSelection(0);
			}
		});
		poplv2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				select2 = position;
				popList3.clear();
				hasMap.clear();
				intList.clear();
				// areaList.get(position).getBusinessName();
				if (poptab == 0) {
					LayoutParams layoutParams = rlPopWindow.getLayoutParams();
					height = poplv2.getHeight();
					if (position == 0) {
						hasRecord.put(first, 0);
						hasRecord.put(poptab, select2);
						searchBusiness = true;
						type = -1;
						latitude = -1;
						longitude = -1;
						areaId = -1;
						businessListId = -1;
						tvQuyu.setText("区域");
						adapter3.notifyDataSetChanged();
						rentList.clear();
						areaList.clear();
						myAdapter.notifyDataSetChanged();
						page = 1;
						initData();
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

				if (poptab == 2) {
					hasRecord.put(poptab, position);
					roomNum = position;
					popIndex = 2;
					if (position == 0) {
						tvFangXing.setText("房型");
						popupWindow.dismiss();
					} else {
						tvFangXing.setTextColor(Color.parseColor("#1693FE"));
						tvFangXing.setCompoundDrawables(null, null, rightDrawableSelect, null);
						tvFangXing.setText(popList2.get(position));
					}
				} else {
					hasRecord.put(poptab, position);
					rentalRange = null;
					switch (position) {
					case 0:
						rentalRange = "不限";
						break;
					case 1:
						rentalRange = 0 + "," + 2000;
						break;
					case 2:
						rentalRange = 2000 + "," + 3000;
						break;
					case 3:
						rentalRange = 3000 + "," + 4000;
						break;
					case 4:
						rentalRange = 4000 + "," + 5000;
						break;
					case 5:
						rentalRange = 5000 + "," + 8000;
						break;
					case 6:
						rentalRange = 8000 + "," + -1;
						break;
					default:
						break;
					}
					popIndex = 1;
					if (position == 0) {
						tvZuJin.setText("租金");
						popupWindow.dismiss();
					} else {
						tvZuJin.setTextColor(Color.parseColor("#1693FE"));
						tvZuJin.setCompoundDrawables(null, null, rightDrawableSelect, null);
						tvZuJin.setText(popList2.get(position));
					}
				}
				rentList.clear();
				areaList.clear();
				page = 1;
				// setSearch(popupWindow);
				adapter2.notifyDataSetChanged();
				mListView.setSelection(1);
				myAdapter.notifyDataSetChanged();
				initData();

			}
		});
		poplv3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				select3 = position;
				popIndex = 0;
				hasRecord.put(third, position);
				hasRecord.put(poptab, select2);
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
							// tvQuyu.setText("区域");
							// adapter3.notifyDataSetChanged();
							// rentList.clear();
							// areaList.clear();
							// initData();
							return;
						} else {
							tvQuyu.setText(popList2.get(select2));
							tvQuyu.setTextColor(Color.parseColor("#1693FE"));
							tvQuyu.setCompoundDrawables(null, null, rightDrawableSelect, null);
							adapter3.notifyDataSetChanged();
							areaId = hasMapAreaId.get(popList2.get(select2));
							rentList.clear();
							areaList.clear();
							myAdapter.notifyDataSetChanged();
							page = 1;
							initData();
							return;
						}
					} else {
						// 解决点了其他筛选之后，直接点击商圈闪退问题
						if (hasMap.get(position - 1) == null) {
							intList.clear();
							for (int i = 0; i < areaList.size(); i++) {
								if (areaList.get(i).getAreaName().equals(popList2.get(select2))) {
									intList.add(areaList.get(i).getBusinessListId());
								}
							}
							for (int j = 0; j < intList.size(); j++) {
								hasMap.put(j, intList.get(j));
							}
						}
						businessListId = hasMap.get(position - 1);
					}
				} else {
					hasRecord.put(first, 1);
					searchBusiness = false;
					areaId = -1;
					businessListId = -1;
					type = position + 1;
					initLocation(position + 1);
					if (!locationClient.isStarted()) {
						locationClient.start();
						adapter3.notifyDataSetChanged();
					}
					tvQuyu.setText(popList3.get(position));
					tvQuyu.setTextColor(Color.parseColor("#1693FE"));
					tvQuyu.setCompoundDrawables(null, null, rightDrawableSelect, null);
					return;
				}
				tvQuyu.setText(popList3.get(position));
				tvQuyu.setTextColor(Color.parseColor("#1693FE"));
				tvQuyu.setCompoundDrawables(null, null, rightDrawableSelect, null);
				rentList.clear();
				areaList.clear();
				page = 1;
				mListView.setSelection(1);
				adapter3.notifyDataSetChanged();
				myAdapter.notifyDataSetChanged();
				initData();
			}
		});
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

	public void selectParams() {
		params = new RequestParams();
		if (params != null) {
			params.put("pageNum", page);
			params.put("sign", sign);
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
			if (!TextUtils.isEmpty(rentalRange)) {
				params.put("rentalRange", rentalRange);
			}
			if (roomNum != -1) {
				params.put("roomNum", roomNum);
			}
			if (!TextUtils.isEmpty(areaIndex)) {
				params.put("areaHouseRange", areaIndex);
			}
			if (orientationIndex != -1) {
				params.put("orientationId", orientationIndex);
			}
			if (!TextUtils.isEmpty(floorNumIndex)) {
				params.put("floorNumRange", floorNumIndex);
			}
			if (!TextUtils.isEmpty(houseAgeIndex)) {
				params.put("houseAgeRange", houseAgeIndex);
			}
			// if (!TextUtils.isEmpty(paiXu)) {
			// params.put(paiXuKey, paiXu);
			// }
			if (paiXu != -1) {
				params.put(paiXuKey, paiXu);
				isDefault = false;
			}
			// if (orderType != -1) {
			// params.put("orderType", orderType);
			// }
			if (areaId != -1) {
				params.put("areaId", areaId);
			}
			if (!TextUtils.isEmpty(specialtyName)) {
				params.put("specialtyName", specialtyName);
			}
			if (!TextUtils.isEmpty(likeSelect)) {
				params.put("likeSelect", likeSelect);
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
		rentalRange = null;
		roomNum = -1;
		areaIndex = null;
		orientationIndex = -1;
		floorNumIndex = null;
		houseAgeIndex = null;
		paiXu = -1;
		// orderType = -1;
		likeSelect = null;
		specialtyName = null;
		moreIndex1 = -1;
		moreIndex2 = -1;
		moreIndex3 = -1;
		moreIndex4 = -1;
		setNormalState();
	}

	public void setNormalState() {
		// 默认字体的颜色和图片
		tvQuyu.setTextColor(Color.parseColor("#919191"));
		tvZuJin.setTextColor(Color.parseColor("#919191"));
		tvFangXing.setTextColor(Color.parseColor("#919191"));
		tvMore.setTextColor(Color.parseColor("#919191"));

		tvQuyu.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvZuJin.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvFangXing.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvMore.setCompoundDrawables(null, null, rightDrawableNormal, null);

		tvQuyu.setText("区域");
		tvZuJin.setText("租金");
		tvFangXing.setText("房型");
		tvMore.setText("更多");
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		isRefresh = true;
		initData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page++;
		isRefresh = false;
		initData();
	}

	private void initLocation(final int type) {
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
				// TODO Auto-generated method stub
				if (location == null) {
					return;
				}
				rentList.clear();
				areaList.clear();
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				page = 1;
				sign = 2;
				mListView.setSelection(1);
				myAdapter.notifyDataSetChanged();
				initData();

			}
		});

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

	public class OrientationAdapter extends CommonAdapter<String> {

		public OrientationAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
			// TODO Auto-generated constructor stub
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

	public class FloorNumAdapter extends CommonAdapter<String> {

		public FloorNumAdapter(Context context, List<String> mDatas, int layoutId) {
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

	public class HouseAgeAdapter extends CommonAdapter<String> {

		public HouseAgeAdapter(Context context, List<String> mDatas, int layoutId) {
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

	@Override
	public void onLoadData() {
		initData();
	}

}
