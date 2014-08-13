package com.jainbooks.fragments;


import org.jainbooks.ebook.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jainbooks.activitys.DashboardActivity;

/**
 * Temporary placeholder/reference
 * 
 * @author Atul Mittal
 * 
 */
public class EBookStoreFragment extends BaseFragment {

	private ViewPager pager;
	private PagerTabStrip indicator;
    
   
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		 view = inflater.inflate(R.layout.activity_video_player,
				container, false);
		setupUiComponent();
		return view;
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		dashboardActivity.getMenuInflater().inflate(R.menu.search, menu);
		super.onCreateOptionsMenu(menu, inflater);
		
	}
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen =dashboardActivity.mDrawerLayout.isDrawerOpen(dashboardActivity.mDrawerList);
		menu.findItem(R.id.action_search).setVisible(!drawerOpen);
		super.onPrepareOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (dashboardActivity.mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_search:

		

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}
	@Override
	void setupUiComponent() {
		pager = (android.support.v4.view.ViewPager)view.findViewById(R.id.pager);
		indicator =PagerTabStrip.class.cast( view.findViewById(R.id.pager_title_strip));
		pager.setAdapter(new EBookStoreFragmentAdapter(getFragmentManager()));
		indicator.setHorizontalScrollBarEnabled(true);
		indicator.setTabIndicatorColor(getResources().getColor(R.color.alpha_blue));
		indicator.setSelected(true);
		indicator.setDrawFullUnderline(false);
		indicator.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		
		
	}
}
