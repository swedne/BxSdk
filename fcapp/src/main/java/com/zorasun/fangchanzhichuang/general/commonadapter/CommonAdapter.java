package com.zorasun.fangchanzhichuang.general.commonadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 通用适配器 CommonAdapter
 * 
 * @author chenzhifeng
 * @e-mail seven2729@126.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年10月14日 下午2:19:56
 *
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int layoutId;

	public CommonAdapter(Context context, List<T> mDatas, int layoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(context);
		this.mDatas = mDatas;
		this.layoutId = layoutId;
	}

	@Override
	public int getCount() {
		return mDatas != null ? mDatas.size() : 0;
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
		convert(viewHolder, getItem(position), position);
		return viewHolder.getConvertView();

	}

	public abstract void convert(ViewHolder helper, T item, int position);

}