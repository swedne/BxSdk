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
import com.zorasun.fangchanzhichuang.section.index.IndexApi;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.LastMonthSuccessNumListByArea;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.LastMonthSuccessNumListByArea_null;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.XiamenSecondHouseQuotationListByArea;
import com.zorasun.fangchanzhichuang.section.index.entity.WidePriceEntity.XiamenSecondHouseQuotationListByArea_null;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TradingFragment extends BaseFragment {

	private LayoutInflater inflate;
	private View view;
	private MyAdapter adapter;
	private List<String> titleXList = new ArrayList<String>();
	private List<XiamenSecondHouseQuotationListByArea> quotationListByArea = new ArrayList<>();
	private List<Float> arrayList1 = new ArrayList<Float>();
	private List<Float> arrayList2 = new ArrayList<Float>();
	private List<Float> arrayList3 = new ArrayList<Float>();
	private List<Float> arrayList4 = new ArrayList<Float>();
	private List<Float> arrayList5 = new ArrayList<Float>();
	private List<Float> arrayList6 = new ArrayList<Float>();
	private List<Object> lastMonthSuccessNumListByArea = new ArrayList<>();
	private List<String> areaList = new ArrayList<String>();
	private List<String> titleYList = new ArrayList<String>();
	private LineCharView2 charView;
	private ListView mListView;
	protected boolean isWhole;

	public TradingFragment() {
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
		// TODO Auto-generated method stub
		super.onResume();
		initData();
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
		mListView = (ListView) view.findViewById(R.id.list_view);
		TextView tvName = (TextView) view.findViewById(R.id.textView1);
		tvName.setText("成交量走势");
		adapter = new MyAdapter();
		mListView.setAdapter(adapter);

		charView = (LineCharView2) view.findViewById(R.id.lineCharView1);
		// titleYList.clear();
		// titleYList.add("0套");
		// titleYList.add("500套");
		// titleYList.add("1000套");
		// titleYList.add("1500套");
		// titleYList.add("2000套");
		charView.setyUnit("套");
		// charView.setTitleYList(titleYList);
		charView.setNumberOfY(5, true);
		charView.setClassfy(true);
	}

	public void initData() {
		IndexApi.getInstance().requestWidePrice(getActivity(), 2, getCurrentMonth(), getCurrentTime(),
				new RequestCallBack() {
					private List<String> pointList;

					@Override
					public void onSuccess(int code, String msg, Object object) {
						List pointList2 = new ArrayList();
						WidePriceEntity widePriceEntity = (WidePriceEntity) object;
						lastMonthSuccessNumListByArea.clear();
						ArrayList<LastMonthSuccessNumListByArea> lastMonthSuccessNumListByArea2 = widePriceEntity
								.getContent().getLastMonthSuccessNumListByArea();
						// 如果为六条数据直接添加
						if (lastMonthSuccessNumListByArea2.size() == 6) {
							isWhole = true;
							lastMonthSuccessNumListByArea.addAll(lastMonthSuccessNumListByArea2);
						} else {
							isWhole = false;
							if (widePriceEntity.getContent().getLastMonthSuccessNumListByArea_null() != null) {
								ArrayList<LastMonthSuccessNumListByArea_null> lastMonthSuccessNumListByArea_null = widePriceEntity
										.getContent().getLastMonthSuccessNumListByArea_null();
								for (int i = 0; i < lastMonthSuccessNumListByArea_null.size(); i++) {
									for (int j = 0; j < lastMonthSuccessNumListByArea2.size(); j++) {
										// 如果有数值的区域名和空的区域名相同将数据覆盖
										if (lastMonthSuccessNumListByArea_null.get(i).getAreaName()
												.equals(lastMonthSuccessNumListByArea2.get(j).getAreaName())) {
											lastMonthSuccessNumListByArea_null.get(i).setSuccessNum(
													lastMonthSuccessNumListByArea2.get(j).getSuccessNum());
										}
									}
								}
								lastMonthSuccessNumListByArea.addAll(lastMonthSuccessNumListByArea_null);
							}
						}
						mListView.postDelayed(new Runnable() {

							@Override
							public void run() {

								adapter.notifyDataSetChanged();
							}
						}, 100);
						titleXList.clear();
						// areaList.clear();
						arrayList1.clear();
						arrayList2.clear();
						arrayList3.clear();
						arrayList4.clear();
						arrayList5.clear();
						arrayList6.clear();
						quotationListByArea.clear();
						// 添加折线标注
						ArrayList<String> circleLabelList = new ArrayList<>();
						circleLabelList.clear();
						ArrayList<XiamenSecondHouseQuotationListByArea> quotationListByAreaData = widePriceEntity
								.getContent().getXiamenSecondHouseQuotationListByArea();
						quotationListByArea.addAll(quotationListByAreaData);
						if (quotationListByArea.size() == 36) {
							for (int i = 0; i < quotationListByArea.size(); i++) {
								if (!titleXList.contains(quotationListByArea.get(i).getMonth() + "月")) {
									titleXList.add(quotationListByArea.get(i).getMonth() + "月");
								}
								if (!circleLabelList.contains(quotationListByArea.get(i).getAreaName())) {
									circleLabelList.add(quotationListByArea.get(i).getAreaName());
								}
							}
							for (int i = 0; i < quotationListByArea.size(); i++) {
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(0))) {
									arrayList1.add(quotationListByArea.get(i).getSuccessNum());
								}
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(1))) {
									arrayList2.add(quotationListByArea.get(i).getSuccessNum());
								}
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(2))) {
									arrayList3.add(quotationListByArea.get(i).getSuccessNum());
								}
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(3))) {
									arrayList4.add(quotationListByArea.get(i).getSuccessNum());
								}
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(4))) {
									arrayList5.add(quotationListByArea.get(i).getSuccessNum());
								}
								if (quotationListByArea.get(i).getAreaName().equals(circleLabelList.get(5))) {
									arrayList6.add(quotationListByArea.get(i).getSuccessNum());
								}
								// if
								// (!areaList.contains(quotationListByArea.get(i).getAreaName()))
								// {
								// areaList.add(quotationListByArea.get(i).getAreaName());
								// }
							}
						} else {
							ArrayList<XiamenSecondHouseQuotationListByArea_null> xiamenSecondHouseQuotationListByArea_null = widePriceEntity
									.getContent().getXiamenSecondHouseQuotationListByArea_null();
							for (int i = 0; i < xiamenSecondHouseQuotationListByArea_null.size(); i++) {
								for (int j = 0; j < quotationListByAreaData.size(); j++) {

									// 如果有数值的区域名、月份和空的区域名相同将数据覆盖
									if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
											.equals(quotationListByAreaData.get(j).getAreaName())
											&& xiamenSecondHouseQuotationListByArea_null.get(i).getMonth()
													.equals(quotationListByAreaData.get(j).getMonth())) {
										xiamenSecondHouseQuotationListByArea_null.get(i)
												.setSuccessNum(quotationListByAreaData.get(j).getSuccessNum());
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
									arrayList1.add(xiamenSecondHouseQuotationListByArea_null.get(i).getSuccessNum());
								}
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(1))) {
									arrayList2.add(xiamenSecondHouseQuotationListByArea_null.get(i).getSuccessNum());
								}
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(2))) {
									arrayList3.add(xiamenSecondHouseQuotationListByArea_null.get(i).getSuccessNum());
								}
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(3))) {
									arrayList4.add(xiamenSecondHouseQuotationListByArea_null.get(i).getSuccessNum());
								}
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(4))) {
									arrayList5.add(xiamenSecondHouseQuotationListByArea_null.get(i).getSuccessNum());
								}
								if (xiamenSecondHouseQuotationListByArea_null.get(i).getAreaName()
										.equals(circleLabelList.get(5))) {
									arrayList6.add(xiamenSecondHouseQuotationListByArea_null.get(i).getSuccessNum());
								}
								// if
								// (!areaList.contains(quotationListByArea.get(i).getAreaName()))
								// {
								// areaList.add(quotationListByArea.get(i).getAreaName());
								// }
							}

						}
						pointList2.clear();
						// 模拟数据
						// arrayList4.add((float) 230);
						// arrayList4.add((float) 500);
						// arrayList4.add((float) 1999);
						// arrayList4.add((float) 150);
						// arrayList4.add((float) 250);
						//
						// arrayList1.add((float) 600);
						// arrayList1.add((float) 150);
						// arrayList1.add((float) 2030);
						// arrayList1.add((float) 500);
						// arrayList1.add((float) 400);
						pointList2.add(arrayList1);
						pointList2.add(arrayList2);
						pointList2.add(arrayList3);
						pointList2.add(arrayList4);
						pointList2.add(arrayList5);
						pointList2.add(arrayList6);
						charView.setPointList(pointList2);
						charView.setTitleXList(titleXList);
						charView.setCircleLabelList(circleLabelList);
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

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lastMonthSuccessNumListByArea.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@SuppressLint({ "InflateParams", "ViewHolder" })
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = inflate.inflate(R.layout.list_item_wideprice, null);
			TextView tvUnit = (TextView) view.findViewById(R.id.unit);
			TextView tvArea = (TextView) view.findViewById(R.id.tv_area);
			TextView tvDate = (TextView) view.findViewById(R.id.tv_data);
			tvUnit.setText("套");
			Object object = lastMonthSuccessNumListByArea.get(position);
			if (isWhole) {
				LastMonthSuccessNumListByArea lastMonthSuccessNumListByArea2 = (LastMonthSuccessNumListByArea) object;
				tvArea.setText(lastMonthSuccessNumListByArea2.getAreaName());
				tvDate.setText(lastMonthSuccessNumListByArea2.getSuccessNum() + "");
			} else {
				LastMonthSuccessNumListByArea_null lastMonthSuccessNumListByArea_null = (LastMonthSuccessNumListByArea_null) object;
				tvArea.setText(lastMonthSuccessNumListByArea_null.getAreaName());
				tvDate.setText(lastMonthSuccessNumListByArea_null.getSuccessNum() + "");
			}
			return view;
		}

	}

	@Override
	public void initView() {
		if (lastMonthSuccessNumListByArea.size() <= 0) {
			// 获取列表
			initData();
		} else {
			adapter.notifyDataSetChanged();
		}
	}

}
