package com.jainbooks.fragments;

import org.jainbooks.ebook.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.jainbooks.model.Store;
import com.jainbooks.utils.NotificationUtils;
import com.jainbooks.utils.TAListener;
import com.jainbooks.utils.Utils;
import com.jainbooks.web.TAWebServiceAsyncTask;
import com.jainbooks.web.WebServiceConstants;
import com.jainbooks.activitys.App;
import com.jainbooks.activitys.DashboardActivity;
import com.jainbooks.adapter.EBookStoreFragmentAdapter;

/**
 * Temporary placeholder/reference
 * 
 * @author Tn Yadav
 * 
 */
public class EBookStoreFragment extends BaseFragment {

	private ViewPager pager;
	private PagerTabStrip indicator;
	private Store store;
    //private App app;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	   
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		store=dashboardActivity.app.getStore();
           	view = inflater.inflate(R.layout.activity_video_player, container,
    				false);
        	
      	
		return view;

	}

@Override
public void onResume() {
	super.onResume();
	
	if (store!=null) {
		setupUiComponent();
	}else {
		getStore();
	}
}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		dashboardActivity.getMenuInflater().inflate(R.menu.search, menu);
		super.onCreateOptionsMenu(menu, inflater);

	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = dashboardActivity.mDrawerLayout
				.isDrawerOpen(dashboardActivity.mDrawerList);
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
		
		pager = (android.support.v4.view.ViewPager) view
				.findViewById(R.id.pager);
		indicator = PagerTabStrip.class.cast(view
				.findViewById(R.id.pager_title_strip));
		pager.setAdapter(new EBookStoreFragmentAdapter(getFragmentManager()));
		//indicator.setHorizontalScrollBarEnabled(true);
		indicator.setTabIndicatorColor(getResources().getColor(
				R.color.alpha_blue));
		indicator.setSelected(true);
		indicator.setDrawFullUnderline(false);
		pager.setCurrentItem(1);
		//indicator.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		

	}

	private void getStore() {

		Bundle argBundle = new Bundle();
		
		new TAWebServiceAsyncTask(
				dashboardActivity,
				argBundle,
				new TAListener() {

					@Override
					public void onTaskFailed(Bundle argBundle) {

						String result1 = "fmhgfcmhg";

						Log.e("val", result1);
					}

					@Override
					public void onTaskCompleted(Bundle argBundle) {
						String resultJSON = argBundle
								.getString(TAListener.LISTENER_BUNDLE_STRING);

						if (resultJSON != null
								&& !TextUtils.isEmpty(resultJSON)) {
							 store = new Gson().fromJson(resultJSON,
									Store.class);
							 if (store!=null) {
								 dashboardActivity.app.setStore(store);
								 setupUiComponent();
								} else {
                                NotificationUtils.showNotificationToast(dashboardActivity, "Server Not Responds");
							}
						}

					}
				},
				WebServiceConstants.BASE_URL+WebServiceConstants.GET_EBOOK_CATEGORIES,
				false).execute();

	}
	
}
