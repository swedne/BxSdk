package com.zorasun.fangchanzhichuang.general.widget;

import java.util.ArrayList;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 图片选择器
 * 
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年8月26日 下午6:05:23
 */
public class GeneralImageAdapter extends BaseAdapter {
	private List<String> tempSelectBitmap = new ArrayList<String>();
	private Activity mContext;

	public GeneralImageAdapter(Activity context, ArrayList<String> arrayList) {
		this.mContext = context;
		tempSelectBitmap = arrayList;
	}

	@Override
	public int getCount() {
		return tempSelectBitmap.size();

	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_general_image, null);
			viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
			convertView.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) convertView.getTag();

		AsyncImageLoader.setAsynImages(viewHolder.iv, ApiConfig.getImageUrl(tempSelectBitmap.get(position)));
		return convertView;
	}

	private class ViewHolder {
		ImageView iv;
	}

}
