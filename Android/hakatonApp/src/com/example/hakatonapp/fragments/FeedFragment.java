package com.example.hakatonapp.fragments;

import java.security.KeyStore.Entry;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hakatonapp.R;
import com.example.hakatonapp.StudyApplication;
import com.example.hakatonapp.activities.MainActivity;
import com.example.hakatonapp.activities.MainActivity.entryAdd;

public class FeedFragment extends Fragment implements entryAdd {

	public static final String ARG_SECTION_NUMBER = "selection_number";

	public static FeedFragment newInstance(int position) {
		FeedFragment fragment = new FeedFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, position);
		fragment.setArguments(args);
		return fragment;
	}

	public FeedFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
	}

	public static CharSequence getTitle() {
		return StudyApplication.getContext().getString(R.string.title_section1);
	}

	@Override
	public void addNewEntry() {
		// TODO Auto-generated method stub

	}
}
