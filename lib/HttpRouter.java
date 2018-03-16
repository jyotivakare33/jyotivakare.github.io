package lib;
import java.util.*;
import java.io.*;
public class HttpRouter {
	public String controllerName = "";
	public HttpRouter mapController(String controller) {
		String[] listOfControllers = {"ProgramsController","UsersController","AssetsController","HistoriesController", "MailersController"};
		for(int index = 0; index < listOfControllers.length; index++) {
			if(controller.equals(listOfControllers[index])) {
				controllerName = listOfControllers[index];
			}
		}
		System.out.println(controllerName+"router");
		return this;
	}
}
