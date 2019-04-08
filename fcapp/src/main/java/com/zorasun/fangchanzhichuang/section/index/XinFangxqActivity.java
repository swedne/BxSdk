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
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.account.LoginActivity;
import com.zorasun.fangchanzhichuang.section.dialog.DialogShare;
import com.zorasun.fangchanzhichuang.section.dialog.DialogShare.DialogShareCallBack;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity.ApartmentList;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity.Content;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity.ImageUrlList;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity.NewHouseMap;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity.PropertyGalleryList;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity.SpecialList;
import com.zorasun.fangchanzhichuang.section.index.tools.ToolActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class XinFangxqActivity extends BaseActivity implements OnClickListener {

	private RecyclerView hlvHuXing;
	private RecyclerView hlvLouPan;
	private TextView tvNewHouseName;
	private TextView tvAverage;
	private TextView tvProjectUrl;
	private TextView tvHostHouseType;
	private TextView tvStructureName;
	private TextView tvManagerFee;
	private TextView tvAgentDevelopers;
	private TextView tvOpenTime;
	private TextView tvVolumeTime;
	private TextView tvProjectDetail;
	private Content content;
	private List<SpecialList> specialList = new ArrayList<>();
	private LinearLayout llHouseTag;
	private HuXingShowAdapter1 hlvHuXingAdapter;
	private NewHouseMap newHouseMap;
	private PopupWindow popupWindow;
	private int isCollection;
	private ViewPager mViewPage;
	private TextView tvPage;
	private PagerAdapter pagerAdapter;
	private List<ImageUrlList> imageList = new ArrayList<>();
	private List<ApartmentList> apartmentList = new ArrayList<>();
	private List<PropertyGalleryList> propertyGalleryList = new ArrayList<>();
	private LouPanShowAdapter1 louPanShowAdapter;
	private View noPic0;
	private View nopic1;
	private TextView tvCollect;
	private View view;
	private int newhouseId;
	private TextView tvTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xin_fangxq);

		initView();
		initData();
	}

	private void initData() {
		newhouseId = getIntent().getIntExtra("NewhouseId", -1);
		IndexApi.getInstance().requestNewHouseInfo(this, newhouseId, AccountConfig.getAccountId(),
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {
						NewHouseEntity newHouseEntity = (NewHouseEntity) object;
						content = newHouseEntity.getContent();
						setData();
					}

					@Override
					public void onNetworkError() {

					}

					@Override
					public void onFailure(int code, String msg, Object object) {

					}
				});

	}

	private String convertTime(long mills) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(mills);
		return formatter.format(calendar.getTime());
	}

	protected void setData() {
		newHouseMap = content.getNewHouseMap();
		if (newHouseMap != null) {
			isCollection = newHouseMap.getIsCollection();
			tvTitle.setText(newHouseMap.getNewhouseName());
			if (isCollection == 0) {
				tvCollect.setText("取消收藏");
			} else {
				tvCollect.setText("收藏");
			}
			tvNewHouseName.setText(newHouseMap.getNewhouseName());
			tvAverage.setText(newHouseMap.getAverage() + "元/㎡");
			tvProjectUrl.setText(newHouseMap.getProjectUrl());
			tvHostHouseType.setText(newHouseMap.getHostHouseType());
			tvStructureName.setText(newHouseMap.getStructureName());
			tvManagerFee.setText(newHouseMap.getManagerFee() + "元/平米月");
			if (!TextUtils.isEmpty(newHouseMap.getDevelopers())) {
				tvAgentDevelopers.setText(newHouseMap.getDevelopers());
			}
			tvOpenTime.setText("" + convertTime(newHouseMap.getOpenTime()));
			tvVolumeTime.setText("" + convertTime(newHouseMap.getVolumeTime()));
			tvProjectDetail.setText(newHouseMap.getProjectDetail());
			specialList.clear();
			specialList.addAll(newHouseMap.getSpecialList());
			if (specialList.size() > 0) {
				for (int i = 0; i < specialList.size(); i++) {
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
					tvHouseTag.setText(specialList.get(i).getSpecialName());
					llHouseTag.addView(child);
				}
			} else {
				llHouseTag.setVisibility(View.GONE);
			}
			imageList.clear();
			if (newHouseMap.getImageUrlList() != null) {
				if (newHouseMap.getImageUrlList().size() > 0) {
					imageList.addAll(newHouseMap.getImageUrlList());
					tvPage.setText(1 + "/" + imageList.size());
					pagerAdapter.notifyDataSetChanged();
				} else {
					tvPage.setText(1 + "/" + 1);
				}
			} else {
				tvPage.setText(1 + "/" + 1);
			}
			mViewPage.setCurrentItem(imageList.size() * 5000 / 2);
			apartmentList.clear();
			apartmentList.addAll(newHouseMap.getApartmentList());
			if (apartmentList == null || apartmentList.size() <= 0) {
				noPic0.setVisibility(View.VISIBLE);
			}
			hlvHuXingAdapter.notifyDataSetChanged();

			propertyGalleryList.clear();
			propertyGalleryList.addAll(newHouseMap.getPropertyGalleryList());
			if (propertyGalleryList == null || propertyGalleryList.size() <= 0) {
				nopic1.setVisibility(View.VISIBLE);
			}
			louPanShowAdapter.notifyDataSetChanged();
		}
	}

	@SuppressLint("InflateParams")
	private void initView() {
		View imgMenu = findViewById(R.id.rl_sandian);
		imgMenu.setVisibility(View.VISIBLE);
		imgMenu.setOnClickListener(this);
		findViewById(R.id.rl_call_xinfangxq).setOnClickListener(this);
		findViewById(R.id.ly_zhuanjia).setVisibility(View.GONE);
		findViewById(R.id.img_avator).setVisibility(View.INVISIBLE);
		tvTitle = (TextView) findViewById(R.id.title_name);
		tvTitle.setText("");
		findViewById(R.id.title_left).setOnClickListener(this);

		tvNewHouseName = (TextView) findViewById(R.id.tv_newHouseName);
		tvAverage = (TextView) findViewById(R.id.tv_average);
		tvProjectUrl = (TextView) findViewById(R.id.tv_projectUrl);
		tvHostHouseType = (TextView) findViewById(R.id.tv_hostHouseType);
		tvStructureName = (TextView) findViewById(R.id.tv_structureName);
		tvManagerFee = (TextView) findViewById(R.id.tv_managerFee);
		tvAgentDevelopers = (TextView) findViewById(R.id.tv_agentDevelopers);
		tvOpenTime = (TextView) findViewById(R.id.tv_openTime);
		tvVolumeTime = (TextView) findViewById(R.id.tv_volumeTime);
		tvProjectDetail = (TextView) findViewById(R.id.tv_projectDetail);
		llHouseTag = (LinearLayout) findViewById(R.id.ll_housetag);
		hlvHuXing = (RecyclerView) findViewById(R.id.horizontallistview1);
		hlvLouPan = (RecyclerView) findViewById(R.id.horizontallistview2);

		view = getLayoutInflater().inflate(R.layout.item_pop_more, null);
		popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		tvCollect = (TextView) view.findViewById(R.id.tv_collect_more);

		// 设置布局管理器
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		hlvHuXing.setLayoutManager(linearLayoutManager);
		hlvHuXingAdapter = new HuXingShowAdapter1();
		hlvHuXingAdapter.notifyDataSetChanged();
		hlvHuXing.setAdapter(hlvHuXingAdapter);

		LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
		linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
		hlvLouPan.setLayoutManager(linearLayoutManager1);
		louPanShowAdapter = new LouPanShowAdapter1();
		hlvLouPan.setAdapter(louPanShowAdapter);
		louPanShowAdapter.notifyDataSetChanged();
		findViewById(R.id.ll_sendDemand).setOnClickListener(this);
		findViewById(R.id.img_jisuanqi).setOnClickListener(this);
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
					final String url = imageUrlList.getUrl();
					if (!TextUtils.isEmpty(url)) {
						AsyncImageLoader.setAsynImages(image1, url);
					}
					image1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(XinFangxqActivity.this, ImageActivity.class);
							intent.putExtra("imageUrl", url);
							intent.putExtra("imageName", imageUrlList.getTypeName());
							startActivity(intent);
						}
					});
				} else {
					image1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(XinFangxqActivity.this, ImageActivity.class);
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
		noPic0 = findViewById(R.id.tv_nopic);
		nopic1 = findViewById(R.id.tv_nopic1);

	}

	public class HuXingShowAdapter1 extends RecyclerView.Adapter<HuXingShowAdapter1.ViewHolder> {
		public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

			private View view;

			public ViewHolder(View itemView) {
				super(itemView);
				this.view = itemView;
				view.setOnClickListener(this);
			}

			private ImageView imgHuxing;
			private TextView tvHuXing;

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(XinFangxqActivity.this, HuXingShowActivity.class);
				intent.putExtra("newHouseMap", newHouseMap);
				startActivity(intent);
			}

		}

		@Override
		public int getItemCount() {
			// TODO Auto-generated method stub
			return apartmentList.size();
		}

		@Override
		public void onBindViewHolder(ViewHolder viewHolder, int position) {
			ApartmentList apartmentInfo = apartmentList.get(position);
			AsyncImageLoader.setAsynImages(viewHolder.imgHuxing, apartmentInfo.getUrl());
			viewHolder.tvHuXing.setText(apartmentInfo.getHouseTypeName());
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
			View rootView = getLayoutInflater().inflate(R.layout.rv_item, null);
			ViewHolder viewHolder = new ViewHolder(rootView);
			viewHolder.imgHuxing = (ImageView) rootView.findViewById(R.id.img_huXing);
			viewHolder.tvHuXing = (TextView) rootView.findViewById(R.id.tv_huXing);
			return viewHolder;
		}
	}

	public class LouPanShowAdapter1 extends RecyclerView.Adapter<LouPanShowAdapter1.ViewHolder> {
		public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

			private View view;

			public ViewHolder(View itemView) {
				super(itemView);
				this.view = itemView;
				view.setOnClickListener(this);
			}

			private ImageView imgHuxing;
			private TextView tvHuXing;

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(XinFangxqActivity.this, HuXingShowActivity.class);
				intent.putExtra("newHouseMap", newHouseMap);
				intent.putExtra("flag", 2);
				startActivity(intent);
			}

		}

		@Override
		public int getItemCount() {
			// TODO Auto-generated method stub
			return propertyGalleryList.size();
		}

		@Override
		public void onBindViewHolder(ViewHolder viewHolder, int position) {
			PropertyGalleryList propertyGalleryInfo = propertyGalleryList.get(position);
			AsyncImageLoader.setAsynImages(viewHolder.imgHuxing, propertyGalleryInfo.getUrl());
			viewHolder.tvHuXing.setText(propertyGalleryInfo.getTypeName());
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
			View rootView = getLayoutInflater().inflate(R.layout.rv_item, null);
			ViewHolder viewHolder = new ViewHolder(rootView);
			viewHolder.imgHuxing = (ImageView) rootView.findViewById(R.id.img_huXing);
			viewHolder.tvHuXing = (TextView) rootView.findViewById(R.id.tv_huXing);
			return viewHolder;
		}
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
				CommonUtils.call(XinFangxqActivity.this, newHouseMap.getDevelopersMobile());
			}
		});
		view.findViewById(R.id.tv_cancle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
	}

	//

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		case R.id.rl_call_xinfangxq:
			showCallWindow();
			break;
		case R.id.ll_sendDemand:
			// if (AccountConfig.isLogin()) {
			Intent intent = new Intent(this, QiuGouXinFangActivity.class);
			intent.putExtra("newhouseId", newhouseId);
			startActivity(intent);
			// } else {
			// startActivity(new Intent(this, LoginActivity.class));
			// }
			break;
		case R.id.rl_sandian:
			showMenuWindow();
			break;
		case R.id.img_jisuanqi:
			startActivity(new Intent(this, ToolActivity.class));
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
					startActivity(new Intent(XinFangxqActivity.this, LoginActivity.class));
					ToastUtil.toastShow(XinFangxqActivity.this, "请先登录");
				}
			}
		});
		view.findViewById(R.id.tv_share_more).setVisibility(View.GONE);
//		view.findViewById(R.id.tv_share_more).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				DialogShare dialog = new DialogShare();
//				dialog.showDialog(XinFangxqActivity.this);
//				dialog.setCallBack(new DialogShareCallBack() {
//
//					private String shareImage;
//
//					public void handle(int type) {
//						if (imageList.size() > 0) {
//							shareImage = ApiConfig.getImageUrl(imageList.get(0).getUrl());
//						}
//						if (type == 0) {// 微信的
//							new UmengSocialShare(XinFangxqActivity.this).shareWx(newHouseMap.getNewhouseName(),
//									newHouseMap.getNewhouseName(), newHouseMap.getProjectUrl(), shareImage, newhouseId);
//						} else if (type == 1) {// 微信朋友圈的
//							new UmengSocialShare(XinFangxqActivity.this).shareWxCircle(newHouseMap.getNewhouseName(),
//									newHouseMap.getNewhouseName(), newHouseMap.getProjectUrl(), shareImage, newhouseId);
//						} else if (type == 2) {// qq
//							new UmengSocialShare(XinFangxqActivity.this).shareQQ(newHouseMap.getNewhouseName(),
//									newHouseMap.getNewhouseName(), newHouseMap.getProjectUrl(), shareImage, newhouseId);
//						}
//					}
//				});
//
//			}
//		});
//		view.findViewById(R.id.tv_cancle).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				popupWindow.dismiss();
//			}
//		});
	}

	protected void collectHouse() {
		IndexApi.getInstance().requestCollectHouse(this, newHouseMap.getNewhouseId(), 2, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				popupWindow.dismiss();
				isCollection = 0;
				ToastUtil.toastShow(XinFangxqActivity.this, msg);
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				popupWindow.dismiss();
				ToastUtil.toastShow(XinFangxqActivity.this, msg);
			}
		});
	}

	protected void cancleCollectHouse() {
		IndexApi.getInstance().requestCancleCollectHouse(XinFangxqActivity.this, newHouseMap.getNewhouseId(), 2,

		new RequestCallBack() {
			@Override
			public void onSuccess(int code, String msg, Object object) {
				popupWindow.dismiss();
				isCollection = 1;
				ToastUtil.toastShow(XinFangxqActivity.this, "取消收藏成功");
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(XinFangxqActivity.this, msg);
				popupWindow.dismiss();
			}
		});
	}
}
