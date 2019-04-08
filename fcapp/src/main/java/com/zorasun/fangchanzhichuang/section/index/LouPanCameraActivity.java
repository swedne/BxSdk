package com.zorasun.fangchanzhichuang.section.index;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LouPanCameraActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hu_xing_show);
		initView();
	}

	private void initView() {
		TextView tvTitle= (TextView) findViewById(R.id.title_name);
		tvTitle.setText("楼盘相册");
		findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				LouPanCameraActivity.super.onBackPressed();
			}
		});
		
		ListView mListView = (ListView) findViewById(R.id.lv_huXing);
		mListView.setAdapter(new MyAdapter());
	}
	
	
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 6;
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
			View view = getLayoutInflater().inflate(R.layout.list_item_huxingimage,null);
			
			return view;
		}
		
	}
}
