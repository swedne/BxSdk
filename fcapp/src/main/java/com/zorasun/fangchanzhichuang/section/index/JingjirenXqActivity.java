package com.zorasun.fangchanzhichuang.section.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.Base2Activity;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.commonadapter.CommonAdapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.ViewHolder;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollGridView;
import com.zorasun.fangchanzhichuang.general.widget.NoScrollGridView2;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.account.LoginActivity;
import com.zorasun.fangchanzhichuang.section.dialog.DialogShare;
import com.zorasun.fangchanzhichuang.section.dialog.DialogShare.DialogShareCallBack;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.BrokerInfo_;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.CertificateAuthenticList;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.Content;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.RentHouseList;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.SecondHouseList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class JingjirenXqActivity extends Base2Activity implements OnClickListener {

	private TextView tvBrokerName;
	private TextView tvPracticeTime;
	private TextView tvBussinessName;
	private TextView tvRealName;
	private TextView tvPersonalDetail;
	private View isExpertView;
	private TextView tvChiZheng;
	private HashMap<Integer, String> certificationMap = new HashMap<>();
	private TextView tvSecondHouseNum;
	private NoScrollGridView2 gridSecondHouse;
	private List<SecondHouseList> secondHouseList = new ArrayList<>();
	private List<RentHouseList> rentHouseList = new ArrayList<>();
	private SecondHouseAdapter secondHouseApdater;
	private NoScrollGridView gridRentHouse;
	private RentHouseAdapter rentHouseApdater;
	private TextView tvRentHouseNum;
	private TextView tvAttion;
	private BrokerInfo_ brokerInfo;
	protected int isActtention;
	private ImageView imgHead;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jingjirenxq);

		certificationMap.put(0, "全国经纪人证");
		certificationMap.put(1, "福建省房地产经纪人协助从业资格证");
		certificationMap.put(2, "厦门房地产经纪人执业资格证）");
		initView();
	}

	@Override
	protected void onStart() {
		super.onStart();
		initData();
	}

	private Content content;

	private void initData() {
		int brokerId = getIntent().getIntExtra("brokerId", -1);
		IndexApi.getInstance().requestBrokerInfo(this, brokerId, AccountConfig.getAccountId(), new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				BrokerInfoEntity brokerInfoEntity = (BrokerInfoEntity) object;
				content = brokerInfoEntity.getContent();
				brokerInfo = content.getBrokerInfo();

				if (!AccountConfig.isLogin()) {
					tvAttion.setText("+ 关注");
				} else {
					if (brokerInfo.getIsActtention() == 1) {
						tvAttion.setText("+ 关注");
					} else {
						tvAttion.setText("取消关注");

					}
				}
				isActtention = brokerInfo.getIsActtention();

				tvBrokerName.setText(brokerInfo.getBrokerName());
				tvPracticeTime.setText(brokerInfo.getPracticeTime() + "年");
				tvBussinessName.setText(content.getBrokerInfo().getBusinessService().getBusinessName());
				tvRealName.setText(brokerInfo.getRealName());
				tvPersonalDetail.setText(brokerInfo.getPersonalDetail());
				if (brokerInfo.getIsExpert() == 0) {
					isExpertView.setVisibility(View.GONE);
				}
				if (!TextUtils.isEmpty(brokerInfo.getHeadUrl())) {
					AsyncImageLoader.setAsynAvatarImagesInfo(imgHead, brokerInfo.getHeadUrl());
				}
				// if (!TextUtils.isEmpty(brokerInfo.getQrcodeUrl())) {
				// AsyncImageLoader.setAsynImages(imgTitle,
				// brokerInfo.getQrcodeUrl());
				// }
				List<CertificateAuthenticList> certificateAuthenticList = content.getCertificateAuthenticList();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < certificateAuthenticList.size(); i++) {
					if (i == certificateAuthenticList.size() - 1) {
						sb.append(certificationMap.get(i));
					} else {
						sb.append(certificationMap.get(i) + "、");
					}
				}
				tvChiZheng.setText(sb.toString());

				// 二手房源
				List<SecondHouseList> secondHouseList2 = content.getSecondHouseList();
				secondHouseList.clear();
				if (secondHouseList2.size() < 4) {
					secondHouseList.addAll(secondHouseList2);
				} else {
					for (int i = 0; i < 4; i++) {
						secondHouseList.add(secondHouseList2.get(i));
					}
				}
				tvSecondHouseNum.setText("二手房源" + "(" + brokerInfo.getOldHouseRes() + "套)");
				secondHouseApdater.notifyDataSetChanged();
				// 出租房源
				rentHouseList.clear();
				List<RentHouseList> rentHouseList2 = content.getRentHouseList();
				if (rentHouseList2.size() < 4) {
					rentHouseList.addAll(rentHouseList2);
				} else {
					for (int i = 0; i < 4; i++) {
						rentHouseList.add(rentHouseList2.get(i));
					}
				}

				tvRentHouseNum.setText("出租房源" + "(" + brokerInfo.getRentRes() + "套)");
				rentHouseApdater.notifyDataSetChanged();

			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(JingjirenXqActivity.this, R.string.net_error);

			}

			@Override
			public void onFailure(int code, String msg, Object object) {
				ToastUtil.toastShow(JingjirenXqActivity.this, msg);
			}
		});

	}

	protected void setAttation() {
		IndexApi.getInstance().requestBrokerAttion(this, brokerInfo.getId(), new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				tvAttion.setText("取消关注");
				ToastUtil.toastShow(JingjirenXqActivity.this, msg);
				isActtention = 0;
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(JingjirenXqActivity.this, R.string.net_error);

			}

			@Override
			public void onFailure(int code, String msg, Object object) {

				ToastUtil.toastShow(JingjirenXqActivity.this, msg);
			}
		});
	}

	protected void setAttationCancle() {
		IndexApi.getInstance().requestBrokerAttionCancle(this, brokerInfo.getId(), new RequestCallBack() {

			@Override
			public void onSuccess(int code, String msg, Object object) {
				tvAttion.setText("+ 关注");
				ToastUtil.toastShow(JingjirenXqActivity.this, msg);
				isActtention = 1;
			}

			@Override
			public void onNetworkError() {
				ToastUtil.toastShow(JingjirenXqActivity.this, R.string.net_error);

			}

			@Override
			public void onFailure(int code, String msg, Object object) {

				ToastUtil.toastShow(JingjirenXqActivity.this, msg);
			}
		});
	}

	private void initView() {
		TextView tv_more = (TextView) findViewById(R.id.tv_more);
		findViewById(R.id.rl_suoshujiguo).setOnClickListener(this);
		findViewById(R.id.rl_fuwu).setOnClickListener(this);
		findViewById(R.id.rl_call_brokerxq).setOnClickListener(this);
		findViewById(R.id.ll_sendMsg).setOnClickListener(this);
		findViewById(R.id.img_share).setVisibility(View.GONE);;
		findViewById(R.id.rl_secondmore).setOnClickListener(this);
		findViewById(R.id.rl_rentmore).setOnClickListener(this);

		tvBrokerName = (TextView) findViewById(R.id.tv_brokerName);
		tvPracticeTime = (TextView) findViewById(R.id.tv_practiceTime);
		tvBussinessName = (TextView) findViewById(R.id.tv_businessName);
		tvRealName = (TextView) findViewById(R.id.tv_realName);
		tvPersonalDetail = (TextView) findViewById(R.id.tv_personalDetail);
		isExpertView = findViewById(R.id.tv_isExpert);
		tvChiZheng = (TextView) findViewById(R.id.tv_chizheng);
		tvSecondHouseNum = (TextView) findViewById(R.id.ershoufangyuan);
		gridSecondHouse = (NoScrollGridView2) findViewById(R.id.gv_secondGrid);
		secondHouseApdater = new SecondHouseAdapter(this, secondHouseList, R.layout.grid_view_fangyuaninfo);
		gridSecondHouse.setAdapter(secondHouseApdater);

		tvRentHouseNum = (TextView) findViewById(R.id.tv_renthouseNum);
		gridRentHouse = (NoScrollGridView) findViewById(R.id.gv_rentGrid);
		rentHouseApdater = new RentHouseAdapter(this, rentHouseList, R.layout.grid_view_fangyuaninfo);
		gridRentHouse.setAdapter(rentHouseApdater);

		tvAttion = (TextView) findViewById(R.id.tv_attion);
		tvAttion.setOnClickListener(this);
		imgHead = (ImageView) findViewById(R.id.img_head);

		gridSecondHouse.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int secondHouseId = secondHouseList.get(position).getId();
				int areaListId = secondHouseList.get(position).getAlId();
				Intent intent = new Intent(JingjirenXqActivity.this, SecondHouseDetailActivity.class);
				intent.putExtra("secondHouseId", secondHouseId);
				intent.putExtra("areaListId", areaListId);
				startActivity(intent);
			}
		});

		gridRentHouse.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int rentHouseId = rentHouseList.get(position).getId();
				Intent intent = new Intent(JingjirenXqActivity.this, ZuFangxqActivity.class);
				intent.putExtra("rentHouseId", rentHouseId);
				startActivity(intent);
			}
		});
		// setListViewHeightBasedOnChildren(gridSecondHouse);
	}

	class SecondHouseAdapter extends CommonAdapter<SecondHouseList> {

		public SecondHouseAdapter(Context context, List<SecondHouseList> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, SecondHouseList item, int position) {
			helper.setText(R.id.tv_title, item.getTilte());
			ImageView imgSurface = helper.getView(R.id.img_surface);
			View renzhengView = helper.getView(R.id.img_renzheng);
			if (!TextUtils.isEmpty(item.getSurFaceUrl())) {
				AsyncImageLoader.setAsynImages(imgSurface, item.getSurFaceUrl());
			} else {
				imgSurface.setImageResource(R.drawable.wutu);
			}
			if (item.getIsAuth() == 0) {
				renzhengView.setVisibility(View.GONE);
			} else {
				renzhengView.setVisibility(View.VISIBLE);
			}

		}

	}

	class RentHouseAdapter extends CommonAdapter<RentHouseList> {

		public RentHouseAdapter(Context context, List<RentHouseList> mDatas, int layoutId) {
			super(context, mDatas, layoutId);
		}

		@Override
		public void convert(ViewHolder helper, RentHouseList item, int position) {
			helper.setText(R.id.tv_title, item.getTilte());
			ImageView imgSurface = helper.getView(R.id.img_surface);
			if (!TextUtils.isEmpty(item.getSurFaceUrl())) {
				AsyncImageLoader.setAsynImages(imgSurface, item.getSurFaceUrl());
			} else {
				imgSurface.setImageResource(R.drawable.wutu);
			}
			View renzhengView = helper.getView(R.id.img_renzheng);
			if (item.getIsAuth() == 0) {
				renzhengView.setVisibility(View.GONE);
			} else {
				renzhengView.setVisibility(View.VISIBLE);
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 更多二手房源
		case R.id.rl_secondmore:
			Intent intent = new Intent(this, SecondHouseActivity.class);
			intent.putExtra("houseres", SystemConstant.JINGJIREN_SECONDHOUSE);
			intent.putExtra("brokerId", brokerInfo.getId());
			startActivity(intent);
			break;
		// 更多出租房源
		case R.id.rl_rentmore:
			Intent intent1 = new Intent(this, SecondHouseActivity.class);
			intent1.putExtra("houseres", SystemConstant.JINGJIREN_RENTHOUSE);
			intent1.putExtra("brokerId", brokerInfo.getId());
			startActivity(intent1);
			break;
		// 所属机构
		case R.id.rl_suoshujiguo:
			Intent intent2 = new Intent(this, SuoShuJiGouActivity.class);
			intent2.putExtra("agencyId", brokerInfo.getUid());
			startActivity(intent2);
			break;
		// 服务评价
		case R.id.rl_fuwu:
			Intent intent3 = new Intent(this, FuWuPingJiaActivity.class);
			intent3.putExtra("brokerId", brokerInfo.getId());
			startActivity(intent3);
			break;
		// 打电话
		case R.id.rl_call_brokerxq:
			showCallWindow();
			break;
		// 发短信
		case R.id.ll_sendMsg:
			sendSmsWithNumber(this, brokerInfo.getMobile());
			break;
		// 添加关注
		case R.id.tv_attion:
			if (AccountConfig.isLogin()) {
				if (isActtention == 1) {
					setAttation();
				} else {
					setAttationCancle();
				}
			} else {
				startActivity(new Intent(this, LoginActivity.class));
			}
			break;
		// 分享
		case R.id.img_share:
//			DialogShare dialog = new DialogShare();
//			dialog.showDialog(JingjirenXqActivity.this);
//			dialog.setCallBack(new DialogShareCallBack() {
//
//				public void handle(int type) {
//					if (type == 0) {// 微信的
//						new UmengSocialShare(JingjirenXqActivity.this).shareWx(brokerInfo.getBrokerName(),
//								brokerInfo.getBrokerName(), content.getWxUrl(),
//								ApiConfig.getImageUrl(brokerInfo.getHeadUrl()), brokerInfo.getId());
//					} else if (type == 1) {// 微信朋友圈的
//						new UmengSocialShare(JingjirenXqActivity.this).shareWxCircle(brokerInfo.getBrokerName(),
//								brokerInfo.getBrokerName(), content.getWxUrl(),
//								ApiConfig.getImageUrl(brokerInfo.getHeadUrl()), brokerInfo.getId());
//					} else if (type == 2) {// qq
//						new UmengSocialShare(JingjirenXqActivity.this).shareQQ(brokerInfo.getBrokerName(),
//								brokerInfo.getBrokerName(), content.getWxUrl(),
//								ApiConfig.getImageUrl(brokerInfo.getHeadUrl()), brokerInfo.getId());
//					}
//				}
//			});
			break;
		default:
			break;
		}
	}

	public void sendSmsWithNumber(Context context, String number) {
		Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
		context.startActivity(sendIntent);
	}

	private void showCallWindow() {
		View view = getLayoutInflater().inflate(R.layout.item_pop_call, null);
		final PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(this, view);
		popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		view.findViewById(R.id.textView1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		view.findViewById(R.id.tv_niming_call).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		view.findViewById(R.id.tv_call).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CommonUtils.call(JingjirenXqActivity.this, brokerInfo.getMobile());
			}
		});
		view.findViewById(R.id.tv_cancle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
	}

	public void setListViewHeightBasedOnChildren(GridView gridView) {
		if (secondHouseApdater == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < (secondHouseList.size()); i++) {
			View listItem = secondHouseApdater.getView(i, null, gridView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = gridView.getLayoutParams();
		params.height = totalHeight;
		((ViewGroup.MarginLayoutParams) params).setMargins(15, 15, 15, 15);
		gridView.setLayoutParams(params);
	}
}
