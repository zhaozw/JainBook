package com.jainbooks.adapter;

import java.util.List;

import org.jainbooks.ebook.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jainbooks.model.Category;

public class EBookHomeFragmentAdapter extends ArrayAdapter<Category> {

	private LayoutInflater inflater;
	
	private List<Category> categoryList;

	public EBookHomeFragmentAdapter(Activity activity, 
			List<Category> categoryList) {
		
		super(activity,  R.layout.item_ebook_home, categoryList);
		this.categoryList = categoryList;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	private class ViewHolder {
		TextView textCategoryName;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Category category = categoryList.get(position);
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_ebook_home, null);
			holder.textCategoryName = (TextView) convertView
					.findViewById(R.id.textCategoryName);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

	holder.textCategoryName.setText(category.getName());

	
		return convertView;
	}

}
