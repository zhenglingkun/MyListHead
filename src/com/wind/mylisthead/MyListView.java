package com.wind.mylisthead;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MyListView extends ListView {
	
	private boolean mAddHeader = false;
	private ViewGroup mSearchView = null;
	private int mSearchViewWidth = 0;
	private int mSearchViewHeight = 0;

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
	boolean clampedY) {
	//Log.e(TAG, "onOverScrolled(),scrollX=" +scrollX + ",scrollY="+scrollY+
	//",clampedX="+clampedX+",clampedY:"+clampedY+",mAddHeader="+mAddHeader);

	//判断向下滚动，重新布局
	if(scrollY < 0 ){
	mAddHeader = true;
	if(mSearchView != null){
	mSearchView.layout(0, scrollY, mSearchViewWidth, mSearchViewHeight + scrollY);
	this.setHeaderDividersEnabled(false);
	}

	//向上滚动时，重新布局
	}else if(scrollY >= 0 && mAddHeader ){
	mAddHeader = false;
	requestLayout();
	}
	super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
	}

	@Override
	public void addHeaderView(View v) {
		super.addHeaderView(v);
		mSearchView = (ViewGroup) v;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (mSearchView != null) {
			mSearchViewWidth = mSearchView.getMeasuredWidth();
			mSearchViewHeight = mSearchView.getMeasuredHeight();
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mSearchView != null) {
			mSearchView.layout(0, 0, mSearchViewWidth, mSearchViewHeight);
		}
	}

}
