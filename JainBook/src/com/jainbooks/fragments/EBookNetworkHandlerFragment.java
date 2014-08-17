package com.jainbooks.fragments;

import org.jainbooks.ebook.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.jainbooks.utils.NotificationUtils;
import com.jainbooks.utils.Utils;

public class EBookNetworkHandlerFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_network, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		setupUiComponent();
	}

	@Override
	void setupUiComponent() {
		dashboardActivity.getActionBar().setIcon(R.drawable.icon);
		dashboardActivity.getActionBar().setDisplayHomeAsUpEnabled(true);
		dashboardActivity.getActionBar().setHomeButtonEnabled(true);
		dashboardActivity.getActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.actionbar_bg));
		dashboardActivity.getActionBar().setTitle("Network");

		ImageButton buttonNetworkRetry = (ImageButton) view
				.findViewById(R.id.buttonNetworkRetry);
		ImageButton buttonGoToMyLibrary = (ImageButton) view
				.findViewById(R.id.buttonGoToMyLibrary);

		buttonNetworkRetry.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (Utils.isNetworkAvailable(dashboardActivity)) {

					dashboardActivity.selectItem(0);

				} else {
					NotificationUtils.showNotificationToast(dashboardActivity,
							"No network connection.");
				}
			}
		});

		buttonGoToMyLibrary.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});

	}

}
