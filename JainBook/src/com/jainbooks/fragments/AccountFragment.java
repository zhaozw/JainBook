package com.jainbooks.fragments;


import org.jainbooks.ebook.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.Session;
import com.google.gson.Gson;
import com.jainbooks.activitys.App;
import com.jainbooks.activitys.LoginActivity;
import com.jainbooks.model.Login;
import com.jainbooks.model.User;
import com.jainbooks.utils.SharedPreferencesUtil;


public class AccountFragment extends BaseFragment {

	TextView loginStatus;
	Button loginLogout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_account,
				container, false);
		loginStatus=(TextView)view.findViewById(R.id.loginStatus);
		loginLogout=(Button)view.findViewById(R.id.loginLogout);
		

		return view;
	}

	@Override
	public void onResume() {
		setupUiComponent();
		super.onResume();
	}
	@Override
	void setupUiComponent() {
		
		final String userString=SharedPreferencesUtil.getPreferences(dashboardActivity, SharedPreferencesUtil.USER, null);
		User user=null;
		if (null!=userString) {
			
			 user=new Gson().fromJson(userString, User.class);
			loginLogout.setText("Logout");
			loginStatus.setText("Online "+user.getName()+"("+user.getLoginFrom()+")");
		} else {
			loginLogout.setText("Login");
			loginStatus.setText("Offline ");
		}
		loginLogout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			if (null==userString) {
				startActivity(new Intent(dashboardActivity, LoginActivity.class));	
			}else {
				SharedPreferencesUtil.deletePreferences(dashboardActivity, SharedPreferencesUtil.USER);
				setupUiComponent();
				Session session = Session.getActiveSession();
			    if (session != null) {

			        if (!session.isClosed()) {
			            session.closeAndClearTokenInformation();
			            //clear your preferences if saved
			        }
			    } else {

			        session = new Session(dashboardActivity);
			        Session.setActiveSession(session);

			        session.closeAndClearTokenInformation();
			            //clear your preferences if saved

			    }
			}	
			
			}
		});

	}

}
