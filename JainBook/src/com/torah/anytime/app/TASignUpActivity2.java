package com.torah.anytime.app;

import android.app.Activity;

public class TASignUpActivity2 extends Activity {/*
	private TextView textViewSignupRequiredFields;
	private LinearLayout linearLayoutAddnLayout;
	private List<TARegistrationFormResponseModel> mSignUpRegFormData;
	private LayoutInflater inflater;
	private RadioGroup radioSignUp;
	private LinearLayout linearLayoutAddnLayout2;

	private JSONArray mSignUpJsonArray;
	private String[] optionExpandStringArray;
	private JSONObject expandObject;

	// ArrayList<View> regViews = new ArrayList<View>();
	private ImageButton imageButtonSubmit;
	private String type;
	private View newView;
	private LinearLayout linearLayoutSignUp;

	JSONObject jsonReturnObject = new JSONObject();
	private TextView textViewSignupSignIn;
//	private TextView textViewSignupLater;
	private String value;
	private String label;
	private Spinner mStateSpinner;
	private TextView mStateTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_signup2);
		FlurryAgent.logEvent("Sign Up");
		new TAWebServiceAsyncTask(TASignUpActivity2.this, null,
				new TAListener() {

					@Override
					public void onTaskFailed(Bundle argBundle) {
						NotificationUtils
								.showNotificationToast(TASignUpActivity2.this,
										"Failed to fetch registration form. Please try again later.");
						TASignUpActivity2.this.finish();
					}

					@Override
					public void onTaskCompleted(Bundle argBundle) {
						try {
							String responseJSON = argBundle
									.getString(TAListener.LISTENER_BUNDLE_STRING);
							Type listType = new TypeToken<List<TARegistrationFormResponseModel>>() {
							}.getType();
							mSignUpRegFormData = new Gson().fromJson(
									responseJSON, listType);
							try {
								mSignUpJsonArray = new JSONArray(responseJSON);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							bindContents();
						} catch (NullPointerException e) {
							e.printStackTrace();
							onTaskFailed(null);
						}
					}

				}, TAWebServiceConstants.TA_API_BASE_URL
						+ TAWebServiceConstants.TA_API_GET_SIGNUP_FORM, false)
				.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
						(Void[]) null);;

		initVar();

		getActionBar().setIcon(R.drawable.logo);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.actionbar_bg));
		getActionBar().setTitle("Register");

	}

	private void initVar() {
		linearLayoutSignUp = (LinearLayout) findViewById(R.id.linearLayoutSignUp);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		linearLayoutAddnLayout = (LinearLayout) findViewById(R.id.linearLayoutAddnLayout);
		textViewSignupRequiredFields = (TextView) findViewById(R.id.textViewSignupRequiredFields);
		setTextViewMandatory(textViewSignupRequiredFields);
		imageButtonSubmit = (ImageButton) findViewById(R.id.imageButtonSubmit);
		textViewSignupSignIn = (TextView) findViewById(R.id.textViewSignupSignIn);
//		textViewSignupLater = (TextView) findViewById(R.id.textViewSignupLater);
	}

	private void bindContents() {
		try {
			for (int i = 0; i < mSignUpJsonArray.length(); i++) {
				JSONObject currentObject;
				currentObject = mSignUpJsonArray.getJSONObject(i);

				type = currentObject.getString("type");
				type = type.trim();

				value = currentObject.getString("value");
				
				label = currentObject.getString("Label");

				newView = null;
				if (type.equalsIgnoreCase("text")
						|| type.equalsIgnoreCase("email")
						|| type.equalsIgnoreCase("password")) {
					newView = inflater.inflate(R.layout.signup_item_textbox,
							null);
					newView.setTag(type);

					EditText editText = ((EditText) newView
							.findViewById(R.id.editTextSignUp));
					if (type.equalsIgnoreCase("password")) {
						editText.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
					} else if (type.equalsIgnoreCase("email")) {
						editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
					}
					editText.setText("" + value);

				} else if (type.equalsIgnoreCase("radio")) {
					newView = inflater
							.inflate(R.layout.signup_item_radio, null);
					radioSignUp = (RadioGroup) newView
							.findViewById(R.id.radioSignUp);
					radioSignUp.setOrientation(RadioGroup.VERTICAL);

					JSONArray optionsArray = currentObject
							.getJSONArray("Options");
					for (int j = 0; j < optionsArray.length(); j++) {
						RadioButton radioButton = new RadioButton(
								TASignUpActivity2.this);

						radioButton.setText(optionsArray.getString(j));

						if (value.equalsIgnoreCase(optionsArray.getString(j))) {
							radioButton.setSelected(true);
						}

						radioSignUp.addView(radioButton);
					}
					newView.setTag(type);
				} else if (type.equalsIgnoreCase("dropdown")) {
					newView = inflater.inflate(R.layout.signup_item_dropdown,
							null);
					Spinner spinner = (Spinner) newView
							.findViewById(R.id.spinnerSignUp);

					JSONArray optionArray = currentObject
							.getJSONArray("Option");
					String[] optionStringArray = new String[optionArray
							.length()];

					int selectedOption = 0;
					for (int j = 0; j < optionArray.length(); j++) {
						if (optionArray.getString(j).equalsIgnoreCase(value)) {
							selectedOption = j;
						}

						optionStringArray[j] = optionArray.getString(j);
					}

					ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
							this,
							android.R.layout.simple_spinner_dropdown_item,
							optionStringArray);
					spinner.setAdapter(spinnerArrayAdapter);

					if (currentObject.getString("Label").equalsIgnoreCase(
							"state"))
						mStateSpinner = spinner;
					
					if (currentObject.getString("Label").equalsIgnoreCase(
							"country"))
						spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								try {
									TextView text1 = (TextView) arg1
											.findViewById(android.R.id.text1);

									if (text1.getText().toString()
											.contains("America")) {
										setStateOptional(false);
									} else {
										setStateOptional(true);
									}
								} catch (NullPointerException e) {
									e.printStackTrace();
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub
							}
						});
						
					spinner.setSelection(selectedOption);

					newView.setTag(type);

				}else if (type.equalsIgnoreCase("expandableDropdown")) {
					newView = inflater.inflate(R.layout.signup_item_dropdown,
							null);
					Spinner spinner = (Spinner) newView
							.findViewById(R.id.spinnerSignUp);
					linearLayoutAddnLayout2 = (LinearLayout) newView
							.findViewById(R.id.linearLayoutAddnLayout2);

					JSONArray optionArray = currentObject
							.getJSONArray("Option");
					optionExpandStringArray = new String[optionArray.length() - 1];
					expandObject = currentObject.getJSONObject("expand");
					for (int j = 0; j < optionArray.length() - 1; j++) {
						optionExpandStringArray[j] = optionArray.getString(j);
					}

					ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
							this,
							android.R.layout.simple_spinner_dropdown_item,
							optionExpandStringArray);

					spinner.setAdapter(spinnerArrayAdapter);
					spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							try {
								linearLayoutAddnLayout2.removeAllViews();

								String selectedOption = optionExpandStringArray[arg2];

								Gson gson = new Gson();
								JsonElement selectedExpandObject = gson
										.fromJson(expandObject
												.getString(selectedOption
														.trim()),
												JsonElement.class);

								if (selectedExpandObject.isJsonArray()) {
									JSONArray jsonArray = new JSONArray(
											selectedExpandObject.toString());

									for (int i = 0; i < jsonArray.length(); i++) {
										JSONObject subJSONObject = new JSONObject(
												jsonArray.getJSONObject(i)
														.toString());
										View newSubView = inflater.inflate(
												R.layout.signup_item_textbox,
												null);
										addViewToLayout(
												newSubView,
												subJSONObject.getString(
														"required")
														.equalsIgnoreCase(
																"true"),
												subJSONObject
														.getString("Label"),
												linearLayoutAddnLayout2);
										newSubView.setTag("text");
									}
								} else {
									JSONObject subJSONObject = new JSONObject(
											selectedExpandObject.toString());
									View newSubView = inflater.inflate(
											R.layout.signup_item_textbox, null);

									addViewToLayout(newSubView, subJSONObject
											.getString("required")
											.equalsIgnoreCase("true"),
											subJSONObject.getString("Label"),
											linearLayoutAddnLayout2);
									newSubView.setTag("text");
									// regViews.add(newView);
								}

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
						}
					});

					newView.setTag(type);
					// regViews.add(newView);
				}
				if (newView == null) {
					continue;
				}

				String required = currentObject.getString("required");
				boolean boolRequired = required.equalsIgnoreCase("true");
				setUpTextViewLabel(newView, boolRequired,
						currentObject.getString("Label"));

				linearLayoutAddnLayout.addView(newView);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		imageButtonSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					ViewGroup parent = linearLayoutSignUp;

					recursiveLoopChildren(parent);

					if (Utils.loginData != null
							&& Utils.loginData.getLoginStatus()) {
						jsonReturnObject.put(
								TAWebServiceConstants.TA_API_USER_ID_FIELD, ""
										+ Utils.loginData.getLogin().getID());
					}
					jsonReturnObject.accumulate(
							TAWebServiceConstants.TA_API_LANGUAGE_FIELD,
							new JSONArray(Utils.languages));

					// Fire up API for login
					new TAPOSTWebServiceAsyncTask(
							TASignUpActivity2.this,
							null,
							new TAListener() {

								@Override
								public void onTaskFailed(Bundle argBundle) {
									String resultJSON = argBundle
											.getString(TAListener.LISTENER_BUNDLE_STRING);
									NotificationUtils.showNotificationToast(
											TASignUpActivity2.this, ""
													+ resultJSON);
								}

								@Override
								public void onTaskCompleted(Bundle argBundle) {
									String resultJSON = argBundle
											.getString(TAListener.LISTENER_BUNDLE_STRING);

									if (resultJSON != null
											&& !TextUtils.isEmpty(resultJSON)) {
										try {
											JSONObject object = new JSONObject(
													resultJSON);
											String error = object
													.getString("error");

											if (error != null) {
												NotificationUtils
														.showNotificationToast(
																TASignUpActivity2.this,
																error);
											}

										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

										try {
											JSONObject object = new JSONObject(
													resultJSON);
											String error = object
													.getString("message");

											if (error != null) {
												NotificationUtils
														.showNotificationToast(
																TASignUpActivity2.this,
																error);
												TASignUpActivity2.this.finish();
											}

										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}

								}
							},
							TAWebServiceConstants.TA_API_BASE_URL
									+ TAWebServiceConstants.TA_API_GET_SIGNUP_FORM,
							jsonReturnObject.toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
									(Void[]) null);
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}

			}
		});

		textViewSignupSignIn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				TAMainActivity.showLoginFragment = true;
				TASignUpActivity2.this.finish();

			}
		});

//		textViewSignupLater.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				TASignUpActivity2.this.finish();
//
//			}
//		});
	}
	
	protected void setStateOptional(boolean b) {
		if (b) {
			String textString = mStateTextView.getText().toString();
			mStateTextView.setText(textString.replace("*", ""));
			mStateSpinner.setEnabled(false);
		} else {
			String textString = mStateTextView.getText().toString();
			
			if (!textString.contains("*")) {
				setTextViewMandatory(mStateTextView);
			}

			mStateSpinner.setEnabled(true);
		}
	}

	public void recursiveLoopChildren(ViewGroup parent) throws JSONException {

		for (int i = parent.getChildCount() - 1; i >= 0; i--) {
			final View child = parent.getChildAt(i);
			if (child instanceof ViewGroup) {
				recursiveLoopChildren((ViewGroup) child);

				String tag = (String) child.getTag();

				if (tag != null) {
					if (tag.equalsIgnoreCase("text")
							|| tag.equalsIgnoreCase("email")
							|| tag.equalsIgnoreCase("password")) {
						String question = ((TextView) child
								.findViewById(R.id.textViewSignup)).getText()
								.toString();
						String answer = ((EditText) child
								.findViewById(R.id.editTextSignUp)).getText()
								.toString();
						jsonReturnObject.put(
								question.replace("*", "").replace(" ", "")
										.replace("?", "").toLowerCase(), ""
										+ answer);
					} else if (tag.equalsIgnoreCase("radio")) {
						String question = ((TextView) child
								.findViewById(R.id.textViewSignup)).getText()
								.toString();
						String answer = "";
						RadioGroup radioGroup = ((RadioGroup) child
								.findViewById(R.id.radioSignUp));

						for (int j = radioGroup.getChildCount() - 1; j >= 0; j--) {
							View childradio = radioGroup.getChildAt(j);

							if (childradio != null) {
								RadioButton radio = (RadioButton) radioGroup
										.getChildAt(j);
								if (radio.isChecked()) {
									answer = radio.getText().toString();
									break;
								}
							}
						}
						jsonReturnObject.put(
								question.replace("*", "").replace(" ", "")
										.replace("?", "").toLowerCase(), ""
										+ answer);
					} else if (tag.equalsIgnoreCase("dropdown")) {
						String question = ((TextView) child
								.findViewById(R.id.textViewSignup)).getText()
								.toString();
						String answer = ((Spinner) child
								.findViewById(R.id.spinnerSignUp))
								.getSelectedItem().toString();
						jsonReturnObject.put(
								question.replace("*", "").replace(" ", "")
										.replace("?", "").toLowerCase(), ""
										+ answer);
					} else if (tag.equalsIgnoreCase("expandableDropdown")) {
						String question = ((TextView) child
								.findViewById(R.id.textViewSignup)).getText()
								.toString();
						String answer = ((Spinner) child
								.findViewById(R.id.spinnerSignUp))
								.getSelectedItem().toString();
						jsonReturnObject.put(
								question.replace("*", "").replace(" ", "")
										.replace("?", "").toLowerCase(), ""
										+ answer);
					}
				}

			} else {
				if (child != null) {

				}
			}
		}
	}

	void addViewToLayout(View argView, boolean isMandatory, String textLabel,
			LinearLayout targetView) {
		setUpTextViewLabel(argView, isMandatory, textLabel);
		targetView.addView(argView);
	}

	void setUpTextViewLabel(View argView, boolean isMandatory, String textLabel) {
		TextView textView = (TextView) argView
				.findViewById(R.id.textViewSignup);
		textView.setText(textLabel);

		if (textLabel.equalsIgnoreCase("state")) {
			mStateTextView = textView;
		}
		
		if (isMandatory) {
			setTextViewMandatory(textView);
		}
	}

	void setTextViewMandatory(TextView argTextView) {
		String simple = argTextView.getText().toString();
		String colored = "*";
		SpannableStringBuilder builder = new SpannableStringBuilder();

		builder.append(simple);
		int start = builder.length();
		builder.append(colored);
		int end = builder.length();

		builder.setSpan(new ForegroundColorSpan(Color.RED), start, end,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		argTextView.setText(builder);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		final Menu m = menu;
		final MenuItem item = menu.findItem(R.id.action_donate);
		item.getActionView().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				m.performIdentifierAction(item.getItemId(), 0);
			}
		});

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_donate:
			Intent donateIntent = new Intent(TASignUpActivity2.this,TADonateActivity.class);
			startActivity(donateIntent);
			return true;
		case android.R.id.home:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		FlurryAgent.onStartSession(this, Utils.FLURRY_API_KEY);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		FlurryAgent.onEndSession(this);
	}

*/}
