package com.zorasun.fangchanzhichuang.section.index;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.ScreenUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.adcycle.CycleViewPager;
import com.zorasun.fangchanzhichuang.general.widget.adcycle.CycleViewPager.ImageCycleViewListener;
import com.zorasun.fangchanzhichuang.section.index.entity.AdMangerEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.AdMangerEntity.AdListByIndex;
import com.zorasun.fangchanzhichuang.section.index.entity.AdMangerEntity.AdListByPlay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 首页-frm ==
 * 
 * @author 林文熹
 * @e-mail 635991604@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月14日 下午2:19:56
 */
public class IndexFragment extends BaseFragment implements OnClickListener {
	LayoutInflater inflater;

	private boolean isTouch;
	private Runnable run = null;
	private View view;
	private TextView tvCity;
	private List<AdListByIndex> adListByIndex = new ArrayList<>();
	private List<AdListByPlay> adListByPlay = new ArrayList<>();
	private List<com.zorasun.fangchanzhichuang.section.index.entity.AdListByPlay> adListByPlay1 = new ArrayList<>();
	private ImageView imgTitle;
	private TextView tvPriceLastMonth;
	private TextView tvSuccessNumLastMonth;
	// private View layoutPager;

	private CycleViewPager mCycleViewPager;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		if (view == null) {
			view = inflater.inflate(R.layout.fragment_index, null);
			initUI();
		}
		initData();
		return view;
	}

	private void initData() {
		IndexApi.getInstance().requestAdmanager(getActivity(), getCurrentTime(), getCurrentMonth(),
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {
						AdMangerEntity adMangerEntity = (AdMangerEntity) object;
						adListByIndex.clear();
						adListByPlay.clear();
						adListByIndex.addAll(adMangerEntity.getContent().getAdListByIndex());
						adListByPlay.addAll(adMangerEntity.getContent().getAdListByPlay());
						// indicator.setCircleNum(adListByPlay.size());
						// adListByPlay.clear();
						if (adListByPlay.size() > 0) {
							// mlifeviewpager.setCurrentItem(adListByPlay.size()
							// * 5000 / 2);
							// layoutPager.setVisibility(View.VISIBLE);
						} else {
							// layoutPager.setVisibility(View.GONE);
						}
						if (adListByIndex.size() > 0) {
							AsyncImageLoader.setAsynImages(imgTitle, adListByIndex.get(0).getBigImage());
						}
						tvPriceLastMonth.setText(adMangerEntity.getContent().getXiamenSecondHouseQuotationMap()
								.getXiamenAvgPriceByLastMonth() + "");
						tvSuccessNumLastMonth.setText(adMangerEntity.getContent().getXiamenSecondHouseQuotationMap()
								.getXiamenSuccessNumByLastMonth() + "");
						initAD();
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

	/**
	 * 初始化广告
	 */
	private void initAD() {

		mCycleViewPager = (CycleViewPager) getChildFragmentManager().findFragmentById(R.id.cycleViewPager);
		RelativeLayout relat_banner = (RelativeLayout) view.findViewById(R.id.relat_banner);
		LinearLayout.LayoutParams vpLayout = (LinearLayout.LayoutParams) relat_banner.getLayoutParams();
		vpLayout.height = (int) (ScreenUtils.getScreenWidth(getActivity()) / 3);// 屏幕大小
		if (adListByPlay.size() > 0) {
			relat_banner.setVisibility(View.VISIBLE);
		} else {
			relat_banner.setVisibility(View.GONE);
		}
		adListByPlay1.clear();
		for (int i = 0; i < adListByPlay.size(); i++) {
			com.zorasun.fangchanzhichuang.section.index.entity.AdListByPlay info = new com.zorasun.fangchanzhichuang.section.index.entity.AdListByPlay();
			if (!TextUtils.isEmpty(adListByPlay.get(i).getBigImage())) {
				info.setBigImage(ApiConfig.getImageUrl(adListByPlay.get(i).getBigImage()));
			}
			if (adListByPlay.get(i).getSign() != null) {
				info.setSign(adListByPlay.get(i).getSign());
			}
			if (adListByPlay.get(i).getBrokerId() != null) {
				info.setBrokerId(adListByPlay.get(i).getBrokerId());
			}
			if (adListByPlay.get(i).getHouseResourceListId() != null) {
				info.setHouseResourceListId(adListByPlay.get(i).getHouseResourceListId());
			}
			if (adListByPlay.get(i).getNewHouseListId() != null) {
				info.setNewHouseListId(adListByPlay.get(i).getNewHouseListId());
			}
			adListByPlay1.add(info);
		}

		// 设置循环，在调用setData方法前调用
		mCycleViewPager.setCycle(true);
		// 在加载数据前设置是否循环
		mCycleViewPager.setData(adListByPlay1, mAdCycleViewListener);
		// 设置轮播
		if (adListByPlay.size() == 1) {
			mCycleViewPager.setWheel(false);
		} else {
			mCycleViewPager.setWheel(true);

		}
		// 设置轮播时间，默认5000ms
		mCycleViewPager.setTime(2000);
		// 设置圆点指示图标组居中显示，默认靠右
		mCycleViewPager.setIndicatorCenter();

	}

	/**
	 * 图片轮播监听
	 */
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(com.zorasun.fangchanzhichuang.section.index.entity.AdListByPlay info, int postion,
				View imageView) {

			Intent intent = null;
			int sign = info.getSign();
			if (sign == 1) {
				intent = new Intent(getActivity(), JingjirenXqActivity.class);
				intent.putExtra("brokerId", info.getBrokerId());
			} else if (sign == 2) {
				intent = new Intent(getActivity(), SecondHouseDetailActivity.class);
				intent.putExtra("secondHouseId", info.getHouseResourceListId());
			} else if (sign == 3) {
				intent = new Intent(getActivity(), ZuFangxqActivity.class);
				intent.putExtra("rentHouseId", info.getHouseResourceListId());
			} else if (sign == 4) {
				intent = new Intent(getActivity(), XinFangxqActivity.class);
				intent.putExtra("NewhouseId", info.getNewHouseListId());
			}
			startActivity(intent);

		}

	};

	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		// 获得当前时间的月份，月份从0开始所以结果要加1
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static String getCurrentTime() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		returnStr = f.format(date);
		return returnStr;
	}

	private void initUI() {
		// initBanner();
		tvCity = (TextView) view.findViewById(R.id.tv_choosecity);
//		tvCity.setOnClickListener(this);
		TextView index_agent = (TextView) view.findViewById(R.id.index_agent);
		index_agent.setOnClickListener(this);
		EditText etSearch = (EditText) view.findViewById(R.id.etSearch);
		etSearch.setOnClickListener(this);
		TextView index_secondhouse = (TextView) view.findViewById(R.id.index_secondhouse);
		index_secondhouse.setOnClickListener(this);
		view.findViewById(R.id.index_mapSeach).setOnClickListener(this);
		view.findViewById(R.id.index_calculator).setOnClickListener(this);
		view.findViewById(R.id.index_businessArea).setOnClickListener(this);
		view.findViewById(R.id.index_chanquanyanzheng).setOnClickListener(this);

		TextView index_rend = (TextView) view.findViewById(R.id.index_rend);
		index_rend.setOnClickListener(this);
		TextView index_newHouse = (TextView) view.findViewById(R.id.index_newHouse);
		index_newHouse.setOnClickListener(this);

		view.findViewById(R.id.rv_citywideprice).setOnClickListener(this);
		view.findViewById(R.id.rl_lastmontnum).setOnClickListener(this);
		imgTitle = (ImageView) view.findViewById(R.id.img_title);

		tvPriceLastMonth = (TextView) view.findViewById(R.id.tv_citywideprice);
		tvSuccessNumLastMonth = (TextView) view.findViewById(R.id.tv_lastmonthnum);
	}

	// private void initBanner() {
	// FragmentManager fm = getFragmentManager();
	// bannerAdapter = new BannerAdapter(fm);
	//
	// }

	// private void pagerAutoScroll() {
	// mlifeviewpager.postDelayed(new Runnable() {
	//
	// @Override
	// public void run() {
	// int item = mlifeviewpager.getCurrentItem() + 1;
	// // if (!hasDestroy) {
	// mlifeviewpager.postDelayed(this, 3500);
	// // } else {
	// // return;
	// // }
	// if (!isTouch) {
	// mlifeviewpager.setCurrentItem(item);
	// }
	// }
	// }, 3500);
	// }

	@Override
	public void onStart() {
		super.onStart();
	}

	// class MyAdapter extends PagerAdapter {
	// @Override
	// public void destroyItem(View container, int position, Object object) {
	// View view = (View) object;
	// mlifeviewpager.removeView(view);
	// }
	//
	// @Override
	// public Object instantiateItem(View container, int position) {
	// int i = position % 3;
	// // if(hash_help.size()!=0)
	// // {
	// // i=position%hash_help.size();
	// // }
	// View inflate = inflater.inflate(R.layout.lifeyue, null);
	// ImageView image1 = (ImageView) inflate.findViewById(R.id.imageView1);
	// image1.setBackgroundResource(R.drawable.bgxia);
	// mlifeviewpager.addView(inflate);
	// return inflate;
	// }
	//
	// @Override
	// public boolean isViewFromObject(View arg0, Object arg1) {
	// return arg0 == arg1;
	// }
	//
	// @Override
	// public int getCount() {
	// return 5000;
	// }
	// }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 选择城市跳转
		case R.id.tv_choosecity:
			Intent choosecityIntent = new Intent(getActivity(), ChooseCityActivity.class);
			startActivityForResult(choosecityIntent, 0);
			break;
		// 搜索跳转
		case R.id.etSearch:
			Intent shouyeIntent = new Intent(getActivity(), IndexSerachActivity.class);
			shouyeIntent.putExtra("stageNum", SystemConstant.STATE_PAGE_SHOUYE);
			startActivity(shouyeIntent);
			// startActivityForResult(shouyeIntent,
			// SystemConstant.STATE_PAGE_SHOUYE);
			break;
		// 经纪人跳转
		case R.id.index_agent:
			Intent indexagentIntent = new Intent(getActivity(), JingjiRenActivity.class);
			startActivity(indexagentIntent);
			break;

		// 二手房跳转
		case R.id.index_secondhouse:
			Intent indexsecondhouseIntent = new Intent(getActivity(), IndexSecondHouseActivity.class);
			startActivity(indexsecondhouseIntent);
			break;
		// 租房跳转
		case R.id.index_rend:
			Intent indexzufangIntent = new Intent(getActivity(), ZuFangActivity.class);
			startActivity(indexzufangIntent);
			break;
		// 新房跳转
		case R.id.index_newHouse:
			Intent index_newHouseintent = new Intent(getActivity(), XinFangActivity.class);
			startActivity(index_newHouseintent);
			break;
		// 地图找房
		case R.id.index_mapSeach:
			ToastUtil.toastShow(getActivity(),"暂未开放");

//			Intent indexDiTuZhaoIntent = new Intent(getActivity(), DiTuZhaoFangActivity.class);
//			startActivity(indexDiTuZhaoIntent);
			break;
		// 计算器
		case R.id.index_calculator:
			Intent intent = new Intent(getActivity(), LoanCalActivity.class);
			startActivity(intent);
			break;
		// 商业地产
		case R.id.index_businessArea:
			Intent indexBusinessAreaIntent = new Intent(getActivity(), ShangYeDiChanActivity.class);
			startActivity(indexBusinessAreaIntent);
			break;
		// 产权验证
		case R.id.index_chanquanyanzheng:
			Intent indexChanQuanYanZhengIntent = new Intent(getActivity(), ChanQuanYanZhengActivity.class);
			startActivity(indexChanQuanYanZhengIntent);
			break;
		// 均价
		case R.id.rv_citywideprice:
			Intent wpIntent = new Intent(getActivity(), WidePriceActivity.class);
			wpIntent.putExtra("flag", 1);
			startActivity(wpIntent);
			break;
		case R.id.rl_lastmontnum:
			Intent lastmontIntent = new Intent(getActivity(), WidePriceActivity.class);
			lastmontIntent.putExtra("flag", SystemConstant.LASTMONTH_INTENT);
			startActivity(lastmontIntent);
			break;
		default:
			break;
		}
	}

	// class BannerAdapter extends FragmentStatePagerAdapter {
	//
	// public BannerAdapter(FragmentManager fm) {
	// super(fm);
	// }
	//
	// @Override
	// public Fragment getItem(int position) {
	// position = position % adListByPlay.size();
	// String bigImage = adListByPlay.get(position).getBigImage();
	// int sign = adListByPlay.get(position).getSign();
	// int brokerId = -1;
	// int houseResourceListId = -1;
	// int newHouseListId = -1;
	// if (adListByPlay.get(position).getBrokerId() != null) {
	// brokerId = adListByPlay.get(position).getBrokerId();
	// }
	// if (adListByPlay.get(position).getHouseResourceListId() != null) {
	// houseResourceListId =
	// adListByPlay.get(position).getHouseResourceListId();
	// }
	// if (adListByPlay.get(position).getNewHouseListId() != null) {
	// newHouseListId = adListByPlay.get(position).getNewHouseListId();
	// }
	// return new BannerFragment(bigImage, sign, brokerId, houseResourceListId,
	// newHouseListId);
	// }
	//
	// @Override
	// public int getCount() {
	// return adListByPlay.size() * 5000;
	// }
	//
	// }

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		// hasDestroy = true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String city = null;
		if (data != null) {
			city = data.getStringExtra("city");
			if (TextUtils.isEmpty(city)) {
				tvCity.setText("厦门");
			} else {
				if (requestCode == 0) {
					tvCity.setText(city);
				}
			}
		}
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

}
