package com.zorasun.fangchanzhichuang.section.dialog;

import com.zorasun.fangchanzhichuang.R;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewDialog {

	private Dialog dialog;
	private Context context;
	private ListView lv;
	private String time[];

	public Dialog ShowDialog(Context context, final int isWho) {
		this.context = context;
		if (isWho == 0) {
			addData1();
		} else {
			addData2();
		}

		dialog = new Dialog(context);
		dialog = new Dialog(context, R.style.dialog_basic);
		dialog.setContentView(R.layout.dialog_listview);
		lv = (ListView) dialog.findViewById(R.id.lv);
		ListViewAdapter adapter = new ListViewAdapter();
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (isWho == 0) {
					setOnClickListener.getTime(time[position], position);
				} else {
					setOnClickListener.getChengshu(time[position], position);
				}

				dialog.dismiss();
			}
		});
		dialog.show();
		return dialog;
	}

	SetOnClickListener setOnClickListener;

	public void SetOnClickListener(SetOnClickListener setOnClickListener) {
		this.setOnClickListener = setOnClickListener;
	}

	private void addData1() {
		time = new String[30];
		for (int i = 0; i < 30; i++) {
			String str = i + 1 + "年(" + (i + 1) * 12 + "期)";
			time[i] = str;
		}
	}

	private void addData2() {
		time = new String[8];
		for (int i = 0; i < 8; i++) {
			String str = i + 2 + "成";
			time[i] = str;
		}
	}

	public class ListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return time.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressLint({ "ViewHolder", "InflateParams" })
		@Override
		public View getView(int position, View view, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(context);

			view = inflater.inflate(R.layout.item_textview, null);
			TextView tv = (TextView) view.findViewById(R.id.item_tv);
			tv.setText(time[position]);
			return view;
		}
	}

	public interface SetOnClickListener {
		public void getTime(String time, int position);

		public void getChengshu(String str, int position);
	}

	public void dismiss() {
		dialog.dismiss();
	}
}
