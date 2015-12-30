package com.gb.hudson.model.models;

import com.gb.hudson.model.Model;
import com.gb.hudson.model.components.Service;

/**
 * Created by james on 30/12/2015.
 */
public class RegisterService implements Service {

    public static final String REGISTER_SERVICE_NAME = "REGISTER_SERVICE_NAME";

    public static final String REGISTERED = "REGISTERED";
    public static final String UNREGISTERED = "REGISTERED";

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final String HOST = "localhost";
    private static final String PORT = "8888";

    private Model model;

    public RegisterService(Model model){
        this.model = model;
    }

    @Override
    public void init() {

//        SimpleWebService service = new SimpleWebService();
    }

    @Override
    public void removing() {

    }
}
