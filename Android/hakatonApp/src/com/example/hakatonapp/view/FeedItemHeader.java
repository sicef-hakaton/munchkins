package com.example.hakatonapp.view;

import it.gmariotti.cardslib.library.internal.CardHeader;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hakatonapp.R;
import com.example.hakatonapp.model.BasicFeedItem;

public class FeedItemHeader extends CardHeader {

	BasicFeedItem item;

	public FeedItemHeader(Context context, BasicFeedItem item) {
		super(context, R.layout.item_header);
		this.item = item;
	}

	@Override
	public void setupInnerViewElements(ViewGroup parent, View view) {
		if (view != null) {
			TextView t1 = (TextView) view.findViewById(R.id.item_header_title);
			// if (t1!=null)
			t1.setText(item.getTitle());

			String tagovi = " ";
			for (String s : item.getTags()) {
				tagovi += s + " ";
			}

			TextView t2 = (TextView) view.findViewById(R.id.item_header_tags);
			t2.setText(tagovi);
		}
	}
}
