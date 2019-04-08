package com.zorasun.fangchanzhichuang.section.index;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.FlowLayout;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.account.LoginActivity;
import com.zorasun.fangchanzhichuang.section.dialog.DialogShare;
import com.zorasun.fangchanzhichuang.section.dialog.DialogShare.DialogShareCallBack;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseEntity.AssortFacility;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseEntity.Content;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseEntity.ImageUrlList;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseEntity.RentHouse;
import com.zorasun.fangchanzhichuang.section.index.entity.RentHouseEntity.SpecityNameList;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ZuFangxqActivity extends BaseActivity implements OnClickListener {

	private ViewPager mViewPage;
	private TextView tvTitle;
	private TextView tvCreateTime;
	private TextView tvUpdateTime;
	private TextView tvRental;
	private TextView tvPayTypeName;
	private TextView tvAreaName;
	private TextView tvBusinessName;
	private TextView tvHousetypeName;
	private TextView tvDecorateDegreeName;
	private TextView tvFloorNum;
	private TextView tvBerryGG;
	private TextView tvOrientationName;
	private TextView tvAreaListName;
	private TextView tvUniqueNo;
	private TextView tvHouseRensourceDesc;
	private Content content;
	private TextView tvBrokerName;
	private TextView tvIsExpert;
	// private NoScrollGridView gridView;
	private LinearLayout llHouseTag;
	private List<AssortFacility> assortFacilityList = new ArrayList<>();
	// private TextView tvEstatePrice;
	private TextView tvHuXing;
	private PopupWindow popupWindow;
	private RentHouse rentHouse;
	private int isCollection;
	private int rentHouseId;
	private FlowLayout flowLayout;
	private PagerAdapter pagerAdapter;
	private List<ImageUrlList> imageList = new ArrayList<>();
	private TextView tvPage;
	private View view;
	private TextView tvCollect;
	private ImageView imgBroker;
	private TextView tvTitleName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zufangxq);
		initView();
		initData();
	}

	private void initData() {
		rentHouseId = getIntent().getIntExtra("rentHouseId", 0);
		IndexApi.getInstance().requestRentHouseInfo(this, rentHouseId, AccountConfig.getAccountId(),
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {
						RentHouseEntity rentHouseEntity = (RentHouseEntity) object;
						content = rentHouseEntity.getContent();
						isCollection = content.getRentHouse().getIsCollection();
						if (isCollection == 0) {
							tvCollect.setText("取消收藏");
						} else {
							tvCollect.setText("收藏");
						}
						assortFacilityList.addAll(content.getRentHouse().getAssortFacility());
						flowLayout.removeAllViews();
						if (assortFacilityList.size() > 0) {
							for (int i = 0; i < assortFacilityList.size(); i++) {
								View view = getLayoutInflater().inflate(R.layout.item_grid_text, null);
								TextView textView = (TextView) view.findViewById(R.id.textView1);
								textView.setText(assortFacilityList.get(i).getFacilityName());
								MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(
										ViewGroup.MarginLayoutParams.WRAP_CONTENT,
										ViewGroup.MarginLayoutParams.WRAP_CONTENT);
								layoutParams.rightMargin = 10;
								flowLayout.addView(view, layoutParams);
							}
						} else {
							View view = getLayoutInflater().inflate(R.layout.item_grid_text, null);
							TextView textView = (TextView) view.findViewById(R.id.textView1);
							textView.setText("暂无");
							MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(
									ViewGroup.MarginLayoutParams.WRAP_CONTENT,
									ViewGroup.MarginLayoutParams.WRAP_CONTENT);
							layoutParams.rightMargin = 10;
							flowLayout.addView(view, layoutParams);
						}

						setData();
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

	private String convertTime(long mills) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(mills);
		return formatter.format(calendar.getTime());
	}

	private void setData() {
		rentHouse = content.getRentHouse();
		if (rentHouse != null) {
			tvTitle.setText(rentHouse.getTitle());
			tvCreateTime.setText(convertTime(rentHouse.getCreateTime()));
			tvUpdateTime.setText(convertTime(rentHouse.getUpdateTime()));
			Double rental = rentHouse.getRental();
			tvRental.setText(String.valueOf(rental) + "元/月");
			tvPayTypeName.setText(rentHouse.getPayTypeName());
			tvAreaName.setText(rentHouse.getAreaName());
			tvBusinessName.setText(rentHouse.getBusinessName());
			tvHousetypeName.setText(rentHouse.getHouseTypeName());
			tvDecorateDegreeName.setText(rentHouse.getDecorateDegreeName());
			if (rentHouse.getRoomNum() != null) {
				tvHuXing.setText(rentHouse.getRoomNum() + "室" + rentHouse.getHallNum() + "厅");
			} else {
				tvHuXing.setText(0 + "室" + 0 + "厅");
			}
			if (rentHouse.getFloorNum() != null) {
				tvFloorNum.setText(rentHouse.getFloorNum() + "层");
			}
			if (!TextUtils.isEmpty(rentHouse.getBerryGG())) {
				tvBerryGG.setText(rentHouse.getBerryGG() + "m²");
			} else {
				tvBerryGG.setText("-");
			}
			tvOrientationName.setText(rentHouse.getOrientationName());
			// tvEstatePrice.setText(rentHouse.getEstatePrice() + "元/月");
			tvTitleName.setText(rentHouse.getAreaListName());
			tvAreaListName.setText(rentHouse.getAreaListName());
			tvUniqueNo.setText(rentHouse.getUniqueNo());
			tvHouseRensourceDesc.setText(rentHouse.getHouseResourceDesc());
			tvBrokerName.setText(rentHouse.getBrokerName());
			if (!TextUtils.isEmpty(rentHouse.getHeadUrl())) {
				AsyncImageLoader.setAsynImages(imgBroker, rentHouse.getHeadUrl());
			}
			// if (rentHouse.getIsExpert() == 0) {
			// tvIsExpert.setVisibility(View.GONE);
			// } else {
			// tvAreaListName.setVisibility(View.VISIBLE);
			// }
			tvIsExpert.setText(rentHouse.realName);

			List<SpecityNameList> speciltyNameList = rentHouse.getSpeciltyNameList();
			String color = "#FD641D";
			if (speciltyNameList.size() > 0) {
				for (int i = 0; i < speciltyNameList.size(); i++) {
					View view = getLayoutInflater().inflate(R.layout.item_text, null);
					TextView tvHouseTag = (TextView) view.findViewById(R.id.tv_housetag01);
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
					llHouseTag.addView(view);
				}
			} else {
				View view = getLayoutInflater().inflate(R.layout.item_text, null);
				TextView tvHouseTag = (TextView) view.findViewById(R.id.tv_housetag01);
				tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
				tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
				tvHouseTag.setText("暂无");
				llHouseTag.addView(view);
			}

			imageList.clear();
			if (rentHouse.getImageUrlList() != null) {
				if (rentHouse.getImageUrlList().size() > 0) {
					imageList.addAll(rentHouse.getImageUrlList());
					tvPage.setText(1 + "/" + imageList.size());
					pagerAdapter.notifyDataSetChanged();
				} else {
					tvPage.setText(1 + "/" + 1);
				}
			} else {
				tvPage.setText(1 + "/" + 1);
			}
			mViewPage.setCurrentItem(imageList.size() * 5000 / 2);
		}
	}

	private void initView() {
		View imgMenu = findViewById(R.id.rl_sandian);
		imgMenu.setVisibility(View.VISIBLE);
		imgMenu.setOnClickListener(this);
		findViewById(R.id.rl_sendMSG).setOnClickListener(this);
		tvTitleName = (TextView) findViewById(R.id.title_name);
		tvTitleName.setText("");
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvCreateTime = (TextView) findViewById(R.id.tv_createTime);
		tvUpdateTime = (TextView) findViewById(R.id.tv_updateTime);
		tvRental = (TextView) findViewById(R.id.tv_rental);
		tvPayTypeName = (TextView) findViewById(R.id.tv_payTypeName);
		tvAreaName = (TextView) findViewById(R.id.tv_areaName);
		tvBusinessName = (TextView) findViewById(R.id.tv_businessName);
		tvHousetypeName = (TextView) findViewById(R.id.tv_housetypename);
		tvDecorateDegreeName = (TextView) findViewById(R.id.tv_decorateDegreeName);
		tvFloorNum = (TextView) findViewById(R.id.tv_floorNum);
		tvBerryGG = (TextView) findViewById(R.id.tv_berryGG);
		tvOrientationName = (TextView) findViewById(R.id.tv_orientationName);
		tvAreaListName = (TextView) findViewById(R.id.tv_areaListName);
		tvUniqueNo = (TextView) findViewById(R.id.tv_uniqueNo);
		tvHouseRensourceDesc = (TextView) findViewById(R.id.tv_houseResDesc);
		// tvEstatePrice = (TextView) findViewById(R.id.tv_estatePrice);
		tvBrokerName = (TextView) findViewById(R.id.tv_brokerName);
		imgBroker = (ImageView) findViewById(R.id.img_rent_broker);
		tvIsExpert = (TextView) findViewById(R.id.tv_isExpert);
		llHouseTag = (LinearLayout) findViewById(R.id.ll_housetag);
		tvHuXing = (TextView) findViewById(R.id.tv_huxing);
		flowLayout = (FlowLayout) findViewById(R.id.gv_peitao);
		// gridView.setAdapter(adapter);
		findViewById(R.id.rl_call).setOnClickListener(this);
		findViewById(R.id.rl_communtiy).setOnClickListener(this);
		findViewById(R.id.img_rent_broker).setOnClickListener(this);
		// 弹窗菜单
		view = getLayoutInflater().inflate(R.layout.item_pop_more, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		tvCollect = (TextView) view.findViewById(R.id.tv_collect_more);

		tvPage = (TextView) findViewById(R.id.tvpage);
		mViewPage = (ViewPager) findViewById(R.id.index_ViewPager);
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
					final ImageUrlList imageUrlList = imageList.get(position);
					final String picUrl = imageUrlList.getPicUrl();
					if (!TextUtils.isEmpty(picUrl)) {
						AsyncImageLoader.setAsynImages(image1, picUrl);
					}
					image1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(ZuFangxqActivity.this, ImageActivity.class);
							intent.putExtra("imageUrl", picUrl);
							startActivity(intent);
						}
					});
				} else {
					image1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(ZuFangxqActivity.this, ImageActivity.class);
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
				CommonUtils.call(ZuFangxqActivity.this, rentHouse.getMobile());

			}
		});
		view.findViewById(R.id.tv_cancle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
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
		view.findViewById(R.id.tv_collect_jubao).setVisibility(View.GONE);
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
					startActivity(new Intent(ZuFangxqActivity.this, LoginActivity.class));
					ToastUtil.toastShow(ZuFangxqActivity.this, "请先登录");
				}

			}
		});
//		view.findViewById(R.id.tv_share_more).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				DialogShare dialog = new DialogShare();
//				dialog.showDialog(ZuFangxqActivity.this);
//				dialog.setCallBack(new DialogShareCallBack() {
//
//					private String shareImage;
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
//							new UmengSocialShare(ZuFangxqActivity.this).shareWx(rentHouse.getTitle(),
//									rentHouse.getTitle(), rentHouse.getWxUrl(), shareImage, rentHouseId);
//						} else if (type == 1) {// 微信朋友圈的
//							new UmengSocialShare(ZuFangxqActivity.this).shareWxCircle(rentHouse.getTitle(),
//									rentHouse.getTitle(), rentHouse.getWxUrl(), shareImage, rentHouseId);
//						} else if (type == 2) {// qq
//							new UmengSocialShare(ZuFangxqActivity.this).shareQQ(rentHouse.getTitle(),
//									rentHouse.getTitle(), rentHouse.getWxUrl(), shareImage, rentHouseId);
//						}
//					}
//				});
//			}
//		});
		view.findViewById(R.id.tv_cancle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
	}

	protected void collectHouse() {
		IndexApi.getInstance().requestCollectHouse(this, rentHouseId, 0, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				popupWindow.dismiss();
				isCollection = 0;
				ToastUtil.toastShow(ZuFangxqActivity.this, msg);
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				popupWindow.dismiss();
				ToastUtil.toastShow(ZuFangxqActivity.this, msg);
			}
		});
	}

	protected void cancleCollectHouse() {
		IndexApi.getInstance().requestCancleCollectHouse(ZuFangxqActivity.this, rentHouseId, 0, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				popupWindow.dismiss();
				isCollection = 1;
				ToastUtil.toastShow(ZuFangxqActivity.this, "取消收藏成功");
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(ZuFangxqActivity.this, msg);
				popupWindow.dismiss();
			}
		});
	}

	public void sendSmsWithNumber(Context context, String number) {
		Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
		context.startActivity(sendIntent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_call:
			showCallWindow();
			break;
		case R.id.rl_sendMSG:
			sendSmsWithNumber(this, rentHouse.getMobile());
			break;
		case R.id.rl_communtiy:
			Intent intent = new Intent(this, XiaoQuActivity.class);
			intent.putExtra("areaListId", rentHouse.getAreaListId());
			startActivity(intent);
			break;
		case R.id.rl_sandian:
			showMenuWindow();
			break;
		case R.id.img_rent_broker:
			Intent intent2 = new Intent(this, JingjirenXqActivity.class);
			intent2.putExtra("brokerId", rentHouse.getBrokeId());
			startActivity(intent2);
			break;
		default:
			break;
		}

	}
}
