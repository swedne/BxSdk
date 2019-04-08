package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.commonadapter.CommonAdapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.ViewHolder;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollGridView;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollGridView2;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouInfoEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouInfoEntity.BrokerList;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouInfoEntity.Content;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouInfoEntity.RentHouseList;
import com.zorasun.fangchanzhichuang.section.index.entity.JiGouInfoEntity.SecondHouseList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SuoShuJiGouActivity extends BaseActivity implements OnClickListener {

	private RecyclerView hlvJingJiren;
	private NoScrollGridView2 gridSecondHouse;
	private SecondHouseAdapter secondHouseApdater;
	private NoScrollGridView gridRentHouse;
	private TextView tvSecondHouseNum;
	private TextView tvRentHouseNum;
	private List<SecondHouseList> secondHouseList = new ArrayList<>();
	private List<RentHouseList> rentHouseList = new ArrayList<>();
	private TextView tvReaName;
	private TextView tvConpanyProfile;
	private TextView tvQualificationNo;
	private RentHouseAdapter rentHouseApdater;
	private Content content;
	private int agencyId;
	private List<BrokerList> brokerList = new ArrayList<>();
	private ImageView imgTitle;
	private ImageView imgAvatar;
	private GalleryAdapter jingjirenAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suo_shu_ji_gou);
		initView();
		initData();
	}

	private void initData() {
		agencyId = getIntent().getIntExtra("agencyId", -1);
		IndexApi.getInstance().requestJiGouInfo(this, agencyId, new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				JiGouInfoEntity jigouInfoEntity = (JiGouInfoEntity) object;
				content = jigouInfoEntity.getContent();
				brokerList.addAll(content.getBrokerList());
				if (jigouInfoEntity.getContent().getSecondHouseList().size() < 4) {
					secondHouseList.addAll(jigouInfoEntity.getContent().getSecondHouseList());
				} else {
					for (int i = 0; i < 4; i++) {
						secondHouseList.add(jigouInfoEntity.getContent().getSecondHouseList().get(i));
					}
				}

				if (jigouInfoEntity.getContent().getRentHouseList().size() < 4) {
					rentHouseList.addAll(jigouInfoEntity.getContent().getRentHouseList());
				} else {
					for (int i = 0; i < 4; i++) {
						rentHouseList.add(jigouInfoEntity.getContent().getRentHouseList().get(i));
					}
				}

				tvReaName.setText(content.getRealName());
				tvQualificationNo.setText(content.getQualificationNo());
				tvConpanyProfile.setText(content.getCompanyProfile());
				tvSecondHouseNum.setText("二手房源(" + content.getSecondHouseNum() + "套)");
				tvRentHouseNum.setText("出租房源(" + content.getRentingNum() + "套)");
				if (!TextUtils.isEmpty(content.getLogoImage())) {
					AsyncImageLoader.setAsynImages(imgTitle, content.getLogoImage());
				}
				if (!TextUtils.isEmpty(content.getLogoImage())) {
					AsyncImageLoader.setAsynAvatarImagesInfo(imgAvatar, content.getLogoImage());
				}
				secondHouseApdater.notifyDataSetChanged();
				rentHouseApdater.notifyDataSetChanged();
				jingjirenAdapter.notifyDataSetChanged();
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(SuoShuJiGouActivity.this, R.string.net_error);

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(SuoShuJiGouActivity.this, msg);
			}
		});

	}

	private void initView() {
		TextView tvTitleName = (TextView) findViewById(R.id.title_name);
		tvTitleName.setText("机构详情");
		findViewById(R.id.rl_secondmore).setOnClickListener(this);
		findViewById(R.id.rl_rentmore).setOnClickListener(this);

		hlvJingJiren = (RecyclerView) findViewById(R.id.horizontallistview1);
		// 设置布局管理器
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		hlvJingJiren.setLayoutManager(linearLayoutManager);
		jingjirenAdapter = new GalleryAdapter(this, brokerList);
		hlvJingJiren.setAdapter(jingjirenAdapter);
		tvReaName = (TextView) findViewById(R.id.tv_realname);
		tvConpanyProfile = (TextView) findViewById(R.id.tv_companyInfo);
		tvQualificationNo = (TextView) findViewById(R.id.tv_qualificationNo);
		tvSecondHouseNum = (TextView) findViewById(R.id.tv_secondhousenum);
		tvRentHouseNum = (TextView) findViewById(R.id.tv_renthousenum);

		gridSecondHouse = (NoScrollGridView2) findViewById(R.id.gv_secondhouse);
		secondHouseApdater = new SecondHouseAdapter(this, secondHouseList, R.layout.grid_view_fangyuaninfo);
		gridSecondHouse.setAdapter(secondHouseApdater);

		gridRentHouse = (NoScrollGridView) findViewById(R.id.gv_renthouse);
		rentHouseApdater = new RentHouseAdapter(this, rentHouseList, R.layout.grid_view_fangyuaninfo);
		gridRentHouse.setAdapter(rentHouseApdater);
		imgTitle = (ImageView) findViewById(R.id.img_title);
		// RelativeLayout rlLayout = (RelativeLayout)
		// findViewById(R.id.relativeLayout1);
		// RelativeLayout.LayoutParams vpLayout = (RelativeLayout.LayoutParams)
		// rlLayout.getLayoutParams();
		// float scale = 750 / 500;
		// vpLayout.height = (int)
		// (ScreenUtils.getScreenWidth(SuoShuJiGouActivity.this) / scale);//
		// 屏幕大小

		imgAvatar = (ImageView) findViewById(R.id.imt_avatar);

		gridSecondHouse.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Integer secondHouseId = secondHouseList.get(position).getId();
				int areaListId = secondHouseList.get(position).getAreaListId();
				Intent intent = new Intent(SuoShuJiGouActivity.this, SecondHouseDetailActivity.class);
				intent.putExtra("secondHouseId", secondHouseId);
				intent.putExtra("areaListId", areaListId);
				startActivity(intent);
			}
		});
		gridRentHouse.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int rentHouseId = rentHouseList.get(position).id;
				Intent intent = new Intent(SuoShuJiGouActivity.this, ZuFangxqActivity.class);
				intent.putExtra("rentHouseId", rentHouseId);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 更多二手房源
		case R.id.rl_secondmore:
			Intent intent = new Intent(this, JiGouHouseResActivity.class);
			intent.putExtra("houseres", SystemConstant.JIGOU_SECONDHOUSE);
			intent.putExtra("agencyId", agencyId);
			startActivity(intent);
			break;
		// 更多出租房源
		case R.id.rl_rentmore:
			Intent intent1 = new Intent(this, JiGouHouseResActivity.class);
			intent1.putExtra("houseres", SystemConstant.JIGOU_RENTHOUSR);
			intent1.putExtra("agencyId", agencyId);
			startActivity(intent1);
			break;
		default:
			break;
		}
	}

	class SecondHouseAdapter extends CommonAdapter<SecondHouseList> {

		public SecondHouseAdapter(Context context, List<SecondHouseList> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, SecondHouseList item, int position) {
			ImageView imgSurface = helper.getView(R.id.img_surface);
			if (!TextUtils.isEmpty(item.getPicUrl())) {
				AsyncImageLoader.setAsynImages(imgSurface, item.getPicUrl());
			} else {
				imgSurface.setImageResource(R.drawable.wutu);
			}
			helper.setText(R.id.tv_title, item.getTitle());
			View view = helper.getView(R.id.img_renzheng);
			view.setVisibility(View.GONE);
			if (item.getIsAuth() == 1) {
				view.setVisibility(View.VISIBLE);
			} else {
				view.setVisibility(View.GONE);
			}

		}

	}

	class RentHouseAdapter extends CommonAdapter<RentHouseList> {

		public RentHouseAdapter(Context context, List<RentHouseList> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, RentHouseList item, int position) {
			ImageView imgSurface = helper.getView(R.id.img_surface);
			if (!TextUtils.isEmpty(item.getPicUrl())) {
				AsyncImageLoader.setAsynImages(imgSurface, item.getPicUrl());
			} else {
				imgSurface.setImageResource(R.drawable.wutu);
			}
			helper.setText(R.id.tv_title, item.getTitle());
			View view = helper.getView(R.id.img_renzheng);
			view.setVisibility(View.GONE);
			if (item.getIsAuth() == 1) {
				view.setVisibility(View.VISIBLE);
			}
		}

	}

	public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
		private LayoutInflater mInflater;
		private List<BrokerList> brokerList;

		public GalleryAdapter(Context context, List<BrokerList> datats) {
			mInflater = LayoutInflater.from(context);
			brokerList = datats;
		}

		public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

			private View view;
			private TextView tvJingjiren;
			private ImageView imgJingjiren;
			private ImageView imgRenZheng;

			public ViewHolder(View view) {
				super(view);
				this.view = view;
				view.setOnClickListener(this);
			}

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SuoShuJiGouActivity.this, JingjirenXqActivity.class);
				intent.putExtra("brokerId", brokerList.get(getPosition()).getBrokerId());
				startActivity(intent);
			}

		}

		@Override
		public int getItemCount() {
			return brokerList.size();
		}

		/**
		 * 创建ViewHolder
		 */
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
			View view = mInflater.inflate(R.layout.item_broker, viewGroup, false);
			ViewHolder viewHolder = new ViewHolder(view);
			viewHolder.imgJingjiren = (ImageView) view.findViewById(R.id.img_broker);
			viewHolder.imgRenZheng = (ImageView) view.findViewById(R.id.img_renzheng);
			viewHolder.tvJingjiren = (TextView) view.findViewById(R.id.tv_broker);
			return viewHolder;
		}

		/**
		 * 设置值
		 */
		@Override
		public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
			viewHolder.imgRenZheng.setVisibility(View.GONE);
			BrokerList broker = brokerList.get(i);
			if (!TextUtils.isEmpty(broker.getHeadUrl())) {
				AsyncImageLoader.setAsynImages(viewHolder.imgJingjiren, broker.getHeadUrl());
			} else {
				viewHolder.imgRenZheng.setImageResource(R.drawable.wutu);
			}
			viewHolder.tvJingjiren.setText(broker.getBrokerName());
		}

	}

}
