package com.zorasun.fangchanzhichuang.general.util;

import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.marco.ImageSizeConfig;
import com.zorasun.fangchanzhichuang.section.account.entity.SlideEntity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/**
 * 设置幻灯片效果的工具类
 * 
 * @author 杨伟锦
 * @e-mail 1147953072@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2014-8-14 下午2:46:48
 */
public class ViewPagerUtils
{
	/**
	 * 没有图片时加载一张默认图片
	 * 
	 * @param context
	 * @param pointGroup
	 * @param list
	 * @param points
	 */
	public static void initViewPager(Context context, LinearLayout pointGroup, List<View> list, List<View> points)
	{
		ImageView imageView = new ImageView(context);
		imageView.setScaleType(ScaleType.CENTER_INSIDE);
		AsyncImageLoader.setBigAsynImages(imageView, "");
		list.add(imageView);
		for (int j = 0; j < list.size(); j++)
		{
			ImageView point = new ImageView(context);
			if (j == 0)
			{
				point.setBackgroundResource(R.drawable.point_slide_p);
			}
			else
			{
				point.setBackgroundResource(R.drawable.point_slide_n);
			}
			point.setScaleType(ScaleType.FIT_XY);
			android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 5, 5, 5);
			point.setLayoutParams(params);
			points.add(point);
			// pointGroup.addView(point);
		}

	}

	/**
	 * 添加图片到幻灯片中
	 * 
	 * @param context
	 * @param pointGroup
	 * @param list
	 * @param points
	 * @param urls
	 */
	public static void setImageView(Context context, LinearLayout pointGroup, List<View> list, List<View> points,
			List<String> urls)
	{
		list.clear();
		points.clear();
		for (int i = 0; i < urls.size(); i++)
		{
			ImageView imageView = new ImageView(context);
			imageView.setScaleType(ScaleType.FIT_XY);
			AsyncImageLoader.setAsynImages(imageView,
					ApiConfig.getImageUrl(urls.get(i), ImageSizeConfig.PRODUCT_DETAILS));
			list.add(imageView);
		}
		for (int j = 0; j < list.size(); j++)
		{
			ImageView point = new ImageView(context);
			if (j == 0)
			{
				point.setBackgroundResource(R.drawable.point_slide_p);
			}
			else
			{
				point.setBackgroundResource(R.drawable.point_slide_n);
			}
			point.setScaleType(ScaleType.FIT_XY);
			android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 5, 5, 5);
			point.setLayoutParams(params);
			points.add(point);
			pointGroup.addView(point);
		}

	}

	/**
	 * 添加图片到幻灯片中
	 * 
	 * @param context
	 * @param pointGroup
	 * @param list
	 * @param points
	 * @param urls
	 */
	public static void setImageView2(Context context, LinearLayout pointGroup, List<View> list, List<View> points,
			List<SlideEntity> imgs)
	{
		list.clear();
		points.clear();
		for (int i = 0; i < imgs.size(); i++)
		{
			ImageView imageView = new ImageView(context);
			imageView.setScaleType(ScaleType.FIT_XY);
			AsyncImageLoader.setBigAsynImages(imageView,
					ApiConfig.getImageUrl(imgs.get(i).slidePic, 0));
//			ImageLoader.getInstance().displayImage(ApiConfig.getImageUrl(imgs.get(i).slidePic, Utils.getWidth(context),Utils.getWidth(context)), imageView);
			list.add(imageView);
		}
		for (int j = 0; j < list.size(); j++)
		{
			ImageView point = new ImageView(context);
			if (j == 0)
			{
				point.setBackgroundResource(R.drawable.point_slide_p);
			}
			else
			{
				point.setBackgroundResource(R.drawable.point_slide_n);
			}
			point.setScaleType(ScaleType.FIT_XY);
			android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 5, 5, 5);
			point.setLayoutParams(params);
			points.add(point);
			pointGroup.addView(point);
		}
	}

	/**
	 * 添加图片到幻灯片中
	 * 
	 * @param context
	 * @param pointGroup
	 * @param list
	 * @param points
	 * @param urls
	 */
	public static void setImageView3_1(Context context, LinearLayout pointGroup, List<View> list, List<View> points,
			List<SlideEntity> imgs, final OnClickCallBack aOnClickCallBack)
	{
		list.clear();
		points.clear();
		for (int i = 0; i < imgs.size(); i++)
		{
			ImageView imageView = new ImageView(context);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View arg0)
				{
					if (aOnClickCallBack != null)
					{
						aOnClickCallBack.onClickCallBack(arg0);
					}
				}
			});
			AsyncImageLoader.setAsynImages(imageView,
					ApiConfig.getImageUrl(imgs.get(i).slidePic, ImageSizeConfig.PRODUCT_DETAILS));
			list.add(imageView);
		}
		for (int j = 0; j < list.size(); j++)
		{
			ImageView point = new ImageView(context);
			if (j == 0)
			{
				point.setBackgroundResource(R.drawable.point_slide_p);
			}
			else
			{
				point.setBackgroundResource(R.drawable.point_slide_n);
			}
			point.setScaleType(ScaleType.FIT_XY);
			android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 5, 5, 5);
			point.setLayoutParams(params);
			points.add(point);
			pointGroup.addView(point);
		}
	}
	
	
	/**
	 * 添加图片到幻灯片中
	 * 
	 * @param context
	 * @param pointGroup
	 * @param list
	 * @param points
	 * @param urls
	 */
	public static void setImageView3(Context context, LinearLayout pointGroup, List<View> list, List<View> points,
			List<SlideEntity> imgs, final OnClickCallBack aOnClickCallBack)
	{
		list.clear();
		points.clear();
		for (int i = 0; i < imgs.size(); i++)
		{
			ImageView imageView = new ImageView(context);
//			int result = AppHelper.getScreenWidth(context)/ImageSizeConfig.WIDTH;
//            int result1 = AppHelper.getScreenWidth(context)%ImageSizeConfig.WIDTH;
//            String ss  = String.valueOf(result)+"."+String.valueOf(result1);
//            double tt = Double.parseDouble(ss) * ImageSizeConfig.SLIDE_HEIGHT;
////            java.text.DecimalFormat df = new java.text.DecimalFormat("#");
////            String hh = df.format(df);
//            
////			Log.e("height","height"+ hh);
//			
//			BigDecimal bg = new BigDecimal(tt);
//			double height = bg.setScale(-1, BigDecimal.ROUND_HALF_UP).doubleValue();
//			Log.e("height","height"+ "<-height->" +"  "+height);
//
//			imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (int)height));
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View arg0)
				{
					if (aOnClickCallBack != null)
					{
						aOnClickCallBack.onClickCallBack(arg0);
					}
				}
			});
			AsyncImageLoader.setBigAsynImages(imageView,
					ApiConfig.getImageUrl(imgs.get(i).slidePic, ImageSizeConfig.PRODUCT_DETAILS));
			list.add(imageView);
		}
		for (int j = 0; j < list.size(); j++)
		{
			ImageView point = new ImageView(context);
			if (j == 0)
			{
				point.setBackgroundResource(R.drawable.point_slide_p);
			}
			else
			{
				point.setBackgroundResource(R.drawable.point_slide_n);
			}
			point.setScaleType(ScaleType.FIT_XY);
			android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 5, 5, 5);
			point.setLayoutParams(params);
			points.add(point);
			pointGroup.addView(point);
		}
	}

	public interface OnClickCallBack
	{
		public void onClickCallBack(View arg0);
	}
}
