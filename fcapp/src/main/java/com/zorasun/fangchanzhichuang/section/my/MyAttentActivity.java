package com.zorasun.fangchanzhichuang.section.my;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.commonadapter.Common2Adapter;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.index.JingjirenXqActivity;
import com.zorasun.fangchanzhichuang.section.my.entiy.MyAttentionListEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.MyAttentionListEntity.AttentionList_;
import com.zorasun.fangchanzhichuang.section.my.entiy.MyAttentionListEntity.BrokerInfo;
import com.zorasun.fangchanzhichuang.section.my.entiy.MyAttentionListEntity.SpecialSkillList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MyAttentActivity extends BaseActivity
		implements OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private int pageNum;
	private CustomView customview;
	private List<AttentionList_> attentionList = new ArrayList<>();
	private MyAdapter adapter;
	private PullToRefreshListView ptrListView;
	private ListView mListView;
	private boolean isRefresh;
	private int pageCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attent);
		initView();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		attentionList.clear();
		pageNum = 1;
		initData();
	}

	private void initData() {
		MyApi.getInstance().requestAttentionList(this, pageNum, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				MyAttentionListEntity brokerListEntity = (MyAttentionListEntity) object;
				if (isRefresh) {
					attentionList.clear();
				}
				attentionList.addAll(brokerListEntity.getContent().getAttentionList());
				if (attentionList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				pageCount = brokerListEntity.getContent().getPageCount();

				if (pageCount <= pageNum) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}

				adapter.notifyDataSetChanged();
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(MyAttentActivity.this, R.string.net_error);
				ptrListView.postDelayed(new Runnable() {

					@Override
					public void run() {
						ptrListView.onRefreshComplete();
					}
				}, 1000);
				ptrListView.setMode(Mode.DISABLED);
				customview.showLoadStateView(CustomView.STATE_ERROR);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				ToastUtil.toastShow(MyAttentActivity.this, msg);
				ptrListView.setMode(Mode.DISABLED);
				customview.showLoadStateView(CustomView.STATE_EMPTY);
			}
		});

	}

	private void initView() {
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("我的关注");
		ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);
		mListView = ptrListView.getRefreshableView();

		adapter = new MyAdapter(this, attentionList, R.layout.jingjiren_item);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
	}

	class MyAdapter extends Common2Adapter<AttentionList_> {

		public MyAdapter(Context context, List<AttentionList_> mDatas, int itemLayoutId) {
			super(context, mDatas, itemLayoutId);
		}

		@SuppressLint("InflateParams")
		@Override
		public void convert(View helper, AttentionList_ item, int position) {
			ImageView imgLevel = (ImageView) helper.findViewById(R.id.img_leavel);
			imgLevel.setVisibility(View.GONE);
			// switch (position) {
			// case 0:
			// imgLevel.setImageResource(R.drawable.jin);
			// break;
			// case 1:
			// imgLevel.setImageResource(R.drawable.yin);
			// break;
			// case 2:
			// imgLevel.setImageResource(R.drawable.tong);
			// break;
			// default:
			// imgLevel.setVisibility(View.GONE);
			// break;
			// }
			BrokerInfo brokerInfo = item.getBrokerInfo();
			helper.findViewById(R.id.tv_attact).setVisibility(View.VISIBLE);
			TextView tvBrokerName = (TextView) helper.findViewById(R.id.tv_brokerName);
			TextView tvRealName = (TextView) helper.findViewById(R.id.tv_realName);
			TextView tvIsExpert = (TextView) helper.findViewById(R.id.tv_isExpert);
			TextView tvHarkBackHouse = (TextView) helper.findViewById(R.id.tv_harkBackHouse);
			TextView tvBusinessName = (TextView) helper.findViewById(R.id.tv_businessname);
			ImageView imgBroker = (ImageView) helper.findViewById(R.id.img_broker);
			View line = helper.findViewById(R.id.line);
			LinearLayout llSkill = (LinearLayout) helper.findViewById(R.id.ll_biaoqian);
			tvBrokerName.setText(item.getBrokerName());
			tvRealName.setText(brokerInfo.getRealName());
			tvHarkBackHouse.setText(brokerInfo.getHarkBackHouse());
			tvBusinessName.setText(brokerInfo.getBusinessName());
			if (item.getIsExpert() == 1) {
				tvIsExpert.setVisibility(View.VISIBLE);
				line.setVisibility(View.VISIBLE);
			} else {
				tvIsExpert.setVisibility(View.GONE);
				line.setVisibility(View.GONE);
			}
			if (!TextUtils.isEmpty(item.getHeadUrl())) {
				AsyncImageLoader.setAsynImages(imgBroker, item.getHeadUrl());
			} else {
				imgBroker.setImageResource(R.drawable.wutu);
			}
			List<SpecialSkillList> specialSkillList = brokerInfo.getSpecialSkillList();
			for (int i = 0; i < specialSkillList.size(); i++) {
				View childLayout = getLayoutInflater().inflate(R.layout.childview_jingjiren_item, null);
				TextView tvSkill = (TextView) childLayout.findViewById(R.id.tv_skill);
				tvSkill.setText(specialSkillList.get(i).getSpeciaName());
				llSkill.addView(childLayout);
			}
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		position -= mListView.getHeaderViewsCount();
		int brokerId = attentionList.get(position).getId();
		Intent intent = new Intent(this, JingjirenXqActivity.class);
		intent.putExtra("brokerId", brokerId);
		startActivity(intent);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum = 1;
		isRefresh = true;
		initData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum++;
		isRefresh = false;
		initData();
	}

	@Override
	public void onLoadData() {
		initData();
	}

}
