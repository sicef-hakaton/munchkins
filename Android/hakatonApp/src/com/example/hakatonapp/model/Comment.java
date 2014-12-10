package com.example.hakatonapp.model;

public class Comment {

	String username;
	String themeId;
	String commenttext;

	public Comment(String username, String avatarUrl, String commenttext) {
		super();
		this.username = username;
		this.themeId = avatarUrl;
		this.commenttext = commenttext;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getThemeId() {
		return themeId;
	}

	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}

	public String getCommenttext() {
		return commenttext;
	}

	public void setCommenttext(String commenttext) {
		this.commenttext = commenttext;
	}

}
