package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.widget.LetterView;
import com.zorasun.fangchanzhichuang.general.widget.LetterView.OnLetterTouchListener;
import com.zorasun.fangchanzhichuang.section.index.entity.AllCityEntity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseCityActivity extends BaseActivity implements OnItemClickListener {

	private MyAdapter myAdapter;
	private ArrayList<String> cityList = new ArrayList<>();
	private ListView mListView;
	private LetterView mletterView;
	private TextView currentCity;
	private LocationClient locationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acivity_choosecity);
		initView();
		initLocation();
		initData();
	}

	private void initData() {

		IndexApi.getInstance().requestCityList(this, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				AllCityEntity allCityEntity = (AllCityEntity) object;
				cityList.add("A");
				cityList.addAll(allCityEntity.getContent().getA());
				cityList.add("B");
				cityList.addAll(allCityEntity.getContent().getB());
				cityList.add("C");
				cityList.addAll(allCityEntity.getContent().getC());
				cityList.add("D");
				cityList.addAll(allCityEntity.getContent().getD());
				cityList.add("E");
				cityList.addAll(allCityEntity.getContent().getE());
				cityList.add("F");
				cityList.addAll(allCityEntity.getContent().getF());
				cityList.add("G");
				cityList.addAll(allCityEntity.getContent().getG());
				cityList.add("H");
				// cityList.addAll(allCityEntity.getContent().getH());
				// cityList.add("I");
				cityList.addAll(allCityEntity.getContent().getI());
				cityList.add("J");
				cityList.addAll(allCityEntity.getContent().getJ());
				cityList.add("K");
				cityList.addAll(allCityEntity.getContent().getK());
				cityList.add("L");
				cityList.addAll(allCityEntity.getContent().getL());
				cityList.add("M");
				cityList.addAll(allCityEntity.getContent().getM());
				cityList.add("N");
				cityList.addAll(allCityEntity.getContent().getN());
				cityList.add("O");
				cityList.addAll(allCityEntity.getContent().getO());
				cityList.add("P");
				cityList.addAll(allCityEntity.getContent().getP());
				cityList.add("Q");
				cityList.addAll(allCityEntity.getContent().getQ());
				cityList.add("R");
				cityList.addAll(allCityEntity.getContent().getR());
				cityList.add("S");
				cityList.addAll(allCityEntity.getContent().getS());
				cityList.add("T");
				cityList.addAll(allCityEntity.getContent().getT());
				// cityList.add("U");
				// cityList.addAll(allCityEntity.getContent().getU());
				// cityList.add("V");
				cityList.addAll(allCityEntity.getContent().getV());
				cityList.add("W");
				cityList.addAll(allCityEntity.getContent().getW());
				cityList.add("X");
				cityList.addAll(allCityEntity.getContent().getX());
				cityList.add("Y");
				cityList.addAll(allCityEntity.getContent().getY());
				cityList.add("Z");
				cityList.addAll(allCityEntity.getContent().getZ());
				myAdapter.notifyDataSetChanged();
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

	private void initView() {

		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("选择城市");
		View headView = getLayoutInflater().inflate(R.layout.item_head_city, null);
		currentCity = (TextView) headView.findViewById(R.id.tv_localcity);
		currentCity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(1, getIntent().putExtra("city", currentCity.getText().toString()));
				finish();
			}
		});
		mListView = (ListView) findViewById(R.id.lv_allcity);
		mListView.addHeaderView(headView);
		myAdapter = new MyAdapter();
		mListView.setAdapter(myAdapter);
		mListView.setOnItemClickListener(this);
		mletterView = (LetterView) findViewById(R.id.letterView1);

		List<String> list = new ArrayList<>();
		final String[] letters = new String[] { "#", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N",
				"O", "P", "Q", "R", "S", "T", "W", "X", "Y", "Z" };
		for (int i = 0; i < letters.length; i++) {
			list.add(letters[i]);
		}

		mletterView.setList(list);
		mletterView.setOnLetterTouchListener(new OnLetterTouchListener() {

			@Override
			public void onTouchDown(String letter) {

				for (int i = 0; i < cityList.size(); i++) {
					String index = cityList.get(i);
					if (index.equals(letter)) {
						mListView.setSelection(i - mListView.getHeaderViewsCount());
					}
				}

			}

			@Override
			public void onTouchUp() {
				// TODO Auto-generated method stub

			}
		});
		// mListView.setOnScrollListener(new OnScrollListener() {
		//
		// @Override
		// public void onScroll(AbsListView view, int firstVisibleItem, int
		// visibleItemCount, int totalItemCount) {
		// if (cityList.size() > 0) {
		// String cityIndex = cityList.get(firstVisibleItem);
		// mletterView.selectStyle(cityIndex);
		//
		// }
		// }
		//
		// @Override
		// public void onScrollStateChanged(AbsListView view, int scrollState) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// });

	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return cityList.size();
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = getLayoutInflater().inflate(R.layout.list_item_city, null);
			TextView tvCity = (TextView) view.findViewById(R.id.tv_city);
			View line = view.findViewById(R.id.line);
			line.setVisibility(View.VISIBLE);
			if (cityList.get(position).length() < 2) {
				line.setVisibility(View.GONE);
			} else {
				line.setVisibility(View.VISIBLE);
			}
			tvCity.setText(cityList.get(position));
			return view;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		position -= mListView.getHeaderViewsCount();
		String string = cityList.get(position);
		if (!string.equals("厦门")) {
			return;
		}
		if (string.length() < 2) {
			return;
		} else {
			setResult(1, getIntent().putExtra("city", string));
			finish();
			super.onBackPressed();
		}
	}

	@Override
	public void onBackPressed() {
		setResult(1, getIntent().putExtra("city", "厦门"));
		super.onBackPressed();
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (locationClient != null && !locationClient.isStarted()) {
			locationClient.start();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (locationClient != null && locationClient.isStarted()) {
			locationClient.stop();
		}
	}

	private void initLocation() {
		locationClient = new LocationClient(getApplicationContext());
		// 设置定位条件
		LocationClientOption option = new LocationClientOption();
		option.setIsNeedAddress(true);
		option.setOpenGps(true); // 是否打开GPS
		option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
		// option.setProdName("LocationDemo"); //
		// 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		// option.setScanSpan(UPDATE_TIME);// 设置定时定位的时间间隔。单位毫秒
		option.setScanSpan(1);
		locationClient.setLocOption(option);

		// 注册位置监听器
		locationClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				if (location == null) {
					return;
				}
				location.getLatitude();
				location.getLongitude();
				String city = location.getCity();
				int lastIndexOf = city.lastIndexOf("市");
				String substring = city.substring(0, lastIndexOf);
				currentCity.setText(substring);

			}
		});

	}
}
