package com.zorasun.fangchanzhichuang.general.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.account.entity.PictureEntity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

/**
 * 上传图片工具类
 */
public class ImageUploadUtils {
	public static final String TAG = "ImageUploadUtils";

	/**
	 * 上传图片(增加了fileId)
	 * 
	 * @param fileId
	 * @param aContext
	 * @param fileName
	 *            文件名
	 * @param apic
	 *            图片
	 * @param acallBack
	 */
//	public static void uploadImg(final Context aContext, final String cmd, final int fileId, final String fileName,
//			final Bitmap apic, final Handler handler) {
//		// ProgressDialog.getInstance().createLoadingDialog(aContext, false);
//		Thread a = new Thread() {
//			@Override
//			public void run() {
//				super.run();
//				// String uploadImgUrl = ApiConfig.IMAGE_URL + cmd;//
//				// ApiConfig.UPLOAD_WANTOBUY_IMG
//				String uploadImgUrl = cmd;
//				AppLog.redLog(TAG, uploadImgUrl);
//				try {
//					if (HttpUtils.isNetworkConnected(aContext)) {
//						// byte[] bytePic = BitmapCompression(apic, true);
//						byte[] bytePic = Bitmap2Bytes(apic, true);// 之前*
//						// byte[] bytePic =
//						// Bitmap2Bytes(ImageUtils.compressedBitmap(apic,
//						// 100*1024),false);
//						byte[] header = HttpUtils.GetPostHead();
//						byte[] footer = HttpUtils.GetPostEnd();
//						byte[] picdata = HttpUtils.GetPostFileContent("urlfile", fileId, fileName,
//								HttpUtils.GetPostContentType(), bytePic);
//						int postdatalength = header.length + footer.length + picdata.length;
//						byte[] postdata = new byte[postdatalength];
//						int offset = 0;
//
//						System.arraycopy(header, 0, postdata, offset, header.length);
//						offset += header.length;
//
//						System.arraycopy(picdata, 0, postdata, offset, picdata.length);
//						offset += picdata.length;
//
//						System.arraycopy(footer, 0, postdata, offset, footer.length);
//						offset += footer.length;
//						header = null;
//						footer = null;
//						picdata = null;
//						postData(aContext, uploadImgUrl, postdata, handler, -1, fileId);
//						postdata = null;
//						apic.recycle();
//					} else {
//						handler.sendEmptyMessage(-1);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					handler.sendEmptyMessage(-1);
//				}
//			}
//		};
//		a.start();
//		a = null;
//	}

	/**
	 * 上传图片不压缩
	 * 
	 * @param fileId
	 * @param aContext
	 * @param fileName
	 *            文件名
	 * @param apic
	 *            图片
	 * @param acallBack
	 */
	public static void uploadHeadImg(final Context aContext, final int fileId, final String cmd, final String fileName,
			final Bitmap apic, final Handler handler) {
		// ProgressDialog.getInstance().createLoadingDialog(aContext, false);
		Thread a = new Thread() {
			@Override
			public void run() {
				super.run();
				// String uploadImgUrl = ApiConfig.IMAGE_URL + cmd;//
				// ApiConfig.UPLOAD_WANTOBUY_IMG
				String uploadImgUrl = ApiConfig.IMAGE_URL_HEAD;
				AppLog.redLog(TAG, uploadImgUrl);
				try {
					if (HttpUtils.isNetworkConnected(aContext)) {
						// byte[] bytePic = BitmapCompression(apic, false);
						byte[] bytePic = headIv2Bytes(apic, false);
						byte[] header = HttpUtils.GetPostHead();
						byte[] footer = HttpUtils.GetPostEnd();
						byte[] picdata = HttpUtils.GetPostFileContent("urlfile", fileId, fileName,
								HttpUtils.GetPostContentType(), bytePic);
						int postdatalength = header.length + footer.length + picdata.length;
						byte[] postdata = new byte[postdatalength];
						int offset = 0;

						System.arraycopy(header, 0, postdata, offset, header.length);
						offset += header.length;

						System.arraycopy(picdata, 0, postdata, offset, picdata.length);
						offset += picdata.length;

						System.arraycopy(footer, 0, postdata, offset, footer.length);
						offset += footer.length;
						header = null;
						footer = null;
						picdata = null;
						postData(aContext, uploadImgUrl, postdata, handler, -1, fileId);
						postdata = null;
						apic.recycle();
					} else {
						handler.sendEmptyMessage(-1);
					}
				} catch (Exception e) {
					e.printStackTrace();
					handler.sendEmptyMessage(0);
				}
			}
		};
		a.start();
		a = null;
	}

	/**
	 * 上传文件
	 * 
	 * @param fileId
	 * @param aContext
	 * @param fileName
	 *            文件名
	 * @param apic
	 *            图片
	 * @param acallBack
	 */
	public static void uploadFile(final Context aContext, final int fileId, final String cmd, final String fileName,
			final byte[] bytes, final Handler handler) {
		// ProgressDialog.getInstance().createLoadingDialog(aContext, false);
		Thread a = new Thread() {
			@Override
			public void run() {
				super.run();
				String uploadImgUrl = ApiConfig.IMAGE_URL + cmd;// ApiConfig.UPLOAD_WANTOBUY_IMG
				AppLog.redLog(TAG, uploadImgUrl);
				try {
					if (HttpUtils.isNetworkConnected(aContext)) {
						byte[] header = HttpUtils.GetPostHead();
						byte[] footer = HttpUtils.GetPostEnd();
						byte[] picdata = HttpUtils.GetPostFileContent("urlfile", fileName,
								HttpUtils.GetPostContentType(), bytes);
						int postdatalength = header.length + footer.length + picdata.length;
						byte[] postdata = new byte[postdatalength];
						int offset = 0;

						System.arraycopy(header, 0, postdata, offset, header.length);
						offset += header.length;

						System.arraycopy(picdata, 0, postdata, offset, picdata.length);
						offset += picdata.length;

						System.arraycopy(footer, 0, postdata, offset, footer.length);
						offset += footer.length;
						header = null;
						footer = null;
						picdata = null;
						postData(aContext, uploadImgUrl, postdata, handler, -1, 0);
						postdata = null;
					} else {
						handler.sendEmptyMessage(-1);
					}
				} catch (Exception e) {
					e.printStackTrace();
					handler.sendEmptyMessage(0);
				}
			}
		};
		a.start();
		a = null;
	}

	/**
	 * 上传图片
	 * 
	 * @param aContext
	 * @param fileName
	 *            文件名
	 * @param apic
	 *            图片
	 * @param acallBack
	 */
	public static void uploadImg(final Context aContext, final String cmd, final String fileName, final Bitmap apic,
			final Handler handler) {
		// ProgressDialog.getInstance().createLoadingDialog(aContext, false);
		Thread a = new Thread() {
			@Override
			public void run() {
				super.run();
				String uploadImgUrl = ApiConfig.IMAGE_URL_HEAD + cmd;// ApiConfig.UPLOAD_WANTOBUY_IMG
				AppLog.redLog("<----图片上传-->", uploadImgUrl);
				try {
					if (HttpUtils.isNetworkConnected(aContext)) {

						byte[] bytePic = Bitmap2Bytes(apic, true);
						byte[] header = HttpUtils.GetPostHead();
						byte[] footer = HttpUtils.GetPostEnd();
						byte[] picdata = HttpUtils.GetPostFileContent("urlfile", fileName,
								HttpUtils.GetPostContentType(), bytePic);
						int postdatalength = header.length + footer.length + picdata.length;
						byte[] postdata = new byte[postdatalength];
						int offset = 0;

						System.arraycopy(header, 0, postdata, offset, header.length);
						offset += header.length;

						System.arraycopy(picdata, 0, postdata, offset, picdata.length);
						offset += picdata.length;

						System.arraycopy(footer, 0, postdata, offset, footer.length);
						offset += footer.length;
						header = null;
						footer = null;
						picdata = null;
						postData(aContext, uploadImgUrl, postdata, handler, -1, 0);
						postdata = null;
						apic.recycle();
					} else {
						handler.sendEmptyMessage(-1);
					}
				} catch (Exception e) {
					handler.sendEmptyMessage(0);
					e.printStackTrace();
				}
			}
		};
		a.start();
		a = null;
	}

	/**
	 * 上传文件
	 * 
	 * @param aContext
	 * @param fileName
	 *            文件名
	 * @param apic
	 *            图片
	 * @param acallBack
	 */
	public static void uploadFile(final Context aContext, final String cmd, final String fileName, final byte[] bytes,
			final long folderId, final String projectId, final Handler handler, final int i) {
		Thread a = new Thread() {
			@Override
			public void run() {
				super.run();
				String uploadImgUrl = ApiConfig.IMAGE_URL + cmd + "?folderId=" + folderId + "&projectId=" + projectId
						+ "&accountId=" + AccountConfig.getAccountId();// ApiConfig.UPLOAD_WANTOBUY_IMG
				try {
					if (HttpUtils.isNetworkConnected(aContext)) {

						byte[] header = HttpUtils.GetPostHead();
						byte[] footer = HttpUtils.GetPostEnd();
						byte[] picdata = HttpUtils.GetPostFileContent("urlfile", fileName,
								HttpUtils.GetPostContentType(), bytes);
						int postdatalength = header.length + footer.length + picdata.length;
						byte[] postdata = new byte[postdatalength];
						int offset = 0;

						System.arraycopy(header, 0, postdata, offset, header.length);
						offset += header.length;

						System.arraycopy(picdata, 0, postdata, offset, picdata.length);
						offset += picdata.length;

						System.arraycopy(footer, 0, postdata, offset, footer.length);
						offset += footer.length;
						header = null;
						footer = null;
						picdata = null;
						postData(aContext, uploadImgUrl, postdata, handler, -1, i);
						postdata = null;
					} else {
						handler.sendEmptyMessage(-1);
					}
				} catch (Exception e) {
					handler.sendEmptyMessage(0);
					e.printStackTrace();
				}
			}
		};
		a.start();
		a = null;
	}

	private static boolean postData(Context aContext, String aUrl, byte[] aData, Handler aHandler, int mark, int fileId)

	{
		boolean ret = true;
		byte[] response;
		try {
			response = HttpUtils.GetDataFromUrlByPostData(aUrl, aData, HttpUtils.GetPostContentType());
			if (response != null) {
				String responsestr = new String(response);
				AppLog.redLog("myTest", responsestr);
				Message msg = new Message();
				JSONObject responsejson = new JSONObject(responsestr);

				@SuppressWarnings("unused")
				String code = HttpUtils.getJSONString(responsejson, "code");

				Gson gson = new Gson();

				PictureEntity picture = gson.fromJson(responsestr, PictureEntity.class);

				msg.arg1 = fileId;
				msg.what = picture.getCode();
				msg.obj = picture;
				aHandler.sendMessage(msg);
			} else {
				Message msg = new Message();
				msg.what = -1;
				aHandler.sendMessage(msg);
			}
		} catch (Exception e) {
			aHandler.sendEmptyMessage(-1);
			e.printStackTrace();

		}

		return ret;
	}

	public static byte[] Bitmap2Bytes(Bitmap bm, final boolean needRecycle) {
		if (bm != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);// Bitmap.CompressFormat.PNG
			int size = baos.toByteArray().length / 1024;
			// int scale = 100;
			AppLog.redLog("Bitmap2Bytes", "size" + size);
			if (size > 200) {
				// 判断如果压缩后图片是否大于100kb,大于继续压缩
				int scale = (200 * 100) / size;
				baos.reset();// 重置baos即清空baos
				AppLog.redLog("Bitmap2Bytes", "scale" + scale);
				bm.compress(Bitmap.CompressFormat.JPEG, scale, baos);// //
																		// 这里压缩options%，把压缩后的数据存放到baos中
			}

			if (needRecycle) {
				bm.recycle();
				System.gc();
			}
			byte[] result = baos.toByteArray();
			int length = result.length;
			AppLog.redLog("Bitmap2Bytes", "result.length" + length);
			try {
				baos.flush();
				baos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		} else {
			return null;
		}

	}

	/**
	 * 不压缩转换成byte
	 * 
	 * @param bm
	 * @param needRecycle
	 * @return
	 */
	public static byte[] headIv2Bytes(Bitmap bm, final boolean needRecycle) {
		if (bm != null) {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);// Bitmap.CompressFormat.PNG
			if (needRecycle) {
				bm.recycle();
			}

			byte[] result = baos.toByteArray();
			int length = result.length;
			AppLog.redLog("Bitmap2Bytes", "result.length" + length);
			try {
				baos.flush();
				baos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		} else {
			return null;
		}

	}

	/**
	 * 等比例压缩
	 * 
	 * @param image
	 * @param needRecycle
	 * @return
	 */
	@SuppressWarnings("unused")
	private static byte[] BitmapCompression(Bitmap image, final boolean needRecycle) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 1280f;// 这里设置高度为800f
		float ww = 720f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据高度度固定大小缩放
			be = (int) (w / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (h / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return Bitmap2Bytes(bitmap, needRecycle);// 压缩好比例大小后再进行质量压缩
	}

	/**
	 * 把一个文件转化为字节
	 * 
	 * @param file
	 * @return byte[]
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static byte[] file2Byte(File file) throws Exception {
		byte[] bytes = null;
		if (file != null) {
			InputStream is = new FileInputStream(file);
			int length = (int) file.length();
			if (length > Integer.MAX_VALUE) // 当文件的长度超过了int的最大值
			{
				System.out.println("this file is max ");
				return null;
			}
			bytes = new byte[length];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			// 如果得到的字节长度和file实际的长度不一致就可能出错了
			if (offset < bytes.length) {
				System.out.println("file length is error");
				return null;
			}
			is.close();
		}
		return bytes;
	}

	/**
	 * 保存图片
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

	/**
	 * 压缩图片
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {

		int w = bitmap.getWidth();

		int h = bitmap.getHeight();

		Matrix matrix = new Matrix();

		float scaleWidth = ((float) width / w);

		float scaleHeight = ((float) height / h);

		matrix.postScale(scaleWidth, scaleHeight);// 利用矩阵进行缩放不会造成内存溢出

		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);

		return newbmp;

	}

	/**
	 * 闪购活动上传商品上传图片用的
	 * <p>
	 * Title: postData2<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param aContext
	 * @param aUrl
	 * @param aData
	 * @param aHandler
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static boolean postData2(Context aContext, String aUrl, byte[] aData, Handler aHandler) throws Exception {
		boolean ret = true;
		byte[] response = HttpUtils.GetDataFromUrlByPostData(aUrl, aData, HttpUtils.GetPostContentType());
		String responsestr = new String(response);
		AppLog.redLog("myTest", responsestr);
		if (!TextUtils.isEmpty(responsestr)) {
			JSONObject responsejson = new JSONObject(responsestr);
			// int code = responsejson.getInt("code");
			String m = HttpUtils.getJSONString(responsejson, "msg");
			// GoodsPictureEntity picture = new GoodsPictureEntity();
			// picture.id = Long.parseLong(HttpUtils.getJSONString(responsejson,
			// "id"));
			// picture.createUser = HttpUtils.getJSONString(responsejson,
			// "createUser");
			// picture.createdIp = HttpUtils.getJSONString(responsejson,
			// "createdIp");
			// picture.name = HttpUtils.getJSONString(responsejson, "name");
			// picture.address = HttpUtils.getJSONString(responsejson,
			// "address");
			Message msg = new Message();
			msg.what = 1;
			msg.obj = m;
			aHandler.sendMessage(msg);
			// ProgressDialog.getInstance().dismissDialog();
		}
		return ret;
	}

	public static void uploadNoteImg(final Context aContext, final int fileId, final String cmd, final String fileName,
			final Bitmap apic, final Handler handler) {
		// ProgressDialog.getInstance().createLoadingDialog(aContext, false);
		Thread a = new Thread() {
			@Override
			public void run() {
				super.run();
				String uploadImgUrl = ApiConfig.IMAGE_URL + cmd;// ApiConfig.UPLOAD_WANTOBUY_IMG
				AppLog.redLog(TAG, uploadImgUrl);
				try {
					if (HttpUtils.isNetworkConnected(aContext)) {
						System.out.println(apic.isRecycled() + "   ////////////////");
						// byte[] bytePic = Bitmap2Bytes(apic, false);
						byte[] bytePic = Bitmap2Bytes(apic, true);
						byte[] header = HttpUtils.GetPostHead();
						byte[] footer = HttpUtils.GetPostEnd();
						byte[] picdata = HttpUtils.GetPostFileContent("urlfile", fileId, fileName,
								HttpUtils.GetPostContentType(), bytePic);
						int postdatalength = header.length + footer.length + picdata.length;
						byte[] postdata = new byte[postdatalength];
						int offset = 0;

						System.arraycopy(header, 0, postdata, offset, header.length);
						offset += header.length;

						System.arraycopy(picdata, 0, postdata, offset, picdata.length);
						offset += picdata.length;

						System.arraycopy(footer, 0, postdata, offset, footer.length);
						offset += footer.length;
						header = null;
						footer = null;
						picdata = null;
						postData3(aContext, uploadImgUrl, postdata, handler, -1, fileId);
						postdata = null;
						apic.recycle();
					} else {
						handler.sendEmptyMessage(-1);
					}
				} catch (Exception e) {
					e.printStackTrace();
					handler.sendEmptyMessage(0);
				}
			}
		};
		a.start();
		a = null;
	}

	private static boolean postData3(Context aContext, String aUrl, byte[] aData, Handler aHandler, int mark,
			int fileIdaaa) throws Exception {
		boolean ret = true;
		byte[] response = HttpUtils.GetDataFromUrlByPostData(aUrl, aData, HttpUtils.GetPostContentType());
		String responsestr = new String(response);
		AppLog.redLog("myTest", responsestr);
		JSONObject responsejson = new JSONObject(responsestr);
		JSONObject content = HttpUtils.getJSONObject(responsejson, "content");
		@SuppressWarnings("unused")
		String field = HttpUtils.getJSONString(content, "field");
		@SuppressWarnings("unused")
		JSONObject noteAttachment = HttpUtils.getJSONObject(content, "noteAttachment");
		// ImgEntity picture = new ImgEntity();
		// picture.id = HttpUtils.getJSONString(noteAttachment, "id");
		// picture.fileId = field;
		// picture.msg = HttpUtils.getJSONString(noteAttachment,
		// "imgDescriber");
		// picture.name = HttpUtils.getJSONString(noteAttachment, "imgName");
		// picture.address = HttpUtils.getJSONString(noteAttachment, "imgUrl");
		Message msg = new Message();
		// msg.what = fileIdaaa;
		// msg.arg1 = mark;
		// msg.obj = picture;
		aHandler.sendMessage(msg);
		return ret;
	}

}
