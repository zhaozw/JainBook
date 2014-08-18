package com.jainbooks.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

public class JainButton extends Button {

	public JainButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public JainButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public JainButton(Context context) {
		super(context);
		init();
	}

	private void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/calibri.otf");
		setTypeface(tf);
	}
}