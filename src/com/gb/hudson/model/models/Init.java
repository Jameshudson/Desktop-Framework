package com.gb.hudson.model.models;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.components.Service;

public class Init implements Service {
	
	public static final String INIT = "INIT";
	
	private String title = "something";

	public Init(Model model) {
		
		model.addInitData("title", title);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removing() {
		// TODO Auto-generated method stub
		
	}
}