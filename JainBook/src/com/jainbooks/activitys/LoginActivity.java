package com.jainbooks.activitys;

import org.jainbooks.ebook.R;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import com.google.android.gms.plus.model.people.Person;
import com.google.gson.Gson;
import com.jainbooks.model.Login;
import com.jainbooks.model.User;
import com.jainbooks.utils.NotificationUtils;
import com.jainbooks.utils.SharedPreferencesUtil;
import com.jainbooks.utils.TAListener;
import com.jainbooks.web.WebServiceConstants;


public class LoginActivity extends Activity{
  
	private EditText editTextLoginUsername;
	private EditText editTextLoginPassword;
	private CheckBox checkBoxSignInRememberMe;
	private ImageButton imageButtonLoginButton;
	private Button buttonLoginForgotPassword;
	private Button buttonLoginSignUp;
	private Activity context;
	private GraphUser user;
	
	// google+
	
	private SignInButton btnSignIn;
	/*private GoogleApiClient mGoogleApiClient;
	private ConnectionResult mConnectionResult;
	 private static final int RC_SIGN_IN = 0;
	 private boolean mIntentInProgress;
	  private boolean mSignInClicked;*/
	 
	
	
	private static final String TAG = "LoginActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_login);
		getActionBar().setIcon(R.drawable.icon);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.color.black));
		getActionBar().setTitle("Login");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.fragment_login);
		context=this;
		/* uiHelper = new UiLifecycleHelper(context, callback);
		    uiHelper.onCreate(savedInstanceState);*/
		editTextLoginUsername = (EditText) 
				findViewById(R.id.editTextLoginUsername);
		editTextLoginPassword = (EditText)findViewById(R.id.editTextLoginPassword);
		checkBoxSignInRememberMe = (CheckBox) findViewById(R.id.checkBoxSignInRememberMe);
		imageButtonLoginButton = (ImageButton) findViewById(R.id.imageButtonLoginButton);
		buttonLoginForgotPassword = (Button) findViewById(R.id.buttonLoginForgotPassword);
		buttonLoginSignUp = (Button)findViewById(R.id.buttonLoginSignUp);

        LoginButton loginButton = (LoginButton) findViewById(R.id.authButton);
        loginButton.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
                LoginActivity.this.user = user;
                updateUI();
            
            }
        });
           btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
           for (int i = 0; i < btnSignIn.getChildCount(); i++) {
               View v = btnSignIn.getChildAt(i);

               if (v instanceof TextView) {
                   TextView tv = (TextView) v;
                   tv.setText("Log in with Google");
                   return;
               }
           }
    /*    btnSignIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (!mGoogleApiClient.isConnecting()) {
			        mSignInClicked = true;
			        resolveSignInError();
			    }
			}
		});*/
         
      /*   mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this).addApi(Plus.API, null)
        .addScope(Plus.SCOPE_PLUS_LOGIN).build();*/
	}
	/*private void resolveSignInError() {
	    if (mConnectionResult.hasResolution()) {
	        try {
	            mIntentInProgress = true;
	            mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
	        } catch (SendIntentException e) {
	            mIntentInProgress = false;
	            mGoogleApiClient.connect();
	        }
	    }
	}*/
	 private void updateUI() {
	        Session session = Session.getActiveSession();
	        boolean enableButtons = (session != null && session.isOpened());

	        if (enableButtons &&user != null) {
	           
	        	
		       	final String email=(String)user.getProperty("email");
		      
	        	
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("email", email);
				jsonObject.put("loginSource", "fb");
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
				
					Gson gson=new Gson();
					String responseJSON = argBundle
							.getString(TAListener.LISTENER_BUNDLE_STRING);
					
					Login login =gson
							.fromJson(responseJSON,
									Login.class);

				if (null != login) {
					if (login.getStatusCode().equalsIgnoreCase(
							"SUCCESS_003")) {
						User mUser=new User();
				       	mUser.setName(user.getFirstName());
				       	mUser.setLoginFrom("Facebook");
				       	mUser.setEmail(email);
						String userString=new Gson().toJson(mUser);
						SharedPreferencesUtil.savePreferences(context, SharedPreferencesUtil.USER, userString);
					
						
					}
					NotificationUtils
					.showNotificationToast(
							context,
							login.getMessage());
					finish();

				} else {
						NotificationUtils
								.showNotificationToast(
										context,
										"Server not responds");
					}

				
			}
			}; 
			
			DashboardActivity.registorOrAuthenticate(context, jsonObject.toString(), taListener,WebServiceConstants.AUTHENTICATE);
		

	        }
	    }
@Override
public void onResume() {
    super.onResume();
    bindContents();
  
}

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
   /* if (requestCode == RC_SIGN_IN) {
        if (resultCode != RESULT_OK) {
            mSignInClicked = false;
        }
 
        mIntentInProgress = false;
 
        if (!mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }*/
}

protected void onStart() {
    super.onStart();
   // mGoogleApiClient.connect();
}

protected void onStop() {
    super.onStop();
   /* if (mGoogleApiClient.isConnected()) {
        mGoogleApiClient.disconnect();
    }*/
}

@Override
public void onPause() {
    super.onPause();
  
}

@Override
public void onDestroy() {
    super.onDestroy();
  
}

@Override
public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
   
}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case android.R.id.home:
			this.onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private void bindContents() {
		
		String userName=SharedPreferencesUtil.getPreferences(context, SharedPreferencesUtil.USER_NAME, "");
		String password=SharedPreferencesUtil.getPreferences(context, SharedPreferencesUtil.PASSWORD, "");
		
		if (!TextUtils.isEmpty(userName)) {
			editTextLoginUsername.setText(userName);
		}

		if (!TextUtils.isEmpty(password)) {
			editTextLoginPassword.setText(password);
			checkBoxSignInRememberMe.setChecked(true);
		}

		imageButtonLoginButton.setOnClickListener(new View.OnClickListener() {
			private String username;
			private String password;

			@Override
			public void onClick(View v) {
				
				username = editTextLoginUsername.getText().toString();
				password = editTextLoginPassword.getText().toString();
				
				if (checkBoxSignInRememberMe.isChecked()) {
					SharedPreferencesUtil.savePreferences(context, SharedPreferencesUtil.USER_NAME, username);
					SharedPreferencesUtil.savePreferences(context, SharedPreferencesUtil.PASSWORD, password);
				
				}
			
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
						
							Gson gson=new Gson();
							String responseJSON = argBundle
									.getString(TAListener.LISTENER_BUNDLE_STRING);
							
							Login login =gson
									.fromJson(responseJSON,
											Login.class);

						if (null != login) {
							if (login.getStatusCode().equalsIgnoreCase(
									"SUCCESS_003")) {
								User user=new User();
								user.setName(username);
								user.setLoginFrom("JainBooks");
								String userString=gson.toJson(user);
								SharedPreferencesUtil.savePreferences(context, SharedPreferencesUtil.USER, userString);
								
							}
							NotificationUtils
							.showNotificationToast(
									context,
									login.getMessage());
							finish();

						} else {
								NotificationUtils
										.showNotificationToast(
												context,
												"Server not responds");
							}

						
					}
					}; 
					
					DashboardActivity.registorOrAuthenticate(context, jsonObject.toString(), taListener,WebServiceConstants.AUTHENTICATE);
				
				
			}
		});

		buttonLoginForgotPassword
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
					}
				});

		buttonLoginSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(context, RegistrationActivity.class));
				
			}
		});
	}
	/*private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	        Log.i(TAG, "Logged in...");
	        
	        
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	    }
	}*/

	/*@Override
	public void onConnected(Bundle arg0) {
		mSignInClicked = false;
	    Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
	 
	   // getProfileInformation();
	    updateUI(true);
	}

	 
	@Override
	public void onDisconnected() {
		mGoogleApiClient.connect();
	    updateUI(false);
		
	}

	private void updateUI(boolean isSignedIn) {
		if (isSignedIn) {
			btnSignIn.setOnClickListener(null);
		}
	}
	@Override
	public void onConnectionFailed(ConnectionResult result) {
		 if (!result.hasResolution()) {
		        GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
		                0).show();
		        return;
		    }
		 
		    if (!mIntentInProgress) {
		        // Store the ConnectionResult for later usage
		        mConnectionResult = result;
		 
		        if (mSignInClicked) {
		            // The user has already clicked 'sign-in' so we attempt to
		            // resolve all
		            // errors until the user is signed in, or they cancel.
		            resolveSignInError();
		        }
		    }
	}
	*/
}
