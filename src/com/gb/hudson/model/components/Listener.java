package com.gb.hudson.model.components;

import java.util.HashMap;

public interface Listener {
	
	public boolean event(HashMap<String, Object> data);

}