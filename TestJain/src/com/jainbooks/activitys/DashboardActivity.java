/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jainbooks.activitys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.jainbooks.ebook.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.jainbooks.adapter.TASlidingMenuListAdapter;
import com.jainbooks.fragments.AccountFragment;
import com.jainbooks.fragments.EBookMyLibraryFragment;
import com.jainbooks.fragments.EBookNetworkHandlerFragment;
import com.jainbooks.fragments.EBookStoreFragment;
import com.jainbooks.model.Store;
import com.jainbooks.utils.TAListener;
import com.jainbooks.utils.Utils;
import com.jainbooks.web.TAPOSTWebServiceAsyncTask;
import com.jainbooks.web.WebServiceConstants;

public class DashboardActivity extends Activity {
	public DrawerLayout mDrawerLayout;
	public ListView mDrawerList;
	public ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mFragmentTitlesTitles;
	private Fragment pendingFragment;
	private int pendingPosition;
	private int currentPosition;
	public static boolean showLoginFragment;
	public static TASlidingMenuListAdapter drawerAdapter;
    public static Activity activity;
    public  App app;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	
		setContentView(R.layout.activity_main);
		activity=this;
		
		/*
		
		 try {
	            PackageInfo info = getPackageManager().getPackageInfo(
	                    "org.jainbooks.ebook", 
	                    PackageManager.GET_SIGNATURES);
	            for (Signature signature : info.signatures) {
	                MessageDigest md = MessageDigest.getInstance("SHA");
	                md.update(signature.toByteArray());
	                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
	                }
	        } catch (NameNotFoundException e) {

	        } catch (NoSuchAlgorithmException e) {

	        }*/
		
		
	  app=(App) getApplication();
		mTitle = mDrawerTitle = "JainBook";
		mFragmentTitlesTitles = getResources().getStringArray(
				R.array.drawer_items);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		//LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		//mDrawerList.addHeaderView();
		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// set up the drawer's list view with items and click listener
		drawerAdapter = new TASlidingMenuListAdapter(DashboardActivity.this);
		mDrawerList.setAdapter(drawerAdapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setIcon(R.drawable.icon);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.top_background_gradient));
		

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				 getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
				drawerAdapter.notifyDataSetChanged();
				mDrawerList.smoothScrollBy(-1000, 1000);
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		String extras = getIntent().getStringExtra("startMyLibrary");
		if (extras == null) {
			selectItem(0);
		} else {
			selectItem(10);
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		String extras = intent.getStringExtra("startMyLibrary");
		if (extras == null) {
			selectItem(0);
		} else {
			selectItem(10);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	//	getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
			return super.onOptionsItemSelected(item);
		
	}

	 
	 
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		/*// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		*/
		return super.onPrepareOptionsMenu(menu);
	}

	
	@Override
	protected void onPause() {
		
		super.onPause();
	}


	@Override
	protected void onResume() {
		super.onResume();
		copyAssets();
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
			
		}
	}

	public void selectItem(int position) {
		Fragment fragment = new EBookStoreFragment();

		if (position == 0) {

			boolean isNetworkConnection = Utils.isNetworkAvailable(activity);
			Store store = ((App) getApplication()).getStore();
			if (store != null || isNetworkConnection) {
				fragment = new EBookStoreFragment();

			} else {
				fragment = new EBookNetworkHandlerFragment();

			}
		} else if (position == 1) {
			fragment = new EBookMyLibraryFragment();
			
		/*	Uri uri = Uri.parse("file:///android_asset/and.pdf");
			Intent intent = new Intent(this,MuPDFActivity.class);
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
			return;*/
		} else if (position == 2) {
			fragment = new AccountFragment();
		}

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mFragmentTitlesTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);

	}

	@Override
	public void onBackPressed() {
		try {
			setTitle(mFragmentTitlesTitles[currentPosition]);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		int i = getFragmentManager().getBackStackEntryCount();
		if (i==0) {
			 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						this);
		 
					// set title
					alertDialogBuilder.setTitle(R.string.app_name);
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Do you want to exit ?")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								dialog.cancel();
								finish();
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
		} else {
			super.onBackPressed();
		}
		
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}




	protected void executePendingTransactions() {
		if (pendingFragment == null) {
			return;
		}

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, pendingFragment).commit();

		// update selected item and title, then close the drawer
		setTitle(mFragmentTitlesTitles[pendingPosition]);
		mDrawerLayout.closeDrawer(mDrawerList);
		mDrawerList.smoothScrollToPosition(0);

	}

	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		View currentView = getCurrentFocus();

		boolean retVal = super.dispatchTouchEvent(event);
		if (currentView instanceof EditText) {
			View cView = getCurrentFocus();
			int scrCoordinates[] = new int[2];
			cView.getLocationOnScreen(scrCoordinates);
			float xPos = event.getRawX() + cView.getLeft() - scrCoordinates[0];
			float yPos = event.getRawY() + cView.getTop() - scrCoordinates[1];

			if (event.getAction() == MotionEvent.ACTION_UP
					&& (xPos < cView.getLeft() || xPos >= cView.getRight()
							|| yPos < cView.getTop() || yPos > cView
							.getBottom())) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
						.getWindowToken(), 0);
			}
		}
		return retVal;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}

	public static void registorOrAuthenticate(Activity mActivity,
			String argJSONString, TAListener listener,String url) {
		new TAPOSTWebServiceAsyncTask(
				mActivity,
				null,
				listener,
				WebServiceConstants.BASE_URL + url,
				argJSONString).executeOnExecutor(
				AsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
	}
	private void copyAssets() {
	    AssetManager assetManager = getAssets();
	           InputStream in = null;
	        OutputStream out = null;
	        try {
	          in = assetManager.open("book.pdf");
	          File outFile = getExternalCacheDir();
	          File file = new File(outFile, "book.pdf");
	          out = new FileOutputStream(file);
	          copyFile(in, out);
	          in.close();
	          in = null;
	          out.flush();
	          out.close();
	          out = null;
	          Log.e("tag", "copyed");
	        } catch(IOException e) {
	            Log.e("tag", "Failed to copy asset file: book.pdf "  , e);
	        }       
	    
	}
	private void copyFile(InputStream in, OutputStream out) throws IOException {
	    byte[] buffer = new byte[1024];
	    int read;
	    while((read = in.read(buffer)) != -1){
	      out.write(buffer, 0, read);
	    }
	}
}