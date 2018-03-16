package models;
import java.sql.*;

import mappers.*;
public class LoginHistory {
	LoginHistoryMapper historyMapper = new LoginHistoryMapper();
	public String result = "";
	public void executeQuery(Connection conn) throws Exception {
			historyMapper.executeLoginQuery(conn);
			result = historyMapper.result;
	}
}
