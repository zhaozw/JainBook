package com.jainbooks.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.jainbooks.activitys.DashboardActivity;

public abstract class BaseFragment extends Fragment{
	
	protected DashboardActivity dashboardActivity;
	protected View view;

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setHasOptionsMenu(true);
	    }
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		dashboardActivity=(DashboardActivity) activity;
		
		
	}
	
	abstract void setupUiComponent(); 

}
