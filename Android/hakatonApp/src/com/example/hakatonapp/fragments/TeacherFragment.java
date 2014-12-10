package com.example.hakatonapp.fragments;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.hakatonapp.R;
import com.example.hakatonapp.StudyApplication;
import com.example.hakatonapp.activities.MainActivity;
import com.example.hakatonapp.activities.MainActivity.entryAdd;
import com.example.hakatonapp.data.adapters.StudyEndlessAdapter;
import com.example.hakatonapp.model.BasicFeedItem;
import com.example.hakatonapp.view.CommentExpandCard;
import com.example.hakatonapp.view.CommentListView;
import com.example.hakatonapp.view.FeedItemHeader;
import com.example.hakatonapp.view.ItemCard;

public class TeacherFragment extends Fragment implements entryAdd {

	public static final String ARG_SECTION_NUMBER = "selection_number";
	ArrayList<Card> cards = new ArrayList<Card>();
	CardArrayAdapter endlesAdapter;
	ItemCard card;
	CommentListView list;
	View form;

	EditText title;
	EditText desc;
	EditText tags;
	EditText maxMembers;
	DatePicker datePicker;
	View buttonAddEntry;

	FeedItemHeader header;

	public static TeacherFragment newInstance(int position) {
		TeacherFragment fragment = new TeacherFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, position);
		fragment.setArguments(args);
		return fragment;
	}

	public TeacherFragment() {

	}

	@SuppressLint("CutPasteId")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_teach, container, false);
		list = (CommentListView) rootView.findViewById(R.id.teach_card_list);
		form = rootView.findViewById(R.id.teach_form);
		// FORM STUFF

		buttonAddEntry = rootView.findViewById(R.id.teachAddEntry);
		title = (EditText) rootView.findViewById(R.id.teach_title);
		datePicker = (DatePicker) rootView.findViewById(R.id.datePicker1);
		desc = (EditText) rootView.findViewById(R.id.teach_desc);
		tags = (EditText) rootView.findViewById(R.id.teach_tags);
		tags = (EditText) rootView.findViewById(R.id.teach_max_members);
		// =================

		buttonAddEntry.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String t = title.getText().toString();
				String d = desc.getText().toString();
				String tg = tags.getText().toString();
				String m = tags.getText().toString();
			}
		});

		for (int i = 0; i < StudyApplication.globalBank.getFeed().size(); i++) {

			BasicFeedItem item = StudyApplication.globalBank.getFeed().getFeedItem(i);

			// Create a Card
			card = new ItemCard(getActivity());

			// Create a CardHeader
			header = new FeedItemHeader(getActivity(), item);

			// Set visible the expand/collapse button
			if (item.comments.size() > 0)
				header.setButtonExpandVisible(true);

			// Add Header to card
			card.addCardHeader(header);

			// This provides a simple (and useless) expand area
			CommentExpandCard expand = new CommentExpandCard(getActivity(), list, item, i);

			// Set inner title in Expand Area
			// expand.setTitle(item.get" komentara");

			card.addCardExpand(expand);

			cards.add(card);
		}

		endlesAdapter = new CardArrayAdapter(getActivity(), cards);

		if (list != null) {
			list.setAdapter(endlesAdapter);
		}

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
		if (!form.isShown())
			form.setVisibility(View.VISIBLE);
		else
			form.setVisibility(View.GONE);
	}
}
