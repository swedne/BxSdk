package com.zorasun.fangchanzhichuang.section.account;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NiChengActivity extends BaseActivity implements OnClickListener {

	private EditText etNickName;
	public static int NICHENG = 111;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ni_cheng);
		initView();
	}

	private void initView() {
		findViewById(R.id.title_left).setOnClickListener(this);
		TextView tvName = (TextView) findViewById(R.id.title_name);
		tvName.setText("修改昵称");
		View titleRight = findViewById(R.id.title_right_tv);
		titleRight.setVisibility(View.VISIBLE);
		titleRight.setOnClickListener(this);
		etNickName = (EditText) findViewById(R.id.et_nickName);
		removeEmoji(etNickName);
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
								Toast.makeText(NiChengActivity.this, "不支持输入Emoji表情符号", Toast.LENGTH_SHORT).show();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			super.onBackPressed();
			finish();
			break;
		case R.id.title_right_tv:
			changeNickName();
			break;

		default:
			break;
		}

	}

	private void changeNickName() {
		final String nickName = etNickName.getText().toString().trim();
		if (nickName.equals("")) {
			ToastUtil.toastShow(this, "昵称不能为空");
			return;
		}
		if (nickName.length() > 20) {
			ToastUtil.toastShow(this, "昵称不能超过20个字符");
			return;
		}
		AccountApi.getInstance().changeNickName(this, nickName, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				AccountConfig.setAccountName(nickName);
				setResult(0, getIntent().putExtra("nickName", nickName));
				finish();
				ToastUtil.toastShow(NiChengActivity.this, "修改成功");
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

		// ApiClient.changeNickName(this, params, heads, new ResponseListener()
		// {
		//
		// @Override
		// public void onResponse(String arg0) {
		// Userinfo info = GsonUtils.parseJSON(arg0, Userinfo.class);
		// if (info.getCode() == 1) {
		// sp.edit().putString("nickName", nickName).commit();
		// finish();
		// }
		// ToastUtil.toastShow(NiChengActivity.this, info.getMsg());
		// }
		//
		// @Override
		// public void onErrorResponse(VolleyError arg0) {
		// ToastUtil.toastShow(NiChengActivity.this, "网络异常");
		// }
		// });

	}

}
