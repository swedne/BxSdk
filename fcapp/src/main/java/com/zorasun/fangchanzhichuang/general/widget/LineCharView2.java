package com.zorasun.fangchanzhichuang.general.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class LineCharView2 extends View {

	private int bgColor = Color.rgb(Integer.parseInt("4d", 16), Integer.parseInt("af", 16), Integer.parseInt("ea", 16));// ����ı���ɫ

	private String xyLineColor = "#919191";// ��������ɫ

	private String yUnit = "";// Y�ᵥλ

	// private boolean isDrawY = false;// �Ƿ����Y��

	private boolean isDrawX = false;// �Ƿ����X��

	private boolean isDrawInsideX = true;// �Ƿ�����ڲ���X��

	private boolean isDrawInsedeY = false;// �Ƿ�����ڲ���Y��

	private boolean isFillDown = false;// �Ƿ���������沿��

	private boolean isFillUp = false;// �Ƿ���������沿�֣���δʵ�֣�

	// private boolean isAppendX = true;// X���Ƿ�����ͻ��һ��

	private boolean isDemo = false;// �Ƿ�demo��������

	private int ScreenX;// view�Ŀ��

	private int ScreenY;// view�ĸ߶�

	private int numberOfX = 6;// Ĭ��X���6��ֵ

	private int numberOfY = 5;// Ĭ��Y���5��ֵ��Խ����ʾ��ֵԽ��ϸ��

	private int paddingTop = 30;// Ĭ���������ҵ�padding

	private int paddingLeft = 55;// Ĭ���������ҵ�padding55
	private int paddingRight = 40;// Ĭ���������ҵ�padding

	private int paddingDown = 50;// Ĭ���������ҵ�padding

	private int appendXLength = 0;// ����X��ͻ���ĳ���

	private float maxNumber = 0;// Y�����ֵ

	private List<List<Float>> pointList;// ���������

	private List<String> bitmapList = new ArrayList<String>();// �������ɫֵ
	private List<String> bitmapList1 = new ArrayList<String>();// �������ɫֵ

	private List<Integer> lineColorList;

	private List<String> titleXList = new ArrayList<>();// �����X�����

	private List<String> titleYList = new ArrayList<>();// ����ó���Y�����

	private String color1 = "#E230CE";
	private String color2 = "#FD7D21";
	private String color3 = "#4692DC";
	private String color4 = "#4CDD65";
	private String color5 = "#FE5053";
	private String color6 = "#F2D253";
	private ArrayList<String> circleLabelList = new ArrayList<>();

	private PathEffect effects;
	private Paint paintDraw;

	private Path path;

	private boolean isInt;

	public LineCharView2(Context context) {
		super(context);
		demo();
	}

	public LineCharView2(Context context, AttributeSet attr) {
		super(context, attr);
		bitmapList.add(color1);
		bitmapList.add(color2);
		bitmapList.add(color3);
		bitmapList.add(color4);
		bitmapList.add(color5);
		bitmapList.add(color6);
		initPaint();
		demo();
	}

	private void initPaint() {
		paintDraw = new Paint();
		paintDraw.setStyle(Paint.Style.STROKE);
		paintDraw.setColor(Color.parseColor(xyLineColor));// //�������ɫ
		path = new Path();
		effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
		paintDraw.setPathEffect(effects);
		paintDraw.setAntiAlias(true);
	}

	private void demo() {
		if (!isDemo) {
			return;
		}
		pointList = new ArrayList<List<Float>>();
		titleXList = new ArrayList<String>();
		lineColorList = new ArrayList<Integer>();
		lineColorList.add(Color.WHITE);
		lineColorList.add(Color.GREEN);
		lineColorList.add(Color.YELLOW);
		// TODO ����
		for (int i = 0; i < 3; i++) {
			List<Float> pointInList = new ArrayList<Float>();
			for (int j = 0; j < 6; j++) {
				Random r = new Random();
				Float z = r.nextFloat() * 100;
				pointInList.add(z);
				titleXList.add("12." + (i + 1) + "1");
			}
			pointList.add(pointInList);
		}
	}

	/**
	 * ����ó�View�Ŀ��
	 * 
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int measuredHeight = measureHeight(heightMeasureSpec);

		int measuredWidth = measureWidth(widthMeasureSpec);

		setMeasuredDimension(measuredWidth, measuredHeight);

		ScreenX = measuredWidth;

		ScreenY = measuredHeight - 20;

	}

	private int measureHeight(int measureSpec) {

		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		int result = 300;
		if (specMode == MeasureSpec.AT_MOST) {

			result = specSize;
		} else if (specMode == MeasureSpec.EXACTLY) {

			result = specSize;
		}

		return result;
	}

	private int measureWidth(int measureSpec) {
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		int result = 450;
		if (specMode == MeasureSpec.AT_MOST) {
			result = specSize;
		}

		else if (specMode == MeasureSpec.EXACTLY) {

			result = specSize;
		}

		return result;
	}

	/**
	 * �滭View����
	 * 
	 * @param canvas
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// maxNumber = 0;
		if (isInt) {
			paddingLeft = 60;
		} else {
			paddingLeft = 80;
		}
		canvas.translate(15.0f, 0.0f);
		List<Point> listX = initNumberOfX();// �����X��ƽ���������
		List<Point> listY = initNumberOfY();// �����Y��ƽ���������
		// canvas.drawColor(bgColor);// ����ɫ
		// fillColor(listX, canvas);// �������󣬶�ÿһ��������ͬ�������ɫ

		if (isDrawX) {
			int appendX = 0;
			// if (isAppendX) {
			// appendX = appendXLength;
			// }
			canvas.drawLine(paddingLeft - appendX, paddingTop + listY.get(0).y, listY.get(0).x + paddingLeft,
					paddingTop + listY.get(0).y, paintDraw);
		}
		// TODO
		// if (isDrawY) {
		//
		// canvas.drawLine(listX.get(0).x, paddingTop, listX.get(0).x,
		// listX.get(0).y + paddingTop, paintDraw);
		// }
		if (isDrawInsedeY) {// ���������
			for (Point point : listX) {
				if (!isDrawX) {
					isDrawX = !isDrawX;
					continue;
				}
				canvas.drawLine(point.x, paddingTop, point.x, point.y + paddingTop, paintDraw);
			}
		}
		if (isDrawInsideX) {// ���ƺ����
			for (Point point : listY) {
				// if (!isDrawY) {
				// isDrawY = !isDrawY;
				// continue;
				// }
				// int appendX = 0;
				// if (isAppendX) {
				// appendX = appendXLength;
				// }
				// canvas.drawLine(paddingLeft, paddingTop + point.y, point.x +
				// paddingLeft, paddingTop + point.y,
				// paintDraw);
				path.moveTo(paddingLeft, paddingTop + point.y);
				path.lineTo(point.x + paddingLeft, paddingTop + point.y);
				canvas.drawPath(path, paintDraw);
			}
		}

		setYTitle(listY, canvas);// ������ͼY�ĵ�λ��ͬʱ���������Y��ֵ

		List<List<Point>> positionList = countListPosition(listX);// ��������λ��
		// drawFill(canvas, positionList);// ������ߺͱ߿�
		drawCicleLabel(canvas, positionList);// ��
		drawChart(canvas, positionList);// ������
		drawCicle(canvas, positionList);// ����
		setXTitle(listX, canvas);// ������ͼX�ĵ�λ
		// drawCicleLabelDetail(canvas, positionList);

	}

	private void drawCicleLabelDetail(Canvas canvas, List<List<Point>> positionList) {

		int num = (ScreenX - paddingRight) / (numberOfX);
		Paint paint = new Paint();
		paint.setTextSize(18);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		paint.setTypeface(font);
		paint.setColor(Color.parseColor("#666666"));
		paint.setAntiAlias(true);
		String color;
		// TODO
		canvas.translate(canvas.getWidth() / 2, 0);
		for (int i = 0; i < circleLabelList.size(); i++) {
			if (bitmapList != null && bitmapList.get(i) != null) {
				color = bitmapList.get(i);
			} else {
				color = "#aaaaaa";
			}
			paint.setColor(Color.parseColor(color));
			// canvas.drawCircle(30 + num * i, ScreenY + 4, 6, paint);
			// canvas.drawText(circleLabelList.get(i), 30 + 10 + num * i,
			// ScreenY + 8, paint);
			canvas.drawCircle((-1.5f * (circleLabelList.size() - 1)) * 32 + i * 4 * 32 - 60, ScreenY + 4, 6, paint);
			canvas.drawText(circleLabelList.get(i), (-1.5f * (circleLabelList.size() - 1)) * 32 + i * 4 * 32 - 60 + 10,
					ScreenY + 8, paint);
			// canvas.drawCircle(positionList.get(i).get(j).x,
			// positionList.get(i).get(j).y,4, paint);
		}

	}

	private void drawCicleLabel(Canvas canvas, List<List<Point>> positionList) {
		int num = (ScreenX - paddingRight) / (numberOfX);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		Paint textPaint = new Paint();
		textPaint.setTextSize(20);
		textPaint.setTypeface(font);
		textPaint.setColor(Color.parseColor("#666666"));
		textPaint.setAntiAlias(true);
		Paint paint = new Paint();
		paint.setTextSize(18);
		paint.setTypeface(font);
		paint.setAntiAlias(true);
		String color = null;
		// TODO
		// canvas.translate(canvas.getWidth() / 2, 0);
		if (circleLabelList.size() > 0) {
			bitmapList1.clear();
			for (int i = 0; i < circleLabelList.size(); i++) {
				if (circleLabelList.get(i).equals("湖里区")) {
					color = bitmapList.get(3);
				} else if (circleLabelList.get(i).equals("思明区")) {
					color = bitmapList.get(4);
				} else if (circleLabelList.get(i).equals("海沧区")) {
					color = bitmapList.get(5);
				} else if (circleLabelList.get(i).equals("同安区")) {
					color = bitmapList.get(2);
				} else if (circleLabelList.get(i).equals("集美区")) {
					color = bitmapList.get(1);
				} else if (circleLabelList.get(i).equals("翔安区")) {
					color = bitmapList.get(0);
				}
				bitmapList1.add(color);
				paint.setColor(Color.parseColor(color));
				canvas.drawCircle(20 + num * i, ScreenY + 4, 6, paint);
				canvas.drawText(circleLabelList.get(i), 20 + 10 + num * i, ScreenY + 8, textPaint);
			}
		}
		// canvas.drawCircle((-1.5f * (circleLabelList.size() - 1)) * 32 + i
		// * 3 * 32, ScreenY + 4, 6, paint);
		// canvas.drawText(circleLabelList.get(i), (-1.5f *
		// (circleLabelList.size() - 1)) * 32 + i * 3 * 32 + 10,
		// ScreenY + 8, paint);
		// canvas.drawCircle(positionList.get(i).get(j).x,
		// positionList.get(i).get(j).y,4, paint);
	}

	private void drawFill(Canvas canvas, List<List<Point>> positionList) {
		if (!isFillDown) {
			return;
		}
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		// paint.setColor(fillDownColor);
		paint.setAlpha(76);
		for (int i = 0; i < positionList.size(); i++) {
			Path path = new Path();
			path.moveTo(paddingLeft, ScreenY - paddingDown);
			for (int j = 0; j < positionList.get(i).size(); j++) {
				path.lineTo(positionList.get(i).get(j).x, positionList.get(i).get(j).y);
			}
			path.lineTo(ScreenX - paddingRight, ScreenY - paddingDown);
			path.close();
			canvas.drawPath(path, paint);
		}
	}

	private void drawCicle(Canvas canvas, List<List<Point>> positionList) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		// Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
		// R.drawable.comm_chart_point);
		String color = null;
		for (int i = 0; i < positionList.size(); i++) {
			if (bitmapList1.size() > 0) {
				color = bitmapList1.get(i);
			} else {
				color = "#aaaaaa";
			}
			paint.setColor(Color.parseColor(color));
			for (int j = 0; j < positionList.get(i).size(); j++) {
				// canvas.drawPoint(positionList.get(i).get(j).x,
				// positionList.get(i).get(j).y, paint);
				canvas.drawCircle(positionList.get(i).get(j).x, positionList.get(i).get(j).y, 4, paint);
				// canvas.drawBitmap(bitmap, positionList.get(i).get(j).x + 0.5f
				// - bitmap.getWidth() / 2,
				// positionList.get(i).get(j).y + 0.5f - bitmap.getHeight() / 2,
				// paint);
			}
		}
	}

	private List<List<Point>> countListPosition(List<Point> listX) {
		List<List<Point>> positionList = new ArrayList<List<Point>>();
		if (pointList == null) {
			pointList = new ArrayList<List<Float>>();
			List<Float> pointInList = new ArrayList<Float>();
			for (int i = 0; i < numberOfX; i++) {
				pointInList.add(0f);
			}
			pointList.add(pointInList);
		}
		for (int i = 0; i < pointList.size(); i++) {
			List<Point> positionInList = new ArrayList<Point>();
			for (int j = 0; j < pointList.get(i).size(); j++) {
				Point point = new Point();
				Float z = pointList.get(i).get(j);
				if (j > 5) {
					continue;
				}
				point.x = listX.get(j).x;
				point.y = listX.get(j).y + paddingTop - (int) ((listX.get(j).y) * (float) z / (float) maxNumber);
				positionInList.add(point);
			}
			positionList.add(positionInList);
		}
		return positionList;
	}

	private void drawChart(Canvas canvas, List<List<Point>> positionList) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		// paint.setColor(chartLineColor);
		paint.setStrokeWidth(2);// Ĭ���߿�Ϊ3����ʱ��������ȫ�ֱ�������������
		Paint shadowPaint = new Paint();
		shadowPaint.setAntiAlias(true);
		// shadowPaint.setColor(shadowLineColor);
		shadowPaint.setStrokeWidth(1);// Ĭ���߿�Ϊ3����ʱ��������ȫ�ֱ�������������
		shadowPaint.setAlpha(178);
		String color = null;
		for (int i = 0; i < positionList.size(); i++) {
			if (bitmapList1.size() > 0) {
				color = bitmapList1.get(i);
			} else {
				color = "#aaaaaa";
			}
			paint.setColor(Color.parseColor(color));
			for (int j = 0; j < positionList.get(i).size() - 1; j++) {
				// canvas.drawLine(positionList.get(i).get(j).x,
				// positionList.get(i).get(j).y + 2,
				// positionList.get(i).get(j + 1).x, positionList.get(i).get(j +
				// 1).y + 2, shadowPaint);
				canvas.drawLine(positionList.get(i).get(j).x, positionList.get(i).get(j).y,
						positionList.get(i).get(j + 1).x, positionList.get(i).get(j + 1).y, paint);
			}
		}

	}

	// private void fillColor(List<Point> listX, Canvas canvas) {
	// Paint paint = new Paint();
	// paint.setStyle(Style.FILL);
	// for (int i = 0; i < numberOfX - 1; i++) {
	// if (i % 2 == 0) {
	// paint.setColor(singleColumnFillColor);
	// paint.setAlpha(102);
	// } else {
	// paint.setColor(doubleColumnFillColor);
	// paint.setAlpha(255);
	// }
	// canvas.drawRect(listX.get(i).x, paddingTop, listX.get(i + 1).x, ScreenY -
	// paddingDown, paint);
	// }
	// }

	private void setYTitle(List<Point> listY, Canvas canvas) {
		Paint paint = new Paint();
		paint.setTextSize(20);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		paint.setTypeface(font);
		paint.setColor(Color.parseColor("#666666"));
		if (titleYList == null) {
			titleYList = new ArrayList<String>();
			for (int i = 1; i <= numberOfY; i++) {
				titleYList.add(String.valueOf(100 / i));
			}
		} else {
			if (pointList != null) {
				for (int i = 0; i < pointList.size(); i++) {
					for (int j = 0; j < pointList.get(i).size(); j++) {
						if (pointList.get(i).get(j) > maxNumber) {
							maxNumber = pointList.get(i).get(j);
						}
					}
				}
				if (isInt) {
					maxNumber = (int) (maxNumber + 0.5);
				}
				titleYList = new ArrayList<String>();
				java.text.DecimalFormat df = new java.text.DecimalFormat("#####0.00");
				for (int i = 0; i < numberOfY; i++) {
					if (isInt) {
						titleYList.add(String.valueOf((int) (0 + i * (maxNumber / (numberOfY - 1)))));
					} else {
						titleYList.add(df.format((0 + i * (maxNumber / (numberOfY - 1)))));
					}
				}

			}

		}
		if (titleYList.size() > 0)

		{
			for (int i = 0; i < titleYList.size(); i++) {
				int appendX = 0;
				// if (isAppendX) {
				// appendX = appendXLength;
				// }
				if (i != 0) {
					// TODO
					// canvas.drawText(titleYList.get(i), paddingLeft - appendX
					// -
					// paddingLeft / 3, paddingTop + listY.get(i).y,
					// paint);
					canvas.drawText(titleYList.get(i) + yUnit, -2, paddingTop + listY.get(i).y, paint);
				} else {
					// TODO
//					canvas.drawText(titleYList.get(i), 15, paddingTop + listY.get(i).y, paint);
					canvas.drawText("0", 15, paddingTop + listY.get(i).y, paint);
				}
			}
		}

	}

	private void setXTitle(List<Point> listX, Canvas canvas) {
		Paint paint = new Paint();
		paint.setTextSize(18);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		paint.setTypeface(font);
		paint.setColor(Color.parseColor("#666666"));
		if (titleXList == null) {
			titleXList = new ArrayList<String>();
			for (int i = 1; i <= numberOfX; i++) {
				titleXList.add("title" + i);
			}
		}
		if (titleXList.size() > 0) {
			for (int i = 0; i < titleXList.size(); i++) {
				canvas.save();
				// canvas.rotate(30, listX.get(i).x, listX.get(i).y + paddingTop
				// +
				// paddingDown / 2);
				canvas.drawText(titleXList.get(i), listX.get(i).x - 2 * numberOfX - 4,
						listX.get(i).y + paddingTop + paddingDown / 2, paint);
				canvas.restore();
			}
		}
	}

	private List<Point> initNumberOfX() {
		int num = (ScreenX - paddingLeft - paddingRight) / (numberOfX - 1);
		List<Point> list = new ArrayList<Point>();
		list.clear();
		for (int i = 0; i < numberOfX; i++) {
			Point point = new Point();
			point.y = ScreenY - paddingDown - paddingTop;
			point.x = paddingLeft + num * i;
			list.add(point);
		}
		return list;
	}

	private List<Point> initNumberOfY() {
		int num = (ScreenY - paddingDown - paddingTop) / (numberOfY - 1);
		List<Point> list = new ArrayList<Point>();
		list.clear();
		for (int i = 0; i < numberOfY; i++) {
			Point point = new Point();
			point.x = ScreenX - paddingLeft - paddingRight;
			point.y = ScreenY - paddingDown - paddingTop - num * i;
			list.add(point);
		}
		return list;
	}

	// public boolean isDrawY() {
	// return isDrawY;
	// }
	//
	// public void setDrawY(boolean isDrawY) {
	// this.isDrawY = isDrawY;
	// }

	public boolean isDrawX() {
		return isDrawX;
	}

	public void setDrawX(boolean isDrawX) {
		this.isDrawX = isDrawX;
	}

	public boolean isFillDown() {
		return isFillDown;
	}

	public void setFillDown(boolean isFillDown) {
		this.isFillDown = isFillDown;
	}

	public boolean isFillUp() {
		return isFillUp;
	}

	public void setFillUp(boolean isFillUp) {
		this.isFillUp = isFillUp;
	}

	public int getScreenX() {
		return ScreenX;
	}

	public void setScreenX(int screenX) {
		ScreenX = screenX;
	}

	public int getScreenY() {
		return ScreenY;
	}

	public void setScreenY(int screenY) {
		ScreenY = screenY;
	}

	public int getNumberOfX() {
		return numberOfX;
	}

	public void setNumberOfX(int numberOfX) {
		this.numberOfX = numberOfX;
	}

	public int getNumberOfY() {
		return numberOfY;
	}

	public void setNumberOfY(int numberOfY, boolean isInt) {
		this.numberOfY = numberOfY;
		this.isInt = isInt;
		invalidate();
	}

	public void setClassfy(boolean isInt) {
		this.isInt = isInt;
	}

	public boolean isDrawInsideX() {
		return isDrawInsideX;
	}

	public void setDrawInsideX(boolean isDrawInsideX) {
		this.isDrawInsideX = isDrawInsideX;
	}

	public boolean isDrawInsedeY() {
		return isDrawInsedeY;
	}

	public void setDrawInsedeY(boolean isDrawInsedeY) {
		this.isDrawInsedeY = isDrawInsedeY;
	}

	// public boolean isAppendX() {
	// return isAppendX;
	// }
	//
	// public void setAppendX(boolean isAppendX) {
	// this.isAppendX = isAppendX;
	// }

	public int getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}

	public int getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public int getPaddingRight() {
		return paddingRight;
	}

	public void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
	}

	public int getPaddingDown() {
		return paddingDown;
	}

	public void setPaddingDown(int paddingDown) {
		this.paddingDown = paddingDown;
	}

	public int getAppendXLength() {
		return appendXLength;
	}

	public void setAppendXLength(int appendXLength) {
		this.appendXLength = appendXLength;
	}

	public float getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(float maxNumber) {
		this.maxNumber = maxNumber;
	}

	public List<String> getTitleXList() {
		return titleXList;
	}

	public void setTitleXList(List<String> titleXList) {
		this.titleXList.clear();
		this.titleXList.addAll(titleXList);
		invalidate();
	}

	public int getBgColor() {
		return bgColor;
	}

	public void setBgColor(int bgColor) {
		this.bgColor = bgColor;
	}

	// public int getSingleColumnFillColor() {
	// return singleColumnFillColor;
	// }
	//
	// public void setSingleColumnFillColor(int singleColumnFillColor) {
	// this.singleColumnFillColor = singleColumnFillColor;
	// }
	//
	// public int getDoubleColumnFillColor() {
	// return doubleColumnFillColor;
	// }
	//
	// public void setDoubleColumnFillColor(int doubleColumnFillColor) {
	// this.doubleColumnFillColor = doubleColumnFillColor;
	// }
	//
	// public int getFillDownColor() {
	// return fillDownColor;
	// }
	//
	// public void setFillDownColor(int fillDownColor) {
	// this.fillDownColor = fillDownColor;
	// }

	public String getXyLineColor() {
		return xyLineColor;
	}

	public void setXyLineColor(String xyLineColor) {
		this.xyLineColor = xyLineColor;
	}

	// public int getShadowLineColor() {
	// return shadowLineColor;
	// }
	//
	// public void setShadowLineColor(int shadowLineColor) {
	// this.shadowLineColor = shadowLineColor;
	// }
	//
	// public int getChartLineColor() {
	// return chartLineColor;
	// }
	//
	// public void setChartLineColor(int chartLineColor) {
	// this.chartLineColor = chartLineColor;
	// }

	public String getyUnit() {
		return yUnit;
	}

	public void setyUnit(String yUnit) {
		this.yUnit = yUnit;
	}

	public List<List<Float>> getPointList() {
		return pointList;
	}

	public void setPointList(List<List<Float>> pointList) {
		this.pointList = pointList;
		invalidate();
	}

	public List<String> getBitmapList() {
		return bitmapList;
	}

	public void setBitmapList(List<String> bitmapList) {
		bitmapList.clear();
		bitmapList.addAll(bitmapList);
	}

	public List<Integer> getLineColorList() {
		return lineColorList;
	}

	public void setLineColorList(List<Integer> lineColorList) {
		this.lineColorList = lineColorList;
	}

	public ArrayList<String> getCircleLabelList() {
		return circleLabelList;
	}

	public void setCircleLabelList(ArrayList<String> circleLabelList) {
		this.circleLabelList = circleLabelList;
	}

}
