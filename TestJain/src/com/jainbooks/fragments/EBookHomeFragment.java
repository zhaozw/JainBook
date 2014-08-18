package com.jainbooks.fragments;


import java.util.List;

import org.jainbooks.ebook.R;

import com.jainbooks.activitys.DashboardActivity;
import com.jainbooks.adapter.EBookHomeFragmentAdapter;
import com.jainbooks.model.Category;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


public class EBookHomeFragment extends BaseFragment {

	
	private ListView listStore;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_home,
				container, false);
		setupUiComponent();

		return view;
	}

	@Override
	void setupUiComponent() {
		listStore = ((ListView) view.findViewById(R.id.listCategory));
		List<Category> categories = dashboardActivity.app.getStore()
				.getAllCategory();
		if (categories != null) {
			listStore
					.setAdapter(new EBookHomeFragmentAdapter(
							dashboardActivity,
							categories));

		}

	}

}
