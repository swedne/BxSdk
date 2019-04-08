package com.zorasun.fangchanzhichuang.section.index;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.LineCharView;
import com.zorasun.fangchanzhichuang.section.index.entity.AreaListInfoEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.AreaListInfoEntity.AreaListInfoMap;
import com.zorasun.fangchanzhichuang.section.index.entity.AreaListInfoEntity.Content;
import com.zorasun.fangchanzhichuang.section.index.entity.AreaListInfoEntity.ImageList;
import com.zorasun.fangchanzhichuang.section.index.entity.AreaListInfoEntity.SpecialList;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.SecondHouseQuotationListByAreaList;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.SecondHouseQuotationListByAreaList_null;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.XiamenSecondHouseQuotationListByMonth;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.XiamenSecondHouseQuotationListByMonth_null;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class XiaoQuActivity extends BaseActivity implements OnClickListener {

	private TextView tvAreaListName;
	private TextView tvAveragePrice;
	private TextView tvAreaName;
	private TextView tvHouseType;
	private TextView tvMangeerCompany;
	private TextView tvManagerFee;
	private TextView tvParking;
	private TextView tvHouseNum;
	private TextView tvBusinessName;
	private TextView tvCreateYear;
	private TextView tvDevelpers;
	private TextView tvDiZhi;
//	private MapView mMapView;
//	private BaiduMap mBaidumap;
	private TextView tvRentHouse;
	private TextView tvSellhouseNum;
	private int areaListId;
	private ViewPager mViewPage;
	private TextView tvPage;
	private AreaListInfoMap areaListInfoMap;
	private TextView tvSecondHouse;
	private TextView tvZuFang;
	private View secondLine;
	private View rentLine;
	private TextView tvBuildNum;
	private LinearLayout llSpeical;
	private TextView tvBuildType;
	private List<ImageList> imageList = new ArrayList<>();
	private PagerAdapter pagerAdapter;
	private List<String> titleXList = new ArrayList<String>();
	private List<XiamenSecondHouseQuotationListByMonth> listByMonth = new ArrayList<>();
	private ArrayList<SecondHouseQuotationListByAreaList> listByArea = new ArrayList<>();
	private ArrayList<Float> modelPositionList = new ArrayList<>();
	private ArrayList<List<Float>> pointList = new ArrayList<List<Float>>();
	private ArrayList<Float> arrayPositionList = new ArrayList<Float>();
	private ArrayList<String> circleLabelList = new ArrayList<>();
	private View rlChartView;
	private View llSecondHouse;
	private View llRentHouse;
	private LineCharView chartView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_xiao_qu);

		initView();
		initData();
		initPositionList();
		initPrice();
	}

	private void initData() {
		areaListId = getIntent().getIntExtra("areaListId", -1);
		IndexApi.getInstance().requestArealistInfo(this, areaListId, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				AreaListInfoEntity areaListInfoEntity = (AreaListInfoEntity) object;
				Content content = areaListInfoEntity.getContent();
				areaListInfoMap = content.getAreaListInfoMap();
				tvAreaListName.setText(areaListInfoMap.getAreaListName());
				// Integer averagePrice = areaListInfoMap.getAveragePrice();
				if (areaListInfoMap.getAveragePrice() != null) {
					tvAveragePrice.setText(areaListInfoMap.getAveragePrice() + "元/㎡");
				} else {
					tvAveragePrice.setText(0 + "元/㎡");
				}
				tvAreaName.setText(areaListInfoMap.getAreaName());
				tvHouseType.setText(areaListInfoMap.getHouseType());
				tvMangeerCompany.setText(areaListInfoMap.getManagerCompany());
				Integer managerFee = areaListInfoMap.getManagerFee();
				if (managerFee != null) {
					tvManagerFee.setText(managerFee + "元/平米月");
				}
				if (!TextUtils.isEmpty(areaListInfoMap.getParking())) {
					tvParking.setText(areaListInfoMap.getParking());
				}
				if (areaListInfoMap.getHouseNums() != null) {
					tvHouseNum.setText(areaListInfoMap.getHouseNums() + "户");
				}
				tvBusinessName.setText(areaListInfoMap.getBusinessName());
				if (areaListInfoMap.getCreateYear() != null) {
					tvCreateYear.setText(areaListInfoMap.getCreateYear() + "年");
				}
				tvDevelpers.setText(areaListInfoMap.getDevelopers());
				if (areaListInfoMap.getBuildingNums() != null) {
					tvBuildNum.setText(areaListInfoMap.getBuildingNums() + "栋");
				}
				tvDiZhi.setText(areaListInfoMap.getAddress());
				tvRentHouse.setText("在租房源(" + areaListInfoMap.getHouseResource() + "套)");
				tvSellhouseNum.setText("在售二手房(" + areaListInfoMap.getOldSellingHouse() + "套)");
				tvBuildType.setText(areaListInfoMap.getBuilding());
				imageList.clear();
				if (areaListInfoMap.getImageList() != null) {
					if (areaListInfoMap.getImageList().size() > 0) {
						imageList.addAll(areaListInfoMap.getImageList());
						tvPage.setText(1 + "/" + imageList.size());
						pagerAdapter.notifyDataSetChanged();
					} else {
						tvPage.setText(1 + "/" + 1);
					}
				} else {
					tvPage.setText(1 + "/" + 1);
				}
				mViewPage.setCurrentItem(imageList.size() * 5000 / 2);
				if (areaListInfoMap.getAreaLatitude() != null && areaListInfoMap.getAreaLongitude() != null) {
//					initBaiDuMap(areaListInfoMap.getAreaLatitude(), areaListInfoMap.getAreaLongitude());
				}
				List<SpecialList> specialList = areaListInfoMap.getSpecialList();
				if (specialList != null) {
					if (specialList.size() > 0) {
						for (int i = 0; i < specialList.size(); i++) {
							if (specialList.size() > 4) {
								return;
							}
							View child = getLayoutInflater().inflate(R.layout.item_text, null);
							TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
							String color = "#4970E1";
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
							tvHouseTag.setText(specialList.get(i).getSpecialName());
							llSpeical.addView(child);
						}
					} else {
						View child = getLayoutInflater().inflate(R.layout.item_text, null);
						TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
						tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
						tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
						tvHouseTag.setText("暂无");
						llSpeical.addView(child);
					}
				} else {
					View child = getLayoutInflater().inflate(R.layout.item_text, null);
					TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
					tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
					tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
					tvHouseTag.setText("暂无");
					llSpeical.addView(child);
				}

				circleLabelList.clear();
				circleLabelList.add("参考均价");
				circleLabelList.add(areaListInfoMap.getAreaName() + "参考均价");
				chartView.setCircleLabelList(circleLabelList);
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(XiaoQuActivity.this, R.string.net_error);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(XiaoQuActivity.this, msg);

			}
		});
	}

	private void initPrice() {
		IndexApi.getInstance().requestCommunityWidePrice(this, 1, areaListId, getCurrentMonth(), getCurrentTime(),
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {
						WidePriceEntity widePriceEntity = (WidePriceEntity) object;
						arrayPositionList.clear();
						modelPositionList.clear();
						titleXList.clear();
						listByMonth.clear();
						listByArea.clear();
						ArrayList<SecondHouseQuotationListByAreaList> secondHouseQuotationListByAreaList = widePriceEntity
								.getContent().getSecondHouseQuotationListByAreaList();
						// 参考均价
						if (secondHouseQuotationListByAreaList != null) {
							listByArea.addAll(secondHouseQuotationListByAreaList);
							if (secondHouseQuotationListByAreaList.size() == 6) {
								for (int i = 0; i < listByArea.size(); i++) {
									arrayPositionList.add(listByArea.get(i).getAvgPrice() / 10000);
								}
								// for (int i = 0; i < listByArea.size(); i++) {
								// titleXList.add(String.valueOf(listByArea.get(i).getMonth())
								// + "月");
								// }
							} else {
								if (widePriceEntity.getContent().getSecondHouseQuotationListByAreaList_null() != null) {
									ArrayList<SecondHouseQuotationListByAreaList_null> secondHouseQuotationListByAreaList_null = widePriceEntity
											.getContent().getSecondHouseQuotationListByAreaList_null();
									for (int i = 0; i < secondHouseQuotationListByAreaList_null.size(); i++) {
										for (int j = 0; j < listByArea.size(); j++) {
											if (secondHouseQuotationListByAreaList_null.get(i).getMonth()
													.equals(listByArea.get(j).getMonth())) {
												secondHouseQuotationListByAreaList_null.get(i)
														.setAvgPrice(listByArea.get(j).getAvgPrice());

											}
										}
										// titleXList.add(String.valueOf(
										// secondHouseQuotationListByAreaList_null.get(i).getMonth())
										// + "月");
									}
									for (int i = 0; i < secondHouseQuotationListByAreaList_null.size(); i++) {
										arrayPositionList.add(
												secondHouseQuotationListByAreaList_null.get(i).getAvgPrice() / 10000);
									}
								}
							}
						}
						// 区域参考均价
						List<XiamenSecondHouseQuotationListByMonth> xiamenSecondHouseQuotationListByMonth = widePriceEntity
								.getContent().getXiamenSecondHouseQuotationMap()
								.getXiamenSecondHouseQuotationListByMonth();
						if (xiamenSecondHouseQuotationListByMonth != null) {
							listByMonth.addAll(xiamenSecondHouseQuotationListByMonth);
							if (listByMonth.size() == 6) {
								for (int i = 0; i < listByMonth.size(); i++) {
									modelPositionList.add(listByMonth.get(i).getAllAvgPrice() / 10000);
								}
								for (int i = 0; i < listByMonth.size(); i++) {
									titleXList.add(String.valueOf(listByMonth.get(i).getMonth()) + "月");
								}
							} else {
								if (widePriceEntity.getContent().getXiamenSecondHouseQuotationMap()
										.getXiamenSecondHouseQuotationListByMonth_null() != null) {
									List<XiamenSecondHouseQuotationListByMonth_null> xiamenSecondHouseQuotationListByMonth_null = widePriceEntity
											.getContent().getXiamenSecondHouseQuotationMap()
											.getXiamenSecondHouseQuotationListByMonth_null();
									for (int i = 0; i < xiamenSecondHouseQuotationListByMonth_null.size(); i++) {
										for (int j = 0; j < listByMonth.size(); j++) {
											if (xiamenSecondHouseQuotationListByMonth_null.get(i).getMonth()
													.equals(listByMonth.get(j).getMonth())) {
												xiamenSecondHouseQuotationListByMonth_null.get(i)
														.setAllAvgPrice(listByMonth.get(j).getAllAvgPrice());
											}
										}
										titleXList.add(String.valueOf(
												xiamenSecondHouseQuotationListByMonth_null.get(i).getMonth()) + "月");
									}
									for (int i = 0; i < xiamenSecondHouseQuotationListByMonth_null.size(); i++) {
										modelPositionList
												.add(xiamenSecondHouseQuotationListByMonth_null.get(i).getAllAvgPrice()
														/ 10000);
									}
								}
							}
						}

						// TODO
						pointList.clear();

						// if (listByMonth.size() > 0) {
						// for (int i = 0; i < listByMonth.size(); i++) {
						// titleXList.add(String.valueOf(listByMonth.get(i).getMonth())
						// + "月");
						// modelPositionList.add(listByMonth.get(i).getAllAvgPrice());
						// }
						// } else {
						// for (int i = 0; i < 6; i++) {
						// modelPositionList.add(0f);
						// }
						// }

						// 参考的模拟数据
						// modelPositionList.add(700f);
						// modelPositionList.add(500f);
						// modelPositionList.add(400f);
						// modelPositionList.add(350f);
						// modelPositionList.add(300f);
						// modelPositionList.add(550f);
						pointList.add(modelPositionList);

						// 区域的模拟数据
						// arrayPositionList.add(500f);
						// arrayPositionList.add(700f);
						// arrayPositionList.add(550f);
						// arrayPositionList.add(300f);
						// arrayPositionList.add(350f);
						// arrayPositionList.add(400f);

						pointList.add(arrayPositionList);
						chartView.setTitleXList(titleXList);
						chartView.setPointList(pointList);

					}

					@Override
					public void onNetworkError() {

					}

					@Override
					public void onFailure(int code, String msg, Object object) {

					}

				});
	}

	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		// 获得当前时间的月份，月份从0开始所以结果要加1
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static String getCurrentTime() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}

	private void initView() {
		chartView = (LineCharView) findViewById(R.id.lineCharView1);
//		mMapView = (MapView) findViewById(R.id.bmapView);
//		mBaidumap = mMapView.getMap();
//		mMapView.showZoomControls(false);
		findViewById(R.id.title_left).setOnClickListener(this);
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		tvTitle.setText("小区详情");
		findViewById(R.id.ly_shequZhuanjia).setOnClickListener(this);
		llSecondHouse = findViewById(R.id.ll_secondhouse);
		llSecondHouse.setOnClickListener(this);
		llRentHouse = findViewById(R.id.ll_rentHouse);
		llRentHouse.setVisibility(View.GONE);
		llRentHouse.setOnClickListener(this);
		findViewById(R.id.rl_ditu).setOnClickListener(this);
		rlChartView = findViewById(R.id.rl_lineCharView1);

		tvAreaListName = (TextView) findViewById(R.id.tv_areaListName);
		tvAveragePrice = (TextView) findViewById(R.id.tv_averagePrice);
		tvAreaName = (TextView) findViewById(R.id.tv_areaName);
		tvBuildNum = (TextView) findViewById(R.id.tv_buildingNums);
		tvHouseType = (TextView) findViewById(R.id.tv_housetype);
		tvMangeerCompany = (TextView) findViewById(R.id.tv_managerCompany);
		tvManagerFee = (TextView) findViewById(R.id.tv_managerFee);
		tvParking = (TextView) findViewById(R.id.tv_parking);
		tvHouseNum = (TextView) findViewById(R.id.tv_houseNums);
		tvBusinessName = (TextView) findViewById(R.id.tv_businessName);
		tvCreateYear = (TextView) findViewById(R.id.tv_createYear);
		tvDevelpers = (TextView) findViewById(R.id.tv_developers);
		tvDiZhi = (TextView) findViewById(R.id.tv_diZhi);
		tvRentHouse = (TextView) findViewById(R.id.tv_rentHouse);
		tvSellhouseNum = (TextView) findViewById(R.id.tv_sellhousenum);
		tvPage = (TextView) findViewById(R.id.tvpage);
		mViewPage = (ViewPager) findViewById(R.id.index_ViewPager);
		findViewById(R.id.ll_zufang).setOnClickListener(this);
		findViewById(R.id.ll_ershoufang).setOnClickListener(this);
		tvSecondHouse = (TextView) findViewById(R.id.tv_secondHouse);
		secondLine = findViewById(R.id.secondhouse_line);
		rentLine = findViewById(R.id.zufang_line);
		tvZuFang = (TextView) findViewById(R.id.tv_zuFang);
		tvBuildType = (TextView) findViewById(R.id.tv_buildtype);
		llSpeical = (LinearLayout) findViewById(R.id.ll_special);
		pagerAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(View container, int position, Object object) {
				View view = (View) object;
				mViewPage.removeView(view);
			}

			@Override
			public Object instantiateItem(View container, int position) {
				View inflate = getLayoutInflater().inflate(R.layout.lifeyue, null);
				ImageView image1 = (ImageView) inflate.findViewById(R.id.imageView1);
				if (imageList.size() > 0) {
					position = position % imageList.size();
					final ImageList imageInfo = imageList.get(position);
					final String url = imageInfo.getUrl();
					if (!TextUtils.isEmpty(url)) {
						AsyncImageLoader.setAsynImages(image1, url);
					}
					image1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(XiaoQuActivity.this, ImageActivity.class);
							intent.putExtra("imageUrl", url);
							intent.putExtra("imageName", imageInfo.getHouseTypeName());
							startActivity(intent);
						}
					});
				} else {
					image1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(XiaoQuActivity.this, ImageActivity.class);
							intent.putExtra("imageName", "暂无图片");
							startActivity(intent);
						}
					});
				}
				mViewPage.addView(inflate);

				return inflate;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				int num;
				if (imageList.size() > 0) {
					num = imageList.size() * 5000;
				} else {
					num = 1;
				}
				return num;
			}
		};
		mViewPage.setAdapter(pagerAdapter);
		mViewPage.setOffscreenPageLimit(6);
		mViewPage.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				if (imageList.size() > 0) {
					int index = arg0 % imageList.size();
					tvPage.setText(index + 1 + "/" + imageList.size());
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	private void initPositionList() {
		chartView = (LineCharView) findViewById(R.id.lineCharView1);
		chartView.setyUnit("万/平");
		chartView.setNumberOfY(5);
	}
//
//	private void initBaiDuMap(double Latitude, double Longitude) {
//
//		LatLng cenpt = new LatLng(Latitude, Longitude);
//		// 定义地图状态
//		MapStatus mMapStatus = new MapStatus.Builder().target(cenpt).zoom(16).build();
//		// 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
//		MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
//		// 改变地图状态
//		mBaidumap.setMapStatus(mMapStatusUpdate);
//		mBaidumap.getUiSettings().setScrollGesturesEnabled(false);
//		showNearbyArea(cenpt);
//	}
//
//	public void showNearbyArea(LatLng center) {
//		BitmapDescriptor centerBitmap = BitmapDescriptorFactory.fromResource(R.drawable.weizhi);
//		MarkerOptions ooMarker = new MarkerOptions().position(center).icon(centerBitmap);
//		mBaidumap.addOverlay(ooMarker);
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		// 在售二手房
		case R.id.ll_secondhouse:
			Intent sellIntent = new Intent(this, IndexSheQuCommonActivity.class);
			sellIntent.putExtra("shequindex", SystemConstant.SHEQU_SELLSECONDEHOUSE);
			sellIntent.putExtra("areaListId", areaListId);
			startActivity(sellIntent);
			break;
		// 在租房源
		case R.id.ll_rentHouse:
			Intent rentIntent = new Intent(this, IndexSheQuCommonActivity.class);
			rentIntent.putExtra("shequindex", SystemConstant.SHEQU_RENTHOUSE);
			rentIntent.putExtra("areaListId", areaListId);
			// rentIntent.putExtra("", value)
			startActivity(rentIntent);
			break;
		// 查看社区专家
		case R.id.ly_shequZhuanjia:
			Intent zhuanjiaIntent = new Intent(this, IndexSheQuCommonActivity.class);
			zhuanjiaIntent.putExtra("shequindex", SystemConstant.SHEQU_ZHUANJIA);
			zhuanjiaIntent.putExtra("areaListId", areaListId);
			startActivity(zhuanjiaIntent);
			break;
		// 周边地图
		case R.id.rl_ditu:
//			Intent intent = new Intent(this, CommunityMapActivity.class);
//			if (areaListInfoMap.getAreaLatitude() != null && areaListInfoMap.getAreaLongitude() != null) {
//				intent.putExtra("latitude", areaListInfoMap.getAreaLatitude());
//				intent.putExtra("longitude", areaListInfoMap.getAreaLongitude());
//			}
//			startActivity(intent);
			break;
		// 二手房
		case R.id.ll_ershoufang:
			tvSecondHouse.setTextColor(Color.parseColor("#FF2B2B"));
			secondLine.setVisibility(View.VISIBLE);
			tvZuFang.setTextColor(Color.parseColor("#666666"));
			rentLine.setVisibility(View.GONE);
			rlChartView.setVisibility(View.VISIBLE);
			llSecondHouse.setVisibility(View.VISIBLE);
			llRentHouse.setVisibility(View.GONE);
			break;
		// 租房
		case R.id.ll_zufang:
			tvSecondHouse.setTextColor(Color.parseColor("#666666"));
			secondLine.setVisibility(View.GONE);
			tvZuFang.setTextColor(Color.parseColor("#FF2B2B"));
			rentLine.setVisibility(View.VISIBLE);
			rlChartView.setVisibility(View.GONE);
			llSecondHouse.setVisibility(View.GONE);
			llRentHouse.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}

	}
}
