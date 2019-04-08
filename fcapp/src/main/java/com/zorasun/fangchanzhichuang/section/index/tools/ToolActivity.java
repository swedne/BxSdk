package com.zorasun.fangchanzhichuang.section.index.tools;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.base.BaseFragmentActivity;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.util.ViewPagerTab;
import com.zorasun.fangchanzhichuang.section.index.IndexApi;
import com.zorasun.fangchanzhichuang.section.index.IndexApi.RateCallback;
import com.zorasun.fangchanzhichuang.section.index.entity.RateEntity;

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

/**
 * 工具箱页面
 *
 * @author chenzhifeng
 * @version v1.0
 * @e-mail 731739299@qq.com
 * @copyright 2010-2015
 * @create-time 2015年11月5日09:41:19
 */
public class ToolActivity extends BaseFragmentActivity implements OnClickListener {
    BaseFragment[] tool_Views = new BaseFragment[3];
    MyAdapter mAdapter;// viewpager适配器
    ViewPager viewpager_tool;
    ViewPagerTab viewpg_tool_tab;
    BusinessActivity bussinessFragment;
    FundActivity fundFragment;
    CombinationActivity combinationFragment;
    TextView tv_business_tab, tv_fund_tab, tv_combination_tab;
    private List<Double> shangyedaikuanlilv1 = new ArrayList<Double>();
    private List<Double> shangyedaikuanlilv2 = new ArrayList<Double>();
    private List<Double> gongjijinlilv1 = new ArrayList<Double>();
    private List<Double> gongjijinlilv2 = new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);
        initUi();
        mAdapter = new MyAdapter();
        viewpager_tool.setAdapter(mAdapter);
        viewpager_tool.setOnPageChangeListener(PagerListener);
        viewpager_tool.setOffscreenPageLimit(3);
        initImage(0);
        setUpTab();
        requestData();
    }

    private void requestData() {
        IndexApi.getInstance().requestRate(this, new RateCallback() {

            @Override
            public void onNetworkError() {
                ToastUtil.toastShow(getApplicationContext(), "获取当前利率失败，请重试！");
                finish();
            }

            @Override
            public void onFailure(int code, String msg) {
                ToastUtil.toastShow(getApplicationContext(), "获取当前利率失败，请重试！");
                finish();
            }

            @Override
            public void onSuccess(int code, String msg, List<RateEntity> entitys) {
                if (entitys == null) {
                    ToastUtil.toastShow(getApplicationContext(), "获取当前利率失败，请重试！");
                    finish();
                }
                for (int i = 0; i < 30; i++) {
                    if (i < 1) {
                        shangyedaikuanlilv1.add(entitys.get(0).getOneYear());
                        gongjijinlilv1.add(entitys.get(0).getGGJFiveYearDown());
                    } else if (i < 3) {
                        shangyedaikuanlilv1.add(entitys.get(0).getOneToThree());
                        gongjijinlilv1.add(entitys.get(0).getGGJFiveYearDown());
                    } else if (i < 5) {
                        shangyedaikuanlilv1.add(entitys.get(0).getThreeToFive());
                        gongjijinlilv1.add(entitys.get(0).getGGJFiveYearDown());
                    } else {
                        shangyedaikuanlilv1.add(entitys.get(0).getFiveYear());
                        gongjijinlilv1.add(entitys.get(0).getGGJFiveYearUp());
                    }
                }
                for (int i = 0; i < 30; i++) {
                    if (i < 1) {
                        shangyedaikuanlilv2.add(entitys.get(1).getOneYear());
                        gongjijinlilv2.add(entitys.get(1).getGGJFiveYearDown());
                    } else if (i < 3) {
                        shangyedaikuanlilv2.add(entitys.get(1).getOneToThree());
                        gongjijinlilv2.add(entitys.get(1).getGGJFiveYearDown());
                    } else if (i < 5) {
                        shangyedaikuanlilv2.add(entitys.get(1).getThreeToFive());
                        gongjijinlilv2.add(entitys.get(1).getGGJFiveYearDown());
                    } else {
                        shangyedaikuanlilv2.add(entitys.get(1).getFiveYear());
                        gongjijinlilv2.add(entitys.get(1).getGGJFiveYearUp());
                    }
                }
                bussinessFragment.addData(shangyedaikuanlilv1, shangyedaikuanlilv2, entitys.get(0).getChangeDate(),
                        entitys.get(1).getChangeDate());
                fundFragment.addData(gongjijinlilv1, gongjijinlilv2, entitys.get(0).getChangeDate(),
                        entitys.get(1).getChangeDate());
                combinationFragment.addData(shangyedaikuanlilv1, shangyedaikuanlilv2, gongjijinlilv1, gongjijinlilv2,
                        entitys.get(0).getChangeDate(), entitys.get(1).getChangeDate());
            }
        });
    }

    /**
     * 初始化Ui界面
     */
    private void initUi() {
        findViewById(R.id.title_left).setOnClickListener(this);
        ((TextView) findViewById(R.id.title_name)).setText(getResources().getString(R.string.home_loan_calculator));// 设置标题
        viewpager_tool = (ViewPager) findViewById(R.id.viewpager_tool);
        viewpg_tool_tab = (ViewPagerTab) findViewById(R.id.viewpg_tool_tab);

        tv_business_tab = (TextView) findViewById(R.id.tv_business_tab);
        tv_fund_tab = (TextView) findViewById(R.id.tv_fund_tab);
        tv_combination_tab = (TextView) findViewById(R.id.tv_combination_tab);
        tv_business_tab.setOnClickListener(this);
        tv_fund_tab.setOnClickListener(this);
        tv_combination_tab.setOnClickListener(this);
    }

    /**
     * 设置tab
     */
    private void setUpTab() {
        viewpg_tool_tab.setViewPager(viewpager_tool);
        ImageView childView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        childView.setBackgroundColor(Color.parseColor("#0089fe"));
        childView.setLayoutParams(params);
        viewpg_tool_tab.addView(childView);
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
        }

    };

    /**
     * 初始化布局
     */
    private void initImage(int index) {
        tv_business_tab.setTextColor(Color.parseColor("#919191"));
        tv_fund_tab.setTextColor(Color.parseColor("#919191"));
        tv_combination_tab.setTextColor(Color.parseColor("#919191"));
        if (index == 0) {
            tv_business_tab.setTextColor(Color.parseColor("#0089fe"));
        } else if (index == 1) {
            tv_fund_tab.setTextColor(Color.parseColor("#0089fe"));
        } else {
            tv_combination_tab.setTextColor(Color.parseColor("#0089fe"));
        }
        viewpager_tool.setCurrentItem(index);

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
                        bussinessFragment = new BusinessActivity();
                        tool_Views[0] = bussinessFragment;
                    }
                    break;
                case 1:
                    if (tool_Views[1] == null) {
                        fundFragment = new FundActivity();
                        tool_Views[1] = fundFragment;
                    }
                    break;
                case 2:
                    if (tool_Views[2] == null) {
                        combinationFragment = new CombinationActivity();
                        tool_Views[2] = combinationFragment;
                    }
                    break;
            }

            return tool_Views[position];
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:// 后退
                finish();
                break;

            case R.id.tv_business_tab:
                initImage(0);
                break;

            case R.id.tv_fund_tab:
                initImage(1);
                break;

            case R.id.tv_combination_tab:
                initImage(2);
                break;
            default:
                break;
        }
    }

}
