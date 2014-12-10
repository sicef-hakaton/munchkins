package com.example.hakatonapp.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;
import com.example.hakatonapp.model.BasicFeedItem;
import com.example.hakatonapp.model.Comment;
import com.example.hakatonapp.model.Profile;

public class Parser {

	public static Profile parseProfile(String response) {
		JSONObject jObject = null;

		Profile profile = new Profile("", "", "", "", "", "", 5, new ArrayList<String>(),
				new ArrayList<String>());

		if (response != null && response != "") {
			try {
				jObject = new JSONObject(response);
				profile.setUserId(jObject.getString("profileid"));
				profile.setUsername(jObject.getString("username"));
				profile.setFirstName(jObject.getString("first_name"));
				profile.setLastName(jObject.getString("last_name"));
				profile.setPictureUrl(jObject.getString("picture"));
				profile.setPassword(jObject.getString("password"));
				profile.setPoints(jObject.getInt("points"));

				JSONArray jInterested = null, jTeach = null;

				jInterested = jObject.getJSONArray("tags_interested");
				jTeach = jObject.getJSONArray("tags_teach");

				ArrayList<String> tagsTeach = new ArrayList<String>();
				ArrayList<String> tagsInterested = new ArrayList<String>();

				for (int i = 0; i < jInterested.length(); i++) {
					tagsInterested.add(jInterested.getString(i));
				}
				profile.setTagsInterested(tagsInterested);

				for (int i = 0; i < jInterested.length(); i++) {
					tagsTeach.add(jTeach.getString(i));
				}
				profile.setTagsInterested(tagsInterested);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return profile;
		} else {
			return null;
		}
	}

	public static BasicFeedItem parseBasicFeedItem(String response) {
		JSONObject jObject = null;
		BasicFeedItem basicFeedItem = new BasicFeedItem("", "", "", new ArrayList<String>(), new Date(), 0,
				new ArrayList<Integer>());

		if (response != null && response != "") {
			try {

				jObject = new JSONObject(response);
				basicFeedItem.setRoomId(jObject.getString("_id"));
				basicFeedItem.setOwnerUsername(jObject.getString("teacher"));
				basicFeedItem.setTitle(jObject.getString("theme"));
				// basicFeedItem.setDateAndTime(Date.valueOf(jObject.getString("date_time")));
				basicFeedItem.setMaxStudents(jObject.getInt("max_students"));

				JSONArray jIds = jObject.getJSONArray("students");
				ArrayList<Integer> Ids = new ArrayList<Integer>();
				for (int i = 0; i < jIds.length(); i++) {
					Ids.add(new Integer(jIds.getInt(i)));
				}
				basicFeedItem.setStudentIds(Ids);

				JSONArray jTags = jObject.getJSONArray("tags");
				ArrayList<String> tags = new ArrayList<String>();
				for (int i = 0; i < jTags.length(); i++) {
					tags.add(jTags.getString(i));
				}
				basicFeedItem.setTags(tags);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return basicFeedItem;
		} else {
			return null;
		}
	}

	/*
	 * public static TeachingFeedItem parseTeachingFeedItem(String response) { JSONObject jObject = null;
	 * TeachingFeedItem teachingFeedItem = new TeachingFeedItem();
	 * 
	 * if(response != null && response != "") { try {
	 * 
	 * jObject = new JSONObject(response);
	 * 
	 * teachingFeedItem.setBasicFeedItem(parseBasicFeedItem(response));
	 * teachingFeedItem.setMaxStudents(jObject.getInt("max_students"));
	 * 
	 * JSONArray jStudentIds = jObject.getJSONArray("students"); int[] studentIds = new
	 * int[jStudentIds.length()]; for(int i = 0; i<jStudentIds.length(); i++) { studentIds[i] =
	 * jStudentIds.getInt(i); } teachingFeedItem.setStudentIds(studentIds);
	 * 
	 * 
	 * } catch (JSONException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return teachingFeedItem; } else { return null; } }
	 */

	public static Comment parseComment(String response) {
		JSONObject jObject = null;
		Comment comment = new Comment("", "", "");

		if (response != null && response != "") {
			try {
				jObject = new JSONObject(response);
				comment.setCommenttext("text");
				comment.setThemeId("theme");
				comment.setUsername("username");

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return comment;
		} else {
			return null;
		}
	}

	public static ArrayList<Comment> parseCommentArray(String response) {
		JSONObject jObject = null;
		ArrayList<Comment> commentArray = new ArrayList<Comment>();

		if (response != null && response != "") {
			try {

				jObject = new JSONObject(response);
				JSONArray jArray = jObject.getJSONArray("comments");

				for (int i = 0; i < jArray.length(); i++) {
					commentArray.add(parseComment(jArray.getString(i).toString()));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return commentArray;
		} else {
			return null;
		}
	}

	public static JSONObject createProfileJSON(Profile profile) {
		String tagsInterested = "[", tagsTeach = "[";
		HashMap map = new HashMap();

		map.put("password", profile.getPassword());
		map.put("first_name", profile.getFirstName());
		map.put("last_name", profile.getLastName());
		map.put("picture", profile.getPictureUrl());
		map.put("points", "" + profile.getPoints());

		int i;
		for (i = 0; i < profile.getTagsInterested().size() - 1; i++) {
			tagsInterested += "\"" + profile.getTagsInterested().get(i) + "\", ";
		}
		i++;
		tagsInterested += "\"" + profile.getTagsInterested().get(i) + "\"]";

		for (i = 0; i < profile.getTagsTeach().size() - 1; i++) {
			tagsTeach += "\"" + profile.getTagsTeach().get(i) + "\", ";
		}
		i++;
		tagsTeach += "\"" + profile.getTagsTeach().get(i) + "\"]";

		map.put("tags_interested", tagsInterested);
		map.put("tags_teach", tagsTeach);

		return new JSONObject(map);
	}

	public static JSONObject createFeedItemJSON(BasicFeedItem feedItem) {
		String studentIds = "[";
		HashMap map = new HashMap();

		map.put("class_id", feedItem.getRoomId());
		map.put("teacher", feedItem.getOwnerUsername());
		map.put("theme", feedItem.getTitle());
		// map.put("date_time", feedItem.getDateAndTime().toString());
		map.put("max_students", "" + feedItem.getMaxStudents());

		int i;
		for (i = 0; i < feedItem.getStudentIds().size() - 1; i++) {
			studentIds += feedItem.getStudentIds().get(i) + ", ";
		}
		i++;
		studentIds += feedItem.getStudentIds().get(i) + "]";

		map.put("students", studentIds);

		return new JSONObject(map);
	}

	public static JSONObject createCommentsJSON(ArrayList<Comment> commentArray) {
		String comments = "[";
		HashMap map = new HashMap();

		int i;
		for (i = 0; i < commentArray.size() - 1; i++) {
			comments += "{ \"username\":" + commentArray.get(i).getUsername() + ",\"text\":"
					+ commentArray.get(i).getCommenttext() + ", \"theme\":"
					+ commentArray.get(i).getThemeId() + "}, ";
		}
		i++;
		comments += "{ \"username\":" + commentArray.get(i).getUsername() + ",\"text\":"
				+ commentArray.get(i).getCommenttext() + ", \"theme\":" + commentArray.get(i).getThemeId()
				+ "}]";

		map.put("comments", comments);

		return new JSONObject(map);
	}

}
