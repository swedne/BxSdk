package com.zorasun.fangchanzhichuang.section.index;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.FlowLayout;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollGridView;
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
public class ChangFangDetailActivity extends BaseActivity implements OnClickListener {

	private TextView tvTitle;
	private TextView tvCreateTime;
	private TextView tvUpdateTime;
	private TextView tvRental;
	private TextView tvPayTypeName;
	private TextView tvAreaName;
	private TextView tvBusinessName;
	private TextView tvDecorateDegreeName;
	private TextView tvAreaListName;
	private TextView tvUniqueNo;
	private TextView tvHouseRensourceDesc;
	private Content content;
	private TextView tvBrokerName;
	private TextView tvIsExpert;
	private NoScrollGridView gridView;
	private TextView tvEstatePrice;
	private int selectTypeId = -1;
	private int id;
	private TextView tvBuildTime;
	private TextView tvRenta;
	private TextView tvChangFangArea;
	private TextView tvElectricPower;
	private TextView tvWorkArea;
	private TextView tvSpaceArea;
	private TextView tvTotalArea;
	private BusinessEstate businessEstate;
	private PopupWindow popupWindow;
	private TextView tvPeiTao;
	private List<AssortFacilityList> assortFacilityList = new ArrayList<>();
	private FlowLayout flowLayout;
	private int isCollection;
	private TextView tvCollect;
	private View view;
	private ArrayList<ImageUrlList> imageList = new ArrayList<>();
	private ViewPager mViewPage;
	private TextView tvPage;
	private PagerAdapter pagerAdapter;
	private ImageView imgBroker;
	private TextView tvTitleName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chang_fang_detail);
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
		if (content != null) {
			businessEstate = content.getBusinessEstate();
			tvTitle.setText(businessEstate.getTitle());
			isCollection = businessEstate.getIsCollection();
			if (isCollection == 0) {
				tvCollect.setText("取消收藏");
			} else {
				tvCollect.setText("收藏");
			}
			tvTitleName.setText(businessEstate.getAreaListName());
			tvCreateTime.setText(convertTime(businessEstate.getCreatedTime()));
			tvUpdateTime.setText(convertTime(businessEstate.getUpdateTime()));
			if (selectTypeId == 2) {
				tvRental.setText(businessEstate.getRental() + "元/月");
			} else {
				tvRental.setText(businessEstate.getSalePrice() + "万/套");
			}
			if (!TextUtils.isEmpty(businessEstate.getPlantAcreage())) {
				tvTotalArea.setText(businessEstate.getPlantAcreage() + "㎡");
			} else {
				tvTotalArea.setText("-");
			}
			if (!TextUtils.isEmpty(businessEstate.getSpaceAcreage())) {
				tvSpaceArea.setText(businessEstate.getSpaceAcreage() + "㎡");
			} else {
				tvSpaceArea.setText("-");
			}
			if (!TextUtils.isEmpty(businessEstate.getWorkAcreage())) {
				tvWorkArea.setText(businessEstate.getWorkAcreage() + "㎡");
			} else {
				tvWorkArea.setText("-");
			}
			if (!TextUtils.isEmpty(businessEstate.getElectricPower())) {
				tvElectricPower.setText(businessEstate.getElectricPower() + "");
			} else {
				tvElectricPower.setText("-");
			}

			String buildTime = businessEstate.getBuildTime();
			if (!TextUtils.isEmpty(buildTime)) {
				tvBuildTime.setText(buildTime + "年");
			} else {
				tvBuildTime.setText("-");
			}
			if (!TextUtils.isEmpty(businessEstate.getAcreage())) {
				tvChangFangArea.setText(businessEstate.getAcreage() + "㎡");
			} else {
				tvChangFangArea.setText("-");
			}
			tvPayTypeName.setText(businessEstate.getPayTypeName());
			tvAreaName.setText(businessEstate.getAreaName());
			tvBusinessName.setText(businessEstate.getBusinessListName());
			if (!TextUtils.isEmpty(businessEstate.getDecorateDegreeName())) {
				tvDecorateDegreeName.setText(businessEstate.getDecorateDegreeName());
			} else {
				tvDecorateDegreeName.setText("-");
			}
			// tvOrientationName.setText(businessEstate.getOrientationName());
			if (businessEstate.getEstatePrice() != null) {
				String estatePrice = String.valueOf(businessEstate.getEstatePrice());
				estatePrice = estatePrice.replaceAll("0+?$", "");// 去掉多余的0
				estatePrice = estatePrice.replaceAll("[.]$", "");// 如最后一位是.则去掉
				tvEstatePrice.setText(estatePrice + "元/平米月");
			} else {
				tvEstatePrice.setText("-");
			}
			tvAreaListName.setText(businessEstate.getAreaListName());
			tvUniqueNo.setText(businessEstate.getUniqueNo());
			tvHouseRensourceDesc.setText(businessEstate.getHouseResourceDesc());
			tvBrokerName.setText(businessEstate.getBrokerName());
			if (!TextUtils.isEmpty(businessEstate.getHeadUrl())) {
				AsyncImageLoader.setAsynImages(imgBroker, businessEstate.getHeadUrl());
			}
			// if (businessEstate.getIsExpert() == 0) {
			// tvIsExpert.setVisibility(View.GONE);
			// } else {
			// tvAreaListName.setVisibility(View.VISIBLE);
			// }
			tvIsExpert.setText(businessEstate.realName);

			assortFacilityList.clear();
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
	}

	private void initView() {
		tvTitleName = (TextView) findViewById(R.id.title_name);
		tvRenta = (TextView) findViewById(R.id.tv_rental01);
		tvPeiTao = (TextView) findViewById(R.id.tv_peitao);
		View rlTime = findViewById(R.id.rl_time);
		tvPayTypeName = (TextView) findViewById(R.id.tv_payTypeName);
		tvTitleName.setText("");
		if (selectTypeId == 2) {
			tvRenta.setText("租        金");
			tvPeiTao.setText("配套设施");
		} else {
			tvRenta.setText("售        价");
			rlTime.setVisibility(View.GONE);
			tvPayTypeName.setVisibility(View.INVISIBLE);
			tvPeiTao.setText("房源参数");
		}
		tvTotalArea = (TextView) findViewById(R.id.tv_totalArea);
		tvSpaceArea = (TextView) findViewById(R.id.tv_kongdiarea);
		tvWorkArea = (TextView) findViewById(R.id.tv_officeBerryGG);
		tvElectricPower = (TextView) findViewById(R.id.tv_electricPower);
		tvBuildTime = (TextView) findViewById(R.id.tv_buildTime);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvChangFangArea = (TextView) findViewById(R.id.tv_changfangarea);
		tvCreateTime = (TextView) findViewById(R.id.tv_createTime);
		tvUpdateTime = (TextView) findViewById(R.id.tv_updateTime);
		tvRental = (TextView) findViewById(R.id.tv_rental);
		tvAreaName = (TextView) findViewById(R.id.tv_areaName);
		tvBusinessName = (TextView) findViewById(R.id.tv_businessName);
		tvDecorateDegreeName = (TextView) findViewById(R.id.tv_decorateDegreeName);
		tvAreaListName = (TextView) findViewById(R.id.tv_areaListName);
		tvUniqueNo = (TextView) findViewById(R.id.tv_uniqueNo);
		tvHouseRensourceDesc = (TextView) findViewById(R.id.tv_houseResDesc);
		tvEstatePrice = (TextView) findViewById(R.id.tv_estatePrice);
		tvBrokerName = (TextView) findViewById(R.id.tv_brokerName);
		tvIsExpert = (TextView) findViewById(R.id.tv_isExpert);
		flowLayout = (FlowLayout) findViewById(R.id.gv_peitao);
		findViewById(R.id.rl_call).setOnClickListener(this);
		findViewById(R.id.rl_communtiy).setOnClickListener(this);
		imgBroker = (ImageView) findViewById(R.id.img_changfang_broker);
		imgBroker.setOnClickListener(this);
		findViewById(R.id.rl_sendMSG).setOnClickListener(this);
		View imgMenu = findViewById(R.id.rl_sandian);
		imgMenu.setVisibility(View.VISIBLE);
		imgMenu.setOnClickListener(this);

		view = getLayoutInflater().inflate(R.layout.item_pop_more, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		tvCollect = (TextView) view.findViewById(R.id.tv_collect_more);

		mViewPage = (ViewPager) findViewById(R.id.index_ViewPager);
		tvPage = (TextView) findViewById(R.id.tvpage);
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
					image1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(ChangFangDetailActivity.this, ImageActivity.class);
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
							Intent intent = new Intent(ChangFangDetailActivity.this, ImageActivity.class);
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
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
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
				CommonUtils.call(ChangFangDetailActivity.this, businessEstate.getMobile());

			}
		});
		view.findViewById(R.id.tv_cancle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
	}

	public void sendSmsWithNumber(Context context, String number) {
		Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
		context.startActivity(sendIntent);
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
				Intent intent = new Intent(ChangFangDetailActivity.this, FankuiYijianActivity.class);
				intent.putExtra("secondHouseReport", id);
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
					startActivity(new Intent(ChangFangDetailActivity.this, LoginActivity.class));
					ToastUtil.toastShow(ChangFangDetailActivity.this, "请先登录");
				}

			}
		});
		view.findViewById(R.id.tv_share_more).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogShare dialog = new DialogShare();
				dialog.showDialog(ChangFangDetailActivity.this);
				dialog.setCallBack(new DialogShareCallBack() {

					private String shareImage;

					public void handle(int type) {
						if (imageList.size() > 0) {
							ImageUrlList imageUrlList = imageList.get(0);
							String picUrl = imageUrlList.getPicUrl();
							String surFaceUrl = imageUrlList.getSurFaceUrl();
							if (!TextUtils.isEmpty(picUrl)) {
								shareImage = ApiConfig.getImageUrl(picUrl);
							} else if (!TextUtils.isEmpty(surFaceUrl)) {
								shareImage = ApiConfig.getImageUrl(surFaceUrl);
							}
						}
					}
				});
			}
		});
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
				ToastUtil.toastShow(ChangFangDetailActivity.this, msg);
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				popupWindow.dismiss();
				ToastUtil.toastShow(ChangFangDetailActivity.this, msg);
			}
		});
	}

	protected void cancleCollectHouse() {

		IndexApi.getInstance().requestCancleCollectHouse(ChangFangDetailActivity.this, id, 0, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				popupWindow.dismiss();
				isCollection = 1;
				ToastUtil.toastShow(ChangFangDetailActivity.this, msg);
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(ChangFangDetailActivity.this, msg);
				popupWindow.dismiss();
			}
		});
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
		case R.id.img_changfang_broker:
			Intent intent2 = new Intent(this, JingjirenXqActivity.class);
			intent2.putExtra("brokerId", businessEstate.getBrokeId());
			startActivity(intent2);
			break;
		case R.id.rl_sandian:
			showMenuWindow();
			break;
		default:
			break;
		}

	}
}
