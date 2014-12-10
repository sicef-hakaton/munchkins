package com.example.hakatonapp.view;

import it.gmariotti.cardslib.library.internal.CardExpand;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakatonapp.R;
import com.example.hakatonapp.StudyApplication;
import com.example.hakatonapp.data.adapters.CommentAdapter;
import com.example.hakatonapp.model.BasicFeedItem;
import com.example.hakatonapp.model.Comment;

public class CommentExpandCard extends CardExpand {

	CommentAdapter adapter;
	public ListView list;
	public Button send;
	public EditText comment;
	Context ctx;
	BasicFeedItem item;
	private CommentListView parentList;
	private TextView commentInfo;
	int rowId;

	public CommentExpandCard(Context context, CommentListView parentList, BasicFeedItem item, int rowId) {
		super(context, R.layout.card_comment);
		ctx = context;
		this.parentList = parentList;
		this.item = item;
		this.rowId = rowId;
	}

	@Override
	public void setupInnerViewElements(ViewGroup parent, View view) {
		adapter = new CommentAdapter(ctx, rowId);
		list = (ListView) parent.findViewById(R.id.comment_list);
		commentInfo = (TextView) parent.findViewById(R.id.comment_info);
		commentInfo.setText(item.comments.size() + " komentara");
		send = (Button) parent.findViewById(R.id.comment_button_sendsend);
		comment = (EditText) parent.findViewById(R.id.comment_edit_text);

		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String mComment = comment.getText().toString();

				// ODAVDE SENDAJ KOMENTAR!!!!!

				Toast.makeText(ctx, mComment, Toast.LENGTH_SHORT).show();
			}
		});

		parentList.addViewThatWantTouch(list);
		parentList.addViewThatWantTouch(send);
		parentList.addViewThatWantTouch(comment);

		// set dummy data for now
		// ArrayList<Comment> comments = new ArrayList<Comment>();

		// for (int i = 0; i < 10; i++) {
		// comments.add(new Comment("username", "", "comment comment aslkdj laksjd aslj "));
		// }

		adapter.setData(StudyApplication.globalBank.getFeed().getFeedItem(rowId).comments);
		list.setAdapter(adapter);

	}
}
