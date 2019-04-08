package com.zorasun.fangchanzhichuang.section.my;

import java.io.File;
import java.io.IOException;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivity;
import com.zorasun.fangchanzhichuang.general.dialog.ProgressDialog;
import com.zorasun.fangchanzhichuang.general.marco.ApiConfig;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.ImageUploadUtils;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.SharedPreferencesUtil;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CircleImageView;
import com.zorasun.fangchanzhichuang.section.account.AccountConfig;
import com.zorasun.fangchanzhichuang.section.account.NiChengActivity;
import com.zorasun.fangchanzhichuang.section.account.XiuGaiMiMaActivity;
import com.zorasun.fangchanzhichuang.section.account.entity.PictureEntity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyDataActivity extends BaseActivity implements OnClickListener {

	private CircleImageView climg_headimage;
	private TextView tvName;
	private TextView tvPhone;
	protected PopupWindow popupwindows;
	private int CAMERA_RESULT = 100;
	private int RESULT_LOAD_IMAGE = 200;
	private File mPhotoFile;
	private String saveDir = Environment.getExternalStorageDirectory().getPath();
	private View allView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mydata);
		if (!AccountConfig.isLogin()) {
			finish();
		}
		inittitle();
		initview();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	

	private void inittitle() {
		TextView title_name = (TextView) findViewById(R.id.title_name);
		title_name.setText("个人信息");
	}

	private void initview() {
		RelativeLayout rl_mychangeimage = (RelativeLayout) findViewById(R.id.rl_mychangeimage);
		allView = findViewById(R.id.rl_layout_all);
		tvName = (TextView) findViewById(R.id.tv_mydata_ninck);
		findViewById(R.id.rl_mydata_ninck).setOnClickListener(this);
		tvName.setText(AccountConfig.getAccountName());
		tvPhone = (TextView) findViewById(R.id.tv_mydata_phone);
		findViewById(R.id.tv_mydata_password).setOnClickListener(this);
		rl_mychangeimage.setOnClickListener(this);
		climg_headimage = (CircleImageView) findViewById(R.id.climg_headimage);
		setAvatar();
		tvPhone.setText(AccountConfig.getAccountPhone());
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void setAvatar() {
		AsyncImageLoader.setAsynAvatarImagesInfo(climg_headimage, AccountConfig.getAccountAvatarul());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 头像选择
		case R.id.rl_mychangeimage:
			// Intent intent = new Intent(MyDataActivity.this,
			// PhotoSelectActivity.class);
			// intent.putExtra(PhotoSelectActivity.OUTPUT_WIDTH,
			// ImageSizeConfig.ImageBigW);
			// intent.putExtra(PhotoSelectActivity.OUTPUT_HEIGHT,
			// ImageSizeConfig.ImageBigH);
			// startActivityForResult(intent, PhotoSelectActivity.REQUEST_CODE);
			showPopWindow(allView);
			break;
		// 修改昵称
		case R.id.rl_mydata_ninck:
			Intent intent1 = new Intent(this, NiChengActivity.class);
			startActivityForResult(intent1, NiChengActivity.NICHENG);
			break;
		// 修改密码
		case R.id.tv_mydata_password:
			Intent intent2 = new Intent(this, XiuGaiMiMaActivity.class);
			startActivityForResult(intent2, XiuGaiMiMaActivity.MODIFYPSW);
			// startActivity(intent2);
			break;
		default:
			break;
		}
	}
	//
	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	//
	// // TODO Auto-generated method stub
	// super.onActivityResult(requestCode, resultCode, data);
	// // if (resultCode == Activity.RESULT_OK && requestCode ==
	// // PhotoSelectActivity.REQUEST_CODE && PhotoSelectActivity.REQUEST_FLAG)
	// if (data != null) {
	// if (resultCode == Activity.RESULT_OK && requestCode ==
	// PhotoSelectActivity.REQUEST_CODE) {
	// // if (resultCode == Activity.RESULT_OK && requestCode ==
	// // PhotoSelectActivity.REQUEST_FLAG) {
	// Bitmap bitmap = data.getParcelableExtra("data");
	// if (bitmap != null) {
	// climg_headimage.setImageBitmap(bitmap);
	// commitAvatar(bitmap);
	// // uploadAvatar(bitmap);
	// }
	// } else if (requestCode == NiChengActivity.NICHENG) {
	// String nickName = data.getStringExtra("nickName");
	// if (!TextUtils.isEmpty(nickName)) {
	// tvName.setText(nickName);
	// }
	// }
	// }
	// if (requestCode == XiuGaiMiMaActivity.MODIFYPSW && resultCode == 0) {
	// } else if (requestCode == XiuGaiMiMaActivity.MODIFYPSW && resultCode ==
	// 1) {
	// finish();
	// }
	//
	// }

	public static String generateModifyAvatarUrl(String uploadImageType) {
		String url = null;
		try {
			if (StringUtils.isEmpty(uploadImageType)) {
				return null;
			}
			url = new StringBuilder().append(ApiConfig.IMAGE_URL_HEAD).append(ApiConfig.UPLOADAVATAR).append("purpose=")
					.append(uploadImageType).toString();
		} catch (Exception e) {
		}
		return url;
	}

	/**
	 * 上传头像
	 * 
	 */
	@SuppressLint("HandlerLeak")
	public void commitAvatar(Bitmap bitmap) {
		ProgressDialog.getInstance().createLoadingDialog(this);
		String fileName = System.currentTimeMillis() + AccountConfig.getAccountPhone() + ".png";

		ImageUploadUtils.uploadImg(this, ApiConfig.UPLOADAVATAR, fileName, bitmap, new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				if (msg.what != 1) {
					ToastUtil.toastShow(MyDataActivity.this, "图片上传失败");
					ProgressDialog.getInstance().dismissDialog();
					return;
				} else {
					PictureEntity entity = (PictureEntity) msg.obj;
					ToastUtil.toastShow(MyDataActivity.this, entity.getMsg());
					// reHead(entity.getContent().getAddress());
					SharedPreferencesUtil.saveString(SharedPreferencesUtil.ACCOUNT_AVATARUL,
							entity.getContent().publicUser.getAddress());
					ProgressDialog.getInstance().dismissDialog();
				}
			}
		});
	}

	/**
	 * 列表显示
	 */
	@SuppressLint({ "ClickableViewAccessibility", "InflateParams" })
	private void showPopWindow(View v) {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);// 隐藏软键盘
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

		View view = getLayoutInflater().inflate(R.layout.dialog_info_photo, null);
		view.findViewById(R.id.info_outter).setOnClickListener(new OnClickListener() {// 点击其他区域取消

			@Override
			public void onClick(View arg0) {
				popupwindows.dismiss();
			}
		});
		view.findViewById(R.id.info_photo_takephotos).setOnClickListener(new OnClickListener() {// 拍照
			@Override
			public void onClick(View arg0) {
				popupwindows.dismiss();
				try {
					String state = Environment.getExternalStorageState();
					if (state.equals(Environment.MEDIA_MOUNTED)) {
						mPhotoFile = new File(saveDir, "tmp.jpg");
						mPhotoFile.delete();
						if (!mPhotoFile.exists()) {
							try {
								mPhotoFile.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
								Toast.makeText(getApplication(), "照片创建失败!", Toast.LENGTH_LONG).show();
								return;
							}
						}
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhotoFile));
						startActivityForResult(intent, CAMERA_RESULT);
					} else {
						Toast.makeText(getApplication(), "sdcard无效或没有插入!", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {// 异常就是权限被禁
					ToastUtil.toastShow(MyDataActivity.this, "没打开相机权限");
				}
			}
		});
		view.findViewById(R.id.info_photo_benjiphots).setOnClickListener(new OnClickListener() {// 本地图片

			@Override
			public void onClick(View arg0) {
				popupwindows.dismiss();
				Intent i = new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				try {
					startActivityForResult(i, RESULT_LOAD_IMAGE);
				} catch (android.content.ActivityNotFoundException e) {
					Intent intent = new Intent(Intent.ACTION_PICK);
					intent.setType("image/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);
					startActivityForResult(intent, RESULT_LOAD_IMAGE);
				} catch (Exception e) {// 异常就是权限被禁
					ToastUtil.toastShow(MyDataActivity.this, "没读取本地照片权限");
				}
			}
		});
		view.findViewById(R.id.info_photo_return).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				popupwindows.dismiss();
			}
		});
		popupwindows = PopupWindowUtil.getPopupWindow(getApplicationContext(), view);
		popupwindows.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				// popupwindow = null;//不能写，会发生popupwindow不能设置图标show
				// 设置标题右边图标向下
			}
		});
		popupwindows.setTouchInterceptor(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupwindows.dismiss();
					return true;
				}
				return false;
			}
		});
		popupwindows.update();
		popupwindows.showAtLocation(v, Gravity.CENTER, 0, 0);
	}

	@SuppressLint("HandlerLeak")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
			if (mPhotoFile != null && mPhotoFile.exists()) {
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				bitmapOptions.inSampleSize = 8;
				int degree = readPictureDegree(mPhotoFile.getAbsolutePath());
				Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
				bitmap = rotaingImageView(degree, bitmap);
				climg_headimage.setImageBitmap(bitmap);
				commitAvatar(bitmap);
			}
		} else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			String picturePath = "";

			if (String.valueOf(data.getData()).indexOf(".") != -1) {
				Uri uri = data.getData();
				picturePath = uri.getPath();
			} else {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				picturePath = cursor.getString(columnIndex);

				cursor.close();
			}

			climg_headimage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			commitAvatar(BitmapFactory.decodeFile(picturePath));
		} else if (requestCode == NiChengActivity.NICHENG) {
			String nickName = data.getStringExtra("nickName");
			if (!TextUtils.isEmpty(nickName)) {
				tvName.setText(nickName);
			}
		} else if (requestCode == XiuGaiMiMaActivity.MODIFYPSW && resultCode == 0) {
			finish();
		}
	}

	private static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}

	private static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}
	/**
	 * 上传头像
	 */
	// private void uploadAvatar(Bitmap avatarBitmap) {
	// ImageUploadUtils.uploadImg(this, ApiConfig.UPLOADAVATAR, 0,
	// "avatar.jpeg", avatarBitmap, new MyHandler(this));
	// }

}