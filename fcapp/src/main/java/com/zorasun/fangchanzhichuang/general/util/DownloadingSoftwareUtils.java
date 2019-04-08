package com.zorasun.fangchanzhichuang.general.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

public class DownloadingSoftwareUtils {
	static DownloadingSoftwareUtils mInstance = null;
	static Activity mContext;
	String mSaveSoftwarePath = null;
	static boolean isStop = false; // 停止标志

	// public static final String filePath = "/zijin_file";

	public static DownloadingSoftwareUtils getInstance() {
		if (mInstance == null) {
			mInstance = new DownloadingSoftwareUtils();
		}
		isStop = false;
		return mInstance;
	}

	public interface DownloadCallBack {
		public void ondownstart();

		public void ondownloading(int percent, String tips);

		public void ondownsucess();

		public void ondownfaile();

		public void ondownstop();
	}

	private String FormetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public void stopDownload() {
		isStop = true;
	}

	public void startDownload(final String url, String fileName, final DownloadCallBack aDownloadCallBack) {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			// mSaveSoftwarePath = Environment.getExternalStorageDirectory() +
			// "/downloadfile.bin";
			mSaveSoftwarePath = OpenFileUtil.strfile + fileName;
			File file = new File(mSaveSoftwarePath);

			if (file.exists()) {
				file.delete();
			}
		} else {
			// Toast.makeText(mContext, R.string.do_update_no_sdcard,
			// Toast.LENGTH_SHORT).show();
			return;
		}

		final Handler mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					if (aDownloadCallBack != null) {
						aDownloadCallBack.ondownstart();
					}
				} else if (msg.what == 1) {
					if (aDownloadCallBack != null) {
						aDownloadCallBack.ondownloading(msg.arg1, (String) msg.obj);
					}
				} else if (msg.what == 2) {
					if (aDownloadCallBack != null) {
						aDownloadCallBack.ondownsucess();
					}
				} else if (msg.what == 3) {
					if (aDownloadCallBack != null) {
						aDownloadCallBack.ondownfaile();
					}
				} else if (msg.what == 4) {
					if (aDownloadCallBack != null) {
						aDownloadCallBack.ondownstop();
					}
				}
			}
		};

		new Thread() {
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpGet get = new HttpGet(url);
					HttpResponse response = client.execute(get);
					HttpEntity entity = response.getEntity();
					long contentlength = entity.getContentLength();
					InputStream is = entity.getContent();
					FileOutputStream fileOutputStream = null;

					mHandler.sendEmptyMessage(0);
					if (is != null) {
						File file = new File(mSaveSoftwarePath + ".tmp");

						if (file.exists()) {
							file.delete();
						}
						int downlength;

						fileOutputStream = new FileOutputStream(file);
						byte[] buf = new byte[1024];
						int ch = -1;
						int count = 0;
						int current_down = 0;
						while ((ch = is.read(buf)) != -1) {
							if (isStop) // 下载停止
							{
								if (file.exists())
									file.delete();
								Message msg = mHandler.obtainMessage();
								msg.what = 4;
								mHandler.sendMessage(msg);

								break;
							}

							// baos.write(buf, 0, ch);
							fileOutputStream.write(buf, 0, ch);
							count += ch;
							int percent = (int) (((float) count / contentlength) * 100);

							downlength = count;
							if (percent - current_down >= 1) {
								current_down = percent;
								Message msg = mHandler.obtainMessage();
								msg.what = 1;
								msg.obj = FormetFileSize(downlength) + "/" + FormetFileSize(contentlength);
								msg.arg1 = percent;
								mHandler.sendMessage(msg);
							}
							if (count == contentlength) {
								boolean rename = file.renameTo(new File(mSaveSoftwarePath));
								mHandler.sendEmptyMessage(rename ? 2 : 3);
							}
						}
					}
					fileOutputStream.flush();
					if (fileOutputStream != null) {
						fileOutputStream.close();
					}
				} catch (Exception e) {
					mHandler.sendEmptyMessage(3);
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * 版本更新
	 * 
	 * @param url
	 * @param aDownloadCallBack
	 */
	public void startDownload(final String url, final DownloadCallBack aDownloadCallBack) {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			mSaveSoftwarePath = Environment.getExternalStorageDirectory() + "/downloadfile.bin";
			File file = new File(mSaveSoftwarePath);

			if (file.exists()) {
				file.delete();
			}
		} else {
			// Toast.makeText(mContext, R.string.do_update_no_sdcard,
			// Toast.LENGTH_SHORT).show();
			return;
		}

		final Handler mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					if (aDownloadCallBack != null) {
						aDownloadCallBack.ondownstart();
					}
				} else if (msg.what == 1) {
					if (aDownloadCallBack != null) {
						aDownloadCallBack.ondownloading(msg.arg1, (String) msg.obj);
					}
				} else if (msg.what == 2) {
					if (aDownloadCallBack != null) {
						aDownloadCallBack.ondownsucess();
					}
				} else if (msg.what == 3) {
					if (aDownloadCallBack != null) {
						aDownloadCallBack.ondownfaile();
					}
				} else if (msg.what == 4) {
					if (aDownloadCallBack != null) {
						aDownloadCallBack.ondownstop();
					}
				}
			}
		};

		new Thread() {
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpGet get = new HttpGet(url);
					// TODO
					HttpResponse response = client.execute(get);
					HttpEntity entity = response.getEntity();
					long contentlength = entity.getContentLength();
					InputStream is = entity.getContent();
					FileOutputStream fileOutputStream = null;

					mHandler.sendEmptyMessage(0);
					if (is != null) {
						File file = new File(mSaveSoftwarePath + ".tmp");

						if (file.exists()) {
							file.delete();
						}
						int downlength;

						fileOutputStream = new FileOutputStream(file);
						byte[] buf = new byte[1024];
						int ch = -1;
						int count = 0;
						int current_down = 0;
						while ((ch = is.read(buf)) != -1) {
							if (isStop) // 下载停止
							{
								if (file.exists())
									file.delete();
								Message msg = mHandler.obtainMessage();
								msg.what = 4;
								mHandler.sendMessage(msg);

								break;
							}

							// baos.write(buf, 0, ch);
							fileOutputStream.write(buf, 0, ch);
							count += ch;
							int percent = (int) (((float) count / contentlength) * 100);

							downlength = count;
							if (percent - current_down >= 1) {
								current_down = percent;
								Message msg = mHandler.obtainMessage();
								msg.what = 1;
								msg.obj = FormetFileSize(downlength) + "/" + FormetFileSize(contentlength);
								msg.arg1 = percent;
								mHandler.sendMessage(msg);
							}
							if (count == contentlength) {
								boolean rename = file.renameTo(new File(mSaveSoftwarePath));
								mHandler.sendEmptyMessage(rename ? 2 : 3);
							}
						}
					}
					fileOutputStream.flush();
					if (fileOutputStream != null) {
						fileOutputStream.close();
					}
				} catch (Exception e) {
					mHandler.sendEmptyMessage(3);
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void openDownloadSoftware(Context aContext) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(new File(mSaveSoftwarePath)), "application/vnd.android.package-archive");
		aContext.startActivity(intent);
	}
}
