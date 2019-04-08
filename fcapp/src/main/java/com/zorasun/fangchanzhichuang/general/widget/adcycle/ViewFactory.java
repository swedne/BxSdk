package com.zorasun.fangchanzhichuang.general.widget.adcycle;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.util.AppHelper;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

/**
 * ImageView创建工厂
 */
public class ViewFactory
{

	/**
	 * 获取ImageView视图的同时加载显示url
	 * 
	 * @param text
	 * @return
	 */
	@SuppressLint("InflateParams")
	public static ImageView getImageView(Context context, String url)
	{
		// 图片比例是5：1
		@SuppressWarnings("unused")
		int widhth = AppHelper.getScreenWidth(context);
		StringBuilder imageUrl = new StringBuilder();
		// imageUrl.append("/");
		imageUrl.append(url);
//		imageUrl.insert(imageUrl.lastIndexOf("."), "_" + widhth + "_" + widhth / 5);
		ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.view_banner, null);
		AsyncImageLoader.setAsynImages(imageView, imageUrl.toString());
		return imageView;
	}
}
