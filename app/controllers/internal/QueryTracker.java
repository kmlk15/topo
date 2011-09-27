package controllers.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.Logger;
import play.Play;
import play.mvc.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class QueryTracker extends Controller {

	private static final String URL = Play.configuration.getProperty("db.url");
	private static final String USER = Play.configuration.getProperty("db.user");
	private static final String PASSWORD = Play.configuration.getProperty("db.pass");
	private static final String DRIVER_NAME = Play.configuration.getProperty("db.driver");
	private static final String DIRECTORY = "db";
	
	static {
		try {
			Class.forName(DRIVER_NAME).newInstance();
			Logger.info("*** Driver loaded");
		} catch (Exception e) {
			Logger.error(e, "*** Error");
		}

	};

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static void index() {
		File dir = new File(DIRECTORY);

		String[] files = dir.list();
		// It is also possible to filter the list of returned files.
		// This example does not return any files that start with `.'.
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".sql");
			}
		};
		files = dir.list(filter);
		List<String> listing = null;
		if (files != null) {
			listing = Arrays.asList(files);
		}
		render(listing);
	}

	public static void run(String name) {
		Map<String, Object> m = new HashMap<String, Object>();
		Gson g = new GsonBuilder().create();
		String s = new String();
		StringBuffer sb = new StringBuffer();

		try {
			FileReader fr = new FileReader(new File(DIRECTORY + "/" + name));
			BufferedReader br = new BufferedReader(fr);
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();

			String[] inst = sb.toString().split(";");

			Connection c = getConnection();
			Statement st = c.createStatement();

			for (int i = 0; i < inst.length; i++) {
				if (!inst[i].trim().equals("")) {
					Logger.info(inst[i]);
					st.executeUpdate(inst[i]);
				}
			}
			m.put("status", 200);
		} catch (Exception e) {
			Logger.error(e, "*** Error");
			m.put("status", 500);
		}
		renderText(g.toJson(m));
	}
}
