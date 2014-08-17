package com.jainbooks.fragments;

import java.util.List;

import org.jainbooks.ebook.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jainbooks.activitys.DashboardActivity;
import com.jainbooks.adapter.EbookCatogeryFragmentAdapter;
import com.jainbooks.model.Category;
import com.jainbooks.model.Store;

public class EbookCatogeryFragment extends BaseFragment {

	private ListView listCategory;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_category, container, false);
		setupUiComponent();
		return view;
	}

	@Override
	void setupUiComponent() {
		listCategory = ((ListView) view.findViewById(R.id.listCategory));
		
		Store store = null;
	
			store = dashboardActivity.app.getStore();
	
		
		List<Category> categories =store.getAllCategory();
		if (categories != null) {
			listCategory
					.setAdapter(new EbookCatogeryFragmentAdapter(
							dashboardActivity,
							categories));

		}

	}

}
