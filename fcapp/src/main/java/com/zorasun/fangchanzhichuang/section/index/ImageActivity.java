package com.zorasun.fangchanzhichuang.section.index;

import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.ScreenUtils;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageActivity extends BaseActivity implements OnClickListener {

	private String imageUrl;
	private String imageName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		imageUrl = getIntent().getStringExtra("imageUrl");
		imageName = getIntent().getStringExtra("imageName");
		initView();
	}

	private void initView() {
		TextView tvName = (TextView) findViewById(R.id.title_name);
		if (!TextUtils.isEmpty(imageName)) {
			tvName.setText(imageName);
		}
		ImageView imgPic = (ImageView) findViewById(R.id.img_res);

		AsyncImageLoader.setAsynImages(imgPic, imageUrl);
		findViewById(R.id.title_left).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			super.onBackPressed();
			break;
		default:
			break;
		}
	}
}
