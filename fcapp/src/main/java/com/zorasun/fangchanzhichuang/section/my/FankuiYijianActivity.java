package com.zorasun.fangchanzhichuang.section.my;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.index.IndexApi;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FankuiYijianActivity extends BaseActivity {

	private TextView tvAdvise;
	private EditText etAdvise;
	private int intExtra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fankui_yijian);
		initView();
	}

	private void initView() {
		intExtra = getIntent().getIntExtra("secondHouseReport", -1);
		etAdvise = (EditText) findViewById(R.id.et_advise);
		TextView tvName = (TextView) findViewById(R.id.title_name);
		if (intExtra != -1) {
			tvName.setText("举报");
			etAdvise.setHint("请输入内容");
		} else {
			tvName.setText("意见反馈");
			etAdvise.setHint("请输入意见");

		}
		tvAdvise = (TextView) findViewById(R.id.title_right_tv);
		tvAdvise.setTextSize(14);
		tvAdvise.setVisibility(View.VISIBLE);
		tvAdvise.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setData();
			}

		});
		findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				FankuiYijianActivity.super.onBackPressed();
			}
		});
		removeEmoji(etAdvise);
	}

	private void removeEmoji(final EditText et) {
		if (et != null) {

			et.addTextChangedListener(new TextWatcher() {
				private boolean resetText;
				private int cursorPos;
				private String inputAfterText;

				@Override
				public void beforeTextChanged(CharSequence s, int start, int before, int count) {

					if (!resetText) {
						cursorPos = et.getSelectionEnd();
						// 这里用s.toString()而不直接用s是因为如果用s，
						// 那么，inputAfterText和s在内存中指向的是同一个地址，s改变了，
						// inputAfterText也就改变了，那么表情过滤就失败了
						inputAfterText = s.toString();
					}
				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {

					if (!resetText) {
						if (before != 0) {
							return;
						}
						if (count >= 2) {// 表情符号的字符长度最小为2

							CharSequence input = s.subSequence(cursorPos, cursorPos + count);

							if (StringUtils.containsEmoji(input.toString())) {
								resetText = true;
								Toast.makeText(FankuiYijianActivity.this, "不支持输入Emoji表情符号", Toast.LENGTH_SHORT).show();
								// 是表情符号就将文本还原为输入表情符号之前的内容
								et.setText(inputAfterText);
								CharSequence text = et.getText();
								if (text instanceof Spannable) {
									Spannable spanText = (Spannable) text;
									Selection.setSelection(spanText, text.length());
								}
							}
						}
					} else {
						resetText = false;
					}

				}

				@Override
				public void afterTextChanged(Editable editable) {

				}

			});
		}
	}

	private void setData() {
		String accountPhone = AccountConfig.getAccountPhone();
		String accountId = AccountConfig.getAccountId();
		String advise = etAdvise.getText().toString();
		if (intExtra == -1) {
			if (TextUtils.isEmpty(advise)) {
				ToastUtil.toastShow(FankuiYijianActivity.this, "请输入您的意见");
				return;
			}
			MyApi.getInstance().requestFankuiYijian(this, accountId, advise, accountPhone, new RequestCallBack() {

				@Override
				public void onSuccess(int code, String msg, Object object) {
					ToastUtil.toastShow(FankuiYijianActivity.this, "提交成功");
					finish();
				}

				@Override
				public void onNetworkError() {

				}

				@Override
				public void onFailure(int code, String msg, Object object) {
					ToastUtil.toastShow(FankuiYijianActivity.this, msg);

				}
			});

		} else {
			IndexApi.getInstance().requestSecondHouseReport(this, accountId, intExtra, advise, new RequestCallBack() {

				@Override
				public void onSuccess(int code, String msg, Object object) {
					ToastUtil.toastShow(FankuiYijianActivity.this, "举报成功");
					finish();
				}

				@Override
				public void onNetworkError() {

				}

				@Override
				public void onFailure(int code, String msg, Object object) {
					ToastUtil.toastShow(FankuiYijianActivity.this, msg);

				}
			});
		}

	}
}
