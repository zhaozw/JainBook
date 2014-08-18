package com.jainbooks.adapter;

import java.util.List;

import org.jainbooks.ebook.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jainbooks.model.EBook;

public class MyLibraryAdapter extends ArrayAdapter<EBook> {

	private Activity mActivity;
	private LayoutInflater inflater;
	List<EBook> ebooks;

	public MyLibraryAdapter(Activity argActivity,
			List<EBook> ebooks) {
		super(argActivity, org.jainbooks.ebook.R.layout.item_mylibrary, ebooks);
		mActivity = argActivity;
		inflater = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.ebooks = ebooks;
	}

	private class ViewHolder {
		TextView textTitle;
		ImageView imageViewBook;
		
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent) {
		EBook eBook=ebooks.get(position);
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_mylibrary, null);
			
			
			holder.imageViewBook = (ImageView) convertView
					.findViewById(R.id.imageViewBook);
			/*holder.imageViewBook
			.setScaleType(ImageView.ScaleType.FIT_CENTER);*/
		 holder.textTitle = (TextView) convertView
					.findViewById(R.id.textTitle);
						convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		/*new ImageLoader(mActivity).displayRoundedCornersImage(
				eBook.getThumbnailUrl(), holder.imageViewbook,
				false);*/
	   holder.textTitle.setText(eBook.getTitle());
	  		return convertView;
	}

}
