package com.zorasun.fangchanzhichuang.section.index.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.widget.LineCharView2;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollListView;
import com.zorasun.fangchanzhichuang.section.index.IndexApi;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.LastMonthAvgPriceListByArea;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.LastMonthAvgPriceListByArea_null;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.XiamenSecondHouseQuotationListByArea;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.XiamenSecondHouseQuotationListByArea_null;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class AveragePriceFragment extends BaseFragment {

	private View view;
	private MyAdapter adapter;
	private List<String> titleXList = new ArrayList<String>();
	private LayoutInflater inflate;
	private List<XiamenSecondHouseQuotationListByArea> quotationListByArea = new ArrayList<>();
	private ArrayList<Float> arrayList1 = new ArrayList<Float>();
	private ArrayList<Float> arrayList2 = new ArrayList<Float>();
	private ArrayList<Float> arrayList3 = new ArrayList<Float>();
	private ArrayList<Float> arrayList4 = new ArrayList<Float>();
	private ArrayList<Float> arrayList5 = new ArrayList<Float>();
	private ArrayList<Float> arrayList6 = new ArrayList<Float>();
	private List<String> titleYList = new ArrayList<String>();
	private List<String> areaList = new ArrayList<String>();
	private ArrayList<List<Float>> pointList = new ArrayList<List<Float>>();
	private List<Object> lastMonthAvgPriceListByArea = new ArrayList<>();
	private LineCharView2 charView;
	protected boolean isWhole;

	public AveragePriceFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflate = inflater;
		view = inflater.inflate(R.layout.fragment_average_price, container, false);
		initUI();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		initData();
	}

	// @Override
	// public void setUserVisibleHint(boolean isVisibleToUser) {
	// Log.e("==============", "setUserVisibleHint");
	// super.setUserVisibleHint(isVisibleToUser);
	// if (isVisibleToUser) {
	// // 相当于Fragment的onResume
	// }
	// }

	public void initData() {
		IndexApi.getInstance().requestWidePrice(getActivity(), 1, getCurrentMonth(), getCurrentTime(),
				new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {
						WidePriceEntity widePriceEntity = (WidePriceEntity) object;
						lastMonthAvgPriceListByArea.clear();
						List<LastMonthAvgPriceListByArea> lastMonthAvgPriceListByArea2 = widePriceEntity.getContent()
								.getXiamenSecondHouseQuotationMap().getLastMonthAvgPriceListByArea();
						// 如果为六条数据直接添加
						if (lastMonthAvgPriceListByArea2.size() == 6) {
							isWhole = true;
							lastMonthAvgPriceListByArea.addAll(lastMonthAvgPriceListByArea2);
						} else {
							isWhole = false;
							if (widePriceEntity.getContent().getXiamenSecondHouseQuotationMap()
									.getLastMonthAvgPriceListByArea_null() != null) {
								List<LastMonthAvgPriceListByArea_null> lastMonthAvgPriceListByArea_null = widePriceEntity
										.getContent().getXiamenSecondHouseQuotationMap()
										.getLastMonthAvgPriceListByArea_null();
								for (int i = 0; i < lastMonthAvgPriceListByArea_null.size(); i++) {
									for (int j = 0; j < lastMonthAvgPriceListByArea2.size(); j++) {
										// 如果有数值的区域名和空的区域名相同将数据覆盖
										if (lastMonthAvgPriceListByArea_null.get(i).getAreaName()
												.equals(lastMonthAvgPriceListByArea2.get(j).getAreaName())) {
											lastMonthAvgPriceListByArea_null.get(i)
													.setAvgPrice(lastMonthAvgPriceListByArea2.get(j).getAvgPrice());
										}
									}
								}
								lastMonthAvgPriceListByArea.addAll(lastMonthAvgPriceListByArea_null);
							}
						}

						adapter.notifyDataSetChanged();

						quotationListByArea.clear();
						quotationListByArea.addAll(widePriceEntity.getContent().getXiamenSecondHouseQuotationMap()
								.getXiamenSecondHouseQuotationListByArea());
						areaList.clear();
						titleXList.clear();
						arrayList1.clear();
						arrayList2.clear();
						arrayList3.clear();
						arrayList4.clear();
						arrayList5.clear();
						arrayList6.clear();
						// 添加折线标注
						ArrayList<String> circleLabelList = new ArrayList<>();
						circleLabelList.clear();
						charView.setCircleLabelList(circleLabelList);
						if (quotationListByArea.size() == 36) {
							for (int i = 0; i < quotationListByArea.size(); i++) {
								if (!circleLabelList.contains(quotationListByArea.get(i).getAreaName())) {
									circleLabelList.add(quotationListByArea.get(i).getAreaName());
								}
								if (!titleXList.contains(quotationListByArea.get(i).getMonth() + "月")) {
									titleXList.add(quotationListByArea.get(i).getMonth() + "月");
								}
							}
							for (int i = 0; i < quotationListByArea.size(); i++) {
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(0))) {
									arrayList1.add(quotationListByArea.get(i).getAvgPrice() / 10000);
								}
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(1))) {
									arrayList2.add(quotationListByArea.get(i).getAvgPrice() / 10000);
								}
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(2))) {
									arrayList3.add(quotationListByArea.get(i).getAvgPrice() / 10000);
								}
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(3))) {
									arrayList4.add(quotationListByArea.get(i).getAvgPrice() / 10000);
								}
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(4))) {
									arrayList5.add(quotationListByArea.get(i).getAvgPrice() / 10000);
								}
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(5))) {
									arrayList6.add(quotationListByArea.get(i).getAvgPrice() / 10000);
								}
								if (!areaList.contains(quotationListByArea.get(i).getAreaName())) {
									areaList.add(quotationListByArea.get(i).getAreaName());
								}
							}
						} else {
							List<XiamenSecondHouseQuotationListByArea_null> xiamenSecondHouseQuotationListByArea_null = widePriceEntity
									.getContent().getXiamenSecondHouseQuotationMap()
									.getXiamenSecondHouseQuotationListByArea_null();
							for (int i = 0; i < xiamenSecondHouseQuotationListByArea_null.size(); i++) {
								for (int j = 0; j < quotationListByArea.size(); j++) {
									// 如果有数值的区域名、月份和空的区域名相同将数据覆盖
									if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
											.equals(quotationListByArea.get(j).getAreaName())
											&& xiamenSecondHouseQuotationListByArea_null.get(i).getMonth()
													.equals(quotationListByArea.get(j).getMonth())) {
										xiamenSecondHouseQuotationListByArea_null.get(i)
												.setAvgPrice(quotationListByArea.get(j).getAvgPrice());
									}

								}
							}
							for (int i = 0; i < xiamenSecondHouseQuotationListByArea_null.size(); i++) {
								if (!titleXList
										.contains(xiamenSecondHouseQuotationListByArea_null.get(i).getMonth() + "月")) {
									titleXList.add(xiamenSecondHouseQuotationListByArea_null.get(i).getMonth() + "月");
								}
								if (!circleLabelList
										.contains(xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName())) {
									circleLabelList.add(xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName());
								}
							}
							for (int i = 0; i < xiamenSecondHouseQuotationListByArea_null.size(); i++) {
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(0))) {
									arrayList1.add(
											xiamenSecondHouseQuotationListByArea_null.get(i).getAvgPrice() / 10000);
								}
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(1))) {
									arrayList2.add(
											xiamenSecondHouseQuotationListByArea_null.get(i).getAvgPrice() / 10000);
								}
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(2))) {
									arrayList3.add(
											xiamenSecondHouseQuotationListByArea_null.get(i).getAvgPrice() / 10000);
								}
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(3))) {
									arrayList4.add(
											xiamenSecondHouseQuotationListByArea_null.get(i).getAvgPrice() / 10000);
								}
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(4))) {
									arrayList5.add(
											xiamenSecondHouseQuotationListByArea_null.get(i).getAvgPrice() / 10000);
								}
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(5))) {
									arrayList6.add(
											xiamenSecondHouseQuotationListByArea_null.get(i).getAvgPrice() / 10000);
								}
							}
						}
						pointList.clear();
						// 模拟数据
						// arrayList5.add(500f);
						// arrayList5.add(700f);
						// arrayList5.add(550f);
						// arrayList5.add(300f);
						// arrayList5.add(350f);
						// arrayList5.add(400f);
						pointList.add(arrayList1);
						pointList.add(arrayList2);
						pointList.add(arrayList3);
						pointList.add(arrayList4);
						pointList.add(arrayList5);
						pointList.add(arrayList6);
						charView.setTitleXList(titleXList);
						charView.setPointList(pointList);
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

	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		// 获得当前时间的月份，月份从0开始所以结果要加1
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static String getCurrentTime() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}

	private void initUI() {
		NoScrollListView mListView = (NoScrollListView) view.findViewById(R.id.list_view);
		TextView tvName = (TextView) view.findViewById(R.id.textView1);
		tvName.setText("价格走势");
		adapter = new MyAdapter();
		mListView.setAdapter(adapter);

		charView = (LineCharView2) view.findViewById(R.id.lineCharView1);

		charView.setyUnit("万/平");
		charView.setNumberOfY(5, false);
//		charView.setClassfy(false);
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return lastMonthAvgPriceListByArea.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@SuppressLint({ "InflateParams", "ViewHolder" })
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = inflate.inflate(R.layout.list_item_wideprice, null);
			TextView tvArea = (TextView) view.findViewById(R.id.tv_area);
			TextView tvDate = (TextView) view.findViewById(R.id.tv_data);
			Object object = lastMonthAvgPriceListByArea.get(position);
			if (isWhole) {
				LastMonthAvgPriceListByArea lastMonthAvgPriceInfo = (LastMonthAvgPriceListByArea) object;
				tvArea.setText(lastMonthAvgPriceInfo.getAreaName());
				tvDate.setText(lastMonthAvgPriceInfo.getAvgPrice() + "");
			} else {
				LastMonthAvgPriceListByArea_null lastMonthAvgPriceListByArea_null = (LastMonthAvgPriceListByArea_null) object;
				tvArea.setText(lastMonthAvgPriceListByArea_null.getAreaName());
				tvDate.setText(lastMonthAvgPriceListByArea_null.getAvgPrice() + "");
			}
			return view;
		}

	}

	@Override
	public void initView() {
		if (lastMonthAvgPriceListByArea.size() <= 0) {
			// 获取列表
			initData();
		} else {
			adapter.notifyDataSetChanged();
		}
	}

}
