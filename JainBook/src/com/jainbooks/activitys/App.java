package com.jainbooks.activitys;

import com.jainbooks.model.Store;

import android.app.Application;

public final class App extends Application {
	private  Store store;
//	private boolean loginStatus;
	@Override
	public void onCreate() {
		super.onCreate();
		
		
	}

	
	@Override
	public void onTerminate() {
		
		super.onTerminate();
	}


	public Store getStore() {
		return store;
	}


	public void setStore(Store store) {
		this.store = store;
	}

/*
	public boolean isLoginStatus() {
		return loginStatus;
	}


	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}*/

}
