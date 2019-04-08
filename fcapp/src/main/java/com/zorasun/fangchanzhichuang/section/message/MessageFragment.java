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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 爱自己-frm
 * 
 * @author 林文熹
 * @e-mail 635991604@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月14日 下午2:19:56
 * 
 */
public class MessageFragment extends BaseFragment
		implements OnClickListener, OnItemClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

	private LayoutInflater minflater;
	private TextView tvMessageAll;
	private TextView tvMessageMessage;
	private TextView tvMessageNotic;
	private int tabSelect;
	private View line1;
	private View line2;
	private View line3;
	private int type = 2;
	private List<AdvicesAll> advicesAll = new ArrayList<>();
	private Myadapter adapter;
	private PullToRefreshListView ptrListView;
	private ListView mListView;
	private int pageNum = 1;
	private boolean isRefresh;
	private int pageCount;
	private CustomView customview;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message, null);

		this.minflater = inflater;
		initTitleBar(view);
		initView(view);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
		advicesAll.clear();
		initData();
	}

	private void initData() {
		MessageApi.getInstance().requestMessageList(getActivity(), type, pageNum, new RequestCallBack() {

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

	private void initView(View view) {

		adapter = new Myadapter();

		ptrListView = (PullToRefreshListView) view.findViewById(R.id.ptr_listview);
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);

		customview = (CustomView) view.findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);

		mListView = ptrListView.getRefreshableView();

		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);

		tvMessageAll = (TextView) view.findViewById(R.id.message_all);
		tvMessageAll.setOnClickListener(this);
		tvMessageMessage = (TextView) view.findViewById(R.id.message_message);
		tvMessageMessage.setOnClickListener(this);
		tvMessageNotic = (TextView) view.findViewById(R.id.message_notic);
		tvMessageNotic.setOnClickListener(this);

		line1 = view.findViewById(R.id.line1);
		line2 = view.findViewById(R.id.line2);
		line3 = view.findViewById(R.id.line3);

	}

	public void setSelectText() {
		line1.setVisibility(View.INVISIBLE);
		line2.setVisibility(View.INVISIBLE);
		line3.setVisibility(View.INVISIBLE);
		tvMessageAll.setTextColor(Color.parseColor("#919191"));
		tvMessageMessage.setTextColor(Color.parseColor("#919191"));
		tvMessageNotic.setTextColor(Color.parseColor("#919191"));
		if (tabSelect == 1) {
			tvMessageAll.setTextColor(Color.parseColor("#0089fe"));
			line1.setVisibility(View.VISIBLE);
		} else if (tabSelect == 2) {
			tvMessageMessage.setTextColor(Color.parseColor("#0089fe"));
			line2.setVisibility(View.VISIBLE);
		} else {
			tvMessageNotic.setTextColor(Color.parseColor("#0089fe"));
			line3.setVisibility(View.VISIBLE);
		}
	}

	// 初始化标题
	private void initTitleBar(View view) {
		TextView title_name = (TextView) view.findViewById(R.id.title_name);
		title_name.setText("我的消息");
		ImageView back = (ImageView) view.findViewById(R.id.title_left);
		back.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.message_all:
			tabSelect = 1;
			type = 2;
			pageNum = 1;
			advicesAll.clear();
			adapter.notifyDataSetChanged();
			mListView.setSelection(1);
			initData();
			setSelectText();
			break;
		case R.id.message_message:
			tabSelect = 2;
			type = 0;
			pageNum = 1;
			advicesAll.clear();
			adapter.notifyDataSetChanged();
			mListView.setSelection(1);
			initData();
			setSelectText();
			break;
		case R.id.message_notic:
			type = 1;
			tabSelect = 3;
			pageNum = 1;
			advicesAll.clear();
			adapter.notifyDataSetChanged();
			mListView.setSelection(1);
			initData();
			setSelectText();
			break;
		default:
			break;
		}
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
			View inflate = minflater.inflate(R.layout.item_messagelist, null);
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

			// if (position == 1) {
			// tvTitle.setText("最新公告");
			// } else if (position == 2) {
			// imgYuanDian.setVisibility(View.GONE);
			// } else {
			// imgPic.setImageResource(R.drawable.gongggao);
			// imgYuanDian.setVisibility(View.GONE);
			// }
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
		if (tabSelect == 1) {
			type = 2;
		} else if (tabSelect == 2) {
			type = 0;
		} else if (tabSelect == 3) {
			type = 1;
		}
		initData();

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		pageNum++;
		if (tabSelect == 1) {
			type = 2;
		} else if (tabSelect == 2) {
			type = 0;
		} else if (tabSelect == 3) {
			type = 1;
		}
		isRefresh = false;
		initData();
	}

	@Override
	public void onLoadData() {
		initData();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}
}
