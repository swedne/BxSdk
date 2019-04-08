package com.zorasun.fangchanzhichuang.section.my;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.section.index.ChangFangDetailActivity;
import com.zorasun.fangchanzhichuang.section.index.SecondHouseDetailActivity;
import com.zorasun.fangchanzhichuang.section.index.ShangPuDetailActivity;
import com.zorasun.fangchanzhichuang.section.index.XieZiLouActivity;
import com.zorasun.fangchanzhichuang.section.index.XinFangxqActivity;
import com.zorasun.fangchanzhichuang.section.index.ZuFangxqActivity;
import com.zorasun.fangchanzhichuang.section.my.entiy.CollectionListEntity;
import com.zorasun.fangchanzhichuang.section.my.entiy.CollectionListEntity.HouseList;
import com.zorasun.fangchanzhichuang.section.my.entiy.CollectionListEntity.NewHouseSpecialList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("ResourceAsColor")
public class MyCollectActivity extends BaseActivity implements OnClickListener, OnLoadStateLinstener {

	private TextView tv_coolect_secondhouse;
	private TextView lin01;
	private TextView tv_coolect_rendhouse;
	private TextView lin02;
	private TextView lin03;
	private TextView tv_coolect_newhouse;
	private int tab = 0;
	private MyAdapter myAdapter;
	private int type = 1;
	private List<HouseList> houseList = new ArrayList<>();
	private CustomView customview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mycollection);
		initview();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initData();
	}

	private void initData() {
		MyApi.getInstance().requestCollectionList(this, type, new RequestCallBack() {

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
				ToastUtil.toastShow(MyCollectActivity.this, R.string.net_error);
				customview.showLoadStateView(CustomView.STATE_ERROR);
			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				customview.showLoadStateView(CustomView.STATE_EMPTY);
			}
		});
	}

	private void initview() {
		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("我的收藏");
		customview = (CustomView) findViewById(R.id.data_error);
		customview.setLoadStateLinstener(this);
		customview.showLoadStateView(CustomView.STATE_EMPTY);
		tv_coolect_secondhouse = (TextView) findViewById(R.id.tv_coolect_secondhouse);
		lin01 = (TextView) findViewById(R.id.lin01);
		tv_coolect_rendhouse = (TextView) findViewById(R.id.tv_coolect_rendhouse);
		lin02 = (TextView) findViewById(R.id.lin02);
		tv_coolect_newhouse = (TextView) findViewById(R.id.tv_coolect_newhouse);
		lin03 = (TextView) findViewById(R.id.lin03);
		LinearLayout ll_coolect_newhouse = (LinearLayout) findViewById(R.id.ll_coolect_newhouse);
		LinearLayout ll_coolect_secondhouse = (LinearLayout) findViewById(R.id.ll_coolect_secondhouse);
		LinearLayout ll_coolect_rendhouse = (LinearLayout) findViewById(R.id.ll_coolect_rendhouse);
		ll_coolect_secondhouse.setOnClickListener(this);
		ll_coolect_rendhouse.setOnClickListener(this);
		ll_coolect_newhouse.setOnClickListener(this);
		ListView xlv_mycollection = (ListView) findViewById(R.id.xlv_mycollection);
		myAdapter = new MyAdapter();
		xlv_mycollection.setAdapter(myAdapter);
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
			View inflate = getLayoutInflater().inflate(R.layout.item_collect, null);
			RelativeLayout rl_secondhandhouse02 = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse02);
			RelativeLayout rl_secondhandhouse = (RelativeLayout) inflate.findViewById(R.id.rl_secondhandhouse);
			final HouseList houseItem = houseList.get(position);
			TextView tvTitle = (TextView) inflate.findViewById(R.id.tv_collect_title);
			TextView tvPrice = (TextView) inflate.findViewById(R.id.tv_collect_price);
			TextView tvArea = (TextView) inflate.findViewById(R.id.tv_collect_areanum);
			TextView tvHouseTypeName = (TextView) inflate.findViewById(R.id.tv_collect_houseTypeName);
			TextView tvArealist = (TextView) inflate.findViewById(R.id.tv_collect_address);
			ImageView imgPicHouse = (ImageView) inflate.findViewById(R.id.imageView1);
			ImageView imgRenZheng = (ImageView) inflate.findViewById(R.id.img_renzheng_web);
			ImageView imgWeiRenZheng = (ImageView) inflate.findViewById(R.id.img_weirenzheng_web);
			if (tab == 0) {
				final String typeName = houseItem.getHouseTypeName().getTypeName();
				rl_secondhandhouse02.setVisibility(View.GONE);
				rl_secondhandhouse.setVisibility(View.VISIBLE);
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
				tvPrice.setText(houseItem.getSalePrice() + "万");
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
							intent = new Intent(MyCollectActivity.this, ChangFangDetailActivity.class);
							intent.putExtra("selectTypeId", 3);
							intent.putExtra("id", houseItem.getId());
						} else if (typeName.equals("写字楼")) {
							intent = new Intent(MyCollectActivity.this, XieZiLouActivity.class);
							intent.putExtra("selectTypeId", 5);
							intent.putExtra("id", houseItem.getId());
						} else if (typeName.equals("商铺")) {
							intent = new Intent(MyCollectActivity.this, ShangPuDetailActivity.class);
							intent.putExtra("selectTypeId", 1);
							intent.putExtra("id", houseItem.getId());
						} else {
							intent = new Intent(MyCollectActivity.this, SecondHouseDetailActivity.class);
							intent.putExtra("secondHouseId", houseItem.getId());
							intent.putExtra("areaListId", houseItem.getAreaListId());
						}
						startActivity(intent);
					}
				});

			} else if (tab == 1) {
				final String typeName = houseItem.getHouseTypeName().getTypeName();
				rl_secondhandhouse02.setVisibility(View.GONE);
				rl_secondhandhouse.setVisibility(View.VISIBLE);
				tvTitle.setText(houseItem.getTilte());
				tvPrice.setText(houseItem.getRental() + "元/月");
				tvArealist.setText(houseItem.getAreaListName());
				tvHouseTypeName.setText(typeName);
				if (typeName.equals("厂房")) {
					tvArea.setText(houseItem.getPlantAcreage() + "㎡");
				} else {
					tvArea.setText(houseItem.getBerryGG() + "㎡");
				}
				if (!TextUtils.isEmpty(houseItem.getUrl())) {
					AsyncImageLoader.setAsynImages(imgPicHouse, houseItem.getUrl());
				}
				inflate.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = null;
						if (typeName.equals("厂房")) {
							intent = new Intent(MyCollectActivity.this, ChangFangDetailActivity.class);
							intent.putExtra("selectTypeId", 2);
							intent.putExtra("id", houseItem.getId());
						} else if (typeName.equals("写字楼")) {
							intent = new Intent(MyCollectActivity.this, XieZiLouActivity.class);
							intent.putExtra("selectTypeId", 4);
							intent.putExtra("id", houseItem.getId());
						} else if (typeName.equals("商铺")) {
							intent = new Intent(MyCollectActivity.this, ShangPuDetailActivity.class);
							intent.putExtra("selectTypeId", 0);
							intent.putExtra("id", houseItem.getId());
						} else {
							intent = new Intent(MyCollectActivity.this, ZuFangxqActivity.class);
							intent.putExtra("rentHouseId", houseItem.getId());
						}

						startActivity(intent);
					}
				});

			} else {
				TextView tvName = (TextView) inflate.findViewById(R.id.tv_collect_newhousetitle);
				TextView tvNewHousePrice = (TextView) inflate.findViewById(R.id.tv_price);
				TextView tvNewHouseBusiness = (TextView) inflate.findViewById(R.id.tv_newhouse_businessname);
				ImageView imgNewPic = (ImageView) inflate.findViewById(R.id.img_newhousepic);
				LinearLayout llSpecial = (LinearLayout) inflate.findViewById(R.id.ll_housespecialname);
				tvNewHousePrice.setText(houseItem.getAverage() + "元/㎡");
				tvName.setText(houseItem.getNewhouseName());
				tvNewHouseBusiness.setText(houseItem.getBusinessName());
				rl_secondhandhouse02.setVisibility(View.VISIBLE);
				rl_secondhandhouse.setVisibility(View.GONE);
				if (!TextUtils.isEmpty(houseItem.getUrl())) {
					AsyncImageLoader.setAsynImages(imgNewPic, houseItem.getUrl());
				}
				List<NewHouseSpecialList> specialsList = houseItem.getNewHouseSpecialList();
				String color = "";
				if (specialsList.size() > 0) {

					for (int i = 0; i < specialsList.size(); i++) {
						View child = getLayoutInflater().inflate(R.layout.item_text, null);
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

					View child = getLayoutInflater().inflate(R.layout.item_text, null);
					TextView tvHouseTag = (TextView) child.findViewById(R.id.tv_housetag01);
					tvHouseTag.setBackgroundResource(R.drawable.shape_text_orange);
					tvHouseTag.setTextColor(Color.parseColor("#FD641D"));
					tvHouseTag.setText("暂无");
					llSpecial.addView(child);

				}

				inflate.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						Intent intent = new Intent(MyCollectActivity.this, XinFangxqActivity.class);
						intent.putExtra("NewhouseId", houseItem.getNewHouseId());
						startActivity(intent);
					}
				});
			}
			return inflate;
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ll_coolect_secondhouse:
			tab = 0;
			tv_coolect_secondhouse.setTextColor(this.getResources().getColorStateList(R.color.title_bg));
			lin01.setBackgroundColor(getResources().getColor(R.color.title_bg));
			tv_coolect_rendhouse.setTextColor(this.getResources().getColorStateList(R.color.line));
			lin02.setBackgroundColor(getResources().getColor(R.color.line));
			tv_coolect_newhouse.setTextColor(this.getResources().getColorStateList(R.color.line));
			lin03.setBackgroundColor(getResources().getColor(R.color.line));
			myAdapter.notifyDataSetChanged();
			houseList.clear();
			type = 1;
			initData();
			break;
		case R.id.ll_coolect_rendhouse:
			tab = 1;
			tv_coolect_secondhouse.setTextColor(this.getResources().getColorStateList(R.color.line));
			lin01.setBackgroundColor(getResources().getColor(R.color.line));
			tv_coolect_rendhouse.setTextColor(this.getResources().getColorStateList(R.color.title_bg));
			// lin02.setTextColor(this.getResources().getColorStateList(R.color.title_bg));
			lin02.setBackgroundColor(getResources().getColor(R.color.title_bg));
			tv_coolect_newhouse.setTextColor(this.getResources().getColorStateList(R.color.line));
			lin03.setBackgroundColor(getResources().getColor(R.color.line));
			myAdapter.notifyDataSetChanged();
			type = 2;
			houseList.clear();
			initData();
			break;

		case R.id.ll_coolect_newhouse:
			tab = 2;
			tv_coolect_secondhouse.setTextColor(this.getResources().getColorStateList(R.color.line));
			lin01.setBackgroundColor(getResources().getColor(R.color.line));
			tv_coolect_rendhouse.setTextColor(this.getResources().getColorStateList(R.color.line));
			lin02.setBackgroundColor(getResources().getColor(R.color.line));
			tv_coolect_newhouse.setTextColor(this.getResources().getColorStateList(R.color.title_bg));
			lin03.setBackgroundColor(getResources().getColor(R.color.title_bg));
			myAdapter.notifyDataSetChanged();
			type = 4;
			houseList.clear();
			initData();
			break;
		default:
			break;
		}
	}

	@Override
	public void onLoadData() {
		initData();
	}
}
