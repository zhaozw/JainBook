package com.jainbooks.adapter;

import java.util.List;

import org.jainbooks.ebook.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jainbooks.fragments.EBookCategoryDetailFragment;
import com.jainbooks.model.Category;
import com.jainbooks.utils.JainBooksConstants;

public class EBookHomeFragmentAdapter extends ArrayAdapter<Category> {

	private LayoutInflater inflater;
	
	private List<Category> categoryList;
	Activity activity;
	public EBookHomeFragmentAdapter(Activity activity, 
			List<Category> categoryList) {
		
		super(activity,  R.layout.item_ebook_home, categoryList);
		this.categoryList = categoryList;
		this.activity=activity;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	private class ViewHolder {
		TextView textCategoryName;
		Button btnMore;
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
			holder.btnMore = (Button) convertView.findViewById(R.id.btnMore);
			holder.btnMore.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Fragment fragment=new EBookCategoryDetailFragment();
					Bundle bundle=new Bundle();
					bundle.putString(JainBooksConstants.CATEGORY_ID, "3"/*+category.getId()*/);
					bundle.putString(JainBooksConstants.CATEGORY_NAME, category.getName());
					fragment.setArguments(bundle);
					activity.getFragmentManager().beginTransaction()
					.replace(R.id.content_frame, fragment)
					/*.addToBackStack(null)*/.commit();
				}
			});
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

	holder.textCategoryName.setText(category.getName());

	
		return convertView;
	}

}
