package com.torah.anytime.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public abstract class TAFragment extends Fragment {
	private View mContentView;
	private ImageView imageViewDedBanner;
	private float screenDensity;

	public abstract void refreshViews();

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	
	}


}
