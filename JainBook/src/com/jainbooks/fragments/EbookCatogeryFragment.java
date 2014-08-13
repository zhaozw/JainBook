package com.jainbooks.fragments;


import org.jainbooks.ebook.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class EbookCatogeryFragment extends BaseFragment {

	private ListView listCategory;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_category,
				container, false);
		setupUiComponent();
		return view;
	}

	@Override
	void setupUiComponent() {
		listCategory = ((ListView) view.findViewById(R.id.listCategory));
		listCategory.setAdapter(new EbookCatogeryFragmentAdapter(getActivity()));
	}

}
