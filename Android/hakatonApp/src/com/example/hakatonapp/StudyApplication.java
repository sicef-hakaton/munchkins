package com.example.hakatonapp;

import android.app.Application;
import android.content.Context;

import com.example.hakatonapp.data.GlobalBank;

public class StudyApplication extends Application {

	private static StudyApplication instance;
	public static GlobalBank globalBank;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		globalBank = new GlobalBank();
	}

	public static Context getContext() {
		return instance;
	}
}
