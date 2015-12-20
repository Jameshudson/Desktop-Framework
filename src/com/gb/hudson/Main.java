package com.gb.hudson;
	
import java.util.HashMap;
import java.util.Map;

import com.airhacks.afterburner.injection.Injector;
import com.gb.hudson.model.Model;
import com.gb.hudson.model.models.Init;
import com.gb.hudson.model.models.PrinterService;
import com.gb.hudson.presenter.mainview.MainView;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {

	@Override
	public void start(Stage stage) {
		
		//Creating the model.
		Model model = new Model();
		
		model.addService("Init", new Init(model));
		model.addService(PrinterService.PRINTER_SERVICE_NAME, new PrinterService(model));
		
		Map<Object,Object> customConfig = new HashMap<Object, Object>();
		customConfig.put("model", model);
		
		Injector.setConfigurationSource(customConfig::get);
		
		MainView mainView = new MainView();
		Scene scene = new Scene(mainView.getView());
		
		String url = getClass().getResource("main.css").toExternalForm();
		scene.getStylesheets().add(url);
		
		model.AddListener(Init.INIT, (data) -> {
			
			if(data.containsKey("title")){
				
				stage.setTitle((String)data.get("title"));
			}
			
			return true;
		});
		
		model.init();

		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
