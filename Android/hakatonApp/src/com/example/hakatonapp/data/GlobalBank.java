package com.example.hakatonapp.data;

import com.example.hakatonapp.model.Feed;

public class GlobalBank {

	Feed feed;

	public GlobalBank() {
		feed = new Feed();
	}

	public Feed getFeed() {
		return this.feed;
	}

}
