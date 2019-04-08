package com.zorasun.fangchanzhichuang.general.widget.imageselect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

/**
 * 文件操作类 Created by Nereo on 2015/4/8.
 */
public class FileUtils
{

	public static File createTmpFile(Context context)
	{

		String state = Environment.getExternalStorageState();
		System.out.println("state--------->" + state);
		if (state.equals(Environment.MEDIA_MOUNTED))
		{
			// 已挂载
			File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			System.out.println("pic------------>" + pic);
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
			String fileName = "multi_image_" + timeStamp + "";
			File tmpFile = new File(pic, fileName + ".jpg");
			System.out.println("tempFile------------>" + tmpFile);
			return tmpFile;
		}
		else
		{
			File cacheDir = context.getCacheDir();
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
			String fileName = "multi_image_" + timeStamp + "";
			File tmpFile = new File(cacheDir, fileName + ".jpg");
			return tmpFile;
		}

	}

	public static String SDPATH = Environment.getExternalStorageDirectory() + "/Photo_LJ/";

	public static void saveBitmap(Bitmap bm, String picName)
	{
		try
		{
			if (!isFileExist(""))
			{
				@SuppressWarnings("unused")
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG");
			if (f.exists())
			{
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) throws IOException
	{
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName)
	{
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}

	public static void delFile(String fileName)
	{
		File file = new File(SDPATH + fileName);
		if (file.isFile())
		{
			file.delete();
		}
		file.exists();
	}

	public static void deleteDir()
	{
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles())
		{
			if (file.isFile())
				file.delete();
			else if (file.isDirectory())
				deleteDir();
		}
		dir.delete();
	}

	public static boolean fileIsExists(String path)
	{
		try
		{
			File f = new File(path);
			if (!f.exists())
			{
				return false;
			}
		}
		catch (Exception e)
		{

			return false;
		}
		return true;
	}

}
