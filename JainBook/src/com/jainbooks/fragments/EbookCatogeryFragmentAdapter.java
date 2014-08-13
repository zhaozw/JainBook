package com.jainbooks.fragments;

import org.jainbooks.ebook.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EbookCatogeryFragmentAdapter extends BaseAdapter {

	private Activity mActivity;
	private LayoutInflater inflater;
	private String[] mFragmentTitles;
	private String[] mCount;

	public EbookCatogeryFragmentAdapter(Activity argActivity) {
		mActivity = argActivity;
		inflater = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mFragmentTitles = mActivity.getResources().getStringArray(
				R.array.category_items);
		
	}

	@Override
	public int getCount() {
		return mFragmentTitles.length;
	}

	@Override
	public Object getItem(int arg0) {
		return mFragmentTitles[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.category_list_item, null);
				}

		TextView textViewDrawerMenuItem = (TextView) arg1
				.findViewById(R.id.textViewDrawerMenuItem);
	
		if (textViewDrawerMenuItem != null)
			textViewDrawerMenuItem.setText(mFragmentTitles[arg0]);
	
		
		return arg1;
	}

}
