package com.zorasun.fangchanzhichuang.general.util;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

/**
 * 打开文件
 * 
 * @author zhouyujing
 * @e-mail 1032668839@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月22日 下午4:15:06
 */
public class OpenFileUtil
{

	String mSaveSoftwarePath;

	protected static final String TAG = "OpenFileUtil";
	public static String strpath = Environment.getExternalStorageDirectory() + "/igene/";
	public static String strfile = Environment.getExternalStorageDirectory() + "/igene/video/";
	static OpenFileUtil mInstance = null;
	private Context mContext;

	public static OpenFileUtil getInstance(Context mContext)
	{
		if (mInstance == null)
		{
			mInstance = new OpenFileUtil(mContext);
		}
		return mInstance;
	}

	public OpenFileUtil (Context mContext)
	{
		this.mContext = mContext;
	}

	public final String[][] MIME_MapTable = {
			// {后缀名，MIME类型}
			{ ".3gp", "video/3gpp" }, { ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" }, { ".avi", "video/x-msvideo" }, { ".bin", "application/octet-stream" },
			{ ".bmp", "image/bmp" }, { ".c", "text/plain" }, { ".class", "application/octet-stream" },
			{ ".conf", "text/plain" }, { ".cpp", "text/plain" }, { ".doc", "application/msword" },
			{ ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ ".xls", "application/vnd.ms-excel" },
			{ ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ ".exe", "application/octet-stream" }, { ".gif", "image/gif" }, { ".gtar", "application/x-gtar" },
			{ ".gz", "application/x-gzip" }, { ".h", "text/plain" }, { ".htm", "text/html" }, { ".html", "text/html" },
			{ ".jar", "application/java-archive" }, { ".java", "text/plain" }, { ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" }, { ".js", "application/x-javascript" }, { ".log", "text/plain" },
			{ ".m3u", "audio/x-mpegurl" }, { ".m4a", "audio/mp4a-latm" }, { ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" }, { ".m4u", "video/vnd.mpegurl" }, { ".m4v", "video/x-m4v" },
			{ ".mov", "video/quicktime" }, { ".mp2", "audio/x-mpeg" }, { ".mp3", "audio/x-mpeg" },
			{ ".mp4", "video/mp4" }, { ".mpc", "application/vnd.mpohun.certificate" }, { ".mpe", "video/mpeg" },
			{ ".mpeg", "video/mpeg" }, { ".mpg", "video/mpeg" }, { ".mpg4", "video/mp4" }, { ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" }, { ".ogg", "audio/ogg" }, { ".pdf", "application/pdf" },
			{ ".png", "image/png" }, { ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ ".prop", "text/plain" }, { ".rc", "text/plain" }, { ".rmvb", "audio/x-pn-realaudio" },
			{ ".rtf", "application/rtf" }, { ".sh", "text/plain" }, { ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" }, { ".wav", "audio/x-wav" },
			{ ".wma", "audio/x-ms-wma" }, { ".wmv", "audio/x-ms-wmv" }, { ".wps", "application/vnd.ms-works" },
			{ ".xml", "text/plain" }, { ".z", "application/x-compress" }, { ".zip", "application/x-zip-compressed" },
			{ "", "*/*" } };

	/**
	 * 判断文件是否存在
	 */
	public boolean checkFoldeIsExit(String fileName)
	{
		boolean isExit = false;
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))//判断存在sdcard
		{
			getPath();
			// mSaveSoftwarePath = Environment.getExternalStorageDirectory() + "/downloadfile.bin";
			mSaveSoftwarePath = strfile + fileName;
			File file = new File(mSaveSoftwarePath);
			if (file.exists())
			{
				isExit = true;
			}
			else
			{
				isExit = false;
			}
		}

		return isExit;
	}
	
	/**
	 * 判断文件夹是否存在
	 */
	public void getPath()
	{
		File ft = new File(strpath);
		File ff = new File(strfile);
		if (!ft.exists())//文件夹是否存在
		{
			ft.mkdir();
		}
		if (!ff.exists())//文件夹是否存在
		{
			ff.mkdir();
		}
	}

	/**
	 * 打开文件
	 * 
	 * @param url
	 */
	public void openFile(String url)
	{
		if (url.indexOf(".jpg") != -1 || url.indexOf(".png") != -1 || url.indexOf(".jpeg") != -1
				|| url.indexOf(".JPEG") != -1 || url.indexOf(".PNG") != -1 || url.indexOf(".JPG") != -1)
		{
			Intent intent = new Intent(Intent.ACTION_VIEW);
			// Uri mUri = Uri.parse("file://" + picFile.getPath());Android3.0以后最好不要通过该方法，存在一些小Bug
			intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
			mContext.startActivity(intent);
		}
		else
		{
			File file = new File(mSaveSoftwarePath);
			Intent intent = new Intent();
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// 设置intent的Action属性
			intent.setAction(Intent.ACTION_VIEW);
			// 获取文件file的MIME类型
			String type = getMIMEType(file);
			// 设置intent的data和Type属性。
			intent.setDataAndType(/* uri */Uri.fromFile(file), type);
			// 跳转
			mContext.startActivity(intent); // 这里最好try一下，有可能会报错。 //比如说你的MIME类型是打开邮箱，但是你手机里面没装邮箱客户端，就会报错。
		}
	}

	/**
	 * 根据文件后缀名获得对应的MIME类型。
	 * 
	 * @param file
	 */
	@SuppressLint("DefaultLocale")
	public String getMIMEType(File file)
	{

		String type = "*/*";
		String fName = file.getName();
		// 获取后缀名前的分隔符"."在fName中的位置。
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0)
		{
			return type;
		}
		/* 获取文件的后缀名 */
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		// 在MIME和文件类型的匹配表中找到对应的MIME类型。
		for (int i = 0; i < MIME_MapTable.length; i++)
		{ // MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}
}

