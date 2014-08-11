package com.torah.anytime.app;

import org.jainbooks.ebook.R;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.torah.anytime.utils.ImageLoader;
import com.torah.anytime.utils.TAListener;
import com.torah.anytime.utils.Utils;

public class TASplashActivity extends Activity {/*

	private boolean isPaused;
	private ImageView imageViewSplash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			if (getIntent().getExtras().getBoolean("setToKill", false)) {
				this.finish();
				return;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		setContentView(R.layout.activity_splash);
		imageViewSplash = (ImageView) findViewById(R.id.imageViewSplash);

		new ImageLoader(TASplashActivity.this)
				.displaySplashImage(
						"http://www.torahanytime.com/wp-content/uploads/appImage/1399047690_815476118e2f499ba14dc2fdd75aad7a.gif",
						imageViewSplash, ScaleType.FIT_XY);
		new ImageLoader(TASplashActivity.this)
		.displaySplashImage(
				"http://www.torahanytime.com/wp-content/uploads/appImage/1401819145_18913f6105dac9dc0162e8f9ab6e517c_iosretina.png",
				imageViewSplash, ScaleType.FIT_XY);
	    startAsync();
		toggleActionBarVisibility(false);
		toggleTitleBarVisibility(false);

		Utils.initUserSettings(TASplashActivity.this);

	

	};
	
private void  finishActivity() {
	new Handler().postDelayed(new Runnable() {

		@Override
		public void run() {
			if (!isPaused) {
				Intent i = new Intent(TASplashActivity.this,
						TAMainActivity.class);
				startActivity(i);
				TASplashActivity.this.finish();
			} else {
				TASplashActivity.this.finish();
			}
		}
	}, 4000);
}
	@Override
	protected void onPause() {
		super.onPause();
		isPaused = true;
	}

	*//**
	 * This method toggles visibility and title of action bar.
	 * 
	 * @param showFlag
	 *            Visibility of action bar.
	 * @param actionBartitle
	 *            Title string to be set.
	 *//*
	public void toggleActionBarVisibility(boolean showFlag) {
		if (!showFlag) {
			ActionBar action = getActionBar();
			action.hide();
		} else {
			ActionBar action = getActionBar();
			action.show();
		}

	}

	*//**
	 * This method toggles visibility of the Android title bar.
	 * 
	 * @param showFlag
	 *//*
	public void toggleTitleBarVisibility(boolean showFlag) {
		if (!showFlag) {
			try {
				((View) this.findViewById(android.R.id.title).getParent())
						.setVisibility(View.GONE);
			} catch (NullPointerException e) {
				// Do nothing
			}
			this.getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			this.getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		} else {
			try {
				((View) this.findViewById(android.R.id.title).getParent())
						.setVisibility(View.VISIBLE);
			} catch (NullPointerException e) {
				// Do nothing
			}
			this.getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
			this.getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}
*/}
