package com.zorasun.fangchanzhichuang.section.my;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;

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

public class CancleDemandActivity extends BaseActivity {

	private EditText etContent;
	private int demandId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cancle_demand);
		initView();
	}

	private void initData() {
		demandId = getIntent().getIntExtra("demandId", 0);
	}

	@Override
	protected void onStart() {
		initData();
		super.onStart();
	}

	private void initView() {
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		tvTitle.setText("取消需求");
		View sure = findViewById(R.id.title_right_tv);
		sure.setVisibility(View.VISIBLE);
		etContent = (EditText) findViewById(R.id.et_content);
		removeEmoji(etContent);
		sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(etContent.getText().toString())) {
					ToastUtil.toastShow(CancleDemandActivity.this, "请输入取消原因");
					return;
				}
				MyApi.getInstance().requestCancleDemand(CancleDemandActivity.this, demandId,
						etContent.getText().toString(), new RequestCallBack() {

					@Override
					public void onSuccess(int code, String msg, Object object) {
						ToastUtil.toastShow(CancleDemandActivity.this, msg);
					}

					@Override
					public void onNetworkError() {

						ToastUtil.toastShow(CancleDemandActivity.this, R.string.net_error);
					}

					@Override
					public void onFailure(int code, String msg, Object object) {
						ToastUtil.toastShow(CancleDemandActivity.this, msg);
						finish();
					}
				});
			}
		});
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
								Toast.makeText(CancleDemandActivity.this, "不支持输入Emoji表情符号", Toast.LENGTH_SHORT).show();
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

}
