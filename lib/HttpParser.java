package lib;
import java.util.ArrayList;
import io.vertx.core.http.HttpServerRequest;
public class HttpParser {
	public ArrayList<String> splitURL(HttpServerRequest request)  {
		String url = request.uri();
		ArrayList<String> list = new ArrayList<String>();
		String controller = "";
		String action = "";
		String param = "";
		if(url.length() == 1) {
			controller = "ProgramsController";
			action 	= "index";
			param = "file=index.html";
		}
		else {
			if(!url.contains("/favicon.ico")) {
				controller = url.split("[/]")[1];
				action = url.split("[/]")[2].split("[?]")[0];
				param = url.split("[?]")[1];
			} else {
				controller = "AssetsController";
				action = "image";
				param = "?file=snapchat.png";
			}
		} 
		list.add(controller);
		list.add(action);
		list.add(param);
		return list;
	}	
}
