package com.zorasun.fangchanzhichuang.general.commonadapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;  

/**
 * 通用适配器(不含ViewHolder)
 * CommonAdapter
 * 
 * @author chenzhifeng
 * @e-mail 731739299@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月14日 下午2:19:56
 *
 */
public abstract class Common2Adapter<T> extends BaseAdapter
{  
    protected LayoutInflater mInflater;  
    protected Context mContext;  
    protected List<T> mDatas;  
    protected final int mItemLayoutId;  
  
    public Common2Adapter(Context context, List<T> mDatas, int itemLayoutId)  
    {  
        this.mContext = context;  
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;  
        this.mItemLayoutId = itemLayoutId;  
    }  
  
    @Override  
    public int getCount()  
    {  
        return mDatas.size();  
    }  
  
    @Override  
    public T getItem(int position)  
    {  
        return mDatas.get(position);  
    }  
  
    @Override  
    public long getItemId(int position)  
    {  
        return position;  
    }  
  
    @SuppressLint("ViewHolder")
	@Override  
    public View getView(int position, View convertView, ViewGroup parent)
    {  
    	convertView = LayoutInflater.from(mContext).inflate(mItemLayoutId, parent,
                false);
        convert(convertView, getItem(position), position);
        return convertView;
    }  
  
    public abstract void convert(View helper, T item, int position);  
  
} 



