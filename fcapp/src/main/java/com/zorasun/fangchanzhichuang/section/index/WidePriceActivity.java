package com.zorasun.fangchanzhichuang.section.index;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.base.BaseFragmentActivity;
import com.zorasun.fangchanzhichuang.general.util.ViewPagerTab;
import com.zorasun.fangchanzhichuang.section.index.tools.AveragePriceFragment;
import com.zorasun.fangchanzhichuang.section.index.tools.TradingFragment;

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

public class WidePriceActivity extends BaseFragmentActivity implements OnClickListener {

	private int intExtra;
	private TextView tvLastMonth;
	private TextView tvWidePrice;
	private ViewPager viewPager;
	private ViewPagerTab viewPagerTab;
	private BaseFragment[] tool_Views = new BaseFragment[2];
	private MyAdapter mAdapter;
	private AveragePriceFragment averagePriceFragment;
	private TradingFragment tradingFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wide_price);
		intExtra = getIntent().getIntExtra("flag", 0);
		initView();
		mAdapter = new MyAdapter();
		viewPager.setAdapter(mAdapter);
		viewPager.setOnPageChangeListener(PagerListener);
		viewPager.setOffscreenPageLimit(3);

		setUpTab();
		if (intExtra == 2) {
			initImage(1);
		} else {
			initImage(0);
		}
		if (mAdapter.getItem(0) != null) {
			averagePriceFragment = (AveragePriceFragment) mAdapter.getItem(0);
			averagePriceFragment.initData();
		}
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
		public void onPageSelected(int position) {
			initImage(position);
			if (tool_Views[position] != null) {
				tool_Views[position].initView();
			}

		}

	};
	private ImageView childView;

	/**
	 * 初始化布局
	 */
	private void initImage(int index) {
		tvWidePrice.setTextColor(Color.parseColor("#919191"));
		tvLastMonth.setTextColor(Color.parseColor("#919191"));
		if (index == 0) {
			tvWidePrice.setTextColor(Color.parseColor("#0089fe"));
		}else{
			tvLastMonth.setTextColor(Color.parseColor("#0089fe"));
		}
		childView.setBackgroundColor(Color.parseColor("#0089fe"));
		// } else if (index == 1) {
		// tvLastMonth.setTextColor(ContextCompat.getColor(this,
		// R.color.title_bg));
		// childView.setBackgroundColor(ContextCompat.getColor(this,
		// R.color.title_bg));
		// }
		viewPager.setCurrentItem(index);
	}

	private void initView() {
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		tvTitle.setText("二手房行情");
		findViewById(R.id.title_left).setOnClickListener(this);
		viewPager = (ViewPager) findViewById(R.id.viewpager_tool);
		viewPagerTab = (ViewPagerTab) findViewById(R.id.viewpg_price_tab);
		// ListView mListView = (ListView) findViewById(R.id.lv_quyu_price);
		// mListView.setAdapter(new MyAdapter());
		findViewById(R.id.rl_lastMonthNum).setOnClickListener(this);
		findViewById(R.id.rl_wideprice).setOnClickListener(this);
		tvLastMonth = (TextView) findViewById(R.id.tv_lastMonth);
		tvWidePrice = (TextView) findViewById(R.id.tv_wideprice);

	}

	/**
	 * 设置tab
	 */
	private void setUpTab() {
		viewPagerTab.setViewPager(viewPager);
		viewPagerTab.setTabNum(2);
		childView = new ImageView(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
		childView.setBackgroundColor(Color.parseColor("#0089fe"));
		childView.setLayoutParams(params);
		viewPagerTab.addView(childView);
	}

	// 添加适配器
	public class MyAdapter extends FragmentStatePagerAdapter {

		public MyAdapter() {
			super(getSupportFragmentManager());
		}

		@Override
		public int getCount() {
			return tool_Views.length;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (tool_Views[0] == null) {
					averagePriceFragment = new AveragePriceFragment();
					tool_Views[0] = averagePriceFragment;
				}
				break;
			case 1:
				if (tool_Views[1] == null) {
					tradingFragment = new TradingFragment();
					tool_Views[1] = tradingFragment;
				}
				break;
			}

			return tool_Views[position];
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		case R.id.rl_wideprice:
			initImage(0);
			break;
		case R.id.rl_lastMonthNum:
			initImage(1);
			break;
		default:
			break;
		}

	}

}
