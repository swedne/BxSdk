package com.zorasun.fangchanzhichuang.section.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.message.entity.MyMessageEntity;
import com.zorasun.fangchanzhichuang.section.message.entity.MyMessageEntity.AdvicesAll;
import com.zorasun.fangchanzhichuang.section.my.DemandDetaliActivity;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class AllMsgsFragment extends BaseFragment
		implements OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private LayoutInflater inflater;
	private Myadapter adapter;
	private PullToRefreshListView ptrListView;
	private CustomView customview;
	private ListView mListView;
	private int pageNum=1;
	private boolean isRefresh;
	private List<AdvicesAll> advicesAll = new ArrayList<>();

	public AllMsgsFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.fragment_blank, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		adapter = new Myadapter();
		ptrListView = (PullToRefreshListView) view.findViewById(R.id.ptr_listview1);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);

		customview = (CustomView) view.findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);

		mListView = ptrListView.getRefreshableView();

		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
	}

	// public void settab(int tab) {
	//
	// MessageFragment2.position = tab;
	//
	// }

	@Override
	public void onResume() {
		Log.e("=====", "BlankFragment");
		advicesAll.clear();
		adapter.notifyDataSetChanged();
		if (MessageFragment2.position == 0) {
			mListView.setSelection(1);
			initData();
		}
		super.onResume();
	}

	public void initData() {
		MessageApi.getInstance().requestMessageList(getActivity(), 2, pageNum, new RequestCallBack() {

			private Integer pageCount;

			@Override
			public void onSuccess(int code, String msg, Object object) {
				ptrListView.onRefreshComplete();
				MyMessageEntity myMessageEntity = (MyMessageEntity) object;
				if (isRefresh) {
					advicesAll.clear();
				}
				pageCount = myMessageEntity.getContent().getPageCount();
				if (pageCount <= pageNum) {
					ptrListView.setMode(Mode.PULL_FROM_START);
				} else {
					ptrListView.setMode(Mode.BOTH);
				}

				advicesAll.addAll(myMessageEntity.getContent().getAdvicesAll());
				if (advicesAll.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
					ptrListView.setMode(Mode.DISABLED);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(getActivity(), getResources().getString(R.string.net_error));
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
				ToastUtil.toastShow(getActivity(), msg);
				customview.showLoadStateView(CustomView.STATE_EMPTY);
				ptrListView.setMode(Mode.DISABLED);
			}
		});
	}

	class Myadapter extends BaseAdapter {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return advicesAll.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = inflater.inflate(R.layout.item_messagelist, null);
			ImageView imgYuanDian = (ImageView) inflate.findViewById(R.id.img_yuandian);
			ImageView imgPic = (ImageView) inflate.findViewById(R.id.img_item_messageheadimg);
			TextView messageContent = (TextView) inflate.findViewById(R.id.tv_item_messagecontent);
			TextView tvTitle = (TextView) inflate.findViewById(R.id.tv_item_title);
			TextView tvTime = (TextView) inflate.findViewById(R.id.tv_item_messagedate);

			AdvicesAll advices = advicesAll.get(position);
			tvTitle.setText(advices.getTitle());
			messageContent.setText(advices.getContent());
			tvTime.setText(convertTime(advices.getAdvicesTime()));

			if (advices.getType().equals("0")) {
				imgPic.setImageResource(R.drawable.tongzhi);
			} else if (advices.getType().equals("1")) {
				imgPic.setImageResource(R.drawable.gongggao);
			}
			if (advices.getIsRead() == 0) {
				imgYuanDian.setVisibility(View.VISIBLE);
			} else {
				tvTitle.setTextColor(Color.parseColor("#AAAAAA"));
				messageContent.setTextColor(Color.parseColor("#AAAAAA"));
				imgYuanDian.setVisibility(View.GONE);
			}

			return inflate;
		}
	}

	private String convertTime(long mills) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(mills);
		return formatter.format(calendar.getTime());
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		position -= mListView.getHeaderViewsCount();
		AdvicesAll advicesInfo = advicesAll.get(position);
		Integer advicesId = advicesInfo.getAdvicesId();
		Intent intent = null;
		if (advicesInfo.getType().equals("0")) {
			intent = new Intent(getActivity(), DemandDetaliActivity.class);
			String id = advicesInfo.getMsgId();
			int demandId = Integer.parseInt(id);
			intent.putExtra("demandId", demandId);
			intent.putExtra("advicesId", advicesId);
		} else {
			intent = new Intent(getActivity(), GongGaoActivity.class);
			intent.putExtra("advicesId", advicesId);
			intent.putExtra("title", advicesInfo.getTitle());
			intent.putExtra("time", advicesInfo.getAdvicesTime());
			intent.putExtra("content", advicesInfo.getContent());
			intent.putExtra("picUrl", advicesInfo.getPicUrl());
		}
		advicesAll.clear();
		getActivity().startActivity(intent);

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

	@Override
	public void initView() {
		if (advicesAll.size() <= 0) {
			mListView.setSelection(1);
			initData();
		} else {
			adapter.notifyDataSetChanged();
		}
	}

}
