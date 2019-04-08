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
import com.zorasun.fangchanzhichuang.section.index.entity.ShangYeDiChanDetailEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.ShangYeDiChanDetailEntity.AssortFacilityList;
import com.zorasun.fangchanzhichuang.section.index.entity.ShangYeDiChanDetailEntity.BusinessEstate;
import com.zorasun.fangchanzhichuang.section.index.entity.ShangYeDiChanDetailEntity.Content;
import com.zorasun.fangchanzhichuang.section.index.entity.ShangYeDiChanDetailEntity.ImageUrlList;
import com.zorasun.fangchanzhichuang.section.my.FankuiYijianActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.widget.PopupWindow;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class ShangPuDetailActivity extends BaseActivity implements OnClickListener {

	private TextView tvTitle;
	private TextView tvCreateTime;
	private TextView tvUpdateTime;
	private TextView tvRental;
	private TextView tvAreaName;
	private TextView tvBusinessName;
	private TextView tvHousetypeName;
	private TextView tvDecorateDegreeName;
	private TextView tvFloorNum;
	private TextView tvBerryGG;
	private TextView tvAreaListName;
	private TextView tvUniqueNo;
	private TextView tvHouseRensourceDesc;
	private Content content;
	private TextView tvBrokerName;
	private TextView tvIsExpert;
	private TextView tvEstatePrice;
	private int selectTypeId = -1;
	private int id;
	private TextView tvBuildTime;
	private TextView tvRenta;
	private BusinessEstate businessEstate;
	private PopupWindow popupWindow;
	private TextView tvPeiTao;
	private TextView tvDecorate;
	private TextView tvPayTypeName;
	private List<AssortFacilityList> assortFacilityList = new ArrayList<>();
	private FlowLayout flowLayout;
	private int isCollection;
	private TextView tvCollect;
	private View view;
	private List<ImageUrlList> imageList = new ArrayList<>();
	private TextView tvPage;
	private ViewPager mViewPage;
	private PagerAdapter pagerAdapter;
	private View sellDecorate;
	private ImageView imgBroker;
	private TextView tvTitleName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shang_ye_di_chan_info);
		selectTypeId = getIntent().getIntExtra("selectTypeId", 0);
		id = getIntent().getIntExtra("id", -1);
		initView();
		initData();
	}

	private void initData() {

		IndexApi.getInstance().requestShangYeDiChanDetail(this, AccountConfig.getAccountId(), selectTypeId, id,
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {
						ShangYeDiChanDetailEntity shangYeDiChanDetailEntity = (ShangYeDiChanDetailEntity) object;
						content = shangYeDiChanDetailEntity.getContent();
						// assortFacilityList.addAll(content.getAssortFacility());
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
		businessEstate = content.getBusinessEstate();
		if (businessEstate != null) {
			isCollection = businessEstate.getIsCollection();
			if (isCollection == 0) {
				tvCollect.setText("取消收藏");
			} else {
				tvCollect.setText("收藏");
			}
			tvTitle.setText(businessEstate.getTitle());
			tvCreateTime.setText(convertTime(businessEstate.getCreatedTime()));
			tvUpdateTime.setText(convertTime(businessEstate.getUpdateTime()));
			if (selectTypeId == 1) {
				tvRental.setText(businessEstate.getSalePrice() + "万/套");
				String buildTime = businessEstate.getBuildTime();
				if (!TextUtils.isEmpty(buildTime)) {
					tvBuildTime.setText(buildTime + "年");
				} else {
					tvBuildTime.setText("-");
				}
			} else {
				if (businessEstate.getRental() != null) {
					tvRental.setText(businessEstate.getRental() + "元/月");
				} else {
					tvRental.setText("-");
				}
			}
			tvTitleName.setText(businessEstate.getAreaListName());
			tvPayTypeName.setText(businessEstate.getPayTypeName());
			tvAreaName.setText(businessEstate.getAreaName());
			tvBusinessName.setText(businessEstate.getBusinessListName());
			tvHousetypeName.setText(businessEstate.getShopTypeName());
			tvDecorateDegreeName.setText(businessEstate.getDecorateDegreeName());
			if (TextUtils.isEmpty(businessEstate.getFloorNum()) || TextUtils.isEmpty(businessEstate.getFloorSum())) {
				tvFloorNum.setText("-");
			} else {
				tvFloorNum.setText(businessEstate.getFloorNum() + "/" + businessEstate.getFloorSum() + "层");
			}

			if (!TextUtils.isEmpty(businessEstate.getBerryGG())) {
				tvBerryGG.setText(businessEstate.getBerryGG() + "m²");
			} else {
				tvBerryGG.setText("-");
			}
			// tvOrientationName.setText(businessEstate.getOrientationName());
			if (businessEstate.getEstatePrice() != null) {
				tvEstatePrice.setText(businessEstate.getEstatePrice() + "元/平米月");
			} else {
				tvEstatePrice.setText("-");
			}
			tvAreaListName.setText(businessEstate.getAreaListName());
			tvUniqueNo.setText(businessEstate.getUniqueNo());
			tvHouseRensourceDesc.setText(businessEstate.getHouseResourceDesc());
			tvDecorate.setText(businessEstate.getDecorateDegreeName());
//			if (businessEstate.getIsExpert() == 0) {
//				tvIsExpert.setVisibility(View.GONE);
//			} else {
//				tvIsExpert.setVisibility(View.VISIBLE);
//			}
			tvIsExpert.setText(businessEstate.realName);
			tvBrokerName.setText(businessEstate.getBrokerName());
			if (!TextUtils.isEmpty(businessEstate.getHeadUrl())) {
				AsyncImageLoader.setAsynImages(imgBroker, businessEstate.getHeadUrl());
			}

			assortFacilityList.addAll(content.getBusinessEstate().getAssortFacilityList());

			flowLayout.removeAllViews();
			if (assortFacilityList.size() > 0) {
				for (int i = 0; i < assortFacilityList.size(); i++) {
					View view = getLayoutInflater().inflate(R.layout.item_grid_text, null);
					TextView textView = (TextView) view.findViewById(R.id.textView1);
					textView.setText(assortFacilityList.get(i).getFacilityName());
					MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(
							ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
					layoutParams.rightMargin = 10;
					flowLayout.addView(view, layoutParams);
				}
			} else {
				View view = getLayoutInflater().inflate(R.layout.item_grid_text, null);
				TextView textView = (TextView) view.findViewById(R.id.textView1);
				textView.setText("暂无");
				MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(
						ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
				layoutParams.rightMargin = 10;
				flowLayout.addView(view, layoutParams);
			}

			imageList.clear();
			if (businessEstate.getImageUrlList() != null) {
				if (businessEstate.getImageUrlList().size() > 0) {
					imageList.addAll(businessEstate.getImageUrlList());
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

		// List<Object> speciltyNameList = content.getSpeciltyNameList();
		// if (speciltyNameList.size() > 0) {
		// llHouseTag.setVisibility(View.VISIBLE);
		// for (int i = 0; i < speciltyNameList.size(); i++) {
		// View view = getLayoutInflater().inflate(R.layout.item_text,
		// null);
		// TextView tvHouseTag = (TextView)
		// view.findViewById(R.id.tv_housetag01);
		// // tvHouseTag.setText(speciltyNameList.get(i));
		// llHouseTag.addView(view);
		// }
		// } else {
		// llHouseTag.setVisibility(View.GONE);
		// }
	}

	private void initView() {
		View imgMenu = findViewById(R.id.rl_sandian);
		imgMenu.setVisibility(View.VISIBLE);
		imgMenu.setOnClickListener(this);
		tvTitleName = (TextView) findViewById(R.id.title_name);
		tvRenta = (TextView) findViewById(R.id.tv_rental01);
		tvPeiTao = (TextView) findViewById(R.id.tv_peitao);
		View rlTime = findViewById(R.id.rl_time);
		tvPayTypeName = (TextView) findViewById(R.id.tv_payTypeName);
		View decorate = findViewById(R.id.rl_decorate);
		sellDecorate = findViewById(R.id.ll_sell_decorate);
		tvTitleName.setText("");
		if (selectTypeId == 0) {
			tvRenta.setText("租        金");
			tvPeiTao.setText("配套设施");
			sellDecorate.setVisibility(View.VISIBLE);
		} else {
			tvRenta.setText("售        价");
			rlTime.setVisibility(View.GONE);
			tvPayTypeName.setVisibility(View.INVISIBLE);
			decorate.setVisibility(View.VISIBLE);
			sellDecorate.setVisibility(View.GONE);
			tvPeiTao.setText("房源参数");
			findViewById(R.id.rl_buildtime).setVisibility(View.VISIBLE);
			tvBuildTime = (TextView) findViewById(R.id.tv_buildTime);
		}
		tvDecorate = (TextView) findViewById(R.id.tv_decorat);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvCreateTime = (TextView) findViewById(R.id.tv_createTime);
		tvUpdateTime = (TextView) findViewById(R.id.tv_updateTime);
		tvRental = (TextView) findViewById(R.id.tv_rental);
		tvAreaName = (TextView) findViewById(R.id.tv_areaName);
		tvBusinessName = (TextView) findViewById(R.id.tv_businessName);
		tvHousetypeName = (TextView) findViewById(R.id.tv_housetypename);
		tvDecorateDegreeName = (TextView) findViewById(R.id.tv_decorateDegreeName);
		tvFloorNum = (TextView) findViewById(R.id.tv_floorNum);
		tvBerryGG = (TextView) findViewById(R.id.tv_berryGG);
		tvAreaListName = (TextView) findViewById(R.id.tv_areaListName);
		tvUniqueNo = (TextView) findViewById(R.id.tv_uniqueNo);
		tvHouseRensourceDesc = (TextView) findViewById(R.id.tv_houseResDesc);
		tvEstatePrice = (TextView) findViewById(R.id.tv_estatePrice);
		tvBrokerName = (TextView) findViewById(R.id.tv_brokerName);
		tvIsExpert = (TextView) findViewById(R.id.tv_isExpert);
		findViewById(R.id.rl_call).setOnClickListener(this);
		findViewById(R.id.rl_communtiy).setOnClickListener(this);
		findViewById(R.id.rl_sendMSG).setOnClickListener(this);
		imgBroker = (ImageView) findViewById(R.id.img_business_broker);
		imgBroker.setOnClickListener(this);

		flowLayout = (FlowLayout) findViewById(R.id.gv_peitao);
		view = getLayoutInflater().inflate(R.layout.item_pop_more, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		tvCollect = (TextView) view.findViewById(R.id.tv_collect_more);
		if (isCollection == 0) {
			tvCollect.setText("取消收藏");
		} else {
			tvCollect.setText("收藏");
		}

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
					final String surFaceUrl = imageUrlList.getSurFaceUrl();
					if (!TextUtils.isEmpty(picUrl)) {
						AsyncImageLoader.setAsynImages(image1, picUrl);
					} else if (!TextUtils.isEmpty(surFaceUrl)) {
						AsyncImageLoader.setAsynImages(image1, surFaceUrl);
					}
					if (!TextUtils.isEmpty(picUrl)) {
						AsyncImageLoader.setAsynImages(image1, picUrl);
					}
					image1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(ShangPuDetailActivity.this, ImageActivity.class);
							if (!TextUtils.isEmpty(picUrl)) {
								intent.putExtra("imageUrl", picUrl);
							} else if (!TextUtils.isEmpty(surFaceUrl)) {
								intent.putExtra("imageUrl", surFaceUrl);
							}
							startActivity(intent);
						}
					});
				} else {
					image1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(ShangPuDetailActivity.this, ImageActivity.class);
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
				CommonUtils.call(ShangPuDetailActivity.this, businessEstate.getMobile());

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
		if (selectTypeId == 0) {
			view.findViewById(R.id.tv_collect_jubao).setVisibility(View.GONE);
		} else {
			view.findViewById(R.id.tv_collect_jubao).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = null;
					if (AccountConfig.isLogin()) {
						intent = new Intent(ShangPuDetailActivity.this, FankuiYijianActivity.class);
						intent.putExtra("secondHouseReport", id);
					} else {
						intent = new Intent(ShangPuDetailActivity.this, LoginActivity.class);
					}
					startActivity(intent);

				}
			});
		}

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
					startActivity(new Intent(ShangPuDetailActivity.this, LoginActivity.class));
					ToastUtil.toastShow(ShangPuDetailActivity.this, "请先登录");
				}
			}
		});
//		view.findViewById(R.id.tv_share_more).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				DialogShare dialog = new DialogShare();
//				dialog.showDialog(ShangPuDetailActivity.this);
//				dialog.setCallBack(new DialogShareCallBack() {
//
//					private String shareImage;
//
//					public void handle(int type) {
//						if (imageList.size() > 0) {
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
//							new UmengSocialShare(ShangPuDetailActivity.this).shareWx(businessEstate.getTitle(),
//									businessEstate.getTitle(), businessEstate.getWxUrl(), shareImage, id);
//						} else if (type == 1) {// 微信朋友圈的
//							new UmengSocialShare(ShangPuDetailActivity.this).shareWxCircle(businessEstate.getTitle(),
//									businessEstate.getTitle(), businessEstate.getWxUrl(), shareImage, id);
//						} else if (type == 2) {// qq
//							new UmengSocialShare(ShangPuDetailActivity.this).shareQQ(businessEstate.getTitle(),
//									businessEstate.getTitle(), businessEstate.getWxUrl(), shareImage, id);
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
		IndexApi.getInstance().requestCollectHouse(this, id, 0, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				popupWindow.dismiss();
				isCollection = 0;
				ToastUtil.toastShow(ShangPuDetailActivity.this, msg);
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				popupWindow.dismiss();
				ToastUtil.toastShow(ShangPuDetailActivity.this, msg);
			}
		});
	}

	protected void cancleCollectHouse() {
		IndexApi.getInstance().requestCancleCollectHouse(ShangPuDetailActivity.this, id, 0, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				popupWindow.dismiss();
				isCollection = 1;
				ToastUtil.toastShow(ShangPuDetailActivity.this, "取消收藏成功");
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(ShangPuDetailActivity.this, msg);
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
			sendSmsWithNumber(this, businessEstate.getMobile());
			break;
		case R.id.rl_communtiy:
			Intent intent = new Intent(this, XiaoQuActivity.class);
			intent.putExtra("areaListId", businessEstate.getAreaListId());
			startActivity(intent);
			break;
		case R.id.rl_sandian:
			showMenuWindow();
			break;
		case R.id.img_business_broker:
			Intent intent2 = new Intent(this, JingjirenXqActivity.class);
			intent2.putExtra("brokerId", businessEstate.getBrokeId());
			startActivity(intent2);
			break;
		default:
			break;
		}

	}
}
