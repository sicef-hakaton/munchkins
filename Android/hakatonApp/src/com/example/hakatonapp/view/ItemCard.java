package com.example.hakatonapp.view;

import it.gmariotti.cardslib.library.internal.Card;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hakatonapp.R;

public class ItemCard extends Card {

	public ItemCard(Context context) {
		this(context, R.layout.item_card);
	}

	public ItemCard(Context context, int innerLayout) {
		super(context, innerLayout);
	}

	@Override
	public void setupInnerViewElements(ViewGroup parent, View view) {

		View prihvatiButton = parent.findViewById(R.id.card_button);
		TextView txt = (TextView) parent.findViewById(R.id.item_header_title);

		txt.setText("4 ljudi planira da učestvuje");

		prihvatiButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// OVDE IDE POZIV ZA PRIHVATI CASOVE - PRIDRUZI SE I POKRENI ZABAVU ;)

			}
		});
	}
}
