package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity.ApartmentList;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity.NewHouseMap;
import com.zorasun.fangchanzhichuang.section.index.entity.NewHouseEntity.PropertyGalleryList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HuXingShowActivity extends BaseActivity {

	private ListView mListView;
	private NewHouseMap newHouseMap;
	private int flag;
	private List<ApartmentList> apartmentList = new ArrayList<>();
	private List<PropertyGalleryList> propertyGalleryList = new ArrayList<>();
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hu_xing_show);
		newHouseMap = (NewHouseMap) getIntent().getSerializableExtra("newHouseMap");
		apartmentList.addAll(newHouseMap.getApartmentList());
		propertyGalleryList.addAll(newHouseMap.getPropertyGalleryList());
		flag = getIntent().getIntExtra("flag", 1);
		initView();
	}

	private void initView() {
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		if (flag == 1) {
			tvTitle.setText("户型展示");
		} else {
			tvTitle.setText("楼盘相册");
		}
		findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				HuXingShowActivity.super.onBackPressed();
			}
		});

		mListView = (ListView) findViewById(R.id.lv_huXing);
		adapter = new MyAdapter();
		mListView.setAdapter(adapter);

	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			int num;
			if (flag == 1) {
				num = apartmentList.size();
			} else {
				num = propertyGalleryList.size();
			}
			return num;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = getLayoutInflater().inflate(R.layout.list_item_huxingimage, null);
			ImageView imgShow = (ImageView) view.findViewById(R.id.img_show);
			if (flag == 1) {
				ApartmentList apartmentInfo = apartmentList.get(position);
				AsyncImageLoader.setAsynImages(imgShow, apartmentInfo.getUrl());
			} else {
				PropertyGalleryList propertyGalleryInfo = propertyGalleryList.get(position);
				AsyncImageLoader.setAsynImages(imgShow, propertyGalleryInfo.getUrl());
			}
			return view;
		}

	}
}
