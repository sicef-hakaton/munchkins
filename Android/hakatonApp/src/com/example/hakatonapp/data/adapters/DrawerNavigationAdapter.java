package com.example.hakatonapp.data.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hakatonapp.R;

public class DrawerNavigationAdapter extends BaseAdapter {

	Activity ac;
	private LayoutInflater inflater;

	public DrawerNavigationAdapter(Activity ac) {
		this.ac = ac;
		inflater = (LayoutInflater) ac.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return 8;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean areAllItemsEnabled() {
		// zelim da header bude preskocen
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		// zelim da header bude preskocen
		if (position == 0 || position == 4)
			return false;
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		TextView tv = null;
		ImageView iv = null;

		if (position == 0 || position == 4) {
			if (convertView == null)
				convertView = inflater.inflate(R.layout.drawer_list_divider, parent, false);
		} else {
			if (convertView == null)
				convertView = inflater.inflate(R.layout.drawer_list_item, parent, false);
			tv = (TextView) convertView.findViewById(R.id.drawer_item_text);
			iv = (ImageView) convertView.findViewById(R.id.drawer_item_logo);

			switch (position) {

			case 1:
				iv.setImageResource(R.drawable.megaphone15);
				tv.setText(ac.getResources().getString(R.string.title_section1));
				break;
			case 2:
				iv.setImageResource(R.drawable.multiple25);
				tv.setText(ac.getResources().getString(R.string.title_section2));
				break;
			case 3:
				iv.setImageResource(R.drawable.black268);
				tv.setText(ac.getResources().getString(R.string.title_section3));
				break;

			case 5:
				iv.setImageResource(R.drawable.male18);
				tv.setText(ac.getResources().getString(R.string.title_section4));
				break;
			case 6:
				iv.setImageResource(R.drawable.tools3);
				tv.setText(ac.getResources().getString(R.string.title_section5));
				break;
			case 7:
				iv.setImageResource(R.drawable.idea);
				tv.setText(ac.getResources().getString(R.string.title_section6));
				break;
			}

		}

		return convertView;
	}

}
