package com.jainbooks.adapter;

import java.util.List;

import org.jainbooks.ebook.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jainbooks.model.EBook;
import com.jainbooks.utils.ImageLoader;

public class EBookCategoryDetailAdapter extends ArrayAdapter<EBook> {

	private Activity mActivity;
	private LayoutInflater inflater;
	List<EBook> ebooks;

	public EBookCategoryDetailAdapter(Activity argActivity,
			List<EBook> ebooks) {
		super(argActivity, org.jainbooks.ebook.R.layout.item_myfragment, ebooks);
		mActivity = argActivity;
		inflater = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.ebooks = ebooks;
	}

	private class ViewHolder {
		TextView textTitle,textAuthor,textBookCategory,textSummery;
		ImageView imageViewbook;
		RatingBar ratingBar;
		LinearLayout layoutListItemDetails;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent) {
		EBook eBook=ebooks.get(position);
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_myfragment, null);
			
			
			holder.imageViewbook = (ImageView) convertView
					.findViewById(R.id.imageViewbook);
			holder.textAuthor = (TextView) convertView
					.findViewById(R.id.textAuthor);
			holder.textTitle = (TextView) convertView
					.findViewById(R.id.textTitle);
			holder.textBookCategory = (TextView) convertView
					.findViewById(R.id.textBookCategory);
			holder.textBookCategory.setVisibility(View.GONE);
			holder.textSummery = (TextView) convertView
					.findViewById(R.id.textSummery);
			holder.layoutListItemDetails = (LinearLayout) convertView
					.findViewById(R.id.layoutListItemDetails);
			holder.ratingBar=(RatingBar)convertView.findViewById(R.id.ratingBar);
			
			
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		/*new ImageLoader(mActivity).displayRoundedCornersImage(
				eBook.getThumbnailUrl(), holder.imageViewbook,
				false);*/
	  // holder.textBookCategory.setText(eBook.getCategory().getName());
	   holder.textTitle.setText(eBook.getTitle());
	   holder.textAuthor.setText(eBook.getAuthor());
	   holder.textSummery.setText(eBook.getSummary());
	   holder.ratingBar.setRating(eBook.getRating());
      /* holder.layoutListItemDetails.setDrawingCacheEnabled(true);
	   holder.layoutListItemDetails.measure(
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

		convertView.setMinimumHeight(holder.layoutListItemDetails.getMeasuredHeight() + 15);

		RelativeLayout.LayoutParams paramsImage = (android.widget.RelativeLayout.LayoutParams) holder.imageViewbook
				.getLayoutParams();
		paramsImage.height = holder.layoutListItemDetails.getMeasuredHeight();
		holder.imageViewbook.setLayoutParams(paramsImage);
		holder.imageViewbook
				.setScaleType(ImageView.ScaleType.FIT_CENTER);*/

		return convertView;
	}

}
