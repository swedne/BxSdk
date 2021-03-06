package com.zorasun.fangchanzhichuang.general.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

/**
 * 弹窗工具类
 */
public class PopupWindowUtil {
	public static PopupWindow getPopupWindow(Context context, View view) {
		PopupWindow popWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		popWindow.setTouchable(true);
		popWindow.setOutsideTouchable(true);
		popWindow.setFocusable(true);
		ColorDrawable dw = new ColorDrawable(-00000);
		popWindow.setBackgroundDrawable(dw);
		popWindow.setContentView(view);
		return popWindow;
	}
}
