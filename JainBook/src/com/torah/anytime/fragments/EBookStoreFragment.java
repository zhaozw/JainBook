package com.torah.anytime.fragments;


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

import com.torah.anytime.app.TAMainActivity;

/**
 * Temporary placeholder/reference
 * 
 * @author Atul Mittal
 * 
 */
public class EBookStoreFragment extends Fragment {

	private ViewPager pager;
	private PagerTabStrip indicator;
    private TAMainActivity activity;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		View contentView = inflater.inflate(R.layout.activity_video_player,
				container, false);
		pager = (android.support.v4.view.ViewPager)contentView.findViewById(R.id.pager);
		indicator =PagerTabStrip.class.cast( contentView.findViewById(R.id.pager_title_strip));
		pager.setAdapter(new EBookStoreFragmentAdapter(getFragmentManager()));
		indicator.setHorizontalScrollBarEnabled(true);
		//indicator.setTabIndicatorColor(getResources().getColor(R.color.busy_indicator));
		indicator.setSelected(true);
		indicator.setDrawFullUnderline(true);
		indicator.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		
		return contentView;
		
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		activity=(TAMainActivity)activity;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		activity.getMenuInflater().inflate(R.menu.search, menu);
		super.onCreateOptionsMenu(menu, inflater);
		
	}
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen =activity.mDrawerLayout.isDrawerOpen(activity.mDrawerList);
		menu.findItem(R.id.action_search).setVisible(!drawerOpen);
		super.onPrepareOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (activity.mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_search:

		

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}
}
