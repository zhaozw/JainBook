package com.jainbooks.fragments;

import java.util.List;

import org.jainbooks.ebook.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jainbooks.adapter.EbookCatogeryFragmentAdapter;
import com.jainbooks.model.Category;
import com.jainbooks.model.Store;
import com.jainbooks.utils.JainBooksConstants;

public class EbookCatogeryFragment extends BaseFragment {

	private ListView listCategory;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_category, container, false);
		
		return view;
	}
@Override
public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	setupUiComponent();
}
	@Override
	void setupUiComponent() {
		listCategory = ((ListView) view.findViewById(R.id.listCategory));
		
		Store store = null;
	
			store = dashboardActivity.app.getStore();
	
		
		final List<Category> categories =store.getAllCategory();
		if (categories != null) {
			listCategory
					.setAdapter(new EbookCatogeryFragmentAdapter(
							dashboardActivity,
							categories));
			listCategory.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Category category=categories.get(arg2);
					Fragment fragment=new EBookCategoryDetailFragment();
					Bundle bundle=new Bundle();
					bundle.putString(JainBooksConstants.CATEGORY_ID, "3"/*+category.getId()*/);
					bundle.putString(JainBooksConstants.CATEGORY_NAME, category.getName());
					fragment.setArguments(bundle);
					getFragmentManager().beginTransaction()
					.replace(R.id.content_frame, fragment)
					/*.addToBackStack(null)*/.commit();
					
				}
			});
			
		}

	}

}
