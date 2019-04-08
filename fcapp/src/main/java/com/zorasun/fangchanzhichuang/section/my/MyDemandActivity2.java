package com.zorasun.fangchanzhichuang.section.my;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragmentActivity;
import com.zorasun.fangchanzhichuang.general.util.ViewPagerTab;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyDemandActivity2 extends BaseFragmentActivity implements OnClickListener {

	private ViewPager viewpager_demand;
	private ViewPagerTab viewpg_demand_tab;
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private TextView tv5;
	private TextView tv6;
	private MyAdapter mAdapter;
	protected static int position;
	public AllDemandFragment allDemandFragment;
	public SecBuyDemandFragment secBuyDemandFragment;
	public WantRentDemandFragment wantRentDemandFragment;
	public SecSellDemandFragment secSellDemandFragment;
	public RentDemandFragment rentDemandFragment;
	public NewDemandFragment newDemandFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mydemand2);
		initTitle();
		initView();
		initData();
		setUpTab();
		initImage(0);
	}

	private void initData() {
		mAdapter = new MyAdapter();
		viewpager_demand.setAdapter(mAdapter);
		viewpager_demand.setOffscreenPageLimit(6);
		viewpager_demand.setOnPageChangeListener(PagerListener);
	}

	/**
	 * 页面切换
	 */
	OnPageChangeListener PagerListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			initImage(arg0);
			position = arg0;
			if (arg0 == 0) {
				if (allDemandFragment != null) {
					allDemandFragment.initView();
				}
			} else if (arg0 == 1) {
				if (secBuyDemandFragment != null) {
					secBuyDemandFragment.initView();
				}
			} else if (arg0 == 2) {
				if (wantRentDemandFragment != null) {
					wantRentDemandFragment.initView();
				}
			} else if (arg0 == 3) {
				if (secSellDemandFragment != null) {
					secSellDemandFragment.initView();
				}
			} else if (arg0 == 4) {
				if (rentDemandFragment != null) {
					rentDemandFragment.initView();
				}
			} else if (arg0 == 5) {
				if (newDemandFragment != null) {
					newDemandFragment.initView();
				}
			}
		}

	};

	// 添加适配器
	public class MyAdapter extends FragmentStatePagerAdapter {
		public MyAdapter() {
			super(getSupportFragmentManager());
		}

		@Override
		public int getCount() {
			return 6;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (allDemandFragment == null) {
					return allDemandFragment = new AllDemandFragment();
				}
				break;
			case 1:
				if (secBuyDemandFragment == null) {
					return secBuyDemandFragment = new SecBuyDemandFragment();
				}
				break;
			case 2:
				if (wantRentDemandFragment == null) {
					return wantRentDemandFragment = new WantRentDemandFragment();
				}
				break;
			case 3:
				if (secSellDemandFragment == null) {
					return secSellDemandFragment = new SecSellDemandFragment();
				}
				break;
			case 4:
				if (rentDemandFragment == null) {
					return rentDemandFragment = new RentDemandFragment();
				}
				break;
			case 5:
				if (newDemandFragment == null) {
					return newDemandFragment = new NewDemandFragment();
				}
				break;
			}
			return null;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initTitle() {
		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("我的需求");
	}

	private void initView() {
		viewpager_demand = (ViewPager) findViewById(R.id.viewpager_demand);
		viewpg_demand_tab = (ViewPagerTab) findViewById(R.id.viewpg_demand_tab);
		findViewById(R.id.title_left).setOnClickListener(this);
		findViewById(R.id.ly_quanbu).setOnClickListener(this);
		findViewById(R.id.ly_secondQiugou).setOnClickListener(this);
		findViewById(R.id.ly_qiuZu).setOnClickListener(this);
		findViewById(R.id.ly_secondQiushou).setOnClickListener(this);
		findViewById(R.id.ly_chuZu).setOnClickListener(this);
		findViewById(R.id.ll_qiugouxinfang).setOnClickListener(this);
		tv1 = (TextView) findViewById(R.id.tv_quanbu);
		tv2 = (TextView) findViewById(R.id.tv_secondQiugou);
		tv3 = (TextView) findViewById(R.id.tv_qiuZu);
		tv4 = (TextView) findViewById(R.id.tv_secondQiushou);
		tv5 = (TextView) findViewById(R.id.tv_chuZu);
		tv6 = (TextView) findViewById(R.id.tv_qiugouxinfang);
	}

	/**
	 * 设置tab
	 */
	@SuppressLint("ResourceAsColor")
	private void setUpTab() {
		viewpg_demand_tab.setViewPager(viewpager_demand);
		viewpg_demand_tab.setTabNum(6);
		ImageView childView = new ImageView(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
		childView.setBackgroundColor(Color.parseColor("#0089fe"));
		childView.setLayoutParams(params);
		viewpg_demand_tab.addView(childView);
	}

	/**
	 * 初始化布局
	 */
	private void initImage(int index) {
		tv1.setTextColor(Color.parseColor("#919191"));
		tv2.setTextColor(Color.parseColor("#919191"));
		tv3.setTextColor(Color.parseColor("#919191"));
		tv4.setTextColor(Color.parseColor("#919191"));
		tv5.setTextColor(Color.parseColor("#919191"));
		tv1.setTextColor(Color.parseColor("#919191"));
		tv6.setTextColor(Color.parseColor("#919191"));
		if (index == 0) {
			tv1.setTextColor(Color.parseColor("#0089fe"));
		} else if (index == 1) {
			tv2.setTextColor(Color.parseColor("#0089fe"));
		} else if (index == 2) {
			tv3.setTextColor(Color.parseColor("#0089fe"));
		} else if (index == 3) {
			tv4.setTextColor(Color.parseColor("#0089fe"));
		} else if (index == 4) {
			tv5.setTextColor(Color.parseColor("#0089fe"));
		} else if (index == 5) {
			tv6.setTextColor(Color.parseColor("#0089fe"));
		}
		viewpager_demand.setCurrentItem(index);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ly_quanbu:
			initImage(0);
			if (allDemandFragment != null) {
				allDemandFragment.initView();
			}
			break;
		case R.id.ly_secondQiugou:
			initImage(1);
			if (secBuyDemandFragment != null) {
				secBuyDemandFragment.initView();
			}
			break;
		case R.id.ly_qiuZu:
			initImage(2);
			if (wantRentDemandFragment != null) {
				wantRentDemandFragment.initView();
			}
			break;
		case R.id.ly_secondQiushou:
			initImage(3);
			if (secSellDemandFragment != null) {
				secSellDemandFragment.initView();
			}
			break;
		case R.id.ly_chuZu:
			initImage(4);
			if (rentDemandFragment != null) {
				rentDemandFragment.initView();
			}
			break;
		case R.id.ll_qiugouxinfang:
			initImage(5);
			if (newDemandFragment != null) {
				newDemandFragment.initView();
			}
			break;
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;

		default:
			break;
		}
	}

}
