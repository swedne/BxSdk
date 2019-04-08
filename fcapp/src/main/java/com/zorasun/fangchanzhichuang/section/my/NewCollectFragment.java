package com.zorasun.fangchanzhichuang.section.my;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.section.index.XinFangxqActivity;
import com.zorasun.fangchanzhichuang.section.my.entiy.CollectionListEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.CollectionListEntity.HouseList;
import com.zorasun.fangchanzhichuang.section.my.entiy.CollectionListEntity.NewHouseSpecialList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class NewCollectFragment extends BaseFragment implements OnLoadStateLinstener {

	private CustomView customview;
	private MyAdapter myAdapter;
	private LayoutInflater mInflate;
	protected List<HouseList> houseList = new ArrayList<>();

	public NewCollectFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_sec_collect, container, false);
		this.mInflate = inflater;
		initView(view);
		initData();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		houseList.clear();
		if (MyCollectActivity2.position == 2) {
			initData();
		}
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);
		Log.e("====", "NewCollectFragment");
	}

	private void initView(View view) {
		customview = (CustomView) view.findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		ListView xlv_mycollection = (ListView) view.findViewById(R.id.xlv_mycollection);
		myAdapter = new MyAdapter();
		xlv_mycollection.setAdapter(myAdapter);
	}

	private void initData() {
		MyApi.getInstance().requestCollectionList(getActivity(), 4, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				CollectionListEntity collectionListEntity = (CollectionListEntity) object;
				houseList.clear();
				if (collectionListEntity.getContent() != null
						&& collectionListEntity.getContent().getHouseList() != null) {
					houseList.addAll(collectionListEntity.getContent().getHouseList());
				}
				if (houseList.isEmpty()) {
					customview.showLoadStateView(CustomView.STATE_EMPTY);
				} else {
					customview.showLoadStateView(CustomView.STATE_NONE);
				}
				myAdapter.notifyDataSetChanged();
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(getActivity(), R.string.net_error);
				customview.showLoadStateView(CustomView.STATE_ERROR);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				customview.showLoadStateView(CustomView.STATE_EMPTY);
			}
		});
	}

	class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return houseList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = mInflate.inflate(R.layout.item_collect, null);
			final HouseList houseItem = houseList.get(position);
			RelativeLayout rl_secondhandhouse02 = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse02);
			RelativeLayout rl_secondhandhouse = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse);
			rl_secondhandhouse02.setVisibility(View.VISIBLE);
			rl_secondhandhouse.setVisibility(View.GONE);
			TextView tvName = (TextView) inflate.findViewById(R.id.tv_collect_newhousetitle);
			TextView tvNewHousePrice = (TextView) inflate.findViewById(R.id.tv_price);
			TextView tvNewHouseBusiness = (TextView) inflate.findViewById(R.id.tv_newhouse_businessname);
			ImageView imgNewPic = (ImageView) inflate.findViewById(R.id.img_newhousepic);
			LinearLayout llSpecial = (LinearLayout) inflate.findViewById(R.id.ll_housespecialname);
			tvNewHousePrice.setText(houseItem.getAverage() + "");
			tvName.setText(houseItem.getNewhouseName());
			tvNewHouseBusiness.setText(houseItem.getBusinessName());
			if (!TextUtils.isEmpty(houseItem.getUrl())) {
				AsyncImageLoader.setAsynImages(imgNewPic, houseItem.getUrl());
			}
			List<NewHouseSpecialList> specialsList = houseItem.getNewHouseSpecialList();
			String color = "";
			if (specialsList.size() > 0) {

				for (int i = 0; i < specialsList.size(); i++) {
					View child = mInflate.inflate(R.layout.item_text, null);
					TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
					if (i == 0) {
						tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
						color = "#FD641D";
					} else if (i == 1) {
						tvHouseTag.setBackgroundResource(R.drawable.shape_text_purple);
						color = "#4970E1";
					} else {
						tvHouseTag.setBackgroundResource(R.drawable.shape_text_green);
						color = "#20B648";
					}
					tvHouseTag.setTextColor(Color.parseColor(color));
					tvHouseTag.setText(specialsList.get(i).getSpecialName());
					llSpecial.addView(child);
				}
			} else {
				View child = mInflate.inflate(R.layout.item_text, null);
				TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
				tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
				tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
				tvHouseTag.setText("暂无");
				llSpecial.addView(child);

			}
			inflate.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Intent intent = new Intent(getActivity(), XinFangxqActivity.class);
					intent.putExtra("NewhouseId", houseItem.getNewHouseId());
					startActivity(intent);
				}
			});
			return inflate;
		}
	}

	@Override
	public void initView() {
		if (houseList.size() <= 0) {
			initData();
		} else {
			myAdapter.notifyDataSetChanged();
		}

	}

	@Override
	public void onLoadData() {
		initData();
	}

}
