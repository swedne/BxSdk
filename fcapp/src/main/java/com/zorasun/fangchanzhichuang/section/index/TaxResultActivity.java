package com.zorasun.fangchanzhichuang.section.index;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TaxResultActivity extends BaseActivity implements OnClickListener {

	private Button btn_taxresult_recal;
	private TextView tv_tax_result_land;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tax_result);

		Bundle bundle = getIntent().getExtras();
		int type = bundle.getInt("type");
		double deedTax = bundle.getDouble("deedTax");
		double indivTax = bundle.getDouble("indivTax");
		double valueTax = bundle.getDouble("valueTax");
		double landMoney = bundle.getDouble("landMoney");
		double charge = bundle.getDouble("charge");
		double regisFee = bundle.getDouble("regisFee");
		double assessFee = bundle.getDouble("assessFee");

		double total = 0;

		total = deedTax + indivTax + valueTax + landMoney + charge + regisFee + assessFee;

		TextView tv_taxresult_total = (TextView) findViewById(R.id.tv_taxresult_total);
		TextView tv_taxresult_deed = (TextView) findViewById(R.id.tv_taxresult_deed);
		TextView tv_taxresult_indiv = (TextView) findViewById(R.id.tv_taxresult_indiv);
		TextView tv_taxresult_value = (TextView) findViewById(R.id.tv_taxresult_value);
		TextView tv_taxresult_land = (TextView) findViewById(R.id.tv_taxresult_land);
		TextView tv_taxresult_charge = (TextView) findViewById(R.id.tv_taxresult_charge);
		TextView tv_taxresult_regis = (TextView) findViewById(R.id.tv_taxresult_regis);
		TextView tv_taxresult_assess = (TextView) findViewById(R.id.tv_taxresult_assess);
		tv_taxresult_total.setText(StringUtils.save2(total));
		tv_taxresult_deed.setText(StringUtils.save2(deedTax));
		tv_taxresult_indiv.setText(StringUtils.save2(indivTax));
		tv_taxresult_value.setText(StringUtils.save2(valueTax));
		tv_taxresult_land.setText(StringUtils.save2(landMoney));
		tv_taxresult_charge.setText(StringUtils.save2(charge));
		tv_taxresult_regis.setText(StringUtils.save2(regisFee));
		tv_taxresult_assess.setText(StringUtils.save2(assessFee));

		findViewById(R.id.title_left).setOnClickListener(this);
		((TextView) findViewById(R.id.title_name)).setText("计算结果");
		btn_taxresult_recal = (Button) findViewById(R.id.btn_taxresult_recal);
		btn_taxresult_recal.setOnClickListener(this);
		tv_tax_result_land = (TextView) findViewById(R.id.tv_tax_result_land);
		if (type == 1) {
			tv_tax_result_land.setText("土地增值税");
		} else if (type == 0) {
			tv_tax_result_land.setText("土地出让金");
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:// 返回
			finish();
			super.onBackPressed();
			break;
		case R.id.btn_taxresult_recal:// 重新计算
			finish();
		default:
			break;
		}
	}
}
