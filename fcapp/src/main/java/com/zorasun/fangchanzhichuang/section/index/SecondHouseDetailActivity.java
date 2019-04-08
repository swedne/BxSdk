package com.zorasun.fangchanzhichuang.section.index;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.LineCharView;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.account.LoginActivity;
import com.zorasun.fangchanzhichuang.section.dialog.DialogShare;
import com.zorasun.fangchanzhichuang.section.dialog.DialogShare.DialogShareCallBack;
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseInfoEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseInfoEntity.Content;
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseInfoEntity.ImageUrlList;
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseInfoEntity.SecondHouse;
import com.zorasun.fangchanzhichuang.section.index.entity.SecondHouseInfoEntity.SpeciltyNameList;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.SecondHouseQuotationListByAreaList;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.SecondHouseQuotationListByAreaList_null;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.XiamenSecondHouseQuotationListByMonth;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.XiamenSecondHouseQuotationListByMonth_null;
import com.zorasun.fangchanzhichuang.section.index.tools.ToolActivity;
import com.zorasun.fangchanzhichuang.section.my.FankuiYijianActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

public class SecondHouseDetailActivity extends BaseActivity implements OnClickListener {

    private ViewPager mViewPager;
    private TextView tvPage;
    private TextView tvTitle;
    private View renZhengView;
    private TextView tvSalePrice;
    private TextView tvRoomHallNum;
    private TextView tvDownPayment;
    private TextView tvAreaName;
    private TextView tvHouseType;
    private TextView tvIsExpert;
    private TextView tvBrokerName;
    private TextView tvHouseResDesc;
    private TextView tvUniqueNo;
    private TextView tvBuildTime;
    private TextView tvFloorNum;
    private TextView tvPropertyName;
    private TextView tvBusinessName;
    private TextView tvAreaListName;
    private TextView tvOrientationName;
    private TextView tvDecorate;
    private SecondHouse secondHouse;
    //	private MapView mMapView;
//	private BaiduMap map;
    private PopupWindow popupWindow;
    private int secondHouseId;
    private int isCollection;
    private TextView tvPrice;
    private TextView tvMonthPayment;
    private LinearLayout llHouseTag;
    private TextView tvBerryGG;
    private RequestParams params;
    private PagerAdapter pagerAdapter;
    protected List<ImageUrlList> imageList = new ArrayList<>();
    private int areaListId;
    private List<String> titleXList = new ArrayList<String>();
    private List<XiamenSecondHouseQuotationListByMonth> listByMonth = new ArrayList<>();
    private ArrayList<SecondHouseQuotationListByAreaList> listByArea = new ArrayList<>();
    private ArrayList<List<Float>> pointList = new ArrayList<List<Float>>();
    private LineCharView chartView;
    private ArrayList<Float> arrayPositionList = new ArrayList<Float>();
    private ArrayList<Float> modelPositionList = new ArrayList<Float>();
    private ArrayList<String> circleLabelList = new ArrayList<>();
    private ImageView imgBroker;
    private ScrollView mScrollView;
    private TextView tvCollect;
    private View view;
    private TextView tvName;
    private String shareImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_secondhousedetail);
        secondHouseId = getIntent().getIntExtra("secondHouseId", -1);
        areaListId = getIntent().getIntExtra("areaListId", -1);
        initView();
        initData();
        initPrice();
        initPositionList();
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
                        // 区域参考均价
                        if (secondHouseQuotationListByAreaList != null) {
                            listByArea.addAll(secondHouseQuotationListByAreaList);
                            if (secondHouseQuotationListByAreaList.size() == 6) {
                                for (int i = 0; i < listByArea.size(); i++) {
                                    arrayPositionList.add(listByArea.get(i).getAvgPrice() / 10000);
                                }
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
                                    }
                                    for (int i = 0; i < secondHouseQuotationListByAreaList_null.size(); i++) {
                                        arrayPositionList.add(
                                                secondHouseQuotationListByAreaList_null.get(i).getAvgPrice() / 10000);
                                    }
                                }
                            }
                        }
                        // 参考均价
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

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentTime() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    private void initData() {
        params = new RequestParams();
        params.put("secondHouseId", secondHouseId);
        if (!TextUtils.isEmpty(AccountConfig.getAccountId())) {
            params.put("accountId", AccountConfig.getAccountId());
        }
        IndexApi.getInstance().requestSecondHouseInfo(this, params, new RequestCallBack() {

            @Override
            public void onSuccess(int code, String msg, Object object) {
                SecondHouseInfoEntity secondHouseInfoEntity = (SecondHouseInfoEntity) object;
                Content content = secondHouseInfoEntity.getContent();
                secondHouse = content.getSecondHouse();
                isCollection = secondHouse.getIsCollection();
                if (isCollection == 0) {
                    tvCollect.setText("取消收藏");
                } else {
                    tvCollect.setText("收藏");
                }
                tvName.setText(secondHouse.getAreaListName());
                tvTitle.setText(secondHouse.getTitle());
                if (secondHouse.getIsAuth() == 1) {
                    renZhengView.setVisibility(View.VISIBLE);
                } else {
                    renZhengView.setVisibility(View.GONE);
                }
                Integer price = secondHouse.getPrice();
                if (price != null) {
                    tvPrice.setText(price + "元/㎡");
                } else {
                    tvPrice.setText("-");

                }
                if (!TextUtils.isEmpty(secondHouse.getMonthPayment())) {
                    tvMonthPayment.setText(secondHouse.getMonthPayment() + "元");
                } else {
                    tvMonthPayment.setText("-");

                }

                tvSalePrice.setText(secondHouse.getSalePrice() + "");
                Integer roomNum = secondHouse.getRoomNum();
                if (roomNum == null) {
                    roomNum = 0;
                }
                Integer hallNum = secondHouse.getHallNum();
                if (hallNum == null) {
                    hallNum = 0;
                }
                tvRoomHallNum.setText(roomNum + "室" + hallNum + "厅");
                Integer downPayment = secondHouse.getDownPayment();
                if (downPayment != null) {
                    tvDownPayment.setText(downPayment + "万");
                } else {
                    tvDownPayment.setText("-");
                }
                tvAreaName.setText(secondHouse.getAreaName());
                String berryGG = secondHouse.getBerryGG();
                if (!TextUtils.isEmpty(berryGG)) {
                    tvBerryGG.setText(berryGG + "㎡");
                } else {
                    tvBerryGG.setText("-");
                }
                tvHouseType.setText(secondHouse.getHouseTypeName());
//				if (secondHouse.getIsExpert() == 1) {
//					tvIsExpert.setVisibility(View.VISIBLE);
//				} else {
//					tvIsExpert.setVisibility(View.GONE);
//				}
                tvIsExpert.setText(secondHouse.getRealName());
                tvBrokerName.setText(secondHouse.getBrokerName());
                String headUrl = secondHouse.getHeadUrl();
                if (!TextUtils.isEmpty(headUrl)) {
                    AsyncImageLoader.setAsynImages(imgBroker, headUrl);
                }
                tvHouseResDesc.setText(secondHouse.getHouseResourceDesc());
                tvUniqueNo.setText(secondHouse.getHouseAuthCode());
                String buildTime = secondHouse.getBuildTime();
                if (!TextUtils.isEmpty(buildTime)) {
                    tvBuildTime.setText(buildTime + "年");
                } else {
                    tvBuildTime.setText("-");
                }
                // String floorNum = secondHouse.getFloorNum();
                // if (condition) {
                //
                // }
                // tvFloorNum.setText(floorNum);
                // } else {
                // tvFloorNum.setText(floorNum + "层");
                // }
                if (!TextUtils.isEmpty(secondHouse.getFloorNum())) {
                    tvFloorNum.setText(secondHouse.getFloorNum() + "层");
                } else {
                    tvFloorNum.setText("-");
                }
                tvPropertyName.setText(secondHouse.getPropertyName());
                tvBusinessName.setText(secondHouse.getBusinessName());
                tvAreaListName.setText(secondHouse.getAreaListName());
                tvOrientationName.setText(secondHouse.getOrientationName());
                tvDecorate.setText(secondHouse.getDecorateDegreeName());
                List<SpeciltyNameList> speciltyNameList = secondHouse.getSpeciltyNameList();
                if (speciltyNameList.size() > 0) {
                    for (int i = 0; i < speciltyNameList.size(); i++) {
                        if (speciltyNameList.size() > 4) {
                            return;
                        }
                        View child = getLayoutInflater().inflate(R.layout.item_text, null);
                        TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
                        String color = "#FD641D";
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
                        tvHouseTag.setText(speciltyNameList.get(i).getSpecialtyName());
                        llHouseTag.addView(child);
                    }
                } else {
                    View child = getLayoutInflater().inflate(R.layout.item_text, null);
                    TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
                    tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
                    tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
                    tvHouseTag.setText("暂无");
                    llHouseTag.addView(child);
                }
                if (secondHouse.getLatitude() != null && secondHouse.getLongitude() != null) {
//					initBaiDuMap(secondHouse.getLatitude(), secondHouse.getLongitude());
                }

                if (secondHouse.getImageUrlList() != null) {
                    if (secondHouse.getImageUrlList().size() > 0) {
                        imageList.addAll(secondHouse.getImageUrlList());
                        tvPage.setText(1 + "/" + imageList.size());
                        pagerAdapter.notifyDataSetChanged();
                    } else {
                        tvPage.setText(1 + "/" + 1);
                    }
                } else {
                    tvPage.setText(1 + "/" + 1);
                }
                mViewPager.setCurrentItem(imageList.size() * 5000 / 2);

                circleLabelList.clear();
                circleLabelList.add("参考均价");
                circleLabelList.add(secondHouse.getAreaName() + "参考均价");
                chartView.setCircleLabelList(circleLabelList);
            }

            @Override
            public void onNetworkError() {
                ToastUtil.toastShow(SecondHouseDetailActivity.this, R.string.net_error);
            }

            @Override
            public void onFailure(int code, String msg, Object object) {
                ToastUtil.toastShow(SecondHouseDetailActivity.this, R.string.net_error);

            }
        });
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
//		map.setMapStatus(mMapStatusUpdate);
//		UiSettings settings = map.getUiSettings();
//		settings.setAllGesturesEnabled(false); // 设置不能拖拽
//		showNearbyArea(cenpt);
//
//		mMapView.setOnTouchListener(new View.OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				if (event.getAction() == MotionEvent.ACTION_UP) {
//					mScrollView.requestDisallowInterceptTouchEvent(false);
//				} else {
//					mScrollView.requestDisallowInterceptTouchEvent(true);
//				}
//				return false;
//			}
//		});
//		// map.getUiSettings().setScrollGesturesEnabled(false);
//	}
//
//	public void showNearbyArea(LatLng center) {
//		BitmapDescriptor centerBitmap = BitmapDescriptorFactory.fromResource(R.drawable.weizhi);
//		MarkerOptions ooMarker = new MarkerOptions().position(center).icon(centerBitmap);
//		map.addOverlay(ooMarker);
//	}

    @SuppressLint("InflateParams")
    private void initView() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView1);
        tvName = (TextView) findViewById(R.id.title_name);
        tvName.setText("");
        chartView = (LineCharView) findViewById(R.id.lineCharView1);
        View imgMenu = findViewById(R.id.rl_sandian);
        imgMenu.setVisibility(View.VISIBLE);
        imgMenu.setOnClickListener(this);
//		mMapView = (MapView) findViewById(R.id.bmapView);
//		mMapView.showZoomControls(false);
//		map = mMapView.getMap();
        findViewById(R.id.rl_call).setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_Title);
        renZhengView = findViewById(R.id.is_auth);
        tvSalePrice = (TextView) findViewById(R.id.tv_saleprice);
        tvRoomHallNum = (TextView) findViewById(R.id.tv_room_hallnum);
        tvDownPayment = (TextView) findViewById(R.id.tv_downpayment);
        tvAreaName = (TextView) findViewById(R.id.tv_areaname);
        tvHouseType = (TextView) findViewById(R.id.tv_housetypename);
        tvDecorate = (TextView) findViewById(R.id.tv_decorateDegreename);
        tvOrientationName = (TextView) findViewById(R.id.tv_orientationname);
        tvAreaListName = (TextView) findViewById(R.id.tv_arealistname);
        tvBusinessName = (TextView) findViewById(R.id.tv_businessname);
        tvPropertyName = (TextView) findViewById(R.id.tv_propertyname);
        tvFloorNum = (TextView) findViewById(R.id.tv_floorNum);
        tvBuildTime = (TextView) findViewById(R.id.tv_buildtime);
        tvUniqueNo = (TextView) findViewById(R.id.tv_uniqueNo);
        tvHouseResDesc = (TextView) findViewById(R.id.tv_houseResDesc);
        tvBrokerName = (TextView) findViewById(R.id.tv_BrokerName);
        tvIsExpert = (TextView) findViewById(R.id.tv_isExpert);
        tvPrice = (TextView) findViewById(R.id.tv_unitprice);
        tvMonthPayment = (TextView) findViewById(R.id.tv_monthpay);
        llHouseTag = (LinearLayout) findViewById(R.id.ll_housetag);
        tvBerryGG = (TextView) findViewById(R.id.tv_berryGG);
        imgBroker = (ImageView) findViewById(R.id.img_secondhouse_broker);

        findViewById(R.id.img_second_jisuanqi).setOnClickListener(this);
        findViewById(R.id.rl_sendMSG).setOnClickListener(this);
        findViewById(R.id.img_secondhouse_broker).setOnClickListener(this);
        findViewById(R.id.rl_aroundmap).setOnClickListener(this);

        view = getLayoutInflater().inflate(R.layout.item_pop_more, null);
        popupWindow = PopupWindowUtil.getPopupWindow(this, view);
        tvCollect = (TextView) view.findViewById(R.id.tv_collect_more);

        findViewById(R.id.rl_communtiy).setOnClickListener(this);
        tvPage = (TextView) findViewById(R.id.tvpage);
        mViewPager = (ViewPager) findViewById(R.id.index_ViewPager);
        pagerAdapter = new PagerAdapter() {

            @Override
            public void destroyItem(View container, int position, Object object) {
                View view = (View) object;
                mViewPager.removeView(view);
            }

            @Override
            public Object instantiateItem(View container, int position) {
                View inflate = getLayoutInflater().inflate(R.layout.lifeyue, null);
                ImageView image1 = (ImageView) inflate.findViewById(R.id.imageView1);
                if (imageList.size() > 0) {
                    position = position % imageList.size();
                    final ImageUrlList imageUrlList = imageList.get(position);
                    final String picUrl = imageUrlList.getPicUrl();
                    final String surFaceUrl = imageUrlList.getSurFaceUrl();
                    if (!TextUtils.isEmpty(picUrl)) {
                        AsyncImageLoader.setAsynImages(image1, picUrl);
                    } else if (!TextUtils.isEmpty(surFaceUrl)) {
                        AsyncImageLoader.setAsynImages(image1, surFaceUrl);
                    }
                    image1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SecondHouseDetailActivity.this, ImageActivity.class);
                            if (!TextUtils.isEmpty(picUrl)) {
                                intent.putExtra("imageUrl", picUrl);
                            } else if (!TextUtils.isEmpty(surFaceUrl)) {
                                intent.putExtra("imageUrl", surFaceUrl);
                            }
                            // intent.putExtra("imageName",
                            // imageInfo.getFileName());
                            startActivity(intent);
                        }
                    });
                } else {
                    image1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SecondHouseDetailActivity.this, ImageActivity.class);
                            intent.putExtra("imageName", "暂无图片");
                            startActivity(intent);
                        }
                    });
                }
                mViewPager.addView(inflate);

                return inflate;

            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
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
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(6);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

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
        chartView.setyUnit("万/平");
        chartView.setNumberOfY(5);
    }

    public void sendSmsWithNumber(Context context, String number) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
        context.startActivity(sendIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_communtiy:
                Intent intent = new Intent(this, XiaoQuActivity.class);
                intent.putExtra("areaListId", secondHouse.getAreaListId());
                startActivity(intent);
                break;
            // 打电话
            case R.id.rl_call:
                showCallWindow();
                break;
            // 打开菜单栏
            case R.id.rl_sandian:
                showMenuWindow();
                break;
            // 发送短信
            case R.id.rl_sendMSG:
                sendSmsWithNumber(this, secondHouse.getMobile());
                break;
            case R.id.img_second_jisuanqi:
                startActivity(new Intent(this, ToolActivity.class));
                break;
            case R.id.img_secondhouse_broker:
                Intent intent2 = new Intent(this, JingjirenXqActivity.class);
                intent2.putExtra("brokerId", secondHouse.getBrokeId());
                startActivity(intent2);
                break;
            case R.id.rl_aroundmap:
//			Intent mapIntent = new Intent(this, CommunityMapActivity.class);
//			if (secondHouse.getLatitude() != null && secondHouse.getLongitude() != null) {
//				mapIntent.putExtra("latitude", secondHouse.getLatitude());
//				mapIntent.putExtra("longitude", secondHouse.getLongitude());
//			}
//			startActivity(mapIntent);
                break;
            default:
                break;
        }

    }

    private void showMenuWindow() {
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        if (isCollection == 0) {
            tvCollect.setText("取消收藏");
        } else {
            tvCollect.setText("收藏");
        }
        view.findViewById(R.id.textView1).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        view.findViewById(R.id.tv_collect_jubao).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (AccountConfig.isLogin()) {
                    intent = new Intent(SecondHouseDetailActivity.this, FankuiYijianActivity.class);
                    intent.putExtra("secondHouseReport", secondHouseId);
                } else {
                    intent = new Intent(SecondHouseDetailActivity.this, LoginActivity.class);
                }
                startActivity(intent);

            }
        });
        view.findViewById(R.id.tv_collect_more).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (AccountConfig.isLogin()) {
                    if (isCollection == 0) {
                        cancleCollectHouse();
                    } else {
                        collectHouse();
                    }
                } else {
                    startActivity(new Intent(SecondHouseDetailActivity.this, LoginActivity.class));
                    ToastUtil.toastShow(SecondHouseDetailActivity.this, "请先登录");
                }

            }
        });
//		// 分享
//		view.findViewById(R.id.tv_share_more).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				DialogShare dialog = new DialogShare();
//				dialog.showDialog(SecondHouseDetailActivity.this);
//				dialog.setCallBack(new DialogShareCallBack() {
//
//					public void handle(int type) {
//						if (imageList.size() > 0 && imageList != null) {
//							ImageUrlList imageUrlList = imageList.get(0);
//							String picUrl = imageUrlList.getPicUrl();
//							String surFaceUrl = imageUrlList.getSurFaceUrl();
//							if (!TextUtils.isEmpty(picUrl)) {
//								shareImage = ApiConfig.getImageUrl(picUrl);
//							} else if (!TextUtils.isEmpty(surFaceUrl)) {
//								shareImage = ApiConfig.getImageUrl(surFaceUrl);
//							}
//						}
//						if (type == 0) {// 微信的
//							// TODO
//							new UmengSocialShare(SecondHouseDetailActivity.this).shareWx(secondHouse.getTitle(),
//									secondHouse.getTitle(), secondHouse.getWxUrl(), shareImage, secondHouseId);
//						} else if (type == 1) {// 微信朋友圈的
//							new UmengSocialShare(SecondHouseDetailActivity.this).shareWxCircle(secondHouse.getTitle(),
//									secondHouse.getTitle(), secondHouse.getWxUrl(), shareImage, secondHouseId);
//						} else if (type == 2) {// qq
//							new UmengSocialShare(SecondHouseDetailActivity.this).shareQQ(secondHouse.getTitle(),
//									secondHouse.getTitle(), secondHouse.getWxUrl(), shareImage, secondHouseId);
//						}
    }
//				});
//			}
//		});
//		view.findViewById(R.id.tv_cancle).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				popupWindow.dismiss();
//			}
//		});
//	}

    protected void collectHouse() {
        IndexApi.getInstance().requestCollectHouse(this, secondHouseId, 0, new RequestCallBack() {

            @Override
            public void onSuccess(int code, String msg, Object object) {
                popupWindow.dismiss();
                isCollection = 0;
                ToastUtil.toastShow(SecondHouseDetailActivity.this, msg);
            }

            @Override
            public void onNetworkError() {

            }

            @Override
            public void onFailure(int code, String msg, Object object) {
                popupWindow.dismiss();
                ToastUtil.toastShow(SecondHouseDetailActivity.this, msg);
            }
        });
    }

    protected void cancleCollectHouse() {
        IndexApi.getInstance().requestCancleCollectHouse(SecondHouseDetailActivity.this, secondHouseId, 0,
                new RequestCallBack() {

                    @Override
                    public void onSuccess(int code, String msg, Object object) {
                        popupWindow.dismiss();
                        isCollection = 1;
                        ToastUtil.toastShow(SecondHouseDetailActivity.this, "取消收藏成功");
                    }

                    @Override
                    public void onNetworkError() {

                    }

                    @Override
                    public void onFailure(int code, String msg, Object object) {
                        ToastUtil.toastShow(SecondHouseDetailActivity.this, msg);
                        popupWindow.dismiss();
                    }
                });
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
                CommonUtils.call(SecondHouseDetailActivity.this, secondHouse.getMobile());
            }
        });
        view.findViewById(R.id.tv_cancle).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
