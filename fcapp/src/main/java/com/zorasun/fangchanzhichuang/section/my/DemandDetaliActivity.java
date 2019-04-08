package com.zorasun.fangchanzhichuang.section.my;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.commonadapter.Common2Adapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.CommonAdapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.ViewHolder;
import com.zorasun.fangchanzhichuang.general.dialog.GeneralDialog;
import com.zorasun.fangchanzhichuang.general.dialog.ProgressDialog;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollListView;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.index.ChangFangDetailActivity;
import com.zorasun.fangchanzhichuang.section.index.JingjirenXqActivity;
import com.zorasun.fangchanzhichuang.section.index.SecondHouseDetailActivity;
import com.zorasun.fangchanzhichuang.section.index.ShangPuDetailActivity;
import com.zorasun.fangchanzhichuang.section.index.XieZiLouActivity;
import com.zorasun.fangchanzhichuang.section.index.ZuFangxqActivity;
import com.zorasun.fangchanzhichuang.section.my.entiy.DemandDetailEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.DemandDetailEntity.Content;
import com.zorasun.fangchanzhichuang.section.my.entiy.DemandDetailEntity.HouseResDemandProList;
import com.zorasun.fangchanzhichuang.section.my.entiy.DemandDetailEntity.HouseResourceListDetail;
import com.zorasun.fangchanzhichuang.section.my.entiy.DemandDetailEntity.SpecialtyList;
import com.zorasun.fangchanzhichuang.section.senddemand.SendDemandApi;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;

public class DemandDetaliActivity extends BaseActivity implements OnClickListener {

	private TextView tvUniqueNum;
	private TextView tvDemandData;
	private TextView tvDemandType;
	private TextView tvState;
	private TextView tvHouseTypeName;
	private TextView tvSquareName;
	private TextView tvBusniess;
	private TextView tvSquare;
	private TextView tvPrice;
	private TextView tvUserName;
	private TextView tvMobile;
	private View includeView;
	private TextView tvBrokerName;
	private TextView tvDetailPhone;
	private TextView tvAddress;
	private TextView tvCompany;
	private TextView tvCommunity;
	private View isExpert;
	private View line;
	private View brokerLayout;
	private Content content;
	private View valuateView;
	private View titleQiangDan;
	private TextView tvTitleBrokerName;
	private TextView tvTitleBrokerPhone;
	private View titleDaiQiangDan;
	private List<HouseResDemandProList> houseResDemandProList = new ArrayList<>();
	private MyAdapter adapter;
	private View cancleView;
	private TextView tvCancleReason;
	private View rlBottom;
	private int demandId;
	private View modifyValue;
	private View cancleDemandView;
	private View rentAlreadyView;
	private TextView tvRentAlready;
	private View myhouseResView;
	private int advicesId;
	private ProgressDialog progressDialog;
	private String cancleResult;
	private String cancleType;
	private ImageView imgBroker;
	private NoScrollListView mLiseView;
	private DemandDetailEntity demandDetailEntity;
	private NoScrollListView myHouseListView;
	private List<HouseResourceListDetail> houseResourceListDetailList = new ArrayList<>();
	private MyHouseAdapter1 myAdapter;
	private View llArea;
	private View llKanFang;
	private TextView tvTitleInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demand_detali);

		advicesId = getIntent().getIntExtra("advicesId", -1);
		demandId = getIntent().getIntExtra("demandId", 0);
		initView();

	}

	@Override
	protected void onResume() {
		if (advicesId != -1) {
			initRecord();
		}
		initData();
		super.onResume();
	}

	private void initRecord() {
		SendDemandApi.getInstance().requestReadRecord(this, advicesId, AccountConfig.getAccountId(),
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {

					}

					@Override
					public void onNetworkError() {

					}

					@Override
					public void onFailure(int code, String msg, Object object) {

					}
				});
	}

	private void initData() {
		SendDemandApi.getInstance().requestDemandDetail(this, demandId, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {

				demandDetailEntity = (DemandDetailEntity) object;
				content = demandDetailEntity.getContent();
				tvUniqueNum.setText(content.getUniqueNum());
				tvDemandData.setText(convertTime1(content.getDemandDate()));
				tvDemandType.setText(content.getDemandTypeName());
				switch (content.getState()) {
				case 0:
					tvState.setText("待抢单");
					break;
				case 1:
					tvState.setText("已抢单");
					break;
				case 2:
					tvState.setText("取消需求");
					break;
				case 4:
					tvState.setText("进行中");
					break;
				case 5:
					tvState.setText("已结束");
					break;

				default:
					break;
				}
				tvHouseTypeName.setText(content.getHouseTypeName());
				tvSquareName.setText(content.getAreaName());
				tvBusniess.setText(content.getBusinessName());
				tvSquare.setText(content.getSquare());

				// 判断字符串为纯数字的
				if (!TextUtils.isEmpty(content.getPrice())) {

					String price = content.getPrice();
					boolean onlyNum = price.matches("[0-9]{1,}");
					if (onlyNum) {
						if (content.getDemandTypeName().equals("二手房出售")) {
							tvPrice.setText(price + "万");
						} else if (content.getDemandTypeName().equals("一手房求购")) {
							tvPrice.setText(price + "万");
						} else {
							content.getDemandTypeName().equals("出租");
							tvPrice.setText(price + "元/月");
						}
					} else {
						// 判断字符串是否含有数字，如果有直接显示没有不显示
						// Pattern p = Pattern.compile(".*\\d+.*");
						// Matcher m = p.matcher(price);
						// if (m.matches()) {
						tvPrice.setText(price);
						// }

					}
				}

				tvUserName.setText(content.getUserName());
				tvMobile.setText(content.getMobile());

				if (content.getState() == 0) {
					if (content.getDemandTypeName().equals("求租") || content.getDemandTypeName().equals("出租")) {
						llArea.setVisibility(View.GONE);
					} else {
						llArea.setVisibility(View.VISIBLE);
					}
					valuateView.setVisibility(View.GONE);
					titleQiangDan.setVisibility(View.GONE);
					if (content.getDemandTypeName().equals("出租")) {
						rentAlreadyView.setVisibility(View.VISIBLE);
					} else if (content.getDemandTypeName().equals("二手房出售")) {
						rentAlreadyView.setVisibility(View.VISIBLE);
						tvRentAlready.setText("已售出");
					}
					cancleDemandView.setVisibility(View.VISIBLE);

				} else if (content.getState() == 1) {
					if (content.getDemandTypeName().equals("求租") || content.getDemandTypeName().equals("出租")) {
						llArea.setVisibility(View.GONE);
					} else {
						llArea.setVisibility(View.VISIBLE);
					}
					titleDaiQiangDan.setVisibility(View.GONE);
					titleQiangDan.setVisibility(View.VISIBLE);
					tvTitleBrokerName.setText(content.getBrokerName());
					tvTitleBrokerPhone.setText(content.getBrokerMobile());
					if (content.getBrokerId() != 0) {
						includeView.setVisibility(View.VISIBLE);
						tvBrokerName.setText(content.getBrokerName());
						tvDetailPhone.setText(content.getBrokerMobile());
						String businessName = content.getBusinessName();
						tvAddress.setText(businessName);
						tvCompany.setText(content.getRealName());
						tvCommunity.setText(content.getHarkBackHouse());
						if (!TextUtils.isEmpty(content.getHeadUrl())) {
							AsyncImageLoader.setAsynImages(imgBroker, content.getHeadUrl());
						} else {
							imgBroker.setImageResource(R.drawable.wutu);
						}
						if (content.getIsExpert() != null) {
							if (content.getIsExpert() == 1) {
								isExpert.setVisibility(View.VISIBLE);
								line.setVisibility(View.VISIBLE);
							} else {
								isExpert.setVisibility(View.GONE);
								line.setVisibility(View.GONE);
							}
						}
					}
					if (content.getHouseResDemandProList() != null && content.getHouseResDemandProList().size() > 0) {
						houseResDemandProList.clear();
						houseResDemandProList.addAll(content.getHouseResDemandProList());
						adapter.notifyDataSetChanged();
					}
					if (content.getHouseResourceListDetail() != null) {
						houseResourceListDetailList.clear();
						houseResourceListDetailList.add(content.getHouseResourceListDetail());
						if (houseResourceListDetailList.size() > 0) {
							myhouseResView.setVisibility(View.VISIBLE);
							myAdapter.notifyDataSetChanged();
						} else {
							myhouseResView.setVisibility(View.GONE);
							myAdapter.notifyDataSetChanged();
						}
					}
					if (content.getDemandTypeName().equals("二手房出售")) {
						rentAlreadyView.setVisibility(View.VISIBLE);
						tvRentAlready.setText("已售出");
					} else if (content.getDemandTypeName().equals("出租")) {
						rentAlreadyView.setVisibility(View.VISIBLE);
						tvRentAlready.setText("已出租");
					}
					cancleDemandView.setVisibility(View.VISIBLE);
					if (content.getAppraiseInfo() != null) {
						modifyValue.setVisibility(View.VISIBLE);
						valuateView.setVisibility(View.GONE);
					} else {
						valuateView.setVisibility(View.VISIBLE);
						modifyValue.setVisibility(View.GONE);
					}
				} else if (content.getState() == 2) {
					if (content.getDemandTypeName().equals("求租") || content.getDemandTypeName().equals("出租")) {
						llArea.setVisibility(View.GONE);
					} else {
						llArea.setVisibility(View.VISIBLE);
					}
					cancleDemandView.setVisibility(View.GONE);
					titleDaiQiangDan.setVisibility(View.GONE);
					titleQiangDan.setVisibility(View.GONE);
					cancleView.setVisibility(View.VISIBLE);
					tvCancleReason.setText(content.getRemark());
					if (content.getBrokerId() != 0) {
						includeView.setVisibility(View.VISIBLE);
						tvBrokerName.setText(content.getBrokerName());
						tvDetailPhone.setText(content.getBrokerMobile());
						String businessName = content.getBusinessName();
						tvAddress.setText(businessName);
						tvCompany.setText(content.getRealName());
						tvCommunity.setText(content.getHarkBackHouse());
						tvRentAlready.setVisibility(View.GONE);
						if (content.getAppraiseInfo() != null) {
							modifyValue.setVisibility(View.VISIBLE);
							valuateView.setVisibility(View.GONE);
						} else {
							valuateView.setVisibility(View.VISIBLE);
							modifyValue.setVisibility(View.GONE);
						}
						houseResDemandProList.clear();
						houseResDemandProList.addAll(content.getHouseResDemandProList());
						adapter.notifyDataSetChanged();
						if (!TextUtils.isEmpty(content.getHeadUrl())) {
							AsyncImageLoader.setAsynImages(imgBroker, content.getHeadUrl());
						} else {
							imgBroker.setImageResource(R.drawable.wutu);
						}
						if (content.getIsExpert() == 1) {
							isExpert.setVisibility(View.VISIBLE);
							line.setVisibility(View.VISIBLE);
						} else {
							isExpert.setVisibility(View.GONE);
							line.setVisibility(View.GONE);
						}

					} else {
						includeView.setVisibility(View.GONE);
						modifyValue.setVisibility(View.GONE);
						rlBottom.setVisibility(View.GONE);
					}
					houseResDemandProList.clear();
					houseResDemandProList.addAll(content.getHouseResDemandProList());
					if (houseResDemandProList.size() > 0) {
						brokerLayout.setVisibility(View.GONE);
						includeView.setVisibility(View.VISIBLE);
						llKanFang.setVisibility(View.VISIBLE);
					}
					adapter.notifyDataSetChanged();

					// if (content.getDemandTypeName().equals("二手房出售")) {
					// myhouseResView.setVisibility(View.VISIBLE);
					//
					// } else if (content.getDemandTypeName().equals("出租")) {
					// myhouseResView.setVisibility(View.VISIBLE);
					// }
					if (content.getHouseResourceListDetail() != null) {
						houseResourceListDetailList.clear();
						houseResourceListDetailList.add(content.getHouseResourceListDetail());
						if (houseResourceListDetailList.size() > 0) {
							myhouseResView.setVisibility(View.VISIBLE);
							myAdapter.notifyDataSetChanged();
						} else {

							myhouseResView.setVisibility(View.GONE);
							myAdapter.notifyDataSetChanged();
						}
					}

				} else if (content.getState() == 4) {
					if (content.getDemandTypeName().equals("求租") || content.getDemandTypeName().equals("出租")) {
						llArea.setVisibility(View.GONE);
					} else {
						llArea.setVisibility(View.VISIBLE);
					}
					titleDaiQiangDan.setVisibility(View.GONE);
					titleQiangDan.setVisibility(View.VISIBLE);
					tvTitleInfo.setText("您好," + content.getDevelopers() + "工作人员为您服务,联系电话");
					tvBrokerName.setVisibility(View.GONE);
					tvTitleBrokerPhone.setText("" + content.getMobile());
					cancleDemandView.setVisibility(View.VISIBLE);
					includeView.setVisibility(View.VISIBLE);
					if (!TextUtils.isEmpty(content.getHeadUrl())) {
						AsyncImageLoader.setAsynImages(imgBroker, content.getHeadUrl());
					} else {
						imgBroker.setImageResource(R.drawable.wutu);
					}
					brokerLayout.setVisibility(View.GONE);
					houseResDemandProList.clear();
					houseResDemandProList.addAll(content.getHouseResDemandProList());
					adapter.notifyDataSetChanged();

				} else if (content.getState() == 5) {
					// titleDaiQiangDan.setVisibility(View.GONE);
					// titleQiangDan.setVisibility(View.VISIBLE);
					// tvTitleBrokerName.setText(content.getBrokerName());
					// tvTitleBrokerPhone.setText(content.getBrokerMobile());
					// includeView.setVisibility(View.VISIBLE);
					// brokerLayout.setVisibility(View.GONE);
					// valuateView.setVisibility(View.GONE);

				}
			}

			@Override
			public void onNetworkError() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				// TODO Auto-generated method stub

			}

		});

	}

	private String convertTime1(long mills) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(mills);
		return formatter.format(calendar.getTime());
	}

	private String convertTime(long mills) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(mills);
		return formatter.format(calendar.getTime());
	}

	private void initView() {
		findViewById(R.id.title_left).setOnClickListener(this);
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		tvTitle.setText("需求详情");
		tvUniqueNum = (TextView) findViewById(R.id.tv_demand_detail_uniqueNum);
		tvDemandData = (TextView) findViewById(R.id.tv_demand_detail_demanddata);
		tvDemandType = (TextView) findViewById(R.id.tv_demand_detail_demandtype);
		tvState = (TextView) findViewById(R.id.tv_demand_detail_state);
		tvHouseTypeName = (TextView) findViewById(R.id.tv_demand_detail_housetypename);
		tvSquareName = (TextView) findViewById(R.id.tv_demand_detail_squarename);
		tvBusniess = (TextView) findViewById(R.id.tv_demand_detail_business);
		tvSquare = (TextView) findViewById(R.id.tv_demand_detail_square);
		tvPrice = (TextView) findViewById(R.id.tv_demand_detail_price);
		tvUserName = (TextView) findViewById(R.id.tv_demand_detail_username);
		tvMobile = (TextView) findViewById(R.id.tv_demand_detail_phone);
		llArea = findViewById(R.id.ll_area);

		llKanFang = findViewById(R.id.ll_kanfang_record);
		rlBottom = findViewById(R.id.rl_bottom);
		tvTitleInfo = (TextView) findViewById(R.id.tv_titleInfo);
		// 不同需求状态的头部
		valuateView = findViewById(R.id.rl_demandevaluate);
		titleDaiQiangDan = findViewById(R.id.rl_daiqiangdan);
		titleQiangDan = findViewById(R.id.rl_yiqiangdan);
		tvTitleBrokerName = (TextView) findViewById(R.id.title_brokername);
		tvTitleBrokerPhone = (TextView) findViewById(R.id.titlePhone);
		cancleView = findViewById(R.id.rl_xuqiuquxiao);
		tvCancleReason = (TextView) findViewById(R.id.tv_canclereson);

		valuateView.setOnClickListener(this);
		cancleDemandView = findViewById(R.id.rl_demandcancle);
		cancleDemandView.setOnClickListener(this);
		rentAlreadyView = findViewById(R.id.rl_rentareldy);
		tvRentAlready = (TextView) findViewById(R.id.tv_rentalready);
		tvRentAlready.setOnClickListener(this);
		includeView = findViewById(R.id.demainddetialtwo);
		isExpert = findViewById(R.id.tv_view_demand_detail_leavel);
		imgBroker = (ImageView) findViewById(R.id.img_demandDetail_broker);
		line = findViewById(R.id.line);

		// 经纪人布局
		tvBrokerName = (TextView) findViewById(R.id.tv_view_demand_detail_name);
		tvDetailPhone = (TextView) findViewById(R.id.tv_view_demand_detail_phone);
		tvAddress = (TextView) findViewById(R.id.tv_view_demand_detail_address);
		tvCompany = (TextView) findViewById(R.id.tv_view_demand_detail_company);
		tvCommunity = (TextView) findViewById(R.id.tv_view_demand_detail_community);
		brokerLayout = findViewById(R.id.ll_broker);
		brokerLayout.setOnClickListener(this);
		modifyValue = findViewById(R.id.rl_modifyvalue);
		modifyValue.setOnClickListener(this);
		mLiseView = (NoScrollListView) findViewById(R.id.lv_view_demand_detail_lookrecode);
		adapter = new MyAdapter(this, houseResDemandProList, R.layout.list_item_kanfang);
		mLiseView.setAdapter(adapter);
		myhouseResView = findViewById(R.id.item_myhouseres);
		myHouseListView = (NoScrollListView) findViewById(R.id.lv_view_demand_detail_myhouse);
		// myAdapter = new MyHouseAdapter(this, houseResourceListDetailList,
		// R.layout.item_secondhouse);
		myAdapter = new MyHouseAdapter1();
		myHouseListView.setAdapter(myAdapter);
		myHouseListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				HouseResourceListDetail houseResourceInfo = houseResourceListDetailList.get(position);
				Intent intent = null;
				if (content.getDemandTypeName().equals("二手房出售")) {
					if (houseResourceInfo.getTypeName().equals("商铺")) {
						intent = new Intent(DemandDetaliActivity.this, ShangPuDetailActivity.class);
						intent.putExtra("id", houseResourceInfo.getId());
						intent.putExtra("selectTypeId", 1);
					} else if (houseResourceInfo.getTypeName().equals("住宅")) {
						intent = new Intent(DemandDetaliActivity.this, SecondHouseDetailActivity.class);
						intent.putExtra("secondHouseId", houseResourceInfo.getId());
						intent.putExtra("areaListId", houseResourceInfo.getAreaListId());
					} else if (houseResourceInfo.getTypeName().equals("写字楼")) {
						intent = new Intent(DemandDetaliActivity.this, XieZiLouActivity.class);
						intent.putExtra("id", houseResourceInfo.getId());
						intent.putExtra("selectTypeId", 5);
					} else if (houseResourceInfo.getTypeName().equals("厂房")) {
						intent = new Intent(DemandDetaliActivity.this, ChangFangDetailActivity.class);
						intent.putExtra("id", houseResourceInfo.getId());
						intent.putExtra("selectTypeId", 3);
					}
				} else if (content.getDemandTypeName().equals("出租")) {
					if (houseResourceInfo.getTypeName().equals("商铺")) {
						intent = new Intent(DemandDetaliActivity.this, ShangPuDetailActivity.class);
						intent.putExtra("id", houseResourceInfo.getId());
						intent.putExtra("selectTypeId", 0);
					} else if (houseResourceInfo.getTypeName().equals("住宅")) {
						intent = new Intent(DemandDetaliActivity.this, ZuFangxqActivity.class);
						intent.putExtra("secondHouseId", houseResourceInfo.getId());
					} else if (houseResourceInfo.getTypeName().equals("写字楼")) {
						intent = new Intent(DemandDetaliActivity.this, XieZiLouActivity.class);
						intent.putExtra("id", houseResourceInfo.getId());
						intent.putExtra("selectTypeId", 4);
					} else if (houseResourceInfo.getTypeName().equals("厂房")) {
						intent = new Intent(DemandDetaliActivity.this, ChangFangDetailActivity.class);
						intent.putExtra("id", houseResourceInfo.getId());
						intent.putExtra("selectTypeId", 2);
					}
				}
				startActivity(intent);
			}
		});
		findViewById(R.id.tv_myhouseres).setOnClickListener(this);

	}

	public class MyAdapter extends CommonAdapter<HouseResDemandProList> {

		public MyAdapter(Context context, List<HouseResDemandProList> mDatas, int layoutId) {
			super(context, mDatas, layoutId);

		}

		@Override
		public void convert(ViewHolder helper, HouseResDemandProList item, int position) {
			helper.setText(R.id.tv_content, item.getContent());
			helper.setText(R.id.tv_date, convertTime(item.getLookData()));
		}

	}

	class MyHouseAdapter1 extends BaseAdapter {

		@Override
		public int getCount() {
			return houseResourceListDetailList.size();
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
			HouseResourceListDetail houseInfo = houseResourceListDetailList.get(position);
			if (content.getDemandTypeName().equals("出租")) {
				if (houseInfo.getTypeName().equals("住宅")) {
					convertView = getLayoutInflater().inflate(R.layout.index_zufang_item, null);
					TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
					TextView tvAreaListName = (TextView) convertView.findViewById(R.id.tv_areaListName);
					TextView tvRentMoney = (TextView) convertView.findViewById(R.id.tv_rentMoney);
					LinearLayout llSpecial = (LinearLayout) convertView.findViewById(R.id.ll_special);
					tvTitle.setText(houseInfo.getTitle());
					tvAreaListName.setText(houseInfo.getAreaListName());
					if (houseInfo.getRentMoney() != null) {
						Double doublePrice = houseInfo.getRentMoney();
						String price = String.valueOf(doublePrice);
						price = price.replaceAll("0+?$", "");// 去掉多余的0
						price = price.replaceAll("[.]$", "");// 如最后一位是.则去掉
						tvRentMoney.setText(price);
					} else {
						tvRentMoney.setText(0 + "");
					}
					ImageView imgTitle = (ImageView) convertView.findViewById(R.id.img_newhousepic);
					if (!TextUtils.isEmpty(houseInfo.getSurFaceUrl())) {
						AsyncImageLoader.setAsynImages(imgTitle, houseInfo.getSurFaceUrl());
					}
					ArrayList<SpecialtyList> specialtyList = content.getSpecialtyList();
					String color = "";
					if (specialtyList != null) {
						if (specialtyList.size() > 0) {
							for (int i = 0; i < specialtyList.size(); i++) {
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
								tvHouseTag.setText(specialtyList.get(i).getSpecialtyName());
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
					} else {
						View child = getLayoutInflater().inflate(R.layout.item_text, null);
						TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
						tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
						tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
						tvHouseTag.setText("暂无");
						llSpecial.addView(child);
					}
				} else {
					convertView = getLayoutInflater().inflate(R.layout.item_shangyedichan, null);
					TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
					TextView tvAreaListName = (TextView) convertView.findViewById(R.id.tv_arealistName);
					TextView tvSalePrice = (TextView) convertView.findViewById(R.id.tv_salePrice);
					TextView tvBerry = (TextView) convertView.findViewById(R.id.tv_berryGG);
					TextView tvWan = (TextView) convertView.findViewById(R.id.text_wan);
					ImageView imgPic = (ImageView) convertView.findViewById(R.id.img_newhousepic);
					if (houseInfo.getSurFaceUrl() != null) {
						AsyncImageLoader.setAsynImages(imgPic, houseInfo.getSurFaceUrl());
					}

					tvTitle.setText(houseInfo.getTitle());
					tvAreaListName.setText(houseInfo.getAreaListName());
					if (houseInfo.getRentMoney() != null) {
						Double doublePrice = houseInfo.getRentMoney();
						String price = String.valueOf(doublePrice);
						price = price.replaceAll("0+?$", "");// 去掉多余的0
						price = price.replaceAll("[.]$", "");// 如最后一位是.则去掉
						tvSalePrice.setText(price);
					} else {
						tvSalePrice.setText(0 + "");
					}

					tvWan.setText("元/月");
					if (houseInfo.getTypeName().equals("厂房")) {
						if (houseInfo.getPlantAcreage() != null) {
							String berryGG = houseInfo.getPlantAcreage();
							berryGG = berryGG.replaceAll("0+?$", "");// 去掉多余的0
							berryGG = berryGG.replaceAll("[.]$", "");// 如最后一位是.则去掉
							tvBerry.setText(berryGG + "㎡");
						} else {
							tvBerry.setText(0 + "㎡");
							tvBerry.setText(houseInfo.getPlantAcreage() + "㎡");
						}
					} else {
						if (!TextUtils.isEmpty(houseInfo.getBerryGG())) {
							String berryGG = houseInfo.getBerryGG();
							berryGG = berryGG.replaceAll("0+?$", "");// 去掉多余的0
							berryGG = berryGG.replaceAll("[.]$", "");// 如最后一位是.则去掉
							tvBerry.setText(berryGG + "㎡");
						} else {
							tvBerry.setText(0 + "㎡");
						}
					}
				}
			} else {
				if (houseInfo.getTypeName().equals("住宅")) {
					convertView = getLayoutInflater().inflate(R.layout.item_secondhouse, null);
					convertView.findViewById(R.id.rl_secondhandhouse).setVisibility(View.GONE);
					TextView tvRoomHall = (TextView) convertView.findViewById(R.id.tv_room_hall);
					if (houseInfo.getRoomNum() != null && houseInfo.getHallNum() != null) {
						tvRoomHall.setText(houseInfo.getRoomNum() + "室" + houseInfo.getHallNum() + "厅");
					} else {
						tvRoomHall.setText(0 + "室" + 0 + "厅");
					}
					TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_collect_title01);
					tvTitle.setText(houseInfo.getTitle());
					TextView tvBerry = (TextView) convertView.findViewById(R.id.tv_berryGG);
					String berryGG = houseInfo.getBerryGG();
					if (!TextUtils.isEmpty(berryGG)) {
						berryGG = berryGG.replaceAll("0+?$", "");// 去掉多余的0
						berryGG = berryGG.replaceAll("[.]$", "");// 如最后一位是.则去掉
						tvBerry.setText(berryGG + "㎡");
					} else {
						tvBerry.setText(0 + "㎡");
					}
					TextView tvSalePrice = (TextView) convertView.findViewById(R.id.tv_saleprice);
					tvSalePrice.setText("" + houseInfo.getPrice());
					TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
					tvPrice.setText(houseInfo.getUnitPrice() + "元/平");
					TextView tvAreaListName = (TextView) convertView.findViewById(R.id.tv_collect_arealistname);
					tvAreaListName.setText(houseInfo.getAreaListName());
					View imgRenZheng = convertView.findViewById(R.id.img_renzheng_web);
					View imgWeiRenZheng = convertView.findViewById(R.id.img_weirenzheng_web);
					if (houseInfo.getIsAuth() == 0) {
						imgRenZheng.setVisibility(View.GONE);
						imgWeiRenZheng.setVisibility(View.VISIBLE);
					} else {
						imgWeiRenZheng.setVisibility(View.GONE);
						imgRenZheng.setVisibility(View.VISIBLE);
					}
					ImageView imgTitle = (ImageView) convertView.findViewById(R.id.img_newhousepic);
					if (!TextUtils.isEmpty(houseInfo.getSurFaceUrl())) {
						AsyncImageLoader.setAsynImages(imgTitle, houseInfo.getSurFaceUrl());
					}
					LinearLayout llSecondSpecialist = (LinearLayout) convertView
							.findViewById(R.id.ll_secondHouseSpecialist);
					if (content.getSpecialtyList() != null) {
						ArrayList<SpecialtyList> secondHouseSpecialtyList = content.getSpecialtyList();
						if (secondHouseSpecialtyList.size() > 0) {
							for (int i = 0; i < secondHouseSpecialtyList.size(); i++) {
								if (secondHouseSpecialtyList.size() > 4) {
									continue;
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
					} else {
						View child = getLayoutInflater().inflate(R.layout.item_text, null);
						TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
						tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
						tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
						tvHouseTag.setText("暂无");
						llSecondSpecialist.addView(child);
					}

				} else {
					convertView = getLayoutInflater().inflate(R.layout.item_shangyedichan, null);
					TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
					TextView tvAreaListName = (TextView) convertView.findViewById(R.id.tv_arealistName);
					TextView tvSalePrice = (TextView) convertView.findViewById(R.id.tv_salePrice);
					TextView tvBerry = (TextView) convertView.findViewById(R.id.tv_berryGG);
					TextView tvWan = (TextView) convertView.findViewById(R.id.text_wan);
					View imgRenZheng = convertView.findViewById(R.id.img_renzheng_web);
					View imgWeiRenZheng = convertView.findViewById(R.id.img_weirenzheng_web);
					if (houseInfo.getIsAuth() == 0) {
						imgRenZheng.setVisibility(View.GONE);
						imgWeiRenZheng.setVisibility(View.VISIBLE);
					} else {
						imgWeiRenZheng.setVisibility(View.GONE);
						imgRenZheng.setVisibility(View.VISIBLE);
					}
					ImageView imgPic = (ImageView) convertView.findViewById(R.id.img_newhousepic);
					if (houseInfo.getSurFaceUrl() != null) {
						AsyncImageLoader.setAsynImages(imgPic, houseInfo.getSurFaceUrl());
					}
					tvTitle.setText(houseInfo.getTitle());
					tvAreaListName.setText(houseInfo.getAreaListName());

					tvSalePrice.setText("" + houseInfo.getPrice());
					tvWan.setText("万");
					if (houseInfo.getTypeName().equals("厂房")) {
						if (houseInfo.getPlantAcreage() != null) {
							tvBerry.setText(houseInfo.getPlantAcreage() + "㎡");
						}
					} else {
						tvBerry.setText(houseInfo.getBerryGG() + "㎡");
					}

				}
			}

			return convertView;
		}

	}

	class MyHouseAdapter extends Common2Adapter<HouseResourceListDetail> {

		public MyHouseAdapter(Context context, List<HouseResourceListDetail> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(View helper, HouseResourceListDetail item, int position) {
			if (content.getDemandTypeName().equals("出租")) {
				helper.findViewById(R.id.rl_secondhandhouse02).setVisibility(View.GONE);
				ImageView imgMyHou = (ImageView) helper.findViewById(R.id.imageView1);
				TextView tvMyHouTitle = (TextView) helper.findViewById(R.id.tv_collect_title);
				TextView tvMyHouAddress = (TextView) helper.findViewById(R.id.tv_collect_address);
				TextView tvMyHouPrice = (TextView) helper.findViewById(R.id.tv_collect_price);
				TextView tvMyHouArea = (TextView) helper.findViewById(R.id.tv_area);

				if (!TextUtils.isEmpty(item.getSurFaceUrl())) {
					AsyncImageLoader.setAsynImages(imgMyHou, item.getSurFaceUrl());
				}
				tvMyHouTitle.setText(item.getTitle());
				tvMyHouAddress.setText(item.getAreaListName());

				if (item.getRentMoney() != null) {
					Double doublePrice = item.getRentMoney();
					String price = String.valueOf(doublePrice);
					price = price.replaceAll("0+?$", "");// 去掉多余的0
					price = price.replaceAll("[.]$", "");// 如最后一位是.则去掉
					tvMyHouPrice.setText(price + "");
				} else {
					tvMyHouPrice.setText(0 + "");
				}

				if (!TextUtils.isEmpty(item.getBerryGG())) {
					String berryGG = item.getBerryGG();
					berryGG = berryGG.replaceAll("0+?$", "");// 去掉多余的0
					berryGG = berryGG.replaceAll("[.]$", "");// 如最后一位是.则去掉
					tvMyHouArea.setText(berryGG + "㎡");
				} else {
					tvMyHouArea.setText(0 + "㎡");
				}

			} else {
				if (item.getTypeName().equals("住宅")) {
					helper.findViewById(R.id.rl_secondhandhouse).setVisibility(View.GONE);
					TextView tvRoomHall = (TextView) helper.findViewById(R.id.tv_room_hall);
					if (item.getRoomNum() != null && item.getHallNum() != null) {
						tvRoomHall.setText(item.getRoomNum() + "室" + item.getHallNum() + "厅");
					} else {
						tvRoomHall.setText(0 + "室" + 0 + "厅");
					}
					TextView tvTitle = (TextView) helper.findViewById(R.id.tv_collect_title01);
					tvTitle.setText(item.getTitle());
					TextView tvBerry = (TextView) helper.findViewById(R.id.tv_berryGG);

					if (!TextUtils.isEmpty(item.getBerryGG())) {
						String berryGG = item.getBerryGG();
						berryGG = berryGG.replaceAll("0+?$", "");// 去掉多余的0
						berryGG = berryGG.replaceAll("[.]$", "");// 如最后一位是.则去掉
						tvBerry.setText(berryGG + "㎡");
					} else {
						tvBerry.setText(0 + "㎡");
					}
					TextView tvSalePrice = (TextView) helper.findViewById(R.id.tv_saleprice);
					tvSalePrice.setText("" + item.getPrice());
					TextView tvPrice = (TextView) helper.findViewById(R.id.tv_price);
					if (item.getUnitPrice() != null) {
						Integer unitPrice = item.getUnitPrice();
						tvPrice.setText(unitPrice + "元/㎡");
					} else {
						tvPrice.setText(0 + "元/㎡");
					}
					TextView tvAreaListName = (TextView) helper.findViewById(R.id.tv_collect_arealistname);
					tvAreaListName.setText(item.getAreaListName());
					View imgRenZheng = helper.findViewById(R.id.img_renzheng_web);
					View imgWeiRenZheng = helper.findViewById(R.id.img_weirenzheng_web);
					if (item.getIsAuth() == 0) {
						imgRenZheng.setVisibility(View.GONE);
						imgWeiRenZheng.setVisibility(View.VISIBLE);
					} else {
						imgWeiRenZheng.setVisibility(View.GONE);
						imgRenZheng.setVisibility(View.VISIBLE);
					}
					ImageView imgTitle = (ImageView) helper.findViewById(R.id.img_newhousepic);
					if (!TextUtils.isEmpty(item.getPicUrl())) {
						AsyncImageLoader.setAsynImages(imgTitle, item.getPicUrl());
					}
					LinearLayout llSecondSpecialist = (LinearLayout) helper.findViewById(R.id.ll_secondHouseSpecialist);
					ArrayList<SpecialtyList> specialtyList = content.getSpecialtyList();
					if (specialtyList != null) {
						if (specialtyList.size() > 0) {
							for (int i = 0; i < specialtyList.size(); i++) {
								if (specialtyList.size() > 4) {
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
								tvHouseTag.setText(specialtyList.get(i).getSpecialtyName());
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
				} else {

				}
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 需求评价
		case R.id.rl_demandevaluate:
			Intent commentIntent = new Intent(this, CommentActivity.class);
			commentIntent.putExtra("content", demandDetailEntity);
			commentIntent.putExtra("flag", 1);
			startActivity(commentIntent);
			break;
		// 取消需求
		case R.id.rl_demandcancle:
			Intent cancleDemandintent = new Intent(this, CancleDemandActivity.class);
			cancleDemandintent.putExtra("demandId", demandId);
			startActivity(cancleDemandintent);
			break;
		// 经纪人信息
		case R.id.ll_broker:
			Intent intent = new Intent(this, JingjirenXqActivity.class);
			intent.putExtra("brokerId", content.getBrokerId());
			startActivity(intent);
			break;
		// 我的房源信息
		case R.id.tv_myhouseres:
			break;
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		// 已出租
		case R.id.tv_rentalready:
			if (content.getDemandTypeName().equals("出租")) {
				cancleResult = "房源已出租";
				cancleType = "您确定房源已出租了?";
			} else if (content.getDemandTypeName().equals("二手房出售")) {
				cancleResult = "房源已售出";
				cancleType = "您确定房源已售出了?";
			}
			setCancleType();
			break;
		// 修改评价
		case R.id.rl_modifyvalue:
			Intent commentIntent1 = new Intent(this, CommentActivity.class);
			commentIntent1.putExtra("content", demandDetailEntity);
			commentIntent1.putExtra("flag", 2);
			startActivity(commentIntent1);
			break;
		default:
			break;
		}
	}

	private void setCancleType() {
		progressDialog = new ProgressDialog();
		final GeneralDialog dd = new GeneralDialog();
		dd.showDialog(this, cancleType);
		dd.sure(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dd.dismiss();
				progressDialog.createLoadingDialog(DemandDetaliActivity.this);
				cancleDemand();
			}
		});
	}

	private void cancleDemand() {
		MyApi.getInstance().requestCancleDemand(DemandDetaliActivity.this, demandId, cancleResult,
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {
						ToastUtil.toastShow(DemandDetaliActivity.this, msg);
					}

					@Override
					public void onNetworkError() {

						ToastUtil.toastShow(DemandDetaliActivity.this, R.string.net_error);
					}

					@Override
					public void onFailure(int code, String msg, Object object) {
						ToastUtil.toastShow(DemandDetaliActivity.this, msg);
						progressDialog.dismissDialog();
						initData();
					}
				});
	}

}
