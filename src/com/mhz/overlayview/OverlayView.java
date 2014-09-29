/* $Id: $ */
package com.mhz.overlayview;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by shanmuga-1921 on 20/9/13.
 */
public class OverlayView extends ViewGroup {

	private static final String TAG = "OverlayView";

	public static final int VERTICAL = 1;
	public static final int HORIZONTAL = 2;

	private int columns;
	private int rows;
	private int itemMinWidth;
	private int itemMinHeight;
	private int itemWidth;
	private int itemHeight;
	private int itemLayoutResource;
	private int itemOverlayLayoutResource;
	private boolean stackFromEnd;
	private int orientation;

	private int configurationXML; //TODO check whether it is working or not
	private Drawable overlayIcon;
	private String overlayText;
	private Drawable overlayWindowBackground;

	private List<OverlayState> overlayStates;
	private OverlayInflator overlayInflator;

	private Paint paint;

	private LayoutInflater layoutInflater;

	public OverlayView(Context context) {
		super(context);
		init();
	}

	public OverlayView(Context context, AttributeSet attrs) {
		super(context, attrs);

		overlayInflator = new OverlayInflator(getContext());

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OverlayView, 0, 0);

		try {
			columns = a.getInteger(R.styleable.OverlayView_columns, -1);
			rows = a.getInteger(R.styleable.OverlayView_row, -1);

			itemMinWidth = a.getDimensionPixelSize(R.styleable.OverlayView_item_min_width, -1);
			itemMinHeight = a.getDimensionPixelSize(R.styleable.OverlayView_item_min_height, -1);

			itemWidth = a.getDimensionPixelSize(R.styleable.OverlayView_item_width, -1);
			itemHeight = a.getDimensionPixelSize(R.styleable.OverlayView_item_height, -1);

			itemLayoutResource = a.getResourceId(R.styleable.OverlayView_item_layout, -1);
			itemOverlayLayoutResource = a.getResourceId(R.styleable.OverlayView_item_overlay_layout, -1);

			stackFromEnd = a.getBoolean(R.styleable.OverlayView_stack_from_end, false);
			orientation = a.getInteger(R.styleable.OverlayView_orientation, VERTICAL);

			configurationXML = a.getResourceId(R.styleable.OverlayView_overlay_item_xml, -1);
			overlayIcon = a.getDrawable(R.styleable.OverlayView_overlay_image);
			overlayText = a.getString(R.styleable.OverlayView_overlay_text);
			overlayWindowBackground = a.getDrawable(R.styleable.OverlayView_overlayWindowBackground);
		} finally {
			a.recycle();
		}

		checkForValues();

		init();

		setWillNotDraw(false);
	}

	private void init() {
		layoutInflater = LayoutInflater.from(getContext());
		overlayStates = overlayInflator.inflate(configurationXML);

		paint = new Paint();
		
		addViews();
	}

	private View getView(OverlayState overlayState , int position) {
		Log.e(TAG, "get view...");
		View view = layoutInflater.inflate(itemLayoutResource, null);
//		TextView textView = (TextView) view.findViewById(R.id.textview);
//		textView.setText(overlayState.getItemText());
		
		view.layout(10, 10, 300, 300);
		Log.e(TAG, "layout param: " + ((ViewGroup) view).getChildCount());
		
		
//		View view = new View(getContext());
//		view.setBackgroundColor(Color.parseColor("#782357"));
//		int right = 50;
//		view.layout(10, 10, right, 50);
		
		return view;
	}

	private void checkForValues() {
		if(configurationXML == -1) {
			throw new IllegalArgumentException("configuration file is missing");
		}
		if(overlayIcon == null) {
		}
		if(overlayWindowBackground == null) {
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub

		measureChild(widthMeasureSpec, heightMeasureSpec);
	}

	private void measureChild(int widthMeasureSpec, int heightMeasureSpec) {
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		
		setMeasuredDimension(500, 400);
	}
	
	private void addViews() {
		addView(getView(overlayStates.get(0), 0));
		addView(getView(overlayStates.get(1), 1));
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		Log.e(TAG, "changed: " + changed+", "+left+", "+top+", "+right+", "+bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.e(TAG, "On draw..." + getChildCount());
		//		canvas.drawRect(0, 0, 100, 100, paint);

//		for(OverlayState o : overlayStates) {
//			addView(getView(o));
//		}
	}
}