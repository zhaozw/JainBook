package com.jainbooks.fragments;

import java.util.List;

import org.jainbooks.ebook.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jainbooks.adapter.MyLibraryAdapter;
import com.jainbooks.model.EBook;
import com.jainbooks.model.UserLibrary;
import com.jainbooks.utils.NotificationUtils;
import com.jainbooks.utils.TAListener;
import com.jainbooks.utils.Utils;
import com.jainbooks.web.TAPOSTWebServiceAsyncTask;
import com.jainbooks.web.TAWebServiceAsyncTask;
import com.jainbooks.web.WebServiceConstants;

public class EBookMyLibraryFragment extends BaseFragment {
	private ListView listBooks;
    private UserLibrary userLibraries;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_mylibrary, null);
		listBooks = (ListView) view
				.findViewById(R.id.listBooks);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		

			// Fire up API for login
			new TAWebServiceAsyncTask(
					getActivity(),
					null,
					new TAListener() {

						@Override
						public void onTaskFailed(Bundle argBundle) {
							NotificationUtils
									.showNotificationToast(getActivity(),
											"No results found");
						}

						@Override
						public void onTaskCompleted(Bundle argBundle) {
							String resultJSON = argBundle
									.getString(TAListener.LISTENER_BUNDLE_STRING);

							if (resultJSON != null
									&& !TextUtils.isEmpty(resultJSON)) {
								try {
									userLibraries =  new Gson().fromJson(
											resultJSON,
											UserLibrary.class);
									refreshViews();
								} catch (JsonSyntaxException e) {
									onTaskFailed(null);
								}
							}else{
								TextView error_text = (TextView) view
										.findViewById(R.id.error_text);
								error_text.setVisibility(View.VISIBLE);
								error_text.setText("No videos found");
								listBooks.setVisibility(View.GONE);
							}

						}
					}, WebServiceConstants.BASE_URL
							+ WebServiceConstants.GET_USER_LIBRARY+"nitesh@qa.com",false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
							(Void[]) null);
		
		
	}

	public void refreshViews() {
		if (userLibraries != null) {
			List<EBook> ebooks=userLibraries.getEbooks();
			listBooks.setAdapter(new MyLibraryAdapter(
					getActivity(), ebooks));
			listBooks
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, final View arg1,
								final int arg2, long arg3) {
							
						}
					});
		}else{
			TextView error_text = (TextView) view
					.findViewById(R.id.error_text);
			error_text.setVisibility(View.VISIBLE);
			error_text.setText("No results found");
			listBooks.setVisibility(View.GONE);
		}
	}

	@Override
	void setupUiComponent() {
		// TODO Auto-generated method stub
		
	}

}
