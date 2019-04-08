package com.zorasun.fangchanzhichuang.general.dialog;

import com.zorasun.fangchanzhichuang.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 通用dialog
 */
public class GeneralDialog
{
	Dialog dialog;
	private Button sure,cancel;
	private View line;
	private EditText et;

	public Dialog showDialog(Activity context)
	{
		dialog = new Dialog(context, R.style.general_dialog);
		dialog.setContentView(R.layout.dialog_delete_prompt);
		((TextView) dialog.findViewById(R.id.dwp_txt_content)).setText("确定要删除该图片吗？");
		dialog.show();
		sure = (Button) dialog.findViewById(R.id.dwp_btn_queren);
		cancel = (Button) dialog.findViewById(R.id.dwp_btn_quxiao);
		line = dialog.findViewById(R.id.line);
		cancel.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				dialog.dismiss();
			}
		});
		return dialog;
	}

	/**
	 * @param context
	 * @param details
	 * @return 内容
	 */
	public Dialog showDialog(Context context, String details) {
		dialog = new Dialog(context, R.style.general_dialog);
		dialog.setContentView(R.layout.dialog_delete_prompt);
		((TextView) dialog.findViewById(R.id.dwp_txt_content)).setText(details);
		line = dialog.findViewById(R.id.line);
		sure = (Button) dialog.findViewById(R.id.dwp_btn_queren);
		cancel = (Button) dialog.findViewById(R.id.dwp_btn_quxiao);
		cancel.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				dialog.dismiss();
			}
		});
		dialog.show();
		return dialog;
	}

	/**
	 * 使用edittext，隐藏textView
	 * 
	 * @param context
	 * @param details
	 * @return
	 */

	public Dialog showDialogWithEt(Activity context, String etHint)
	{
		dialog = new Dialog(context, R.style.general_dialog);
		dialog.setContentView(R.layout.dialog_delete_prompt);
		((TextView) dialog.findViewById(R.id.dwp_txt_content)).setVisibility(View.GONE);;
		et = (EditText) dialog.findViewById(R.id.dwp_et_content);
		et.setHint(etHint);
		et.setVisibility(View.VISIBLE);
		line = dialog.findViewById(R.id.line);
		sure = (Button) dialog.findViewById(R.id.dwp_btn_queren);
		cancel = (Button) dialog.findViewById(R.id.dwp_btn_quxiao);
		cancel.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				dialog.dismiss();
			}
		});
		dialog.show();
		return dialog;
	}

	/**
	 * @param context
	 * @param title
	 *            标题
	 * @param details
	 *            内容
	 * @return
	 */
	public Dialog showDialog(Activity context, String title, String details)
	{
		dialog = new Dialog(context, R.style.general_dialog);
		dialog.setContentView(R.layout.dialog_delete_prompt);
		line = dialog.findViewById(R.id.line);
		((TextView) dialog.findViewById(R.id.dwp_txt_title)).setText(title);
		((TextView) dialog.findViewById(R.id.dwp_txt_content)).setText(details);
		sure = (Button) dialog.findViewById(R.id.dwp_btn_queren);
		cancel = (Button) dialog.findViewById(R.id.dwp_btn_quxiao);
		cancel.setOnClickListener(new OnClickListener()
		{
 			@Override
			public void onClick(View v)
			{
				dialog.dismiss();
			}
		});
		dialog.show();
		return dialog;
	}

	public void setOneButton(String str)
	{
		cancel.setText(str);
		sure.setVisibility(View.GONE);
		line.setVisibility(View.VISIBLE);
		cancel.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				dialog.dismiss();
			}
		});
	}

	/**
	 * 确认按钮内容
	 * 
	 * @param str
	 */
	public void setSureText(String str)
	{
		sure.setText(str);
	}

	/**
	 * 取消按钮
	 * 
	 * @param str
	 */
	public void setCancelText(String str)
	{
		cancel.setText(str);
	}

	/**
	 * 确认键监听
	 * 
	 * @param l
	 */
	public void sure(OnClickListener l)
	{
		sure.setOnClickListener(l);
	}

	/**
	 * 返回edittext中内容
	 * 
	 * @param l
	 * @return
	 */
	public String getEt()
	{
		return et.getText().toString();
	}

	/**
	 * 取消键监听
	 * 
	 * @param l
	 */
	public void cancel(OnClickListener l)
	{
		cancel.setOnClickListener(l);
	}

	/**
	 * 关闭dialog
	 */
	public void dismiss()
	{
		dialog.dismiss();
	}

	public void setCancel(boolean bo)
	{
		dialog.setCancelable(bo);
	}

	public void setTitleVisible(int visibility)
	{
		((TextView) dialog.findViewById(R.id.dwp_txt_title)).setVisibility(visibility);
	}
}
