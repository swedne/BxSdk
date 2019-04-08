package com.zorasun.fangchanzhichuang.section.my;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.widget.CircleImageView;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.account.LoginActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 我的页面
 * 
 * @author 林文熹
 * @e-mail 635991604@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月14日 下午2:19:56
 */

public class MyFragment extends BaseFragment implements OnClickListener {

	private LayoutInflater inflater;
	private View view;
	private CircleImageView imgAvatar;
	private TextView tvNickName;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		view = inflater.inflate(R.layout.fragment_my, null);

		initUI();
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
		isLogin = AccountConfig.isLogin();

		String name = AccountConfig.getAccountName();
		if (isLogin) {
			tvNickName.setText(name);
			setAvatar();
		} else {
			tvNickName.setText("登录/注册");
			imgAvatar.setImageResource(R.drawable.touxiang);
		}

	}

	private void setAvatar() {
		AsyncImageLoader.setAsynAvatarImagesInfo(imgAvatar, AccountConfig.getAccountAvatarul());
	}

	private boolean isLogin;

	private void initUI() {
		imgAvatar = (CircleImageView) view.findViewById(R.id.img_avatar);
		tvNickName = (TextView) view.findViewById(R.id.tv_nickname);
		LinearLayout ll_imageHead = (LinearLayout) view.findViewById(R.id.ll_imageHead);
		ll_imageHead.setOnClickListener(this);
		TextView tv_my_demand = (TextView) view.findViewById(R.id.tv_my_demand);
		tv_my_demand.setOnClickListener(this);
		TextView tv_my_setting = (TextView) view.findViewById(R.id.tv_my_setting);
		tv_my_setting.setOnClickListener(this);
		TextView tv_my_collect = (TextView) view.findViewById(R.id.tv_my_collect);
		tv_my_collect.setOnClickListener(this);
		TextView tv_my_attent = (TextView) view.findViewById(R.id.tv_my_attent);
		tv_my_attent.setOnClickListener(this);
		view.findViewById(R.id.tv_my_feedback).setOnClickListener(this);
		view.findViewById(R.id.tv_my_kefutel).setOnClickListener(this);
	}

	private void showKeFuWindow() {
		View view = inflater.inflate(R.layout.item_pop_kefu, null);
		final TextView tvPhone = (TextView) view.findViewById(R.id.tv_call);
		final PopupWindow popupWindow = PopupWindowUtil.getPopupWindow(getActivity(), view);
		popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
		view.findViewById(R.id.textView1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		view.findViewById(R.id.tv_call).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String str = tvPhone.getText().toString();
				String str2 = "";
				if (str != null && !"".equals(str)) {
					for (int i = 0; i < str.length(); i++) {
						if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
							str2 += str.charAt(i);
						}
					}
					CommonUtils.call(getActivity(), str2);
				}
			}
		});
		view.findViewById(R.id.tv_cancle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 登录
		case R.id.ll_imageHead:
			if (isLogin) {
				Intent imageheadIntent = new Intent(getActivity(), MyDataActivity.class);
				// startActivity(imageheadIntent);
				startActivityForResult(imageheadIntent, 1);
			} else {
				Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
				startActivity(loginIntent);
			}
			break;
		// 我的需求
		case R.id.tv_my_demand:
			if (AccountConfig.isLogin()) {
				Intent myDemandIntent = new Intent(getActivity(), MyDemandActivity2.class);
				startActivity(myDemandIntent);
			} else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		// 设置
		case R.id.tv_my_setting:
			Intent mySettingIntent = new Intent(getActivity(), SettingActivity.class);
			startActivity(mySettingIntent);
			break;
		// 我的收藏
		case R.id.tv_my_collect:
			if (AccountConfig.isLogin()) {
				Intent mycollectIntent = new Intent(getActivity(), MyCollectActivity2.class);
				startActivity(mycollectIntent);
			} else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		// 我的关注
		case R.id.tv_my_attent:
			if (AccountConfig.isLogin()) {
				Intent myattentIntent = new Intent(getActivity(), MyAttentActivity.class);
				startActivity(myattentIntent);
			} else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		// 意见反馈
		case R.id.tv_my_feedback:
			if (AccountConfig.isLogin()) {
				startActivity(new Intent(getActivity(), FankuiYijianActivity.class));
			} else {
				startActivity(new Intent(getActivity(), LoginActivity.class));
			}
			break;
		// 客服电话
		case R.id.tv_my_kefutel:
			showKeFuWindow();
			break;
		default:
			break;
		}
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

}
