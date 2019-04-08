package com.zorasun.fangchanzhichuang.section.index;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FundCalActivity extends BaseActivity implements OnClickListener {

	// private TextView tv_fundloaner_one_base;
	// private TextView tv_fundloaner_two_base;
	// private TextView tv_fundloaner_one_balance;
	// private TextView tv_fundloaner_two_balance;
	// private EditText et_fundloaner_one_base;
	// private EditText et_fundloaner_two_base;
	private EditText et_fundloaner_one_balance;
	private EditText et_fundloaner_two_balance;
	private EditText et_fundloaner_year;
	private TextView tv_fundloaner_adjust;
	private LinearLayout linear_fundloaner_adjust;
	private TextView tv_fund_result;
	private Button btn_fund_startcal;
	private EditText et_fundloaner_one_money;
	private EditText et_fundloaner_one_ratio;
	private EditText et_fundloaner_two_money;
	private EditText et_fundloaner_two_ratio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fund_cal);
		initUI();
	}

	private void initUI() {
		findViewById(R.id.title_left).setOnClickListener(this);
		((TextView) findViewById(R.id.title_name)).setText("公积金贷款金额计算器");
		// tv_fundloaner_one_base = (TextView)
		// findViewById(R.id.include_fund_base).findViewById(R.id.tv_fundloaner_one);
		// tv_fundloaner_two_base = (TextView)
		// findViewById(R.id.include_fund_base).findViewById(R.id.tv_fundloaner_two);
		// tv_fundloaner_one_balance = (TextView)
		// findViewById(R.id.include_fund_balance)
		// .findViewById(R.id.tv_fundloaner_one);
		// tv_fundloaner_two_balance = (TextView)
		// findViewById(R.id.include_fund_balance)
		// .findViewById(R.id.tv_fundloaner_two);

		et_fundloaner_one_money = (EditText) findViewById(R.id.include_fund_base)
				.findViewById(R.id.et_fundloaner_one_money);
		et_fundloaner_one_ratio = (EditText) findViewById(R.id.include_fund_base)
				.findViewById(R.id.et_fundloaner_one_ratio);
		et_fundloaner_two_money = (EditText) findViewById(R.id.include_fund_base)
				.findViewById(R.id.et_fundloaner_two_money);
		et_fundloaner_two_ratio = (EditText) findViewById(R.id.include_fund_base)
				.findViewById(R.id.et_fundloaner_two_ratio);
		et_fundloaner_one_balance = (EditText) findViewById(R.id.include_fund_base)
				.findViewById(R.id.et_fundloaner_one_balance);
		et_fundloaner_two_balance = (EditText) findViewById(R.id.include_fund_base)
				.findViewById(R.id.et_fundloaner_two_balance);

		et_fundloaner_year = (EditText) findViewById(R.id.et_fundloaner_year);

		btn_fund_startcal = (Button) findViewById(R.id.btn_fund_startcal);
		btn_fund_startcal.setOnClickListener(this);
		// 贷款金额计算结果
		tv_fund_result = (TextView) findViewById(R.id.tv_fund_result);
		// 选择流动性调整基数
		linear_fundloaner_adjust = (LinearLayout) findViewById(R.id.linear_fundloaner_adjust);
		linear_fundloaner_adjust.setOnClickListener(this);
		// 流动性调整选择结果
		tv_fundloaner_adjust = (TextView) findViewById(R.id.tv_fundloaner_adjust);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:// 后退
			finish();
			super.onBackPressed();
			break;
		case R.id.linear_fundloaner_adjust:// 选择流动性调整基数

			Intent intent = new Intent(this, AdjustActivity.class);
			intent.putExtra("type", 10);
			startActivityForResult(intent, 0);

			break;
		case R.id.btn_fund_startcal:// 开始计算

			double base_one_money = 0.0;
			double base_one_ratio = 0.0;
			Double base_one = 0.0;
			Double base_two = 0.0;
			double base_two_money = 0.0;
			double base_two_ratio = 0.0;
			Double balance_one = 0.0;
			Double balance_two = 0.0;
			double adjust = 0;
			int year = 0;

			if (!TextUtils.isEmpty(et_fundloaner_one_money.getText().toString().trim())) {
				base_one_money = Double.parseDouble(et_fundloaner_one_money.getText().toString().trim());
			}

			if (!TextUtils.isEmpty(et_fundloaner_one_ratio.getText().toString().trim())) {
				base_one_ratio = Double.parseDouble(et_fundloaner_one_ratio.getText().toString().trim());
			}

			if (!TextUtils.isEmpty(et_fundloaner_two_money.getText().toString().trim())) {
				base_two_money = Double.parseDouble(et_fundloaner_two_money.getText().toString().trim());
			}

			if (!TextUtils.isEmpty(et_fundloaner_two_ratio.getText().toString().trim())) {
				base_two_ratio = Double.parseDouble(et_fundloaner_two_ratio.getText().toString().trim());
			}

			if (!TextUtils.isEmpty(et_fundloaner_one_balance.getText().toString().trim())) {
				balance_one = Double.parseDouble(et_fundloaner_one_balance.getText().toString().trim());
			}

			if (!TextUtils.isEmpty(et_fundloaner_two_balance.getText().toString().trim())) {
				balance_two = Double.parseDouble(et_fundloaner_two_balance.getText().toString().trim());
			}

			if (!TextUtils.isEmpty(et_fundloaner_year.getText().toString().trim())) {
				year = Integer.parseInt(et_fundloaner_year.getText().toString().trim());
			}

			if (!TextUtils.isEmpty(tv_fundloaner_adjust.getText().toString())) {
				adjust = Double.parseDouble(tv_fundloaner_adjust.getText().toString());
			}

			if (base_one_ratio != 0) {
				base_one = base_one_money / (base_one_ratio * 0.01 * 2);
			}
			if (base_two_ratio != 0) {
				base_two = base_two_money / (base_two_ratio * 0.01 * 2);
			}

			// 判断
			if (TextUtils.isEmpty(et_fundloaner_one_money.getText().toString().trim())) {
				ToastUtil.toastShow(this, "请输入贷款人之一的公积金月缴金额");
				return;
			}

			if (TextUtils.isEmpty(et_fundloaner_one_ratio.getText().toString().trim())) {
				ToastUtil.toastShow(this, "请输入贷款人之一的公积金缴交比例");
				return;
			}

			if (TextUtils.isEmpty(et_fundloaner_one_balance.getText().toString().trim())) {
				ToastUtil.toastShow(this, "请输入贷款人之一的公积金账户余额");
				return;
			}

			if (TextUtils.isEmpty(et_fundloaner_two_money.getText().toString().trim())
					&& TextUtils.isEmpty(et_fundloaner_two_ratio.getText().toString().trim())
					&& TextUtils.isEmpty(et_fundloaner_two_balance.getText().toString().trim())) {

			} else {
				if (TextUtils.isEmpty(et_fundloaner_two_money.getText().toString().trim())) {
					ToastUtil.toastShow(this, "请输入贷款人之二的公积金月缴金额");
					return;
				}

				if (TextUtils.isEmpty(et_fundloaner_two_ratio.getText().toString().trim())) {
					ToastUtil.toastShow(this, "请输入贷款人之二的公积金缴交比例");
					return;
				}

				if (TextUtils.isEmpty(et_fundloaner_two_balance.getText().toString().trim())) {
					ToastUtil.toastShow(this, "请输入贷款人之二的公积金账户余额");
					return;
				}
			}

			if (year == 0) {
				ToastUtil.toastShow(this, "请输入贷款年限");
				return;
			}

			double result = ((base_one + base_two) * 12 * year * 0.35 + (balance_one + balance_two)) * adjust;
			String res = StringUtils.save2(result);
			tv_fund_result.setText(res + "元");
			// ToastUtil.toastShow(this, "计算完成");
			Toast.makeText(this, "计算完成", Toast.LENGTH_SHORT).show();

			// if
			// (!TextUtils.isEmpty(et_fundloaner_one_base.getText().toString().trim())
			// &&
			// !TextUtils.isEmpty(et_fundloaner_two_base.getText().toString().trim()))
			// {
			// // 两人贷款， 20万 =< 贷款额 <= 100万
			// if(result < 200000)
			// {
			// ToastUtil.toastShow(this, "公积金贷款金额最低20万");
			// }
			//
			// if(result > 1000000)
			// {
			// ToastUtil.toastShow(this, "两人共同申请公积金贷款金额最高100万");
			// }
			// }else
			// {
			// //个人贷款， 20万 =< 贷款额 <= 50万
			// if(result < 200000)
			// {
			// ToastUtil.toastShow(this, "公积金贷款金额最低20万");
			// }
			//
			// if(result > 1000000)
			// {
			// ToastUtil.toastShow(this, "个人公积金贷款金额最高50万");
			// }
			// }
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (data != null) {
				String adjust = data.getStringExtra("adjust");
				tv_fundloaner_adjust.setText(adjust);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
