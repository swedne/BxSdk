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
import com.zorasun.fangchanzhichuang.section.index.ChangFangDetailActivity;
import com.zorasun.fangchanzhichuang.section.index.SecondHouseDetailActivity;
import com.zorasun.fangchanzhichuang.section.index.ShangPuDetailActivity;
import com.zorasun.fangchanzhichuang.section.index.XieZiLouActivity;
import com.zorasun.fangchanzhichuang.section.my.entiy.CollectionListEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.CollectionListEntity.HouseList;

import android.content.Intent;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class SecCollectFragment extends BaseFragment implements OnLoadStateLinstener {

	private CustomView customview;
	private MyAdapter myAdapter;
	private LayoutInflater mInflate;
	protected List<HouseList> houseList = new ArrayList<>();

	public SecCollectFragment() {
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
		if (MyCollectActivity2.position == 0) {
			initData();
		}
		Log.e("====", "SecCollectFragment");
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
		MyApi.getInstance().requestCollectionList(getActivity(), 1, new RequestCallBack() {

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
			RelativeLayout rl_secondhandhouse02 = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse02);
			RelativeLayout rl_secondhandhouse = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse);
			final HouseList houseItem = houseList.get(position);
			TextView tvTitle = (TextView) inflate.findViewById(R.id.tv_collect_title);
			TextView tvPrice = (TextView) inflate.findViewById(R.id.tv_collect_price);
			TextView tvArea = (TextView) inflate.findViewById(R.id.tv_collect_areanum);
			TextView tvHouseTypeName = (TextView) inflate.findViewById(R.id.tv_collect_houseTypeName);
			TextView tvArealist = (TextView) inflate.findViewById(R.id.tv_collect_address);
			View isList = inflate.findViewById(R.id.tv_islist);
			ImageView imgPicHouse = (ImageView) inflate.findViewById(R.id.imageView1);
			ImageView imgRenZheng = (ImageView) inflate.findViewById(R.id.img_renzheng_web);
			ImageView imgWeiRenZheng = (ImageView) inflate.findViewById(R.id.img_weirenzheng_web);
			final String typeName = houseItem.getHouseTypeName().getTypeName();
			rl_secondhandhouse02.setVisibility(View.GONE);
			rl_secondhandhouse.setVisibility(View.VISIBLE);
			if (houseItem.isList == 1) {
				isList.setVisibility(View.GONE);
			} else {// 房源已下架
				isList.setVisibility(View.VISIBLE);
			}
			if (!TextUtils.isEmpty(houseItem.getUrl())) {
				AsyncImageLoader.setAsynImages(imgPicHouse, houseItem.getUrl());
			}
			if (houseItem.getIsAuth() == 0) {
				imgRenZheng.setVisibility(View.GONE);
				imgWeiRenZheng.setVisibility(View.VISIBLE);
			} else {
				imgWeiRenZheng.setVisibility(View.GONE);
				imgRenZheng.setVisibility(View.VISIBLE);
			}
			tvTitle.setText(houseItem.getTilte());
			tvPrice.setText(houseItem.getSalePrice() + "");
			if (typeName.equals("厂房")) {
				tvArea.setText(houseItem.getPlantAcreage() + "㎡");
			} else {
				tvArea.setText(houseItem.getBerryGG() + "㎡");
			}
			tvHouseTypeName.setText(typeName);
			tvArealist.setText(houseItem.getAreaListName());
			inflate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = null;
					if (typeName.equals("厂房")) {
						intent = new Intent(getActivity(), ChangFangDetailActivity.class);
						intent.putExtra("selectTypeId", 3);
						intent.putExtra("id", houseItem.getId());
					} else if (typeName.equals("写字楼")) {
						intent = new Intent(getActivity(), XieZiLouActivity.class);
						intent.putExtra("selectTypeId", 5);
						intent.putExtra("id", houseItem.getId());
					} else if (typeName.equals("商铺")) {
						intent = new Intent(getActivity(), ShangPuDetailActivity.class);
						intent.putExtra("selectTypeId", 1);
						intent.putExtra("id", houseItem.getId());
					} else {
						intent = new Intent(getActivity(), SecondHouseDetailActivity.class);
						intent.putExtra("secondHouseId", houseItem.getId());
						intent.putExtra("areaListId", houseItem.getAreaListId());
					}
					startActivity(intent);
				}
			});
			return inflate;
		}

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
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
