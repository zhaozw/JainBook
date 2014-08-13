package com.jainbooks.fragments;


import org.jainbooks.ebook.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Temporary placeholder/reference
 * 
 * @author Atul Mittal
 * 
 */
public class EBookHomeFragment extends BaseFragment {

	private TextView labelText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_sample,
				container, false);
		setupUiComponent();

		return view;
	}

	@Override
	void setupUiComponent() {
		labelText = ((TextView) view.findViewById(R.id.label_text));
        labelText.setText("Home");
		
	}

}
