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
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseListEntity.AreaList;
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseListEntity.SecondHouseList_;
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseListEntity.SecondHouseSpecialtyList;

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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class IndexSecondHouseActivity extends BaseActivity
		implements OnClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private RelativeLayout rl_quyu;
	int poptab = 0;
	private TextView tvQuYu;
	private TextView tvZongJia;
	private TextView tvFangXing;
	private TextView tvGengDuo;
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<String> popList1 = new ArrayList<>();
	private ArrayList<String> popList2 = new ArrayList<>();
	private ArrayList<String> popList3 = new ArrayList<>();
	private View viewBottom;
	private int select1 = -1;
	private int select2 = -1;
	private int select3 = -1;
	private PullToRefreshListView ptrListView;
	private int page = 1;
	private boolean isRefresh;
	private int pageCount;
	private RequestParams params;
	private List<SecondHouseList_> secondHouseList = new ArrayList<>();
	private List<AreaList> areaList = new ArrayList<>();
	private CustomView customview;
	private MyAdapter myAdapter;
	public int moreIndex1 = -1;
	public int moreIndex2 = -1;
	public int moreIndex3 = -1;
	public int moreIndex4 = -1;
	protected int popIndex = -1;
	protected int indexFlag;
	protected int businessListId = -1;
	private PopupWindow popupWindow;
	protected String totalPriceRange;
	private ListView mListView;
	private HashMap<Integer, Integer> hasMap = new HashMap<>();
	private ArrayList<Integer> intList = new ArrayList<>();
	protected int roomNum = -1;
	private ArrayList<String> berryList = new ArrayList<>();
	private ArrayList<String> authHouseList = new ArrayList<>();
	private ArrayList<String> teSeList = new ArrayList<>();
	private ArrayList<String> houseageList = new ArrayList<>();
	private HashMap<String, Integer> hasMapAreaId = new HashMap<>();
	private AreaAdapter areaAdapter;
	private AuthResAdapter authHouseAdapter;
	private TeSeAdapter teSeAdapter;
	private HouseAgeAdapter houseAgeAdapter;
	private LocationClient locationClient;
	protected double latitude = -1;
	protected double longitude = -1;
	protected int type = -1;
	private NoScrollGridView gvArea;
	private NoScrollGridView gvAuthRes;
	private NoScrollGridView gvTeSe;
	private NoScrollGridView gvHouseAge;
	protected String areaHouseRange;
	protected int authId = -1;
	protected String houseAgeRange;
	protected int specialtyId = -1;
	protected int paiXu = -1;
	protected String paiXuKey;
	// protected int orderType = -1;
	private String likeSelect;
	private View searchEmpty;
	private String specialtyName;
	protected int index = -1;
	private Drawable rightDrawableSelect;
	private Drawable rightDrawableNormal;
	private Drawable rightDrawableUp;
	private EditText etSearch;
	private View delete;
	protected int areaId = -1;
	private PaiXuPopAdapter paixuAdapter;
	private int sign = 1;
	private HashMap<Integer, Integer> hasRecord = new HashMap<>();
	private boolean searchBusiness = true;
	private int height;
	private int first = -1;
	private int third = -3;
	private boolean isDefault = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_indexsecondhouse);
		hasRecord.put(0, -1);
		hasRecord.put(1, -1);
		hasRecord.put(2, -1);
		hasRecord.put(3, -1);
		hasRecord.put(first, -1);
		hasRecord.put(third, -1);

		list.add("默认排序");
		list.add("总价由低到高");
		list.add("总价由高到低");
		list.add("房源面积由小到大");
		list.add("房源面积由大到小");
		list.add("发布时间");
		list.add("更新时间");
		paixuAdapter = new PaiXuPopAdapter(this, list, R.layout.paixu_search_item);
		initAdapterList();
		likeSelect = getIntent().getStringExtra("likeSelect");
		specialtyName = getIntent().getStringExtra("specialtyName");
		initView();
		initData();
	}

	private void initView() {
		searchEmpty = findViewById(R.id.search_empty);
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		viewBottom = findViewById(R.id.topline);
		findViewById(R.id.img_second_paixu).setOnClickListener(this);
		etSearch = (EditText) findViewById(R.id.et_title_Search);
		etSearch.setHint(R.string.secondhouseseach);
		etSearch.setOnClickListener(this);
		findViewById(R.id.title_right_iv).setOnClickListener(this);
		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);
		myAdapter = new MyAdapter(this, secondHouseList, R.layout.item_secondhouse);
		mListView = ptrListView.getRefreshableView();
		mListView.setAdapter(myAdapter);
		delete = findViewById(R.id.img_delete);
		delete.setOnClickListener(this);
		if (!TextUtils.isEmpty(likeSelect)) {
			etSearch.setText(likeSelect);
			delete.setVisibility(View.VISIBLE);
		}

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				position -= mListView.getHeaderViewsCount();
				Intent intent = new Intent(IndexSecondHouseActivity.this, SecondHouseDetailActivity.class);
				intent.putExtra("secondHouseId", secondHouseList.get(position).getId());
				intent.putExtra("areaListId", secondHouseList.get(position).getAreaListId());
				startActivity(intent);
			}
		});
		rl_quyu = (RelativeLayout) findViewById(R.id.rl_quyu);
		rl_quyu.setOnClickListener(this);
		findViewById(R.id.rl_fangxing).setOnClickListener(this);
		findViewById(R.id.rl_zongjia).setOnClickListener(this);
		findViewById(R.id.rl_gengdou).setOnClickListener(this);

		tvQuYu = (TextView) findViewById(R.id.tv_quyu);
		tvZongJia = (TextView) findViewById(R.id.tv_zongjia);
		tvFangXing = (TextView) findViewById(R.id.tv_fangxing);
		tvGengDuo = (TextView) findViewById(R.id.tv_gengduo);

		rightDrawableSelect = getResources().getDrawable(R.drawable.sanjiao_p);
		rightDrawableSelect.setBounds(0, 0, rightDrawableSelect.getMinimumWidth(),
				rightDrawableSelect.getMinimumHeight());
		rightDrawableNormal = getResources().getDrawable(R.drawable.sanjiao_n);
		rightDrawableNormal.setBounds(0, 0, rightDrawableNormal.getMinimumWidth(),
				rightDrawableNormal.getMinimumHeight());
		rightDrawableUp = getResources().getDrawable(R.drawable.sanjiao_p_up);
		rightDrawableUp.setBounds(0, 0, rightDrawableNormal.getMinimumWidth(), rightDrawableUp.getMinimumHeight());
		findViewById(R.id.tv_aroundhouse).setOnClickListener(this);
	}

	private void initAdapterList() {
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_area_more).length; i++) {
			berryList.add(getResources().getStringArray(R.array.shangye_area_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.secondhouse_authhouseres_more).length; i++) {
			authHouseList.add(getResources().getStringArray(R.array.secondhouse_authhouseres_more)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.pop_xiaoqu_tese).length; i++) {
			teSeList.add(getResources().getStringArray(R.array.pop_xiaoqu_tese)[i]);
		}
		for (int i = 0; i < getResources().getStringArray(R.array.shangye_houseage_more).length; i++) {
			houseageList.add(getResources().getStringArray(R.array.shangye_houseage_more)[i]);
		}

		areaAdapter = new AreaAdapter(this, berryList, R.layout.gric_item_pop_more);
		authHouseAdapter = new AuthResAdapter(this, authHouseList, R.layout.gric_item_pop_more);
		teSeAdapter = new TeSeAdapter(this, teSeList, R.layout.gric_item_pop_more);
		houseAgeAdapter = new HouseAgeAdapter(this, houseageList, R.layout.gric_item_pop_more);
	}

	private void initData() {
		searchEmpty.setVisibility(View.GONE);
		selectParams();
		IndexApi.getInstance().requestSecondHouseList(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				SecondHouseListEntity secondHouseListEntity = (SecondHouseListEntity) object;
				if (isRefresh) {
					secondHouseList.clear();
					areaList.clear();
				}

				pageCount = secondHouseListEntity.getContent().getPageCount();
				if (pageCount <= page) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}
				secondHouseList.addAll(secondHouseListEntity.getContent().getSecondHouseList());
				areaList.addAll(secondHouseListEntity.getContent().getAreaList());
				myAdapter.notifyDataSetChanged();

				for (int i = 0; i < areaList.size(); i++) {
					if (!hasMapAreaId.containsKey(areaList.get(i).getAreaName())) {
						hasMapAreaId.put(areaList.get(i).getAreaName(), areaList.get(i).getAreaId());
					}
				}
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
				if (secondHouseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
					ToastUtil.toastShow(IndexSecondHouseActivity.this, "无满足条件的房源信息");
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				// if (secondHouseList.isEmpty()
				// && ((!TextUtils.isEmpty(likeSelect)) ||
				// !TextUtils.isEmpty(specialtyName))) {
				// // searchEmpty.setVisibility(View.VISIBLE);
				// ToastUtil.toastShow(IndexSecondHouseActivity.this,
				// "无满足条件的房源信息");
				// } else {
				// searchEmpty.setVisibility(View.GONE);
				// }
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(IndexSecondHouseActivity.this, getResources().getString(R.string.net_error));
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
				ToastUtil.toastShow(IndexSecondHouseActivity.this, msg);
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});
	}

	class MyAdapter extends CommonAdapter<SecondHouseList_> {

		public MyAdapter(Context context, List<SecondHouseList_> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(ViewHolder helper, SecondHouseList_ item, int position) {
			helper.getView(R.id.rl_secondhandhouse).setVisibility(View.GONE);
			TextView tvRoomHall = (TextView) helper.getView(R.id.tv_room_hall);
			if (item.getRoomNum() != null && item.getHallNum() != null) {
				tvRoomHall.setText(item.getRoomNum() + "室" + item.getHallNum() + "厅");
			} else {
				tvRoomHall.setText(0 + "室" + 0 + "厅");
			}
			TextView tvTitle = (TextView) helper.getView(R.id.tv_collect_title01);
			tvTitle.setText(item.getTitle());
			TextView tvBerry = (TextView) helper.getView(R.id.tv_berryGG);
			String berryGG = item.getBerryGG();
			if (!TextUtils.isEmpty(berryGG)) {
				tvBerry.setText(berryGG + "㎡");
			} else {
				tvBerry.setText(0 + "㎡");
			}
			TextView tvSalePrice = (TextView) helper.getView(R.id.tv_saleprice);
			tvSalePrice.setText("" + item.getSalePrice());
			TextView tvPrice = (TextView) helper.getView(R.id.tv_price);
			tvPrice.setText(item.getPrice() + "元/平");
			TextView tvAreaListName = (TextView) helper.getView(R.id.tv_collect_arealistname);
			tvAreaListName.setText(item.getAreaListName());
			View imgRenZheng = helper.getView(R.id.img_renzheng_web);
			View imgWeiRenZheng = helper.getView(R.id.img_weirenzheng_web);
			if (item.getIsAuth() == 0) {
				imgRenZheng.setVisibility(View.GONE);
				imgWeiRenZheng.setVisibility(View.VISIBLE);
			} else {
				imgWeiRenZheng.setVisibility(View.GONE);
				imgRenZheng.setVisibility(View.VISIBLE);
			}
			ImageView imgTitle = (ImageView) helper.getView(R.id.img_newhousepic);
			if (!TextUtils.isEmpty(item.getSurFaceUrl())) {
				AsyncImageLoader.setAsynImages(imgTitle, item.getSurFaceUrl());
			}
			LinearLayout llSecondSpecialist = (LinearLayout) helper.getView(R.id.ll_secondHouseSpecialist);
			llSecondSpecialist.removeAllViews();
			List<SecondHouseSpecialtyList> secondHouseSpecialtyList = item.getSecondHouseSpecialtyList();
			if (secondHouseSpecialtyList.size() > 0) {
				for (int i = 0; i < secondHouseSpecialtyList.size(); i++) {
					if (secondHouseSpecialtyList.size() > 4) {
						return;
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
				tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
				tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
				tvHouseTag.setText("暂无");
				llSecondSpecialist.addView(child);
			}
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (locationClient != null) {
			locationClient.stop();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_quyu:
			poptab = 0;
			showPopWindow();
			break;
		case R.id.rl_zongjia:
			poptab = 1;
			showPopWindow();
			break;
		case R.id.rl_fangxing:
			poptab = 2;
			showPopWindow();
			break;
		case R.id.rl_gengdou:
			poptab = 3;
			showPopWindow();
			break;
		case R.id.title_right_iv:
			ToastUtil.toastShow(this,"暂未开放");

//			Intent intent = new Intent(this, DiTuZhaoFangActivity.class);
//			intent.putExtra("selectTypeId", 1);
//			intent.putExtra("classify", 1);
//			startActivity(intent);
			break;
		case R.id.img_second_paixu:
			showPaiXuWindow();
			break;
		case R.id.et_title_Search:
			Intent newhouseIntent = new Intent(this, IndexSerachActivity.class);
			newhouseIntent.putExtra("stageNum", SystemConstant.STATE_PAGE_SECONDHOUSE);
			startActivityForResult(newhouseIntent, SystemConstant.STATE_PAGE_SECONDHOUSE);
			break;
		// 清除搜索框内的内容
		case R.id.img_delete:
			likeSelect = null;
			specialtyName = null;
			sign = 1;
			page = 1;
			etSearch.setText("");
			delete.setVisibility(View.GONE);
			secondHouseList.clear();
			areaList.clear();
			mListView.setSelection(1);
			myAdapter.notifyDataSetChanged();
			initData();
			break;
		// 查看附近的房源
		case R.id.tv_aroundhouse:
			Intent intent2 = new Intent(this, AroundSecHouseActivity.class);
			intent2.putExtra("houseResourceTypeName", "二手房");
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
					// params = new RequestParams();
					// params.put("likeSelect", likeSelect);

					mListView.setSelection(1);
					popIndex = 5;
					delete.setVisibility(View.VISIBLE);
					etSearch.setText(likeSelect);
				} else {
					isDefault = true;
					etSearch.setText("");
					delete.setVisibility(View.GONE);
				}
			} else {
				sign = 3;
				// params = new RequestParams();
				specialtyName = data.getStringExtra("specialtyName");
				// params.put("specialtyName", specialtyName);
				mListView.setSelection(1);
				popIndex = 6;
				delete.setVisibility(View.VISIBLE);
				etSearch.setText(specialtyName);
			}
			index = -1;
			secondHouseList.clear();
			areaList.clear();
			myAdapter.notifyDataSetChanged();
			mListView.setSelection(1);
			page = 1;
			initData();
		}
	}

	private void showPaiXuWindow() {
		// params = new RequestParams();
		View view = getLayoutInflater().inflate(R.layout.item_listview, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.setAnimationStyle(R.style.PopupStyle);
		popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		ListView paiXuList = (ListView) view.findViewById(R.id.list_PaiXu);
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
				popIndex = 4;
				if (position == 0) {
					paiXu = 0;
					paiXuKey = "defaultOrder";
				} else if (position == 1) {
					paiXu = 1;
					paiXuKey = "salePrice";
					// orderType = 1;
				} else if (position == 2) {
					paiXu = 0;
					paiXuKey = "salePrice";
					// orderType = 0;
				} else if (position == 3) {
					paiXu = 1;
					paiXuKey = "berryGG";
					// orderType = 1;
				} else if (position == 4) {
					paiXu = 0;
					paiXuKey = "berryGG";
					// orderType = 0;
				} else if (position == 5) {
					paiXu = 0;
					paiXuKey = "createTime";
					// orderType = 0;
				} else if (position == 6) {
					paiXu = 0;
					paiXuKey = "updateTime";
					// orderType = 0;
				}
				paixuAdapter.notifyDataSetChanged();
				// params.put(paiXuKey, paiXu);
				// params.put("orderType", orderType);
				secondHouseList.clear();
				areaList.clear();
				page = 1;
				mListView.setSelection(1);
				myAdapter.notifyDataSetChanged();
				initData();
			}

		});
	}

	// private void showPaiXuWindow() {
	// list.clear();
	// View view = getLayoutInflater().inflate(R.layout.item_listview, null);
	// final PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(this,
	// view);
	// popupWindow.setAnimationStyle(R.style.PopupStyle);
	// popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
	// ListView paiXuList = (ListView) view.findViewById(R.id.list_PaiXu);
	// paiXuList.setAdapter(new PaiXuPopAdapter(this, list,
	// R.layout.searchkey_item));
	// view.findViewById(R.id.textView1).setOnClickListener(new
	// OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// popupWindow.dismiss();
	// }
	// });
	// }

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

	// 区域、总价、房型、更多popWindow

	private void showPopWindow() {
		// select1 = -1;
		// select2 = -1;
		// select3 = -1;
		// params = new RequestParams();
		View view = getLayoutInflater().inflate(R.layout.jingjirenpop_item, null);
		final LinearLayout rlPopWindow = (LinearLayout) view.findViewById(R.id.relativeLayout);
		final ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView2);
		ListView poplv1 = (ListView) view.findViewById(R.id.poplist1);
		final ListView poplv2 = (ListView) view.findViewById(R.id.poplist2);
		final ListView poplv3 = (ListView) view.findViewById(R.id.poplist3);
		View setPriceView = view.findViewById(R.id.ll_setprice);
		View headView = view.findViewById(R.id.include_secondhouse_head);
		final View line0 = view.findViewById(R.id.line0);
		final View line1 = view.findViewById(R.id.line1);

		LinearLayout ll_popleft = (LinearLayout) view.findViewById(R.id.lv_popleft);
		final ScrollView sl_popmiddle = (ScrollView) view.findViewById(R.id.sl_popmiddle);
		LinearLayout sl_popright = (LinearLayout) view.findViewById(R.id.lv_popright);

		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.showAsDropDown(viewBottom);
		TextView tv_bottom = (TextView) view.findViewById(R.id.tv_bottom);

		if (poptab != 0) {
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
		final EditText etLowPrice = (EditText) setPriceView.findViewById(R.id.et_lowprice);
		final EditText etHighPrice = (EditText) setPriceView.findViewById(R.id.et_highprice);
		if (poptab == 1) {

			String[] strings = getResources().getStringArray(R.array.pop_secondhouse_totalprice);
			for (int i = 0; i < strings.length; i++) {
				popList2.add(strings[i]);
			}
			setPriceView.setVisibility(View.VISIBLE);
			setPriceView.findViewById(R.id.bt_sure).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String showString = null;
					String lowPrice = etLowPrice.getText().toString();
					String highPrice = etHighPrice.getText().toString();
					secondHouseList.clear();
					areaList.clear();
					if (TextUtils.isEmpty(lowPrice)) {
						if (TextUtils.isEmpty(highPrice)) {
							totalPriceRange = null;
							tvZongJia.setText("总价");
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
							ToastUtil.toastShow(IndexSecondHouseActivity.this, "最低价格不能高于最高价格");
							return;
						}
					}
					if (TextUtils.isEmpty(lowPrice)) {
						lowPrice = "0";
					}
					if (TextUtils.isEmpty(highPrice)) {
						highPrice = "-1";
					}
					popIndex = 1;
					int lowPrice1 = Integer.parseInt(lowPrice);
					int highPrice1 = Integer.parseInt(highPrice);
					if (lowPrice1 == 0) {
						showString = highPrice1 + "万以下";
					}
					if (highPrice1 == -1) {
						showString = lowPrice1 + "万以上";
					}
					if (TextUtils.isEmpty(showString)) {
						showString = lowPrice1 + "-" + highPrice1 + "万";
					}
					totalPriceRange = lowPrice + "," + highPrice;
					hasRecord.put(poptab, -1);
					page = 1;
					mListView.setSelection(1);
					tvZongJia.setText(showString);
					tvZongJia.setTextColor(Color.parseColor("#1693FE"));
					tvZongJia.setCompoundDrawables(null, null, rightDrawableSelect, null);
					myAdapter.notifyDataSetChanged();
					hasRecord.put(poptab, -1);
					initData();
				}
			});
			ll_popleft.setVisibility(View.GONE);
			sl_popright.setVisibility(View.GONE);
			tvZongJia.setTextColor(Color.parseColor("#1693FE"));
			tvZongJia.setCompoundDrawables(null, null, rightDrawableUp, null);

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
			headView.setVisibility(View.VISIBLE);
			setPopMore(view);
			ll_popleft.setVisibility(View.GONE);
			sl_popmiddle.setVisibility(View.GONE);
			tvGengDuo.setTextColor(Color.parseColor("#1693FE"));
			tvGengDuo.setCompoundDrawables(null, null, rightDrawableUp, null);
		}
		final PopAdapter1 adapter1 = new PopAdapter1();
		poplv1.setAdapter(adapter1);
		final PopAdapter2 adapter2 = new PopAdapter2();
		poplv2.setAdapter(adapter2);
		final PopAdapter3 adapter3 = new PopAdapter3();
		poplv3.setAdapter(adapter3);
		adapter1.notifyDataSetChanged();
		adapter2.notifyDataSetChanged();
		adapter3.notifyDataSetChanged();

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
				if (tvQuYu.getText().toString().equals("区域")) {
					tvQuYu.setTextColor(Color.parseColor("#919191"));
					tvQuYu.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
				if (tvZongJia.getText().toString().equals("总价")) {
					tvZongJia.setTextColor(Color.parseColor("#919191"));
					tvZongJia.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvZongJia.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}
				if (tvFangXing.getText().toString().equals("房型")) {
					tvFangXing.setTextColor(Color.parseColor("#919191"));
					tvFangXing.setCompoundDrawables(null, null, rightDrawableNormal, null);
				} else {
					tvFangXing.setCompoundDrawables(null, null, rightDrawableSelect, null);
				}

				if (TextUtils.isEmpty(areaHouseRange) && authId == -1 && specialtyId == -1
						&& TextUtils.isEmpty(houseAgeRange)) {
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
						tvQuYu.setText("区域");
						adapter3.notifyDataSetChanged();
						secondHouseList.clear();
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
					// params.put("roomNum", roomNum);
				} else {
					hasRecord.put(poptab, position);
					totalPriceRange = null;
					switch (position) {
					case 0:
						totalPriceRange = "不限";
						break;
					case 1:
						totalPriceRange = 0 + "," + 100;
						break;
					case 2:
						totalPriceRange = 100 + "," + 200;
						break;
					case 3:
						totalPriceRange = 200 + "," + 300;
						break;
					case 4:
						totalPriceRange = 300 + "," + 400;
						break;
					case 5:
						totalPriceRange = 400 + "," + 600;
						break;
					case 6:
						totalPriceRange = 600 + "," + 1000;
						break;
					case 7:
						totalPriceRange = 1000 + "," + -1;
						break;
					default:
						break;
					}
					popIndex = 1;
					if (position == 0) {
						tvZongJia.setText("总价");
						popupWindow.dismiss();
					} else {
						tvZongJia.setTextColor(Color.parseColor("#1693FE"));
						tvZongJia.setCompoundDrawables(null, null, rightDrawableSelect, null);
						tvZongJia.setText(popList2.get(position));
					}
					// params.put("totalPriceRange", totalPriceRange);
				}
				secondHouseList.clear();
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
				hasRecord.put(third, position);
				hasRecord.put(poptab, select2);
				popIndex = 0;
				if (sl_popmiddle.getVisibility() == View.VISIBLE) {
					hasRecord.put(first, 0);
					searchBusiness = true;
					indexFlag = 1;
					type = -1;
					latitude = -1;
					longitude = -1;
					businessListId = -1;
					if (position == 0) {
						if (select2 == 0) {
							// areaId = -1;
							// tvQuYu.setText("区域");
							// adapter3.notifyDataSetChanged();
							// secondHouseList.clear();
							// areaList.clear();
							// initData();
							return;
						} else {
							tvQuYu.setText(popList2.get(select2));
							tvQuYu.setTextColor(Color.parseColor("#1693FE"));
							tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
							adapter3.notifyDataSetChanged();
							areaId = hasMapAreaId.get(popList2.get(select2));
							secondHouseList.clear();
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
					// params.put("businessListId", businessListId);
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
					tvQuYu.setText(popList3.get(position));
					tvQuYu.setTextColor(Color.parseColor("#1693FE"));
					tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
					return;
				}
				tvQuYu.setText(popList3.get(position));
				tvQuYu.setTextColor(Color.parseColor("#1693FE"));
				tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
				secondHouseList.clear();
				areaList.clear();
				page = 1;
				mListView.setSelection(1);
				adapter3.notifyDataSetChanged();
				myAdapter.notifyDataSetChanged();
				initData();
			}
		});
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
				secondHouseList.clear();
				areaList.clear();
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				// params.put("latitude", latitude);
				// params.put("longitude", longitude);
				// params.put("type", type);
				page = 1;
				sign = 2;
				mListView.setSelection(1);
				myAdapter.notifyDataSetChanged();
				initData();

			}
		});

	}

	private void setPopMore(View view) {
		gvArea = (NoScrollGridView) view.findViewById(R.id.grid_secondhouse_area);
		gvAuthRes = (NoScrollGridView) view.findViewById(R.id.grid_authres);
		gvTeSe = (NoScrollGridView) view.findViewById(R.id.grid_tese);
		gvHouseAge = (NoScrollGridView) view.findViewById(R.id.grid_secondhouse_houseage);
		view.findViewById(R.id.houseage_line).setVisibility(View.GONE);
		view.findViewById(R.id.tv_second_clear).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				moreIndex1 = -1;
				moreIndex2 = -1;
				moreIndex3 = -1;
				moreIndex4 = -1;
				areaHouseRange = null;
				authId = -1;
				specialtyId = -1;
				houseAgeRange = null;
				areaAdapter.notifyDataSetChanged();
				authHouseAdapter.notifyDataSetChanged();
				teSeAdapter.notifyDataSetChanged();
				houseAgeAdapter.notifyDataSetChanged();
			}
		});

		view.findViewById(R.id.btn_second_sure).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				secondHouseList.clear();
				areaList.clear();
				popIndex = 3;
				// params = new RequestParams();
				// params.put("areaHouseRange", areaHouseRange);
				// params.put("authId", authId);
				// params.put("specialtyId", specialtyId);
				// params.put("houseAgeRange", houseAgeRange);
				page = 1;
				mListView.setSelection(1);
				myAdapter.notifyDataSetChanged();
				initData();
			}
		});
		gvArea.setAdapter(areaAdapter);
		gvAuthRes.setAdapter(authHouseAdapter);
		gvTeSe.setAdapter(teSeAdapter);
		gvHouseAge.setAdapter(houseAgeAdapter);
		areaAdapter.notifyDataSetChanged();
		authHouseAdapter.notifyDataSetChanged();
		teSeAdapter.notifyDataSetChanged();
		houseAgeAdapter.notifyDataSetChanged();
		gvArea.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex1 = position;
				if (position == 0) {
					areaHouseRange = "不限";
				} else if (position == 1) {
					areaHouseRange = 50 + "," + 70;
				} else if (position == 2) {
					areaHouseRange = 70 + "," + 90;
				} else if (position == 3) {
					areaHouseRange = 90 + "," + 120;
				} else if (position == 4) {
					areaHouseRange = 120 + "," + 150;
				} else {
					areaHouseRange = 150 + "," + -1;
				}
				areaAdapter.notifyDataSetChanged();
			}
		});
		gvAuthRes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex2 = position;
				authId = position;
				authHouseAdapter.notifyDataSetChanged();
			}
		});

		gvTeSe.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex3 = position;
				specialtyId = position;
				teSeAdapter.notifyDataSetChanged();
			}
		});
		gvHouseAge.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				moreIndex4 = position;
				if (position == 0) {
					houseAgeRange = "不限";
				} else if (position == 1) {
					houseAgeRange = 0 + "," + 2;
				} else if (position == 2) {
					houseAgeRange = 2 + "," + 5;
				} else if (position == 3) {
					houseAgeRange = 5 + "," + 10;
				} else if (position == 4) {
					houseAgeRange = 10 + "," + -1;
				}
				houseAgeAdapter.notifyDataSetChanged();
			}
		});

	}
	// private void showPopWindow() {
	// select1 = -1;
	// select2 = -1;
	// select3 = -1;
	//
	// View view = getLayoutInflater().inflate(R.layout.jingjirenpop_item,
	// null);
	// ListView poplv1 = (ListView) view.findViewById(R.id.poplist1);
	// ListView poplv2 = (ListView) view.findViewById(R.id.poplist2);
	// ListView poplv3 = (ListView) view.findViewById(R.id.poplist3);
	// View setPriceView = view.findViewById(R.id.ll_setprice);
	// View headView = view.findViewById(R.id.include_zufang_head);
	// View line0 = view.findViewById(R.id.line0);
	// View line1 = view.findViewById(R.id.line1);
	//
	// LinearLayout ll_popleft = (LinearLayout)
	// view.findViewById(R.id.lv_popleft);
	// ScrollView sl_popmiddle = (ScrollView)
	// view.findViewById(R.id.sl_popmiddle);
	// LinearLayout sl_popright = (LinearLayout)
	// view.findViewById(R.id.lv_popright);
	//
	// final PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(this,
	// view);
	// popupWindow.setAnimationStyle(R.style.PopupStyle);
	// popupWindow.showAsDropDown(viewBottom);
	// TextView tv_bottom = (TextView) view.findViewById(R.id.tv_bottom);
	//
	// if (poptab != 0) {
	// line0.setVisibility(View.GONE);
	// line1.setVisibility(View.GONE);
	// }
	//
	// Drawable rightDrawableSelect =
	// getResources().getDrawable(R.drawable.sanjiao_p);
	// rightDrawableSelect.setBounds(0, 0,
	// rightDrawableSelect.getMinimumWidth(),
	// rightDrawableSelect.getMinimumHeight());
	// final Drawable rightDrawableNormal =
	// getResources().getDrawable(R.drawable.xiala);
	// rightDrawableNormal.setBounds(0, 0,
	// rightDrawableNormal.getMinimumWidth(),
	// rightDrawableNormal.getMinimumHeight());
	// // 默认字体的颜色和图片
	// tvQuYu.setTextColor(Color.parseColor("#919191"));
	// tvZongJia.setTextColor(Color.parseColor("#919191"));
	// tvFangXing.setTextColor(Color.parseColor("#919191"));
	// tvGengDuo.setTextColor(Color.parseColor("#919191"));
	//
	// tvQuYu.setCompoundDrawables(null, null, rightDrawableNormal, null);
	// tvZongJia.setCompoundDrawables(null, null, rightDrawableNormal, null);
	// tvFangXing.setCompoundDrawables(null, null, rightDrawableNormal, null);
	// tvGengDuo.setCompoundDrawables(null, null, rightDrawableNormal, null);
	// popList1.clear();
	// popList2.clear();
	// popList3.clear();
	// if (poptab == 0) {
	// String[] strings1 = getResources().getStringArray(R.array.pop_quyu1);
	// String[] strings2 = getResources().getStringArray(R.array.pop_quyu2);
	// String[] strings3 = getResources().getStringArray(R.array.pop_quyu3);
	// for (int i = 0; i < strings1.length; i++) {
	// popList1.add(strings1[i]);
	// }
	// for (int i = 0; i < strings2.length; i++) {
	// popList2.add(strings2[i]);
	// }
	// for (int i = 0; i < strings3.length; i++) {
	// popList3.add(strings3[i]);
	// }
	// setPriceView.setVisibility(View.GONE);
	// headView.setVisibility(View.GONE);
	// tvQuYu.setTextColor(Color.parseColor("#1693FE"));
	// tvQuYu.setCompoundDrawables(null, null, rightDrawableSelect, null);
	// }
	// if (poptab == 1) {
	// String[] strings = getResources().getStringArray(R.array.pop_totalprice);
	// for (int i = 0; i < strings.length; i++) {
	// popList2.add(strings[i]);
	// }
	// setPriceView.setVisibility(View.VISIBLE);
	// ll_popleft.setVisibility(View.GONE);
	// sl_popright.setVisibility(View.GONE);
	// tvZongJia.setTextColor(Color.parseColor("#1693FE"));
	// tvZongJia.setCompoundDrawables(null, null, rightDrawableSelect, null);
	//
	// }
	// if (poptab == 2) {
	// String[] strings = getResources().getStringArray(R.array.pop_fangxing);
	// for (int i = 0; i < strings.length; i++) {
	// popList1.add(strings[i]);
	// }
	// sl_popmiddle.setVisibility(View.GONE);
	// sl_popright.setVisibility(View.GONE);
	// tvFangXing.setTextColor(Color.parseColor("#1693FE"));
	// tvFangXing.setCompoundDrawables(null, null, rightDrawableSelect, null);
	//
	// }
	//
	// if (poptab == 3) {
	// list.clear();
	// headView.setVisibility(View.VISIBLE);
	// ll_popleft.setVisibility(View.GONE);
	// sl_popmiddle.setVisibility(View.GONE);
	// tvGengDuo.setTextColor(Color.parseColor("#1693FE"));
	// tvGengDuo.setCompoundDrawables(null, null, rightDrawableSelect, null);
	// }
	// final PopAdapter1 adapter1 = new PopAdapter1();
	// poplv1.setAdapter(adapter1);
	// final PopAdapter2 adapter2 = new PopAdapter2();
	// poplv2.setAdapter(adapter2);
	// final PopAdapter3 adapter3 = new PopAdapter3();
	// poplv3.setAdapter(adapter3);
	//
	// tv_bottom.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// popupWindow.dismiss();
	//
	// }
	// });
	//
	// popupWindow.setOnDismissListener(new OnDismissListener() {
	//
	// @Override
	// public void onDismiss() {
	// tvQuYu.setTextColor(Color.parseColor("#303030"));
	// tvZongJia.setTextColor(Color.parseColor("#303030"));
	// tvFangXing.setTextColor(Color.parseColor("#303030"));
	// tvGengDuo.setTextColor(Color.parseColor("#303030"));
	//
	// tvQuYu.setCompoundDrawables(null, null, rightDrawableNormal, null);
	// tvZongJia.setCompoundDrawables(null, null, rightDrawableNormal, null);
	// tvFangXing.setCompoundDrawables(null, null, rightDrawableNormal, null);
	// tvGengDuo.setCompoundDrawables(null, null, rightDrawableNormal, null);
	// }
	// });
	// poplv1.setOnItemClickListener(new OnItemClickListener() {
	//
	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// select1 = position;
	// adapter1.notifyDataSetChanged();
	// }
	// });
	// poplv2.setOnItemClickListener(new OnItemClickListener() {
	//
	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// select2 = position;
	// adapter2.notifyDataSetChanged();
	// }
	// });
	// poplv3.setOnItemClickListener(new OnItemClickListener() {
	//
	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position,
	// long id) {
	// select3 = position;
	// adapter3.notifyDataSetChanged();
	// }
	// });
	// }

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

	public void selectParams() {
		params = new RequestParams();
		if (params != null) {
			params.put("sign", sign);
			params.put("pageNum", page);
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

			if (!TextUtils.isEmpty(totalPriceRange)) {
				params.put("totalPriceRange", totalPriceRange);
			}

			if (roomNum != -1) {
				params.put("roomNum", roomNum);
			}

			if (!TextUtils.isEmpty(areaHouseRange)) {
				params.put("areaHouseRange", areaHouseRange);
			}

			if (authId != -1) {
				params.put("authId", authId);
			}
			if (specialtyId != -1) {
				params.put("specialtyId", specialtyId);
			}
			if (!TextUtils.isEmpty(houseAgeRange)) {
				params.put("houseAgeRange", houseAgeRange);
			}
			if (paiXu != -1) {
				params.put(paiXuKey, paiXu);
				isDefault = false;
			}
			// if (orderType != -1) {
			// params.put("orderType", orderType);
			// }
			if (!TextUtils.isEmpty(specialtyName)) {
				params.put("specialtyName", specialtyName);
			}
			if (areaId != -1) {
				params.put("areaId", areaId);
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
		totalPriceRange = null;
		roomNum = -1;
		areaHouseRange = null;
		authId = -1;
		specialtyId = -1;
		houseAgeRange = null;
		paiXu = -1;
		// orderType = -1;
		specialtyName = null;
		likeSelect = null;
		moreIndex1 = -1;
		moreIndex2 = -1;
		moreIndex3 = -1;
		moreIndex4 = -1;
		setNormalState();
	}

	public void setNormalState() {
		// 默认字体的颜色和图片
		tvQuYu.setTextColor(Color.parseColor("#919191"));
		tvZongJia.setTextColor(Color.parseColor("#919191"));
		tvFangXing.setTextColor(Color.parseColor("#919191"));
		tvGengDuo.setTextColor(Color.parseColor("#919191"));

		tvQuYu.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvZongJia.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvFangXing.setCompoundDrawables(null, null, rightDrawableNormal, null);
		tvGengDuo.setCompoundDrawables(null, null, rightDrawableNormal, null);

		tvQuYu.setText("区域");
		tvZongJia.setText("总价");
		tvFangXing.setText("房型");
		tvGengDuo.setText("更多");
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// params = new RequestParams();
		page = 1;
		// params.put("pageNum", page);
		// if (popIndex == 0) {
		// if (indexFlag == 1) {
		// params.put("businessListId", businessListId);
		// } else {
		// params.put("latitude", latitude);
		// params.put("longitude", longitude);
		// params.put("type", type);
		// }
		// } else if (popIndex == 1) {
		// params.put("totalPriceRange", totalPriceRange);
		// } else if (popIndex == 2) {
		// params.put("roomNum", roomNum);
		// } else if (popIndex == 3) {
		// params.put("areaHouseRange", areaHouseRange);
		// params.put("authId", authId);
		// params.put("specialtyId", specialtyId);
		// params.put("houseAgeRange", houseAgeRange);
		// } else if (popIndex == 4) {
		// params.put(paiXuKey, paiXu);
		// params.put("orderType", orderType);
		// } else if (popIndex == 5) {
		// params.put("likeSelect", likeSelect);
		// } else if (popIndex == 6) {
		// params.put("specialtyName", specialtyName);
		// }
		isRefresh = true;

		initData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// params = new RequestParams();
		page++;
		isRefresh = false;
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

	public class AuthResAdapter extends CommonAdapter<String> {

		public AuthResAdapter(Context context, List<String> mDatas, int layoutId) {
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

	public class TeSeAdapter extends CommonAdapter<String> {

		public TeSeAdapter(Context context, List<String> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
			// TODO Auto-generated constructor stub
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
