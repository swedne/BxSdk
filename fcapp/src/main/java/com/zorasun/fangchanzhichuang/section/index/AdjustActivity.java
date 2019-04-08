package com.zorasun.fangchanzhichuang.section.index;

import java.util.Arrays;
import java.util.List;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.commonadapter.CommonAdapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.ViewHolder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class AdjustActivity extends BaseActivity implements OnClickListener
{

	private ListView mListView;
	private List<String> mDatas;
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adjust);
		initData();
		initListView();
	}

	private void initListView()
	{
		mListView = (ListView) findViewById(R.id.listView);
		mListView.setAdapter(new CommonAdapter<String>(this, mDatas, android.R.layout.simple_list_item_1)
		{

			@Override
			public void convert(ViewHolder helper, String item, int position)
			{
				helper.setText(android.R.id.text1, item + "");
				helper.setTextColor(android.R.id.text1, Color.BLACK);
				((TextView) helper.getView(android.R.id.text1)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			}

		});

		mListView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent data = new Intent();
				data.putExtra("adjust", mDatas.get(position));
				setResult(RESULT_OK, data);
				finish();
			}
		});
	}

	private void initData()
	{
		// 跳转的类型
		type = getIntent().getIntExtra("type", -1);
		findViewById(R.id.title_left).setOnClickListener(this);
		if (type == 10)
		{
			// 公积金流动性调整基数
			((TextView) findViewById(R.id.title_name)).setText("流动性调整基数");
			mDatas = Arrays.asList("0.6", "0.8", "1.0", "1.2");
		} else if (type == 0)
		{
			// 房产证年限
			((TextView) findViewById(R.id.title_name)).setText("房产证年限");
			mDatas = Arrays.asList("年限<2", "2≤年限<5", "年限≥5");
		} else if (type == 1)
		{
			// 买方家庭购房选项
			((TextView) findViewById(R.id.title_name)).setText("买方家庭购房选项");
			mDatas = Arrays.asList("首套", "第二套", "第三套以上");
		} else if (type == 2)
		{
			// 个人住宅住宅类型
			((TextView) findViewById(R.id.title_name)).setText("住宅类型");
			mDatas = Arrays.asList("商品房", "私宅", "拆迁安置房", "公房");
		} else if (type == 3)
		{
			// 非个人住宅住宅类型
			((TextView) findViewById(R.id.title_name)).setText("住宅类型");
			mDatas = Arrays.asList("写字楼", "商铺", "车库", "车位");
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;

		default:
			break;
		}
	}
}
