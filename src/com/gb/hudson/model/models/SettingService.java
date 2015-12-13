package com.gb.hudson.model.models;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.gb.hudson.model.components.Service;

public class SettingService implements Service {
	
	private static final String USER_SETTINGS = "user_settings";
	private static final String APP_ID = "printing program";
	
	private PropertiesConfiguration configProps;

	public SettingService() {
		
		try {
			configProps = new PropertiesConfiguration(USER_SETTINGS);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String appId = configProps.getString(APP_ID);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removing() {
		// TODO Auto-generated method stub

	}
	
	public void setSetting(String key, Object value){
		
	}
}