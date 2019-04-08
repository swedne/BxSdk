//package com.zorasun.fangchanzhichuang.section.dialog;
//
//import com.zorasun.fangchanzhichuang.R;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.TextView;
//
///**
// * @author 唐惠梅
// * @e-mail 443237034@qq.com
// * @version v1.0
// * @copyright 2010-2015
// * @create-time 2015年2月13日 下午4:20:58
// */
//public class DialogExit implements OnClickListener
//{
//
//	private Dialog dialog;
//	private Context context;
//	private DialogCallBack mCallBack;
//
//	public Dialog showDialog(Context context)
//	{
//		this.context = context;
//		dialog = new Dialog(context, R.style.custom_dialog);
//		dialog.setContentView(R.layout.dialog_text_one);
//		dialog.findViewById(R.id.btn_other_cancel).setOnClickListener(this);
//		dialog.findViewById(R.id.btn_other_ok).setOnClickListener(this);
//		((TextView) dialog.findViewById(R.id.tv_other_text)).setText(R.string.confirm_exit);
//		dialog.show();
//		return dialog;
//	}
//
//	public void setCallBack(DialogExit.DialogCallBack dialogCallBack)
//	{
//		this.mCallBack = dialogCallBack;
//	}
//
//	@Override
//	public void onClick(View v)
//	{
//		// TODO Auto-generated method stub
//		switch (v.getId())
//		{
//			case R.id.btn_other_cancel:
//				dialog.cancel();
//				break;
//			case R.id.btn_other_ok:
//
//				if (mCallBack != null)
//				{
//					mCallBack.handle();
//				}
//
//				dialog.dismiss();
//
//				break;
//
//		}
//	}
//
//	public interface DialogCallBack
//	{
//		public void handle();
//	}
//}
