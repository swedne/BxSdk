package com.zorasun.fangchanzhichuang.general.widget;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.marco.ImageSizeConfig;
import com.zorasun.fangchanzhichuang.general.util.CommonUtils;
import com.zorasun.fangchanzhichuang.general.widget.photocropper.BasePhotoCropActivity;
import com.zorasun.fangchanzhichuang.general.widget.photocropper.CropHelper;
import com.zorasun.fangchanzhichuang.general.widget.photocropper.CropParams;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 照片选择
 * 
 * @author chenzhifeng
 * @e-mail 731739299@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月14日 下午2:19:56
 *
 */
public class PhotoSelectActivity extends BasePhotoCropActivity implements OnClickListener {

	public static final int REQUEST_CODE = 0x000001;
	public static final String OUTPUT_WIDTH = "output_width";
	public static final String OUTPUT_HEIGHT = "output_height";

	private int outputWidth;
	private int outputHeight;
	public RelativeLayout outter;
	CropParams mCropParams = new CropParams();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_photo);

		initUi();
		// 获取截图比例，默认商品详情的比例
		outputWidth = getIntent().getIntExtra(OUTPUT_WIDTH, ImageSizeConfig.ImageBigW);
		outputHeight = getIntent().getIntExtra(OUTPUT_HEIGHT, ImageSizeConfig.ImageBigH);

		outter.setOnTouchListener(new OnTouchListener() {
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				setResult(RESULT_CANCELED);
				finish();
				return true;
			}
		});
	}

	/**
	 * 初始化布局
	 */
	private void initUi() {
		findViewById(R.id.dialog_photo_takephotos).setOnClickListener(this);
		findViewById(R.id.dialog_photo_benjiphots).setOnClickListener(this);
		findViewById(R.id.dialog_photo_return).setOnClickListener(this);
		outter = (RelativeLayout) findViewById(R.id.outter);
	}

	@Override
	public CropParams getCropParams() {
		mCropParams.aspectX = outputWidth;
		mCropParams.aspectY = outputHeight;
		mCropParams.outputX = outputWidth;
		mCropParams.outputY = outputHeight;
		return mCropParams;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public void onPhotoCropped(Uri uri) {
		Bitmap bitmap = CropHelper.decodeUriAsBitmap(this, mCropParams.uri);
		String root = CommonUtils.getSdcardDir() + "/fcgz/photo/";
		CommonUtils.save(bitmap, root, "tmp");
		setResult(RESULT_OK, getIntent().putExtra("data", bitmap));
		finish();
	}

	@Override
	public void onCropCancel() {
		Toast.makeText(this, "取消截图", Toast.LENGTH_LONG).show();
		finish();
	}

	@Override
	public void onCropFailed(String message) {
		Toast.makeText(this, "截图失败" + "  " + message, Toast.LENGTH_LONG).show();
		finish();
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.dialog_photo_takephotos:
			intent = CropHelper.buildCaptureIntent(getCropParams().uri);
			startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
			break;
		//本机
		case R.id.dialog_photo_benjiphots:
			startActivityForResult(CropHelper.buildCropFromGalleryIntent(getCropParams()), CropHelper.REQUEST_CROP);
			break;
		case R.id.dialog_photo_return:
			finish();
			break;

		default:
			break;
		}
	}

}
