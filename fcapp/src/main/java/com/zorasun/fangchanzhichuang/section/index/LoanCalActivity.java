package com.zorasun.fangchanzhichuang.section.index;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.section.index.tools.ToolActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class LoanCalActivity extends BaseActivity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loan_cal);
		initUI();
	}

	private void initUI() {
		findViewById(R.id.houseloan).setOnClickListener(this);
		findViewById(R.id.tax).setOnClickListener(this);
		findViewById(R.id.fund).setOnClickListener(this);

		findViewById(R.id.title_left).setOnClickListener(this);

		((TextView) findViewById(R.id.title_name)).setText("计算器");
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.houseloan:// 房贷
			intent = new Intent(this, ToolActivity.class);
			startActivity(intent);
			break;
		case R.id.tax:// 税费
			intent = new Intent(this, TaxCalActivity.class);
			startActivity(intent);
			break;
		case R.id.fund:// 公积金贷款计算器
			intent = new Intent(this, FundCalActivity.class);
			startActivity(intent);
			break;
		case R.id.title_left:// 后退
			finish();
			super.onBackPressed();
			break;
		default:
			break;
		}
	}
}
