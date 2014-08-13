package com.jainbooks.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

	public static ArrayList<String> languages = new ArrayList<String>();

	public static boolean hqMode = false;

	public static boolean downloadOnWifi = false;

	//public static TALoginResponse loginData = null;


	public static String APPBOOSTER_API_KEY = "B88C57C2EAE50CB80D6E7CC7906348C2";
	
	public static String FLURRY_API_KEY = "YZ7MR6ZN2MNSC389BV26";
	
	
	public static void initUserSettings(Activity argActivity) {
		PreferenceStorage prefs = new PreferenceStorage(argActivity);
		hqMode = prefs.getData("hqmode", false);
		downloadOnWifi = prefs.getData("downloadonwifi", false);

		languages = new ArrayList<String>();

		String savedlanguages = prefs.getData("languages", "");

		if (savedlanguages.length() > 0) {

			String[] savedLanguagesArray = savedlanguages.split(",");
			List<String> Languagelist = Arrays.asList(savedLanguagesArray);
			languages.addAll(Languagelist);
		}
		/*if (languages.isEmpty()) {
			languages.add("1");
		}*/
	}

	public static void saveUserSettings(Activity argActivity) {
		PreferenceStorage prefs = new PreferenceStorage(argActivity);
		prefs.saveData("hqmode", hqMode);
		prefs.saveData("downloadonwifi", downloadOnWifi);

		String savelanguages = "";
		for (String language : languages) {
			savelanguages = savelanguages + language + ",";
		}

		prefs.saveData("languages",
				savelanguages.substring(0, savelanguages.length() - 1));

		initUserSettings(argActivity);
	}

	/**
	 * Utility method to check Internet connection availability
	 * 
	 * @return boolean value indicating the presence of Internet connection
	 *         availability
	 */
	public static boolean isNetworkAvailable(Activity argActivity) {
		if (argActivity == null) {
			return false;
		}

		ConnectivityManager connectivityManager;
		NetworkInfo activeNetworkInfo = null;
		try {
			connectivityManager = (ConnectivityManager) argActivity
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return activeNetworkInfo != null;
	}

	/**
	 * Validates an email based on regex -
	 * "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
	 * "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 * 
	 * @param email
	 *            String containing email address
	 * @return True if the email is valid; false otherwise.
	 */
	public static boolean isEmailValid(String email) {
		boolean isValid = false;
		try {
			// Initialize reg ex for email.
			String expression = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			CharSequence inputStr = email;
			// Make the comparison case-insensitive.
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches()) {
				isValid = true;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return isValid;
	}

	/**
	 * Show device native message composer application.
	 * 
	 * @param argContext
	 * @param argEmail
	 *            email address. Currently only support one
	 * @param argSubject
	 *            email subject
	 * @param argBody
	 *            email body
	 */
	public static void showEmailComposer(Context argContext, String argEmail,
			String argSubject, String argBody) {
		try {
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.setType("plain/text");
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
					new String[] { argEmail });
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
					argSubject);
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, argBody);
			argContext
					.startActivity(Intent.createChooser(emailIntent, "Share via"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}
}
