package com.jainbooks.web;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.jainbooks.utils.JSONParser;
import com.jainbooks.utils.NotificationUtils;
import com.jainbooks.utils.TAListener;
import com.jainbooks.utils.Utils;

/**
 * Custom AsyncTask responsible for encryption, API request/response handling.
 * 
 * @author Optimus
 * 
 */
public class TAWebServiceAsyncTask extends AsyncTask<Void, Void, String> {

	private TAListener mListener;
	private Activity mActivity;
	private String mRequestURL;
	private boolean mVerifyJSONObject;
	private boolean mShowDialog;
	private Bundle mArgBundle;

	public TAWebServiceAsyncTask(Activity argActivity, Bundle argBundle,
			TAListener argListener, String requestURL, boolean verifyJSONObject) {
		mActivity = argActivity;
		mListener = argListener;
		mRequestURL = requestURL;
		mVerifyJSONObject = verifyJSONObject;
		mArgBundle = argBundle;
		mShowDialog = true;
		if (argBundle != null) {
			try {
				mShowDialog = argBundle.getBoolean("showDialog");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		// Check Internet access
		if (!Utils.isNetworkAvailable(mActivity)) {
			NotificationUtils.showNotificationToast(mActivity,
					"Internet connection is required to proceed.");
			cancel(true);
			return;
		}

		if (mShowDialog) {
			// Show loading progress dialog in the beginning of AsyncTask.
			NotificationUtils.showProgressDialog(mActivity, "Please wait",
					"Loading..");
		}
	}

	@Override
	protected String doInBackground(Void... arg0) {
		String responseJson = null;

		try {
			System.out.println("request: " + mRequestURL);
			responseJson = new JSONParser().getJSONFromUrl(mRequestURL);
			System.out.println("" + responseJson);
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return responseJson;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		try {
			// Dismiss all progress dialog on task complete
			if(mShowDialog)
			NotificationUtils.dismissProgressDialog();

			if (mVerifyJSONObject) {
				// Validating JSON response
				/*try {
				//	new Gson().fromJson(result, TARecentVideosModel.class);
					// If this goes by without exceptions, the data received can
					// be
					// confirmed to be in JSON format.
				} catch (JsonParseException e) {
					// If an exception is caught, dump the response and end task
					// with failure.
					e.printStackTrace();
					Bundle returnData = new Bundle();
					returnData.putString(TAListener.LISTENER_BUNDLE_STRING, "");
					mListener.onTaskFailed(returnData);
					return;
				}*/
			}
			// JSON validated, check for content availability and respond to
			// caller accordingly.
			Bundle returnData = new Bundle();
			returnData.putString(TAListener.LISTENER_BUNDLE_STRING, result);
			if (result != null || !TextUtils.isEmpty(result)) {
				mListener.onTaskCompleted(returnData);
			} else {
				mListener.onTaskFailed(returnData);
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
			Bundle returnData = new Bundle();
			returnData.putString(TAListener.LISTENER_BUNDLE_STRING, "");
			mListener.onTaskFailed(returnData);
		}
	}
}
