package com.jainbooks.fragments;

import org.jainbooks.ebook.R;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.jainbooks.model.TALoginResponse;
import com.jainbooks.utils.NotificationUtils;
import com.jainbooks.utils.PreferenceStorage;
import com.jainbooks.utils.TAListener;
import com.jainbooks.utils.Utils;
import com.jainbooks.web.TAPOSTWebServiceAsyncTask;
import com.jainbooks.web.TAWebServiceConstants;

public class TALoginFragment extends BaseFragment {
	int mNum;
	private View mFragmentView;
	private EditText editTextLoginUsername;
	private EditText editTextLoginPassword;
	private CheckBox checkBoxSignInRememberMe;
	private ImageButton imageButtonLoginButton;
	private Button buttonLoginForgotPassword;
	private Button buttonLoginSignUp;
	private TAListener mLoginDialogListener;
	private PreferenceStorage prefs;

	public static final String TA_USERNAME_KEY = "TA_USERNAME_KEY";
	public static final String TA_PASSWORD_KEY = "TA_PASSWORD_KEY";

	public TALoginFragment(TAListener loginDialogListener) {
		mLoginDialogListener = loginDialogListener;
	}

	/**
	 * Create a new instance of MyDialogFragment, providing "num" as an
	 * argument.
	 */
	
	public static TALoginFragment newInstance(int num, TAListener loginDialogListener) {
		TALoginFragment f = new TALoginFragment(loginDialogListener);

		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNum = getArguments().getInt("num");
	
		

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mFragmentView = inflater.inflate(R.layout.fragment_login, null);
		setupUiComponent();
		return mFragmentView;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		prefs = new PreferenceStorage(getActivity());

		bindContents();
	}

	private void bindContents() {

		if (!TextUtils.isEmpty(prefs.getData(TA_USERNAME_KEY, ""))) {
			editTextLoginUsername.setText(prefs.getData(TA_USERNAME_KEY, ""));
		}

		if (!TextUtils.isEmpty(prefs.getData(TA_PASSWORD_KEY, ""))) {
			editTextLoginPassword.setText(prefs.getData(TA_PASSWORD_KEY, ""));
			checkBoxSignInRememberMe.setChecked(true);
		}

		imageButtonLoginButton.setOnClickListener(new View.OnClickListener() {
			private String username;
			private String password;

			@Override
			public void onClick(View v) {
				username = editTextLoginUsername.getText().toString();
				password = editTextLoginPassword.getText().toString();

				if ((username == null || TextUtils.isEmpty(username))
						&& (password == null || TextUtils.isEmpty(password))) {
					NotificationUtils.showNotificationToast(getActivity(),
							"Please fill in your Username and Password");
					return;
				} else {
					if (username == null || TextUtils.isEmpty(username)) {
						NotificationUtils.showNotificationToast(getActivity(),
								"Please fill in your Username");
						return;
					}

					if (password == null || TextUtils.isEmpty(password)) {
						NotificationUtils.showNotificationToast(getActivity(),
								"Please fill in your Password");
						return;
					}
				}

				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("username", username);
					jsonObject.put("password", password);
					jsonObject.put("rememeber_me", ""
							+ checkBoxSignInRememberMe.isChecked());

					// Fire up API for login
					new TAPOSTWebServiceAsyncTask(
							getActivity(),
							null,
							new TAListener() {

								@Override
								public void onTaskFailed(Bundle argBundle) {
									System.out.println("argBundle"
											+ argBundle.toString());
								}

								@Override
								public void onTaskCompleted(Bundle argBundle) {/*
									String responseJSON = argBundle
											.getString(TAListener.LISTENER_BUNDLE_STRING);
									Utils.loginData = new Gson()
											.fromJson(responseJSON,
													TALoginResponse.class);

									if (Utils.loginData.getLoginStatus()) {

										if (checkBoxSignInRememberMe
												.isChecked()) {
											prefs.saveData(TA_USERNAME_KEY,
													username);
											prefs.saveData(TA_PASSWORD_KEY,
													password);
										}

										mLoginDialogListener
												.onTaskCompleted(null);
										getDialog().dismiss();
									} else {
										NotificationUtils
												.showNotificationToast(
														getActivity(),
														"Invalid Username/Password");
									}

								*/}
							}, TAWebServiceConstants.TA_API_BASE_URL
									+ TAWebServiceConstants.TA_API_LOGIN,
							jsonObject.toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
									(Void[]) null);
				} catch (JSONException e) {
					e.printStackTrace();
					NotificationUtils.showNotificationToast(getActivity(),
							"Please re-enter Username and Password");
				} catch (NullPointerException e) {
					e.printStackTrace();
					NotificationUtils.showNotificationToast(getActivity(),
							"Please re-enter Username and Password");
				}
			}
		});

		buttonLoginForgotPassword
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {/*
						Bundle b = new Bundle();
						b.putString(
								TAVideoPlayerCommentsFragment.TA_REQ,
								TAVideoPlayerCommentsFragment.TA_FORGOT_PASS_FRAG_REQ);
						mLoginDialogListener.onTaskCompleted(b);

						getDialog().dismiss();
					*/}
				});

		buttonLoginSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {/*
				Bundle b = new Bundle();
				b.putString(TAVideoPlayerCommentsFragment.TA_REQ,
						TAVideoPlayerCommentsFragment.TA_SIGNUP_FRAG_REQ);
				mLoginDialogListener.onTaskCompleted(b);

				getDialog().dismiss();
			*/}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	void setupUiComponent() {

		editTextLoginUsername = (EditText) mFragmentView
				.findViewById(R.id.editTextLoginUsername);
		editTextLoginPassword = (EditText) mFragmentView
				.findViewById(R.id.editTextLoginPassword);
		checkBoxSignInRememberMe = (CheckBox) mFragmentView
				.findViewById(R.id.checkBoxSignInRememberMe);
		imageButtonLoginButton = (ImageButton) mFragmentView
				.findViewById(R.id.imageButtonLoginButton);
		buttonLoginForgotPassword = (Button) mFragmentView
				.findViewById(R.id.buttonLoginForgotPassword);
		buttonLoginSignUp = (Button) mFragmentView
				.findViewById(R.id.buttonLoginSignUp);
	
		
	}
}
