package com.jainbooks.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Utility class to display in-app notifications.
 * 
 * @author Atul Mittal
 * 
 */
public class NotificationUtils {

	// Only one object for progress dialog will be required in complete
	// application.
	private static ProgressDialog mProgressDialog;

	/**
	 * This method shows the application toast messages
	 * 
	 * @param argContext
	 *            Context of the active activity instance.
	 * @param argDisplayText
	 *            Text to be displayed in the notification.
	 */
	public static void showNotificationToast(Context argContext,
			String argDisplayText) {
		try {
			Toast.makeText(argContext, argDisplayText, Toast.LENGTH_LONG)
					.show();
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
	}

	/**
	 * Shows an indeterminate progress dialog.
	 * 
	 * @param argContext
	 *            Context of the active activity instance.
	 * @param argTitle
	 *            Title of the dialog box.
	 * @param argMessage
	 *            Message to be displayed in the dialog box.
	 */
	public static void showProgressDialog(Context argContext, String argTitle,
			String argMessage) {

		dismissProgressDialog();

		if (argContext != null)
			mProgressDialog = ProgressDialog.show(argContext, null, ""
					+ argMessage, true, false);
	}

	/**
	 * Dismisses open (if any) progress dialog box.
	 */
	public static void dismissProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

}
