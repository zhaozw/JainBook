package com.jainbooks.fragments;

import java.io.File;
import java.util.List;

import org.jainbooks.ebook.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artifex.mupdfdemo.MuPDFActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jainbooks.activitys.LoginActivity;
import com.jainbooks.adapter.MyLibraryAdapter;
import com.jainbooks.model.EBook;
import com.jainbooks.model.UserLibrary;
import com.jainbooks.utils.NotificationUtils;
import com.jainbooks.utils.SharedPreferencesUtil;
import com.jainbooks.utils.TAListener;
import com.jainbooks.web.TAWebServiceAsyncTask;
import com.jainbooks.web.WebServiceConstants;

@TargetApi(Build.VERSION_CODES.HONEYCOMB) public class EBookMyLibraryFragment extends BaseFragment {
	private GridView listBooks;
    private UserLibrary userLibraries;
    private Button btnLibraryLogin,btnSync;
    private LinearLayout loginContainer;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_mylibrary, null);
		setupUiComponent();
		return view;
	}
@Override
public void onResume() {
	super.onResume();
	final String userString=SharedPreferencesUtil.getPreferences(dashboardActivity, SharedPreferencesUtil.USER, null);
	if (null!=userString) {
		loginContainer.setVisibility(View.GONE);		
		btnSync.setVisibility(View.VISIBLE);
	} else {
		loginContainer.setVisibility(View.VISIBLE);		
		btnSync.setVisibility(View.GONE);
	}
	
	
}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		

			// Fire up API for login
			new TAWebServiceAsyncTask(
					getActivity(),
					null,
					new TAListener() {

						@Override
						public void onTaskFailed(Bundle argBundle) {
							NotificationUtils
									.showNotificationToast(getActivity(),
											"No results found");
						}

						@SuppressLint("NewApi") @Override
						public void onTaskCompleted(Bundle argBundle) {
							String resultJSON = argBundle
									.getString(TAListener.LISTENER_BUNDLE_STRING);

							if (resultJSON != null
									&& !TextUtils.isEmpty(resultJSON)) {
								try {
									userLibraries =  new Gson().fromJson(
											resultJSON,
											UserLibrary.class);
									refreshViews();
								} catch (JsonSyntaxException e) {
									onTaskFailed(null);
								}
							}else{
								TextView error_text = (TextView) view
										.findViewById(R.id.error_text);
								error_text.setVisibility(View.VISIBLE);
								error_text.setText("No videos found");
								listBooks.setVisibility(View.GONE);
							}

						}
					}, WebServiceConstants.BASE_URL
							+ WebServiceConstants.GET_USER_LIBRARY+"nitesh@qa.com",false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
							(Void[]) null);
		
		
	}

	@SuppressLint("NewApi") public void refreshViews() {
		if (userLibraries != null) {
			List<EBook> ebooks=userLibraries.getEbooks();
			listBooks.setAdapter(new MyLibraryAdapter(
					getActivity(), ebooks));
			listBooks
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@SuppressLint("NewApi") @Override
						public void onItemClick(AdapterView<?> arg0, final View arg1,
								final int arg2, long arg3) {
							 File outFile = dashboardActivity.getExternalCacheDir();
					         File file = new File(outFile, "book.pdf");
					          Uri uri = Uri.parse(file.getAbsolutePath());
					 		Intent intent = new Intent(dashboardActivity,MuPDFActivity.class);
					 		intent.setAction(Intent.ACTION_VIEW);
					 		intent.setData(uri);
					 		startActivity(intent);
						}
					});
		}else{
			TextView error_text = (TextView) view
					.findViewById(R.id.error_text);
			error_text.setVisibility(View.VISIBLE);
			error_text.setText("No results found");
			listBooks.setVisibility(View.GONE);
		}
	}

	@SuppressLint("NewApi") @Override
	void setupUiComponent() {
		listBooks = (GridView) view.findViewById(R.id.listBooks);
	
		loginContainer=(LinearLayout)view.findViewById(R.id.loginContainer);
		btnLibraryLogin = (Button) view.findViewById(R.id.btnLibraryLogin);
		btnLibraryLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
              startActivity(new Intent(dashboardActivity, LoginActivity.class));
			}
		});
		btnSync = (Button) view.findViewById(R.id.btnSync);

		btnSync.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

}
