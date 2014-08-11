package com.torah.anytime.fragments;


import org.jainbooks.ebook.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Temporary placeholder/reference
 * 
 * @author Atul Mittal
 * 
 */
public class EbookCatogeryFragment extends Fragment {

	private ListView listCategory;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_category,
				container, false);
		listCategory = ((ListView) contentView.findViewById(R.id.listCategory));
		listCategory.setAdapter(new EbookCatogeryFragmentAdapter(getActivity()));
		return contentView;
	}

}
