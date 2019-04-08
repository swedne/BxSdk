package com.zorasun.fangchanzhichuang.section.index;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.section.index.entity.PropertyEntity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class ChanQuanYanZhengActivity extends BaseActivity implements OnClickListener {

	private EditText etCustom;
	private EditText etXiaDiNum;
	private EditText etXiaGuoNum;
	private EditText etMinNum;
	private EditText etBuDongNum;
	private EditText etHost;
	private EditText etIdentityCard;
	private String propertyNo;
	private String idCardNo;
	private String landlord;
	private int checkedRadioButtonId = R.id.radio0;
	private View view1;
	private View view2;
	private View view3;
	private View view4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chan_quan_yan_zheng);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initView() {
		TextView tv_title = (TextView) findViewById(R.id.title_name);
		tv_title.setText("产权验证");
		findViewById(R.id.title_left).setOnClickListener(this);
		findViewById(R.id.bt_search).setOnClickListener(this);
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		etCustom = (EditText) findViewById(R.id.et_custom);
		etXiaDiNum = (EditText) findViewById(R.id.et_xiadi_num);
		etXiaGuoNum = (EditText) findViewById(R.id.et_xiaguo_num);
		etMinNum = (EditText) findViewById(R.id.et_min_num);
		etBuDongNum = (EditText) findViewById(R.id.et_budong_num);
		etIdentityCard = (EditText) findViewById(R.id.et_identityCard);
		etIdentityCard.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// InputMethodManager imm = (InputMethodManager)
				// ChanQuanYanZhengActivity.this
				// .getSystemService(Context.INPUT_METHOD_SERVICE);
				// imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				if (hasFocus) {
					etIdentityCard.setHint("请严格按产权证格式输入，包括半角括号、空格及字母等");
					if (view1.getVisibility() == View.VISIBLE) {
						//数字没有八位在前面自动补0
						String customStr = etCustom.getText().toString();
						if (!TextUtils.isEmpty(customStr)) {
							Matcher match = Pattern.compile("\\d").matcher(customStr);
							int num = 0;
							while (match.find()) {
								num++;
							}
							if (num < 8) {
								char[] charArray = customStr.toCharArray();
								for (int i = 0; i < charArray.length; i++) {
									if (Character.isDigit(charArray[i])) {
										StringBuffer sb = new StringBuffer();
										for (int j = 0; j < 8 - num; j++) {
											sb.append("0");
										}
										String str = sb.toString();
										StringBuffer result = new StringBuffer(customStr).insert(i, str);
										etCustom.setText(result);
										break;
									}
								}
							}
							if (customStr.contains("共有")) {
								ToastUtil.toastShow(ChanQuanYanZhengActivity.this, "请使用主证的产权证编号");
							}
						}
					} else if (view3.getVisibility() == View.VISIBLE) {
						String xiaguoStr = etXiaGuoNum.getText().toString();
						if (!TextUtils.isEmpty(xiaguoStr)) {
							if (xiaguoStr.contains("-")) {
								int index = xiaguoStr.indexOf("-");
								String result = xiaguoStr.substring(0, index);
								etXiaGuoNum.setText(result);
							}
						}
					}
				} else {
					etIdentityCard.setHint("请输入身份证件号");
				}
			}
		});

		etHost = (EditText) findViewById(R.id.et_host);
		etHost.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					etHost.setHint("请严格按产权证格式输入，包括半角括号、空格及字母等");
					if (view1.getVisibility() == View.VISIBLE) {
						String customStr = etCustom.getText().toString();
						if (!TextUtils.isEmpty(customStr)) {
							Matcher match = Pattern.compile("\\d").matcher(customStr);
							int num = 0;
							while (match.find()) {
								num++;
							}
							if (num < 8) {
								char[] charArray = customStr.toCharArray();
								for (int i = 0; i < charArray.length; i++) {
									if (Character.isDigit(charArray[i])) {
										StringBuffer sb = new StringBuffer();
										for (int j = 0; j < 8 - num; j++) {
											sb.append("0");
										}
										String str = sb.toString();
										StringBuffer result = new StringBuffer(customStr).insert(i, str);
										etCustom.setText(result);
										break;
									}
								}
							}
							if (customStr.contains("共有")) {
								ToastUtil.toastShow(ChanQuanYanZhengActivity.this, "请使用主证的产权证编号");
							}
						}
					} else if (view3.getVisibility() == View.VISIBLE) {
						String xiaguoStr = etXiaGuoNum.getText().toString();
						if (!TextUtils.isEmpty(xiaguoStr)) {
							if (xiaguoStr.contains("-")) {
								int index = xiaguoStr.indexOf("-");
								String result = xiaguoStr.substring(0, index);
								etXiaGuoNum.setText(result);
							}
						}
					}
				} else {
					etHost.setHint("请输入房东姓名");
				}
			}
		});
		view1 = findViewById(R.id.view1);
		view2 = findViewById(R.id.view2);
		view3 = findViewById(R.id.view3);
		view4 = findViewById(R.id.view4);
		TextView tvRed = (TextView) findViewById(R.id.tv_red);
		SpannableString spannableString = new SpannableString(tvRed.getText().toString());
		spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tvRed.setText(spannableString);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				checkedRadioButtonId = group.getCheckedRadioButtonId();
				if (checkedRadioButtonId == R.id.radio0) {
					view1.setVisibility(View.VISIBLE);
					view2.setVisibility(View.GONE);
					view3.setVisibility(View.GONE);
					view4.setVisibility(View.GONE);
				} else if (checkedRadioButtonId == R.id.radio1) {
					view1.setVisibility(View.GONE);
					view2.setVisibility(View.VISIBLE);
					view3.setVisibility(View.GONE);
					view4.setVisibility(View.GONE);
					etXiaDiNum.requestFocus();

				} else if (checkedRadioButtonId == R.id.radio2) {
					view1.setVisibility(View.GONE);
					view2.setVisibility(View.GONE);
					view3.setVisibility(View.VISIBLE);
					view4.setVisibility(View.GONE);
					etXiaGuoNum.requestFocus();
				} else if (checkedRadioButtonId == R.id.radio3) {
					view1.setVisibility(View.GONE);
					view2.setVisibility(View.GONE);
					view3.setVisibility(View.GONE);
					view4.setVisibility(View.VISIBLE);
					etMinNum.requestFocus();
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		case R.id.bt_search:
			search();
			break;
		default:
			break;
		}
	}

	private void search() {
		RequestParams params = new RequestParams();
		if (checkedRadioButtonId == R.id.radio0) {
			propertyNo = etCustom.getText().toString().trim();
		} else if (checkedRadioButtonId == R.id.radio1) {
			propertyNo = "厦地房证第" + etXiaDiNum.getText().toString().trim() + "号";
		} else if (checkedRadioButtonId == R.id.radio2) {
			propertyNo = "厦国土房证第" + etXiaGuoNum.getText().toString().trim() + "号";
		} else if (checkedRadioButtonId == R.id.radio3) {
			propertyNo = "闽（" + etMinNum.getText().toString().trim() + "）厦门市不动产权第"
					+ etBuDongNum.getText().toString().trim() + "号";
		}
		if (etCustom.getText().toString().contains("共有")) {
			ToastUtil.toastShow(ChanQuanYanZhengActivity.this, "请使用主证的产权证编号");
			return;
		}

		idCardNo = etIdentityCard.getText().toString().trim();
		landlord = etHost.getText().toString().trim();
		if (TextUtils.isEmpty(idCardNo) && TextUtils.isEmpty(landlord)) {
			ToastUtil.toastShow(this, "请至少填写身份证件号或者房东姓名");
			return;
		}
		if (!TextUtils.isEmpty(idCardNo)) {
			params.put("idCardNo", idCardNo);
		}
		if (!TextUtils.isEmpty(propertyNo)) {
			params.put("propertyNo", propertyNo);
		}
		if (!TextUtils.isEmpty(landlord)) {
			params.put("landlord", landlord);
		}
		IndexApi.getInstance().requestChanQuan(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				PropertyEntity propertyEntity = (PropertyEntity) object;
				if (!TextUtils.isEmpty(propertyEntity.getContent())) {
					Intent intent = new Intent(ChanQuanYanZhengActivity.this, IndexFangChanResultActivity.class);
					intent.putExtra("address", propertyEntity.getContent());
					startActivity(intent);
				} else {
					ToastUtil.toastShow(ChanQuanYanZhengActivity.this, "注意核对，若输入信息不完整，可能会导致产权验证结果不准确");
				}
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(ChanQuanYanZhengActivity.this, R.string.net_error);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				// Intent intent = new Intent(ChanQuanYanZhengActivity.this,
				// IndexFangChanResultActivity.class);
				// intent.putExtra("address", "");
				// startActivity(intent);
				ToastUtil.toastShow(ChanQuanYanZhengActivity.this, "注意核对，若输入信息不完整，可能会导致产权验证结果不准确");
			}
		});

	}

}
