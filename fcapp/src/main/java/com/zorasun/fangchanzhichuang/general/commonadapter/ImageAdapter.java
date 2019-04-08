package com.zorasun.fangchanzhichuang.general.commonadapter;

import java.util.ArrayList;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<String> mList;
	private LayoutInflater inflater;

	public ImageAdapter(Context mContext, ArrayList<String> mList) {
		this.mContext = mContext;
		inflater = LayoutInflater.from(mContext);
		this.mList = mList;
	}

	public ImageAdapter(Context mContext, String[] str) {
		this.mContext = mContext;
		inflater = LayoutInflater.from(mContext);
		mList = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			mList.add(str[i]);
		}
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View view, ViewGroup parent) {

		view = inflater.inflate(R.layout.item_image, null);
		AsyncImageLoader.setAsynImages((ImageView) view.findViewById(R.id.iv),
				ApiConfig.getImageUrl(mList.get(position)));
		return view;
	}

}
