package com.zorasun.fangchanzhichuang.section.senddemand;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * fram-发需求
 * 
 * @author 林文熹
 * @e-mail 635991604@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月14日 下午2:19:56
 * 
 */
public class sendDemandFragment extends BaseFragment implements OnClickListener {
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_senddemand, null);
		initTitleBar(view);
		initView(view);
		return view;
	}

	private void initTitleBar(View view) {
		TextView title_name = (TextView) view.findViewById(R.id.title_name);
		title_name.setText("发布需求");
		ImageView back = (ImageView) view.findViewById(R.id.title_left);
		back.setVisibility(View.GONE);
	}

	private void initView(View view) {

		view.findViewById(R.id.rl_demand_toRend).setOnClickListener(this);
		RelativeLayout demand_buySecondHouse = (RelativeLayout) view.findViewById(R.id.rl_demand_buySecondHouse);
		demand_buySecondHouse.setOnClickListener(this);
		RelativeLayout demand_sellSecondHouse = (RelativeLayout) view.findViewById(R.id.rl_demand_sellSecondHouse);
		demand_sellSecondHouse.setOnClickListener(this);
		RelativeLayout demand_wantRend = (RelativeLayout) view.findViewById(R.id.rl_demand_wantRend);
		demand_wantRend.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		// 求购二手房
		case R.id.rl_demand_buySecondHouse:
			// if (!AccountConfig.isLogin()) {
			// intent = new Intent(getActivity(), LoginActivity.class);
			// } else {
			intent = new Intent(getActivity(), BuySecondHouseActivity.class);
			// }
			break;
		// 出售二手房
		case R.id.rl_demand_sellSecondHouse:
			// if (!AccountConfig.isLogin()) {
			// intent = new Intent(getActivity(), LoginActivity.class);
			// } else {
			intent = new Intent(getActivity(), SellSecondHouseActivity.class);
			// }
			break;
		// 求租
		case R.id.rl_demand_wantRend:
			// if (!AccountConfig.isLogin()) {
			// intent = new Intent(getActivity(), LoginActivity.class);
			// } else {
			intent = new Intent(getActivity(), WantRentActivity.class);
			// }
			break;
		// 出租
		case R.id.rl_demand_toRend:
			// if (!AccountConfig.isLogin()) {
			// intent = new Intent(getActivity(), LoginActivity.class);
			// } else {
			intent = new Intent(getActivity(), SellSecondHouseActivity.class);
			intent.putExtra("wantRend", SystemConstant.CHUZU_INTENT);
			// }
			break;
		default:
			break;
		}
		startActivity(intent);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

	}

}
