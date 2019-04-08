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
 * 公积金贷款fragment
 * 
 * @author chenzhifeng
 * @e-mail 731739299@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年11月5日10:12:20
 *
 */
public class FundActivity extends BaseFragment implements OnClickListener, SetOnClickListener {

	private View view;
	/**
	 * 0 本息 1 本金
	 */
	private int isBenxiOrBenjin = 0;
	/**
	 * 0 总价 1 单价
	 */
	private int isZongjiaOrDanjia = 0;

	LinearLayout mLy_house_all;
	private TextView mZongjiajisuan;
	private TextView mDanjiajisuan;
	private LinearLayout mLayout_total;
	private EditText mDaikuanzonge;
	private LinearLayout mLayout_univalent;
	private TextView mAnjienianshu;
	private EditText mYinhanlilv;
	private TextView mDengebenxi;
	private TextView mDengebenjin;
	private Button mBtn_sure;
	ImageView mHelp;
	private RelativeLayout mRl_shouqihuankuan, mRl_fangwuzongjia;
	private TextView mTv_shouqihuankuan;
	private TextView mTv_fangwuzongjia;
	private TextView mTv_daikuanzonge;
	private TextView mTv_huankuanzonge;
	private TextView mTv_zhifulixi;
	private TextView mTv_anjienianshu;
	private TextView mTv_yuejunhuankuan;
	private RelativeLayout mRl_yuejunhuankuan;
	private LinearLayout mDenge_parent;
	private LinearLayout mJisuan_parent;
	double daikuanzonge, fangjiazonge, shouqihuankuan, anjienianshu, yinhanglilv, huankuanzonge, lixizonge,
			yuejunhuankuan, shouyuehuankuan, moyuehuankuan, yuelilv, nianlilv, daikuanqishu;

	private List<Double> shangyedaikuanlilv1 = new ArrayList<Double>();
	private List<Double> shangyedaikuanlilv2 = new ArrayList<Double>();
	private int nianshuPos = 19;
	private int chenshuPos = 5;
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
	private TextView set;
	private RelativeLayout mTime_parent;

	private EditText mDanjia, mMianji;
	private TextView mAnjiechengshu;
	private ImageView mPromt_top;
	private TextView mPromt_bottom;
	private TextView mPromt;

	private RelativeLayout mRl_shouyue_huankuan;
	private TextView mTv_shouyue_huankuan;
	private RelativeLayout mRl_moyue_huankuan;
	private TextView mTv_moyue_huankuan;

	private void bindViews() {

		mLy_house_all = (LinearLayout) view.findViewById(R.id.ly_house_all);
		mZongjiajisuan = (TextView) view.findViewById(R.id.zongjiajisuan);
		mDanjiajisuan = (TextView) view.findViewById(R.id.danjiajisuan);
		mLayout_total = (LinearLayout) view.findViewById(R.id.layout_total);
		mDaikuanzonge = (EditText) view.findViewById(R.id.daikuanzonge);
		mLayout_univalent = (LinearLayout) view.findViewById(R.id.layout_univalent);
		mAnjienianshu = (TextView) view.findViewById(R.id.anjienianshu);
		mYinhanlilv = (EditText) view.findViewById(R.id.yinhanlilv);
		mDengebenxi = (TextView) view.findViewById(R.id.dengebenxi);
		mDengebenjin = (TextView) view.findViewById(R.id.dengebenjin);
		mBtn_sure = (Button) view.findViewById(R.id.btn_sure);
		mHelp = (ImageView) view.findViewById(R.id.help);
		mDenge_parent = (LinearLayout) view.findViewById(R.id.dengen_parent);

		mJisuan_parent = (LinearLayout) view.findViewById(R.id.jisuan_parent);
		mTv_daikuanzonge = (TextView) view.findViewById(R.id.tv_daikuanzonge);
		mTv_huankuanzonge = (TextView) view.findViewById(R.id.tv_huankuanzonge);
		mTv_zhifulixi = (TextView) view.findViewById(R.id.tv_zhifulixi);
		mTv_anjienianshu = (TextView) view.findViewById(R.id.tv_anjienianshu);
		mTv_yuejunhuankuan = (TextView) view.findViewById(R.id.tv_yuejunhuankuan);
		mRl_fangwuzongjia = (RelativeLayout) view.findViewById(R.id.rl_fangwuzongjia);
		mRl_shouqihuankuan = (RelativeLayout) view.findViewById(R.id.rl_shouqihuankuan);
		mTv_fangwuzongjia = (TextView) view.findViewById(R.id.tv_fangwuzongjia);
		mTv_shouqihuankuan = (TextView) view.findViewById(R.id.tv_shouqihuankuan);

		mTime1 = (TextView) view.findViewById(R.id.time1);
		mTime1_jizhun = (TextView) view.findViewById(R.id.time1_jizhun);
		mTime1_youhui10 = (TextView) view.findViewById(R.id.time1_youhui10);
		mTime1_shangfu5 = (TextView) view.findViewById(R.id.time1_shangfu5);
		mTime1_shangfu10 = (TextView) view.findViewById(R.id.time1_shangfu10);
		mTime2 = (TextView) view.findViewById(R.id.time2);
		mTime2_youhui10 = (TextView) view.findViewById(R.id.time2_youhui10);
		mTime2_shangfu5 = (TextView) view.findViewById(R.id.time2_shangfu5);
		mTime2_shangfu10 = (TextView) view.findViewById(R.id.time2_shangfu10);
		set = (TextView) view.findViewById(R.id.set);
		mTime_parent = (RelativeLayout) view.findViewById(R.id.time_parent);
		mDanjia = (EditText) view.findViewById(R.id.danjia);
		mMianji = (EditText) view.findViewById(R.id.mianji);
		mAnjiechengshu = (TextView) view.findViewById(R.id.anjiechengshu);
		mPromt_bottom = (TextView) view.findViewById(R.id.promt_bottom);
		mPromt_top = (ImageView) view.findViewById(R.id.promt_top);
		view.findViewById(R.id.help).setOnClickListener(this);
		mTime2_jizhun = (TextView) view.findViewById(R.id.time2_jizhun);
		mPromt = (TextView) view.findViewById(R.id.promt);

		mRl_shouyue_huankuan = (RelativeLayout) view.findViewById(R.id.rl_shouyue_huankuan);
		mTv_shouyue_huankuan = (TextView) view.findViewById(R.id.tv_shouyue_huankuan);
		mRl_moyue_huankuan = (RelativeLayout) view.findViewById(R.id.rl_moyue_huankuan);
		mTv_moyue_huankuan = (TextView) view.findViewById(R.id.tv_moyue_huankuan);
		mRl_yuejunhuankuan = (RelativeLayout) view.findViewById(R.id.rl_yuejunhuankuan);
		mAnjiechengshu.setOnClickListener(this);
		mTime1_jizhun.setOnClickListener(this);
		mTime1_youhui10.setOnClickListener(this);
		mTime1_shangfu5.setOnClickListener(this);
		mTime1_shangfu10.setOnClickListener(this);
		mTime2_jizhun.setOnClickListener(this);
		mTime2_youhui10.setOnClickListener(this);
		mTime2_shangfu5.setOnClickListener(this);
		mTime2_shangfu10.setOnClickListener(this);
		set.setOnClickListener(this);

		mZongjiajisuan.setOnClickListener(this);
		mDanjiajisuan.setOnClickListener(this);
		mDengebenjin.setOnClickListener(this);
		mDengebenxi.setOnClickListener(this);
		mAnjienianshu.setOnClickListener(this);
		mBtn_sure.setOnClickListener(this);
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_business, null);
		bindViews();
		// mYinhanlilv.setText(shangyedaikuanlilv1.get(nianshuPos) + "");
		return view;
	}

	public void addData(List<Double> list1, List<Double> list2, long l, long m) {
		shangyedaikuanlilv1.clear();
		shangyedaikuanlilv2.clear();
		shangyedaikuanlilv1.addAll(list1);
		shangyedaikuanlilv2.addAll(list2);
		mYinhanlilv.setText(new BigDecimal(list1.get(nianshuPos)).setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
		mTime1.setText(DateFormatUtils.formatWithYMD(l));
		mTime2.setText(DateFormatUtils.formatWithYMD(m));
	}

	/**
	 * 贷款本金
	 */
	public void benjin() {
		daikuanqishu = (nianshuPos + 1) * 12;
		nianlilv = Double.parseDouble(mYinhanlilv.getText().toString()) / 100;
		yuelilv = nianlilv / 12;
		if (isZongjiaOrDanjia == 0) {
			daikuanzonge = Double.parseDouble(mDaikuanzonge.getText().toString()) * 10000;

		} else {

			fangjiazonge = Double
					.parseDouble(mMianji.getText().toString().equals("") ? "0" : mMianji.getText().toString())
					* Double.parseDouble(mDanjia.getText().toString().equals("") ? "0" : mDanjia.getText().toString());
			daikuanzonge = fangjiazonge * ((chenshuPos + 2) / 10d);
			shouqihuankuan = fangjiazonge - daikuanzonge;
		}
		shouyuehuankuan = daikuanzonge / daikuanqishu + daikuanzonge * yuelilv;
		moyuehuankuan = daikuanzonge / daikuanqishu + daikuanzonge / daikuanqishu * yuelilv;
		// 贷款总额/（贷款年限*12）+贷款总额*（年利率/12）
		// }

		huankuanzonge = (daikuanqishu + 1) * daikuanzonge * yuelilv / 2d + daikuanzonge;
		lixizonge = huankuanzonge - daikuanzonge;
		yuejunhuankuan = huankuanzonge / daikuanqishu;
		mTv_anjienianshu.setText(mAnjienianshu.getText().toString());
		setPrice(fangjiazonge, shouqihuankuan, daikuanzonge, huankuanzonge, lixizonge, yuejunhuankuan, shouyuehuankuan,
				moyuehuankuan);
	}

	/**
	 * 贷款本息
	 */
	public void benxi() {
		yuelilv = Double.parseDouble(mYinhanlilv.getText().toString()) / 1200;
		daikuanqishu = (nianshuPos + 1) * 12;
		if (isZongjiaOrDanjia == 0) {
			daikuanzonge = Double.parseDouble(mDaikuanzonge.getText().toString()) * 10000;
		} else {
			fangjiazonge = Double
					.parseDouble(mMianji.getText().toString().equals("") ? "0" : mMianji.getText().toString())
					* Double.parseDouble(mDanjia.getText().toString().equals("") ? "0" : mDanjia.getText().toString());
			daikuanzonge = fangjiazonge * ((chenshuPos + 2) / 10d);
			shouqihuankuan = fangjiazonge - daikuanzonge;
		}

		// }

		yuejunhuankuan = (daikuanzonge * yuelilv * Math.pow(1 + yuelilv, daikuanqishu))
				/ (Math.pow(1 + yuelilv, daikuanqishu) - 1);
		huankuanzonge = yuejunhuankuan * (daikuanqishu);
		lixizonge = huankuanzonge - daikuanzonge;
		mTv_anjienianshu.setText(mAnjienianshu.getText().toString());
		setPrice(fangjiazonge, shouqihuankuan, daikuanzonge, huankuanzonge, lixizonge, yuejunhuankuan, 0, 0);
	}

	public void setPrice(double fangjiazonge, double shouyueyuegong, double daikuanzonge, double huankuanzonge,
			double lixizonge, double yuejunhuankuan, double shouyuehuankuan, double moyuehuankuan) {
		mTv_fangwuzongjia.setText(new DecimalFormat("0.00").format(fangjiazonge) + "元");
		mTv_shouqihuankuan.setText(new DecimalFormat("0.00").format(shouyueyuegong) + "元");
		mTv_daikuanzonge.setText(new DecimalFormat("0.00").format(daikuanzonge) + "元");
		mTv_huankuanzonge.setText(new DecimalFormat("0.00").format(huankuanzonge) + "元");
		mTv_zhifulixi.setText(new DecimalFormat("0.00").format(lixizonge) + "元");
		mTv_yuejunhuankuan.setText(new DecimalFormat("0.00").format(yuejunhuankuan) + "元");
		mTv_shouyue_huankuan.setText(new DecimalFormat("0.00").format(shouyuehuankuan) + "元");
		mTv_moyue_huankuan.setText(new DecimalFormat("0.00").format(moyuehuankuan) + "元");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sure:
			lixizonge = 0;
			huankuanzonge = 0;
			if (isZongjiaOrDanjia == 0 && mDaikuanzonge.getText().toString().equals("")) {
				ToastUtil.toastShow(getActivity(), "请输入贷款总额");
			} else if (isZongjiaOrDanjia == 0 && mDanjia.equals("")) {
				ToastUtil.toastShow(getActivity(), "请输入单价");
			} else if (isZongjiaOrDanjia == 1 && mMianji.equals("")) {
				ToastUtil.toastShow(getActivity(), "请输入面积");
			} else if (isBenxiOrBenjin == 0) {
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
			mRl_shouyue_huankuan.setVisibility(View.VISIBLE);
			mRl_moyue_huankuan.setVisibility(View.VISIBLE);
			mRl_yuejunhuankuan.setVisibility(View.GONE);
			mRl_shouyue_huankuan.setVisibility(View.VISIBLE);
			mRl_moyue_huankuan.setVisibility(View.VISIBLE);
			mRl_yuejunhuankuan.setVisibility(View.GONE);
			mDengebenjin.setTextColor(Color.WHITE);
			mDengebenxi.setTextColor(Color.BLACK);
			mDenge_parent.setBackgroundResource(R.mipmap.tool_univalent);
			isBenxiOrBenjin = 1;
			mPromt.setText(getResources().getString(R.string.benjin_promt));
			setPrice(0, 0, 0, 0, 0, 0, 0, 0);
			break;
		case R.id.dengebenxi:
			mDengebenjin.setTextColor(Color.BLACK);
			mDengebenxi.setTextColor(Color.WHITE);
			mDenge_parent.setBackgroundResource(R.mipmap.tool_total);
			isBenxiOrBenjin = 0;
			mPromt.setText(getResources().getString(R.string.benxi_promt));
			setPrice(0, 0, 0, 0, 0, 0, 0, 0);
			mRl_shouyue_huankuan.setVisibility(View.GONE);
			mRl_moyue_huankuan.setVisibility(View.GONE);
			mRl_yuejunhuankuan.setVisibility(View.VISIBLE);
			break;
		case R.id.zongjiajisuan:
			mZongjiajisuan.setTextColor(Color.WHITE);
			mDanjiajisuan.setTextColor(Color.BLACK);
			mJisuan_parent.setBackgroundResource(R.mipmap.tool_total);
			isZongjiaOrDanjia = 0;
			setPrice(0, 0, 0, 0, 0, 0, 0, 0);
			if (isBenxiOrBenjin == 0) {
				mLayout_univalent.setVisibility(View.GONE);
				mLayout_total.setVisibility(View.VISIBLE);
				mRl_fangwuzongjia.setVisibility(View.GONE);
				mRl_shouqihuankuan.setVisibility(View.GONE);
				mRl_shouyue_huankuan.setVisibility(View.GONE);
				mRl_moyue_huankuan.setVisibility(View.GONE);
				mRl_yuejunhuankuan.setVisibility(View.VISIBLE);
			} else {
				mLayout_univalent.setVisibility(View.GONE);
				mLayout_total.setVisibility(View.VISIBLE);
				mRl_fangwuzongjia.setVisibility(View.GONE);
				mRl_shouqihuankuan.setVisibility(View.GONE);
				mRl_shouyue_huankuan.setVisibility(View.VISIBLE);
				mRl_moyue_huankuan.setVisibility(View.VISIBLE);
				mRl_yuejunhuankuan.setVisibility(View.GONE);
			}
			break;
		case R.id.danjiajisuan:
			mZongjiajisuan.setTextColor(Color.BLACK);
			mDanjiajisuan.setTextColor(Color.WHITE);
			mJisuan_parent.setBackgroundResource(R.mipmap.tool_univalent);
			isZongjiaOrDanjia = 1;
			mLayout_univalent.setVisibility(View.VISIBLE);
			mLayout_total.setVisibility(View.GONE);
			mRl_fangwuzongjia.setVisibility(View.VISIBLE);
			mRl_shouqihuankuan.setVisibility(View.VISIBLE);
			if (isBenxiOrBenjin == 1) {
				mRl_yuejunhuankuan.setVisibility(View.GONE);
				mRl_shouyue_huankuan.setVisibility(View.VISIBLE);
				mRl_moyue_huankuan.setVisibility(View.VISIBLE);
			} else {
				mRl_shouyue_huankuan.setVisibility(View.GONE);
				mRl_moyue_huankuan.setVisibility(View.GONE);
				mRl_yuejunhuankuan.setVisibility(View.VISIBLE);
			}
			setPrice(0, 0, 0, 0, 0, 0, 0, 0);

			break;
		case R.id.time1_jizhun:
			timeFlag = 0;
			setTimeBg(mTime1_jizhun);
			mYinhanlilv.setText(
					new BigDecimal(shangyedaikuanlilv1.get(nianshuPos)).setScale(2, RoundingMode.HALF_UP).doubleValue()
							+ "");
			break;
		case R.id.time1_youhui10:
			timeFlag = 1;
			setTimeBg(mTime1_youhui10);
			mYinhanlilv.setText(new BigDecimal(shangyedaikuanlilv1.get(nianshuPos) * 0.90)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			break;
		case R.id.time1_shangfu5:
			timeFlag = 2;
			setTimeBg(mTime1_shangfu5);
			mYinhanlilv.setText(new BigDecimal(shangyedaikuanlilv1.get(nianshuPos) * 1.05)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			break;
		case R.id.time1_shangfu10:
			timeFlag = 3;
			setTimeBg(mTime1_shangfu10);
			mYinhanlilv.setText(new BigDecimal(shangyedaikuanlilv1.get(nianshuPos) * 1.1)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			break;
		case R.id.time2_jizhun:
			timeFlag = 4;
			setTimeBg(mTime2_jizhun);
			mYinhanlilv.setText(
					new BigDecimal(shangyedaikuanlilv1.get(nianshuPos)).setScale(2, RoundingMode.HALF_UP).doubleValue()
							+ "");
			break;
		case R.id.time2_youhui10:
			timeFlag = 5;
			setTimeBg(mTime2_youhui10);
			mYinhanlilv.setText(new BigDecimal(shangyedaikuanlilv2.get(nianshuPos) * 0.90)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			break;
		case R.id.time2_shangfu5:
			timeFlag = 6;
			setTimeBg(mTime2_shangfu5);
			mYinhanlilv.setText(new BigDecimal(shangyedaikuanlilv2.get(nianshuPos) * 1.05)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
			break;
		case R.id.time2_shangfu10:
			timeFlag = 7;
			setTimeBg(mTime2_shangfu10);
			mYinhanlilv.setText(new BigDecimal(shangyedaikuanlilv2.get(nianshuPos) * 1.1)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
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

	/**
	 * 0第一行基准利率； 1第一行优惠10 ；2第一行上浮5 ；3第一 行上浮10 ； 4第二行基准利率； 5第二行优惠10 ；6第二行上浮5
	 * ；7第二行上浮10 ；
	 */
	private int timeFlag = 0;

	@Override
	public void getTime(String time, int position) {
		this.nianshuPos = position;
		mAnjienianshu.setText(time);
		double price = 0;
		switch (timeFlag) {
		case 0:
			price = shangyedaikuanlilv1.get(position);
			break;
		case 1:
			price = shangyedaikuanlilv1.get(position) * 0.95;
			break;
		case 2:
			price = shangyedaikuanlilv1.get(position) * 1.05;
			break;
		case 3:
			price = shangyedaikuanlilv1.get(position) * 1.10;
			break;
		case 4:
			price = shangyedaikuanlilv2.get(position);
			break;
		case 5:
			price = shangyedaikuanlilv2.get(position) * 0.95;
			break;
		case 6:
			price = shangyedaikuanlilv2.get(position) * 1.05;
			break;
		case 7:
			price = shangyedaikuanlilv2.get(position) * 1.10;
			break;
		default:
			break;
		}
		mYinhanlilv.setText(new BigDecimal(price).setScale(2, RoundingMode.HALF_UP).doubleValue() + "");
	}

	@Override
	public void getChengshu(String str, int position) {
		this.chenshuPos = position;
		mAnjiechengshu.setText(str);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

}
