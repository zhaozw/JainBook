package com.torah.anytime.app;


import org.jainbooks.ebook.R;

import com.torah.anytime.utils.NotificationUtils;
import com.torah.anytime.utils.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

@SuppressLint("NewApi") public class TANetworkHandlerActivity extends Activity {

	private ImageButton buttonNetworkRetry;
	private ImageButton buttonGoToMyLibrary;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_network);
		
		getActionBar().setIcon(R.drawable.icon);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.actionbar_bg));
		getActionBar().setTitle("Network");

		initVar();

	}

	private void initVar() {
		buttonNetworkRetry = (ImageButton) findViewById(R.id.buttonNetworkRetry);
		buttonGoToMyLibrary = (ImageButton) findViewById(R.id.buttonGoToMyLibrary);

		buttonNetworkRetry.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (Utils.isNetworkAvailable(TANetworkHandlerActivity.this)) {
					NotificationUtils.showNotificationToast(
							TANetworkHandlerActivity.this, "Welcome back!");
					TANetworkHandlerActivity.this.finish();
				} else {
					NotificationUtils.showNotificationToast(
							TANetworkHandlerActivity.this,
							"No network connection.");
				}
			}
		});

		buttonGoToMyLibrary.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(TANetworkHandlerActivity.this,
						TAMainActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				Bundle b = new Bundle();
				b.putString("startMyLibrary", "true");
				i.putExtras(b);
				startActivity(i);
			}
		});
	}

	
}
