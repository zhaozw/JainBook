package com.jainbooks.adapter;

import java.util.Locale;

import com.jainbooks.fragments.EBookHomeFragment;
import com.jainbooks.fragments.EbookCatogeryFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;


public class EBookStoreFragmentAdapter extends FragmentStatePagerAdapter {

	private String[] videoPlayerTabs = { "Categorys", "Home"};
 
	public EBookStoreFragmentAdapter(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int arg0) {
		if (arg0 == 0) {
			return new EbookCatogeryFragment();
		}else {
			return new EBookHomeFragment();
		}
	}

	@Override
	public int getCount() {
		return 2;
	}

	
	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {

		case 0:

			return videoPlayerTabs[0];

		case 1:
			return videoPlayerTabs[1];

		}
		return null;
	}
}
