package com.zorasun.fangchanzhichuang.section.index;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragmentActivity;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
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

public class SecondHouseActivity2 extends BaseFragmentActivity implements OnClickListener {
    private TextView tv_secondhouse;
    private TextView tv_shangpu;
    private TextView tv_xiezilou;
    private MyAdapter mAdapter;
    private ViewPager viewpager_house;
    private ViewPagerTab viewpg_house_tab;
    public ZhuZaiFragment zhuZaiFragment;
    public ShangPuFragment shangPuFragment;
    public XieZiLouFragment xieZiLouFragment;
    public ChangFangFragment changFangFragment;
    private TextView tv_changfang;
    protected static int position;
    public static int intExtra;
    public static int brokerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondhouse2);
        intExtra = getIntent().getIntExtra("house", -1);
        brokerId = getIntent().getIntExtra("brokerId", -1);
        initUI();
        initData();
        setUpTab();
        initImage(0);
    }

    /**
     * 设置tab
     */
    @SuppressLint("ResourceAsColor")
    private void setUpTab() {
        viewpg_house_tab.setViewPager(viewpager_house);
        viewpg_house_tab.setTabNum(4);
        ImageView childView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        childView.setBackgroundColor(Color.parseColor("#919191"));
        childView.setLayoutParams(params);
        viewpg_house_tab.addView(childView);
    }

    private void initData() {
        mAdapter = new MyAdapter();
        viewpager_house.setAdapter(mAdapter);
        viewpager_house.setOffscreenPageLimit(4);
        viewpager_house.setOnPageChangeListener(PagerListener);
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
                if (zhuZaiFragment != null) {
                    zhuZaiFragment.initView();
                }
            } else if (arg0 == 1) {
                if (shangPuFragment != null) {
                    shangPuFragment.initView();
                }
            } else if (arg0 == 2) {
                if (xieZiLouFragment != null) {
                    xieZiLouFragment.initView();
                }
            } else if (arg0 == 3) {
                if (changFangFragment != null) {
                    changFangFragment.initView();
                }
            }
        }

    };

    /**
     * 初始化布局
     */
    private void initImage(int index) {
        tv_secondhouse.setTextColor(Color.parseColor("#919191"));
        tv_shangpu.setTextColor(Color.parseColor("#919191"));
        tv_xiezilou.setTextColor(Color.parseColor("#919191"));
        tv_changfang.setTextColor(Color.parseColor("#919191"));
        if (index == 0) {
            tv_secondhouse.setTextColor(Color.parseColor("#919191"));
        } else if (index == 1) {
            tv_shangpu.setTextColor(Color.parseColor("#919191"));
        } else if (index == 2) {
            tv_xiezilou.setTextColor(Color.parseColor("#919191"));
        } else {
            tv_changfang.setTextColor(Color.parseColor("#919191"));
        }
        viewpager_house.setCurrentItem(index);
    }

    // 添加适配器
    public class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (zhuZaiFragment == null) {
                        return zhuZaiFragment = new ZhuZaiFragment();
                    }
                    break;
                case 1:
                    if (shangPuFragment == null) {
                        return shangPuFragment = new ShangPuFragment();
                    }
                    break;
                case 2:
                    if (xieZiLouFragment == null) {
                        return xieZiLouFragment = new XieZiLouFragment();
                    }
                    break;
                case 3:
                    if (changFangFragment == null) {
                        return changFangFragment = new ChangFangFragment();
                    }
                    break;
            }
            return null;

        }
    }

    private void initUI() {
        viewpager_house = (ViewPager) findViewById(R.id.viewpager_house);
        viewpg_house_tab = (ViewPagerTab) findViewById(R.id.viewpg_house_tab);
        TextView tvTitle = (TextView) findViewById(R.id.title_name);
        if (intExtra == SystemConstant.JINGJIREN_SECONDHOUSE) {
            tvTitle.setText("二手房源");
        } else {
            tvTitle.setText("出租房源");
        }
        LinearLayout ll_secondhouse = (LinearLayout) findViewById(R.id.ll_secondhouse);
        LinearLayout ll_shangpu = (LinearLayout) findViewById(R.id.ll_shangpu);
        LinearLayout ll_xiezilou = (LinearLayout) findViewById(R.id.ll_xiezilou);
        LinearLayout ll_changfang = (LinearLayout) findViewById(R.id.ll_changfang);
        tv_secondhouse = (TextView) findViewById(R.id.tv_secondhouse);
        tv_shangpu = (TextView) findViewById(R.id.tv_shangpu);
        tv_xiezilou = (TextView) findViewById(R.id.tv_xiezilou);
        tv_changfang = (TextView) findViewById(R.id.tv_changfang);
        ll_secondhouse.setOnClickListener(this);
        ll_shangpu.setOnClickListener(this);
        ll_xiezilou.setOnClickListener(this);
        ll_changfang.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_secondhouse:
                initImage(0);
                if (zhuZaiFragment != null) {
                    zhuZaiFragment.initView();
                }
                break;
            case R.id.ll_shangpu:
                initImage(1);
                if (shangPuFragment != null) {
                    shangPuFragment.initView();
                }
                break;
            case R.id.ll_xiezilou:
                initImage(2);
                if (xieZiLouFragment != null) {
                    xieZiLouFragment.initView();
                }
                break;
            case R.id.ll_changfang:
                initImage(3);
                if (changFangFragment != null) {
                    changFangFragment.initView();
                }
                break;
            default:
                break;
        }
    }

}
