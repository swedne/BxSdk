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

@SuppressLint("ResourceAsColor")
public class MyCollectActivity2 extends BaseFragmentActivity implements OnClickListener {

	private TextView tv_coolect_secondhouse;
	private TextView tv_coolect_rendhouse;
	private TextView tv_coolect_newhouse;
	private ViewPager viewpager_news;
	private ViewPagerTab viewpg_news_tab;
	private MyAdapter mAdapter;
	public SecCollectFragment secFragment;
	public RentCollectFragment rentFragment;
	public NewCollectFragment newFragment;
	protected static int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mycollection2);
		initview();
		initData();
		setUpTab();
		initImage(0);
	}

	/**
	 * 设置tab
	 */
	@SuppressLint("ResourceAsColor")
	private void setUpTab() {
		viewpg_news_tab.setViewPager(viewpager_news);
		ImageView childView = new ImageView(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
		childView.setBackgroundColor(Color.parseColor("#0089fe"));
		childView.setLayoutParams(params);
		viewpg_news_tab.addView(childView);
	}

	private void initData() {
		mAdapter = new MyAdapter();
		viewpager_news.setAdapter(mAdapter);
		viewpager_news.setOffscreenPageLimit(3);
		viewpager_news.setOnPageChangeListener(PagerListener);
	}

	// 添加适配器
	public class MyAdapter extends FragmentStatePagerAdapter {
		public MyAdapter() {
			super(getSupportFragmentManager());
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (secFragment == null) {
					return secFragment = new SecCollectFragment();
				}
				break;
			case 1:
				if (rentFragment == null) {
					return rentFragment = new RentCollectFragment();
				}
				break;
			case 2:
				if (newFragment == null) {
					return newFragment = new NewCollectFragment();
				}
				break;
			}
			return null;

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
		public void onPageSelected(int arg0) {
			initImage(arg0);
			position = arg0;
			if (arg0 == 0) {
				if (secFragment != null) {
					secFragment.initView();
				}
			} else if (arg0 == 1) {
				if (rentFragment != null) {
					rentFragment.initView();
				}
			} else if (arg0 == 2) {
				if (newFragment != null) {
					newFragment.initView();
				}
			}
		}

	};

	/**
	 * 初始化布局
	 */
	private void initImage(int index) {
		tv_coolect_secondhouse.setTextColor(Color.parseColor("#919191"));
		tv_coolect_rendhouse.setTextColor(Color.parseColor("#919191"));
		tv_coolect_newhouse.setTextColor(Color.parseColor("#919191"));
		if (index == 0) {
			tv_coolect_secondhouse.setTextColor(Color.parseColor("#0089fe"));
		} else if (index == 1) {
			tv_coolect_rendhouse.setTextColor(Color.parseColor("#0089fe"));
		} else {
			tv_coolect_newhouse.setTextColor(Color.parseColor("#0089fe"));
		}
		viewpager_news.setCurrentItem(index);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initview() {

		viewpager_news = (ViewPager) findViewById(R.id.viewpager_collection);
		viewpg_news_tab = (ViewPagerTab) findViewById(R.id.viewpg_collection_tab);

		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("我的收藏");
		tv_coolect_secondhouse = (TextView) findViewById(R.id.tv_coolect_secondhouse);
		tv_coolect_rendhouse = (TextView) findViewById(R.id.tv_coolect_rendhouse);
		tv_coolect_newhouse = (TextView) findViewById(R.id.tv_coolect_newhouse);

		LinearLayout ll_coolect_newhouse = (LinearLayout) findViewById(R.id.ll_coolect_newhouse);
		LinearLayout ll_coolect_secondhouse = (LinearLayout) findViewById(R.id.ll_coolect_secondhouse);
		LinearLayout ll_coolect_rendhouse = (LinearLayout) findViewById(R.id.ll_coolect_rendhouse);
		ll_coolect_secondhouse.setOnClickListener(this);
		ll_coolect_rendhouse.setOnClickListener(this);
		ll_coolect_newhouse.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ll_coolect_secondhouse:
			initImage(0);
			if (secFragment != null) {
				secFragment.initView();
			}
			break;
		case R.id.ll_coolect_rendhouse:
			initImage(1);
			if (rentFragment != null) {
				rentFragment.initView();
			}
			break;
		case R.id.ll_coolect_newhouse:
			if (newFragment != null) {
				newFragment.initView();
			}
			initImage(2);
			break;
		default:
			break;
		}
	}

}
