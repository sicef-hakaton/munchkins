package com.example.hakatonapp.model;

import java.util.ArrayList;

public class Feed {

	public ArrayList<BasicFeedItem> items;

	public Feed() {
		items = new ArrayList<BasicFeedItem>();
	}

	/** Get current size of feed elements. Is expended by lazy load */
	public int size() {
		return items.size();
	}

	public BasicFeedItem getFeedItem(int position) {
		if (items != null && position < items.size())
			return items.get(position);
		return null;

	}
}
