package com.zorasun.fangchanzhichuang.general.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 */
public class ImageUtils {
	public static Drawable Bitmap2Drawable(Context aContext, Bitmap aBitmap) {
		@SuppressWarnings("deprecation")
		BitmapDrawable ret = aBitmap != null ? new BitmapDrawable(aBitmap) : null;
		ret.setTargetDensity(aContext.getResources().getDisplayMetrics());
		return ret;
	}

	public static Bitmap Drawable2Bitmap(Drawable aDrawable) {
		return aDrawable != null ? ((BitmapDrawable) aDrawable).getBitmap() : null;
	}

	public static Drawable GetDrawableByResName(Context aContext, String aResName) {
		Drawable ret = null;
		String iconName = aResName.replace("res://", "").replace(".png", "").toLowerCase(Locale.ENGLISH);
		int indentify = GetResIdByResName(aContext, iconName);
		if (indentify > 0) {
			ret = GetDrawableByResId(aContext, indentify);
		}
		return ret;
	}

	public static int GetResIdByResName(Context aContext, String aResName) {
		String iconName = aResName.replace("res://", "").replace(".png", "").toLowerCase(Locale.ENGLISH);
		int indentify = aContext.getResources().getIdentifier(aContext.getPackageName() + ":drawable/" + iconName, null,
				null);
		return indentify;
	}

	public static Drawable GetDrawableByResId(Context aContext, int aDrawableId) {
		Drawable bmp = aContext.getResources().getDrawable(aDrawableId);
		return bmp;
	}

	/**
	 * 本地展示获取压缩，压缩力度比较大
	 * 
	 * @param aImageName
	 * @return
	 */
	public static final Bitmap File2Bitmap(String aImageName) {
		if (aImageName != null & aImageName.length() > 0) {
			File b = getFile(aImageName);
			if (b.exists() && b.length() > 0) {
				try {
					BitmapFactory.Options options = new BitmapFactory.Options();

					options.inJustDecodeBounds = true;

					BitmapFactory.decodeFile(b.getAbsolutePath(), options);

					if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {

						AppLog.redLog("File2BitmapHW", "alert!!!" + String.valueOf(options.mCancel) + options.outWidth
								+ " " + options.outHeight);
						return null;
					}
					ExifInterface exif = new ExifInterface(b.getAbsolutePath());

					int picwidth = exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 1000);

					// int be = picwidth / 200;
					int be = picwidth / 500;
					if (be <= 0) {
						be = 1;
					}

					options.inJustDecodeBounds = false;
					options.inSampleSize = be;
					return BitmapFactory.decodeFile(b.getAbsolutePath(), options);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (OutOfMemoryError e) {
					AppLog.redLog("OutOfMemoryError", e.toString());
				}
				return null;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 压缩上传
	 * 
	 * @param aImageName
	 * @return
	 */
	public static final Bitmap File2BitmapUpload0(String aImageName) {
		if (aImageName != null & aImageName.length() > 0) {
			File b = getFile(aImageName);
			if (b.exists() && b.length() > 0) {
				try {
					BitmapFactory.Options options = new BitmapFactory.Options();

					options.inJustDecodeBounds = true;

					BitmapFactory.decodeFile(b.getAbsolutePath(), options);

					if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {

						AppLog.redLog("File2BitmapHW", "alert!!!" + String.valueOf(options.mCancel) + options.outWidth
								+ " " + options.outHeight);
						return null;
					}
					ExifInterface exif = new ExifInterface(b.getAbsolutePath());

					@SuppressWarnings("unused")
					int picwidth = exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 1000);

					int w = options.outWidth;
					int h = options.outHeight;
					float hh = 960f;// 这里设置高度为800f
					float ww = 640;// 这里设置宽度为480f
					// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
					int be = 1;// be=1表示不缩放
					if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
						be = (int) (options.outWidth / ww);
					} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
						be = (int) (options.outHeight / hh);
					}

					if (be <= 0) {
						be = 1;
					}

					// int be = picwidth / 500;
					// if (be <= 0)
					// {
					// be = 1;
					// }
					options.inJustDecodeBounds = false;
					options.inSampleSize = be;
					return BitmapFactory.decodeFile(b.getAbsolutePath(), options);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (OutOfMemoryError e) {
					AppLog.redLog("OutOfMemoryError", e.toString());
				}
				return null;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Bitmap File2BitmapUpload(String srcPath) {
		try {
			// BitmapFactory.Options newOpts = new BitmapFactory.Options();
			// // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
			// newOpts.inJustDecodeBounds = true;
			// Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//
			// 此时返回bm为空
			//
			// newOpts.inJustDecodeBounds = false;
			// int w = newOpts.outWidth;
			// int h = newOpts.outHeight;
			//
			// System.out.println("前w======" + w + "==h======" + h);
			//
			// int be = 1;// be=1表示不缩放
			// be = calculateInSampleSize(newOpts, 640, 960);
			//
			// newOpts.inSampleSize = be;// 设置缩放比例
			// // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
			// bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
			// System.out.println("后w=====" + newOpts.outWidth);
			// // return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
			// return bitmap;

			BitmapFactory.Options newOpts = new BitmapFactory.Options();
			newOpts.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
			newOpts.inJustDecodeBounds = false;
			// int height = newOpts.outHeight;
			// int width = newOpts.outWidth;
			int be = 1;// be=1表示不缩放
			be = calculateInSampleSize(newOpts, 640, 960);

			newOpts.inSampleSize = be;// 设置缩放比例
			newOpts.inPurgeable = true;// 同时设置才会有效
			newOpts.inInputShareable = true;// 当系统内存不够时候图片自动被回收
			return BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
		} catch (OutOfMemoryError e) {
			AppLog.redLog("OutOfMemoryError", e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Bitmap getDiskBitmap(String pathString) {
		Bitmap bitmap = null;
		try {
			File file = new File(pathString);
			if (file.exists()) {
				bitmap = BitmapFactory.decodeFile(pathString);
			}
		} catch (Exception e) {
		}

		return bitmap;
	}

	/**
	 * 计算图片的缩放值
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = Math.max(heightRatio, widthRatio);
		}

		return inSampleSize;
	}

	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) {
			// 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	public static Bitmap compressedBitmap(Bitmap bitMap, double maxSize) {
		double mid = getBitmapSize(bitMap);
		// 判断bitmap占用空间是否大于允许最大空间 如果大于则压缩 小于则不压缩
		if (mid > maxSize) {
			// 获取bitmap大小 是允许最大大小的多少倍
			double i = mid / maxSize;
			// 开始压缩 此处用到平方根 将宽带和高度压缩掉对应的平方根倍
			// （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
			float scale = (float) (1.0 / Math.sqrt(i));
			bitMap = zoomImage(bitMap, scale);
			return bitMap;
		}
		return bitMap;
	}

	public static Bitmap zoomImage(Bitmap bgimage, float scale) {
		// 获取这个图片的宽和高
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();

		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();

		// 缩放图片动作
		matrix.postScale(scale, scale);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
		return bitmap;
	}

	// 获取图片的大小的字节数
	@SuppressLint("NewApi")
	public static double getBitmapSize(Bitmap bitMap) {
		// //将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
		// ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		// byte[] b = baos.toByteArray();
		//
		// //获得字节
		// double mid = b.length;
		//
		// return mid;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			return bitMap.getByteCount();
		} else {
			return bitMap.getRowBytes() * bitMap.getHeight();
		}
	}

	public static File getFile(String aImagePath) {
		String path = getFilePath(aImagePath);
		return path != null ? new File(path) : null;
	}

	public static String getFilePath(String aImagePath) {
		return aImagePath != null ? aImagePath.replace("sdcard://", "") : null;
	}

	/**
	 * 保存图片
	 * <p>
	 * Title: saveImage<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param photo
	 * @param spath
	 */
	public static void saveImage(Bitmap photo, String spath) {
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(spath, false));
			photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
			bos.flush();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public static String SaveBitmap(Bitmap bmp, String name) {
		String path = null;
		// 初始化
		String picPath = Environment.getExternalStorageDirectory().getPath() + "/zxht/bpic/";
		// CramerProActivity.imageView.setImageBitmap(null);
		String picname = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
		File file = new File(picPath);
		if (!file.exists()) {
			// 检查图片存放的文件夹是否存在
			file.mkdirs();
			// 不存在的话 创建文件夹
		}
		if (bmp != null) {
			try {
				path = picPath + picname;
				FileOutputStream fileOutputStream = new FileOutputStream(path);

				bmp.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
				fileOutputStream.flush();
				fileOutputStream.close();
				System.out.println("saveBmp is here");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return path;
	}

	/** 缩放Bitmap图片 **/

	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {

		int w = bitmap.getWidth();

		int h = bitmap.getHeight();
		if (w > 100) {
			Matrix matrix = new Matrix();

			float scaleWidth = ((float) width / w);

			float scaleHeight = ((float) height / h);

			matrix.postScale(scaleWidth, scaleHeight);// 利用矩阵进行缩放不会造成内存溢出

			Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);

			return newbmp;
		} else {
			return bitmap;
		}
	}

	public static Bitmap decodeUriAsBitmap(Context aContext, Uri imageUri) {
		Bitmap bitmap = null;
		if (imageUri != null) {
			try {
				bitmap = BitmapFactory.decodeStream(aContext.getContentResolver().openInputStream(imageUri));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}

		return bitmap;
	}

	/**
	 * 获取圆角位图的方法
	 * 
	 * @param bitmap
	 *            需要转化成圆角的位图
	 * @param pixels
	 *            圆角的度数，数值越大，圆角越大
	 * @return 处理后的圆角位图
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * 获取图片的宽高比
	 * 
	 * @param context
	 * @param iv
	 * @return
	 */
	public static double getImageRatio(Context context, int i) {
		Bitmap iv = ImageUtils.Drawable2Bitmap(context.getResources().getDrawable(i));
		int height = iv.getHeight();
		int width = iv.getWidth();
		return width / (height * 1.0);
	}

	/**
	 * 根据比例设置图片的高
	 *
	 * @param context
	 * @param ratio
	 *            比例(width/height)
	 * @param iv
	 */
	public static int setImageViewHeight(Context context, double ratio, ImageView iv) {
		int width = AppHelper.getScreenWidth(context);
		AppLog.redLog("ImageUtil",
				"width:" + width + ";height:" + (int) Math.rint(width / ratio) + ";width / ratio:" + width / ratio);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, (int) Math.rint(width / ratio));
		iv.setLayoutParams(params);
		return width;
	}

	/**
	 * 根据比例设置图片的高
	 *
	 * @param context
	 * @param ratio
	 *            比例(width/height)
	 * @param iv
	 */
	public static int setImageViewHeight2(Context context, double ratio, ImageView iv) {
		int width = AppHelper.getScreenWidth(context);
		AppLog.redLog("ImageUtil",
				"width:" + width + ";height:" + (int) Math.rint(width / ratio) + ";width / ratio:" + width / ratio);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, (int) Math.rint(width / ratio));
		iv.setLayoutParams(params);
		return width;
	}

}
