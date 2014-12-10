package com.example.hakatonapp.model;

import java.util.ArrayList;

public class Profile {

	String userId;
	String username;
	String password;
	String firstName;
	String lastName;
	String pictureUrl;
	ArrayList<String> tagsInterested;
	ArrayList<String> tagsTeach;
	int points;

	public Profile() {
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Profile(String ui, String un, String p, String f, String l, String pic, int po,
			ArrayList<String> tI, ArrayList<String> tt) {
		userId = ui;
		username = un;
		password = p;
		firstName = f;
		lastName = l;
		pictureUrl = pic;
		points = po;
		tagsInterested = tI;
		tagsTeach = tt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public ArrayList<String> getTagsInterested() {
		return tagsInterested;
	}

	public void setTagsInterested(ArrayList<String> tagsInterested) {
		this.tagsInterested = tagsInterested;
	}

	public ArrayList<String> getTagsTeach() {
		return tagsTeach;
	}

	public void setTagsTeach(ArrayList<String> tagsTeach) {
		this.tagsTeach = tagsTeach;
	}
}
