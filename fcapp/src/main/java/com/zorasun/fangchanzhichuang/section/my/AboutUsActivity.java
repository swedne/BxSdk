package com.zorasun.fangchanzhichuang.section.my;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AboutUsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		initView();
	}

	private void initView() {
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		tvTitle.setText("关于我们");
		findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				AboutUsActivity.super.onBackPressed();
			}
		});
	}
}
