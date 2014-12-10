package com.example.hakatonapp.model;


public class TeachingFeedItem {
	
	BasicFeedItem basicFeedItem;
	int maxStudents;
	int[] studentIds;
	
	public TeachingFeedItem(){}
	
	public BasicFeedItem getBasicFeedItem() {
		return basicFeedItem;
	}
	public void setBasicFeedItem(BasicFeedItem basicFeedItem) {
		this.basicFeedItem = basicFeedItem;
	}
	public int getMaxStudents() {
		return maxStudents;
	}
	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	public int[] getStudentIds() {
		return studentIds;
	}
	public void setStudentIds(int[] studentIds) {
		this.studentIds = studentIds;
	}
	
	

}
