package com.gb.hudson.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.gb.hudson.model.components.Listener;
import com.gb.hudson.model.components.Service;
import com.gb.hudson.model.models.Init;
import org.apache.commons.collections.IteratorUtils;

public class Model {
	
	protected HashMap<String, List<Listener>> listeners;
	private HashMap<String, Service> services;
	
	private HashMap<String, Object> initData;
	
	public Model(){

		listeners = new HashMap<String, List<Listener>>();
		services = new HashMap<String, Service>();
		
		initData = new HashMap<String, Object>();
	}
	
	public void AddListener(String listenerName, Listener listener){
		
		//checks if the listener name is already resister.
		if(listeners.containsKey(listenerName)){
				
			listeners.get(listenerName).add(listener);
		}else{//if it isn't then resister.
			
			listeners.put(listenerName, new LinkedList<Listener>());
			listeners.get(listenerName).add(listener);
		}
	}
	
	/*
	 * 
	 */
	public void addService(String serviceName, Service newService){
		
		if(!services.containsKey(serviceName)){
			
			newService.init();
			services.put(serviceName, newService);
		}
	}
	
	public void removeService(String serviceName){
		
		if(services.containsKey(serviceName)){
			
			services.remove(serviceName);
		}
	}
	
	public void fireListenerEvent(String listenersNames, HashMap<String, Object> data){
		
		if(listeners.containsKey(listenersNames) && listeners.get(listenersNames).size() > 0){
			
			List<Listener> list = listeners.get(listenersNames);
			
			for(int i = list.size() - 1; i >= 0; i--){
				
				list.get(i).event(data);
			}
		}
	}
	
	public void addInitData(String name, Object data){
		this.initData.put(name, data);
	}
	
	private void dropListenerEvents(){
		
		listeners = new HashMap<String, List<Listener>>();
	}
	
	public void switchController(boolean bool){
		
		if(bool == true){
			
			dropListenerEvents();
		}
	}
	
	public Service getService(String name){
		
		if(services.containsKey(name)){
			return services.get(name);
		}
		
		return null;
	}
	
	public String[] getServiceNames(){
		
		Iterator<String> names = services.keySet().iterator();
		
		return (String[]) IteratorUtils.toArray(names);
	}

	public void init() {
		
		this.fireListenerEvent(Init.INIT, initData);
	}
}