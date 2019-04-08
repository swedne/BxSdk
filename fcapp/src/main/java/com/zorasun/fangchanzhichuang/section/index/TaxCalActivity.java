package com.zorasun.fangchanzhichuang.section.index;

import java.util.Arrays;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragmentActivity;
import com.zorasun.fangchanzhichuang.general.widget.TabsContainer;
import com.zorasun.fangchanzhichuang.general.widget.ViewPagerCustom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TaxCalActivity extends BaseFragmentActivity implements OnClickListener
{
	// Tab标题
	private List<String> mTitles = Arrays.asList("个人住宅", "个人非住宅");
	private ViewPagerCustom mViewPager;
	private TabsContainer mTabsContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tax_cal);
		initUI();
	}

	private void initUI()
	{
		initToolbar();
		initTabsContainer();
	}

	private void initToolbar()
	{
		findViewById(R.id.title_left).setOnClickListener(this);
		((TextView) findViewById(R.id.title_name)).setText("税费计算器");
	}

	private void initTabsContainer()
	{
		mViewPager = (ViewPagerCustom) findViewById(R.id.viewpager);
		mViewPager.setOffscreenPageLimit(0);
		mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager())
		{

			@Override
			public int getCount()
			{
				return mTitles.size();
			}

			@Override
			public Fragment getItem(int position)
			{
				return new TaxCalFragment(position);
			}
		});
		mTabsContainer = (TabsContainer) findViewById(R.id.tabs_container);
		mTabsContainer.setTabTitles(mTitles);
		// true为ViewPager不可滑动
		mTabsContainer.setViewPager(mViewPager, 0, false);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.title_left:// 返回
			finish();
			super.onBackPressed();
			break;

		default:
			break;
		}
	}
}