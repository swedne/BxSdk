package com.zorasun.fangchanzhichuang.general.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class AsyncImageLoader {
	/**
	 * 头像加载的时候使用
	 */
	private static DisplayImageOptions avaterOptions = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.item_customer_head) // 加载图片时的图片
			.showImageForEmptyUri(R.drawable.item_customer_head)// 没有图片资源时的默认图片
			.showImageOnFail(R.drawable.item_customer_head) // 加载失败时的图片
			.cacheInMemory(true) // 启用内存缓存
			.cacheOnDisk(true) // 启用外存缓存
//			.considerExifParams(true) // 启用EXIF和JPEG图像格式
			.bitmapConfig(Bitmap.Config.RGB_565)
			// .displayer(new RoundedBitmapDisplayer(20)) //设置显示风格这里是圆角矩形
			.build();
	public static DisplayImageOptions uploadOptions = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.lunbo_noresult).showImageForEmptyUri(R.drawable.lunbo_noresult)
			.showImageOnFail(R.drawable.lunbo_noresult).cacheInMemory(true).cacheOnDisk(true)
			.considerExifParams(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			// .displayer(new RoundedBitmapDisplayer(radiu))
			.build();
	public static DisplayImageOptions uploadOptionsNoExif = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.lunbo_noresult).showImageForEmptyUri(R.drawable.lunbo_noresult)
			.showImageOnFail(R.drawable.lunbo_noresult).cacheInMemory(true).cacheOnDisk(true)
			.considerExifParams(false)
			.bitmapConfig(Bitmap.Config.RGB_565)
			// .displayer(new RoundedBitmapDisplayer(radiu))
			.build();

	private static DisplayImageOptions dynamicoptions = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.none).showImageForEmptyUri(R.drawable.none).showImageOnFail(R.drawable.none)
			.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565)
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
			// .displayer(new RoundedBitmapDisplayer(20))
			.build();

	private static DisplayImageOptions originaloptions = new DisplayImageOptions.Builder().cacheInMemory(true)
			.cacheOnDisk(true).considerExifParams(true)
			// .displayer(new RoundedBitmapDisplayer(20))
			.build();

	private static ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				String url = mImageHash.get(view);
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					if (imageView.getVisibility() == View.VISIBLE) {
						FadeInBitmapDisplayer.animate(imageView, 500);
					}
					displayedImages.add(imageUri);
				}
				if (url.equals(imageUri)) {
					imageView.setImageBitmap(loadedImage);
				}
			}
		}
	}

	static HashMap<View, String> mImageHash = new HashMap<View, String>();
	private static ImageLoaderConfiguration mImageLoaderConfiguration = null;

	/**
	 * 用户头像加载
	 * 
	 * @param aImageView
	 * @param aImageUrl
	 */
	public static void setAsynAvatarImagesInfo(ImageView aImageView, String aImageUrl) {
		try {
			if (aImageUrl != null) {
				aImageUrl = ApiConfig.getImageUrl(aImageUrl);
				mImageHash.put(aImageView, aImageUrl);
				String url = aImageUrl;
				if (aImageUrl.startsWith("res://")) {
					Drawable ret = ImageUtils.GetDrawableByResName(aImageView.getContext(), aImageUrl);
					aImageView.setImageDrawable(ret);
				} else if (aImageUrl.startsWith("http://") || aImageUrl.startsWith("https://")) {
					if (!url.startsWith("http://q.qlogo.cn") && !url.startsWith("http://wx.qlogo.cn/")) {
						// 微信和qq头像的地址与自己上传的有区别
						StringBuilder imageUrl = new StringBuilder(url);
						// imageUrl.append("/");
						// imageUrl.insert(imageUrl.lastIndexOf("."), "_" + 100
						// + "_" + 100);
						ImageLoader.getInstance().displayImage(imageUrl.toString(), aImageView, avaterOptions,
								animateFirstListener);
					} else {
						ImageLoader.getInstance().displayImage(url.toString(), aImageView, avaterOptions,
								animateFirstListener);
					}
				} else if (aImageUrl.startsWith("sdcard://")) {
					Bitmap ret = ImageUtils.File2Bitmap(aImageUrl.replace("sdcard://", ""));
					if (ret != null) {
						aImageView.setImageBitmap(ret);
					}
				}
			} else {
				aImageView.setImageResource(R.drawable.item_customer_head);
			}
		} catch (Exception e) {
			aImageView.setImageResource(R.drawable.item_customer_head);
		}
	}

	/**
	 * 图片加载
	 * 
	 * @param aImageView
	 * @param aImageUrl
	 * @param aImageLoadingListener
	 */
	public static void setAsynImages(ImageView aImageView, String aImageUrl,
			ImageLoadingListener aImageLoadingListener) {
		try {
			mImageHash.put(aImageView, aImageUrl);
			aImageView.setTag("Asyn:" + aImageUrl);
			if (aImageUrl.startsWith("res://")) {
				Drawable ret = ImageUtils.GetDrawableByResName(aImageView.getContext(), aImageUrl);
				aImageView.setImageDrawable(ret);

				if (aImageLoadingListener != null) {
					aImageLoadingListener.onLoadingComplete(aImageUrl, aImageView, ImageUtils.Drawable2Bitmap(ret));
				}
			} else if (aImageUrl.startsWith("http://") || aImageUrl.startsWith("https://")) {
				ImageLoader.getInstance().displayImage(aImageUrl, aImageView, uploadOptions, aImageLoadingListener);
			} else if (aImageUrl.startsWith("sdcard://")) {
				Bitmap ret = ImageUtils.File2Bitmap(aImageUrl.replace("sdcard://", ""));
				if (ret != null) {
					aImageView.setImageBitmap(ret);
				}
				if (aImageLoadingListener != null) {
					aImageLoadingListener.onLoadingComplete(aImageUrl, aImageView, ret);
				}
			} else {
				// aImageView.setImageDrawable(null);
				aImageView.setBackgroundResource(0);
			}
		} catch (Exception e) {
			AppLog.redLog("setAsynAvatarImages", e.toString());
			aImageView.setBackgroundResource(0);
		}
	}

	/**
	 * 图片加载
	 * 
	 * @param aImageView
	 * @param aImageUrl
	 * @param aImageLoadingListener
	 */
	public static void setAsynImages(ImageView aImageView, String aImageUrl) {
		if (aImageUrl != null) {
			try {
				aImageUrl = ApiConfig.getImageUrl(aImageUrl);
				mImageHash.put(aImageView, aImageUrl);
				aImageView.setTag("Asyn:" + aImageUrl);
				if (aImageUrl.startsWith("res://")) {
					Drawable ret = ImageUtils.GetDrawableByResName(aImageView.getContext(), aImageUrl);
					aImageView.setImageDrawable(ret);
				} else if (aImageUrl.startsWith("http://") || aImageUrl.startsWith("https://")) {
					ImageLoader.getInstance().displayImage(aImageUrl, aImageView, uploadOptions);
				} else if (aImageUrl.startsWith("sdcard://")) {
					Bitmap ret = ImageUtils.File2Bitmap(aImageUrl.replace("sdcard://", ""));
					if (ret != null) {
						aImageView.setImageBitmap(ret);
					}
				} else {
					aImageView.setBackgroundResource(0);
				}
			} catch (Exception e) {
				AppLog.redLog("setAsynAvatarImages", e.toString());
				aImageView.setBackgroundResource(0);
			}
		} else {
			aImageView.setBackgroundResource(0);
		}
	}
	/**
	 * 图片加载，没有考虑jpeg的旋转
	 * 
	 * @param aImageView
	 * @param aImageUrl
	 * @param aImageLoadingListener
	 */
	public static void setAsynImagesNoExif(ImageView aImageView, String aImageUrl) {
		if (aImageUrl != null) {
			try {
				aImageUrl = ApiConfig.getImageUrl(aImageUrl);
				mImageHash.put(aImageView, aImageUrl);
				aImageView.setTag("Asyn:" + aImageUrl);
				if (aImageUrl.startsWith("res://")) {
					Drawable ret = ImageUtils.GetDrawableByResName(aImageView.getContext(), aImageUrl);
					aImageView.setImageDrawable(ret);
				} else if (aImageUrl.startsWith("http://") || aImageUrl.startsWith("https://")) {
					ImageLoader.getInstance().displayImage(aImageUrl, aImageView, uploadOptionsNoExif);
				} else if (aImageUrl.startsWith("sdcard://")) {
					Bitmap ret = ImageUtils.File2Bitmap(aImageUrl.replace("sdcard://", ""));
					if (ret != null) {
						aImageView.setImageBitmap(ret);
					}
				} else {
					aImageView.setBackgroundResource(0);
				}
			} catch (Exception e) {
				AppLog.redLog("setAsynAvatarImages", e.toString());
				aImageView.setBackgroundResource(0);
			}
		} else {
			aImageView.setBackgroundResource(0);
		}
	}

	@SuppressWarnings("deprecation")
	public static void deleteCache(String aUrl) {
		DiskCacheUtils.removeFromCache(aUrl, ImageLoader.getInstance().getDiscCache());
		MemoryCacheUtils.removeFromCache(aUrl, ImageLoader.getInstance().getMemoryCache());
	}

	public static Bitmap getImage(String aUrl) {
		return ImageLoader.getInstance().loadImageSync(aUrl, originaloptions);
	}

	public static Bitmap getImage(String aUrl, Context context) {
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
		return ImageLoader.getInstance().loadImageSync(aUrl, originaloptions);
	}

	@SuppressWarnings("deprecation")
	public static void setBigAsynImages(ImageView aImageView, String aImageUrl) {
		try {
			if (aImageUrl != null && !aImageUrl.equals("")) {
				mImageHash.put(aImageView, aImageUrl);
				String url = aImageUrl;
				if (aImageUrl.startsWith("res://")) {
					Drawable ret = ImageUtils.GetDrawableByResName(aImageView.getContext(), aImageUrl);
					aImageView.setImageDrawable(ret);
				} else if (aImageUrl.startsWith("http://") || aImageUrl.startsWith("https://")) {

					if (mImageLoaderConfiguration == null) {
						mImageLoaderConfiguration = new ImageLoaderConfiguration.Builder(aImageView.getContext())
								.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
								.discCacheFileNameGenerator(new Md5FileNameGenerator())
								.tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove
																									// for
																									// release
																									// app
								.build();
						// Initialize ImageLoader with configuration.
						ImageLoader.getInstance().init(mImageLoaderConfiguration);
					}

					ImageLoader.getInstance().displayImage(url, aImageView, dynamicoptions, animateFirstListener);
				} else if (aImageUrl.startsWith("sdcard://")) {
					Bitmap ret = ImageUtils.File2Bitmap(aImageUrl.replace("sdcard://", ""));
					if (ret != null) {
						aImageView.setImageBitmap(ret);
					}
				}
			} else {
				aImageView.setImageResource(R.drawable.none);
			}
		} catch (Exception e) {
			aImageView.setImageResource(R.drawable.none);
			AppLog.redLog("setAsynImage", e.toString());
		}
	}

}
