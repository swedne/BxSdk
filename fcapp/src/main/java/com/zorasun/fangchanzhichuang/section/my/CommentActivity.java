package com.zorasun.fangchanzhichuang.section.my;

import java.util.List;

import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.RatingBarView;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.index.IndexApi;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.BrokerInfo_;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.SpecialSkillList;
import com.zorasun.fangchanzhichuang.section.my.entiy.DemandDetailEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.DemandDetailEntity.Content;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends BaseActivity implements OnClickListener {

	private RequestParams params;
	private int demandId;
	private int brokerId;
	private EditText etContent;
	private RatingBarView ratingBar1;
	private RatingBarView ratingBar2;
	private RatingBarView ratingBar3;
	private TextView tvBroker;
	private Content content;
	private TextView tvAddress;
	private View isExpert;
	private View line;
	private TextView tvCommunity;
	private LinearLayout llSkill;
	private ImageView imgBroker;
	private int flag;
	protected com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.Content content2;
	protected BrokerInfo_ brokerInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		flag = getIntent().getIntExtra("flag", 0);
		DemandDetailEntity demandDetailEntity = (DemandDetailEntity) getIntent().getSerializableExtra("content");
		content = demandDetailEntity.getContent();
		demandId = content.getDemandId();
		brokerId = content.getBrokerId();
		initView();
		initData();
	}

	private void initData() {
		IndexApi.getInstance().requestBrokerInfo(this, brokerId, AccountConfig.getAccountId(), new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				BrokerInfoEntity brokerInfoEntity = (BrokerInfoEntity) object;
				content2 = brokerInfoEntity.getContent();
				brokerInfo = content2.getBrokerInfo();
				setView();
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(CommentActivity.this, R.string.net_error);

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(CommentActivity.this, msg);
			}
		});
	}

	private void initView() {
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		if (flag == 1) {
			tvTitle.setText("评价");
		} else {
			tvTitle.setText("修改评价");
		}
		View btnSure = findViewById(R.id.title_right_tv);
		btnSure.setOnClickListener(this);
		btnSure.setVisibility(View.VISIBLE);
		findViewById(R.id.title_left).setOnClickListener(this);
		etContent = (EditText) findViewById(R.id.et_content);
		ratingBar1 = (RatingBarView) findViewById(R.id.ratingBarGoods1);
		ratingBar2 = (RatingBarView) findViewById(R.id.ratingBarGoods2);
		ratingBar3 = (RatingBarView) findViewById(R.id.ratingBarGoods3);
		tvBroker = (TextView) findViewById(R.id.tv_view_demand_detail_name);
		tvAddress = (TextView) findViewById(R.id.tv_view_demand_detail_address);
		isExpert = findViewById(R.id.tv_view_demand_detail_leavel);
		tvCommunity = (TextView) findViewById(R.id.tv_view_demand_detail_company01);
		llSkill = (LinearLayout) findViewById(R.id.ll_skill);
		line = findViewById(R.id.line);
		imgBroker = (ImageView) findViewById(R.id.img_broker);
		removeEmoji(etContent);
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
								Toast.makeText(CommentActivity.this, "不支持输入Emoji表情符号", Toast.LENGTH_SHORT).show();
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

	private void setView() {
		if (brokerId != 0) {
			tvBroker.setText(brokerInfo.getBrokerName());
			String businessName = brokerInfo.getBusinessService().getBusinessName();
			tvAddress.setText(businessName);
			tvCommunity.setText(brokerInfo.getHarkBackHouse());
			if (brokerInfo.getIsExpert() != null) {
				if (brokerInfo.getIsExpert() == 1) {
					isExpert.setVisibility(View.VISIBLE);
					line.setVisibility(View.VISIBLE);
				} else {
					isExpert.setVisibility(View.GONE);
					line.setVisibility(View.GONE);
				}
			}
			if (!TextUtils.isEmpty(brokerInfo.getHeadUrl())) {
				AsyncImageLoader.setAsynImages(imgBroker, brokerInfo.getHeadUrl());
			} else {
				imgBroker.setImageResource(R.drawable.wutu);
			}
		}
		List<SpecialSkillList> specialSkillList = brokerInfo.getSpecialSkillList();
		if (specialSkillList.size() > 0) {
			for (int i = 0; i < specialSkillList.size(); i++) {
				View childLayout = getLayoutInflater().inflate(R.layout.childview_jingjiren_item, null);
				TextView tvSkill = (TextView) childLayout.findViewById(R.id.tv_skill);
				tvSkill.setText(specialSkillList.get(i).getSpeciaName());
				llSkill.addView(childLayout);
			}
		} else {
			View childLayout = getLayoutInflater().inflate(R.layout.childview_jingjiren_item, null);
			TextView tvSkill = (TextView) childLayout.findViewById(R.id.tv_skill);
			tvSkill.setText("暂无");
			llSkill.addView(childLayout);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		case R.id.title_right_tv:
			commit();
			break;
		default:
			break;
		}
	}

	private void commit() {
		params = new RequestParams();
		if (flag == 2) {
			params.put("appraiseId", content.getAppraiseInfo().getAppraiseId());
		}
		params.put("demendId", demandId);
		params.put("brokerId", brokerId);
		int validityNum = ratingBar1.getStarCount();
		if (validityNum == 0) {
			validityNum = 5;
		}
		params.put("validity", validityNum);
		int serviceNum = ratingBar2.getStarCount();
		if (serviceNum == 0) {
			serviceNum = 5;
		}
		params.put("service", serviceNum);
		int professionalNum = ratingBar3.getStarCount();
		if (professionalNum == 0) {
			professionalNum = 5;
		}
		params.put("professional", professionalNum);
		params.put("appraiseContent", etContent.getText().toString());
		if (TextUtils.isEmpty(etContent.getText().toString())) {
			ToastUtil.toastShow(this, "评价内容不能为空");
			return;
		}
		MyApi.getInstance().requestEvaluateDemand(this, params, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				if (flag == 2) {
					ToastUtil.toastShow(CommentActivity.this, "修改评价成功");
				} else {
					ToastUtil.toastShow(CommentActivity.this, "评价成功");
				}
				finish();
			}

			@Override
			public void onNetworkError() {

			}

			@Override
			public void onFailure(int code, String msg, Object object) {

			}
		});
	}
}
