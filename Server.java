import io.vertx.core.http.*;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.net.*;
import io.vertx.core.AbstractVerticle;
import java.lang.reflect.*;

import lib.DBProgram;
import lib.HttpParser;
import lib.HttpRouter;
import controllers.ProgramsController;
import controllers.UsersController;
import controllers.AssetsController;
import controllers.HistoriesController;
import controllers.MailersController;
public class Server extends AbstractVerticle {
	
	public void start() {
		try {
			VertxOptions options = new VertxOptions(); 
			options.setMaxEventLoopExecuteTime(Long.MAX_VALUE);
			vertx = Vertx.vertx(options);
			Connection conn = new DBProgram().getConnection();
			HttpServer httpServer = vertx.createHttpServer();
			httpServer.requestHandler(request -> {
				HttpServerResponse response = request.response();
				response.setStatusCode(200);
				response.setChunked(true);
				if(conn == null) 
					System.exit(0);
				String result = "";
				
					ArrayList<String> list = new ArrayList<String>();
					HttpParser parser = new HttpParser();
					HttpRouter router = new HttpRouter();
					list = parser.splitURL(request);
					String controller = list.get(0);
					String action = list.get(1);
					router = router.mapController(controller);
					String controllerName = router.controllerName;
					System.out.println(controller);
					System.out.println("list =="+list);
					System.out.println("URL==>" + request.uri());
					try {
						Class<?> cls = Class.forName("controllers."+controllerName);
						Object obj = cls.newInstance();
						Method method = cls.getDeclaredMethod(action, Connection.class, HttpServerRequest.class, HttpServerResponse.class );
						method.invoke(obj,conn,request,response);
					}
					catch(Exception e) { 
						e.printStackTrace();
					}
					response.end();
			});	
			httpServer.listen(3000);
		}
		catch(Exception e) {
			System.out.println("exception");
		}
	}
}
				
