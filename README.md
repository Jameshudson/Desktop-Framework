# Java-print-pdf-order
This small Java fx program allows the user to quickly order pdf's to be printed.

Java print pdf order is heavily based on dependency injection and the MVP design pattern. The model itself is just an observer that holds services and Event objects. Services should be suited to one task ie a Service that gets news articles from a news api. And the Event objects that sit inbetween the service and the view should only handle processing the data passed by the model in a map to update the view.

Example of how to resiger a Service to the model.

```Java
model.addService("Service Name", new ServiceObject(model));
```

Example of how to add an event object to listen for a specified event.

```Java
model.AddListener("event name", (data) -> {
  //some action to update the view.
  return true;
});
```

note that any Service can call any event.

Example of how to fire an event from a Service.

```Java
model.fireListenerEvent("event name", new HashMap<String, Object>());
```

anything registered to listen to this event will be executed.

The Presenter.
The presenter sits inbetween the view and the model and "talks" to both. A typical Presenter will handle user input and model events. To register model events, first you must implements Initializable in to the class.

```Java
public class MainPresenter implements Initializable {
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
```

Links

afterburner.fx handles injecting the model and FXML loading. 
http://afterburner.adam-bien.com/

pdfbox is used to help with managing pdf's and getting them ready for printing.
https://pdfbox.apache.org/
