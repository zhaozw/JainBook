package com.jainbooks.activitys;

import org.jainbooks.ebook.R;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.jainbooks.model.Login;
import com.jainbooks.utils.NotificationUtils;
import com.jainbooks.utils.SharedPreferencesUtil;
import com.jainbooks.utils.TAListener;
import com.jainbooks.web.TAPOSTWebServiceAsyncTask;
import com.jainbooks.web.WebServiceConstants;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class RegistrationActivity extends Activity {
  
	private EditText editTextLoginUsername;
	private EditText editTextLoginPassword;
	private Activity context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		getActionBar().setIcon(R.drawable.icon);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.color.black));
		getActionBar().setTitle("Registration");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		context=this;
		editTextLoginUsername = (EditText) 
				findViewById(R.id.editTextLoginUsername);
		editTextLoginPassword = (EditText)findViewById(R.id.editTextLoginPassword);
	
		
		
		
	}
	
	
@Override
protected void onResume() {
	
	super.onResume();
	
}
public void register(View view) {


	String username;
	String password;

	
		username = editTextLoginUsername.getText().toString();
		password = editTextLoginPassword.getText().toString();

		if ((username == null || TextUtils.isEmpty(username))
				&& (password == null || TextUtils.isEmpty(password))) {
			NotificationUtils.showNotificationToast(context,
					"Please fill in your Username and Password");
			return;
		} else {
			if (username == null || TextUtils.isEmpty(username)) {
				NotificationUtils.showNotificationToast(context,
						"Please fill in your Username");
				return;
			}

			if (password == null || TextUtils.isEmpty(password)) {
				NotificationUtils.showNotificationToast(context,
						"Please fill in your Password");
				return;
			}
		}

	
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("email", username);
				jsonObject.put("password", password);
				jsonObject.put("loginSource", "direct");
			} catch (JSONException e) {
				e.printStackTrace();
				NotificationUtils.showNotificationToast(context,
						"Please re-enter Username and Password");
			} catch (NullPointerException e) {
				e.printStackTrace();
				NotificationUtils.showNotificationToast(context,
						"Please re-enter Username and Password");
			}
			
            TAListener taListener=new TAListener() {
				
				@Override
				public void onTaskFailed(Bundle argBundle) {
					
				}
				
				@Override
				public void onTaskCompleted(Bundle argBundle) {
					
					
					String responseJSON = argBundle
							.getString(TAListener.LISTENER_BUNDLE_STRING);
					Login login = new Gson()
							.fromJson(responseJSON,
									Login.class);

				if (null != login) {
					
					NotificationUtils
					.showNotificationToast(
							context,
							login.getMessage());
					

				} else {
						NotificationUtils
								.showNotificationToast(
										context,
										"Server not responds");
					}

				
			}
			}; 
			
			DashboardActivity.registorOrAuthenticate(context, jsonObject.toString(), taListener,WebServiceConstants.REGISTRATION);
		
		
	

	

}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			this.onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

}
