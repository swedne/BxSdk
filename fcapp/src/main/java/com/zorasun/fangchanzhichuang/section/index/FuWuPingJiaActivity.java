package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.commonadapter.Common2Adapter;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CircleImageView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.RatingBarView;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.index.entity.EvaluateEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.EvaluateEntity.AppraiseList;
import com.zorasun.fangchanzhichuang.section.index.entity.EvaluateEntity.Content;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class FuWuPingJiaActivity extends BaseActivity
		implements OnClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private List<AppraiseList> appraiseList = new ArrayList<>();
	private MyAdapter adapter;
	private TextView tvValidity;
	private TextView tvService;
	private TextView tvProfessional;
	private Content content;
	private View noData;
	private PullToRefreshListView ptrListView;
	private ListView mListView;
	private int page = 1;
	private boolean isRefresh;
	private int pageCount;
	private CustomView customview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fu_wu_ping_jia);

		initView();
		initData();
	}

	private void initData() {
		int brokerId = getIntent().getIntExtra("brokerId", -1);
		IndexApi.getInstance().requestBrokerEvaluate(this, page, brokerId, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				EvaluateEntity enEvaluateEntity = (EvaluateEntity) object;
				content = enEvaluateEntity.getContent();
				if (isRefresh) {
					appraiseList.clear();
				}
				pageCount = content.getPageCount();
				if (pageCount <= page) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}

				if (content.getAppraiseList() != null) {
					appraiseList.addAll(content.getAppraiseList());
					if (appraiseList.size() <= 0) {
						customview.showLoadStateView(CustomView.STATE_EMPTY);
					}
				}
				if (appraiseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				adapter.notifyDataSetChanged();
				Double validity = content.getValidity();
				if (validity != null) {
					if (validity == 0) {
						tvValidity.setText("0");
					} else {
						tvValidity.setText(validity + "");
					}
				} else {
					tvValidity.setText("0");
				}
				Double service = content.getService();
				if (service != null) {
					if (service == 0) {
						tvService.setText("0");
					} else {
						tvService.setText(service + "");
					}
				} else {
					tvService.setText("0");

				}
				Double professional = content.getProfessional();
				if (professional != null) {
					if (professional == 0) {
						tvProfessional.setText("0");
					} else {
						tvProfessional.setText(professional + "");
					}
				} else {
					tvProfessional.setText("0");
				}

			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(FuWuPingJiaActivity.this, getResources().getString(R.string.net_error));
				customview.showLoadStateView(CustomView.STATE_ERROR);
				ptrListView.postDelayed(new Runnable() {

					@Override
					public void run() {
						ptrListView.onRefreshComplete();
					}
				}, 1000);
				ptrListView.setMode(Mode.DISABLED);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(FuWuPingJiaActivity.this, msg);
				ptrListView.onRefreshComplete();
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
			}
		});

	}

	private void initView() {
		TextView tvTitle = (TextView) findViewById(R.id.title_name);
		tvTitle.setText("服务评价");
		tvValidity = (TextView) findViewById(R.id.textView2);
		tvService = (TextView) findViewById(R.id.tv_service);
		noData = findViewById(R.id.nodate);
		tvProfessional = (TextView) findViewById(R.id.tv_professional);
		findViewById(R.id.title_left).setOnClickListener(this);
		findViewById(R.id.ptr_listView);
		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);
		mListView = ptrListView.getRefreshableView();
		adapter = new MyAdapter(this, appraiseList, R.layout.list_item_fuwupingjia);
		mListView.setAdapter(adapter);
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
	}

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;

		default:
			break;
		}
	}

	class MyAdapter extends Common2Adapter<AppraiseList> {

		public MyAdapter(Context context, List<AppraiseList> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@Override
		public void convert(View helper, AppraiseList item, int position) {
			RatingBarView validityStar = (RatingBarView) helper.findViewById(R.id.ratingBarGoods2);
			RatingBarView serviceStar = (RatingBarView) helper.findViewById(R.id.ratingBarGoods3);
			RatingBarView proferssionalStar = (RatingBarView) helper.findViewById(R.id.ratingBarGoods1);
			TextView tvBrokerName = (TextView) helper.findViewById(R.id.tv_brokerName);
			TextView tvContent = (TextView) helper.findViewById(R.id.textView2);
			TextView tvTime = (TextView) helper.findViewById(R.id.TextView04);
			CircleImageView imgBroker = (CircleImageView) helper.findViewById(R.id.img_broker);
			if (!TextUtils.isEmpty(item.getUrl())) {
				AsyncImageLoader.setAsynAvatarImagesInfo(imgBroker, item.getUrl());
			}
			tvBrokerName.setText(item.getName());
			validityStar.setStar(item.getValidity(), false);

			validityStar.setClickable(false);
			serviceStar.setStar(item.getService(), false);
			serviceStar.setClickable(false);
			proferssionalStar.setStar(item.getProfessional(), true);
			proferssionalStar.setClickable(false);
			tvContent.setText(item.getAppraiseContent());
			tvTime.setText("" + item.getAppraiseTime());
		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		isRefresh = true;
		initData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page++;
		isRefresh = false;
		initData();
	}

	@Override
	public void onLoadData() {
		initData();
	}

}
