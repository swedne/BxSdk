package com.zorasun.fangchanzhichuang.general.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;

import android.graphics.Bitmap;
import android.os.Environment;

/**
 * SD卡工具类
 * 
 * @author 魏少冬
 * @e-mail shaodong.wei@heysroad.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2014-11-10 上午09:38:47
 */
public class SdCardTool
{

	private static final String TAG = "SdCardTool";

	private SdCardTool ()
	{
	}

	/**
	 * sdcard is exists call this method before working on the sdcard
	 */
	@Deprecated
	public static boolean exists()
	{
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	/**
	 * sdcard is exists call this method before working on the sdcard
	 */
	public static boolean isMounted()
	{
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	/**
	 * copy file
	 */
	public static File copy(File file, String dir, String fileName)
	{
		InputStream in = null;
		try
		{
			in = new FileInputStream(file);
			return write(in, dir, fileName);
		}
		catch (FileNotFoundException e)
		{
			AppLog.redLog(TAG, "FileNotFoundException error message:" + e.getMessage());
			StreamUtil.closeSilently(in);
		}
		return null;
	}

	public static File write(final InputStream in, final String dir, final String fileName)
	{
		if (in == null)
			return null;

		String absolutePath = dir;

		File f = new File(absolutePath);
		if (!f.exists())
		{
			if (!f.mkdirs())
			{
				AppLog.redLog(TAG, "mkdirs error:" + absolutePath);
			}
		}

		File mf = new File(absolutePath + "/" + fileName);
		FileOutputStream out = null;
		try
		{
			out = new FileOutputStream(mf);

			byte bt[] = new byte[512];
			int n = -1;
			while (true)
			{
				n = in.read(bt);
				if (n <= 0)
					break;
				out.write(bt, 0, n);
			}
			in.close();
			out.close();
		}
		catch (FileNotFoundException e)
		{
			AppLog.redLog(TAG, "FileNotFoundException error message:" + e.getMessage());
		}
		catch (IOException e)
		{
			AppLog.redLog(TAG, "IOException error desc:" + e.getMessage());
		}
		finally
		{
			absolutePath = null;
			StreamUtil.closeSilently(out);
			StreamUtil.closeSilently(in);
		}
		return mf;
	}

	/**
	 * save bitmap to the sdcard dir "/mnt/sdcard/temp/" fileName "20111020163433.jpg"
	 */
	public static File save(final Bitmap bitmap, String dir, String fileName)
	{
		if (bitmap == null)
			return null;

		String absolutePath = dir;
		AppLog.greenLog(TAG, "absolutePath " + absolutePath);
		AppLog.greenLog(TAG, "fileName " + fileName);

		File f = new File(absolutePath);
		if (!f.exists())
		{
			if (!f.mkdirs())
			{
				AppLog.redLog(TAG, "mkdirs error:" + absolutePath);
			}
		}

		File mf = new File(absolutePath + "/" + fileName);
		OutputStream outputStream = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out);
		byte[] jpegData = out.toByteArray();

		try
		{
			outputStream = new FileOutputStream(mf);
			outputStream.write(jpegData);
		}
		catch (FileNotFoundException e)
		{
			AppLog.redLog(TAG, "FileNotFoundException error message:" + e.getMessage());
		}
		catch (IOException e)
		{
			AppLog.redLog(TAG, "IOException error desc:" + e.getMessage());
		}
		finally
		{
			StreamUtil.closeSilently(outputStream);
		}
		return mf;
	}

	/**
	 * sdcard root directory
	 */
	public static String getSdcardDir()
	{
		return Environment.getExternalStorageDirectory().toString();
	}

	/**
	 * delete all files on the path
	 * 
	 * @param path
	 */
	public static void deleteAll(String path)
	{
		File file = new File(path);
		if (file.exists())
		{
			if (file.isDirectory())
			{
				File[] files = file.listFiles();
				for (File f : files)
				{
					f.delete();
				}
			}
			else
			{
				file.delete();
			}
		}
	}

}
