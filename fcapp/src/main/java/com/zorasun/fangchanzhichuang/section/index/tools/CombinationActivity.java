package com.zorasun.fangchanzhichuang.section.index.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.DateFormatUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.section.dialog.ListViewDialog;
import com.zorasun.fangchanzhichuang.section.dialog.ListViewDialog.SetOnClickListener;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 组合贷款fragment
 * 
 * @author chenzhifeng
 * @e-mail 731739299@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年11月5日10:42:20
 *
 */
@SuppressLint("InflateParams")
public class CombinationActivity extends BaseFragment implements OnClickListener, SetOnClickListener {
	private View view;
	LinearLayout mLy_house_all;
	private EditText mShangyezonge;
	private EditText mGongjijinzonge;
	private TextView mAnjienianshu;
	private EditText mGongjijinlilv;
	private EditText mShangyelilv;
	private TextView mSet;
	private RelativeLayout mTime_parent;
	LinearLayout mLl_time1;
	private TextView mTime1;
	private TextView mTime1_jizhun;
	private TextView mTime1_youhui10;
	private TextView mTime1_shangfu5;
	private TextView mTime1_shangfu10;
	private TextView mTime2;
	private TextView mTime2_jizhun;
	private TextView mTime2_youhui10;
	private TextView mTime2_shangfu5;
	private TextView mTime2_shangfu10;
	private TextView mDengebenxi;
	private TextView mDengebenjin;
	private Button mBtn_sure;
	ImageView mHelp;
	private TextView mTv_daikuanzonge;
	private TextView mTv_huankuanzonge;
	private TextView mTv_zhifulixi;
	private TextView mTv_anjienianshu;
	private TextView mTv_yuejunhuankuan;
	private LinearLayout mDenge_parent;
	private TextView mPrompt;
	private List<Double> shangyedaikuanlilv1 = new ArrayList<Double>();
	private List<Double> shangyedaikuanlilv2 = new ArrayList<Double>();
	private List<Double> gongjijinlilv1 = new ArrayList<Double>();
	private List<Double> gongjijinlilv2 = new ArrayList<Double>();
	private int nianshuPos = 19;
	/**
	 * 0 本息 1 本金
	 */
	private int isBenxiOrBenjin = 0;

	double daikuanzonge, gongjinzonge, shangyezonge, anjienianshu, yinhanglilv, huankuanzonge, lixizonge,
			yuejunhuankuan, shouyuefankuan, gongjijinyuelilv, gongjijinnianlilv, shangyeyuelilv, shangyenianlilv,
			daikuanqishu;
	private String benxiPromt = "每月还款额固定，所还总利息较多，适合收入稳定者。";
	private String benjinPromt = "每月还款额递减，所还总利息较低，前期还款额较大";
	private ImageView mPromt_top;
	private TextView mPromt_bottom;

	private void bindViews() {

		mLy_house_all = (LinearLayout) view.findViewById(R.id.ly_house_all);
		mShangyezonge = (EditText) view.findViewById(R.id.shangyezonge);
		mGongjijinzonge = (EditText) view.findViewById(R.id.gongjijinzonge);
		mAnjienianshu = (TextView) view.findViewById(R.id.anjienianshu);
		mGongjijinlilv = (EditText) view.findViewById(R.id.gongjijinlilv);
		mShangyelilv = (EditText) view.findViewById(R.id.shangyelilv);
		mSet = (TextView) view.findViewById(R.id.set);
		mTime_parent = (RelativeLayout) view.findViewById(R.id.time_parent);
		mLl_time1 = (LinearLayout) view.findViewById(R.id.ll_time1);
		mTime1 = (TextView) view.findViewById(R.id.time1);
		mTime1_jizhun = (TextView) view.findViewById(R.id.time1_jizhun);
		mTime1_youhui10 = (TextView) view.findViewById(R.id.time1_youhui10);
		mTime1_shangfu5 = (TextView) view.findViewById(R.id.time1_shangfu5);
		mTime1_shangfu10 = (TextView) view.findViewById(R.id.time1_shangfu10);
		mTime2 = (TextView) view.findViewById(R.id.time2);
		mTime2_jizhun = (TextView) view.findViewById(R.id.time2_jizhun);
		mTime2_youhui10 = (TextView) view.findViewById(R.id.time2_youhui10);
		mTime2_shangfu5 = (TextView) view.findViewById(R.id.time2_shangfu5);
		mTime2_shangfu10 = (TextView) view.findViewById(R.id.time2_shangfu10);
		mDengebenxi = (TextView) view.findViewById(R.id.dengebenxi);
		mDengebenjin = (TextView) view.findViewById(R.id.dengebenjin);
		mBtn_sure = (Button) view.findViewById(R.id.btn_sure);
		mHelp = (ImageView) view.findViewById(R.id.help);
		mTv_daikuanzonge = (TextView) view.findViewById(R.id.tv_daikuanzonge);
		mTv_huankuanzonge = (TextView) view.findViewById(R.id.tv_huankuanzonge);
		mTv_zhifulixi = (TextView) view.findViewById(R.id.tv_zhifulixi);
		mTv_anjienianshu = (TextView) view.findViewById(R.id.tv_anjienianshu);
		mTv_yuejunhuankuan = (TextView) view.findViewById(R.id.tv_yuejunhuankuan);
		mDenge_parent = (LinearLayout) view.findViewById(R.id.denge_parent);
		mPrompt = (TextView) view.findViewById(R.id.prompt);
		mPromt_bottom = (TextView) view.findViewById(R.id.promt_bottom);
		mPromt_top = (ImageView) view.findViewById(R.id.promt_top);
		view.findViewById(R.id.help).setOnClickListener(this);
		mTime1_jizhun.setOnClickListener(this);
		mTime1_youhui10.setOnClickListener(this);
		mTime1_shangfu5.setOnClickListener(this);
		mTime1_shangfu10.setOnClickListener(this);
		mTime2_jizhun.setOnClickListener(this);
		mTime2_youhui10.setOnClickListener(this);
		mTime2_shangfu5.setOnClickListener(this);
		mTime2_shangfu10.setOnClickListener(this);
		mSet.setOnClickListener(this);

		mDengebenjin.setOnClickListener(this);
		mDengebenxi.setOnClickListener(this);
		mAnjienianshu.setOnClickListener(this);
		mBtn_sure.setOnClickListener(this);
		tv_average = (TextView) view.findViewById(R.id.tv_average);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_combination, null);
		bindViews();
		mPrompt.setText(benxiPromt);
		// mShangyelilv.setText(shangyedaikuanlilv1.get(nianshuPos) + "");
		// mGongjijinlilv.setText(gongjijinlilv1.get(nianshuPos) + "");
		return view;
	}

	/**
	 * 0第一行基准利率； 1第一行优惠10 ；2第一行上浮5 ；3第一 行上浮10 ； 4第二行基准利率； 5第二行优惠10 ；6第二行上浮5
	 * ；7第二行上浮10 ；
	 */
	private int timeFlag = 0;
	private TextView tv_average;

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_sure:
			lixizonge = 0;
			huankuanzonge = 0;
			if (mShangyezonge.getText().toString().trim().equals("")) {
				ToastUtil.toastShow(getActivity(), "请输入商业贷款总额");
				break;
			}
			if (mGongjijinzonge.getText().toString().trim().equals("")) {
				ToastUtil.toastShow(getActivity(), "请输入公积金贷款总额");
				break;
			}
			gongjinzonge = Double.parseDouble(
					mGongjijinzonge.getText().toString().equals("") ? "0" : mGongjijinzonge.getText().toString())
					* 10000;
			shangyezonge = Double.parseDouble(
					mShangyezonge.getText().toString().equals("") ? "0" : mShangyezonge.getText().toString()) * 10000;
			daikuanzonge = gongjinzonge + shangyezonge;
			gongjijinnianlilv = Double.parseDouble(mGongjijinlilv.getText().toString()) / 100d;
			gongjijinyuelilv = gongjijinnianlilv / 12d;
			shangyenianlilv = Double.parseDouble(mShangyelilv.getText().toString()) / 100d;
			shangyeyuelilv = shangyenianlilv / 12d;
			if (isBenxiOrBenjin == 0) {
				benxi();
			} else {
				benjin();
			}
			CommonUtils.hideKeyboard(getActivity());
			break;
		case R.id.anjienianshu:
			ListViewDialog lvd = new ListViewDialog();
			lvd.ShowDialog(getActivity(), 0);
			lvd.SetOnClickListener(this);
			break;
		case R.id.anjiechengshu:
			ListViewDialog lvd1 = new ListViewDialog();
			lvd1.ShowDialog(getActivity(), 1);
			lvd1.SetOnClickListener(this);
			break;
		case R.id.dengebenjin:
			mDengebenjin.setTextColor(Color.WHITE);
			mDengebenxi.setTextColor(Color.BLACK);
			mDenge_parent.setBackgroundResource(R.mipmap.tool_univalent);
			isBenxiOrBenjin = 1;
			mPrompt.setText(benjinPromt);
			tv_average.setText("首月还款");
			break;
		case R.id.dengebenxi:
			mDengebenjin.setTextColor(Color.BLACK);
			mDengebenxi.setTextColor(Color.WHITE);
			mDenge_parent.setBackgroundResource(R.mipmap.tool_total);
			isBenxiOrBenjin = 0;
			mPrompt.setText(benxiPromt);
			tv_average.setText("月均还款");
			break;
		case R.id.time1_jizhun:
			timeFlag = 0;
			setTimeBg(mTime1_jizhun);

			mShangyelilv.setText(
					new BigDecimal(shangyedaikuanlilv1.get(nianshuPos)).setScale(2, RoundingMode.HALF_UP).doubleValue()
							+ "");
			// mGongjijinlilv.setText(
			// new BigDecimal(gongjijinlilv1.get(nianshuPos)).setScale(2,
			// RoundingMode.HALF_UP).doubleValue()
			// + "");
			break;
		case R.id.time1_youhui10:
			timeFlag = 1;
			setTimeBg(mTime1_youhui10);
			// mShangyelilv.setText(shangyedaikuanlilv1.get(nianshuPos) * 0.90 +
			// "");
			// mGongjijinlilv.setText(gongjijinlilv1.get(nianshuPos) + "");

			mShangyelilv.setText(new BigDecimal(shangyedaikuanlilv1.get(nianshuPos) * 0.90)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			// mGongjijinlilv.setText(
			// new BigDecimal(gongjijinlilv1.get(nianshuPos)).setScale(2,
			// RoundingMode.HALF_UP).doubleValue()
			// + "");
			break;
		case R.id.time1_shangfu5:
			timeFlag = 2;
			setTimeBg(mTime1_shangfu5);
			// mShangyelilv.setText(shangyedaikuanlilv1.get(nianshuPos) * 1.05 +
			// "");
			// mGongjijinlilv.setText(gongjijinlilv1.get(nianshuPos) + "");

			mShangyelilv.setText(new BigDecimal(shangyedaikuanlilv1.get(nianshuPos) * 1.05)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			// mGongjijinlilv.setText(
			// new BigDecimal(gongjijinlilv1.get(nianshuPos)).setScale(2,
			// RoundingMode.HALF_UP).doubleValue()
			// + "");
			break;
		case R.id.time1_shangfu10:
			timeFlag = 3;
			setTimeBg(mTime1_shangfu10);
			// mShangyelilv.setText(shangyedaikuanlilv1.get(nianshuPos) * 1.1 +
			// "");
			// mGongjijinlilv.setText(gongjijinlilv1.get(nianshuPos) + "");

			mShangyelilv.setText(new BigDecimal(shangyedaikuanlilv1.get(nianshuPos) * 1.1)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			// mGongjijinlilv.setText(
			// new BigDecimal(gongjijinlilv1.get(nianshuPos)).setScale(2,
			// RoundingMode.HALF_UP).doubleValue()
			// + "");
			break;
		case R.id.time2_jizhun:
			timeFlag = 4;
			setTimeBg(mTime2_jizhun);
			// mShangyelilv.setText(shangyedaikuanlilv2.get(nianshuPos) + "");
			// mGongjijinlilv.setText(gongjijinlilv2.get(nianshuPos) + "");

			mShangyelilv.setText(
					new BigDecimal(shangyedaikuanlilv2.get(nianshuPos)).setScale(2, RoundingMode.HALF_UP).doubleValue()
							+ "");
			// mGongjijinlilv.setText(
			// new BigDecimal(gongjijinlilv2.get(nianshuPos)).setScale(2,
			// RoundingMode.HALF_UP).doubleValue()
			// + "");
			break;
		case R.id.time2_youhui10:
			timeFlag = 5;
			setTimeBg(mTime2_youhui10);
			// mShangyelilv.setText(shangyedaikuanlilv2.get(nianshuPos) * 0.90 +
			// "");
			// mGongjijinlilv.setText(gongjijinlilv2.get(nianshuPos) * 0.90 +
			// "");

			mShangyelilv.setText(new BigDecimal(shangyedaikuanlilv2.get(nianshuPos) * 0.90)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			// mGongjijinlilv.setText(new
			// BigDecimal(gongjijinlilv2.get(nianshuPos) * 0.90)
			// .setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			break;
		case R.id.time2_shangfu5:
			timeFlag = 6;
			setTimeBg(mTime2_shangfu5);
			// mShangyelilv.setText(shangyedaikuanlilv2.get(nianshuPos) * 1.05 +
			// "");
			// mGongjijinlilv.setText(gongjijinlilv2.get(nianshuPos) * 1.05 +
			// "");

			mShangyelilv.setText(new BigDecimal(shangyedaikuanlilv2.get(nianshuPos) * 1.05)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			// mGongjijinlilv.setText(new
			// BigDecimal(gongjijinlilv2.get(nianshuPos) * 1.05)
			// .setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			break;
		case R.id.time2_shangfu10:
			timeFlag = 7;
			setTimeBg(mTime2_shangfu10);
			// mShangyelilv.setText(shangyedaikuanlilv2.get(nianshuPos) * 1.1 +
			// "");
			// mGongjijinlilv.setText(gongjijinlilv2.get(nianshuPos) * 1.1 +
			// "");

			mShangyelilv.setText(new BigDecimal(shangyedaikuanlilv2.get(nianshuPos) * 1.1)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			// mGongjijinlilv.setText(
			// new BigDecimal(gongjijinlilv2.get(nianshuPos) * 1.1).setScale(2,
			// RoundingMode.HALF_UP).doubleValue()
			// + "");
			break;
		case R.id.set:
			if (mTime_parent.getVisibility() == View.VISIBLE) {
				mTime_parent.setVisibility(View.GONE);
			} else {
				mTime_parent.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.help:
			if (mPromt_bottom.getVisibility() == View.VISIBLE) {
				mPromt_bottom.setVisibility(View.GONE);
				mPromt_top.setVisibility(View.GONE);
				view.findViewById(R.id.btn_sure_line).setVisibility(View.VISIBLE);
			} else {
				mPromt_bottom.setVisibility(View.VISIBLE);
				mPromt_top.setVisibility(View.VISIBLE);
				view.findViewById(R.id.btn_sure_line).setVisibility(View.GONE);
			}
			break;
		}

	}

	double yiguihuanbenjin = 0;

	/**
	 * 贷款本金
	 */
	public void benjin() {
		// 每月还款公式
		// 每月还款金额= （贷款本金/还款月数）+（本金—已归还本金累计额）×每月利率

		// 贷款期数
		daikuanqishu = (nianshuPos + 1) * 12;

		// 公积金首月还款
		double d = (gongjinzonge / daikuanqishu) + (gongjinzonge - yiguihuanbenjin) * gongjijinyuelilv;
		// 商业贷款首月还款
		double e = (shangyezonge / daikuanqishu) + (shangyezonge - yiguihuanbenjin) * shangyeyuelilv;
		yuejunhuankuan = d + e;

		// 还款总额
		huankuanzonge = ((daikuanqishu + 1) * shangyezonge * shangyeyuelilv / 2d + shangyezonge)
				+ ((daikuanqishu + 1) * gongjinzonge * gongjijinyuelilv / 2d + gongjinzonge);
		// 利息总额
		lixizonge = huankuanzonge - daikuanzonge;

		mTv_daikuanzonge.setText(new DecimalFormat("0.00").format(daikuanzonge) + "元");
		mTv_huankuanzonge.setText(new DecimalFormat("0.00").format(huankuanzonge) + "元");
		mTv_zhifulixi.setText(new DecimalFormat("0.00").format(lixizonge) + "元");
		mTv_anjienianshu.setText(mAnjienianshu.getText().toString());
		mTv_yuejunhuankuan.setText(new DecimalFormat("0.00").format(yuejunhuankuan) + "元");
	}

	/**
	 * 贷款本息
	 */
	public void benxi() {

		// 月均还款
		yuejunhuankuan = ((gongjinzonge * gongjijinyuelilv * Math.pow(1 + gongjijinyuelilv, (nianshuPos + 1) * 12))
				/ (Math.pow(1 + gongjijinyuelilv, (nianshuPos + 1) * 12) - 1))
				+ ((shangyezonge * shangyeyuelilv * Math.pow(1 + shangyeyuelilv, (nianshuPos + 1) * 12))
						/ (Math.pow(1 + shangyeyuelilv, (nianshuPos + 1) * 12) - 1));
		// 还款总额
		huankuanzonge = yuejunhuankuan * ((nianshuPos + 1) * 12);
		// 利息总额
		lixizonge = huankuanzonge - daikuanzonge;

		mTv_daikuanzonge.setText(new DecimalFormat("0.00").format(daikuanzonge) + "元");
		mTv_huankuanzonge.setText(new DecimalFormat("0.00").format(huankuanzonge) + "元");
		mTv_zhifulixi.setText(new DecimalFormat("0.00").format(lixizonge) + "元");
		mTv_anjienianshu.setText(mAnjienianshu.getText().toString());
		mTv_yuejunhuankuan.setText(new DecimalFormat("0.00").format(yuejunhuankuan) + "元");
	}

	public void addData(List<Double> list1, List<Double> list2, List<Double> list3, List<Double> list4, long l,
			long m) {
		shangyedaikuanlilv1.clear();
		shangyedaikuanlilv2.clear();
		shangyedaikuanlilv1.addAll(list1);
		shangyedaikuanlilv2.addAll(list2);
		gongjijinlilv1.clear();
		gongjijinlilv2.clear();
		gongjijinlilv1.addAll(list3);
		gongjijinlilv2.addAll(list4);
		// mShangyelilv.setText(list1.get(nianshuPos) + "");
		// mGongjijinlilv.setText(list3.get(nianshuPos) + "");
		mShangyelilv
				.setText(new BigDecimal(list1.get(nianshuPos)).setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
		mGongjijinlilv
				.setText(new BigDecimal(list3.get(nianshuPos)).setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
		mTime1.setText(DateFormatUtils.formatWithYMD(l));
		mTime2.setText(DateFormatUtils.formatWithYMD(m));
	}

	private void setTimeBg(TextView v) {
		mTime1_jizhun.setBackgroundColor(getResources().getColor(R.color.tool_btn_bg));
		mTime1_shangfu10.setBackgroundColor(getResources().getColor(R.color.tool_btn_bg));
		mTime1_shangfu5.setBackgroundColor(getResources().getColor(R.color.tool_btn_bg));
		mTime1_youhui10.setBackgroundColor(getResources().getColor(R.color.tool_btn_bg));
		mTime2_jizhun.setBackgroundColor(getResources().getColor(R.color.tool_btn_bg));
		mTime2_shangfu10.setBackgroundColor(getResources().getColor(R.color.tool_btn_bg));
		mTime2_shangfu5.setBackgroundColor(getResources().getColor(R.color.tool_btn_bg));
		mTime2_youhui10.setBackgroundColor(getResources().getColor(R.color.tool_btn_bg));
		v.setBackgroundColor(getResources().getColor(R.color.tool_btn_bg_p));
	}

	@Override
	public void getTime(String time, int position) {
		this.nianshuPos = position;
		mAnjienianshu.setText(time);
		double shangyePrice = 0;
		double gongjijinPrice = 0;
		switch (timeFlag) {
		case 0:
			shangyePrice = shangyedaikuanlilv1.get(position);
			// gongjijinPrice = gongjijinlilv1.get(position);
			break;
		case 1:
			shangyePrice = shangyedaikuanlilv1.get(position) * 0.95;
			// gongjijinPrice = gongjijinlilv1.get(position) * 0.95;
			break;
		case 2:
			shangyePrice = shangyedaikuanlilv1.get(position) * 1.05;
			// gongjijinPrice = gongjijinlilv1.get(position) * 1.05;
			break;
		case 3:
			shangyePrice = shangyedaikuanlilv1.get(position) * 1.10;
			// gongjijinPrice = gongjijinlilv1.get(position) * 1.10;
			break;
		case 4:
			shangyePrice = shangyedaikuanlilv2.get(position);
			// gongjijinPrice = gongjijinlilv2.get(position);
			break;
		case 5:
			shangyePrice = shangyedaikuanlilv2.get(position) * 0.95;
			// gongjijinPrice = gongjijinlilv2.get(position) * 0.95;
			break;
		case 6:
			shangyePrice = shangyedaikuanlilv2.get(position) * 1.05;
			// gongjijinPrice = gongjijinlilv2.get(position) * 1.05;
			break;
		case 7:
			shangyePrice = shangyedaikuanlilv2.get(position) * 1.10;
			// gongjijinPrice = gongjijinlilv2.get(position) * 1.10;
			break;
		default:
			break;
		}
		// mShangyelilv.setText(shangyePrice + "");
		// mGongjijinlilv.setText(gongjijinPrice + "");
		gongjijinPrice = gongjijinlilv1.get(position);
		mShangyelilv.setText(new BigDecimal(shangyePrice).setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
		mGongjijinlilv.setText(new BigDecimal(gongjijinPrice).setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
	}

	@Override
	public void getChengshu(String str, int position) {

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}
}
