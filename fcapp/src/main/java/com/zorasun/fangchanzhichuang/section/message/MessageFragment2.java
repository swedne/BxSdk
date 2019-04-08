package com.zorasun.fangchanzhichuang.section.message;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.util.ViewPagerTab;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 消息界面
 * 
 */
public class MessageFragment2 extends BaseFragment implements OnClickListener {

	private TextView tvMessageAll;
	private TextView tvMessageMessage;
	private TextView tvMessageNotic;
	private int tabSelect;
	private ViewPager viewpager_news;
	private ViewPagerTab viewpg_news_tab;

	private AllMsgsFragment allMsgFragment;
	private NewsFragment newsFragment;
	private NoticeFragment noticeFragment;
	private MyAdapter mAdapter;
	public static int position;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message2, null);

		initTitleBar(view);
		initView(view);
		initData(view);
		return view;
	}

	private void initData(View view) {
		mAdapter = new MyAdapter();
		viewpager_news.setAdapter(mAdapter);
		viewpager_news.setOffscreenPageLimit(3);
		viewpager_news.setOnPageChangeListener(PagerListener);
	}

	@Override
	public void onResume() {
		super.onResume();
		setUpTab();
		initImage(position);

	}

	/**
	 * 设置tab
	 */
	@SuppressLint("ResourceAsColor")
	private void setUpTab() {
		viewpg_news_tab.setViewPager(viewpager_news);
		ImageView childView = new ImageView(getActivity());
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
		childView.setBackgroundColor(Color.parseColor("#0089fe"));
		childView.setLayoutParams(params);
		viewpg_news_tab.addView(childView);
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
				if (allMsgFragment != null) {
					allMsgFragment.initView();
				}
			} else if (arg0 == 1) {
				if (newsFragment != null) {
					newsFragment.initView();
				}
			} else if (arg0 == 2) {
				if (noticeFragment != null) {
					noticeFragment.initView();
				}
			}
		}

	};

	/**
	 * 初始化布局
	 */
	private void initImage(int index) {
		tvMessageAll.setTextColor(Color.parseColor("#919191"));
		tvMessageMessage.setTextColor(Color.parseColor("#919191"));
		tvMessageNotic.setTextColor(Color.parseColor("#919191"));
		if (index == 0) {
			tvMessageAll.setTextColor(Color.parseColor("#0089fe"));
		} else if (index == 1) {
			tvMessageMessage.setTextColor(Color.parseColor("#0089fe"));
		} else {
			tvMessageNotic.setTextColor(Color.parseColor("#0089fe"));
		}
		viewpager_news.setCurrentItem(index);
	}

	// 添加适配器
	public class MyAdapter extends FragmentStatePagerAdapter {
		public MyAdapter() {
			super(getChildFragmentManager());
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (allMsgFragment == null) {
					return allMsgFragment = new AllMsgsFragment();
				}
				break;
			case 1:
				if (newsFragment == null) {
					return newsFragment = new NewsFragment();
				}
				break;
			case 2:
				if (noticeFragment == null) {
					return noticeFragment = new NoticeFragment();
				}
				break;
			}
			return null;

		}
	}

	private void initView(View view) {
		viewpager_news = (ViewPager) view.findViewById(R.id.viewpager_news);
		viewpg_news_tab = (ViewPagerTab) view.findViewById(R.id.viewpg_news_tab);

		tvMessageAll = (TextView) view.findViewById(R.id.message_all);
		tvMessageMessage = (TextView) view.findViewById(R.id.message_message);
		tvMessageNotic = (TextView) view.findViewById(R.id.message_notic);
		view.findViewById(R.id.rl_message_all).setOnClickListener(this);
		view.findViewById(R.id.rl_message_msg).setOnClickListener(this);
		view.findViewById(R.id.rl_message_notice).setOnClickListener(this);

	}

	public void setSelectText() {
		tvMessageAll.setTextColor(Color.parseColor("#919191"));
		tvMessageMessage.setTextColor(Color.parseColor("#919191"));
		tvMessageNotic.setTextColor(Color.parseColor("#919191"));
		if (tabSelect == 1) {
			tvMessageAll.setTextColor(Color.parseColor("#0089fe"));
		} else if (tabSelect == 2) {
			tvMessageMessage.setTextColor(Color.parseColor("#0089fe"));
		} else {
			tvMessageNotic.setTextColor(Color.parseColor("#0089fe"));
		}
	}

	// 初始化标题
	private void initTitleBar(View view) {
		TextView title_name = (TextView) view.findViewById(R.id.title_name);
		title_name.setText("我的消息");
		ImageView back = (ImageView) view.findViewById(R.id.title_left);
		back.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 全部
		case R.id.rl_message_all:
			initImage(0);
			if (allMsgFragment != null) {
				allMsgFragment.initView();
			}
			break;
		// 消息
		case R.id.rl_message_msg:
			initImage(1);
			if (newsFragment != null) {
				newsFragment.initView();
			}
			break;
		// 公告
		case R.id.rl_message_notice:
			initImage(2);
			if (noticeFragment != null) {
				noticeFragment.initView();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void initView() {

	}
}
