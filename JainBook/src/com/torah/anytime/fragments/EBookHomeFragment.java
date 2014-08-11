package com.torah.anytime.fragments;


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
public class EBookHomeFragment extends Fragment {

	private TextView labelText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_sample,
				container, false);
		labelText = ((TextView) contentView.findViewById(R.id.label_text));
		//Bundle bundle = getArguments();
		//String label = bundle.getString("label");
		labelText.setText("Home");
//		Random rnd = new Random();
//		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),
//				rnd.nextInt(256));
//		contentView.setBackgroundColor(color);
		return contentView;
	}

}
