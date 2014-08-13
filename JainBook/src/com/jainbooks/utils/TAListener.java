package com.jainbooks.utils;

import android.os.Bundle;

/**
 * This class serves as a listener for UI thread in all asynchronous tasks.
 * 
 * @author Atul Mittal
 * 
 */
public abstract class TAListener {

	public static final String LISTENER_BUNDLE_BOOLEAN = "LISTENER_BUNDLE_BOOLEAN";
	public static final String LISTENER_BUNDLE_STRING = "LISTENER_BUNDLE_STRING";

	/**
	 * To be called by the worker task indicating task completion.
	 * 
	 * @param argBundle
	 */
	public abstract void onTaskCompleted(Bundle argBundle);

	/**
	 * To be called by the worker task indicating task failure.
	 * 
	 * @param argBundle
	 */
	public abstract void onTaskFailed(Bundle argBundle);

}
