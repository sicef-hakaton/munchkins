package com.example.hakatonapp.view;

import it.gmariotti.cardslib.library.view.CardListView;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CommentListView extends CardListView {

	ArrayList<View> childrenTahtWantTouch;

	public CommentListView(Context context) {
		super(context);
		childrenTahtWantTouch = new ArrayList<View>();
	}

	public CommentListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
		childrenTahtWantTouch = new ArrayList<View>();
	}

	public CommentListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
		childrenTahtWantTouch = new ArrayList<View>();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {

		if (childrenTahtWantTouch == null || childrenTahtWantTouch.isEmpty())
			return super.onInterceptTouchEvent(event);

		for (View v : childrenTahtWantTouch) {
			if (viewClicked(v, event))
				return false;
		}

		return super.onInterceptTouchEvent(event);
	}

	public void addViewThatWantTouch(View v) {
		childrenTahtWantTouch.add(v);
	}

	private boolean viewClicked(View view, MotionEvent event) {

		if (view == null || !view.isShown() || view.getVisibility() == View.GONE)
			return false;

		// EVENT DATA
		int eX = (int) event.getRawX();
		int eY = (int) event.getRawY();

		int[] absCoords = new int[2];
		view.getLocationOnScreen(absCoords);

		// CHILD VIEW DATA
		int x = absCoords[0];
		int y = absCoords[1];
		int w = view.getWidth();
		int h = view.getHeight();

		// KeevaLogger.d("event - (" + eX + "," + eY + ")");
		// KeevaLogger.d("view - (" + x + "," + y + "," + w + "," + h + ")");

		if (eX >= x && eX <= x + w && eY >= y && eY <= y + h)
			return true;

		return false;
	}
}
